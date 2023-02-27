package ru.recipe.recipeapp.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.recipe.recipeapp.model.Cooking;
import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.FilesService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service("filesServiceIngredientImpl")
public class FilesServiceIngredientImpl implements FilesService<Ingredient> {

    @Value("${path.to.data.fileIngredient}")
    private String dataFilePath;

    @Value("${name.of.data.fileIngredient}")
    private String dataFileName;

    @Value("${path.to.data.fileIngredientTxt}")
    private String dataFilePathTxt;
    @Value("${name.of.data.fileIngredientTxt}")
    private String dataFileNameTxt;

    private final Path pathToTxtTemplate;

    public FilesServiceIngredientImpl(@Value("{path.to.data.fileIngredientTxt}") String path) {
        this.pathToTxtTemplate = Paths.get(path);
    }

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePath, dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath, dataFileName));
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл");
        }
    }

    @Override
    public File getDataFile() {
        return new File(dataFilePath + "/" + dataFileName);
    }

    @Override
    public Path createTempFile(String suffix) {
        try {
            return Files.createTempFile(Path.of(dataFilePath), "tempFile", suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePath, dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public byte[] exportTxt(Map<Integer, Ingredient> ingredientMap) {
        try {

            String template = Files.readString(Paths.get(dataFilePathTxt, dataFileNameTxt), StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            for (Ingredient ingredient : ingredientMap.values()) {
                String ingredientData = template.replace("%ingredient%", ingredient.toString());
                stringBuilder.append(ingredientData).append("\n");
            }
            return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
