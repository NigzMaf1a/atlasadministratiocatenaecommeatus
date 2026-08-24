package com.nigel.atlas_administratio_catenae_commeatus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.math.BigDecimal;

@Entity
@Table(name = "items", uniqueConstraints = {
        @UniqueConstraint(name = "uk_items_item_ref", columnNames = "item_ref"),
        @UniqueConstraint(name = "uk_items_item_img_url", columnNames = "item_img_url")
})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;

    @Column(name = "item_img_url", unique = true)
    private String imageUrl;

    @Column(name = "item_ref", nullable = false, length = 100)
    private String reference;

    @Column(name = "item_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal value;

    @Column(name = "item_name", nullable = false, length = 255)
    private String name;

    @Column(name = "item_desc")
    private String description;

    public Item() {
    }

    public Integer getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}