/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class cliente extends conexion{
    private int ruc,telefono;
    private String razon,direccion;
    Statement query;
    
    
    
    public ArrayList<cliente> consultaBuscar(){
            ArrayList<cliente> clientito= new ArrayList<>();
        try {
            
            String sql="select * cliente";
            query=getCon().createStatement();
            ResultSet rs= query.executeQuery(sql);
            while(rs.next()){
                int rucc=rs.getInt(1);
                String raz=rs.getString(2);
                String dir=rs.getString(3);
                int tel =rs.getInt(4);
                cliente cli=new cliente(rucc,raz,dir,tel);
                clientito.add(cli);//agregamos cada fila(objeto al arrayList
            }//fin while
        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientito;
    }
     
    
    public cliente() {
    }

    public cliente(int ruc, String razon, String direccion, int telefono) {
        this.ruc = ruc;
        this.telefono = telefono;
        this.razon = razon;
        this.direccion = direccion;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }    

    public ArrayList<cliente> consulta() {
        ArrayList<cliente> busc=new ArrayList<>();
            try {
                String sql="select * from cliente";
                query=getConexion().createStatement();
                ResultSet rs=query.executeQuery(sql);
            while(rs.next()){
                int rucc=rs.getInt(1);
                String raz=rs.getString(2);
                String dir=rs.getString(3);
                int tel =rs.getInt(4);
                cliente a=new cliente(rucc,raz,dir,tel);
                busc.add(a);//agregamos cada fila(objeto) al arraylist
            }//fin del while
        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       return busc; 
    }
}
