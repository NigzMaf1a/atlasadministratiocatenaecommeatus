package com.nigel.atlas_administratio_catenae_commeatus.dto.storeditem;

import java.math.BigDecimal;

public record StoredItemResponse(

        Integer storedItemId,

        Integer storeId,

        Integer itemId,

        BigDecimal itemQty

) {
}