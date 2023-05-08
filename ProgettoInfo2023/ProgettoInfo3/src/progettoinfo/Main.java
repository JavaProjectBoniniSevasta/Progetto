/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettoinfo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, Exception {
        
        char risp;
            System.out.println("Benvenuto nel programma");
        do {
            
            System.out.println("\nCosa vuoi fare?");
            System.out.println("Inserisci nuove credenziali (A)");
            System.out.println("Cancella tutte le credenziali salvate (D)");
            System.out.println("Login con le credenziali create in precedenza (L)");
            System.out.println("Esci (Q)");
            
            Scanner myObj = new Scanner(System.in);
            risp = myObj.next().charAt(0);
            while (risp!='A' && risp!='D' && risp!='L' && risp!='Q') {
            
            System.out.println("\nInserimento errato, cosa vuoi fare?");
            risp = myObj.next().charAt(0);
            }  
            
            switch (risp) {
                
                case 'A': addCred();
                break;
                
                case 'D': delete();
                break;
                
                case 'L': check();
            }
            
        } while (risp!='Q');
        
 
} 
    
private static void addCred () {
            
    try {
         Scanner myObj = new Scanner(System.in);
         System.out.println("\nInserisci il nuovo username");
         String username = myObj.nextLine();
         System.out.println("Inserisci la nuova password");
         String password = myObj.nextLine();
         
        Login credenziali = new Login(username, password);
        Gestore gestore = new Gestore();
        gestore.addCredenziali(credenziali);
        
    } catch (Exception e) {
        e.printStackTrace();
    }      
}

private static void delete () throws FileNotFoundException {
    
    Gestore gestore = new Gestore();
    gestore.deleteAllCredenziali();
    System.out.println("\nCredenziali eliminate correttamente");
}

  public static void check() throws Exception {
 
         Scanner myObj = new Scanner(System.in);
         System.out.println("\nInserisci il username");
         String username = myObj.nextLine();
         System.out.println("Inserisci la password");
         String password = myObj.nextLine();
         Gestore gestore = new Gestore();
         gestore.checkCredentials(username, password);
  }

}
