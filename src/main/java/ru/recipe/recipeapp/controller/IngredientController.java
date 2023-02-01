package ru.recipe.recipeapp.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.IngredientService;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингридиенты", description = "CRUD-операции и другие эндпоинты для работы с ингридиентами")
public class IngredientController {

    private IngredientService ingredientService;


    //Контроллер
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    //Методы
    @GetMapping("/getall")
    @Operation(summary = "Список всех ингридиентов",description = "Выведет список всех ингридиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все ингридиенты найдены"
            )
    })
    public Map<Integer, Ingredient> getAllIngredients() {                                                   //Метод "Получение всех ингридиентов"
        return this.ingredientService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск определённого ингридиента",description = "Нужно указать id ингридиента и он , скорее всего, найдётся")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {                                             //Метод "Получение ингридиента по id"
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PostMapping("/")
    @Operation(summary = "Добавление ингридиента", description = "Для добавления ингридиента нужно создать тело (JSON)")
    public ResponseEntity<Ingredient> postIngredient(@RequestBody Ingredient ingredient) {                              //Метод "Добавить ингридиент"
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Изменение определённого ингридиента", description = "Нужно указать id ингридиента, который мы хотим изменить и изменить JSON-файл")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {        //Метод "Изменить ингридиент"
        Ingredient ingredients = ingredientService.editIngredient(id, ingredient);
        if (ingredient == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/deleteall")                                                    //Метод "удалить все ингридиенты"
    @Operation(summary = "Удаление всех ингридиентов",description = "Удалит все ингридиенты из приложения")
    public ResponseEntity<Ingredient> deleteAllIngredient() {
        ingredientService.deleteAllIngredient();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление определённого ингридиента", description = "Нужно указать id ингридиента и после, удалить его")
    public ResponseEntity<Void> deleteI(@PathVariable int id) {                       //Метод "Удалить ингридиенты"
        if (ingredientService.deleteIngredientById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}



