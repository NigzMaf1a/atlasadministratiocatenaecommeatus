package com.nigel.atlas_administratio_catenae_commeatus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "store_worth")
public class StoreWorth {

    @Id
    @Column(name = "store_id")
    private Integer storeId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_store_worth_store"))
    private Store store;

    @Column(name = "store_worth", nullable = false, precision = 15, scale = 2)
    private BigDecimal worth;

    @Column(name = "timestamp", nullable = false)
    private LocalDate timestamp;

    public StoreWorth() {
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Store getStore() {
        return store;
    }

    public BigDecimal getWorth() {
        return worth;
    }

    public void setWorth(BigDecimal worth) {
        this.worth = worth;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}