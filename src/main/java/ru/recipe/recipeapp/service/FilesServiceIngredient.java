package ru.recipe.recipeapp.service;

public interface FilesServiceIngredient {
    boolean saveToFile(String json);

    String readFromFile();
}
