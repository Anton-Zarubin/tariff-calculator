package ru.fastdelivery.domain.common.dimensions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Объём упаковки, куб м
 *
 * @param length    - длина, мм
 * @param width     - ширина, мм
 * @param height    - высота, мм
 */
public record Volume(Dimension length, Dimension width, Dimension height) {
    private static final BigDecimal CUBIC_MM_IN_CBM = BigDecimal.valueOf(1_000_000_000);

    public BigDecimal getVolume() {
        return new BigDecimal(length.roundUp().value() * width.roundUp().value() * height.roundUp().value())
                .divide(CUBIC_MM_IN_CBM, 4, RoundingMode.HALF_UP);
    }
}