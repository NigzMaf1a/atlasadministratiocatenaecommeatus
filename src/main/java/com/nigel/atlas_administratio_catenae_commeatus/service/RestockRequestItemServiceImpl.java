package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.CreateRestockRequestItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.RestockRequestItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemQuantityRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemStatusRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemTotalRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Item;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestItem;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestItemStatus;
import com.nigel.atlas_administratio_catenae_commeatus.exception.ResourceNotFoundException;
import com.nigel.atlas_administratio_catenae_commeatus.repository.ItemRepository;
import com.nigel.atlas_administratio_catenae_commeatus.repository.RestockRequestItemRepository;
import com.nigel.atlas_administratio_catenae_commeatus.repository.RestockRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class RestockRequestItemServiceImpl
        implements RestockRequestItemService {

    private final RestockRequestItemRepository repository;
    private final RestockRequestRepository restockRequestRepository;
    private final ItemRepository itemRepository;

    public RestockRequestItemServiceImpl(
            RestockRequestItemRepository repository,
            RestockRequestRepository restockRequestRepository,
            ItemRepository itemRepository) {
        this.repository = repository;
        this.restockRequestRepository = restockRequestRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public RestockRequestItemResponse create(
            CreateRestockRequestItemRequest request) {

        RestockRequest restockRequest = findRestockRequest(request.resReqId());

        Item item = findItem(request.itemId());

        RestockRequestItem entity = new RestockRequestItem();

        entity.setRestockRequest(restockRequest);
        entity.setItem(item);
        entity.setQuantity(request.reqQty());

        entity.setTotal(
                request.reqTotal() != null
                        ? request.reqTotal()
                        : BigDecimal.ZERO);

        entity.setStatus(
                RestockRequestItemStatus.Pending);

        RestockRequestItem saved = repository.save(entity);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestockRequestItemResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestockRequestItemResponse> findByRequestId(
            Integer resReqId) {

        findRestockRequest(resReqId);

        return repository
                .findAllByRestockRequest_Id(resReqId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestockRequestItemResponse> findByItemId(
            Integer itemId) {

        findItem(itemId);

        return repository
                .findAllByItem_Id(itemId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public RestockRequestItemResponse update(
            Integer id,
            UpdateRestockRequestItemRequest request) {

        RestockRequestItem entity = findRestockRequestItem(id);

        RestockRequest restockRequest = findRestockRequest(request.resReqId());

        Item item = findItem(request.itemId());

        entity.setRestockRequest(restockRequest);
        entity.setItem(item);
        entity.setQuantity(request.reqQty());
        entity.setTotal(request.reqTotal());
        entity.setStatus(request.resReqItemStatus());

        RestockRequestItem updated = repository.save(entity);

        return toResponse(updated);
    }

    @Override
    public RestockRequestItemResponse updateQuantity(
            Integer id,
            UpdateRestockRequestItemQuantityRequest request) {

        RestockRequestItem entity = findRestockRequestItem(id);

        entity.setQuantity(request.reqQty());

        RestockRequestItem updated = repository.save(entity);

        return toResponse(updated);
    }

    @Override
    public RestockRequestItemResponse updateTotal(
            Integer id,
            UpdateRestockRequestItemTotalRequest request) {

        RestockRequestItem entity = findRestockRequestItem(id);

        entity.setTotal(request.reqTotal());

        RestockRequestItem updated = repository.save(entity);

        return toResponse(updated);
    }

    @Override
    public RestockRequestItemResponse updateStatus(
            Integer id,
            UpdateRestockRequestItemStatusRequest request) {

        RestockRequestItem entity = findRestockRequestItem(id);

        entity.setStatus(
                request.resReqItemStatus());

        RestockRequestItem updated = repository.save(entity);

        return toResponse(updated);
    }

    private RestockRequestItem findRestockRequestItem(
            Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Restock request item not found with id: "
                                + id));
    }

    private RestockRequest findRestockRequest(
            Integer id) {

        return restockRequestRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Restock request not found with id: "
                                + id));
    }

    private Item findItem(Integer id) {

        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Item not found with id: " + id));
    }

    private RestockRequestItemResponse toResponse(
            RestockRequestItem entity) {

        return new RestockRequestItemResponse(
                entity.getId(),
                entity.getRestockRequest().getId(),
                entity.getItem().getId(),
                entity.getQuantity(),
                entity.getTotal(),
                entity.getStatus());
    }
}