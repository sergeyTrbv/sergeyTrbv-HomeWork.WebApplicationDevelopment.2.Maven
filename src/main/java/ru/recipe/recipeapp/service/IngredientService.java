package ru.recipe.recipeapp.service;

import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;

import java.util.Collection;
import java.util.Map;

public interface IngredientService {


    void addIngredient(Ingredient ingredient);

    Map<Integer, Ingredient> getAll();

    Ingredient getIngredientById(int id);

    Ingredient editIngredient(Integer id, Ingredient ingredient);

    boolean deleteIngredientById(Integer id);

    Ingredient deleteAllIngredient();
}
