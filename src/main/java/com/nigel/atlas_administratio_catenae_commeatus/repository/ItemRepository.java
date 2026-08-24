package com.nigel.atlas_administratio_catenae_commeatus.repository;

import com.nigel.atlas_administratio_catenae_commeatus.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository
        extends JpaRepository<Item, Integer> {

    boolean existsByReference(String reference);

    boolean existsByReferenceAndIdNot(
            String reference,
            Integer id);

    boolean existsByImageUrl(String imageUrl);

    boolean existsByImageUrlAndIdNot(
            String imageUrl,
            Integer id);
}