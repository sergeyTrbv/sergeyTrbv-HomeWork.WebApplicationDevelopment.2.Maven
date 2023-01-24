package ru.recipe.recipeapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.IngredientService;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    //Контроллер
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    //Методы
    @GetMapping("/getall")
    public Collection<Recipe> getAllIngredients() {                                                   //Метод "Получение всех ингридиентов"
        return this.ingredientService.getAll();
    }

    @GetMapping("/get")
    public ResponseEntity<Ingredient> getIngredient(@RequestParam int id) {                           //Метод "Получение ингридиента по id"
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Ingredient> postIngredient(@RequestBody Ingredient ingredient) {             //Метод "Добавить ингридиент"
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }


}
