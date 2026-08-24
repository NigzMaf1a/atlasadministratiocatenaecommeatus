package com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateRestockRequestItemTotalRequest(

        @NotNull(message = "Request total is required") @DecimalMin(value = "0.00", message = "Request total cannot be negative") BigDecimal reqTotal

) {
}