package com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem;

import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestItemStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateRestockRequestItemRequest(

        @NotNull(message = "Restock request ID is required") Integer resReqId,

        @NotNull(message = "Item ID is required") Integer itemId,

        @NotNull(message = "Requested quantity is required") @DecimalMin(value = "0.001", message = "Requested quantity must be greater than zero") BigDecimal reqQty,

        @NotNull(message = "Request total is required") @DecimalMin(value = "0.00", message = "Request total cannot be negative") BigDecimal reqTotal,

        @NotNull(message = "Request item status is required") RestockRequestItemStatus resReqItemStatus

) {
}