package ru.fastdelivery.domain.common.dimensions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VolumeTest {

    @ParameterizedTest(name = "Проверка вычисления объема упаковки по линейным размерам")
    @CsvSource({"250, 375, 698, 0.07",
            "128, 826, 473, 0.0638"})
    void testGetVolume(int length, int width, int height, String expected) {
        var volume = new Volume(new Dimension(length),new Dimension(width), new Dimension(height));

        assertEquals(expected, volume.getVolume().stripTrailingZeros().toPlainString());
    }
}