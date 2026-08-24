package com.nigel.atlas_administratio_catenae_commeatus.repository;

import com.nigel.atlas_administratio_catenae_commeatus.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignationRepository
        extends JpaRepository<Designation, Integer> {

    boolean existsByReference(String reference);

    Optional<Designation> findByReference(String reference);

    boolean existsByReferenceAndIdNot(
            String reference,
            Integer id);
}
