package com.nigel.atlas_administratio_catenae_commeatus.dto.restockrequestitem;

import com.nigel.atlas_administratio_catenae_commeatus.entity.RestockRequestItemStatus;

import java.math.BigDecimal;

public record RestockRequestItemResponse(

        Integer resReqItemId,

        Integer resReqId,

        Integer itemId,

        BigDecimal reqQty,

        BigDecimal reqTotal,

        RestockRequestItemStatus resReqItemStatus

) {
}