package ru.fastdelivery.properties_provider;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.fastdelivery.properties.provider.GeoPointProperties;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeoPointPropertiesTest {
    private GeoPointProperties properties;

    @BeforeEach
    void setUp() {
        properties = new GeoPointProperties();
        properties.setMinLatitude(45.0);
        properties.setMaxLatitude(65.0);
        properties.setMinLongitude(30.0);
        properties.setMaxLongitude(96.0);
    }

    @AfterEach
    void tearDown() {
        properties = null;
    }

    @ParameterizedTest(name = "Широта, градусов = {arguments} -> в допустимых пределах")
    @ValueSource(doubles = {45.0, 54.984, 64.999})
    void whenValidLatitudeValue_thenTrue(double parameter) {
        assertTrue(properties.isValidLatitudeValue(parameter));
    }

    @ParameterizedTest(name = "Широта, градусов = {arguments} -> вне зоны оказания услуг")
    @ValueSource(doubles = {43.999, 65.001})
    void whenLatitudeOutOfBounds_thenFalse(double parameter) {
        assertFalse(properties.isValidLatitudeValue(parameter));
    }

    @ParameterizedTest(name = "Долгота, градусов = {arguments} -> в допустимых пределах")
    @ValueSource(doubles = {31.526, 65.213, 96.000})
    void whenValidLongitudeValue_thenTrue(double parameter) {
        assertTrue(properties.isValidLongitudeValue(parameter));
    }

    @ParameterizedTest(name = "Долгота, градусов = {arguments} -> вне зоны оказания услуг")
    @ValueSource(doubles = {29.999, 96.001})
    void whenLongitudeOutOfBounds_thenFalse(double parameter) {
        assertFalse(properties.isValidLongitudeValue(parameter));
    }
}