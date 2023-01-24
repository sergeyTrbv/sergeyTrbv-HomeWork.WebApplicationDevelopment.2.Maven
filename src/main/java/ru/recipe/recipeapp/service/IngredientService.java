package ru.recipe.recipeapp.service;

import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;

import java.util.Collection;

public interface IngredientService {


    void addIngredient(Ingredient ingredient);

    Ingredient getIngredientById(int id);

    Collection<Recipe> getAll();
}
