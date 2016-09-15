package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.serviceImpl.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Enumeration;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Микитенко on 12.09.2016.
 */

public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(MealServlet.class);
    private static MealService mealService = new MealServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        request.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(mealService.allMeals(),
                LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
//        response.sendRedirect("mealList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("operation");

        if(op.equals("create")) {

            String timestamp = req.getParameter("timestamp");
            String mealName = req.getParameter("meal");
            Integer calories = Integer.parseInt(req.getParameter("calories"));
            Meal meal = new Meal(LocalDateTime.parse(timestamp), mealName, calories);

            mealService.createMeal(meal);
        }
        else if(op.equals("delete"))
        {
            int id = Integer.parseInt(req.getParameter("id"));
            mealService.deleteMeal(id);
        }
        resp.sendRedirect("meals");
    }
}
