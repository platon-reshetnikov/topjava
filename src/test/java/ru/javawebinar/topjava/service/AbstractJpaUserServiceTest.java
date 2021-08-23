package ru.javawebinar.topjava.service;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.JpaUtil;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;

abstract public class AbstractJpaUserServiceTest extends AbstractUserServiceTest {

    //@Autowired
    private JpaUtil jpaUtil;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new User(null, "  ", "invalid@yandex.ru", "password", 2000, Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", 2000, Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "invalid@yandex.ru", "", 2000, Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", 9, true, new Date(), Collections.emptySet())), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "password", 10001, true, new Date(), Collections.emptySet())), ConstraintViolationException.class);
    }
}