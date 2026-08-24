package com.nigel.atlas_administratio_catenae_commeatus.controller;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.CreateStoreWorthDailyRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.StoreWorthDailyResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily.UpdateStoreWorthDailyRequest;
import com.nigel.atlas_administratio_catenae_commeatus.service.StoreWorthDailyService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/store-worth-daily")
public class StoreWorthDailyController {

    private final StoreWorthDailyService service;

    public StoreWorthDailyController(
            StoreWorthDailyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StoreWorthDailyResponse> create(
            @Valid @RequestBody CreateStoreWorthDailyRequest request) {

        StoreWorthDailyResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<StoreWorthDailyResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<StoreWorthDailyResponse>> findByStoreId(
            @PathVariable Integer storeId) {

        return ResponseEntity.ok(
                service.findByStoreId(storeId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<StoreWorthDailyResponse>> findByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                service.findByDate(date));
    }

    @GetMapping("/store/{storeId}/date/{date}")
    public ResponseEntity<StoreWorthDailyResponse> findByStoreAndDate(
            @PathVariable Integer storeId,

            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(
                service.findByStoreAndDate(
                        storeId,
                        date));
    }

    @PatchMapping("/{id}/worth")
    public ResponseEntity<StoreWorthDailyResponse> updateWorth(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateStoreWorthDailyRequest request) {

        return ResponseEntity.ok(
                service.updateWorth(id, request));
    }
}