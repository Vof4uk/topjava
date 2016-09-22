package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal get(int id, int userid) {
        Meal meal = repository.get(id, userid);
        if (meal == null) throw new NotFoundException("No meal found");
        return meal;
    }

    @Override
    public Meal save(Meal meal, int usrId) {
        if(meal.getUserId() == usrId)
            return repository.save(meal);
        else throw new NotFoundException("Not your meal to save");
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meals = repository.getAll(userId).stream().collect(Collectors.toList());
        return meals;
    }

    @Override
    public boolean delete(int mealId, int userId) {
        if (!repository.delete(mealId, userId)) throw new NotFoundException("No such meal in your meals");
        return true;
    }

    @Override
    public Meal update(int id, Meal meal, int userId) {
        meal.setId(id);
        return save(meal, userId);
    }
}
