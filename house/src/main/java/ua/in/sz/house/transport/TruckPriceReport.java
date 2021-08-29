package ua.in.sz.house.transport;

import org.apache.commons.lang3.StringUtils;

public class TruckPriceReport {
    public static String report(TruckPrice truckPrice) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\t Transport price:");

        String title = String.format("\n\t%-16.16s %9.9s %7.7s %9.9s %9.9s",
                "car name", "distance KM", "forward", "weight T", "cost UAH");
        int size = title.length();

        sb.append(StringUtils.rightPad("\n\t=", size, "="));
        sb.append(title);
        sb.append(StringUtils.rightPad("\n\t=", size, "="));

        for (TruckPrice.Item price : truckPrice.items()) {
            sb.append(String.format(
                    "\n\t%-16.16s %9.0f %7.0f %9.0f %9.0f",
                    price.car().getName(), price.distance(), price.forward(), kgToT(price.weight()),price.cost()));
        }

        sb.append(StringUtils.rightPad("\n\t=", size, "="));

        return sb.toString();
    }

    private static double kgToT(double kg) {
        return kg / 1000.0;
    }
}
