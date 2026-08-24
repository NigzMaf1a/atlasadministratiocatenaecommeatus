package com.nigel.atlas_administratio_catenae_commeatus.dto.designations;

import com.nigel.atlas_administratio_catenae_commeatus.entity.DesignationStatus;

public record DesignationResponse(
        Integer desId,
        String desRef,
        String desDesc,
        DesignationStatus desStatus) {
}
