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
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Materia {
    
    ArrayList <Float> voti = new ArrayList();
    String materia;

    public Materia(String materia) {
        
        this.materia = materia;
    }

    public String getMateria() {
        return materia;
    }
    
    public void addVoto(float voto, String materia, String username) throws IOException, Exception {
    try {
        File file = new File("Registro" + username + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<String>();
        String line;

        // Legge il file riga per riga e cerca la materia specificata
        while ((line = reader.readLine()) != null) {
            if (line.contains(materia)) {
                line += " " + voto; // Aggiunge il voto alla fine della riga
            }
            lines.add(line);
        }

        reader.close();

        // Scrive le righe modificate nel file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String l : lines) {
            writer.write(l);
            writer.newLine();
        }

        writer.close();

        System.out.println("Voto aggiunto correttamente alla materia indicata");
    } catch (IOException e) {
        throw new Exception("Errore nella lettura/scrittura del file");
    }
}  
}
