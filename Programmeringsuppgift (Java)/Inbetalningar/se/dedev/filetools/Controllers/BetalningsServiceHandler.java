package se.dedev.filetools.Controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import se.dedev.filetools.se.dedev.Pojo.BetalningsServiceData;
import se.dedev.filetools.se.dedev.Pojo.BetalningsServicePayment;
import se.lumera.example.payments.PaymentReceiver;

public class BetalningsServiceHandler implements PaymentReceiver {

    private BetalningsServiceData betalningsServiceData = null;
    private String fileName;
    private List<String> lines;

    // Constructor
    public BetalningsServiceHandler(String Path) {
        setFileName(Path);
        betalningsServiceData = new BetalningsServiceData();
        try {
            retrieveFileLines();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BetalningsServiceData getBetalningsServiceData() {
        return this.betalningsServiceData;
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
        // Öppningspost (Openingpost)
        String l = lines.get(0);
        String kontonummer = l.substring(1, 16);
        String betalningsDatum = l.substring(40, 48);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH);
        Date datum = null;
        try {
            datum = sdf.parse(betalningsDatum);
        } catch (ParseException e) {
            throw new Exception("fel vid datum convertering");
        }
        String valuta = l.substring(48, l.length());
        startPaymentBundle(kontonummer, datum, valuta);

        // Kontroll av Antal poster är korrekt (Checking the correct number of records)
        String poster = l.substring(30, 40).trim();
        if (lines.size() - 1 != Integer.parseInt(poster)) {
            throw new Exception("antal poster stämmer inte (incorrect number of records)");
        }

        // Lops through the remaining lines in the file
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String bp = line.substring(1, 16).trim();
            try {
                BigDecimal belopp = new BigDecimal(bp.replaceAll(",", "."));
                String ref = line.substring(15, line.length());
                payment(belopp, ref);
            } catch (Exception e) {
                throw new Exception("fel vid skapandet av belopp. Rad:" + i + " (error creating amount. Line: " + i + ")");
            }
        }

        endPaymentBundle();
    }
    
    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {
        betalningsServiceData.setAccountNumber(accountNumber);
        betalningsServiceData.setCurrency(currency);
        betalningsServiceData.setPaymentDate(paymentDate);
    }

    @Override
    public void payment(BigDecimal amount, String reference) {
        BetalningsServicePayment payment = new BetalningsServicePayment(amount, reference);
        betalningsServiceData.addToPayments(payment);
    }

    @Override
    public void endPaymentBundle() {
        // Perform treatment on the payment data
        new Treatment(betalningsServiceData);
    }
}
