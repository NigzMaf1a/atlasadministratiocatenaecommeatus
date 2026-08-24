package com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateStoredItemQuantityRequest(

        @NotNull(message = "Item quantity is required") @DecimalMin(value = "0.000", message = "Item quantity cannot be negative") BigDecimal itemQty

) {
}