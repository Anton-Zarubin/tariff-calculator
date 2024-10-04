package ru.fastdelivery.domain.delivery.shipment;

import lombok.extern.slf4j.Slf4j;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.dimensions.Volume;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.util.List;

/**
 * @param packages упаковки в грузе
 * @param currency валюта объявленная для груза
 */

@Slf4j
public record Shipment(
        List<Pack> packages,
        Currency currency
) {
    public Weight weightAllPackages() {
        Weight totalWeight = packages.stream()
                .map(Pack::weight)
                .reduce(Weight.zero(), Weight::add);
        log.info("Cargo weight is {} grams", totalWeight.weightGrams());
        return totalWeight;
    }

    public BigDecimal volumeAllPackages() {
        BigDecimal totalVolume = packages.stream()
                .map(Pack::volume)
                .map(Volume::getVolume)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("Cargo volume is {} cbm", totalVolume);
        return totalVolume;
    }
}
