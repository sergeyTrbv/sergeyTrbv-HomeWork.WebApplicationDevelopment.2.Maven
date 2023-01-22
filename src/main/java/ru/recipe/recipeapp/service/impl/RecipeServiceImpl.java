package ru.recipe.recipeapp.service.impl;

import org.springframework.stereotype.Service;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.RecipeService;

import java.util.HashMap;
import java.util.Map;


@Service
public class RecipeServiceImpl implements RecipeService {

    private static int Id = 0;

    private final Map<Integer, Recipe> mapRecipe = new HashMap<>();

@Override
    public void addRecipe(Recipe recipe) {       //Метод добавление рецепта
        mapRecipe.put(Id++, recipe);
    }
@Override
    public Recipe getRecipeById(int id) {       //Метод получение рецепта по Id
        if (mapRecipe.containsKey(id)) {
            return mapRecipe.get(id);
        } else {
            throw new RuntimeException("Не удалось получить рецепт");
        }
    }
}
