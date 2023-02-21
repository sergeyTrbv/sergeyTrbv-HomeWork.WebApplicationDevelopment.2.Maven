package ru.recipe.recipeapp.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.recipe.recipeapp.model.Cooking;
import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.FilesService;
import ru.recipe.recipeapp.service.RecipeService;
import ru.recipe.recipeapp.service.impl.RecipeServiceImpl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service("filesServiceRecipeImpl")
public class FilesServiceRecipeImpl implements FilesService<Recipe> {

    @Value("${path.to.data.fileRecipe}")
    private String dataFilePath;
    @Value("${name.of.data.fileRecipe}")
    private String dataFileName;


    @Value("${path.to.data.fileRecipeTxt}")
    private String dataFilePathTxt;
    @Value("${name.of.data.fileRecipeTxt}")
    private String dataFileNameTxt;

    private final Path pathToTxtTemplate;

    public FilesServiceRecipeImpl(@Value("${path.to.data.fileRecipeTxt}") String path) {
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
            throw new RuntimeException(e);
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
    public byte[] exportTxt(Map<Integer, Recipe> recipeMap) {
        try {
            String template = Files.readString(Paths.get(dataFilePathTxt, dataFileNameTxt), StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            for (Recipe recipe : recipeMap.values()) {
                StringBuilder ingredients = new StringBuilder();
                StringBuilder steps = new StringBuilder();
                for (Ingredient ingredient : recipe.getIngredient()) {
                    ingredients.append(" - ").append(ingredient).append("\n");
                }
                int stepCounter = 1;
                for (Cooking step : recipe.getCookingStep()) {
                    steps.append((stepCounter++)).append(". ").append(step).append("\n");
                }
                String recipeData = template.replace("%title%", recipe.getName())
                        .replace("%cookingTime%", String.valueOf(recipe.getCookingTime()))
                        .replace("%ingredient%", ingredients.toString())
                        .replace("%steps%", steps.toString());
                stringBuilder.append(recipeData).append("\n\n\n");
            }
            return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
