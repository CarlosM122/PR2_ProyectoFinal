package co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Controller.ClienteController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClienteViewController {

    Cliente clienteSeleccionado;
    ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();
    ClienteController clienteController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Cliente> ClienteTable;

    @FXML
    private Button btnActualizarCliente;

    @FXML
    private Button btnCrearCliente;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private TableColumn<Cliente, String> tcApellido;

    @FXML
    private TableColumn<Cliente, String> tcCedula;

    @FXML
    private TableColumn<Cliente, String> tcDireccion;

    @FXML
    private TableColumn<Cliente, String> tcIdCliente;

    @FXML
    private TableColumn<Cliente, String> tcNombre;

    @FXML
    private TableColumn<Cliente, String> tcNumCelular;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtIdCliente;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumCelular;

    @FXML
    private TextField txtFiltro;

    @FXML
    void onActualizarCliente(ActionEvent event) {
        actualizarCliente(clienteSeleccionado);
    }

    @FXML
    void onCrearCliente(ActionEvent event) {
        crearCliente();
    }

    @FXML
    void onEliminarCliente(ActionEvent event) {
        eliminarCliente(clienteSeleccionado.getIdCliente());
    }

    @FXML
    void initialize() {
        clienteController = new ClienteController();
        initView();

        txtFiltro.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrarClientes(newValue);
            }
        });
    }

    private void filtrarClientes(String filtro) {
        ObservableList<Cliente> clientesFiltrados = FXCollections.observableArrayList();
        if(txtFiltro.getText().isEmpty()){
            clientesFiltrados.addAll(listaClientes);
        }
        else{
            for(Cliente cliente:listaClientes){
                if(cliente.getNombre().toLowerCase().contains(filtro.toLowerCase()) || cliente.getCedula().toLowerCase().contains(filtro.toLowerCase())){
                    clientesFiltrados.add(cliente);
                }
            }
        }
        ClienteTable.setItems(clientesFiltrados);
    }

    private void initView() {
        initDataBinding();
        obtenerClientes();
        ClienteTable.getItems().clear();
        ClienteTable.setItems(listaClientes);
        listenerSelection();
    }

    private void obtenerClientes() {
        listaClientes.addAll(clienteController.obtenerClientes());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getApellido())));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCedula())));
        tcNumCelular.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumCelular())));
        tcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDireccion())));
        tcIdCliente.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdCliente())));
    }
    private void listenerSelection() {
        ClienteTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            clienteSeleccionado = newSelection;
            mostrarInformacionCliente(clienteSeleccionado);
        });
    }

    private void mostrarInformacionCliente(Cliente cliente) {
        if(clienteSeleccionado!=null){
            txtNombre.setText(clienteSeleccionado.getNombre());
            txtApellido.setText(clienteSeleccionado.getApellido());
            txtCedula.setText(clienteSeleccionado.getCedula());
            txtDireccion.setText(clienteSeleccionado.getDireccion());
            txtNumCelular.setText(String.valueOf(clienteSeleccionado.getNumCelular()));
            txtIdCliente.setText(clienteSeleccionado.getIdCliente());
        }
    }

    private void crearCliente() {
        if(validarFormulario()){
            Cliente cliente = construirDatosCliente();
            if(clienteController.crearCliente(cliente)){
                listaClientes.add(cliente);
                mostrarMensaje("Informacion Cliente","Cliente Creado","El cliente se creao correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
            }else{
                mostrarMensaje("Informacion Cliente","Cliente No Creado","El cliente no se creo correctamente",Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje(" Informacion Cliente","Campos Vacios","Hay campos vacios que son necesarios",Alert.AlertType.ERROR);
        }
    }
    private void eliminarCliente(String idCliente) {
        for (Cliente cliente:listaClientes){
            if(cliente.getIdCliente().equalsIgnoreCase(idCliente)){
                listaClientes.remove(cliente);
                mostrarMensaje("Información Cliente", "Cliente Eliminado", "El cliente se eliminó correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
                return;
            }
        }
    }

    private void actualizarCliente(Cliente clienteSeleccionado) {
        if(clienteSeleccionado!=null){
            if(validarFormulario()){
                Cliente cliente = construirDatosCliente();
                clienteSeleccionado.setNombre(cliente.getNombre());
                clienteSeleccionado.setApellido(cliente.getApellido());
                clienteSeleccionado.setIdCliente(cliente.getIdCliente());
                clienteSeleccionado.setCedula(cliente.getCedula());
                clienteSeleccionado.setDireccion(cliente.getDireccion());
                clienteSeleccionado.setNumCelular(cliente.getNumCelular());
                listaClientes.remove(clienteSeleccionado);
                listaClientes.add(cliente);
                ClienteTable.setItems(null);
                ClienteTable.setItems(listaClientes);
            }
        }
    }

    private void limpiarCamposProducto() {
        txtNombre.setText("");
        txtCedula.setText(String.valueOf(""));
        txtIdCliente.setText(String.valueOf(""));
        txtNumCelular.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }

    private boolean validarFormulario() {
        if(txtNombre.getText().isEmpty()||txtApellido.getText().isEmpty()||txtCedula.getText().isEmpty()||txtDireccion.getText().isEmpty()||txtNumCelular.getText().isEmpty()||txtIdCliente.getText().isEmpty()){
            return false;
        }
        return true;
    }

    private Cliente construirDatosCliente() {
        return Cliente.builder()
                .nombre(txtNombre.getText())
                .numCelular(txtNumCelular.getText())
                .apellido(txtApellido.getText())
                .cedula(txtCedula.getText())
                .direccion(txtDireccion.getText())
                .idCliente(txtIdCliente.getText())
                .build();
    }

}