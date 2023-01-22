package ru.recipe.recipeapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private RecipeService recipeService;


    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/get")
    public ResponseEntity<Recipe> getRecipe(@RequestParam int id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Recipe> postRecipe(@RequestBody Recipe recipe){
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }


}
