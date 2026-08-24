package com.nigel.atlas_administratio_catenae_commeatus.controller;

import com.nigel.atlas_administratio_catenae_commeatus.dto.store.CreateStoreRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.StoreResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.UpdateStoreRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.store.UpdateStoreStatusRequest;
import com.nigel.atlas_administratio_catenae_commeatus.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StoreResponse> create(
            @Valid @RequestBody CreateStoreRequest request) {

        StoreResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponse> update(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateStoreRequest request) {

        return ResponseEntity.ok(
                service.update(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<StoreResponse> updateStatus(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateStoreStatusRequest request) {

        return ResponseEntity.ok(
                service.updateStatus(id, request));
    }
}