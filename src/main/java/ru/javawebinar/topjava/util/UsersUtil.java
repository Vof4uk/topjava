package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Микитенко on 18.09.2016.
 */
public class UsersUtil {

    public static List<User> USERS = Arrays.asList(
            new User(0, "anton", "anton@gmail.com", "pw", Role.ROLE_ADMIN),
            new User(1, "lina", "lina@gmail.com", "pwlina", Role.ROLE_USER),
            new User(2, "kisa", "kisa@gmail.com", "pwkisa", Role.ROLE_USER),
            new User(3, "crunch", "crunch@gmail.com", "pwcrunch", Role.ROLE_USER)
    );

    public static void sortByName(List<User> userList){
        Collections.sort(userList, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }
}
