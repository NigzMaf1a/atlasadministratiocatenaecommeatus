package com.nigel.atlas_administratio_catenae_commeatus.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "designations", uniqueConstraints = {
        @UniqueConstraint(name = "uk_designations_des_ref", columnNames = "des_ref")
})
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "des_id")
    private Integer id;

    @Column(name = "des_ref", nullable = false, length = 100)
    private String reference;

    @Column(name = "des_desc")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "des_status", nullable = false)
    private DesignationStatus status;

    public Designation() {
    }

    public Integer getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DesignationStatus getStatus() {
        return status;
    }

    public void setStatus(DesignationStatus status) {
        this.status = status;
    }
}