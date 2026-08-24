package com.nigel.atlas_administratio_catenae_commeatus.controller;

import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.CreateRestockRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.RestockRequestResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.UpdateRestockRequestStatus;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest.UpdateRestockRequestTotal;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestStatus;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestType;
import com.nigel.atlas_administratio_catenae_commeatus.service.RestockRequestService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restock-requests")
public class RestockRequestController {

    private final RestockRequestService service;

    public RestockRequestController(
            RestockRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RestockRequestResponse> create(
            @Valid @RequestBody CreateRestockRequest request) {

        RestockRequestResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<RestockRequestResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<RestockRequestResponse>> findByStoreId(
            @PathVariable Integer storeId) {

        return ResponseEntity.ok(
                service.findByStoreId(storeId));
    }

    @GetMapping("/type/{reqType}")
    public ResponseEntity<List<RestockRequestResponse>> findByType(
            @PathVariable RestockRequestType reqType) {

        return ResponseEntity.ok(
                service.findByType(reqType));
    }

    @GetMapping("/status/{reqStatus}")
    public ResponseEntity<List<RestockRequestResponse>> findByStatus(
            @PathVariable RestockRequestStatus reqStatus) {

        return ResponseEntity.ok(
                service.findByStatus(reqStatus));
    }

    @GetMapping("/date/{reqDate}")
    public ResponseEntity<List<RestockRequestResponse>> findByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reqDate) {

        return ResponseEntity.ok(
                service.findByDate(reqDate));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RestockRequestResponse> updateStatus(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateRestockRequestStatus request) {

        return ResponseEntity.ok(
                service.updateStatus(id, request));
    }

    @PatchMapping("/{id}/total")
    public ResponseEntity<RestockRequestResponse> updateTotal(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateRestockRequestTotal request) {

        return ResponseEntity.ok(
                service.updateTotal(id, request));
    }
}