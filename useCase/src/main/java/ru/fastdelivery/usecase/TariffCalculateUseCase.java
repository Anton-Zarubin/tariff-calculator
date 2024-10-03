package ru.fastdelivery.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import javax.inject.Named;

@Slf4j
@Named
@RequiredArgsConstructor
public class TariffCalculateUseCase {
    private final WeightPriceProvider weightPriceProvider;

    public Price calc(Shipment shipment) {
        var weightAllPackagesKg = shipment.weightAllPackages().kilograms();
        var minimalPrice = weightPriceProvider.minimalPrice();

        Price priceByWeight = weightPriceProvider
                .costPerKg()
                .multiply(weightAllPackagesKg);
        log.info("Price calculated by weight: {}", priceByWeight.amount());
        return priceByWeight.max(minimalPrice);
    }

    public Price minimalPrice() {
        return weightPriceProvider.minimalPrice();
    }
}
