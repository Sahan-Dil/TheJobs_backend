package com.SahanDilshan.TheJobs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "consultancy")
public class Consultancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consultancy_id")
    private Integer consultancyId;

    private String name;
    private String country;
    private String gender;
    private String description;
    private String phone;
    private String category;

    @Column(unique = true)
    private String email;
    private String availability;
    @Column(unique = true)
    private Integer userId;


    // Constructors, getters, and setters

    public Consultancy() {
    }

    public Consultancy(Integer consultancyId, String name, String country, String gender, String description, String phone, String category, String email, String availability, Integer userId) {
        this.consultancyId = consultancyId;
        this.name = name;
        this.country = country;
        this.gender = gender;
        this.description = description;
        this.phone = phone;
        this.category = category;
        this.email = email;
        this.availability = availability;
        this.userId = userId;
    }

    public Integer getConsultancyId() {
        return consultancyId;
    }

    public void setConsultancyId(Integer consultancyId) {
        this.consultancyId = consultancyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
