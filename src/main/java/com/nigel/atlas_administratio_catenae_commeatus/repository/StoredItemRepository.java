package com.nigel.atlas_administratio_catenae_commeatus.repository;

import com.nigel.atlas_administratio_catenae_commeatus.entity.StoredItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoredItemRepository
        extends JpaRepository<StoredItem, Integer> {

    boolean existsByStore_IdAndItem_Id(
            Integer storeId,
            Integer itemId);

    List<StoredItem> findAllByStore_Id(
            Integer storeId);

    List<StoredItem> findAllByItem_Id(
            Integer itemId);
}