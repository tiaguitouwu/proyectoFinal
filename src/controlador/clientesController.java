/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.cliente;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class clientesController implements Initializable {

    @FXML
    private TableView<cliente> tablaCliente;
    @FXML
    private TableColumn<cliente, Integer> rucCliente;
    @FXML
    private TableColumn<cliente, String> razonCliente;
    @FXML
    private TableColumn<cliente, String> direccionCliente;
    @FXML
    private TableColumn<cliente, Integer> telefonoCliente;
    cliente c=new cliente();
    VentaController venta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }    
    private void cargarDatos() {
        ArrayList<cliente> lista = c.consulta();
        ObservableList<cliente> registros = FXCollections.observableArrayList(lista);
        rucCliente.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        razonCliente.setCellValueFactory(new PropertyValueFactory<>("razon"));
        direccionCliente.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        telefonoCliente.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tablaCliente.setItems(registros);
    }
    public void recibirDatos(VentaController ventacontrolador){
        venta=ventacontrolador;
         
     }

    @FXML
    private void seleccionar(MouseEvent event) {
        cliente c=tablaCliente.getSelectionModel().getSelectedItem();
        int id=c.getRuc();
        venta.recibirCodigo((id));
        Node ventana=(Node) event.getSource();
        Stage stage=(Stage) ventana.getScene().getWindow();
        stage.close();  
    }
}
