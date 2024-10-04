package ru.fastdelivery.domain.common.route;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Общий класс географических координат точки
 */

@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class GeoPoint {
    double latitude;
    double longitude;

    double getSineOfLatitude() { return Math.sin(latitude); }

    double getCosineOfLatitude() { return Math.cos(latitude); }
}