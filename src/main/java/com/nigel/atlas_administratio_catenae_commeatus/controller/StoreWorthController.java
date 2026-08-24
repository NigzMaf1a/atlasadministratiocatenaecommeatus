package com.nigel.atlas_administratio_catenae_commeatus.controller;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.CreateStoreWorthRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.StoreWorthResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth.UpdateStoreWorthRequest;
import com.nigel.atlas_administratio_catenae_commeatus.service.StoreWorthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/store-worth")
public class StoreWorthController {

    private final StoreWorthService service;

    public StoreWorthController(
            StoreWorthService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StoreWorthResponse> create(
            @Valid @RequestBody CreateStoreWorthRequest request) {

        StoreWorthResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<StoreWorthResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreWorthResponse> findByStoreId(
            @PathVariable Integer storeId) {

        return ResponseEntity.ok(
                service.findByStoreId(storeId));
    }

    @PatchMapping("/{storeId}/worth")
    public ResponseEntity<StoreWorthResponse> updateWorth(
            @PathVariable Integer storeId,

            @Valid @RequestBody UpdateStoreWorthRequest request) {

        return ResponseEntity.ok(
                service.updateWorth(
                        storeId,
                        request));
    }
}