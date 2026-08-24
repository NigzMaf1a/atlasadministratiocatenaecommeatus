package com.nigel.atlas_administratio_catenae_commeatus.repository;

import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequest;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestStatus;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RestockRequestRepository
                extends JpaRepository<RestockRequest, Integer> {

        List<RestockRequest> findAllByStore_Id(
                        Integer storeId);

        List<RestockRequest> findAllByType(
                        RestockRequestType type);

        List<RestockRequest> findAllByStatus(
                        RestockRequestStatus status);

        List<RestockRequest> findAllByDate(
                        LocalDate date);
}