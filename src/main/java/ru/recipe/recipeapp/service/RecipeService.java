package ru.recipe.recipeapp.service;

import ru.recipe.recipeapp.model.Recipe;

import java.util.Collection;
import java.util.Map;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    Map<Integer, Recipe> getAll();

    Recipe getRecipeById(int id);


    Recipe editRecipe(Integer id, Recipe recipe);

    boolean deleteRecipeById(Integer id);

    Recipe deleteAllRecipe();
}

