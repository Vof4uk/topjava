package ru.javawebinar.topjava.repository.jdbc.dataconverter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Микитенко on 16.10.2016.
 */
@Profile("postgres")
@Component
public class MockLocalDateTimeConverter implements DataConverter<LocalDateTime, LocalDateTime>{
    @Override
    public LocalDateTime convertToSql(LocalDateTime input) {
        return input;
    }

    @Override
    public LocalDateTime convertFromSql(LocalDateTime output) {
        return output;
    }
}
