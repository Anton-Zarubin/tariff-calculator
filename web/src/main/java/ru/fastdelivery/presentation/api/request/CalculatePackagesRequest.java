package ru.fastdelivery.presentation.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Данные для расчета стоимости доставки")
public record CalculatePackagesRequest(
        @Schema(description = "Список упаковок отправления",
                example = "[{\"weight\": 5184, \"length\": 531, \"width\": 304, \"height\": 339}]")
        @NotNull
        @NotEmpty
        List<CargoPackage> packages,

        @Schema(description = "Трехбуквенный код валюты", example = "RUB")
        @NotNull
        String currencyCode,

        @Schema(description = "Координаты пункта отправления (широта/долгота)",
                example = "{\"latitude\" : 55.398660, \"longitude\" : 55.027532}")
        @NotNull
        RoutePoint departure,

        @Schema(description = "Координаты пункта назначения (широта/долгота)",
                example = "{\"latitude\" : 55.398660, \"longitude\" : 55.027532}")
        @NotNull
        RoutePoint destination
) {
}
