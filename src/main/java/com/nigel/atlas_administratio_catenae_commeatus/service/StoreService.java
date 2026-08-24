package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.store.CreateStoreRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.StoreResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.UpdateStoreRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.UpdateStoreStatusRequest;

import java.util.List;

public interface StoreService {

    StoreResponse create(CreateStoreRequest request);

    List<StoreResponse> findAll();

    StoreResponse update(
            Integer id,
            UpdateStoreRequest request);

    StoreResponse updateStatus(
            Integer id,
            UpdateStoreStatusRequest request);
}