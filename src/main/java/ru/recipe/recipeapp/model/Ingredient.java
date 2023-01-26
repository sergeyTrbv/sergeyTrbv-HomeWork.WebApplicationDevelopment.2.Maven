package ru.recipe.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {


    private String nameIngredient;
    private int numberOfIngredient;     //Кол-во ингредиентов
    private String unitOfMeasurement;    //Единица измерения


    //Методы
    @Override
    public String toString() {
        return nameIngredient + numberOfIngredient + unitOfMeasurement;
    }

    //Геттеры и Сеттеры
    public String getNameIngredient() {
        return nameIngredient;
    }

    public int getNumberOfIngredient() {
        return numberOfIngredient;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }


}
