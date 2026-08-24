package com.nigel.atlas_administratio_catenae_commeatus.dto.designations;

import com.nigel.atlas_administratio_catenae_commeatus.entity.DesignationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateDesignationRequest(

        @NotBlank(message = "Designation reference is required") @Size(max = 100, message = "Designation reference cannot exceed 100 characters") String desRef,

        @Size(max = 1000, message = "Designation description cannot exceed 1000 characters") String desDesc,

        DesignationStatus desStatus) {
}
