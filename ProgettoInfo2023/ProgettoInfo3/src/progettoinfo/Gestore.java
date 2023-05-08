package progettoinfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Gestore {
    
    private ArrayList<Login> credenziali = new ArrayList<>();
    
    
    public boolean usernameExists(String username) throws IOException {
    Path path = Paths.get("Credenziali.txt");
    try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] credentials = line.split(",");
            if (credentials[0].equals(username)) {
                return true;
            }
        }
        } catch (IOException e) {
            throw new IOException("Errore durante la lettura del file", e);
        }
        return false;
    }
    
    
    public void addCredenziali(Login c) throws IOException {
        if (usernameExists(c.getUsername())) {
        System.out.println("\nLo username esiste già nel file!");
        return;
        }
        else{
            System.out.println("\nNuove credenziali aggiunte correttamente");
        }
        
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
    
    public static boolean checkCredentials(String username, String password) throws Exception {
       
      try {
            BufferedReader reader = new BufferedReader(new FileReader("Credenziali.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    System.out.println("LOGIN AVVENUTO CON SUCCESSO");
                    accessoRegistro(username, password);
                    return true;
                    
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         System.out.println("LOGIN FALLITO");
        return false;
        
    }
    
    public static void accessoRegistro (String username, String password) throws IOException, Exception {
        
        Registro r = new Registro (username, password);
        char risp;
        
            System.out.println("Benvenuto nel tuo registro");
        do {
            
            
            System.out.println("\nCosa vuoi fare?");
            System.out.println("Inserire una nuova materia (N)");
            System.out.println("Inserire nuovo voto ad una materia esistente (V)");
            System.out.println("Visualizzare il mio registro (R)");
            System.out.println("Eliminare tutti i miei dati (D)");
            System.out.println("Esci (Q)");
            
            Scanner myObj = new Scanner(System.in);
            risp = myObj.next().charAt(0);
            while (risp!='N' && risp!='V' && risp!='R' && risp!='D' && risp!='Q') {
            
            System.out.println("\nInserimento errato, cosa vuoi fare?");
            risp = myObj.next().charAt(0);
            }  
            
            switch (risp) {
                
                case 'N': r.addMateria();
                break;
                
                case 'V': r.addVoto();
                break;
                
                case 'R': r.visuaRegistro();
                break;
                
                case 'D': r.delete();
                break;
                
            }
            
        } while (risp!='Q');
    }
}