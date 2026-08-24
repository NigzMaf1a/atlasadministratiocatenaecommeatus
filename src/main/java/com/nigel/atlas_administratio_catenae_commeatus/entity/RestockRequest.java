package com.nigel.atlas_administratio_catenae_commeatus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "restock_requests")
public class RestockRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_req_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "fk_restock_requests_store"))
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_type", nullable = false)
    private RestockRequestType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "req_status", nullable = false)
    private RestockRequestStatus status;

    @Column(name = "req_date", nullable = false)
    private LocalDate date;

    @Column(name = "req_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal total;

    public RestockRequest() {
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

    public RestockRequestType getType() {
        return type;
    }

    public void setType(RestockRequestType type) {
        this.type = type;
    }

    public RestockRequestStatus getStatus() {
        return status;
    }

    public void setStatus(RestockRequestStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}