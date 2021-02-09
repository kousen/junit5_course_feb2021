package com.kousenit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Disabled until you want to demonstrate timeouts")
public class TimeoutTest {
    @Test
    void timeoutWithoutPreemption() {
        assertTimeout(Duration.ofMillis(100), () -> Thread.sleep(250));
    }

    @Test
    void timeoutWithPreemption() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> Thread.sleep(250));
    }
}
