package ua.in.sz.other;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

@Slf4j
public class OtherMain {

    public static final double p = 1.0e-12;
    public static final double k = 1.0e3;

    // m = 10^-3
    // u = 10^-6
    // n = 10^-9
    // p = 10^-12
    public static void main(String[] args) {
        double r1 = 430; // kOm
        double c1 = 150.0 * p; // pF

        double t = r1 * c1;
        double f = 0.5 / t;

        double tn = t * 1.0e9;
        double fM = f * 1.0e-6;
        DecimalFormat fmt1 = new DecimalFormat("#0.00");

        log.info("{} MHz, {} nano sec",fmt1.format(fM), tn);
    }
}
