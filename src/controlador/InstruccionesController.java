/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Tercero TM
 */
public class InstruccionesController implements Initializable {

    @FXML
    private Button siguiente;
    @FXML
    private Button atras;
    @FXML
    private ImageView imagen;
    private String home="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        Image image=new Image("/recursos/manual1.jpg",978,639,false,false);
        imagen.setImage(image);
    }    

    @FXML
    private void siguiente(ActionEvent event) {
        
    }

    @FXML
    private void atras(ActionEvent event) {
    }
    
}
