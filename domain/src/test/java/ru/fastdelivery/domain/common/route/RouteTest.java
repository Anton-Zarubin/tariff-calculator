package ru.fastdelivery.domain.common.route;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RouteTest {

    @ParameterizedTest(name = "Проверка расчета расстояния по формуле гаверсинусов с модификацией для антиподов")
    @CsvSource({"77.1539, 120.398, 77.1804, 129.55, 226", "77.1539, -139.398, -77.1804, -139.55, 17166",
            "77.1539, -120.398, 77.1804, 129.55, 2333"})
    void testDistance(double lat1, double lon1, double lat2, double lon2, int expected) {

        GeoPointPropertiesProvider provider = Mockito.mock(GeoPointPropertiesProvider.class);
        when(provider.isValidLatitudeValue(lat1)).thenReturn(true);
        when(provider.isValidLongitudeValue(lon1)).thenReturn(true);
        when(provider.isValidLatitudeValue(lat2)).thenReturn(true);
        when(provider.isValidLongitudeValue(lon2)).thenReturn(true);
        GeoPointFactory factory = new GeoPointFactory(provider);

        GeoPoint departure = factory.create(lat1, lon1);
        GeoPoint destination = factory.create(lat2, lon2);
        Route route = new Route(departure, destination);
        int distance = route.distance();
        assertEquals(expected, distance);
    }
}