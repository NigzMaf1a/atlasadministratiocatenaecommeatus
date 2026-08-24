package com.nigel.atlas_administratio_catenae_commeatus.dto.designations;

import com.nigel.atlas_administratio_catenae_commeatus.entity.DesignationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateDesignationStatusRequest(

        @NotNull(message = "Designation status is required") DesignationStatus desStatus) {
}
