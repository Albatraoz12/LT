package se.dedev.filetools.se.dedev.Pojo;

import java.math.BigDecimal;

public class InBetalningsTjanstenPayment {
    private String reference;
    private BigDecimal amount;

    public InBetalningsTjanstenPayment(BigDecimal amount, String reference) {
        setAmount(amount);
        setReference(reference);
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void readPaymentsInfo() {
        System.out.println(amount + " "+reference);
    }
}