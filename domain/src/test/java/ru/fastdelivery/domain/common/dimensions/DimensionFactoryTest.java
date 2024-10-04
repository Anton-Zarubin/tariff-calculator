package ru.fastdelivery.domain.common.dimensions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class DimensionFactoryTest {

    @ParameterizedTest(name = "Линейный размер, мм = {arguments} -> объект создан")
    @ValueSource(ints = {0, 375, 1500})
    @DisplayName("Валидное значение линейного размера -> новый объект")
    void whenDimensionInRange_thenObjectCreated (int value) {
        var dimension = new Dimension(value);

        assertNotNull(dimension);
        assertEquals(value, dimension.value());
    }

    @ParameterizedTest(name = "Линейный размер, мм = {arguments} -> исключение")
    @ValueSource(ints = {-1, 1501})
    @DisplayName("Некорректное значение линейного размера -> исключение")
    void whenDimensionOutOfRange_thenThrowException (int value) {
        assertThatThrownBy(() -> new Dimension(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}