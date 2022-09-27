/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TERCEROS TM
 */
public class acceso extends conexion {
    private Statement query;
    public acceso(){
    }
    public int consultarCantidad(String sql){
        int cant=0;
        try {    
            query=getConexion().createStatement();
            ResultSet rs=query.executeQuery(sql);
            if(rs.next()){
                cant=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(acceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cant;
    }
}
    