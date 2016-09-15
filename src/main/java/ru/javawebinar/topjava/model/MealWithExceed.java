package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed {

    private final boolean exceed;

    private final Meal meal;

    public LocalDateTime getDateTime() {
        return meal.getDateTime();
    }

    public String getDescription() {
        return meal.getDescription();
    }

    public int getCalories() {
        return meal.getCalories();
    }

    public boolean isExceed() {
        return exceed;
    }

    public int getId(){
        return meal.getId();
    }

    public MealWithExceed (Meal meal,  boolean exceed) {
        this.exceed = exceed;
        this.meal = meal;
    }


    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + getDateTime() +
                ", description='" + getDescription() + '\'' +
                ", calories=" + getCalories() +
                ", exceed=" + exceed +
                '}';
    }
}
