package ua.in.sz.house.transport;

public class Trucks {

    public static CargoTruck kamaz_5511_10t() {
        return new CargoTruck("KAMAZ 5511 - 10t",0, 0, 0, 10_000, 0, 0, 18, 60);
    }

    /**
     * Isuzu NQR75 - 5 000 Kg
     */
    public static CargoTruck isuzuNqr75_5t() {
        return new CargoTruck("Isuzu NQR75 - 5t",6.0, 2.4, 2.4, 5_000, 275, 275, 13, 60);
    }

    /**
     * DAF CF 65 - 10 000 Kg
     */
    public static CargoTruck dafCf65_10t() {
        return new CargoTruck("DAF CF 65 - 10t",7.2, 2.5, 2.5, 10_000, 370, 370, 18, 60);
    }

    /**
     * DAF XF 95 - 20 000 Kg
     */
    public static CargoTruck dafXf95_20t() {
        return new CargoTruck("DAF XF 95 - 20t",13.6, 2.5, 2.5, 20_000, 620, 620, 30, 60);
    }

}
