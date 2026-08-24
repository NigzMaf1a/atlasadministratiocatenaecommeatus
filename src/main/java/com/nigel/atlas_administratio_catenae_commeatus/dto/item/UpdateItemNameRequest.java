package com.nigel.atlas_administratio_catenae_commeatus.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateItemNameRequest(

        @NotBlank(message = "Item name is required") @Size(max = 255, message = "Item name cannot exceed 255 characters") String itemName

) {
}