package ru.recipe.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {


    private String name;
    private int cookingTime;
    private ArrayList<Ingredient> ingredient = new ArrayList<>();
    private ArrayList<Cooking> cookingStep = new ArrayList<>();


    //Методы
    @Override
    public String toString() {
        return "Рецепт: " + name +
                ".\n Время готовки: " + cookingTime + " минут" +
                ".\n Ингридиенты: " + ingredient +
                ".\n Шаги приготовления: " + cookingStep;
    }
}

