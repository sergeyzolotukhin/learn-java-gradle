package ua.in.sz.house.material.calculator;

import ua.in.sz.house.house.House;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

/**
 * https://vik.by/instruments/glubina-promerzaniya-grunta/ukraina/kiev
 * https://cdelayremont.ru/raschet-betona-na-fundament
 * http://eurobeton.su/beton_news/klassy-i-marki-betona/
 */
public class FoundationCalculator {
    private static final double DEPTH_OF_FREEZING = 1.18;
    private static final double UPPER_OFFSET = 0.3;
    private static final double LOWER_OFFSET = 0.3;
    private static final double SIDE_OFFSET = 0.1;

    // B22.5 - M300 -> cement M400
    private static final double WATER_CEMENT_FACTOR = 0.53;

    // gravel max size 25 mm
    private static final double SANG_FACTOR = 0.46; // коэффициент
    // concrete - бетон
    private static final double WATER_PER_CONCRETE_M3 = 195; // L - литр
    private static final double CEMENT_M400_DENSITY = 3000; // kg / m3
    private static final double SANG_DENSITY = 1500; // kg / m3
    private static final double GRAVEL_DENSITY = 2700; // kg / m3
    private static final double L_TO_M3 = 1 / 1000.0;

    public static Material calculate(House house) {
        Material foundation = new Material(MaterialType.FOUNDATION, 1, MaterialUnit.PIECE, 0.0);

        double height = UPPER_OFFSET + DEPTH_OF_FREEZING + LOWER_OFFSET;
        double width = SIDE_OFFSET + house.getWallWidth() + SIDE_OFFSET;
        double length = house.getWall().getLength();
        double volume = height * width * length; // m3

        // per 1 m3 concrete
        double cementWeight = WATER_PER_CONCRETE_M3 / WATER_CEMENT_FACTOR; // kg
        double fillerVolume = 1 - (cementWeight / CEMENT_M400_DENSITY) - WATER_PER_CONCRETE_M3 * L_TO_M3; // m3
        double sangVolume = fillerVolume * SANG_FACTOR; // kg
        double gravelVolume = fillerVolume - sangVolume;

        // total materials
        double totalSang = sangVolume * volume * SANG_DENSITY; // kg
        double totalGravel = gravelVolume * volume * GRAVEL_DENSITY; // kg
        double totalCement = cementWeight * volume; // kg

        foundation.add(new Material(MaterialType.SANG, totalSang, MaterialUnit.KG, totalSang));
        foundation.add(new Material(MaterialType.GRAVEL, totalGravel, MaterialUnit.KG, totalGravel));
        foundation.add(new Material(MaterialType.CEMENT, totalCement, MaterialUnit.KG, totalCement));

        return foundation;
    }
}
