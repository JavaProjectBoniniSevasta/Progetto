package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import model.Login;
import model.Materia;
import model.Registro;

public class Gestore {
    
    //Funzione di controllo per scorrere il file e vedere se già esiste un username chiamato in un certo modo
    //in tal caso non aggiungiamo le credenziali in questione
    public boolean usernameExists(String username) throws IOException {
    
    //Creiamo un file reader connesso al percorso del database
    FileReader file = new FileReader("data/credenziali/" + "credenziali.txt");
    try (BufferedReader reader = new BufferedReader(file)) {
        String line;
        //Finchè c'è da leggere fallo
        while ((line = reader.readLine()) != null) {
            //crea un array splittato in base alla virgola della riga corrente e controlla se 
            //lo username (primo campo) è uguale a quello da controllare passato alla funzione
            String[] credentials = line.split(",");
            if (credentials[0].equals(username)) {
                //se trova lo username già esistente returna true
                return true;
            }
        }
        } catch (IOException e) {
            throw new IOException("Errore durante la lettura del file", e);
        }
        return false;
    }
    
    //Funzione che consente di aggiungere credenziali al nostro database
    public boolean addCredenziali(Login c) throws IOException {
        //Prima di aggiungere nuove credenziali al database controlliamo non esista già un utente con questo nome
        if (usernameExists(c.getUsername())) {
        System.out.println("\nLo username esiste già nel file!");
        //se esiste già questo utente returna false e l'operazione non viene eseguita
        return false;
        }
        else{
            System.out.println("\nNuove credenziali aggiunte correttamente");
        }
        
        //Creiamo un file writer con il percorso del nostro database
        FileWriter file = new FileWriter("data/credenziali/" + "credenziali.txt", true);
        
        try (BufferedWriter output = new BufferedWriter(file)) {
            //Scrive sul file il nuovo username e password splittati da una virgola e poi li manda a capo
            output.write(c.getUsername());
            output.write(",");
            output.write(c.getPassword());
            output.newLine();
            output.flush(); //controllo che abbiamo scritto
            return true; //operazione terminata, return true
        } catch (IOException e) {
            throw new IOException("Errore durante la scrittura su file", e);
        }
    }
    
    //funzione che consente di resettare il database delle credenziali
    public boolean deleteAllCredenziali() throws FileNotFoundException, IOException {
       
        File file = new File("data/credenziali/" + "credenziali.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        
        writer.write("");
        writer.close();
        return true;
    }
    
    //Nel caso in cui si prova a fare l'accesso controlliamo le credenziali
    public static boolean checkCredentials(String username, String password) throws Exception {
       
      try {
            BufferedReader reader = new BufferedReader(new FileReader("data/credenziali/" + "credenziali.txt"));
            //la variabile line si associa sempre ad una riga successiva finchè ne sono presenti
            String line = reader.readLine();

            while (line != null) {
                //crea un array con le credenziali di questa riga splittato in base alla virgola
                String[] credentials = line.split(",");
                //se trovano delle credenziali corrispondenti a quelle inserite si returna true 
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    System.out.println("\nLOGIN AVVENUTO CON SUCCESSO");
                    return true;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         System.out.println("\nLOGIN FALLITO");
        return false;
        
    }
    
    //Funzione menù utilizzabile una volta eseguito l'accesso e verificate le credenziali
    public static void accessoRegistro (String username, String password) throws IOException, Exception {
        
        //creaiamo una variabile di tipo registro che potrà eseguire le funzioni sottostanti
        Registro r = new Registro (username, password);
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
                
                //Nei case prendiamo gli input per le funzioni in registro
                
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
                
                case 'R': r.visuaRegistro();
                break;
                
                case 'D': r.delete();
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