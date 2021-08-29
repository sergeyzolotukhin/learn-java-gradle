package ua.in.sz.house.transport;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.shop.MaterialPackage;
import ua.in.sz.house.shop.order.MaterialOrder;
import ua.in.sz.house.shop.order.PackageMaterial;
import ua.in.sz.house.shop.order.UnPackageMaterial;

import java.util.ArrayList;
import java.util.List;

import static ua.in.sz.house.material.MaterialType.*;

/**
 * http://motor-m.kiev.ua/gryzoperevozki_kiev_do_20_tonn.html
 */
@Slf4j
public class TruckService {
    public static TruckOrder makeOrder(MaterialOrder materialOrder) {
        Distance distance = new Distance(26.8, 26.8, 33.6);

        List<TruckOrder.Item> items = new ArrayList<>();

        items.add(new TruckOrder.Item(Trucks.isuzuNqr75_5t(), distance, materialOrder.get(CEMENT)));
        items.add(new TruckOrder.Item(Trucks.kamaz_5511_10t(), distance, materialOrder.get(SANG)));
        items.add(new TruckOrder.Item(Trucks.dafXf95_20t(), distance, materialOrder.get(BRICK)));

        return new TruckOrder(items);
    }

    public static TruckPrice makePrice(TruckOrder truckOrder) {
        List<TruckPrice.Item> result = new ArrayList<>();

        for (TruckOrder.Item item : truckOrder.items()) {
            CargoTruck car = item.car();
            Distance distance = item.distance();
            MaterialOrder.Item materialOrder = item.materialOrder();

            double travelCount = travelCount(car, materialOrder);
            TruckPrice.Item price = makePrice(car, travelCount, distance);

            result.add(price);
        }

        return new TruckPrice(result);
    }

    // ================================================================================================================
    // private methods
    // ================================================================================================================

    private static TruckPrice.Item makePrice(CargoTruck car, double travelCount, Distance distance) {
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

        return new TruckPrice.Item(car, totalDistance, forwardCount, movedWeight, totalDistance * car.getKmCost());
    }

    public static double travelCount(CargoTruck car, MaterialOrder.Item materialOrder) {
        if (materialOrder instanceof PackageMaterial packageOrder) {
            return packageTravelCount(car, packageOrder);
        }

        if (materialOrder instanceof UnPackageMaterial unPackageOrder) {
            return unPackageTravelCount(car, unPackageOrder);
        }

        throw new NotImplementedException("Unsupported package type " + materialOrder.getClass());
    }

    private static double packageTravelCount(CargoTruck car, PackageMaterial order) {
        double maxPackage = maxPackage(car, order.pack());
        return Math.ceil(order.quantity() / maxPackage);
    }

    private static double unPackageTravelCount(CargoTruck car, UnPackageMaterial order) {
        return Math.ceil(order.quantity() / car.getMaxWeight());
    }

    private static double maxPackage(CargoTruck car, MaterialPackage pack) {
        double maxPackagePerWeight = Math.floor(car.getMaxWeight() / pack.getWeight());
        double maxPackagePerSquare = maxPackagePerSquare(car, pack);
        return Math.min(maxPackagePerWeight, maxPackagePerSquare);
    }

    private static double maxPackagePerSquare(CargoTruck car, MaterialPackage pack) {
        double packagePerWidth = Math.floor(car.getWidth() / pack.getLength());
        double packagePerLength = Math.floor(car.getLength() / pack.getWidth());
        return packagePerLength * packagePerWidth;
    }
}
