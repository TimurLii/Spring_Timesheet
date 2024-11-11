package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
@Test
    void testSum(){
    Calculator calculator = new Calculator();
    int actual = calculator.sum(2,3);
    int expected = 5;

    assertEquals(actual,expected  );
}
}
