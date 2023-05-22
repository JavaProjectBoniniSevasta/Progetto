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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Registro {
    private ArrayList<Materia> materie = new ArrayList();
    private Login credenziali;

    public Registro(Login credenziali) throws Exception {
        setCredenziali(credenziali);
        creaRegistro();
    }
    
    private void setCredenziali(Login credenziali) throws Exception{
        if(credenziali==null)
            throw new Exception("Credenziali non valide");
        this.credenziali=credenziali;
    }
    
    public Login getCredenziali(){
        return credenziali;
    }
    
    private void creaRegistro(){
        try {
            File myObj;
            myObj = new File("data/registri/" + credenziali.getUsername() + ".txt");
            myObj.createNewFile();
        } catch (IOException ex) {
            System.out.println("Errore");
        }
    }
    
    public boolean addMateria (String m) throws IOException, Exception {
        if(checkMateria(m)){
            System.out.print("Materia già presente nel registro");
            return false;
        }
        else {
            Materia mat = new Materia(m);

            Path path = Paths.get("data/registri/" + credenziali.getUsername() + ".txt");

            try (BufferedWriter output = new BufferedWriter(new FileWriter(path.toString(), true))) {
                output.write(mat.getMateria());
                output.newLine();
                output.flush();
                materie.add(mat);
                return true;

            } catch (IOException e) {
                throw new IOException("Errore durante la scrittura su file", e);
            }
        }
    }
    
    private boolean checkMateria(String m){
        for(Materia mate: materie){
            if(mate.getMateria().equalsIgnoreCase(m))
                return true;
        }
        return false;
    }
    
    public boolean addVoto(Materia materia, float voto) throws Exception { 
        if(materia==null || voto<=0)
            throw new Exception("Materia o voto non validi");
        return (materia.addVoto(voto, materia.getMateria() ,credenziali.getUsername()));
    }

   public ArrayList<String> visuaRegistro() throws Exception {
        try {
            File file = new File("data/registri/" + credenziali.getUsername() + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line;

            // Legge il file riga per riga e successivamente lo stampa
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();

            return lines;

        } catch (IOException e) {
            throw new Exception("Errore nella lettura/scrittura del file");
        }
    }

    public boolean delete() throws Exception { 
        try {
            File file = new File("data/registri/" + credenziali.getUsername() + ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            List<String> lines = new ArrayList<String>();
            String line;

            writer.write("");
            writer.close();
            
            for(Materia m: materie){
                m.getVoti().clear();
            }
            materie.clear();
            
            System.out.println("Registro correttamente eliminato");
            return true;
            
        } catch (IOException e) {
            throw new Exception("Errore nella lettura/scrittura del file");
        }
    }
    
    public float media (Materia materia) throws Exception {
        float media=0;
        for(Materia m: materie){
            if(m.getMateria().equalsIgnoreCase(materia.getMateria())){
                for(Float f: m.getVoti()){
                    media+=f;
                }
                media/=m.getVoti().size();
                return media;
            }
        }
        System.out.println("Materia non presente!");
        return -9999;
    }
}