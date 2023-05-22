/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Materia {
    
    String materia;

    public Materia(String materia) {
        
        this.materia = materia;
    }

    public String getMateria() {
        return materia;
    }
    
    public boolean addVoto(float voto, String materia, String username) throws IOException, Exception {
    try {
        File file = new File("data/registri/" + username + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        boolean ok = false;

        // Legge il file riga per riga e cerca la materia specificata.
        //In ogni caso mi salvo ogni riga del registro nell'arraylist di stringhe lines e quando trovo la materia
        //a fine della riga in questione aggiungo anche il nuovo voto
        while ((line = reader.readLine()) != null) {
            if (line.contains(materia)) {
                ok = true;
                line += " " + voto; // Aggiunge il voto alla fine della riga
            }
            lines.add(line);
        }

        reader.close();
        
        //se ha trovato la materia a cui bisognava aggiungere il voto vado avanti nell'operazione sennò returno false
        if (ok) {

        // Scrive le righe modificate nel file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String l : lines) {
            writer.write(l);
            writer.newLine();
        }

        writer.close();

        System.out.println("Voto aggiunto correttamente alla materia indicata");
        return true;
        }
        else { System.out.println("Materia non presente");
        return false; }
            
    } catch (IOException e) {
        throw new Exception("Errore nella lettura/scrittura del file");
    }
    }
}
