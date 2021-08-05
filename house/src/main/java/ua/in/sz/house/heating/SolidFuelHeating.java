package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.TempCalendar;
import ua.in.sz.house.building.House;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class SolidFuelHeating implements Heating {
    public static final double HOUR_PER_DAY = 24.0;

    private final House house;
    private final TempCalendar calendar;

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
        double powerPerYear = 0.0;
        for (TempCalendar.Month month : calendar) {
            double powerPerHour = house.getHeatLoss(23, month.avgTemperature());
            double powerPerDay = powerPerHour * HOUR_PER_DAY * month.getDayPerMonth();
            powerPerYear += powerPerDay;
        }

        double pelletWidthPerYear = powerPerYear / heatCapacityOfPellet;
        return pelletWidthPerYear * costOfPellet;
    }
}
