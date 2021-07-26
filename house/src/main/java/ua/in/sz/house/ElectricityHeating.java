package ua.in.sz.house;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.House;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class ElectricityHeating {

    private final House house;

    /**
     * Холожных дней в году
     */
    private static final int coldDayPerYear = /* November */ 15 + 30 + 30 + 30 + 30 + /* April */ 15;

    /**
     * Стоимось электро энергии за ватт - гривен
     */
    private static final double costOfElectricity = 2.3 / 1_000.0;

    public double costPerYear() {
        double boilerPowerPerHour = house.getHeatLoss(23, -20);
        log.debug("Boiler power is {} Watt", boilerPowerPerHour);

        double electricityPerYear = boilerPowerPerHour / 2.0 * 24.0 * coldDayPerYear;
        log.debug("Electricity per year is {} M3", electricityPerYear);

        double electricityPerMonth = boilerPowerPerHour / 2.0 * 24.0 * 30;
        log.debug("Electricity per month is {} KWt", electricityPerMonth / 1000.0);

        double costPerYear = electricityPerYear * costOfElectricity;
        log.debug("Cost per year is {} UAH", costPerYear);

        return costPerYear;
    }
}
