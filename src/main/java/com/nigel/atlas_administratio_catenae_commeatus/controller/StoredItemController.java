package com.nigel.atlas_administratio_catenae_commeatus.controller;

import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.CreateStoredItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.StoredItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem.UpdateStoredItemQuantityRequest;
import com.nigel.atlas_administratio_catenae_commeatus.service.StoredItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stored-items")
public class StoredItemController {

    private final StoredItemService service;

    public StoredItemController(
            StoredItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StoredItemResponse> create(
            @Valid @RequestBody CreateStoredItemRequest request) {

        StoredItemResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<StoredItemResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<StoredItemResponse>> findByStoreId(
            @PathVariable Integer storeId) {

        return ResponseEntity.ok(
                service.findByStoreId(storeId));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<StoredItemResponse>> findByItemId(
            @PathVariable Integer itemId) {

        return ResponseEntity.ok(
                service.findByItemId(itemId));
    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<StoredItemResponse> updateQuantity(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateStoredItemQuantityRequest request) {

        return ResponseEntity.ok(
                service.updateQuantity(id, request));
    }
}