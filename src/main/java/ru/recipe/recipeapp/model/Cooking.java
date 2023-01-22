package ru.recipe.recipeapp.model;

import lombok.Data;

@Data
public class Cooking {

    private String cooking;


    public Cooking(String cooking) {
        this.cooking = cooking;
    }


    @Override
    public String toString() {
        return cooking;
    }


    public String getCooking() {
        return cooking;
    }

    public void setCooking(String cooking) {
        this.cooking = cooking;
    }
}
