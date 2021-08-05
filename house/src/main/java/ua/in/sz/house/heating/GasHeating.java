package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.TempCalendar;
import ua.in.sz.house.building.House;

import static ua.in.sz.house.heating.ElectricityHeating.TARGET_TEMPERATURE;
import static ua.in.sz.house.heating.SolidFuelHeating.HOUR_PER_DAY;

/**
 * Heating - обогрев
 *
 * https://www.teplodar.ru/help/articles/detail/raskhod-pellet/
 */
@Slf4j
@AllArgsConstructor(staticName = "of")
public class GasHeating implements Heating {
    private final House house;
    private final TempCalendar calendar;

    /**
     * ratio - коэффициенты
     * climate - климат
     * Для Москвы и Подмосковья полученный результат требуется умножить на 1,2 — 1,5.
     */
    private static final double climateRatio = 1.3;

    /**
     * удельная теплоемкость сгорания природного газа
     */
    private static final double heatCapacity = 9_300;

    /**
     * Стоимось газа за куб - гривен
     */
    private static final int costOfGas = 11;

    @Override
    public double costPerYear() {
        double gasPerYear = 0.0;
        for (TempCalendar.Month month : calendar) {
            double boilerPowerPerHour = house.getHeatLoss(TARGET_TEMPERATURE, month.avgTemperature()) * climateRatio;

            double gasPerHour = boilerPowerPerHour / heatCapacity;
            double gasPerMonth = gasPerHour * HOUR_PER_DAY * month.getDayPerMonth();
            gasPerYear += gasPerMonth;
        }

        return gasPerYear * costOfGas;
    }
}
