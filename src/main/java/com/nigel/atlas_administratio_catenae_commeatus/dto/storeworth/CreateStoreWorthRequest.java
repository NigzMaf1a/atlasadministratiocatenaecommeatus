package com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateStoreWorthRequest(

        @NotNull(message = "Store ID is required") Integer storeId,

        @NotNull(message = "Store worth is required") @DecimalMin(value = "0.00", message = "Store worth cannot be negative") BigDecimal storeWorth

) {
}