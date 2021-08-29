package ua.in.sz.house.transport;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.shop.MaterialPackage;
import ua.in.sz.house.shop.order.MaterialOrder;
import ua.in.sz.house.shop.order.PackageMaterial;
import ua.in.sz.house.shop.order.UnPackageMaterial;
import ua.in.sz.house.transport.truck.CargoTruck;
import ua.in.sz.house.transport.truck.DumpTruck;
import ua.in.sz.house.transport.truck.Truck;

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
            Truck truck = item.truck();
            Distance distance = item.distance();
            MaterialOrder.Item materialOrder = item.materialOrder();

            double travelCount = travelCount(truck, materialOrder);
            TruckPrice.Item price = makePrice(truck, travelCount, distance);

            result.add(price);
        }

        return new TruckPrice(result);
    }

    // ================================================================================================================
    // private methods
    // ================================================================================================================

    private static TruckPrice.Item makePrice(Truck truck, double travelCount, Distance distance) {
        int workTime = 8;

        final double loadTime = 1; // hours
        final double unloadTime = 1; // hours

        // distance in KM
        double comeIn = distance.comeIn();
        double forward = distance.travel();
        double back = distance.travel();
        double comeOut = distance.comeOut();

        // time in hours
        final double forwardTime = forward / truck.getVelocity();
        final double backTime = back / truck.getVelocity();
        final double comeInTime = comeIn / truck.getVelocity();
        final double comeOutTime = comeOut / truck.getVelocity();

        double leftTimePerDay = workTime - (comeInTime + loadTime + forwardTime + unloadTime + comeOutTime);
        double cyclePerDay = Math.floor(leftTimePerDay / (loadTime + forwardTime + unloadTime + backTime));
        double days = Math.floor(travelCount / (cyclePerDay + 1));
        double lastDayCycle = Math.max(travelCount - days * (cyclePerDay + 1) - 1, 0);

        double totalDistance = days * (comeIn + forward + comeOut)
                + days * cyclePerDay * (comeIn + forward + comeOut + back)
                + lastDayCycle * (comeIn + forward + comeOut + back);

        double forwardCount = days * (1 + cyclePerDay) + lastDayCycle;
        double movedWeight = forwardCount * truck.getMaxWeight();

        return new TruckPrice.Item(truck, totalDistance, forwardCount, movedWeight, totalDistance * truck.getKmCost());
    }

    public static double travelCount(Truck truck, MaterialOrder.Item materialOrder) {
        if (materialOrder instanceof PackageMaterial packageOrder
                && truck instanceof CargoTruck cargoTruck) {
            return packageTravelCount(cargoTruck, packageOrder);
        }

        if (materialOrder instanceof UnPackageMaterial unPackageOrder
                && truck instanceof DumpTruck dumpTruck) {
            return unPackageTravelCount(dumpTruck, unPackageOrder);
        }

        throw new NotImplementedException("Unsupported package type " + materialOrder.getClass());
    }

    private static double packageTravelCount(CargoTruck cargoTruck, PackageMaterial order) {
        double maxPackage = maxPackage(cargoTruck, order.pack());
        return Math.ceil(order.quantity() / maxPackage);
    }

    private static double unPackageTravelCount(DumpTruck truck, UnPackageMaterial order) {
        return Math.ceil(order.quantity() / truck.getMaxWeight());
    }

    private static double maxPackage(CargoTruck truck, MaterialPackage pack) {
        double maxPackagePerWeight = Math.floor(truck.getMaxWeight() / pack.getWeight());
        double maxPackagePerSquare = maxPackagePerSquare(truck, pack);
        return Math.min(maxPackagePerWeight, maxPackagePerSquare);
    }

    private static double maxPackagePerSquare(CargoTruck truck, MaterialPackage pack) {
        double packagePerWidth = Math.floor(truck.getWidth() / pack.getLength());
        double packagePerLength = Math.floor(truck.getLength() / pack.getWidth());
        return packagePerLength * packagePerWidth;
    }
}
