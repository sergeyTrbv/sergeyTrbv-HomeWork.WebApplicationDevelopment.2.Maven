package ru.recipe.recipeapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {


    @Schema(description = "Ингридиент",example = "Колбаса")
    private String nameIngredient;
    @Schema(description = "Количество",example = "2")
    private int numberOfIngredient;     //Кол-во ингредиентов
    @Schema(description = "Единица измерения",example = "Дольки")
    private String unitOfMeasurement;    //Единица измерения


    //Методы
    @Override
    public String toString() {
        return nameIngredient + numberOfIngredient + unitOfMeasurement;
    }

}
