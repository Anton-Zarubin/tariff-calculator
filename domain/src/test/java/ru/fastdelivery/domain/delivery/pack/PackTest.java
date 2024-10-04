package ru.fastdelivery.domain.delivery.pack;

import org.junit.jupiter.api.Test;
import ru.fastdelivery.domain.common.dimensions.Dimension;
import ru.fastdelivery.domain.common.dimensions.Volume;
import ru.fastdelivery.domain.common.weight.Weight;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PackTest {

    @Test
    void whenWeightMoreThanMaxWeight_thenThrowException() {
        var weight = new Weight(BigInteger.valueOf(150_001));
        var volume = new Volume(new Dimension(100), new Dimension(100), new Dimension(100));
        assertThatThrownBy(() -> new Pack(weight, volume))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWeightLessThanMaxWeight_thenObjectCreated() {
        var actual = new Pack(new Weight(BigInteger.valueOf(1_000)),
                new Volume(new Dimension(100), new Dimension(100), new Dimension(100)));
        assertThat(actual.weight()).isEqualTo(new Weight(BigInteger.valueOf(1_000)));
    }
}