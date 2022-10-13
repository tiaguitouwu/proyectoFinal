/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
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
    int numerito=1;
    /**
     * Initializes the controller class.
     */
      
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        mostrar();
    }    
    @FXML
    private void siguiente(ActionEvent event) {
        if(numerito<5){
             numerito=numerito+1;
             atras.setDisable(false);
             mostrar();
        }else{
            siguiente.setDisable(true);
        
        }
      
    }

    @FXML
    private void atras(ActionEvent event) {
        if(numerito>1){
            numerito=numerito-1;
             atras.setDisable(false);
             mostrar();
             siguiente.setDisable(false);
        }else{
            atras.setDisable(true);
            siguiente.setDisable(false);
        }
        
    }
    private void mostrar(){
        Image image=new Image("/recursos/manual"+numerito+".png",978,639,false,false);
        imagen.setImage(image);
    }
}
