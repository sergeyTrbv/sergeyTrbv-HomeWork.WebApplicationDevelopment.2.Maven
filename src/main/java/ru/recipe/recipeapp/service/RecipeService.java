package ru.recipe.recipeapp.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import ru.recipe.recipeapp.model.Recipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

public interface RecipeService {

   Recipe addRecipe(Recipe recipe);

    Map<Integer, Recipe> getAll();

    Recipe getRecipeById(int id);

    Recipe editRecipe(Integer id, Recipe recipe);

    boolean deleteRecipeById(Integer id);

    Recipe deleteAllRecipe();

    Path createRecipeReport(Integer id) throws IOException;
}

