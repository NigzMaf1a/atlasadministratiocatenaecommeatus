package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.CreateStoreWorthRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.StoreWorthResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.UpdateStoreWorthRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Store;
import com.nigel.atlas_administratio_catenae_commeatus.entity.StoreWorth;
import com.nigel.atlas_administratio_catenae_commeatus.exception.DuplicateResourceException;
import com.nigel.atlas_administratio_catenae_commeatus.exception.ResourceNotFoundException;
import com.nigel.atlas_administratio_catenae_commeatus.repository.StoreRepository;
import com.nigel.atlas_administratio_catenae_commeatus.repository.StoreWorthRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StoreWorthServiceImpl implements StoreWorthService {

    private final StoreWorthRepository storeWorthRepository;
    private final StoreRepository storeRepository;

    public StoreWorthServiceImpl(
            StoreWorthRepository storeWorthRepository,
            StoreRepository storeRepository) {
        this.storeWorthRepository = storeWorthRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreWorthResponse create(
            CreateStoreWorthRequest request) {

        if (storeWorthRepository.existsById(request.storeId())) {
            throw new DuplicateResourceException(
                    "Store worth already exists for store id: "
                            + request.storeId());
        }

        Store store = findStore(request.storeId());

        StoreWorth storeWorth = new StoreWorth();

        storeWorth.setStoreId(store.getId());
        storeWorth.setWorth(request.storeWorth());

        /*
         * PostgreSQL has DEFAULT CURRENT_DATE.
         * Setting it explicitly here gives the entity a
         * complete in-memory state before persistence.
         */
        storeWorth.setTimestamp(LocalDate.now());

        StoreWorth saved = storeWorthRepository.save(storeWorth);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreWorthResponse> findAll() {

        return storeWorthRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StoreWorthResponse findByStoreId(
            Integer storeId) {

        StoreWorth storeWorth = storeWorthRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store worth not found for store id: "
                                + storeId));

        return toResponse(storeWorth);
    }

    @Override
    public StoreWorthResponse updateWorth(
            Integer storeId,
            UpdateStoreWorthRequest request) {

        StoreWorth storeWorth = storeWorthRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store worth not found for store id: "
                                + storeId));

        storeWorth.setWorth(request.storeWorth());

        StoreWorth updated = storeWorthRepository.save(storeWorth);

        return toResponse(updated);
    }

    private Store findStore(Integer storeId) {

        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store not found with id: " + storeId));
    }

    private StoreWorthResponse toResponse(
            StoreWorth storeWorth) {

        return new StoreWorthResponse(
                storeWorth.getStoreId(),
                storeWorth.getWorth(),
                storeWorth.getTimestamp());
    }
}