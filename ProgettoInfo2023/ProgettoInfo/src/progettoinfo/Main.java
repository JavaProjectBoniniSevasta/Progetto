/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoinfo;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author sevastaf
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        char risp;
        
        do {
            
            
            System.out.println("Benvenuto nel programma");
            System.out.println("\nCosa vuoi fare?");
            System.out.println("\nInserisci nuove credenziali (A)");
            System.out.println("\ncancella tutte le credenziali salvate (D)");
            System.out.println("\nLogga con le credenziali che hai creato in precedenza (L)");
            System.out.println("\nEsci (Q)");
            
            Scanner myObj = new Scanner(System.in);
            risp = myObj.next().charAt(0);
            while (risp!='A' && risp!='D' && risp!='L' && risp!='Q') {
            
            System.out.println("\ninserimento errato, cosa vuoi fare?");
            risp = myObj.next().charAt(0);
            }  
            
            switch (risp) {
                
                case 'A': addCred();
                break;
                
                case 'D': delete();
                break;
            }
            
        } while (risp!='Q');
        
 
} 
    
private static void addCred () {
            
    try {
         Scanner myObj = new Scanner(System.in);
         System.out.println("\nInserisci il nuovo username");
         String username = myObj.nextLine();
         System.out.println("\nInserisci la nuova password");
         String password = myObj.nextLine();
         
        Login credenziali = new Login(username, password);
        Gestore gestore = new Gestore();
        gestore.addCredenziali(credenziali);
        System.out.println("\nNuove credenziali aggiunte correttamente");
    } catch (Exception e) {
        e.printStackTrace();
    }      
}

private static void delete () throws FileNotFoundException {
    
    Gestore gestore = new Gestore();
    gestore.deleteAllCredenziali();
    System.out.println("\nCredenziali eliminate correttamente");
}


}

