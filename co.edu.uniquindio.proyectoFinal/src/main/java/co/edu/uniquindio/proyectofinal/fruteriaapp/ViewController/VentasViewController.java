package co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Controller.VentasController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Dto.VentaDto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Service.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VentasViewController implements Observer{

    VentasController ventasController;
    VentaDto ventaDtoSeleccionada;
    ObservableList<VentaDto> listaVentas = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<VentaDto> TableVentas;

    @FXML
    private Button btnVerDetalles;

    @FXML
    private TableColumn<VentaDto, String> tcCedulaCliente;

    @FXML
    private TableColumn<VentaDto, String> tcDireccion;

    @FXML
    private TableColumn<VentaDto, String> tcFechaVenta;

    @FXML
    private TableColumn<VentaDto, String> tcIDEmpleado;

    @FXML
    private TableColumn<VentaDto, String> tcIDVenta;

    @FXML
    private TableColumn<VentaDto, String> tcNombreCliente;

    @FXML
    private TableColumn<VentaDto, String> tcTotal;

    @FXML
    void initialize() {
        ventasController = new VentasController();
        initView();
        ventasController.agregarseComoObserver(this);
    }

    private void initView() {
        initDataBinding();
        obtenerVentas();
        TableVentas.getItems().clear();
        TableVentas.setItems(listaVentas);
        listenerSelection();
    }

    private void obtenerVentas() {
        listaVentas.addAll(ventasController.obtenerVentas());
    }

    private void initDataBinding() {
        tcIDVenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idVenta()));
        tcCedulaCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedulaCliente()));
        tcFechaVenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaDeVenta()));
        tcTotal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().total()));
        tcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccionCliente()));
        tcIDEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idEmpleado()));
        tcNombreCliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCliente()));
    }

    private void listenerSelection() {
        TableVentas.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            ventaDtoSeleccionada = newSelection;
        });
    }

    @Override
    public void uptade() {
        listaVentas.setAll(ventasController.obtenerVentas());
    }
}