package com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateRestockRequestItemRequest(

        @NotNull(message = "Restock request ID is required") Integer resReqId,

        @NotNull(message = "Item ID is required") Integer itemId,

        @NotNull(message = "Requested quantity is required") @DecimalMin(value = "0.001", message = "Requested quantity must be greater than zero") BigDecimal reqQty,

        @DecimalMin(value = "0.00", message = "Request total cannot be negative") BigDecimal reqTotal

) {
}