package ru.recipe.recipeapp.service.impl;

import org.springframework.stereotype.Service;
import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.IngredientService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static int Id = 0;

    private final Map<Integer, Ingredient> mapIngredients = new HashMap<>();

    @Override
    public void addIngredient(Ingredient ingredient) {       //Метод добавление ингредиента
        mapIngredients.put(Id++, ingredient);
    }

    @Override
    public Ingredient getIngredientById(int id) {           //Метод получение ингридиента по Id
        if (mapIngredients.containsKey(id)) {
            return mapIngredients.get(id);
        } else {
            throw new RuntimeException("Не удалось получить ингридиент");
        }
    }
    @Override
    public Collection<Recipe> getAll() {       //Метод получения всех ингредиентов
        return null;
    }
  }
