package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.item.CreateItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.ItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemDescriptionRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemNameRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemValueRequest;

import java.util.List;

public interface ItemService {

    ItemResponse create(CreateItemRequest request);

    List<ItemResponse> findAll();

    ItemResponse update(
            Integer id,
            UpdateItemRequest request);

    ItemResponse updateValue(
            Integer id,
            UpdateItemValueRequest request);

    ItemResponse updateName(
            Integer id,
            UpdateItemNameRequest request);

    ItemResponse updateDescription(
            Integer id,
            UpdateItemDescriptionRequest request);
}