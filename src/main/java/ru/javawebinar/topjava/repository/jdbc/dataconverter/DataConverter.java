package ru.javawebinar.topjava.repository.jdbc.dataconverter;

/**
 * Created by Микитенко on 16.10.2016.
 */
public interface DataConverter<I extends Object, O extends Object> {
    O convertToSql(I input);
    I convertFromSql(O output);
}
