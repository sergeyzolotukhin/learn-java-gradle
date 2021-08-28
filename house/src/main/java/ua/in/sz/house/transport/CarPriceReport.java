package ua.in.sz.house.transport;

public class CarPriceReport {
    public static String report(CarPrice carPrice) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nTransport price:");

        for (CarPrice.Item price : carPrice.items()) {
            sb.append(String.format(
                    "\n\tThe %-16.16s car will run %5.0f KM, " +
                            "cargo moving %2.0f moved weight %3.0f T " +
                            "cost %6.0f UAH",
                    price.car().getName(), price.distance(),
                    price.forward(), kgToT(price.weight()),
                    price.cost()));
        }

        return sb.toString();
    }

    private static double kgToT(double kg) {
        return kg / 1000.0;
    }
}
