package ru.recipe.recipeapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {


    @Schema(description = "Название",example = "Бутерброд")
    private String name;
    @Schema(description = "Время приготовления",example = "1")
    private int cookingTime;
    @Schema(description = "Ингридиенты")
    private ArrayList<Ingredient> ingredient = new ArrayList<>();
    @Schema(description = "Шаги приготовления")
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

