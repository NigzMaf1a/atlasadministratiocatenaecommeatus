package com.nigel.atlas_administratio_catenae_commeatus.repository;

import com.nigel.atlas_administratio_catenae_commeatus.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository
        extends JpaRepository<Store, Integer> {

    boolean existsByReference(String reference);

    boolean existsByReferenceAndIdNot(
            String reference,
            Integer id);
}