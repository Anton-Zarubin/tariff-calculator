package ru.fastdelivery.domain.delivery.shipment;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.dimensions.Dimension;
import ru.fastdelivery.domain.common.dimensions.Volume;
import ru.fastdelivery.domain.common.route.Route;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    private static Shipment shipment;

    @BeforeAll
    static void setUp() {
        var weight1 = new Weight(BigInteger.TEN);
        var weight2 = new Weight(BigInteger.ONE);
        var volume1 = new Volume(new Dimension(531), new Dimension(304), new Dimension(339));
        var volume2 = new Volume(new Dimension(325), new Dimension(310), new Dimension(450));

        var packages = List.of(new Pack(weight1, volume1), new Pack(weight2, volume2));
        var route = Mockito.mock(Route.class);
        shipment = new Shipment(packages, new CurrencyFactory(code -> true).create("RUB"), route);
    }

    @AfterAll
    static void tearDown () {
        shipment = null;
    }

    @Test
    void whenSummarizingWeightOfAllPackages_thenReturnSum() {
        var massOfShipment = shipment.weightAllPackages();

        assertThat(massOfShipment.weightGrams()).isEqualByComparingTo(BigInteger.valueOf(11));
    }

    @Test
    void whenSummarizingVolumeOfAllPackages_thenReturnSum() {
        var volumeOfShipment = shipment.volumeAllPackages();

        assertThat(volumeOfShipment).isEqualByComparingTo(new BigDecimal("0.1225"));
    }
}