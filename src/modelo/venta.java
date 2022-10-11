/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TERCEROS TM
 */
public class venta extends conexion {
    Statement query;
    
    private int idVenta,ruc;

    public venta(int idVenta, int ruc) {
        this.idVenta=idVenta;
        this.ruc=ruc;
    }

    public venta() {
    }
    
    public boolean insertarVenta(){
        LocalDate fecha1 = LocalDate.now();
        String formato="dd/LL/yyyy";
        String fecha=(obtenerFechaFormateada(fecha1,formato));
        try {   
            String sql="Insert into venta values ("+this.idVenta+",'"+fecha+"',"+this.ruc+")";
            query=getConexion().createStatement();
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Statement getQuery() {
        return query;
    }

    public void setQuery(Statement query) {
        this.query = query;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }
    private String obtenerFechaFormateada(LocalDate fecha, String formato) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formato);
        return fecha.format(dtf);
    }   

}
