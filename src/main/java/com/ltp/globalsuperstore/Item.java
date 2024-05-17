package com.ltp.globalsuperstore;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

//This is the Shopping Kart Items List
public class Item {
    //Field Validation
    @NotBlank(message = "Please select a category")
    private String category;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Min(value = 0, message = "Price cannot be negative")
    private Double price;
    @Min(value = 0, message = "Discount cannot be negative")
    private Double discount;
    @Past(message = "Date must be of the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    //This the the Unique ID for the Item being Constructed
    //Unvalidated, since itis not revealed in the Views
    private String id;

    //Publicizing of the Unique ID for the Item being Constructed
    public Item() {
        //The Unique ID is randomized
        this.id = UUID.randomUUID().toString();
    }

    //Getters, abd Setters methods...
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
