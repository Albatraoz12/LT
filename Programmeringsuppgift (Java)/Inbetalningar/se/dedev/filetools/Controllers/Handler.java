package se.dedev.filetools.Controllers;

public class Handler {

    public Handler(String path) {
        if (path.endsWith("_betalningsservice.txt")) {
            new BetalningsServiceHandler(path);
        } else {
            new InbetalningstjanstenHandler(path);
        }
        
    }
    
}
