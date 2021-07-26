package ua.in.sz.house.heating;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.House;

/**
 * Heating - обогрев
 *
 * https://www.teplodar.ru/help/articles/detail/raskhod-pellet/
 */
@Slf4j
@AllArgsConstructor(staticName = "of")
public class GasHeating implements Heating {

    private final House house;

    /**
     * ratio - коэффициенты
     * climate - климат
     * Для Москвы и Подмосковья полученный результат требуется умножить на 1,2 — 1,5.
     */
    private static final double climateRatio = 1.3;

    /**
     * удельная теплоемкость сгорания природного газа
     */
    private static final double heatCapacity = 9_300;

    /**
     * Холожных дней в году
     */
    private static final int coldDayPerYear = /* November */ 15 + 30 + 30 + 30 + 30 + /* April */ 15;

    /**
     * Стоимось газа за куб - гривен
     */
    private static final int costOfGas = 11;

    @Override
    public double costPerYear() {
        double boilerPowerPerHour = house.getHeatLoss(23, -20) * climateRatio;
        log.debug("Boiler power is {} Watt", boilerPowerPerHour);

        double gasPerHour = boilerPowerPerHour / 2.0 / heatCapacity;
        log.debug("Gas per hour is {} M3/h", gasPerHour);

        double gasPerYear = gasPerHour * 24 * coldDayPerYear;
        log.debug("Gas per year is {} M3", gasPerYear);

        return gasPerYear * costOfGas;
    }
}
