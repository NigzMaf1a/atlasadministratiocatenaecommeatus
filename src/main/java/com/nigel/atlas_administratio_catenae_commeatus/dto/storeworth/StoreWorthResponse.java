package com.nigel.atlas_administratio_catenae_commeatus.dto.storeworth;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StoreWorthResponse(

        Integer storeId,

        BigDecimal storeWorth,

        LocalDate timestamp

) {
}