package ru.recipe.recipeapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cooking {

    @Schema(description = "Шаги приготовления",example = "Положить колбасу на хлеб")
    private String cooking;


    //Методы
    @Override
    public String toString() {
        return cooking;
    }

}
