package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;

/**
 * Heating - обогрев
 */
@Slf4j
public class GasHeating {
    /**
     * При анализе многих готовых расчетов была выведена средняя цифра:
     * на отопление 10 квадратных метров площади требуется 1 кВт тепла.
     */
    private static final double powerPerSquare = 1_000.0 / 10.0;

    /**
     * Площадь дома
     */
    private static final double houseSquare = 100.0;

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
     * Холожных дней в году
     */
    private static final int coldDayPerYear = /* November */ 15 + 30 + 30 + 30 + 30 + /* April */ 15;

    /**
     * Стоимось газа за куб - гривен
     */
    private static final int costOfGas = 11;

    public static void main(String[] args) {
        double boilerPowerPerHour = houseSquare * powerPerSquare * climateRatio;
        log.info("Boiler power is {} Watt", boilerPowerPerHour);

        double gasPerHour = boilerPowerPerHour / 2.0 / heatCapacity;
        log.info("Gas per hour is {} M3/h", gasPerHour);

        double gasPerYear = gasPerHour * 24 * coldDayPerYear;
        log.info("Gas per year is {} M3", gasPerYear);

        double costPerYear = gasPerYear * costOfGas;
        log.info("Cost per year is {} UAH", costPerYear);

        double costPerMonth = costPerYear / 6.0;
        log.info("Cost per month is {} UAH", costPerMonth);
    }
}
