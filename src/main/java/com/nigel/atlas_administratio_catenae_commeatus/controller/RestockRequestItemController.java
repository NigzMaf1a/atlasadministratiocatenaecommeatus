package com.nigel.atlas_administratio_catenae_commeatus.controller;

import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.CreateRestockRequestItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.RestockRequestItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemQuantityRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemStatusRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem.UpdateRestockRequestItemTotalRequest;
import com.nigel.atlas_administratio_catenae_commeatus.service.RestockRequestItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restock-request-items")
public class RestockRequestItemController {

    private final RestockRequestItemService service;

    public RestockRequestItemController(
            RestockRequestItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RestockRequestItemResponse> create(
            @Valid @RequestBody CreateRestockRequestItemRequest request) {

        RestockRequestItemResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<RestockRequestItemResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @GetMapping("/request/{resReqId}")
    public ResponseEntity<List<RestockRequestItemResponse>> findByRequestId(
            @PathVariable Integer resReqId) {

        return ResponseEntity.ok(
                service.findByRequestId(resReqId));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<RestockRequestItemResponse>> findByItemId(
            @PathVariable Integer itemId) {

        return ResponseEntity.ok(
                service.findByItemId(itemId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestockRequestItemResponse> update(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateRestockRequestItemRequest request) {

        return ResponseEntity.ok(
                service.update(id, request));
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<RestockRequestItemResponse> updateQuantity(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateRestockRequestItemQuantityRequest request) {

        return ResponseEntity.ok(
                service.updateQuantity(id, request));
    }

    @PatchMapping("/{id}/total")
    public ResponseEntity<RestockRequestItemResponse> updateTotal(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateRestockRequestItemTotalRequest request) {

        return ResponseEntity.ok(
                service.updateTotal(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RestockRequestItemResponse> updateStatus(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateRestockRequestItemStatusRequest request) {

        return ResponseEntity.ok(
                service.updateStatus(id, request));
    }
}