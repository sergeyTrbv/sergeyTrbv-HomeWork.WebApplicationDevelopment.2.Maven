package ru.recipe.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cooking {

    private String cooking;


    //Методы
    @Override
    public String toString() {
        return cooking;
    }


    //Геттеры и Сеттеры
    public String getCooking() {
        return cooking;
    }

    public void setCooking(String cooking) {
        this.cooking = cooking;
    }
}
