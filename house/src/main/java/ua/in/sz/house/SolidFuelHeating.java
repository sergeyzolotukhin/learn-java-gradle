package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolidFuelHeating {
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
     * Холожных дней в году
     */
    private static final int coldDayPerYear = /* November */ 15 + 30 + 30 + 30 + 30 + /* April */ 15;

    /**
     * Стоимось пелет за килограм - гривен
     */
    private static final double costOfPellet = 3_400.0 / 1_000.0;

    /**
     * теплоемкость пеллет — 5 кВт/кг.
     */
    private static final double heatCapacityOfPellet = 4.73 * 1000.0;

    public static void main(String[] args) {
        double boilerPowerPerHour = houseSquare * powerPerSquare;
        log.debug("Boiler power is {} Watt", boilerPowerPerHour);

        double powerPerYear = boilerPowerPerHour / 2.0 * 24.0 * coldDayPerYear;
        double pelletWidthPerYear = powerPerYear / heatCapacityOfPellet;
        double costPerYear = pelletWidthPerYear * costOfPellet;
        log.debug("Cost per year is {} UAH", costPerYear);

        double costPerMonth = costPerYear / 6.0;
        log.info("Cost per month is {} UAH", costPerMonth);
    }
}
