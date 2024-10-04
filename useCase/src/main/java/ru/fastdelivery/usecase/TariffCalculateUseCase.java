package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    static final int MIN_ROUTE_LENGTH = 450;
    private final PriceProvider priceProvider;

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var volumeAllPackagesCBM = shipment.volumeAllPackages();
        var minimalPrice = priceProvider.minimalPrice();

        Price priceByWeight = priceProvider
                .costPerKg()
                .multiply(weightAllPackagesKg);
        log.info("Price calculated by weight: {}", priceByWeight.amount());

        Price priceByVolume = priceProvider
                .costPerCBM()
                .multiply(volumeAllPackagesCBM);
        log.info("Price calculated by volume: {}", priceByVolume.amount());

        var basePrice = priceByWeight.max(priceByVolume).max(minimalPrice);
        log.info("Base prise is {}", basePrice.amount());

        return  PriceIncludingRoute(shipment.routeLength(), basePrice);
    }

    Price PriceIncludingRoute(int routeLength, Price basePrice) {
        if (routeLength > MIN_ROUTE_LENGTH) {
            var newAmount = basePrice.amount()
                    .multiply(BigDecimal.valueOf((double) routeLength / MIN_ROUTE_LENGTH))
                    .setScale(2, RoundingMode.CEILING);
            basePrice = new Price(newAmount, basePrice.currency());
        }
        log.info("Price including route is {}", basePrice.amount());
        return basePrice;
    }

    public Price minimalPrice() {
        return priceProvider.minimalPrice();
    }
}
