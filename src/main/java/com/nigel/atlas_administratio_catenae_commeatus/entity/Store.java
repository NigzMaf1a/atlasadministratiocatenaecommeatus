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
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "stores", uniqueConstraints = {
        @UniqueConstraint(name = "uk_stores_store_ref", columnNames = "store_ref")
})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer id;

    @Column(name = "store_ref", nullable = false, length = 100)
    private String reference;

    @Column(name = "store_location", nullable = false)
    private String location;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "store_status", nullable = false)
    private DesignationStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "des_id", nullable = false, foreignKey = @ForeignKey(name = "fk_stores_designation"))
    private Designation designation;

    public Store() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DesignationStatus getStatus() {
        return status;
    }

    public void setStatus(DesignationStatus status) {
        this.status = status;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }
}
