package se.dedev.filetools.Controllers;

import se.dedev.filetools.se.dedev.Pojo.BetalningsServiceData;
import se.dedev.filetools.se.dedev.Pojo.BetalningsServicePayment;
import se.dedev.filetools.se.dedev.Pojo.InBetalningsTjanstenData;
import se.dedev.filetools.se.dedev.Pojo.InBetalningsTjanstenPayment;

public class Treatment {
    private BetalningsServiceData betalningsServiceData;
    private InBetalningsTjanstenData inBetalningsTjanstenData;

    public Treatment(BetalningsServiceData betalningsServiceData) {
        this.betalningsServiceData = betalningsServiceData;
        info();
        System.out.println("Betalningsdata har sparats!");
    }

    public Treatment(InBetalningsTjanstenData inBetalningsTjanstenData) {
        this.inBetalningsTjanstenData = inBetalningsTjanstenData;
        inBetalningsInfo();
        System.out.println("Betalningsdata har sparats!");
    }

    private void info() {
        betalningsServiceData.readOppeningPost();
        for (BetalningsServicePayment payment : betalningsServiceData.getPayments()) {
            payment.readPaymentsInfo();
        }

    }
    
      private void inBetalningsInfo() {
        inBetalningsTjanstenData.readOppeningPost();
        for (InBetalningsTjanstenPayment payment : inBetalningsTjanstenData.getPayments()) {
            payment.readPaymentsInfo();
        }
        inBetalningsTjanstenData.readSlutPost();

    }

}
