/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import modelo.producto;
import modelo.proveedores;

/**
 * FXML Controller class
 *
 * @author TERCEROS TM
 */
public class ProveedoresController implements Initializable {

    @FXML
    private TextField rucProveedores;
    @FXML
    private TextField nombreProveedores;
    @FXML
    private TextField direccionProveedores;
    @FXML
    private TextField telefonoProveedores;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<proveedores> tablaProveedores;
    @FXML
    private TableColumn<proveedores, Integer> rucTabla;
    @FXML
    private TableColumn<proveedores, String> nombreTabla;
    @FXML
    private TableColumn<proveedores, String> dirTabla;
    @FXML
    private TableColumn<proveedores, Integer> telefTabla;
    
    proveedores p=new proveedores();
    boolean modificar=false;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnCancelar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }
    
    private void cargarDatos() {
        ArrayList<proveedores> lista=p.consulta();
        ObservableList<proveedores> registros=FXCollections.observableArrayList(lista);
        rucTabla.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        nombreTabla.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        dirTabla.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        telefTabla.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tablaProveedores.setItems(registros);
    }
    
    @FXML
    private void mostrar(MouseEvent event){
        proveedores p=tablaProveedores.getSelectionModel().getSelectedItem();
        rucProveedores.setText(String.valueOf(p.getRuc()));
        nombreProveedores.setText(String.valueOf(p.getNombre()));
        direccionProveedores.setText(String.valueOf(p.getDireccion()));
        telefonoProveedores.setText(String.valueOf(p.getTelefono()));
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
    }
    
    @FXML
    private void guardar() {
        if(modificar){
            System.out.println("Usted desea modificar");
            p.setRuc(Integer.parseInt(rucProveedores.getText()));
            p.setNombre(nombreProveedores.getText());
            p.setDireccion(direccionProveedores.getText());
            p.setTelefono(Integer.parseInt(telefonoProveedores.getText()));
            if(p.modificar()){
                Alert ins=new Alert(Alert.AlertType.INFORMATION);
                ins.setTitle("Aviso");
                ins.setHeaderText(null);
                ins.setContentText("Datos modificados correctamente");
                ins.show();
                limpiarTexto();
                cargarDatos();
                cancelar();
            }else{
                Alert ins=new Alert(Alert.AlertType.ERROR);
                ins.setTitle("Aviso");
                ins.setHeaderText(null);
                ins.setContentText("Error al moficar.Contacte con al Administrador");
                ins.show();
                limpiarTexto();
                cancelar();
            }
            modificar=false;
        }else{
            Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Desea guardar los datos?");
            Optional<ButtonType> accion=alerta.showAndWait();
            if(accion.get()==ButtonType.OK){
                p.setRuc(Integer.parseInt(rucProveedores.getText()));
                p.setNombre(nombreProveedores.getText());
                p.setDireccion(direccionProveedores.getText());
                p.setTelefono(Integer.parseInt(telefonoProveedores.getText()));
                if(p.insertar()){
                    Alert ins=new Alert(Alert.AlertType.INFORMATION);
                    ins.setTitle("Aviso");
                    ins.setHeaderText(null);
                    ins.setContentText("Datos insertados correctamente");
                    ins.show();
                    limpiarTexto();
                    cargarDatos();
                    cancelar();
                }else{
                    Alert ins=new Alert(Alert.AlertType.ERROR);
                    ins.setTitle("Aviso");
                    ins.setHeaderText(null);
                    ins.setContentText("Error al insertar.Contacte con al Administrador");
                    ins.show();
                    limpiarTexto();
                    cancelar();
                }
            }           
        }
    }
    @FXML
    private void modificar(ActionEvent event) {
        nombreProveedores.setDisable(false);
        direccionProveedores.setDisable(false);
        telefonoProveedores.setDisable(false);
        btnAgregar.setDisable(true);// se deshabilita el botcon nuevo
        btnEliminar.setDisable(true);
        btnGuardar.setDisable(false);
        btnModificar.setDisable(true);
        nombreProveedores.requestFocus();
        modificar=true;
    }
    
    public void limpiarTexto(){
        rucProveedores.setText("");
        nombreProveedores.setText("");
        direccionProveedores.setText("");
        telefonoProveedores.setText("");
    }
    
    @FXML
    private void cancelar() {
        rucProveedores.setDisable(true);
        nombreProveedores.setDisable(true);
        direccionProveedores.setDisable(true);
        telefonoProveedores.setDisable(true);
        btnAgregar.setDisable(false);
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        limpiarTexto();
    }
    
    @FXML
    private void nuevo(ActionEvent event) {
        rucProveedores.setDisable(false);
        nombreProveedores.setDisable(false);
        direccionProveedores.setDisable(false);
        telefonoProveedores.setDisable(false);
        btnAgregar.setDisable(true);// se deshabilita el botcon nuevo
        btnGuardar.setDisable(false);
        rucProveedores.requestFocus();
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("El Sistema Comunica:");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea eliminar los datos?");
        Optional<ButtonType> accion=alerta.showAndWait();
        if(accion.get()==ButtonType.OK){
            p.setRuc(Integer.parseInt(rucProveedores.getText()));
            if(p.eliminar()){
                 Alert del=new Alert(Alert.AlertType.INFORMATION);
                del.setTitle("El Sistema Comunica:");
                del.setHeaderText(null);
               del.setContentText("Datos eliminados correctamente");
               del.show();
               limpiarTexto();
               cargarDatos();
               cancelar();
            }else{
                Alert del=new Alert(Alert.AlertType.ERROR);
                del.setTitle("El Sistema Comunica:");
                del.setHeaderText(null);
               del.setContentText("Datos no eliminados.Contacte al administrador");
               del.show();
               limpiarTexto();
               cancelar();
            }
        }
    }
}
