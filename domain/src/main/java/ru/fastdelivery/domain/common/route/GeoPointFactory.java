package ru.fastdelivery.domain.common.route;

import lombok.RequiredArgsConstructor;

/**
 * Создание точки по географическим координатам с проверками
 */
@RequiredArgsConstructor
public class GeoPointFactory {

    private final GeoPointPropertiesProvider provider;
    private static final double PI = 3.14159265358979;

    public GeoPoint create (double latitude, double longitude) {
        if (!provider.isValidLatitudeValue(latitude)) {
            throw new IllegalArgumentException("Latitude is out of bounds or not set!");
        }
        if (!provider.isValidLongitudeValue(longitude)) {
            throw new IllegalArgumentException("Longitude is out of bounds or not set!");
        }

        return new GeoPoint(getCoordinatesInRadians(latitude), getCoordinatesInRadians(longitude));
    }

    private double getCoordinatesInRadians(double coordinatesInDegrees) {
        return coordinatesInDegrees * PI / 180.;
    }
}