package com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest;

import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateRestockRequest(

                @NotNull(message = "Store ID is required") Integer storeId,

                @NotNull(message = "Request type is required") RestockRequestType reqType,

                @DecimalMin(value = "0.00", message = "Request total cannot be negative") BigDecimal reqTotal

) {
}