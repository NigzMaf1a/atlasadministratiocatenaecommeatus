package com.nigel.atlas_administratio_catenae_commeatus.dto.store;

import com.nigel.atlas_administratio_catenae_commeatus.entity.DesignationStatus;

public record StoreResponse(
        Integer storeId,
        String storeRef,
        String storeLocation,
        DesignationStatus storeStatus,
        Integer desId) {
}