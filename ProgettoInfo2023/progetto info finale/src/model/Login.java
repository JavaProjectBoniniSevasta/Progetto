/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Login {
    
     private final String password, username;

    public Login (String username, String password) throws Exception {
        
        //Nel costruttore di una variabile login di acccesso controllo che lo username e la password passati siano validi
        if (username.length() <= 4) {
            throw new Exception ("Nome utente troppo corto");
        }
        else {
            this.username = username;
        }  
        
        if (password.length() >= 8) {
            this.password = password;
        }
        
        else {
            throw new Exception ("Password troppo corta");
        }
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    
}
