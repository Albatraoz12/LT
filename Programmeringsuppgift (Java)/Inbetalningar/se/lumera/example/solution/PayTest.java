package se.lumera.example.solution;

import java.math.BigDecimal;
import java.util.Date;

import se.lumera.example.payments.PaymentReceiver;

public class PayTest implements PaymentReceiver {

    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {
        // TODO Auto-generated method stub
        System.out.println("startPaymentBundle");
        //throw new UnsupportedOperationException("Unimplemented method 'startPaymentBundle'");
    }

    @Override
    public void payment(BigDecimal amount, String reference) {
        // TODO Auto-generated method stu
        System.out.println("ammount is " +amount + " ref is " +reference);
        //throw new UnsupportedOperationException("Unimplemented method 'payment'");
    }

    @Override
    public void endPaymentBundle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endPaymentBundle'");
    }
    
}
