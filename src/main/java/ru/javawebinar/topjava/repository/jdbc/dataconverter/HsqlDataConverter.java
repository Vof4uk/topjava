package ru.javawebinar.topjava.repository.jdbc.dataconverter;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Микитенко on 16.10.2016.
 */
@Profile("hsqldb")
@Component
public class HsqlDataConverter implements DataConverter<LocalDateTime, Timestamp>{
    @Override
    public Timestamp convertToSql(LocalDateTime input) {
        return Timestamp.valueOf(input);
    }

    @Override
    public LocalDateTime convertFromSql(Timestamp output) {
        return LocalDateTime.parse(output.toString());
    }
}
