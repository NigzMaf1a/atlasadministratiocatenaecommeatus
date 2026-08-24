package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.CreateStoredItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.StoredItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.UpdateStoredItemQuantityRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Item;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Store;
import com.nigel.atlas_administratio_catenae_commeatus.entity.StoredItem;
import com.nigel.atlas_administratio_catenae_commeatus.exception.DuplicateResourceException;
import com.nigel.atlas_administratio_catenae_commeatus.exception.ResourceNotFoundException;
import com.nigel.atlas_administratio_catenae_commeatus.repository.ItemRepository;
import com.nigel.atlas_administratio_catenae_commeatus.repository.StoreRepository;
import com.nigel.atlas_administratio_catenae_commeatus.repository.StoredItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoredItemServiceImpl implements StoredItemService {

    private final StoredItemRepository storedItemRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;

    public StoredItemServiceImpl(
            StoredItemRepository storedItemRepository,
            StoreRepository storeRepository,
            ItemRepository itemRepository) {
        this.storedItemRepository = storedItemRepository;
        this.storeRepository = storeRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public StoredItemResponse create(
            CreateStoredItemRequest request) {

        if (storedItemRepository.existsByStore_IdAndItem_Id(
                request.storeId(),
                request.itemId())) {
            throw new DuplicateResourceException(
                    "Item already exists in the specified store");
        }

        Store store = findStore(request.storeId());

        Item item = findItem(request.itemId());

        StoredItem storedItem = new StoredItem();

        storedItem.setStore(store);
        storedItem.setItem(item);
        storedItem.setQuantity(request.itemQty());

        StoredItem saved = storedItemRepository.save(storedItem);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoredItemResponse> findAll() {

        return storedItemRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoredItemResponse> findByStoreId(
            Integer storeId) {

        // Validate that the store exists.
        findStore(storeId);

        return storedItemRepository
                .findAllByStore_Id(storeId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoredItemResponse> findByItemId(
            Integer itemId) {

        // Validate that the item exists.
        findItem(itemId);

        return storedItemRepository
                .findAllByItem_Id(itemId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public StoredItemResponse updateQuantity(
            Integer id,
            UpdateStoredItemQuantityRequest request) {

        StoredItem storedItem = findStoredItem(id);

        storedItem.setQuantity(request.itemQty());

        StoredItem updated = storedItemRepository.save(storedItem);

        return toResponse(updated);
    }

    private StoredItem findStoredItem(Integer id) {

        return storedItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Stored item not found with id: " + id));
    }

    private Store findStore(Integer id) {

        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store not found with id: " + id));
    }

    private Item findItem(Integer id) {

        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item not found with id: " + id));
    }

    private StoredItemResponse toResponse(
            StoredItem storedItem) {

        return new StoredItemResponse(
                storedItem.getId(),
                storedItem.getStore().getId(),
                storedItem.getItem().getId(),
                storedItem.getQuantity());
    }
}