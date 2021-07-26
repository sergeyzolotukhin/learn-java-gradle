package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.House;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class SolidFuelHeating implements Heating {
    private final House house;

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

    @Override
    public double costPerYear() {
        double boilerPowerPerHour = house.getHeatLoss(23, -20);
        log.debug("Boiler power is {} Watt", boilerPowerPerHour);

        double powerPerYear = boilerPowerPerHour / 2.0 * 24.0 * coldDayPerYear;
        double pelletWidthPerYear = powerPerYear / heatCapacityOfPellet;
        double costPerYear = pelletWidthPerYear * costOfPellet;
        log.debug("Cost per year is {} UAH", costPerYear);

        return costPerYear;
    }
}
