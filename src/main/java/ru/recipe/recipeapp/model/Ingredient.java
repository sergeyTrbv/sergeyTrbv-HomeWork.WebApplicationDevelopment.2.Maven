package ru.recipe.recipeapp.model;

import lombok.Data;

@Data
public class Ingredient {


    private final String nameIngredient;
    private final int  numberOfIngredient;     //Кол-во ингредиентов
    private final String UnitOfMeasurement;    //Единица измерения


    public Ingredient(String nameIngredient, int numberOfIngredient, String unitOfMeasurement) {
        this.nameIngredient = nameIngredient;
        this.numberOfIngredient = numberOfIngredient;
        UnitOfMeasurement = unitOfMeasurement;
    }

    @Override
    public String toString() {
        return nameIngredient + numberOfIngredient + UnitOfMeasurement;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public int getNumberOfIngredient() {
        return numberOfIngredient;
    }

    public String getUnitOfMeasurement() {
        return UnitOfMeasurement;
    }


}
