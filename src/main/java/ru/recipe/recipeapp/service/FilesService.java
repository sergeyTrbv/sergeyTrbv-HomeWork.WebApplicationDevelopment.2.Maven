package ru.recipe.recipeapp.service;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public interface FilesService<T> {
    boolean saveToFile(String json);

    String readFromFile();

    File getDataFile();

    Path createTempFile(String suffix);

    boolean cleanDataFile();

    byte[] exportTxt(Map<Integer, T> map);
}
