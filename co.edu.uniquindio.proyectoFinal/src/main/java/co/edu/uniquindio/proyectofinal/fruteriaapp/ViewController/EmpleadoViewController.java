package co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Controller.EmpleadoController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Empleado;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmpleadoViewController {

    ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();
    EmpleadoController empleadoController;
    Empleado empleadoSeleccionado;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Empleado> EmpleadoTable;

    @FXML
    private TableColumn<Empleado,String> tcApellido;

    @FXML
    private TableColumn<Empleado,String> tcIdEmpleado;

    @FXML
    private TableColumn<Empleado,String> tcNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtFiltro;

    @FXML
    private TextField txtIdEmpleado;

    @FXML
    private TextField txtNombre;

    @FXML
    void onActualizar(ActionEvent event) {
        actualizarEmpleado(empleadoSeleccionado);
    }

    @FXML
    void onCrear(ActionEvent event) {
        crearEmpleado();
    }

    @FXML
    void onEliminar(ActionEvent event) {
        eliminarEmpleado(empleadoSeleccionado.getIdEmpleado());
    }

    @FXML
    void initialize() {
        empleadoController = new EmpleadoController();
        initView();
        txtFiltro.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrarEmpleados(newValue);
            }
        });
    }

    private void filtrarEmpleados(String filtro) {
        ObservableList<Empleado> empleadosFiltrados = FXCollections.observableArrayList();
        if(txtFiltro.getText().isEmpty()){
            empleadosFiltrados.addAll(listaEmpleados);
        }
        else{
            for(Empleado empleado:listaEmpleados){
                if(empleado.getNombre().toLowerCase().contains(filtro.toLowerCase()) || empleado.getIdEmpleado().toLowerCase().contains(filtro.toLowerCase())){
                    empleadosFiltrados.add(empleado);
                }
            }
        }
        EmpleadoTable.setItems(empleadosFiltrados);
    }

    private void initView() {
        initDataBinding();
        obtenerEmpleados();
        EmpleadoTable.getItems().clear();
        EmpleadoTable.setItems(listaEmpleados);
        listenerSelection();
    }

    private void obtenerEmpleados() {
        listaEmpleados.addAll(empleadoController.obtenerEmpleados());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        tcIdEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdEmpleado()));
    }
    private void listenerSelection() {
        EmpleadoTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->{
            empleadoSeleccionado = newSelection;
            mostrarInformacionEmpleado(empleadoSeleccionado);
        });
    }

    private void mostrarInformacionEmpleado(Empleado empleado) {
        if(empleadoSeleccionado!=null){
            txtNombre.setText(empleadoSeleccionado.getNombre());
            txtApellido.setText(empleadoSeleccionado.getApellido());
            txtIdEmpleado.setText(empleadoSeleccionado.getIdEmpleado());
        }
    }

    private void crearEmpleado() {
        if(validarFormulario()){
            Empleado empleado = construirDatosEmpleado();
            if(empleadoController.crearEmpleado(empleado)){
                listaEmpleados.add(empleado);
                mostrarMensaje("Informacion Empleado","Empleado Creado","El empleado se credo correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
            }else{
                mostrarMensaje("Informacion Empleado","Empleado No Creado","El empleado no se creo correctamente",Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje(" Informacion Empleado","Campos Vacios","Hay campos vacios que son necesarios",Alert.AlertType.ERROR);
        }
    }
    private void eliminarEmpleado(String idEmpleado) {
        for (Empleado empleado:listaEmpleados){
            if(empleado.getIdEmpleado().equalsIgnoreCase(idEmpleado)){
                listaEmpleados.remove(empleado);
                mostrarMensaje("Información Empleado", "Empleado Eliminado", "El empleado se eliminó correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleado();
                return;
            }
        }
    }

    private void actualizarEmpleado(Empleado empleadoSeleccionado) {
        if(empleadoSeleccionado!=null){
            if(validarFormulario()){
                Empleado empleado = construirDatosEmpleado();
                empleadoSeleccionado.setNombre(empleado.getNombre());
                empleadoSeleccionado.setApellido(empleado.getApellido());
                empleadoSeleccionado.setIdEmpleado(empleado.getIdEmpleado());
                listaEmpleados.remove(empleadoSeleccionado);
                listaEmpleados.add(empleado);
                EmpleadoTable.setItems(null);
                EmpleadoTable.setItems(listaEmpleados);
            }
        }
    }

    private void limpiarCamposEmpleado() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtIdEmpleado.setText("");
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }

    private boolean validarFormulario() {
        return !txtNombre.getText().isEmpty() && !txtApellido.getText().isEmpty() && !txtIdEmpleado.getText().isEmpty();
    }

    private Empleado construirDatosEmpleado() {
        return Empleado.builder()
                .nombre(txtNombre.getText())
                .apellido(txtApellido.getText())
                .idEmpleado(txtIdEmpleado.getText())
                .build();
    }

}