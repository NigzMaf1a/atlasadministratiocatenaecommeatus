package com.nigel.atlas_administratio_catenae_commeatus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "store_worth_daily")
public class StoreWorthDaily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_worth_daily_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "fk_store_worth_daily_store"))
    private Store store;

    @Column(name = "store_worth", nullable = false, precision = 15, scale = 2)
    private BigDecimal storeWorth;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public StoreWorthDaily() {
    }

    public Integer getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public BigDecimal getStoreWorth() {
        return storeWorth;
    }

    public void setStoreWorth(BigDecimal storeWorth) {
        this.storeWorth = storeWorth;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}