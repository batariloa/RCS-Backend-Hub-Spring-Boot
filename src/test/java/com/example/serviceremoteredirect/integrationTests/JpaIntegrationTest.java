package com.example.serviceremoteredirect.integrationTests;


import com.example.serviceremoteredirect.entity.LoggedAccess;
import com.example.serviceremoteredirect.repository.LocationRepository;
import com.example.serviceremoteredirect.repository.LoggedAccessRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaIntegrationTest {

    @Autowired
    private LoggedAccessRepository loggedAccessRepository;

    @Autowired
    LocationRepository locationRepository;

    @Test
    public void assertLoggedAccessIdNotNull() {

    LoggedAccess loggedAccess = LoggedAccess.builder()
                    .username("TestJPA")
                            .build();

    loggedAccessRepository.save(loggedAccess);
    Assertions.assertNotNull(loggedAccess.getId());

    }

    @Test
    public void assertLoggedAccessIsUpdated() {

        LoggedAccess loggedAccess = LoggedAccess.builder()
                .username("TestJPA")
                .build();

        loggedAccessRepository.save(loggedAccess);
        Assertions.assertEquals(loggedAccess.getUsername(), "TestJPA");

    }
}
