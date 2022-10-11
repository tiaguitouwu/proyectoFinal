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
 * @author fabri
 */
public class producto extends conexion{
    Statement query;
    private String nombre, descripcion,imagen;
    private int codigo,precio,cantidad;
    
    public producto() {
    }

    public producto(String nombre, String descripcion, int codigo, int precio, int cantidad, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen= imagen;
    }
    
    public  ArrayList<producto> consulta(){
            ArrayList<producto> produc= new ArrayList<>();
        try {
            String sql="select * from producto";
            query=getConexion().createStatement();
            ResultSet rs= query.executeQuery(sql);
            while(rs.next()){
                int cod =rs.getInt(1);
                int prec=rs.getInt(2);
                int can=rs.getInt(3);
                String nom= rs.getString(4);
                String descripcion=rs.getString(5);
                String imagen=rs.getString(6);
                producto p=new producto(nom,descripcion,cod,prec,can,imagen);
                produc.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produc;
     }

    public Statement getQuery() {
        return query;
    }

    public void setQuery(Statement query) {
        this.query = query;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public boolean insertar(){
        try {
            String sql="Insert into producto values("+this.codigo+","+this.precio+","+this.cantidad+",'"+this.nombre+"','"+this.descripcion+"','"+this.imagen+"')";
            query=getConexion().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean modificar(){
        try {
            String sql="update producto set "+ "descripcionproducto='"+this.descripcion+"',"+"precio="+this.precio+","+"cantidad="+this.cantidad+","
                    +"nombre='"+this.nombre+"' "
                    + " where idproducto="+this.codigo;
                   
            query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
    }
    }
    public boolean modificarCantidad(){
        try {
            String sql="update producto set cantidad="+this.cantidad
                    +" where idproducto="+this.codigo;
                   
            query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
    public boolean eliminar(){
        try {
            String sql="delete from producto where idproducto= "+this.codigo;
            query=getCon().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(producto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
