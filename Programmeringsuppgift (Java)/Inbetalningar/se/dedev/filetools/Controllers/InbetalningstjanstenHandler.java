package se.dedev.filetools.Controllers;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

import se.dedev.filetools.se.dedev.Pojo.InBetalningsTjanstenData;
import se.dedev.filetools.se.dedev.Pojo.InBetalningsTjanstenPayment;
import se.lumera.example.payments.PaymentReceiver;

public class InbetalningstjanstenHandler implements PaymentReceiver {


    private String fileName;
    private InBetalningsTjanstenData inBetalningsTjanstenData = null;
    private List<String> lines;

    public InbetalningstjanstenHandler(String Path) {
        setFileName(Path);
        inBetalningsTjanstenData = new InBetalningsTjanstenData();
        try {
            retrieveFileLines();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public InBetalningsTjanstenData getInBetalningsTjanstenData() {
        return this.inBetalningsTjanstenData;
    }

    private void setFileName(String path) {
        this.fileName = path;
    }

    private String getFileName() {
        return this.fileName;
    }
    

    private void retrieveFileLines() throws Exception {
        try {
            lines = Files.readAllLines(Paths.get(getFileName()));
        } catch (IOException e) {
            throw e;
        }
        String l = lines.get(0);
        String kontonummer = l.substring(14, 25);
        Date paymentDate = new Date();
        startPaymentBundle(kontonummer, paymentDate, "SEK");

        for (int i = 1; i < lines.size() - 1; i++) {
            String line = lines.get(i);
            String bp = line.substring(2, 22).trim();
            try {
                String blp = bp.substring(0, bp.length() - 2).trim() + "." + bp.substring(bp.length() - 2);
                BigDecimal belopp = new BigDecimal(blp.replaceAll(",", "."));
                String ref = line.substring(40, 65);
                payment(belopp, ref);
            } catch (Exception e) {
                throw new Exception("fel vid skapandet av belopp. Rad:" + i);
            }
        }
    
        endPaymentBundle();
    }
    
     @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {
        inBetalningsTjanstenData.setAccountNumber(accountNumber);
        inBetalningsTjanstenData.setCurrency(currency);
        inBetalningsTjanstenData.setPaymentDate(paymentDate);
        
    }

    @Override
    public void payment(BigDecimal amount, String reference) {
        InBetalningsTjanstenPayment paymanet = new InBetalningsTjanstenPayment(amount, reference);
        inBetalningsTjanstenData.addToPayments(paymanet);
        
    }

    @Override
    public void endPaymentBundle() {
        inBetalningsTjanstenData.setSlutpost(lines.get(lines.size() - 1));
        new Treatment(inBetalningsTjanstenData);

    }
    
}
