package se.lumera.example.solution;

import java.math.BigDecimal;
import java.util.Date;

public class SolutionMain {
    public static void main(String[] args) {
        Hello h = new Hello();
        h.sayHello();

        PayTest pt = new PayTest();
        pt.startPaymentBundle("d", new Date(123), "s");
        BigDecimal bd = new BigDecimal(0.256);
        pt.payment(bd, "Kost");
        pt.endPaymentBundle();
    }
}