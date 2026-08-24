package com.nigel.atlas_administratio_catenae_commeatus.repository;

import com.nigel.atlas_administratio_catenae_commeatus.entity.StoreWorthDaily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StoreWorthDailyRepository
        extends JpaRepository<StoreWorthDaily, Integer> {

    List<StoreWorthDaily> findAllByStore_Id(
            Integer storeId);

    List<StoreWorthDaily> findAllByDate(
            LocalDate date);

    Optional<StoreWorthDaily> findByStore_IdAndDate(
            Integer storeId,
            LocalDate date);
}