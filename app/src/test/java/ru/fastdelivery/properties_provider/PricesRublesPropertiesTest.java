package ru.fastdelivery.properties_provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.currency.Currency;
import ru.fastdelivery.domain.common.currency.CurrencyFactory;
import ru.fastdelivery.properties.provider.PricesRublesProperties;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PricesRublesPropertiesTest {

    public static final BigDecimal PER_KG = BigDecimal.valueOf(8.4);
    public static final BigDecimal PER_CBM = BigDecimal.valueOf(2096);
    public static final BigDecimal MINIMAL = BigDecimal.valueOf(350);
    public static final String RUB = "RUB";
    final CurrencyFactory currencyFactory = mock(CurrencyFactory.class);
    PricesRublesProperties properties;

    @BeforeEach
    void init(){
        properties = new PricesRublesProperties();
        properties.setCurrencyFactory(currencyFactory);

        properties.setPerKg(PER_KG);
        properties.setPerCBM(PER_CBM);
        properties.setMinimal(MINIMAL);

        var currency = mock(Currency.class);
        when(currency.getCode()).thenReturn(RUB);

        when(currencyFactory.create(RUB)).thenReturn(currency);
    }

    @Test
    void whenCallPricePerKg_thenRequestFromConfig() {
        var actual = properties.costPerKg();

        verify(currencyFactory).create("RUB");
        assertThat(actual.amount()).isEqualByComparingTo(PER_KG);
        assertThat(actual.currency().getCode()).isEqualTo("RUB");
    }

    @Test
    void whenCallPricePerCBM_thenRequestFromConfig() {
        var actual = properties.costPerCBM();

        verify(currencyFactory).create("RUB");
        assertThat(actual.amount()).isEqualByComparingTo(PER_CBM);
        assertThat(actual.currency().getCode()).isEqualTo("RUB");
    }

    @Test
    void whenCallMinimalPrice_thenRequestFromConfig() {
        var actual = properties.minimalPrice();

        verify(currencyFactory).create("RUB");
        assertThat(actual.amount()).isEqualByComparingTo(MINIMAL);
        assertThat(actual.currency().getCode()).isEqualTo("RUB");
    }
}