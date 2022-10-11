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
public class factura extends conexion {
    private String producto;
    private int codProduc, cantidad, precio, total,idVenta;
    Statement query;
    
    
    public factura(String producto, int codProduc, int cantidad, int precio, int total, int idVenta) {
        this.producto = producto;
        this.codProduc = codProduc;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.idVenta=idVenta;
    }
    public factura(){
    
    }
    public boolean insertarVenta(){
        try {
            String sql="Insert into det_venta values ("+this.idVenta+","+this.codProduc+","+this.cantidad+")";
            query=getConexion().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCodProduc() {
        return codProduc;
    }

    public void setCodProduc(int codProduc) {
        this.codProduc = codProduc;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
