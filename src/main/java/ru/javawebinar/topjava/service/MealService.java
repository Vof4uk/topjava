package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    Meal get(int id, int userid);
    Meal save (Meal meal, int userId);
    List<Meal> getAll(int userId);
    boolean delete(int mealId, int userId);
    Meal update (int id, Meal meal, int userId);
}
