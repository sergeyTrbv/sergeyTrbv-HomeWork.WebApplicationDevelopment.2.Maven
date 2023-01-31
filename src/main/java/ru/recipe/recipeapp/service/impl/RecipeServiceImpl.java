package ru.recipe.recipeapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.FilesService;
import ru.recipe.recipeapp.service.RecipeService;

import javax.annotation.PostConstruct;
import java.time.Month;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


@Service
public class RecipeServiceImpl implements RecipeService {

    final private FilesService filesService;
    private static int Id = 0;

    public Map<Integer, Recipe> mapRecipe = new TreeMap<>();

    public RecipeServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init(){
        readFromFile();
    }


    @Override

    public void addRecipe(Recipe recipe) {                                                //Метод добавление рецепта
        mapRecipe.put(Id++, recipe);
        saveToFile();
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
                saveToFile();
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

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(mapRecipe);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = filesService.readFromFile();
            mapRecipe = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
