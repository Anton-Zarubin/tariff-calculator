package ru.fastdelivery.domain.common.dimensions;

/**
 * Общий класс линейного размера
 *
 * @param value линейный размер в мм
 */
public record Dimension(Integer value) {
    private static final int ROUND_MULTIPLE = 50;

    public Dimension {
        if (!isValidDimension(value)) {
            throw new IllegalArgumentException("Linear dimension cannot be less than 0 mm and more than 1500 mm");
        }
    }

    private static boolean isValidDimension (Integer value) {
        return value >= 0 && value <= 1500;
    }

    public Dimension roundUp() {
        return new Dimension(roundUp(this.value));
    }

    private Integer roundUp(Integer value) {
        return value % ROUND_MULTIPLE == 0 ? value : (value / ROUND_MULTIPLE + 1) * ROUND_MULTIPLE;
    }
}