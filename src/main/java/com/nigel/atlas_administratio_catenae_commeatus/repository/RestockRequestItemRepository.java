package com.nigel.atlas_administratio_catenae_commeatus.repository;

import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestockRequestItemRepository
        extends JpaRepository<RestockRequestItem, Integer> {

    List<RestockRequestItem> findAllByRestockRequest_Id(
            Integer resReqId);

    List<RestockRequestItem> findAllByItem_Id(
            Integer itemId);
}