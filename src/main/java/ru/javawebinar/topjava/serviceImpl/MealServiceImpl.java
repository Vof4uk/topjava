package ru.javawebinar.topjava.serviceImpl;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.daoImpl.MealDaoViaMap;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

/**
 * Created by Микитенко on 12.09.2016.
 */

public class MealServiceImpl implements MealService{

    private MealDao mealDao = new MealDaoViaMap();

    @Override
    public boolean createMeal(Meal meal) {
        return mealDao.createMeal(meal);
    }

    @Override
    public boolean updateMeal(int mealID, Meal meal) {
        return mealDao.updateMeal(mealID, meal);
    }

    @Override
    public boolean deleteMeal(int mealID) {
        return mealDao.deleteMeal(mealID);
    }

    @Override
    public List<Meal> allMeals() {
        return mealDao.allMeals();
    }
}
