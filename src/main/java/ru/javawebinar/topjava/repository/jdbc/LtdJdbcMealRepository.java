package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Repository
@Profile(Profiles.POSTGRES_DB)
public abstract class LtdJdbcMealRepository extends AbstractJdbcMealRepositoryImpl<LocalDateTime> {

    public LtdJdbcMealRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super((javax.activation.DataSource) dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    LocalDateTime inToDataTime(LocalDateTime ltd) {
        return ltd;
    }
}
