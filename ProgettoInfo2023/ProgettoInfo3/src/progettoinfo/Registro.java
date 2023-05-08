/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoinfo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Registro {
    
    Materia materia;
    public final String username;

    public Registro(String username, String password) {
        
        this.username = username;
        
        File myObj = new File("Registro" + username + ".txt");
    }
    
    public void addMateria () throws IOException {
        
        System.out.println("Inserisci la materia da aggiungere: ");
         Scanner myObj = new Scanner(System.in);
         String m = myObj.nextLine();
         
         Materia materia = new Materia(m);
         
         Path path = Paths.get("Registro" + username + ".txt");
        
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path.toString(), true))) {
            output.write(materia.getMateria());
            output.newLine();
            output.flush();
            
        } catch (IOException e) {
            throw new IOException("Errore durante la scrittura su file", e);
        }
    }
    
    public void addVoto() throws Exception {
        
            System.out.println("Inserisci la materia da aggiungere: ");
            Scanner myObj = new Scanner(System.in);
            String m = myObj.nextLine();
            Materia materia = new Materia(m);
            
            System.out.println("Inserisci il voto da aggiungere alla materia: ");
            Scanner myObj_ = new Scanner(System.in);
            float voto = myObj_.nextFloat();
            
            materia.addVoto(voto, materia.getMateria() ,username);
    }

   public void visuaRegistro() throws Exception {
        
        try {
        File file = new File("Registro" + username + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<String>();
        String line;

        // Legge il file riga per riga e cerca la materia specificata
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        reader.close();
        
        for (String riga : lines) {
            
            System.out.println(riga);
        }

    } catch (IOException e) {
        throw new Exception("Errore nella lettura/scrittura del file");
    }
    }

    public void delete() throws Exception {
        
        try {
        File file = new File("Registro" + username + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        List<String> lines = new ArrayList<String>();
        String line;
        
        writer.write("");
        writer.close();
        System.out.println("Registro correttamente eliminato");
        

    } catch (IOException e) {
        throw new Exception("Errore nella lettura/scrittura del file");
    }
    }
   
   
    
}