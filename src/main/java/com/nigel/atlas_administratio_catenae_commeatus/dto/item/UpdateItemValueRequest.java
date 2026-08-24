package com.nigel.atlas_administratio_catenae_commeatus.dto.item;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateItemValueRequest(

        @NotNull(message = "Item value is required") @DecimalMin(value = "0.00", message = "Item value cannot be negative") BigDecimal itemValue

) {
}