package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import sun.misc.Contended;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public void delete(int id) {
        service.delete(id, AuthorizedUser.id());
    }

    public List<Meal> getAll() {
        return service.getAll(AuthorizedUser.id());
    }

    public Meal get(int id) {
        return service.get(id, AuthorizedUser.id());
    }

    public Meal save(Meal meal) {
        return service.save(meal, AuthorizedUser.id());
    }

    public Meal update(int id, Meal meal){
        meal.setId(id);
        return save(meal);
    }

}
