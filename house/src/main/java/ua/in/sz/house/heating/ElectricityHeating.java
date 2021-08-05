package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.TempCalendar;
import ua.in.sz.house.building.House;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class ElectricityHeating implements Heating {

    public static final double HOUR_PER_DAY = 24.0;
    public static final double TARGET_TEMPERATURE = 23.0;

    private final House house;
    private final TempCalendar calendar;

    /**
     * Стоимось электро энергии за ватт - гривен
     */
    private static final double costOfElectricity = 2.3 / 1_000.0;

    @Override
    public double costPerYear() {
        return powerPerYear() * costOfElectricity;
    }

    private double powerPerYear() {
        double powerPerYear = 0.0;
        for (TempCalendar.Month month : calendar) {
            double powerPerHour = house.getHeatLoss(TARGET_TEMPERATURE, month.avgTemperature());
            double powerPerMonth = powerPerHour * HOUR_PER_DAY * month.getDayPerMonth();
            powerPerYear += powerPerMonth;
        }
        return powerPerYear;
    }
}
