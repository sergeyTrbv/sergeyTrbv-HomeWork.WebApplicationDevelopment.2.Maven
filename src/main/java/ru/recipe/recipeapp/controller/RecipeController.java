package ru.recipe.recipeapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.RecipeService;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;

    //Контроллер
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    //Методы
    @GetMapping("/getall")
    public Collection<Recipe> getAllRecipe() {                                        //Метод "Получение всех рецептов"
        return this.recipeService.getAll();
    }

    @GetMapping("/get")
    public ResponseEntity<Recipe> getRecipe(@RequestParam int id) {                   //Метод "Получение рецепта по id"
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Recipe> postRecipe(@RequestBody Recipe recipe){             //Метод "Добавить рецепт"
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }


}
