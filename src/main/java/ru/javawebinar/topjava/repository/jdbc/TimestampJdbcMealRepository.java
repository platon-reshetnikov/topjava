package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
@Profile(Profiles.HSQL_DB)
public abstract class TimestampJdbcMealRepository extends AbstractJdbcMealRepositoryImpl<Timestamp> {

    public TimestampJdbcMealRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super((javax.activation.DataSource) dataSource, jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    Timestamp inToDataTime(LocalDateTime ltd) {
        return Timestamp.valueOf(ltd);
    }
}
