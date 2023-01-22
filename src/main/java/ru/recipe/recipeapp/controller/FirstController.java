package ru.recipe.recipeapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello, web!";
    }
    @GetMapping("/info")
    public String info() {
        return """
                name developer's: Сергей
                name project: Recipe
                creation date: 13/01/2023
                description: Страничка с вашими самыми любимыми рецептами пока что разрабатывается
                """
                ;
    }


}