/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.venta;

/**
 * FXML Controller class
 *
 * @author TERCEROS TM
 */
public class VentaController implements Initializable {

    @FXML
    private TableView<venta> tablaVenta;
    @FXML
    private TableColumn<venta, Integer> id;
    @FXML
    private TableColumn<venta, String> nombre;
    @FXML
    private TableColumn<venta, Integer> precio;
    @FXML
    private TableColumn<venta, Integer> cantidad;
    @FXML
    private TableColumn<venta, String> descripcion;
    
    venta a=new venta();
    @FXML
    private TextField codVenta;
    @FXML
    private TextField nomVenta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }
    public void cargarDatos(){
        ArrayList<venta> lista=a.consulta();
        ObservableList<venta> registros=FXCollections.observableArrayList(lista);
        id.setCellValueFactory(new PropertyValueFactory<>("idproducto"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionProducto"));
        tablaVenta.setItems(registros);
    }
    private void mostrar(MouseEvent event) {
        venta p=tablaVenta.getSelectionModel().getSelectedItem();
        codVenta.setText(String.valueOf(p.getIdproducto()));
        nomVenta.setText(String.valueOf(p.getNombre()));
        txtapellido.setText(String.valueOf(p.precio()));
        txtdireccion.setText(String.valueOf(p.getDireccion()));
        txttelefono.setText(String.valueOf(p.getTelefono()));
        BtnEliminar.setDisable(false);
        BtnModificar.setDisable(false);
    }
}
