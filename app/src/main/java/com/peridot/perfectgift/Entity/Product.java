package com.peridot.perfectgift.Entity;

import android.content.Intent;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Product extends RealmObject {
    @PrimaryKey
    @Required
    private Long id;

    @Required
    private String name;

    @Required
    private String shortDescription;

    private String longDescription;

    @Required
    private Double price;

    @Required
    private String contactNumber;

    @Required
    private String contactEmail;

    private String genderSpecific;

    private Integer minAge;

    private Integer maxAge;

    private String image;

    private RealmList<Category> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getGenderSpecific() {
        return genderSpecific;
    }

    public void setGenderSpecific(String genderSpecific) {
        this.genderSpecific = genderSpecific;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RealmList<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", price=" + price +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", genderSpecific='" + genderSpecific + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", image='" + image + '\'' +
                ", categories=" + categories +
                '}';
    }
}
