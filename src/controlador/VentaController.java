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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.factura;
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
    @FXML
    private Button borrar;
    @FXML
    private ImageView imagen;
    @FXML
    private TableView<factura> tablaFactura;
    @FXML
    private TableColumn<factura, Integer> codFac;
    @FXML
    private TableColumn<factura, String> productoFac;
    @FXML
    private TableColumn<factura, Integer> cantidadFac;
    @FXML
    private TableColumn<factura, Integer> precioFac;
    @FXML
    private TableColumn<factura, Integer> totalFac;
    @FXML
    private Label total;
    @FXML
    private Button añadir;
    
    ObservableList<factura> registros1;
    int suma,monto;
    @FXML
    private TextField precioVenta;
    @FXML
    private TextField canVenta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        registros1=FXCollections.observableArrayList();
        productoFac.setCellValueFactory(new PropertyValueFactory<>("producto"));
        codFac.setCellValueFactory(new PropertyValueFactory<>("codProduc"));
        cantidadFac.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        precioFac.setCellValueFactory(new PropertyValueFactory<>("precio"));
        totalFac.setCellValueFactory(new PropertyValueFactory<>("total"));
        tablaFactura.setItems(registros1);
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
    @FXML
    private void mostrar(MouseEvent event) {
        venta p=tablaVenta.getSelectionModel().getSelectedItem();
        codVenta.setText(String.valueOf(p.getIdproducto()));
        nomVenta.setText(String.valueOf(p.getNombre()));
        precioVenta.setText(String.valueOf(p.getPrecio()));
        Image image1=new Image("/recursos/"+p.getImagen());
        imagen.setImage(image1);
       
    }

    @FXML
    private void limpiarTexto(ActionEvent event) {
        codVenta.setText("");
        nomVenta.setText("");
        precioVenta.setText("");
        Image image1=new Image("/recursos/icono-producto.jpg");
        imagen.setImage(image1);
    }

    @FXML
    private void factura(ActionEvent event) {
        int totall=Integer.parseInt(precioVenta.getText())*Integer.parseInt(canVenta.getText());
        suma=suma+totall;
        factura local=new factura(nomVenta.getText(),Integer.parseInt(codVenta.getText()),Integer.parseInt(canVenta.getText()),Integer.parseInt(precioVenta.getText()),totall);
        registros1.add(local);
        tablaFactura.setItems(registros1);
        total.setText(String.valueOf(suma));
    }
   
}
