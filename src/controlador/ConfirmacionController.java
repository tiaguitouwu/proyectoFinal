/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.acceso;

/**
 * FXML Controller class
 *
 * @author TERCEROS TM
 */
public class ConfirmacionController extends inicioController implements Initializable {
    @FXML
    private TextField password;
    acceso a=new acceso();
    public String accion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(accion);
    }
    @FXML
    private void ingresar(ActionEvent event){
        String sql="select count(*) from acceso where password="+password.getText()+"";
        int can=a.consultarCantidad(sql);
        System.out.println(can);
        if(can==1){
            System.out.println("Usuario existente");
            mostrarVentana();
            Node ventana=(Node) event.getSource();
            Stage stage=(Stage) ventana.getScene().getWindow();
            stage.close();
            System.out.println(accion);
        }else{
            System.out.println("No existe ese usuario");
            mostrarMensaje();
        }
    }
    private void mostrarVentana(){
        try {
            Stage stage=new Stage();
            Parent menu=FXMLLoader.load(getClass().getResource("/vista/venta.fxml"));
            Scene scene=new Scene(menu);
            stage.setTitle("MENU DE ");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            } catch (IOException ex) {
            Logger.getLogger(inicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void mostrarMensaje() {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("PIN INCORRECTO");
        alert.showAndWait();
    }
    
    
    
}
