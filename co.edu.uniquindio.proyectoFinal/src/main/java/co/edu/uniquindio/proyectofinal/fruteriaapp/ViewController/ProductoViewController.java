package co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Controller.ProductoController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProductoViewController {
    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    Producto productoSeleccionado;
    ProductoController productoController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Actualizarbtn;

    @FXML
    private Button Agregarbtn;

    @FXML
    private Button Eliminarbtn;

    @FXML
    private TableView<Producto> TableProducto;

    @FXML
    private TableColumn<Producto,String> tcIdProducto;

    @FXML
    private TableColumn<Producto,String> tcNombre;

    @FXML
    private TableColumn<Producto,String> tcStock;

    @FXML
    private TableColumn<Producto, String> tcPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecio;

    @FXML
    void onActualizar(ActionEvent event) {
        actualizarProducto(productoSeleccionado);
    }

    @FXML
    void onAgregar(ActionEvent event) {
        crearProducto();
    }

    @FXML
    void onEliminar(ActionEvent event) {
        eliminarProducto(productoSeleccionado.getIdProducto());
    }
    @FXML
    void initialize() {
        productoController = new ProductoController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerPoductos();
        TableProducto.getItems().clear();
        TableProducto.setItems(listaProductos);
        listenerSelection();
    }

    private void obtenerPoductos() {
        listaProductos.addAll(productoController.obtenerProductos());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcIdProducto.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdProducto())));
        tcStock.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCantidadStock())));
        tcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
    }
    private void listenerSelection() {
        TableProducto.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            productoSeleccionado = newSelection;
            mostrarInformacionProducto(productoSeleccionado);
        });
    }

    private void mostrarInformacionProducto(Producto productoSeleccionado) {
        if(productoSeleccionado!=null){
            txtNombreProducto.setText(productoSeleccionado.getNombre());
            txtCantidad.setText(String.valueOf(productoSeleccionado.getCantidadStock()));
            txtIdProducto.setText(String.valueOf(productoSeleccionado.getIdProducto()));
            txtPrecio.setText(String.valueOf(productoSeleccionado.getPrecio()));
        }
    }

    private void crearProducto() {
        if(validarFormulario()){
            Producto producto = construirDatosProducto();
            if(productoController.agregarProducto(producto)){
                listaProductos.add(producto);
                mostrarMensaje("Informacion Producto","Producto Agregado","El Producto se agrego correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
            }else{
                mostrarMensaje("Informacion Producto","Producto No Agregado","El producto no se agrego correctamente",Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje("Informacion Producto","Campos Vacios","Hay campos vacios que son necesarios",Alert.AlertType.ERROR);
        }
    }
    private void eliminarProducto(String idProducto) {
        for (Producto producto:listaProductos){
            if(producto.getIdProducto().equalsIgnoreCase(idProducto)){
                listaProductos.remove(producto);
                mostrarMensaje("Información Producto", "Producto Eliminado", "El producto se eliminó correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
                return;
            }
        }
    }

    private void actualizarProducto(Producto productoSeleccionado) {
        if(productoSeleccionado!=null){
            if(validarFormulario()){
                Producto producto = construirDatosProducto();
                productoSeleccionado.setNombre(producto.getNombre());
                productoSeleccionado.setIdProducto(producto.getIdProducto());
                productoSeleccionado.setCantidadStock(producto.getCantidadStock());
                listaProductos.remove(productoSeleccionado);
                listaProductos.add(producto);
                TableProducto.setItems(null);
                TableProducto.setItems(listaProductos);
            }
        }
    }

    private void limpiarCamposProducto() {
        txtNombreProducto.setText("");
        txtCantidad.setText(String.valueOf(""));
        txtIdProducto.setText(String.valueOf(""));
        txtPrecio.setText("");
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
    }

    private boolean validarFormulario() {
        if(txtNombreProducto.getText().isEmpty()||txtIdProducto.getText().isEmpty()||txtCantidad.getText().isEmpty()){
            return false;
        }
        return true;
    }

    private Producto construirDatosProducto() {
        return Producto.builder()
                .nombre(txtNombreProducto.getText())
                .cantidadStock(Integer.parseInt(txtCantidad.getText()))
                .idProducto(txtIdProducto.getText())
                .precio(Double.parseDouble(txtPrecio.getText()))
                .build();
    }
}
