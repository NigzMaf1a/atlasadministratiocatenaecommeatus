package com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest;

import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateRestockRequestStatus(

        @NotNull(message = "Request status is required") RestockRequestStatus reqStatus

) {
}