package com.example.bpn8adh.ordermanage.models;

/**
 * Created by bpn8adh on 27/02/18.
 */

public class FoodDetails {
    String foodName;
    String foodPrice;
    String foodPreparationTime;
    String foodImage;
    int foodQuantity;

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodPreparationTime() {
        return foodPreparationTime;
    }

    public void setFoodPreparationTime(String foodPreparationTime) {
        this.foodPreparationTime = foodPreparationTime;
    }

}
