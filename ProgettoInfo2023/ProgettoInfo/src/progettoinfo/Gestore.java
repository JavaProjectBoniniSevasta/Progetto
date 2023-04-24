package progettoinfo;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Gestore {
    
    private ArrayList<Login> credenziali = new ArrayList<>();

    public void addCredenziali(Login c) throws IOException {
        credenziali.add(c);
        
        Path path = Paths.get("Credenziali.txt");
        System.out.println("Percorso assoluto del file: " + path.toAbsolutePath());
        
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path.toString(), true))) {
            output.write(c.getUsername());
            output.write(",");
            output.write(c.getPassword());
            output.newLine();
            output.flush();
        } catch (IOException e) {
            throw new IOException("Errore durante la scrittura su file", e);
        }
    }
    
    
    public void deleteAllCredenziali() throws FileNotFoundException {
        
        PrintWriter pw = new PrintWriter("Credenziali.txt");
        pw.close();
    }
}
