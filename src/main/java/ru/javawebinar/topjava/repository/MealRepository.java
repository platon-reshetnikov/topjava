package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    Meal save(Meal entity);

    Meal findOne(Long id);

    void delete(Long id);

    Collection<Meal> findAll();
}