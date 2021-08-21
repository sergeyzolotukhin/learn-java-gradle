package ua.in.sz.house.cost;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.Resource;
import ua.in.sz.house.building.House;

import java.util.Map;

import static ua.in.sz.house.Main.TARGET_TEMPERATURE;
import static ua.in.sz.house.cost.TemperatureCalendar.HOUR_PER_DAY;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class SupportCostCalculator {


    private final House house;
    private final TemperatureCalendar calendar = TemperatureCalendar.of();

    private static final Map<Class<? extends Resource>, Double> resourceCost =
            ImmutableMap.<Class<? extends Resource>, Double>builder()
                    .put(Resource.Electricity.class, 1.68 / 1_000.0)
                    .put(Resource.Pellet.class, 3_400.0 / 1_000.0)
                    .put(Resource.Gas.class, 11.0)
                    .build();

    public double costPerYear() {
        double costPerYear = 0.0;
        for (TemperatureCalendar.Month month : calendar) {
            double tOut = month.avgTemperature();

            Resource resource = house.getBoiler().resourceQuantity(house, TARGET_TEMPERATURE, tOut);
            double cost = resourceCost.get(resource.getClass());

            double powerPerMonth = resource.getQuantity() * HOUR_PER_DAY * month.getDayPerMonth();
            costPerYear += powerPerMonth * cost;
        }
        return costPerYear;
    }
}
