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

@Entity
@Table(name = "restock_request_items")
public class RestockRequestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_req_item_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "res_req_id", nullable = false, foreignKey = @ForeignKey(name = "fk_restock_request_items_request"))
    private RestockRequest restockRequest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false, foreignKey = @ForeignKey(name = "fk_restock_request_items_item"))
    private Item item;

    @Column(name = "req_qty", nullable = false, precision = 15, scale = 3)
    private BigDecimal quantity;

    @Column(name = "req_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "res_req_item_status", nullable = false)
    private RestockRequestItemStatus status;

    public RestockRequestItem() {
    }

    public Integer getId() {
        return id;
    }

    public RestockRequest getRestockRequest() {
        return restockRequest;
    }

    public void setRestockRequest(
            RestockRequest restockRequest) {
        this.restockRequest = restockRequest;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public RestockRequestItemStatus getStatus() {
        return status;
    }

    public void setStatus(
            RestockRequestItemStatus status) {
        this.status = status;
    }
}