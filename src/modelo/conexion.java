/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TERCEROS TM
 */
public class conexion {
    private String base,contra,host,user;
    private Connection con;

    public conexion(String base, String contra, String host, String user) {
        this.base = base;
        this.contra = contra;
        this.host = host;
        this.user = user;
    }
    
    public conexion(){
        this.base="demo";
        this.user="root";
        this.contra="admin";
        this:host="localhost:3306";
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public Connection getConexion(){
        try{
            
            String url="jdbc:mysql://"+host+"/"+base;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url, user, contra);
            System.out.println("Conectado éxitosamente!!");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error en la conexión");
            e.printStackTrace();
        
        }
        return con;
    }
    
}
