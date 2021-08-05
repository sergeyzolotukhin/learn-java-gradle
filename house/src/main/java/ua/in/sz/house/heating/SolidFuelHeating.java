package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.TempCalendar;
import ua.in.sz.house.building.House;

import static ua.in.sz.house.Main.TARGET_TEMPERATURE;
import static ua.in.sz.house.TempCalendar.HOUR_PER_DAY;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class SolidFuelHeating implements Heating {
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
        double pelletWidthPerYear = pelletWidthPerYear();
        return pelletWidthPerYear * costOfPellet;
    }

    private double pelletWidthPerYear() {
        double pelletPerYear = 0.0;
        for (TempCalendar.Month month : calendar) {
            double pelletPerHour = pelletPerHour(month);
            double pelletPerDay = pelletPerHour * HOUR_PER_DAY * month.getDayPerMonth();
            pelletPerYear += pelletPerDay;
        }

        return pelletPerYear;
    }

    private double pelletPerHour(TempCalendar.Month month) {
        return house.getHeatLoss(TARGET_TEMPERATURE, month.avgTemperature()) / heatCapacityOfPellet;
    }
}
