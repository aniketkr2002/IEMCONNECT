package com.iemconnect.iemconnect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    @NotBlank(message = "username is required")
    @NotNull
    private String userName;

    @ManyToOne
    private StudentEntity student;

    private String contactNo;

    @NotBlank
    @NotNull(message = "product name is required")
    private String productName;

    @NotBlank
    @NotNull(message = "product description name is required")
    private String description;

    @NotBlank
    @NotNull(message = "price of product is required")
    private String price;

     @Lob
      private byte[] images;
    //  private String images;

    // No-argument constructor
    public Product() {
    }

    // Constructor with arguments
    public Product(String userName, String contactNo, String productName, String description, String price,byte[] images) {
        this.userName = userName;
        this.contactNo = contactNo;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.images=images;
    }

    // Getters and setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

//	public String getImages() {
//		return images;
//	}
//
//	public void setImages(String images) {
//		this.images = images;
//	}
}
