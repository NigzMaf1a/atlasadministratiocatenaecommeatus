package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.CreateStoredItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.StoredItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.UpdateStoredItemQuantityRequest;

import java.util.List;

public interface StoredItemService {

    StoredItemResponse create(
            CreateStoredItemRequest request);

    List<StoredItemResponse> findAll();

    List<StoredItemResponse> findByStoreId(
            Integer storeId);

    List<StoredItemResponse> findByItemId(
            Integer itemId);

    StoredItemResponse updateQuantity(
            Integer id,
            UpdateStoredItemQuantityRequest request);
}