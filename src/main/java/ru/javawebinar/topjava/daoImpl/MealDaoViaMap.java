package ru.javawebinar.topjava.daoImpl;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Микитенко on 12.09.2016.
 */
public class MealDaoViaMap implements MealDao {
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private AtomicInteger idCount = new AtomicInteger(0);

    @Override
    public boolean createMeal(Meal meal) {
        if (meals.containsValue(meal))
            return false;
        int id = idCount.getAndIncrement();
        meals.put(id, meal);
        meal.setId(id);
        return true;
    }

    @Override
    public boolean updateMeal(int mealID, Meal meal) {
        if( mealID > idCount.get() || mealID < 0 |! meals.containsKey(mealID))
            return false;
        meals.put(mealID, meal);
        return true;
    }

    @Override
    public boolean deleteMeal(int mealID) {
        if( mealID > idCount.get() || mealID < 0)
            return false;
        meals.remove(mealID);
        return true;
    }

    @Override
    public List<Meal> allMeals() {
        return new ArrayList<>(meals.values());
    }
}
