/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author TERCEROS TM
 */
public class factura {
    private String producto;
    private int codProduc, cantidad, precio, total;

    public factura(String producto, int codProduc, int cantidad, int precio, int total) {
        this.producto = producto;
        this.codProduc = codProduc;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }
    public factura(){
    
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
