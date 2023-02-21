package ru.recipe.recipeapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.service.FilesService;
import ru.recipe.recipeapp.service.IngredientService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {


    final private FilesService<Ingredient> filesServiceIngredient;
    private static int id = 0;

    private Map<Integer, Ingredient> mapIngredients = new HashMap<>();

    public IngredientServiceImpl(@Qualifier("filesServiceIngredientImpl") FilesService<Ingredient> filesServiceIngredient) {
        this.filesServiceIngredient = filesServiceIngredient;
    }


    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public void addIngredient(Ingredient ingredient) {                               //Метод добавление ингредиента
        mapIngredients.put(id++, ingredient);
        saveToFile();
    }

    @Override
    public Map<Integer, Ingredient> getAll() {                                       //Метод получения всех ингредиентов
        return mapIngredients;
    }

    @Override
    public Ingredient getIngredientById(int id) {                                    //Метод получение ингридиента по Id
        if (mapIngredients.containsKey(id)) {
            return mapIngredients.get(id);
        }
        throw new RuntimeException("Не удалось получить ингридиент");
    }


    @Override
    public Ingredient editIngredient(Integer id, Ingredient ingredient) {           //Метод изменение ингридиента по Id
        if (mapIngredients.containsKey(id)) {
            mapIngredients.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
        return mapIngredients.get(id);
    }


    @Override
    public boolean deleteIngredientById(Integer id) {                               //Метод удаления ингридиента по id
        if (mapIngredients.containsKey(id)) {
            mapIngredients.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Ingredient deleteAllIngredient() {                                      //Метод "удаление всех ингридиентов"
        mapIngredients.clear();
        return null;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(mapIngredients);
            filesServiceIngredient.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = filesServiceIngredient.readFromFile();
            mapIngredients = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
