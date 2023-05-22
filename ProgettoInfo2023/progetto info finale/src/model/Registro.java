/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Registro {
    
    public String username;

    public Registro(String username, String password) {
        
        //Nel costruttore consideriamo sia il caso questo sia un primo accesso o uno già avvenuto.
        //Nel caso l'utente abbia già un suo registro personale non creiamo un nuovo file, al contrario invece si.
        try {
            this.username = username;
            
            File myObj;
            myObj = new File("data/registri/" + username + ".txt");
            //crea effettivamente il file se non esiste, sennò non fa nulla
            myObj.createNewFile();
        } catch (IOException ex) {
            System.out.println("errore");
        }
    }
    
    //Funzione che consente di aggiungere una nuova materia al proprio registro in una nuova riga
    public boolean addMateria (String m) throws IOException {
        
        //controlliamo prima che la materià non esista già, in tal caso returniamo direttamente false senza fare niente.
        if(checkMateria(m,username))    {
            System.out.print("Materia già presente nel registro");
            return false;
        }
        else {
        Materia materia = new Materia(m);
         
        FileWriter file = new FileWriter("data/registri/" + username + ".txt", true);
        
        try (BufferedWriter output = new BufferedWriter(file)) {
            //Scriviamo nel file la nuova materia e la mandiamo a capo
            output.write(materia.getMateria());
            output.newLine();
            output.flush();
            return true;
            
        } catch (IOException e) {
            throw new IOException("Errore durante la scrittura su file", e);
        }
    }
    }
    
    //Funzione che in base ad una materia di input controlla che non sia già presente nel registro personale di un utente
    private boolean checkMateria(String m, String username) throws FileNotFoundException, IOException{
        File file = new File("data/registri/" + username + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        Materia m1 = new Materia (m);
        //finchè c'è da leggere leggi
        while ((line = reader.readLine()) != null) {
            //se una riga contiene la materia passata returniamo true
            if (line.contains(m1.getMateria())) {
                return true;
            }
        }
        reader.close();
        //in caso generico returniamo false
        return false;
    }
    
    public boolean addVoto(Materia materia, float voto) throws Exception {
        
            //La funzione si lega semplicemente alla sua rispettiva nella classe Materia
              
            if(materia.addVoto(voto, materia.getMateria() ,username)) {
                return true;
            }
            else {
                return false;
            }
    }
    
    //Funzione che returna un arraylist di stringhe contenente tutte le righe di un registro personale e lo stampa
   public ArrayList<String> visuaRegistro() throws Exception {
        
        try {
        File file = new File("data/registri/" + username + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        
        //finchè c'è da leggere legge ogni riga e la pusha nell'arraylist lines
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        reader.close();
        
        //scorre e stampa tutte le righe del registro
        for (String riga : lines) {
            
            System.out.println(riga);
        }
        
        return lines;

    } catch (IOException e) {
        throw new Exception("Errore nella lettura/scrittura del file");
    }
    }
   
   //Funzione che resetta un registro personale di un utente
    public boolean delete() throws Exception {
        
        try {
        File file = new File("data/registri/" + username + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        
        writer.write("");
        writer.close();
        System.out.println("Registro correttamente eliminato");
        return true;
        

    } catch (IOException e) {
        throw new Exception("Errore nella lettura/scrittura del file");
    }
    }
    
    //Funzione che returna la media dei voti di una singola materia
    public float media (Materia materia) throws Exception {
        
        try {
        File file = new File("data/registri/" + username + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<Float> voti = new ArrayList<Float>();
        String line;

        // Legge il file riga per riga e cerca la materia specificata
        while ((line = reader.readLine()) != null) {
            if (line.contains(materia.getMateria())) {
                //se trovo la materia salvo la rispettiva riga in un array di stringhe splittando in base agli spazi
                String riga[] = line.split(" ");
               for (String parola : riga) {   
                   lines.add(parola);
               }
            }
        }
        reader.close();
        //se l'arraylist lines non è vuoto iniziamo a calcolare la media
        if (lines!=null) {     
            //il for parte da 1 per non includere la prima stringa dell'array che sarebbe il nome della materia in questione
            for (int i=1; i<lines.size(); i++) {
                //converto ogni voto da string a float e lo pusho nell'arraylist float chiamato voti
                float voto = Float.parseFloat(lines.get(i));
                voti.add(voto);
            }
            float somma = 0;
            //scorro voti e calcolo la somma
            for (int i=0; i<voti.size(); i++) {
                somma = somma + voti.get(i);
            }
            //calcolo la media e stampo
            float media = somma / voti.size();
            System.out.println("La media dei voti di questa materia è: " + media);
            return media;
        }
        else {
            System.out.println("Materia non presente!");
        }     

    } catch (IOException e) {
        throw new Exception("Errore nella lettura/scrittura del file");
    }
       //nel caso generico returno un valore simbolico
       return -999; 
    }
   
    
}