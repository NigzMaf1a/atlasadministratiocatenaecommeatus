package com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequest;

import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestStatus;
import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RestockRequestResponse(

        Integer resReqId,

        Integer storeId,

        RestockRequestType reqType,

        RestockRequestStatus reqStatus,

        LocalDate reqDate,

        BigDecimal reqTotal

) {
}