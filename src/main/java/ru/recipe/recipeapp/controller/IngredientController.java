package ru.recipe.recipeapp.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.recipe.recipeapp.model.Ingredient;
import ru.recipe.recipeapp.model.Recipe;
import ru.recipe.recipeapp.service.IngredientService;

import java.util.Collection;
import java.util.Map;

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
    public Map<Integer, Ingredient> getAllIngredients() {                                                   //Метод "Получение всех ингридиентов"
        return this.ingredientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {                                             //Метод "Получение ингридиента по id"
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Ingredient> postIngredient(@RequestBody Ingredient ingredient) {                              //Метод "Добавить ингридиент"
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {        //Метод "Изменить ингридиент"
        Ingredient ingredients = ingredientService.editIngredient(id, ingredient);
        if (ingredient == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/deleteall")                                                    //Метод "удалить все ингридиенты"
    public ResponseEntity<Ingredient> deleteAllIngredient() {
        ingredientService.deleteAllIngredient();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteI(@PathVariable int id) {                       //Метод "Удалить ингридиенты"
        if (ingredientService.deleteIngredientById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}



