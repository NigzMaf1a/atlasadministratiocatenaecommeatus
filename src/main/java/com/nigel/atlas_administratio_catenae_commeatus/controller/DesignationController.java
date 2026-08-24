package com.nigel.atlas_administratio_catenae_commeatus.controller;

import com.nigel.atlas_administratio_catenae_commeatus.dto.designations.*;
import com.nigel.atlas_administratio_catenae_commeatus.service.DesignationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/designations")
public class DesignationController {

    private final DesignationService service;

    public DesignationController(
            DesignationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DesignationResponse> create(
            @Valid @RequestBody CreateDesignationRequest request) {

        DesignationResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<DesignationResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesignationResponse> update(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateDesignationRequest request) {

        return ResponseEntity.ok(
                service.update(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DesignationResponse> updateStatus(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateDesignationStatusRequest request) {

        return ResponseEntity.ok(
                service.updateStatus(id, request));
    }
}
