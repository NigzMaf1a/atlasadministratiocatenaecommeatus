package com.nigel.atlas_administratio_catenae_commeatus.dto.storeworthdaily;

import java.math.BigDecimal;
import java.time.LocalDate;

public record StoreWorthDailyResponse(

        Integer storeWorthDailyId,

        Integer storeId,

        BigDecimal storeWorth,

        LocalDate date

) {
}