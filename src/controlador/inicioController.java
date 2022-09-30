/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author TERCEROS TM
 */
public class inicioController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private Button btnVenta;
    @FXML
    private Button btnProveedores;
    @FXML
    private Button btnProductos;
    public String a;
    ConfirmacionController b;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private void mostrarVentana(String ventana){
        try {
            Stage stage=new Stage();
            Parent menu=FXMLLoader.load(getClass().getResource("/vista/"+ventana.toLowerCase()+".fxml"));
            Scene scene=new Scene(menu);
            stage.setTitle("MENU DE "+ventana);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
            Logger.getLogger(inicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void abrirVenta(ActionEvent event) {
        mostrarVentana("CONFIRMACION VENTA");
    }

    @FXML
    private void abrirProveedores(ActionEvent event) {
        mostrarVentana("CONFIRMACION PROVEEDORES");
    }

    @FXML
    private void abrirProductos(ActionEvent event) {
        mostrarVentana("CONFIRMACION PRODUCTOS");        
    }
    
}
