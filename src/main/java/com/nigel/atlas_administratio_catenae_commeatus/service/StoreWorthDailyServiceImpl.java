package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.CreateStoreWorthDailyRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.StoreWorthDailyResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.UpdateStoreWorthDailyRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Store;
import com.nigel.atlas_administratio_catenae_commeatus.entity.StoreWorthDaily;
import com.nigel.atlas_administratio_catenae_commeatus.exception.ResourceNotFoundException;
import com.nigel.atlas_administratio_catenae_commeatus.repository.StoreRepository;
import com.nigel.atlas_administratio_catenae_commeatus.repository.StoreWorthDailyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StoreWorthDailyServiceImpl
        implements StoreWorthDailyService {

    private final StoreWorthDailyRepository repository;
    private final StoreRepository storeRepository;

    public StoreWorthDailyServiceImpl(
            StoreWorthDailyRepository repository,
            StoreRepository storeRepository) {
        this.repository = repository;
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreWorthDailyResponse create(
            CreateStoreWorthDailyRequest request) {

        Store store = findStore(request.storeId());

        LocalDate date = request.date() != null
                ? request.date()
                : LocalDate.now();

        if (repository.findByStore_IdAndDate(
                request.storeId(),
                date).isPresent()) {
            throw new IllegalArgumentException(
                    "Store worth already exists for store "
                            + request.storeId()
                            + " on "
                            + date);
        }

        StoreWorthDaily entity = new StoreWorthDaily();

        entity.setStore(store);
        entity.setStoreWorth(request.storeWorth());
        entity.setDate(date);

        StoreWorthDaily saved = repository.save(entity);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreWorthDailyResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreWorthDailyResponse> findByStoreId(
            Integer storeId) {

        findStore(storeId);

        return repository
                .findAllByStore_Id(storeId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreWorthDailyResponse> findByDate(
            LocalDate date) {

        return repository
                .findAllByDate(date)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StoreWorthDailyResponse findByStoreAndDate(
            Integer storeId,
            LocalDate date) {

        findStore(storeId);

        StoreWorthDaily entity = repository
                .findByStore_IdAndDate(storeId, date)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store worth not found for store "
                                + storeId
                                + " on "
                                + date));

        return toResponse(entity);
    }

    @Override
    public StoreWorthDailyResponse updateWorth(
            Integer id,
            UpdateStoreWorthDailyRequest request) {

        StoreWorthDaily entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Daily store worth not found with id: "
                                + id));

        entity.setStoreWorth(
                request.storeWorth());

        StoreWorthDaily updated = repository.save(entity);

        return toResponse(updated);
    }

    private Store findStore(Integer id) {

        return storeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Store not found with id: " + id));
    }

    private StoreWorthDailyResponse toResponse(
            StoreWorthDaily entity) {

        return new StoreWorthDailyResponse(
                entity.getId(),
                entity.getStore().getId(),
                entity.getStoreWorth(),
                entity.getDate());
    }
}