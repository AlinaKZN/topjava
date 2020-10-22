package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.time.LocalDateTime.now;

public class MealServlet extends HttpServlet {
    public static final String MEALS_JSP = "/meals.jsp";
    private static final String MEAL_JSP = "/meal.jsp";
    private final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealDao dao;

    public MealServlet() {
        dao = new MealDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  List<Meal> meals = MealsUtil.initMeals();
        String forward = MEALS_JSP;
        String action = req.getParameter("action");
        if (action == null) {
            List<MealTo> mealsTo = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
            req.setAttribute("meals", mealsTo);
        } else if (action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            List<MealTo> mealsTo = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
            req.setAttribute("meals", mealsTo);
        } else if (action.equalsIgnoreCase("edit")) {
            forward = MEAL_JSP;
            int id = Integer.parseInt(req.getParameter("id"));
            Meal meal = dao.get(id);
            req.setAttribute("meal", meal);
        } else {
            forward = MEAL_JSP;
        }

        getServletContext().getRequestDispatcher(forward).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime date = now();//LocalDateTime.parse(req.getParameter("date"));
        Meal meal = new Meal(0, date, req.getParameter("desc"), Integer.parseInt(req.getParameter("calories")));
        dao.add(meal);
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        req.setAttribute("meals", mealsTo);
        getServletContext().getRequestDispatcher(MEALS_JSP).forward(req, resp);
    }
}
