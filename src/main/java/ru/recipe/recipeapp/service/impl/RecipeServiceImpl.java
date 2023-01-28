package ru.recipe.recipeapp.service.impl;

import org.springframework.stereotype.Service;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.RecipeService;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Service
public class RecipeServiceImpl implements RecipeService {

    private static int Id = 0;

    public Map<Integer, Recipe> mapRecipe = new HashMap<>();

    @Override
    public void addRecipe(Recipe recipe) {                                                //Метод добавление рецепта
        mapRecipe.put(Id++, recipe);
    }

    @Override
    public Map<Integer, Recipe> getAll() {                                               //Метод получения всех рецептов
        return mapRecipe;
    }

    @Override
    public Recipe getRecipeById(int id) {                                                //Метод получение рецепта по id
        if (mapRecipe.containsKey(id)) {
            return mapRecipe.get(id);
        } else {
            throw new RuntimeException("Не удалось получить рецепт");
        }
    }

    @Override
    public Recipe editRecipe(Integer id, Recipe recipe) {                                //Метод изменение рецепта по id
        for (Recipe recipe1 : mapRecipe.values()) {
            if (mapRecipe.containsKey(id)) {
                mapRecipe.put(id, recipe);
                return recipe;
            }
        }
        return mapRecipe.get(id);

    }

    @Override
    public boolean deleteRecipeById(Integer id) {                                        //Метод удаления рецепта по id
        var removed = mapRecipe.remove(id);
        return removed != null;
    }

    @Override
    public Recipe deleteAllRecipe() {                                                   //Метод "удаление всех рецептов"
        mapRecipe.clear();
        return null;
    }


}
