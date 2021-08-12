package ru.javawebinar.topjava.web.converter;

import org.springframework.core.convert.converter.Converter;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;


public class DateTimeConverter {
    public static class LocalDateConverter implements Converter<String, LocalDate>{

        @Override
        public LocalDate convert(String s) {
            return DateTimeUtil.parseLocalDate(s);
        }
    }

    public static class LocalTimeConverter implements Converter<String, LocalTime>{

        @Override
        public LocalTime convert(String s) {
            return DateTimeUtil.parseLocalTime(s);
        }
    }

}

