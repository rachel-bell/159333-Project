package com.example.petproject.ui;

public class PetFood {
    private String foodName;
    private String foodType;
    private double protein;
    private double fat;
    private double ash;
    private double fibre;

    public PetFood(String foodName, String foodType, double protein, double fat, double ash, double fibre) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.protein = protein;
        this.fat = fat;
        this.ash = ash;
        this.fibre = fibre;
    }


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getAsh() {
        return ash;
    }

    public void setAsh(double ash) {
        this.ash = ash;
    }

    public double getFibre() {
        return fibre;
    }

    public void setFibre(double fibre) {
        this.fibre = fibre;
    }
}
