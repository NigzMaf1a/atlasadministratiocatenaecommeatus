package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.CreateRestockRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.RestockRequestResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.UpdateRestockRequestStatus;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.UpdateRestockRequestTotal;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestStatus;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestType;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Store;
import com.nigel.atlas_administratio_catenae_commeatus.exception.ResourceNotFoundException;
import com.nigel.atlas_administratio_catenae_commeatus.repository.RestockRequestRepository;
import com.nigel.atlas_administratio_catenae_commeatus.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RestockRequestServiceImpl
        implements RestockRequestService {

    private final RestockRequestRepository restockRequestRepository;
    private final StoreRepository storeRepository;

    public RestockRequestServiceImpl(
            RestockRequestRepository restockRequestRepository,
            StoreRepository storeRepository) {
        this.restockRequestRepository = restockRequestRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public RestockRequestResponse create(
            CreateRestockRequest request) {

        Store store = findStore(request.storeId());

        RestockRequest restockRequest = new RestockRequest();

        restockRequest.setStore(store);

        restockRequest.setType(
                request.reqType());

        restockRequest.setStatus(
                RestockRequestStatus.Pending);

        restockRequest.setDate(
                LocalDate.now());

        restockRequest.setTotal(
                request.reqTotal() != null
                        ? request.reqTotal()
                        : BigDecimal.ZERO);

        RestockRequest saved = restockRequestRepository.save(
                restockRequest);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestockRequestResponse> findAll() {

        return restockRequestRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestockRequestResponse> findByStoreId(
            Integer storeId) {

        findStore(storeId);

        return restockRequestRepository
                .findAllByStore_Id(storeId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestockRequestResponse> findByType(
            RestockRequestType type) {

        return restockRequestRepository
                .findAllByType(type)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestockRequestResponse> findByStatus(
            RestockRequestStatus status) {

        return restockRequestRepository
                .findAllByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RestockRequestResponse> findByDate(
            LocalDate date) {

        return restockRequestRepository
                .findAllByDate(date)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public RestockRequestResponse updateStatus(
            Integer id,
            UpdateRestockRequestStatus request) {

        RestockRequest restockRequest = findRestockRequest(id);

        restockRequest.setStatus(
                request.reqStatus());

        RestockRequest updated = restockRequestRepository.save(
                restockRequest);

        return toResponse(updated);
    }

    @Override
    public RestockRequestResponse updateTotal(
            Integer id,
            UpdateRestockRequestTotal request) {

        RestockRequest restockRequest = findRestockRequest(id);

        restockRequest.setTotal(
                request.reqTotal());

        RestockRequest updated = restockRequestRepository.save(
                restockRequest);

        return toResponse(updated);
    }

    private RestockRequest findRestockRequest(
            Integer id) {

        return restockRequestRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Restock request not found with id: "
                                + id));
    }

    private Store findStore(Integer id) {

        return storeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store not found with id: " + id));
    }

    private RestockRequestResponse toResponse(
            RestockRequest request) {

        return new RestockRequestResponse(
                request.getId(),
                request.getStore().getId(),
                request.getType(),
                request.getStatus(),
                request.getDate(),
                request.getTotal());
    }
}