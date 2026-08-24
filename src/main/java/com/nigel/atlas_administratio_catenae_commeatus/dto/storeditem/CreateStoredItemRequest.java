package com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateStoredItemRequest(

        @NotNull(message = "Store ID is required") Integer storeId,

        @NotNull(message = "Item ID is required") Integer itemId,

        @NotNull(message = "Item quantity is required") @DecimalMin(value = "0.000", message = "Item quantity cannot be negative") BigDecimal itemQty

) {
}