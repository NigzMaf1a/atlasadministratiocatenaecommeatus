package com.nigel.atlas_administratio_catenae_commeatus.dto.item;

import java.math.BigDecimal;

public record ItemResponse(
        Integer itemId,
        String itemImgUrl,
        String itemRef,
        BigDecimal itemValue,
        String itemName,
        String itemDesc) {
}