package ru.fastdelivery.domain.common.route;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record Route(GeoPoint departure, GeoPoint destination) {

    private static final int RADIUS = 6372795;
    public int distance () {
        var dLongitude = destination.getLongitude() - departure.getLongitude();
        var sineOfDLongitude = Math.sin(dLongitude);
        var cosineOfDLongitude = Math.cos(dLongitude);
        var numerator = Math.sqrt(
                Math.pow(destination.getCosineOfLatitude() * sineOfDLongitude, 2) +
                        Math.pow(departure.getCosineOfLatitude() * destination.getSineOfLatitude() -
                                departure.getSineOfLatitude() * destination.getCosineOfLatitude() * cosineOfDLongitude, 2)
        );
        var denominator = departure.getSineOfLatitude() * destination.getSineOfLatitude() +
                departure.getCosineOfLatitude() * destination.getCosineOfLatitude() * cosineOfDLongitude;
        var angleDist = Math.atan2(numerator, denominator);
        int result = (int) Math.round(angleDist * RADIUS / 1000);
        log.info("The length of the route is {} kilometers", result);
        return result;
    }
}