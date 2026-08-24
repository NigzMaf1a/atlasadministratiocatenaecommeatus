package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.item.CreateItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.ItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemDescriptionRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemNameRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemValueRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Item;
import com.nigel.atlas_administratio_catenae_commeatus.exception.DuplicateResourceException;
import com.nigel.atlas_administratio_catenae_commeatus.exception.ResourceNotFoundException;
import com.nigel.atlas_administratio_catenae_commeatus.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemResponse create(CreateItemRequest request) {

        validateReferenceUniqueness(request.itemRef());

        validateImageUrlUniqueness(request.itemImgUrl());

        Item item = new Item();

        item.setImageUrl(request.itemImgUrl());
        item.setReference(request.itemRef());
        item.setValue(request.itemValue());
        item.setName(request.itemName());
        item.setDescription(request.itemDesc());

        Item saved = itemRepository.save(item);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemResponse> findAll() {

        return itemRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ItemResponse update(
            Integer id,
            UpdateItemRequest request) {

        Item item = findItem(id);

        if (itemRepository.existsByReferenceAndIdNot(
                request.itemRef(),
                id)) {
            throw new DuplicateResourceException(
                    "Item reference already exists: "
                            + request.itemRef());
        }

        if (request.itemImgUrl() != null
                && itemRepository.existsByImageUrlAndIdNot(
                        request.itemImgUrl(),
                        id)) {
            throw new DuplicateResourceException(
                    "Item image URL already exists");
        }

        item.setImageUrl(request.itemImgUrl());
        item.setReference(request.itemRef());
        item.setValue(request.itemValue());
        item.setName(request.itemName());
        item.setDescription(request.itemDesc());

        Item updated = itemRepository.save(item);

        return toResponse(updated);
    }

    @Override
    public ItemResponse updateValue(
            Integer id,
            UpdateItemValueRequest request) {

        Item item = findItem(id);

        item.setValue(request.itemValue());

        Item updated = itemRepository.save(item);

        return toResponse(updated);
    }

    @Override
    public ItemResponse updateName(
            Integer id,
            UpdateItemNameRequest request) {

        Item item = findItem(id);

        item.setName(request.itemName());

        Item updated = itemRepository.save(item);

        return toResponse(updated);
    }

    @Override
    public ItemResponse updateDescription(
            Integer id,
            UpdateItemDescriptionRequest request) {

        Item item = findItem(id);

        item.setDescription(request.itemDesc());

        Item updated = itemRepository.save(item);

        return toResponse(updated);
    }

    private Item findItem(Integer id) {

        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item not found with id: " + id));
    }

    private void validateReferenceUniqueness(
            String reference) {

        if (itemRepository.existsByReference(reference)) {
            throw new DuplicateResourceException(
                    "Item reference already exists: "
                            + reference);
        }
    }

    private void validateImageUrlUniqueness(
            String imageUrl) {

        if (imageUrl != null
                && itemRepository.existsByImageUrl(imageUrl)) {
            throw new DuplicateResourceException(
                    "Item image URL already exists");
        }
    }

    private ItemResponse toResponse(Item item) {

        return new ItemResponse(
                item.getId(),
                item.getImageUrl(),
                item.getReference(),
                item.getValue(),
                item.getName(),
                item.getDescription());
    }
}