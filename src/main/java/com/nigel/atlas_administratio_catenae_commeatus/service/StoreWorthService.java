package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.CreateStoreWorthRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.StoreWorthResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.UpdateStoreWorthRequest;

import java.util.List;

public interface StoreWorthService {

    StoreWorthResponse create(
            CreateStoreWorthRequest request);

    List<StoreWorthResponse> findAll();

    StoreWorthResponse findByStoreId(
            Integer storeId);

    StoreWorthResponse updateWorth(
            Integer storeId,
            UpdateStoreWorthRequest request);
}