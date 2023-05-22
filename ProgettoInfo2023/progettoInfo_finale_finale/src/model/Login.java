/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Login {
    private String username, password;

    public Login (String username, String password) throws Exception{
        setUsername(username);
        setPassword(password); 
    }

    private void setUsername(String username) throws Exception{
        if (username.length() <= 4)
            throw new Exception ("Nome utente troppo corto");
        this.username = username;
    }
    
    private void setPassword(String password) throws Exception{
        if (password.length() < 8)
            throw new Exception ("Password troppo corta");
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
