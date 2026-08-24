package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.CreateRestockRequestItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.RestockRequestItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemQuantityRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemStatusRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemTotalRequest;

import java.util.List;

public interface RestockRequestItemService {

    RestockRequestItemResponse create(
            CreateRestockRequestItemRequest request);

    List<RestockRequestItemResponse> findAll();

    List<RestockRequestItemResponse> findByRequestId(
            Integer resReqId);

    List<RestockRequestItemResponse> findByItemId(
            Integer itemId);

    RestockRequestItemResponse update(
            Integer id,
            UpdateRestockRequestItemRequest request);

    RestockRequestItemResponse updateQuantity(
            Integer id,
            UpdateRestockRequestItemQuantityRequest request);

    RestockRequestItemResponse updateTotal(
            Integer id,
            UpdateRestockRequestItemTotalRequest request);

    RestockRequestItemResponse updateStatus(
            Integer id,
            UpdateRestockRequestItemStatusRequest request);
}