package com.nigel.atlas_administratio_catenae_commeatus.dto.store;

import com.nigel.atlas_administratio_catenae_commeatus.entity.DesignationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateStoreRequest(

        @NotBlank(message = "Store reference is required") @Size(max = 100, message = "Store reference cannot exceed 100 characters") String storeRef,

        @NotBlank(message = "Store location is required") String storeLocation,

        @NotNull(message = "Store status is required") DesignationStatus storeStatus,

        @NotNull(message = "Designation ID is required") Integer desId

) {
}