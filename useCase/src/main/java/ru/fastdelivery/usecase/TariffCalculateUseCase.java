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

        return priceByWeight.max(priceByVolume).max(minimalPrice);
    }

    public Price minimalPrice() {
        return priceProvider.minimalPrice();
    }
}
