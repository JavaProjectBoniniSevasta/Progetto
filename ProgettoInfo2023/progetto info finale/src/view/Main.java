/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import model.Login;
import controller.Gestore;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
Benvenuti nel progetto di informatica di Federico Bonini e Flavio Sevasta. 
Il nostro progetto consiste in un semplice registro elettronico, gestito tramite file, che consente l'aggiunta di 
materie, voti, e di visualizzare la propria media in una specifica materia.
L'aspetto delle classi e l'organizzazione generale del codice è abbastanza contestabile e ne siamo a conoscenza.
Abbiamo tentato gli ultimi due giorni, rendendoci conto degli errori commessi, di sistemare il tutto ma la sua logica 
era ormai troppo intrinseca nei diversi aspetti del programma.
Siamo però contenti funzioni secondo gli obbiettivi che ci eravamo prefissati.
Termino dicendo che nel programma troverete abbondanti commenti per farvi ben comprendere tutti i passaggi.
Buona visione.
*/


public class Main {

    public static void main(String[] args) throws FileNotFoundException, Exception {
        
        //Chiamata alla procedura di menù testuale "Menu()"
        Menu();

} 
    
public static void Menu () throws FileNotFoundException, Exception {
    
    char risp;
            System.out.println("Benvenuto nel programma");
        do {
            
            System.out.println("\nCosa vuoi fare?");
            System.out.println("Inserisci nuove credenziali (A)");
            System.out.println("Cancella tutte le credenziali salvate (D)");
            System.out.println("Login con le credenziali create in precedenza (L)");
            System.out.println("Esci (Q)");
            
            Scanner myObj = new Scanner(System.in);
            //In risp mi salvo il char della risposta
            risp = myObj.next().charAt(0);
            //validazione caratteri (accettati sia maiuscoli che minuscoli)
            risp = Character.toUpperCase(risp);
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

//Procedura che gestisce l'input per la funzione principale AddCredenziali (nella classe Gestore),
//la quale consente di aggiungere nuove credenziali accettate nel nostro database.
public static void addCred () {
            
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

//Procedura che gestisce l'input per la funzione principale deleteAllCredenziali (nella classe Gestore),
//la quale consente di eliminare tutte le credenziali accettate nel nostro database.
public static void delete () throws FileNotFoundException, IOException {
    
    Gestore gestore = new Gestore();
    gestore.deleteAllCredenziali();
    System.out.println("\nCredenziali eliminate correttamente");
}

/*Procedura che gestisce l'input per la funzione principale accessoRegistro (nella classe Gestore).
Consente di controllare tramite checkCredentials che lo username e password inseriti siano validi
per poi successivamente far accedere o no alla propria area personale
*/
  public static void check() throws Exception {
 
         Scanner myObj = new Scanner(System.in);
         System.out.println("\nInserisci il username");
         String username = myObj.nextLine();
         System.out.println("Inserisci la password");
         String password = myObj.nextLine();
         Gestore gestore = new Gestore();
         boolean ok = gestore.checkCredentials(username, password);
         if (ok == true) gestore.accessoRegistro(username, password);
  }

}
