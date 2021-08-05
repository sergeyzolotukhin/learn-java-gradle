package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.TempCalendar;
import ua.in.sz.house.building.House;

import static ua.in.sz.house.Main.TARGET_TEMPERATURE;
import static ua.in.sz.house.TempCalendar.HOUR_PER_DAY;

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
        double gasPerYear = gasPerYear();

        return gasPerYear * costOfGas;
    }

    private double gasPerYear() {
        double gasPerYear = 0.0;
        for (TempCalendar.Month month : calendar) {
            double gasPerHour = gasPerHour(month.avgTemperature());
            double gasPerMonth = gasPerHour * HOUR_PER_DAY * month.getDayPerMonth();
            gasPerYear += gasPerMonth;
        }
        return gasPerYear;
    }

    private double gasPerHour(double tOut) {
        double boilerPowerPerHour = house.getHeatLoss(TARGET_TEMPERATURE, tOut) * climateRatio;
        return boilerPowerPerHour / heatCapacity;
    }
}
