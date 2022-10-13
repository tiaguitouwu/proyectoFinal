/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.clientes;
import modelo.factura;
import modelo.producto;
import modelo.venta;

/**
 * FXML Controller class
 *
 * @author TERCEROS TM
 */
public class VentaController implements Initializable {
    venta v=new venta();
    factura f=new factura();
    clientes c=new clientes();
    
    @FXML
    private TableView<producto> tablaVenta;
    @FXML
    private TableColumn<producto, Integer> id;
    @FXML
    private TableColumn<producto, String> nombre;
    @FXML
    private TableColumn<producto, Integer> precio;
    @FXML
    private TableColumn<producto, Integer> cantidad;
    @FXML
    private TableColumn<producto, String> descripcion;
    int subtotal=0;
    producto a=new producto();
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
    @FXML
    private Button btnFactura;
    @FXML
    private TextField txtVenta;
    private boolean modificar=false;
    @FXML
    private TextField rucCliente;
    @FXML
    private TextField txtRazon;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
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
        ArrayList<producto> lista=a.consulta();
        ObservableList<producto> registros=FXCollections.observableArrayList(lista);
        id.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaVenta.setItems(registros);
    }
    @FXML
    private void mostrar(MouseEvent event) throws FileNotFoundException {
        producto p=tablaVenta.getSelectionModel().getSelectedItem();
        codVenta.setText(String.valueOf(p.getCodigo()));
        nomVenta.setText(String.valueOf(p.getNombre()));
        precioVenta.setText(String.valueOf(p.getPrecio()));
        FileInputStream stream = new FileInputStream(p.getImagen());
        Image image = new Image(stream);
        imagen.setImage(image);
       
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
        factura local=new factura(nomVenta.getText(),Integer.parseInt(codVenta.getText()),Integer.parseInt(canVenta.getText()),Integer.parseInt(precioVenta.getText()),totall,Integer.parseInt(txtVenta.getText()));
        registros1.add(local);
        tablaFactura.setItems(registros1);
        total.setText(String.valueOf(suma));
        setSubTotal(suma);
    }
    
    private void setSubTotal(int total){
        this.subtotal=total;
        System.out.println(subtotal);
    }
    public int getSubTotal(){
        return subtotal;
    }
    
    @FXML
    private void pagar(ActionEvent event) {
        //   Cabecera   \\
        Alert alerta1=new Alert(Alert.AlertType.CONFIRMATION);
        alerta1.setTitle("Aviso");
        alerta1.setHeaderText(null);
        alerta1.setContentText("¿Desea completar la venta?");
        Optional<ButtonType> accion2=alerta1.showAndWait();
        if(accion2.get()==ButtonType.OK){
            c.setRazon(txtVenta.getText());
            c.setRuc(Integer.parseInt(rucCliente.getText()));
            c.setTelefono(Integer.parseInt(txtTelefono.getText()));
            c.setDireccion(txtDireccion.getText());
            if(c.insertar()){
                v.setIdVenta(Integer.parseInt(txtVenta.getText()));
                v.setRuc(Integer.parseInt(rucCliente.getText()));
                if(v.insertarVenta()){
                   Alert ins=new Alert(Alert.AlertType.INFORMATION);
                   ins.setTitle("Aviso");
                   ins.setHeaderText(null);
                   ins.setContentText("Venta completada");
                   ins.show();
                   cargarDatos();
                }else{
                          Alert ins=new Alert(Alert.AlertType.ERROR);
                        ins.setTitle("Aviso");
                        ins.setHeaderText(null);
                       ins.setContentText("Error al insertar.Contacte con al Administrador");
                       ins.show();
                    }
       }
                
        //   Detalle Venta   //
        
        for (factura object : registros1) {
            f.setCodProduc(object.getCodProduc());
            f.setIdVenta(object.getIdVenta());
            f.setCantidad(object.getCantidad());
        }
        
        
        
        }
    }

    @FXML
    private void buscar(ActionEvent event) {
        try {
         FXMLLoader fxmlLoader = new FXMLLoader();
         fxmlLoader.setLocation(getClass().getResource("/vista/clientes.fxml"));
         Scene scene = new Scene(fxmlLoader.load());
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.setTitle("Cliente");
         stage.initModality(Modality.APPLICATION_MODAL);
         clientesController buscarControlador= fxmlLoader.getController();
         buscarControlador.recibirDatos(this);
         stage.show();
        
     } catch (IOException ex) {
         Logger.getLogger(VentaController.class.getName()).log(Level.SEVERE, null, ex);
     }

    }
    public void recibirCodigo(int id){
       rucCliente.setText(String.valueOf(id));
    }
    
}
