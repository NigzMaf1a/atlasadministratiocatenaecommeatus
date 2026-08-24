package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.store.CreateStoreRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.StoreResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.UpdateStoreRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.UpdateStoreStatusRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Designation;
import com.nigel.atlas_administratio_catenae_commeatus.entity.DesignationStatus;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Store;
import com.nigel.atlas_administratio_catenae_commeatus.exception.DuplicateResourceException;
import com.nigel.atlas_administratio_catenae_commeatus.exception.ResourceNotFoundException;
import com.nigel.atlas_administratio_catenae_commeatus.repository.DesignationRepository;
import com.nigel.atlas_administratio_catenae_commeatus.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final DesignationRepository designationRepository;

    public StoreServiceImpl(
            StoreRepository storeRepository,
            DesignationRepository designationRepository) {
        this.storeRepository = storeRepository;
        this.designationRepository = designationRepository;
    }

    @Override
    public StoreResponse create(CreateStoreRequest request) {

        if (storeRepository.existsByReference(request.storeRef())) {
            throw new DuplicateResourceException(
                    "Store reference already exists: "
                            + request.storeRef());
        }

        Designation designation = findDesignation(request.desId());

        Store store = new Store();

        store.setReference(request.storeRef());
        store.setLocation(request.storeLocation());

        store.setStatus(
                request.storeStatus() != null
                        ? request.storeStatus()
                        : DesignationStatus.Active);

        store.setDesignation(designation);

        Store saved = storeRepository.save(store);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreResponse> findAll() {

        return storeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public StoreResponse update(
            Integer id,
            UpdateStoreRequest request) {

        Store store = findStore(id);

        if (storeRepository.existsByReferenceAndIdNot(
                request.storeRef(),
                id)) {
            throw new DuplicateResourceException(
                    "Store reference already exists: "
                            + request.storeRef());
        }

        Designation designation = findDesignation(request.desId());

        store.setReference(request.storeRef());
        store.setLocation(request.storeLocation());
        store.setStatus(request.storeStatus());
        store.setDesignation(designation);

        Store updated = storeRepository.save(store);

        return toResponse(updated);
    }

    @Override
    public StoreResponse updateStatus(
            Integer id,
            UpdateStoreStatusRequest request) {

        Store store = findStore(id);

        store.setStatus(request.storeStatus());

        Store updated = storeRepository.save(store);

        return toResponse(updated);
    }

    private Store findStore(Integer id) {

        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store not found with id: " + id));
    }

    private Designation findDesignation(Integer id) {

        return designationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Designation not found with id: " + id));
    }

    private StoreResponse toResponse(Store store) {

        return new StoreResponse(
                store.getId(),
                store.getReference(),
                store.getLocation(),
                store.getStatus(),
                store.getDesignation().getId());
    }
}