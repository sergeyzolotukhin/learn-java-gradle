package ua.in.sz.house.transport;

import lombok.extern.slf4j.Slf4j;
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

    public static double cost(CargoCar car, Order order, House house) {
        DistanceResolver distanceResolver = new DistanceResolver(house.getPlace(), Place.TRAVITA, Place.MOROR_M);

        if (order instanceof PackageOrder) {
            PackageOrder materialPackageOrder = (PackageOrder) order;
            MaterialPackage pack = materialPackageOrder.getPack();
            double requiredPackageCount = materialPackageOrder.quantity();

            double maxPackage = maxPackage(car, pack);
            log.debug("Max package count {} weight {}", maxPackage, maxPackage * pack.getWeight() / 1000.0);

            double travelCount = Math.ceil(requiredPackageCount / maxPackage);
            log.debug("Package count {} travel count {} sum block count {}",
                    requiredPackageCount, travelCount, requiredPackageCount * pack.getCount());

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

    public static double sangCost(CargoCar car, Order order, House house) {
        if (!(order instanceof UnPackageOrder)) {
            return 0;
        }
        UnPackageOrder unPackageOrder = (UnPackageOrder) order;
        double requiredTravelCount = Math.ceil(unPackageOrder.getQuantity() / car.getMaxWeight());
        int workTime = 8;

        final double averageVelocity = 60.0; // Km/h
        final double loadTime = 1; // hours
        final double unloadTime = 1; // hours

        // distance in KM
        DistanceResolver distanceResolver = new DistanceResolver(house.getPlace(), Place.TRAVITA, Place.MOROR_M);
        double comeIn = distanceResolver.comeIn();
        double travel = distanceResolver.travel();
        double comeOut = distanceResolver.comeOut();

        // time in hours
        final double runTime = travel / averageVelocity;
        final double comeInTime = comeIn / averageVelocity;
        final double comeOutTime = comeOut / averageVelocity;

        double forwardTime = loadTime + runTime + unloadTime;
        double cycleTime = loadTime + runTime + unloadTime + runTime;

        double leftTimePerDay = workTime - (comeInTime + forwardTime + comeOutTime);
        double cyclePerDay = Math.floor(leftTimePerDay / cycleTime);
        double fullDays = Math.floor(requiredTravelCount / (cyclePerDay + 1));
        double lastDayCycle = Math.max(requiredTravelCount - fullDays * (cyclePerDay + 1) - 1, 0);

        double forwardTravel = comeIn + travel + comeOut;
        double cycleTravel = comeIn + travel + comeOut + travel;
        double distance = fullDays * (forwardTravel + cyclePerDay * cycleTravel) + lastDayCycle * cycleTravel;

        double forwardCount = fullDays * (1 + cyclePerDay) + lastDayCycle;
        double movedWeight = forwardCount * car.getMaxWeight();

        log.info("Distance has value {} KM cargo moving {} moved weight {}", distance, forwardCount, movedWeight);

        return distance * car.getKmCost();
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
