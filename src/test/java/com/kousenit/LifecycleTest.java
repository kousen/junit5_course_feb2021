package com.kousenit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LifecycleTest {
    private int x;

    public LifecycleTest() {
        System.out.println("Constructor: Instantiating the test class");
    }

    @BeforeAll
    static void initialize() {
        System.out.println("BeforeAll: Before any tests are run");
    }

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach: Initializing x to 42");
        x = 42;
    }

    private String getErrorMessage() {
        System.out.println("Inside getErrorMessage");
        return "x should be greater than zero";
    }

    @Test @DisplayName("Check value is greater than zero")
    public void firstTest() {
        System.out.println("x = " + x);
        // assertTrue(x > 0, getErrorMessage());
        assertTrue(x > 0, () -> getErrorMessage()); // Supplier<String>
    }

    @Test @DisplayName("Check value is less than zero")
    public void secondTest() {
        System.out.println("checking for negative numbers");
        x = -1;
        assertFalse(x > 0);
    }

    @Test // @Disabled("leaving this out for no good reason")
    public void computers_should_be_able_to_add() {
        System.out.println("check math");
        assertEquals(4, 2 + 2);
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach: Resetting x to zero");
        x = 0;
    }

    @AfterAll
    static void finish() {
        System.out.println("AfterAll: After all tests have been run");
    }
}
