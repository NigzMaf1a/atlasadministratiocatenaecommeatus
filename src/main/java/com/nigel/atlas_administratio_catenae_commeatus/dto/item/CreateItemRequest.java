package com.nigel.atlas_administratio_catenae_commeatus.dto.item;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateItemRequest(

        @Size(max = 2048, message = "Image URL cannot exceed 2048 characters") String itemImgUrl,

        @NotBlank(message = "Item reference is required") @Size(max = 100, message = "Item reference cannot exceed 100 characters") String itemRef,

        @NotNull(message = "Item value is required") @DecimalMin(value = "0.00", message = "Item value cannot be negative") BigDecimal itemValue,

        @NotBlank(message = "Item name is required") @Size(max = 255, message = "Item name cannot exceed 255 characters") String itemName,

        String itemDesc

) {
}