package ua.in.sz.house.transport;

public class Cars {

    public static CargoCar kamaz_551() {
        return new CargoCar(0, 0, 0, 10_000, 0, 0, 18);
    }

    /**
     * Isuzu NQR75 - 5 000 Kg
     */
    public static CargoCar isuzuNqr75() {
        return new CargoCar(6.0, 2.4, 2.4, 5_000, 275, 275, 13);
    }

    /**
     * DAF CF 65 - 10 000 Kg
     */
    public static CargoCar dafCf65() {
        return new CargoCar(7.2, 2.5, 2.5, 10_000, 370, 370, 18);
    }

    /**
     * DAF XF 95 - 20 000 Kg
     */
    public static CargoCar dafXf95() {
        return new CargoCar(13.6, 2.5, 2.5, 20_000, 620, 620, 30);
    }

}
