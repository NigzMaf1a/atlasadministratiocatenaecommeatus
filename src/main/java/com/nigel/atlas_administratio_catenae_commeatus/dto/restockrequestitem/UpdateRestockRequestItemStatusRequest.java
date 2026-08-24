package com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem;

import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestItemStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateRestockRequestItemStatusRequest(

        @NotNull(message = "Request item status is required") RestockRequestItemStatus resReqItemStatus

) {
}