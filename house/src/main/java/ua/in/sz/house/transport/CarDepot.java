package ua.in.sz.house.transport;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import ua.in.sz.house.house.House;
import ua.in.sz.house.shop.MaterialPackage;
import ua.in.sz.house.shop.order.Order;
import ua.in.sz.house.shop.order.PackageOrder;
import ua.in.sz.house.shop.order.UnPackageOrder;

/**
 * http://motor-m.kiev.ua/gryzoperevozki_kiev_do_20_tonn.html
 */
@Slf4j
public class CarDepot {
    public static double cost(CargoCar car, Order order, Place housePlace) {
         return cost(car, housePlace, travelCount(car, order));
    }

    public static double cost(CargoCar car, Place housePlace, double travelCount) {
        int workTime = 8;

        final double loadTime = 1; // hours
        final double unloadTime = 1; // hours

        DistanceResolver distance = new DistanceResolver(housePlace, Place.TRAVITA, Place.MOROR_M);

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

    // ================================================================================================================

    public static double deprecateCost(CargoCar car, Order order, House house) {
        DistanceResolver distanceResolver = new DistanceResolver(house.getPlace(), Place.TRAVITA, Place.MOROR_M);

        if (order instanceof PackageOrder) {
            PackageOrder materialPackageOrder = (PackageOrder) order;

            double travelCount = packageTravelCount(car, materialPackageOrder);

            double averageVelocity = 60.0; // Km/h
            double loadTime = 1; // hours
            double unloadTime = 1; // hours

            double totalTime = 0;
            double totalDistance = 0;
            double comeInCount = 0;

            double runTime = distanceResolver.travel() / averageVelocity;
            double comeInTime = distanceResolver.comeIn() / averageVelocity;
            double comeOutTime = distanceResolver.comeOut() / averageVelocity;
            double travelCargoTime = loadTime + runTime + unloadTime;

            double leftTravel = travelCount;
            while (leftTravel > 0) {
                totalTime += comeInTime;
                totalDistance += distanceResolver.comeIn();
                comeInCount++;
                log.debug("come in time {} min", String.format("%.0f", comeInTime * 60));

                double leftWorkTime = 9.0;
                double workTime = 0.0;
                leftWorkTime -= comeInTime;
                workTime += comeInTime;
                while (leftWorkTime > comeOutTime) {
                    if (leftWorkTime < travelCargoTime) {
                        break;
                    }

                    // do travel cargo from stock to house
                    leftWorkTime -= travelCargoTime;
                    workTime += travelCargoTime;
                    totalTime = totalTime + travelCargoTime;
                    totalDistance += distanceResolver.travel();
                    leftTravel--;
                    log.debug("travel cargo time {} hours {} min",
                            String.format("%.0f", travelCargoTime),
                            String.format("%.0f", (travelCargoTime - Math.floor(travelCargoTime)) * 60));

                    if (leftWorkTime < runTime + travelCargoTime || leftTravel <= 0.0) {
                        break;
                    }

                    leftWorkTime -= runTime;
                    workTime += runTime;
                    totalDistance += distanceResolver.travel();
                    totalTime += runTime;
                    log.debug("travel to load time {} min",
                            String.format("%.0f", runTime * 60));
                }
                workTime += comeOutTime;

                totalTime += comeOutTime;
                totalDistance += distanceResolver.comeOut();
                log.debug("come out time {} min, work time {} hours", String.format("%.0f", comeOutTime * 60), String.format("%.0f", workTime));
            }

            double totalCost = comeInCount * car.getComeInCost()
                    + Math.ceil(totalTime) * car.getHourCost()
                    + Math.ceil(totalDistance) * car.getKmCost();

            log.debug("Total time to work {} hours, total distance {} km, come in count {}. Cost {} UAH",
                    Math.ceil(totalTime), Math.ceil(totalDistance), comeInCount, Math.ceil(totalCost));

            return totalCost;
        } else {
            return 0.0;
        }

    }
}
