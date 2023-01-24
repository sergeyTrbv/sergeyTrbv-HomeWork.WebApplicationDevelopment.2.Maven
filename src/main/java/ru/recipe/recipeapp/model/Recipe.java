package ru.recipe.recipeapp.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Recipe {


    private final String name;
    private final int cookingTime;
    ArrayList<Ingredient> ingredient = new ArrayList<>();
    private ArrayList<Cooking> cookingStep = new ArrayList<>();

    //Конструктор
    public Recipe(String name, int cookingTime, ArrayList<Ingredient> ingredient, ArrayList<Cooking> cookingStep) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredient = ingredient;
        this.cookingStep = cookingStep;
    }

    //Методы
    @Override
    public String toString() {
        return "Рецепт: " + name +
                ".\n Время готовки: " + cookingTime + " минут" +
                ".\n Ингридиенты: " + ingredient +
                ".\n Шаги приготовления: " + cookingStep;
    }

    //Геттеры и Сеттеры
    public String getRecipeName() {
        return name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Cooking> getCookingStep() {
        return cookingStep;
    }

    public void setCookingStep(ArrayList<Cooking> cookingStep) {
        this.cookingStep = cookingStep;
    }

    public ArrayList<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(ArrayList<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }
}
