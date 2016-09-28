package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int MEAL_ID_START = START_SEQ * 1000;

    public static final List<Meal> MEALS_USER = new ArrayList<>();
    public static final List<Meal> MEALS_ADMIN = new ArrayList<>();
    static
    {
        MEALS_USER.add(new Meal(LocalDateTime.parse("2016-09-26T07:38:00"), "Breakfast", 500));
        MEALS_USER.add(new Meal(LocalDateTime.parse("2016-09-26T11:38:00"), "Dinner", 600));
        MEALS_USER.add(new Meal(LocalDateTime.parse("2016-09-26T20:38:00"), "Supper", 300));
        MEALS_USER.add(new Meal(LocalDateTime.parse("2016-09-27T07:38:00"), "Breakfast", 600));
        MEALS_USER.add(new Meal(LocalDateTime.parse("2016-09-27T12:38:00"), "Dinner", 400));

        MEALS_ADMIN.add(new Meal(LocalDateTime.parse("2016-09-26T07:38:00"), "Breakfast", 200));
        MEALS_ADMIN.add(new Meal(LocalDateTime.parse("2016-09-26T11:38:00"), "Supper", 800));

        int count = MEAL_ID_START;
        for (Meal meal: MEALS_USER) {
            meal.setId(count++);
        }
        for (Meal meal: MEALS_ADMIN) {
            meal.setId(count++);
        }
    }

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    expected.toString().equals(actual.toString())
    );

}
