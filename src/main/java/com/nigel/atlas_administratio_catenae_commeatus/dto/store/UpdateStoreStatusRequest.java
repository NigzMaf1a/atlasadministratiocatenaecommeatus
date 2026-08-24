package com.nigel.atlas_administratio_catenae_commeatus.dto.store;

import com.nigel.atlas_administratio_catenae_commeatus.entity.DesignationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStoreStatusRequest(

        @NotNull(message = "Store status is required") DesignationStatus storeStatus

) {
}