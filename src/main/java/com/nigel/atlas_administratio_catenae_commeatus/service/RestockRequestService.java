package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.CreateRestockRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.RestockRequestResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.UpdateRestockRequestStatus;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.UpdateRestockRequestTotal;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestStatus;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestType;

import java.time.LocalDate;
import java.util.List;

public interface RestockRequestService {

    RestockRequestResponse create(
            CreateRestockRequest request);

    List<RestockRequestResponse> findAll();

    List<RestockRequestResponse> findByStoreId(
            Integer storeId);

    List<RestockRequestResponse> findByType(
            RestockRequestType type);

    List<RestockRequestResponse> findByStatus(
            RestockRequestStatus status);

    List<RestockRequestResponse> findByDate(
            LocalDate date);

    RestockRequestResponse updateStatus(
            Integer id,
            UpdateRestockRequestStatus request);

    RestockRequestResponse updateTotal(
            Integer id,
            UpdateRestockRequestTotal request);
}