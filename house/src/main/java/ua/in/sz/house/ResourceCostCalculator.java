package ua.in.sz.house;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.TempCalendar;
import ua.in.sz.house.boiler.Resource;
import ua.in.sz.house.building.House;

import java.util.Map;

import static ua.in.sz.house.Main.TARGET_TEMPERATURE;
import static ua.in.sz.house.TempCalendar.HOUR_PER_DAY;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class ResourceCostCalculator {


    private final House house;
    private final TempCalendar calendar;

    private static final Map<Class<? extends Resource>, Double> resourceCost =
            ImmutableMap.<Class<? extends Resource>, Double>builder()
                    .put(Resource.Electricity.class, 2.3 / 1_000.0)
                    .build();

    public double costPerYear() {
        double costPerYear = 0.0;
        for (TempCalendar.Month month : calendar) {
            double tOut = month.avgTemperature();

            Resource resource = house.getBoiler().resourceQuantity(house, TARGET_TEMPERATURE, tOut);
            double cost = resourceCost.get(resource.getClass());

            double powerPerMonth = resource.getQuantity() * HOUR_PER_DAY * month.getDayPerMonth();
            costPerYear += powerPerMonth * cost;
        }
        return costPerYear;
    }
}
