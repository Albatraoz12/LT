package se.dedev.filetools.se.dedev.Pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BetalningsServiceData {
    private String accountNumber, currency;
    private Date paymentDate;
    private List<BetalningsServicePayment> payments = new ArrayList<BetalningsServicePayment>();

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void readOppeningPost() {
        System.out.println(accountNumber + " " + currency + " " + paymentDate);

    }

    public void addToPayments(BetalningsServicePayment payment) {
        payments.add(payment);  
    }
    
    public List<BetalningsServicePayment> getPayments() {
        return payments;
    }

    public String toString() {
        String start = accountNumber + " " + currency + " " + paymentDate;
        return start + "\n" +"payments number:" +payments.size();
    }
}
