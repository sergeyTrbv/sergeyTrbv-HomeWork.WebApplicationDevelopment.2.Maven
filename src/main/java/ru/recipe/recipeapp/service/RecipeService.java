package ru.recipe.recipeapp.service;

import ru.recipe.recipeapp.model.Recipe;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    Recipe getRecipeById(int id);
}
