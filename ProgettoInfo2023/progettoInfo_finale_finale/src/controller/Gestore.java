package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import model.Login;
import model.Materia;
import model.Registro;

public class Gestore {
    private ArrayList<Registro> registro = new ArrayList<>();
    
    
    private boolean usernameExists(String username) throws IOException {
        Path path = Paths.get("data/credenziali/" + "credenziali.txt");
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
    
    
    public boolean addCredenziali(Login c) throws IOException, Exception {
        if (usernameExists(c.getUsername())) {
            System.out.println("\nLo username esiste già nel file!");
            return false;
        }
        
        System.out.println("\nNuove credenziali aggiunte correttamente");
        
        registro.add(new Registro(c));
  
        Path path = Paths.get("data/credenziali/" + "credenziali.txt");
        //System.out.println("Percorso assoluto del file: " + path.toAbsolutePath());
        
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path.toString(), true))) {
            output.write(c.getUsername());
            output.write(",");
            output.write(c.getPassword());
            output.newLine();
            output.flush();
            return true;
        } catch (IOException e) {
            throw new IOException("Errore durante la scrittura su file", e);
        }
    }
    
    
    public boolean deleteAllCredenziali() throws FileNotFoundException, Exception {
        PrintWriter pw = new PrintWriter("data/credenziali/" + "credenziali.txt");
        pw.close();
        
        for(Registro r: registro){
            r.delete();
        }
        registro.clear();
        
        return true;
    }
    
    public boolean checkCredentials(String username, String password) throws Exception {
        for (Registro r : registro) {
            if(r.getCredenziali().getUsername().equals(username) && r.getCredenziali().getPassword().equals(password)){
                System.out.println("\nLOGIN AVVENUTO CON SUCCESSO");
                return true;
            }
        }
        
        System.out.println("\nLOGIN FALLITO");
        return false;
        
    }
    
    public static void accessoRegistro (String username, String password) throws IOException, Exception {
        
        Registro r = new Registro (new Login(username, password));
        char risp;
        
        System.out.println("Benvenuto nel tuo registro");
        do {
            System.out.println("\nCosa vuoi fare?");
            System.out.println("Inserire una nuova materia (N)");
            System.out.println("Inserire nuovo voto ad una materia esistente (V)");
            System.out.println("Visualizzare la media dei voti di una materia (M)");
            System.out.println("Visualizzare il mio registro (R)");
            System.out.println("Eliminare tutti i miei dati (D)");
            System.out.println("Esci (Q)");
            
            Scanner myObj = new Scanner(System.in);
            risp = myObj.next().charAt(0);
            risp = Character.toUpperCase(risp);
            while(risp!='N' && risp!='V' && risp!='R' && risp!='D' && risp!='Q' && risp!='M') {
                System.out.println("\nInserimento errato, cosa vuoi fare?");
                risp = myObj.next().charAt(0);
            }  
            
            switch (risp) {
                case 'N': 
                    System.out.println("Inserisci la materia da aggiungere: ");
                    Scanner myOb = new Scanner(System.in);
                    String m = myOb.nextLine();  
                    r.addMateria(m);
                    break;
                
                case 'V': 
                    System.out.println("Inserisci la materia a cui aggiungere un voto: ");
                    Scanner Obj = new Scanner(System.in);
                    String m1 = Obj.nextLine();
                    Materia materia = new Materia(m1);
            
                    System.out.println("Inserisci il voto da aggiungere alla materia: ");
                    Scanner myObj_ = new Scanner(System.in);
                    float voto = myObj_.nextFloat();     
                    r.addVoto(materia, voto);
                    break;
                
                case 'R': 
                    r.visuaRegistro();
                    break;
                
                case 'D': 
                    r.delete();
                    break;
                
                case 'M': 
                    System.out.println("Inserisci la materia: ");
                    Scanner myO = new Scanner(System.in);
                    String m2 = myO.nextLine();
                    //m.toUpperCase();
                    Materia materiaa = new Materia (m2);    
                    r.media(materiaa);
                    break;
            }
        } while (risp!='Q');
    }
}