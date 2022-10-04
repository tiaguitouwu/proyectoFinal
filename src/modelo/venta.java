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
public class venta extends conexion {
    Statement query;
    private String nombre,descripcionProducto;
    private int idproducto,precio,cantidad;

    public venta(int idproducto, String nombre, int precio, int cantidad, String descripcionProducto) {
        this.nombre = nombre;
        this.descripcionProducto = descripcionProducto;
        this.idproducto = idproducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public venta() {
    }
    

    public  ArrayList<venta> consulta(){
            ArrayList<venta> ventaa= new ArrayList<>();
        try {
            String sql="select * from producto";
            query=getConexion().createStatement();
            ResultSet rs= query.executeQuery(sql);
            while(rs.next()){
                int idproduc =rs.getInt(1);
                int prec=rs.getInt(2);
                int can=rs.getInt(3);
                String nom= rs.getString(4);
                String descripcion=rs.getString(5);
                venta p=new venta(idproduc,nom,prec,can,descripcion);
                ventaa.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventaa;
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

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
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
}
