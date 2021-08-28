package ua.in.sz.house.transport;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.shop.MaterialPackage;
import ua.in.sz.house.shop.order.Order;
import ua.in.sz.house.shop.order.PackageOrder;
import ua.in.sz.house.shop.order.UnPackageOrder;

/**
 * http://motor-m.kiev.ua/gryzoperevozki_kiev_do_20_tonn.html
 */
@Slf4j
public class CarDepot {
    public static double cost(CargoCar car, Order order) {
        Distance distance = new Distance(26.8, 26.8, 33.6);
        return cost(car, travelCount(car, order), distance);
    }

    public static double cost(CargoCar car, double travelCount, Distance distance) {
        int workTime = 8;

        final double loadTime = 1; // hours
        final double unloadTime = 1; // hours

        // distance in KM
        double comeIn = distance.comeIn();
        double forward = distance.travel();
        double back = distance.travel();
        double comeOut = distance.comeOut();

        // time in hours
        final double forwardTime = forward / car.getVelocity();
        final double backTime = back / car.getVelocity();
        final double comeInTime = comeIn / car.getVelocity();
        final double comeOutTime = comeOut / car.getVelocity();

        double leftTimePerDay = workTime - (comeInTime + loadTime + forwardTime + unloadTime + comeOutTime);
        double cyclePerDay = Math.floor(leftTimePerDay / (loadTime + forwardTime + unloadTime + backTime));
        double days = Math.floor(travelCount / (cyclePerDay + 1));
        double lastDayCycle = Math.max(travelCount - days * (cyclePerDay + 1) - 1, 0);

        double totalDistance = days * (comeIn + forward + comeOut)
                + days * cyclePerDay * (comeIn + forward + comeOut + back)
                + lastDayCycle * (comeIn + forward + comeOut + back);

        double forwardCount = days * (1 + cyclePerDay) + lastDayCycle;
        double movedWeight = forwardCount * car.getMaxWeight();

        log.info("Distance has value {} KM cargo moving {} moved weight {}", totalDistance, forwardCount, movedWeight);

        return totalDistance * car.getKmCost();
    }

    public static double travelCount(CargoCar car, Order order) {
        if (order instanceof PackageOrder packageOrder) {
            return packageTravelCount(car, packageOrder);
        }

        if (order instanceof UnPackageOrder unPackageOrder) {
            return unPackageTravelCount(car, unPackageOrder);
        }

        throw new NotImplementedException("Unsupported package type " + order.getClass());
    }

    private static double packageTravelCount(CargoCar car, PackageOrder order) {
        double maxPackage = maxPackage(car, order.getPack());
        return Math.ceil(order.quantity() / maxPackage);
    }

    private static double unPackageTravelCount(CargoCar car, UnPackageOrder order) {
        return Math.ceil(order.getQuantity() / car.getMaxWeight());
    }

    private static double maxPackage(CargoCar car, MaterialPackage pack) {
        double maxPackagePerWeight = Math.floor(car.getMaxWeight() / pack.getWeight());
        double maxPackagePerSquare = maxPackagePerSquare(car, pack);
        return Math.min(maxPackagePerWeight, maxPackagePerSquare);
    }

    private static double maxPackagePerSquare(CargoCar car, MaterialPackage pack) {
        double packagePerWidth = Math.floor(car.getWidth() / pack.getLength());
        double packagePerLength = Math.floor(car.getLength() / pack.getWidth());
        return packagePerLength * packagePerWidth;
    }
}
