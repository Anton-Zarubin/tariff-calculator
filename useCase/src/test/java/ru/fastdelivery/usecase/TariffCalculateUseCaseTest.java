package ru.fastdelivery.usecase;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.domain.common.dimensions.Dimension;
import ru.fastdelivery.domain.common.dimensions.Volume;
import ru.fastdelivery.domain.common.price.Price;
import ru.fastdelivery.domain.common.weight.Weight;
import ru.fastdelivery.domain.delivery.pack.Pack;
import ru.fastdelivery.domain.delivery.shipment.Shipment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TariffCalculateUseCaseTest {

    final PriceProvider priceProvider = mock(PriceProvider.class);
    final Currency currency = new CurrencyFactory(code -> true).create("RUB");

    final TariffCalculateUseCase tariffCalculateUseCase = new TariffCalculateUseCase(priceProvider);

    @ParameterizedTest(name ="Расчет стоимости доставки -> успешно")
    @CsvSource({"1200, 100, 100, 100, 120", "14400, 1_000, 1_000, 1_000, 1850"})
    void whenCalculatePrice_thenSuccess(BigInteger weight, Integer length, Integer width, Integer height, BigDecimal expected) {
        var minimalPrice = new Price(BigDecimal.TEN, currency);
        var pricePerKg = new Price(BigDecimal.valueOf(100), currency);
        var pricePerCBM = new Price(BigDecimal.valueOf(1850), currency);

        when(priceProvider.minimalPrice()).thenReturn(minimalPrice);
        when(priceProvider.costPerKg()).thenReturn(pricePerKg);
        when(priceProvider.costPerCBM()).thenReturn(pricePerCBM);

        var shipment = new Shipment(List.of(new Pack(new Weight(weight),
                new Volume(new Dimension(length), new Dimension(width), new Dimension(height)))),
                new CurrencyFactory(code -> true).create("RUB"));
        var expectedPrice = new Price(expected, currency);

        var actualPrice = tariffCalculateUseCase.calc(shipment);

        assertThat(actualPrice).usingRecursiveComparison()
                .withComparatorForType(BigDecimalComparator.BIG_DECIMAL_COMPARATOR, BigDecimal.class)
                .isEqualTo(expectedPrice);
    }

    @Test
    @DisplayName("Получение минимальной стоимости -> успешно")
    void whenMinimalPrice_thenSuccess() {
        BigDecimal minimalValue = BigDecimal.TEN;
        var minimalPrice = new Price(minimalValue, currency);
        when(priceProvider.minimalPrice()).thenReturn(minimalPrice);

        var actual = tariffCalculateUseCase.minimalPrice();

        assertThat(actual).isEqualTo(minimalPrice);
    }
}