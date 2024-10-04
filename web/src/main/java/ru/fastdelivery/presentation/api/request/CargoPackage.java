package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;

public record CargoPackage(
        @Schema(description = "Вес упаковки, граммы", example = "5633")
        BigInteger weight,
        @Schema(description = "Длина упаковки, мм, от 0 до 1500", example = "386")
        Integer length,
        @Schema(description = "Ширина упаковки, мм, от 0 до 1500", example = "480")
        Integer width,
        @Schema(description = "Высота упаковки, мм, от 0 до 1500", example = "441")
        Integer height
) {
}
