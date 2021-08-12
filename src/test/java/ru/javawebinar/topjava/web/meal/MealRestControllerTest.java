package ru.javawebinar.topjava.web.meal;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.TestUtil.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static ru.javawebinar.topjava.web.meal.MealRestController.REST_URL;

public class MealRestControllerTest extends AbstractControllerTest {

    @Autowired
    private MealService mealService;

    @Test
    public void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL + '/' + MEAL1_ID))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(contentType())
                        .andExpect(contentJson(MEAL1))
        );
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + '/' + MEAL1_ID))
                .andExpect(status().isNoContent());
        //.andExpect(status().is2xxSuccessful());
        assertMatch(mealService.getAll(MEAL1_ID));

    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(contentType())
                .andExpect(contentJson(MealsUtil.getTos(MEALS, UserTestData.USER.getCaloriesPerDay())));
    }

    @Test
    public void testCreateWithLocation() throws Exception {
        Meal expected = getCreated();

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Meal returned = TestUtil.readFromJson(action, Meal.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(mealService.getAll(START_SEQ), expected, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();

        mockMvc.perform(
                put(REST_URL + "/" + MEAL1_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(updated))
        ).andExpect(status().isOk());

        assertMatch(mealService.get(MEAL1_ID, START_SEQ), updated);
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "/filter")
                .param("startDate", "2015-05-31")
                .param("endTime", "17:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(contentType())
                .andExpect(contentJson(
                        MealsUtil.createTo(MEAL5, true),
                        MealsUtil.createTo(MEAL4, true)
                ))
        ;
    }
}
