/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettoinfo;

public class Login {
    
     private final String password, username;

    public Login (String username, String password) throws Exception {
        
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
