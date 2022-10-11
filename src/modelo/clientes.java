/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TERCEROS TM
 */
public class clientes extends conexion{
    Statement query;
    int ruc,telefono;
    String razon, direccion;
    public boolean insertar(){
        try {
            String sql="Insert into clientes values("+this.ruc+",'"+this.razon+"','"+this.direccion+"',"+this.telefono+")";
            query=getConexion().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public clientes(Statement query, int telefono, String razon, String direccion) {
        this.query = query;
        this.telefono = telefono;
        this.razon = razon;
        this.direccion = direccion;
    }
    public clientes(){
    }

    public Statement getQuery() {
        return query;
    }

    public void setQuery(Statement query) {
        this.query = query;
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
}
