package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.AuthorizedUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete {} for user {}", id, userId);
        service.delete(userId, id);
    }

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get {} for user {}", id, userId);
        return service.get(userId, id);
    }

    public List<MealTo> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getFilterDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getFilterDate");

        List<Meal> mealList = service.getFilterDate(userId,
                startDate == null ? LocalDate.MIN : startDate,
                endDate == null ? LocalDate.MAX : endDate
        );

        return MealsUtil.getFilteredTos(mealList, MealsUtil.DEFAULT_CALORIES_PER_DAY,
                startTime == null ? LocalTime.MIN : startTime,
                endTime == null ? LocalTime.MAX : endTime
        );
    }

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        log.info("create {} for user {}", meal, userId);
        checkNew(meal);
        return service.create(userId, meal);
    }

    public void update(Meal meal, int id) {
        int userId = AuthorizedUser.id();
        log.info("update {} with id={} for user {}", meal, id, userId);
        assureIdConsistent(meal, id);
        service.update(userId, meal);
    }


}