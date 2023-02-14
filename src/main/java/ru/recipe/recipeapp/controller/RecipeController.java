package ru.recipe.recipeapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.RecipeService;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы с Рецептами")
public class RecipeController {

    private RecipeService recipeService;

    //Контроллер
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //Методы
    @GetMapping("/getall")
    @Operation(summary = "Список всех рецептов",description = "Выведет список всех рецептов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все рецепты найдены",
                    content = @Content(
                            mediaType = "application/JSON",
                            array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                    )
            )
    })
    public Map<Integer, Recipe> getAllRecipe() {                                              //Метод "Получение всех рецептов"
        return this.recipeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск определённого рецепта",description = "Нужно указать id рецепта и он , скорее всего, найдётся")
    public ResponseEntity<Recipe> getRecipe(@PathVariable@Parameter(description = "Идентификатор рецепта, который имеется в базе данных приложения") int id) {                          //Метод "Получение рецепта по id"
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }


    @PostMapping("/")
    @Operation(summary = "Добавление рецепта", description = "Для добавления рецепта нужно создать тело (JSON)")
    public ResponseEntity<Recipe> postRecipe(@RequestBody Recipe recipe) {                  //Метод "Добавить рецепт"
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Изменение определённого рецепта", description = "Нужно указать id рецепта, который мы хотим изменить и изменить JSON-файл")
    public ResponseEntity<Recipe> editRecipe(@PathVariable@Parameter(description = "Идентификатор рецепта, который имеется в базе данных приложения") int id, @RequestBody Recipe recipe) {         //Метод "Изменить рецепт"
        Recipe transactoin = recipeService.editRecipe(id, recipe);
        if (recipe == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);

    }

    @DeleteMapping("/deleteall")                                                         //Метод "удалить все рецепты"
    @Operation(summary = "Удаление всех рецептов",description = "Удалит все рецепты из приложения")
    public ResponseEntity<Recipe> deleteAllRecipe() {
        recipeService.deleteAllRecipe();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление определённого рецепта", description = "Нужно указать id рецепта и после, удалить его")
    public ResponseEntity<Void> deleteRecipeById(@PathVariable@Parameter(description = "Идентификатор рецепта, который имеется в базе данных приложения") int id) {                   //Метод "Удалить рецепт"
        if (recipeService.deleteRecipeById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
