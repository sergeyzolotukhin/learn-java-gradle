package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.House;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class ElectricityHeating implements Heating {

    private final House house;

    /**
     * Холожных дней в году
     */
    private static final int coldDayPerYear = /* November */ 15 + 30 + 30 + 30 + 30 + /* April */ 15;

    /**
     * Стоимось электро энергии за ватт - гривен
     */
    private static final double costOfElectricity = 2.3 / 1_000.0;

    @Override
    public double costPerYear() {
        double boilerPowerPerHour = house.getHeatLoss(23, -20);
        double electricityPerYear = boilerPowerPerHour / 2.0 * 24.0 * coldDayPerYear;
        return electricityPerYear * costOfElectricity;
    }
}
