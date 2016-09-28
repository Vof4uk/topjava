package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;

/**
 * Created by Микитенко on 26.09.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_ID_START, USER_ID);
        MATCHER.assertEquals(MEALS_USER.get(0), meal);
    }

    @Test(expected = NotFoundException.class)
    public void getOutlying() throws Exception{
        Meal meal = service.get(MEAL_ID_START, ADMIN_ID);
        MATCHER.assertEquals(MEALS_USER.get(0), meal);
    }

    @Test(expected = NotFoundException.class)
    public void deleteOutlying() throws Exception {
        service.delete(MEAL_ID_START, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {

    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> list = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(list, MEALS_USER);
    }

    @Test
    public void update() throws Exception {
        Meal newMeal = new Meal(MEAL_ID_START,LocalDateTime.now(), "dd", 400);
        Meal updated = service.update(newMeal, USER_ID);
        MATCHER.assertEquals(newMeal, updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateOutlying() throws Exception {
        Meal newMeal = new Meal(MEAL_ID_START,LocalDateTime.now(), "dd", 400);
        Meal updated = service.update(newMeal, ADMIN_ID);
        MATCHER.assertEquals(newMeal, updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Meal newMeal = new Meal(1,LocalDateTime.now(), "dd", 400);
        Meal updated = service.update(newMeal, USER_ID);
        MATCHER.assertEquals(newMeal, updated);
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.now(), "dd", 400);
        Meal saved = service.save(newMeal, USER_ID);
        MATCHER.assertEquals(newMeal, saved);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID_START  + 1 , USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(1 , USER_ID);
    }

}