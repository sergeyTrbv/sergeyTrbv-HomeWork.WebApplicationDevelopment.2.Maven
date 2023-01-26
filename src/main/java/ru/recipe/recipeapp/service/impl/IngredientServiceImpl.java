package ru.recipe.recipeapp.service.impl;

import org.springframework.stereotype.Service;
import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.IngredientService;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {


    private static int id = 0;

    public Map<Integer, Ingredient> mapIngredients = new HashMap<>();


    @Override
    public void addIngredient(Ingredient ingredient) {                               //Метод добавление ингредиента
        mapIngredients.put(id++, ingredient);
    }

    @Override
    public Map<Integer, Ingredient> getAll() {                                       //Метод получения всех ингредиентов
        return mapIngredients;
    }

    @Override
    public Ingredient getIngredientById(int id) {                                    //Метод получение ингридиента по Id
        if (mapIngredients.containsKey(id)) {
            return mapIngredients.get(id);
        } else {
            throw new RuntimeException("Не удалось получить ингридиент");
        }
    }

    @Override
    public Ingredient editIngredient(Integer id, Ingredient ingredient) {           //Метод изменение ингридиента по Id
        for (Ingredient ingredients : mapIngredients.values()) {
            if (mapIngredients.containsKey(id)) {
                mapIngredients.put(id, ingredient);
                return ingredient;
            }
        }
        throw new RuntimeException("Не удалось изменить ингридиент");
    }

    @Override
    public boolean deleteIngredientById(Integer id) {                               //Метод удаления ингридиента по id
        for (Ingredient ingredient : mapIngredients.values()) {
            if (mapIngredients.containsKey(id)) {
                mapIngredients.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Ingredient deleteAllIngredient() {                                      //Метод "удаление всех ингридиентов"
        mapIngredients = new TreeMap<>();
        return null;
    }
}
