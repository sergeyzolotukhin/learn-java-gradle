package ua.in.sz.house.material.calculator;

import ua.in.sz.house.house.House;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

/**
 * https://vik.by/instruments/glubina-promerzaniya-grunta/ukraina/kiev
 * https://cdelayremont.ru/raschet-betona-na-fundament
 * http://eurobeton.su/beton_news/klassy-i-marki-betona/
 *
 * https://gidroizolyatsiya-fundamenta.ru/blog/raschet-opalubki-dlya-lentochnogo-fundamenta.html
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

        foundation.add(formwork(house));

        return foundation;
    }

    private static final double WOOD_THICKNESS = 50 / 1000.0;
    private static final double WOOD_WIDTH = 150 / 1000.0;
    private static final double WOOD_LENGTH = 2.75;
    private static final double WOOD_VOLUME = WOOD_LENGTH * WOOD_WIDTH * WOOD_THICKNESS;
    private static final double WOOD_DENSITY = 500; // kg / m3 - сосна

    private static Material formwork(House house) {
        Material formwork = new Material(MaterialType.FORMWORK, 1, MaterialUnit.PIECE, 0.0);

        double height = UPPER_OFFSET + DEPTH_OF_FREEZING + LOWER_OFFSET;
        double length = house.getWall().getLength();

        double woodByHeight = Math.ceil(height / WOOD_WIDTH);
        double woodByLength = Math.ceil(length / WOOD_LENGTH);

        double volume = WOOD_VOLUME * woodByHeight * woodByLength;

        Material wood = new Material(MaterialType.WOOD, volume, MaterialUnit.M3, volume * WOOD_DENSITY);
        formwork.add(wood);

        return formwork;
    }
}
