package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.CreateStoreWorthDailyRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.StoreWorthDailyResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.UpdateStoreWorthDailyRequest;

import java.time.LocalDate;
import java.util.List;

public interface StoreWorthDailyService {

    StoreWorthDailyResponse create(
            CreateStoreWorthDailyRequest request);

    List<StoreWorthDailyResponse> findAll();

    List<StoreWorthDailyResponse> findByStoreId(
            Integer storeId);

    List<StoreWorthDailyResponse> findByDate(
            LocalDate date);

    StoreWorthDailyResponse findByStoreAndDate(
            Integer storeId,
            LocalDate date);

    StoreWorthDailyResponse updateWorth(
            Integer id,
            UpdateStoreWorthDailyRequest request);
}