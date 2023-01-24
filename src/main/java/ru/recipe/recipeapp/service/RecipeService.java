package ru.recipe.recipeapp.service;

import ru.recipe.recipeapp.model.Recipe;

import java.util.Collection;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    Recipe getRecipeById(int id);


    Collection<Recipe> getAll();
}

