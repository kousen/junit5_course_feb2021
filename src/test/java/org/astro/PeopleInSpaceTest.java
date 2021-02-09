package org.astro;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class PeopleInSpaceTest {

    @BeforeEach
    void setUp() throws Exception {
        assumeTrue(InetAddress.getByName("api.open-notify.org").isReachable(2000),
                "api.open-notify.org is down");
    }

    @Test
    void getAstronautsAsString() throws Exception {
        String astronauts = PeopleInSpace.getAstronautsAsString();
        assertNotNull(astronauts);
        assertAll(
                () -> assertFalse(astronauts.isEmpty()),
                () -> assertFalse(astronauts.isBlank())
        );
    }

    @Test
    void getAstronauts() {
        AstroResponse response = PeopleInSpace.getAstronauts();
        assertAll(
                () -> assertEquals("success", response.getMessage()),
                () -> assertTrue(response.getNumber() >= 0),
                () -> assertEquals(response.getNumber(), response.getPeople().size())
        );

        System.out.println("There are " + response.getNumber() + " people in space");
        for (Assignment assignment : response.getPeople()) {
            System.out.println(assignment.getName() + " aboard " +assignment.getCraft());
        }
    }

    // In JUnit 4, this would be @Test(expected=Exception.class)
    @Test
    void checkForTimeoutException() {
        Exception exc = assertThrows(Exception.class,
                () -> PeopleInSpace.demonstrateTimeoutException());
        assertTrue(exc.getMessage().contains("timed out"));
    }
}