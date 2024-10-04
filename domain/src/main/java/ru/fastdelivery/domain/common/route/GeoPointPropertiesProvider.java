package ru.fastdelivery.domain.common.route;

public interface GeoPointPropertiesProvider {

    /**
     * @param latitude широта точки в градусах
     * @return  находится ли указанная широта в допустимых пределах
     */
    boolean isValidLatitudeValue(double latitude);

    /**
     * @param longitude долгота точки в градусах
     * @return  находится ли указанная долгота в допустимых пределах
     */
    boolean isValidLongitudeValue(double longitude);
}