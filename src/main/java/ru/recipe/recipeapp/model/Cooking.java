package ru.recipe.recipeapp.model;

import lombok.Data;

@Data
public class Cooking {

    private String cooking;

    //Конструктор
    public Cooking(String cooking) {
        this.cooking = cooking;
    }

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
