package ru.fastdelivery.domain.common.dimensions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DimensionTest {

    @ParameterizedTest(name = "Округление линейного размера кратно 50")
    @CsvSource({"0, 0", "234, 250", "300, 300"})
    void testRoundUp(int actual, int expected) {
        var actualDimension = new Dimension(actual);
        assertEquals(expected, actualDimension.roundUp().value());
    }
}