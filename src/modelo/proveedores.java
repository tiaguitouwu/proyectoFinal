/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author TERCEROS TM
 */
public class proveedores extends conexion {
    Statement query;
    int ruc,telefono;
    String nombre, direccion;

    
     public ArrayList<proveedores> consulta(){
        ArrayList<proveedores> proveedor=new ArrayList<>();
            try {
            String sql="select * from proveedor";
            query=getConexion().createStatement();
            ResultSet rs=query.executeQuery(sql);
            while(rs.next()){
                int rucc=rs.getInt(1);
                String nom=rs.getString(2);
                String dir=rs.getString(3);
                int tel=rs.getInt(4);
                
                proveedores p=new proveedores(rucc,tel,nom,dir);
                proveedor.add(p);//agregamos cada fila(objeto) al arraylist
            }//fin del while
        } catch (SQLException ex) {
            Logger.getLogger(proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
       return proveedor; 
    }
     public boolean insertar(){
        try {
            String sql="insert into proveedor values("+this.ruc+",'"+this.nombre+"','"+this.direccion+"',"+this.telefono+")";
            query=getConexion().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(proveedores.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     public boolean modificar(){
        try {
            String sql="update proveedor set "
                    + "RUC_proveedor="+this.ruc+","
                    +"nombre='"+this.nombre+"',"
                    +"direccion='"+this.direccion+"',"
                    +"telefono="+this.telefono+" "
                    + " where RUC_proveedor="+this.ruc;
                   
            query=getConexion().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(proveedores.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     public boolean eliminar(){
        try {
            String sql="delete from proveedor where RUC_proveedor= "+this.ruc;
            query=getConexion().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(proveedores.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    
    }
    
    
    public proveedores(int ruc, int telefono, String nombre, String direccion) {
        this.ruc = ruc;
        this.telefono = telefono;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public proveedores(){
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
