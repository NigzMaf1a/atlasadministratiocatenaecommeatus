package com.nigel.atlas_administratio_catenae_commeatus.controller;

import com.nigel.atlas_administratio_catenae_commeatus.dto.item.CreateItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.ItemResponse;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemDescriptionRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemNameRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemRequest;
import com.nigel.atlas_administratio_catenae_commeatus.dto.item.UpdateItemValueRequest;
import com.nigel.atlas_administratio_catenae_commeatus.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> create(
            @Valid @RequestBody CreateItemRequest request) {

        ItemResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> findAll() {

        return ResponseEntity.ok(
                service.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> update(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateItemRequest request) {

        return ResponseEntity.ok(
                service.update(id, request));
    }

    @PatchMapping("/{id}/value")
    public ResponseEntity<ItemResponse> updateValue(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateItemValueRequest request) {

        return ResponseEntity.ok(
                service.updateValue(id, request));
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<ItemResponse> updateName(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateItemNameRequest request) {

        return ResponseEntity.ok(
                service.updateName(id, request));
    }

    @PatchMapping("/{id}/description")
    public ResponseEntity<ItemResponse> updateDescription(
            @PathVariable Integer id,

            @Valid @RequestBody UpdateItemDescriptionRequest request) {

        return ResponseEntity.ok(
                service.updateDescription(id, request));
    }
}