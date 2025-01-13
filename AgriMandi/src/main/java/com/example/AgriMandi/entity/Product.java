package com.example.AgriMandi.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private double quantity;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // The farmer who listed the product

    @Column(nullable = true)  // Making nullable true for the following fields
    private String cropType;  // Type of the crop (e.g., fruit, vegetable, grain, etc.)

    @Column(nullable = true)
    private String soilType;  // Type of soil the crop was grown in

    @Column(nullable = true)
    private String wateringRequirements;  // The crop's watering requirements

    @Column(nullable = true)
    private String fertilizerUsed;  // Fertilizers used for growing the crop

    @Column(nullable = true)
    private String pestControlMethods;  // Pest control methods used for the crop

    @Column(nullable = true)
    private String organicCertification;  // Whether the crop is organically certified or not

    @Column(nullable = true)
    private String climateRequirements;  // The type of climate suitable for the crop

    @Column(nullable = true)
    private String growthCycle;  // Duration (e.g., 3 months, 6 months, etc.)

    @Column(nullable = true)
    private String packagingType;  // Type of packaging (e.g., loose, in bags)

    @Column(nullable = true)
    private String storageConditions;  // Storage conditions required for the crop

    @Column(nullable = true)
    private String shelfLife;  // Shelf life after harvesting

    @Column(nullable = true)
    private String sustainabilityPractices;  // Sustainable farming practices used

    @Column(nullable = true)
    private String productImage;  // Image URL or path for the product image

    @Column(nullable = true)
    private String farmerLocation;  // The location of the farmer (city, state, etc.)

    @Column(nullable = true)
    private LocalDate harvestDate;  // Date when the crop was harvested

    @Column(nullable = true)
    private LocalDate plantingDate;  // Date when the crop was planted

    @Column(nullable = false)
    private LocalDate listingDate;  // Date when the product was listed on the platform

    // Getters and Setters
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getListingDate() {
        return listingDate;
    }

    public void setListingDate(LocalDate listingDate) {
        this.listingDate = listingDate;
    }

    // Getters and setters for new properties
    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getWateringRequirements() {
        return wateringRequirements;
    }

    public void setWateringRequirements(String wateringRequirements) {
        this.wateringRequirements = wateringRequirements;
    }

    public String getFertilizerUsed() {
        return fertilizerUsed;
    }

    public void setFertilizerUsed(String fertilizerUsed) {
        this.fertilizerUsed = fertilizerUsed;
    }

    public String getPestControlMethods() {
        return pestControlMethods;
    }

    public void setPestControlMethods(String pestControlMethods) {
        this.pestControlMethods = pestControlMethods;
    }

    public String getOrganicCertification() {
        return organicCertification;
    }

    public void setOrganicCertification(String organicCertification) {
        this.organicCertification = organicCertification;
    }

    public String getClimateRequirements() {
        return climateRequirements;
    }

    public void setClimateRequirements(String climateRequirements) {
        this.climateRequirements = climateRequirements;
    }

    public String getGrowthCycle() {
        return growthCycle;
    }

    public void setGrowthCycle(String growthCycle) {
        this.growthCycle = growthCycle;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public String getStorageConditions() {
        return storageConditions;
    }

    public void setStorageConditions(String storageConditions) {
        this.storageConditions = storageConditions;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getSustainabilityPractices() {
        return sustainabilityPractices;
    }

    public void setSustainabilityPractices(String sustainabilityPractices) {
        this.sustainabilityPractices = sustainabilityPractices;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getFarmerLocation() {
        return farmerLocation;
    }

    public void setFarmerLocation(String farmerLocation) {
        this.farmerLocation = farmerLocation;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(LocalDate harvestDate) {
        this.harvestDate = harvestDate;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }
}
