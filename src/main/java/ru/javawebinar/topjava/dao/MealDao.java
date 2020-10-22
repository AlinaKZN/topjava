package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;

public class MealDao {

    List<Meal> _meals = new ArrayList<>(MealsUtil.initMeals());

    public void add(Meal meal) {
        int id = _meals.get(_meals.size() - 1).getId();
        meal.setId(id++);
        _meals.add(meal);
    }

    public void update(Meal newmeal) {
        Meal meal = get(newmeal.getId());
        if (meal != null) {
            _meals.remove(meal);
            _meals.add(newmeal);
        }
    }

    public void delete(int id) {
        Meal meal = get(id);
        if (meal != null) {
            _meals.remove(meal);
        }
    }

    public Meal get(int id) {
        return _meals.stream().filter(m -> m.getId() == id).findFirst().get();
    }

    public List<Meal> getAll() {
        return _meals;
    }

}
