package com.arqui.ufps.freelancer.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the service_offers database table.
 */
@Entity
@Table(name = "service_offers")
@NamedQuery(name = "ServiceOffer.findAll", query = "SELECT s FROM ServiceOffer s")
public class ServiceOffer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double price;

    private String state;

    @Lob
    @Column(name = "terms_and_conditions")
    private String termsAndConditions;

    @Lob
    @Column(name = "terms_service")
    private String termsService;

    private String title;

    //bi-directional many-to-one association to User
    @ManyToOne
    @JsonBackReference(value = "offerUser")
    @JoinColumn(name = "user_id")
    private User user;

    //bi-directional many-to-one association to Category
    @JsonManagedReference
    @ManyToOne
    private Category category;

    public ServiceOffer() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTermsAndConditions() {
        return this.termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getTermsService() {
        return this.termsService;
    }

    public void setTermsService(String termsService) {
        this.termsService = termsService;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}