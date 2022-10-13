/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.producto;

/**
 * FXML Controller class
 *
 * @author fabri
 */
public class ProductosController implements Initializable {

    boolean modificar = false;
    producto p = new producto();
    @FXML
    private ImageView imagen;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtprecio;
    @FXML
    private TextField txtcantidad;
    @FXML
    private TextField txtnombre;
    @FXML
    private TextArea txtdescripcion;
    @FXML
    private Button btnagregar;
    @FXML
    private Button btneliminar;
    @FXML
    private Button btnmodificar;
    @FXML
    private Hyperlink linkcargar;
    @FXML
    private TableView<producto> tabla;
    @FXML
    private TableColumn<producto, Integer> columcodigo;
    @FXML
    private TableColumn<producto, String> columnombre;
    @FXML
    private TableColumn<producto, Integer> columprecio;
    @FXML
    private TableColumn<producto, Integer> columcantidad;
    @FXML
    private TableColumn<producto, String> columdescripcion;
    @FXML
    private Button btncanelar;
    @FXML
    private Button btnguardar;
    String output = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }

    @FXML
    private void cargar(ActionEvent event) throws FileNotFoundException {
        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(new Stage());
        Image image = new Image(new FileInputStream(file), 100, 100, false, false);
        imagen.setImage(image);
        output = file.getPath();
    }

    @FXML
    private void agregar(ActionEvent event) {
        linkcargar.setDisable(false);
        txtid.setDisable(false);
        txtprecio.setDisable(false);
        txtcantidad.setDisable(false);
        txtnombre.setDisable(false);
        txtdescripcion.setDisable(false);
        btnagregar.setDisable(true);
        btnguardar.setDisable(false);
        btncanelar.setDisable(false);
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("El Sistema Comunica:");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea eliminar los datos?");
        Optional<ButtonType> accion = alerta.showAndWait();
        if (accion.get() == ButtonType.OK) {
            p.setCodigo(Integer.parseInt(txtid.getText()));
            if (p.eliminar()) {
                Alert del = new Alert(Alert.AlertType.INFORMATION);
                del.setTitle("El Sistema Comunica:");
                del.setHeaderText(null);
                del.setContentText("Datos eliminados correctamente");
                del.show();
                limpiarTexto();
                cargarDatos();
                cancelar();
            } else {
                Alert del = new Alert(Alert.AlertType.ERROR);
                del.setTitle("El Sistema Comunica:");
                del.setHeaderText(null);
                del.setContentText("Datos no eliminados.Contacte al administrador");
                del.show();
                limpiarTexto();
                cancelar();
            }
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        txtcantidad.setDisable(false);
        txtdescripcion.setDisable(false);
        txtid.setDisable(false);
        txtnombre.setDisable(false);
        btnguardar.setDisable(true);// se deshabilita el botcon nuevo
        btneliminar.setDisable(true);
        btnguardar.setDisable(false);
        btnmodificar.setDisable(true);
        txtprecio.setDisable(false);
        txtnombre.requestFocus();
        modificar = true;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        cancelar();
    }

    private void cancelar() {
        txtcantidad.setDisable(true);
        txtdescripcion.setDisable(true);
        txtid.setDisable(true);
        txtnombre.setDisable(true);
        txtprecio.setDisable(true);
        btnagregar.setDisable(false);
        btnguardar.setDisable(true);
        btnmodificar.setDisable(true);
        btneliminar.setDisable(true);
        limpiarTexto();
    }

    @FXML
    private void guardar(ActionEvent event) throws FileNotFoundException {
        if (modificar) {
            p.setCodigo(Integer.parseInt(txtid.getText()));
            p.setNombre(txtnombre.getText());
            p.setDescripcion(txtdescripcion.getText());
            p.setPrecio(Integer.parseInt(txtprecio.getText()));
            p.setCantidad(Integer.parseInt(txtcantidad.getText()));
            if (p.modificar()) {
                Alert ins = new Alert(Alert.AlertType.INFORMATION);
                ins.setTitle("Aviso");
                ins.setHeaderText(null);
                ins.setContentText("Datos modificados correctamente");
                ins.show();
                limpiarTexto();
                cargarDatos();
                cancelar();

            } else {
                Alert ins = new Alert(Alert.AlertType.ERROR);
                ins.setTitle("Aviso");
                ins.setHeaderText(null);
                ins.setContentText("Error al moficar.Contacte con al Administrador");
                ins.show();
                limpiarTexto();
                cancelar();
            }
            modificar = false;
        } else {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("¿Desea guardar los datos?");
            Optional<ButtonType> accion = alerta.showAndWait();
            if (accion.get() == ButtonType.OK) {
                String home = System.getProperty("user.home");
                String carpeta = home + "/Documents/Productos";
                new File(carpeta).mkdir();
                String path = carpeta + "/" + txtnombre.getText() + txtid.getText() + ".jpg";
                Path outputPath = Paths.get(path);
                try {
                    Files.copy(Paths.get(output), outputPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(ProductosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                FileInputStream stream = new FileInputStream(path);
                Image image = new Image(stream);
                imagen.setImage(image);
                p.setCodigo(Integer.parseInt(txtid.getText()));
                p.setNombre(txtnombre.getText());
                p.setDescripcion(txtdescripcion.getText());
                p.setPrecio(Integer.parseInt(txtprecio.getText()));
                p.setCantidad(Integer.parseInt(txtcantidad.getText()));

                p.setImagen(path.replace("\\", "/"));
                if (p.insertar()) {
                    Alert ins = new Alert(Alert.AlertType.INFORMATION);
                    ins.setTitle("Aviso");
                    ins.setHeaderText(null);
                    ins.setContentText("Datos insertados correctamente");
                    ins.show();
                    limpiarTexto();
                    cargarDatos();
                    cancelar();
                } else {
                    Alert ins = new Alert(Alert.AlertType.ERROR);
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

    private void cargarDatos() {
        ArrayList<producto> lista = p.consulta();
        ObservableList<producto> registros = FXCollections.observableArrayList(lista);

        columcantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columdescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tabla.setItems(registros);
    }

    private void limpiarTexto() {
        txtcantidad.setText("");
        txtdescripcion.setText("");
        txtid.setText("");
        txtnombre.setText("");
        txtprecio.setText("");
    }

    @FXML
    private void mostrardatos(MouseEvent event) throws FileNotFoundException {
        producto p = tabla.getSelectionModel().getSelectedItem();
        txtid.setText(String.valueOf(p.getCodigo()));
        txtnombre.setText(String.valueOf(p.getNombre()));
        txtdescripcion.setText(String.valueOf(p.getDescripcion()));
        txtprecio.setText(String.valueOf(p.getPrecio()));
        txtcantidad.setText(String.valueOf(p.getCantidad()));
        btnmodificar.setDisable(false);
        btneliminar.setDisable(false);
        btnagregar.setDisable(true);

        System.out.println(p.getImagen());
        FileInputStream stream = new FileInputStream(p.getImagen());
        Image image = new Image(stream,100,100,false,false);
        imagen.setImage(image);

    }

}
