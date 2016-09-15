package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Микитенко on 12.09.2016.
 */
public interface MealService {
    boolean createMeal(Meal meal);
    boolean updateMeal(int mealID, Meal meal);
    boolean deleteMeal(int mealID);
    List<Meal> allMeals();
}
