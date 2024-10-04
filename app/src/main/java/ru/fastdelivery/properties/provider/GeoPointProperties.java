package ru.fastdelivery.properties.provider;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.fastdelivery.domain.common.route.GeoPointPropertiesProvider;

/**
 * Настройки диапазона координат из конфига
 */
@Configuration
@Setter
public class GeoPointProperties implements GeoPointPropertiesProvider {
    @Value("${coordinates.latitude.min}")
    private double minLatitude;
    @Value("${coordinates.latitude.max}")
    private double maxLatitude;
    @Value("${coordinates.longitude.min}")
    private double minLongitude;
    @Value("${coordinates.longitude.max}")
    private double maxLongitude;

    @Override
    public boolean isValidLatitudeValue(double latitude) {
        return latitude >= minLatitude && latitude <= maxLatitude;
    }

    @Override
    public boolean isValidLongitudeValue(double longitude) {
        return longitude >= minLongitude && longitude <= maxLongitude;
    }
}