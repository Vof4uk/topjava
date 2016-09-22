package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
@Component
public class MealServlet extends HttpServlet {

    @Autowired
    private MealRestController controller;

    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
        AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
        adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN));
        controller = appCtx.getBean(MealRestController.class);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        meal.setUserId(getUserId(request));

        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        controller.save(meal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("mealList",
                    MealsUtil.getWithExceeded(controller.getAll(), AuthorizedUser.getCaloriesPerDay()));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);

        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            controller.delete(id);
            response.sendRedirect("meals");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now().withNano(0).withSecond(0), "", 1000) :
                    controller.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);

        } else  if("filter_by_date".equals(action)) {
            final LocalDate fromDate = LocalDate.parse(request.getParameter("fromDate"));
            final LocalDate toDate = LocalDate.parse(request.getParameter("toDate"));
            final LocalTime fromTime = LocalTime.parse(request.getParameter("fromTime"));
            final LocalTime toTome = LocalTime.parse(request.getParameter("toTime"));

            List<Meal> meals =  controller.getAll()
                    .stream()
                    .filter(meal -> TimeUtil.isBetweenTime(meal.getTime(), fromTime, toTome))
                    .filter(meal -> TimeUtil.isBetweenDates(meal.getDate(), fromDate, toDate))
                    .collect(Collectors.toList());
            request.setAttribute("mealList",
                    MealsUtil.getWithExceeded(meals, AuthorizedUser.getCaloriesPerDay()));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private int getUserId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("user_id"));
        return Integer.valueOf(paramId);
    }
}
