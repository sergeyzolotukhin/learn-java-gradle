package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElectricityHeating {
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
     * Стоимось электро энергии за ватт - гривен
     */
    private static final double costOfElectricity = 2.3 / 1_000.0;

    public static void main(String[] args) {
        double boilerPowerPerHour = houseSquare * powerPerSquare;
        log.info("Boiler power is {} Watt", boilerPowerPerHour);

        double electricityPerYear = boilerPowerPerHour / 2.0 * 24.0 * coldDayPerYear;
        log.info("Electricity per year is {} M3", electricityPerYear);

        double electricityPerMonth = boilerPowerPerHour / 2.0 * 24.0 * 30;
        log.info("Electricity per month is {} KWt", electricityPerMonth / 1000.0);

        double costPerYear = electricityPerYear * costOfElectricity;
        log.info("Cost per year is {} UAH", costPerYear);

        double costPerMonth = costPerYear / 6.0;
        log.info("Cost per month is {} UAH", costPerMonth);
    }
}
