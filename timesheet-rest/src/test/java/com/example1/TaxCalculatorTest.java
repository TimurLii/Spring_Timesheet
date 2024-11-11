package com.example1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorTest {
    @Mock
    TaxResolver mock;

    @Test
    void testGetPriceWithTax() {
//          TaxResolver mock = mock(TaxResolver.class);
        when(mock.getCurrentTax()).thenReturn(0.2);

        TaxCalculator taxCalculator = new TaxCalculator(mock);
        assertEquals(120.0, taxCalculator.getPriceWithTax(100.0), 0.000009);
    }
}