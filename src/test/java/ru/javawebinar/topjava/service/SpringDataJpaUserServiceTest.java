package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Микитенко on 16.10.2016.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.DATA_JPA})
public class SpringDataJpaUserServiceTest extends AbstractUserServiceTest{
}
