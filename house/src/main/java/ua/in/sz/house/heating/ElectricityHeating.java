package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.TempCalendar;
import ua.in.sz.house.building.House;

import static ua.in.sz.house.Main.TARGET_TEMPERATURE;
import static ua.in.sz.house.TempCalendar.HOUR_PER_DAY;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class ElectricityHeating implements Heating {


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
            double tOut = month.avgTemperature();
            double powerPerHour = electricityPerHour(tOut);
            double powerPerMonth = powerPerHour * HOUR_PER_DAY * month.getDayPerMonth();
            powerPerYear += powerPerMonth;
        }
        return powerPerYear;
    }

    private double electricityPerHour(double tOut) {
        return house.getHeatLoss(TARGET_TEMPERATURE, tOut);
    }
}
