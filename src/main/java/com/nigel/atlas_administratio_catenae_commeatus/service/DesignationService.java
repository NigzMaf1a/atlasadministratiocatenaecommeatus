package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.designations.*;
import java.util.List;

public interface DesignationService {

    DesignationResponse create(
            CreateDesignationRequest request);

    List<DesignationResponse> findAll();

    DesignationResponse update(
            Integer id,
            UpdateDesignationRequest request);

    DesignationResponse updateStatus(
            Integer id,
            UpdateDesignationStatusRequest request);
}
