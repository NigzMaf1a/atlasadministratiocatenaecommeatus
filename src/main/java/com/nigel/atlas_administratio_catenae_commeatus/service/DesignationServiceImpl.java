package com.nigel.atlas_administratio_catenae_commeatus.service;

import com.nigel.atlas_administratio_catenae_commeatus.dto.designations.*;
import com.nigel.atlas_administratio_catenae_commeatus.entity.Designation;
import com.nigel.atlas_administratio_catenae_commeatus.entity.DesignationStatus;
import com.nigel.atlas_administratio_catenae_commeatus.exception.DuplicateResourceException;
import com.nigel.atlas_administratio_catenae_commeatus.exception.ResourceNotFoundException;
import com.nigel.atlas_administratio_catenae_commeatus.repository.DesignationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DesignationServiceImpl implements DesignationService {

    private final DesignationRepository repository;

    public DesignationServiceImpl(
            DesignationRepository repository) {
        this.repository = repository;
    }

    @Override
    public DesignationResponse create(
            CreateDesignationRequest request) {

        if (repository.existsByReference(request.desRef())) {
            throw new DuplicateResourceException(
                    "Designation reference already exists: "
                            + request.desRef());
        }

        Designation designation = new Designation();

        designation.setReference(request.desRef());
        designation.setDescription(request.desDesc());
        designation.setStatus(
                request.desStatus() != null
                        ? request.desStatus()
                        : DesignationStatus.Active);

        Designation saved = repository.save(designation);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DesignationResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public DesignationResponse update(
            Integer id,
            UpdateDesignationRequest request) {

        Designation designation = findDesignation(id);

        if (repository.existsByReferenceAndIdNot(
                request.desRef(),
                id)) {
            throw new DuplicateResourceException(
                    "Designation reference already exists: "
                            + request.desRef());
        }

        designation.setReference(request.desRef());
        designation.setDescription(request.desDesc());
        designation.setStatus(request.desStatus());

        Designation updated = repository.save(designation);

        return toResponse(updated);
    }

    @Override
    public DesignationResponse updateStatus(
            Integer id,
            UpdateDesignationStatusRequest request) {

        Designation designation = findDesignation(id);

        designation.setStatus(request.desStatus());

        Designation updated = repository.save(designation);

        return toResponse(updated);
    }

    private Designation findDesignation(Integer id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Designation not found with id: " + id));
    }

    private DesignationResponse toResponse(
            Designation designation) {

        return new DesignationResponse(
                designation.getId(),
                designation.getReference(),
                designation.getDescription(),
                designation.getStatus());
    }
}
