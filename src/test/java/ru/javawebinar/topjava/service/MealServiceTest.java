package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID, ADMIN_ID);
        assertMatch(meal, ADMIN_MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(1, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getForeignMeal() {
        service.get(USER_MEAL1.getId(), ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL6, ADMIN_MEAL5, ADMIN_MEAL4, ADMIN_MEAL3, ADMIN_MEAL2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteForeignMeal() {
        service.delete(USER_MEAL2.getId(), ADMIN_ID);
    }

    @Test
    public void getAll() {
        List<Meal> mealList = service.getAll(USER_ID);
        assertMatch(mealList, USER_MEAL2, USER_MEAL1);
    }

    @Test
    public void update() {
        Meal updated = new Meal(USER_MEAL1);
        updated.setDescription("Updated Description");
        updated.setCalories(1500);
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL1.getId(), USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateForeignMeal() {
        Meal updated = new Meal(USER_MEAL1);
        updated.setDescription("Updated Description");
        updated.setCalories(1500);
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.of(2018, Month.MARCH, 13, 14, 0), "Обед", 700);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), USER_MEAL2, newMeal, USER_MEAL1);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateDateTimeCreate() {
        Meal newMeal = new Meal(null, USER_MEAL1.getDateTime(), "Обед", 700);
        service.create(newMeal, USER_ID);
    }

    @Test
    public void getBetween() {
        List<Meal> mealList = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), ADMIN_ID);
        assertMatch(mealList, ADMIN_MEAL3, ADMIN_MEAL2, ADMIN_MEAL1);
    }
}
