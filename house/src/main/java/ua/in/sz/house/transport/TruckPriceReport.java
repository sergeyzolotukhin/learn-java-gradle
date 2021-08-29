package ua.in.sz.house.transport;

import org.apache.commons.lang3.StringUtils;

public class TruckPriceReport {
    public static String report(TruckPrice truckPrice) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n\t Transport price:");

        String title = String.format("\n\t%-16.16s %9.9s %7.7s %9.9s %9.9s %10.10s",
                "truck name", "distance KM", "forward", "weight T", "cost UAH", "material");
        int size = title.length();

        sb.append(StringUtils.rightPad("\n\t=", size, "="));
        sb.append(title);
        sb.append(StringUtils.rightPad("\n\t=", size, "="));

        double totalCost = 0.0;
        for (TruckPrice.Item price : truckPrice.items()) {
            sb.append(String.format(
                    "\n\t%-16.16s %9.0f %7.0f %9.0f %9.0f %10.10s",
                    price.truck().name(),
                    price.distance(),
                    price.forward(),
                    kgToT(price.weight()),
                    price.cost(),
                    price.materialType().getName()));

            totalCost += price.cost();
        }

        sb.append(StringUtils.rightPad("\n\t=", size, "="));
        sb.append(String.format("\n\t%-16.16s %9.9s %7.7s %9.9s %9.0f %10.10s",
                StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, Math.ceil(totalCost), StringUtils.EMPTY));

        return sb.toString();
    }

    private static double kgToT(double kg) {
        return kg / 1000.0;
    }
}
