package se.dedev.filetools.se.dedev.Pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InBetalningsTjanstenData {
     private String accountNumber, currency;
     private Date paymentDate;
     private List<InBetalningsTjanstenPayment> payments = new ArrayList<InBetalningsTjanstenPayment>();

     private String slutpost;

     public void setAccountNumber(String accountNumber) {
         this.accountNumber = accountNumber;
     }
    
     public void setSlutpost(String slutpost) {
         this.slutpost = slutpost;
     }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void addToPayments(InBetalningsTjanstenPayment payment) {
        payments.add(payment);
    }
    

    public List<InBetalningsTjanstenPayment> getPayments() {
        return payments;
    }

    
    public void readOppeningPost() {
        System.out.println(accountNumber + " " + currency + " " + paymentDate);
    }


    public void readSlutPost() {
        System.out.println(slutpost);
    }
    
}
