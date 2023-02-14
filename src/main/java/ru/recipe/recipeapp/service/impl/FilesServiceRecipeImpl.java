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
public class FilesServiceRecipeImpl implements FilesService {

    @Value("${path.to.data.fileRecipe}")
    private String dataFilePath;

    @Value("${name.of.data.fileRecipe}")
    private String dataFileName;




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
    private final Path pathToTxtTemplate;

    public FilesServiceRecipeImpl(@Qualifier("filesServiceRecipeImpl") FilesService filesServiceRecipe, @Value("{path.to.data.fileRecipeTxt}") String path) throws URISyntaxException {
        this.pathToTxtTemplate = Paths.get(FilesServiceRecipeImpl.class.getResource("name.of.data.fileRecipeTxt").toURI());
    }

    @Override
    public byte[] exportTxt() {
        try {
            String template = Files.readString(pathToTxtTemplate, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            for (Recipe recipe : mapRecipe.values()) {
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
