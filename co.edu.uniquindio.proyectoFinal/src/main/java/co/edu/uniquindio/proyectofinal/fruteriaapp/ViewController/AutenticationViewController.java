package co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Controller.AutenticationController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.FruteriaApplication;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutenticationViewController {
    Stage stage;
    AutenticationController autenticationController;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdministrador;

    @FXML
    private Button btnEmpleado;

    @FXML
    private Button btnRegistrar;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtIdEmpleado;

    @FXML
    private TextField txtNewIdEmpleado;

    @FXML
    private TextField txtNombre;

    @FXML
    void onAdministrador(ActionEvent event) {
        ingresarAdministrador(txtIdEmpleado.getText());
    }

    @FXML
    void onEmpleado(ActionEvent event) {
        ingresarEmpleado(txtIdEmpleado.getText());
    }

    @FXML
    void onRegistrar(ActionEvent event) {
        crearEmpleado();
    }

    @FXML
    void initialize() {
        autenticationController = new AutenticationController();
    }

    private void ingresarAdministrador(String idEmpleado){
        if(buscarEmpleado(idEmpleado)){

        }
        else {

        }
    }

    private void ingresarEmpleado(String idEmpleado){
        if(buscarEmpleado(idEmpleado)){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(FruteriaApplication.class.getResource("ProductoView.fxml"));
                Parent root = fxmlLoader.load();
                Stage nuevaVentana = new Stage();
                Scene scene = new Scene(root);
                nuevaVentana.setTitle("Nueva Ventana");
                nuevaVentana.setScene(scene);
                nuevaVentana.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Manejar el caso en que buscarEmpleado(idEmpleado) sea falso
        }
    }

    private boolean buscarEmpleado(String idEmpleado) {
        return autenticationController.buscarEmpleado(idEmpleado);
    }

    private void crearEmpleado() {
        if(validarFormulario()){
            Empleado empleado = construirDatosEmpleado();
            if(autenticationController.crearEmpleado(empleado)){
                mostrarMensaje("Informacion Cliente","Cliente Creado","El cliente se creao correctamente", Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje("Informacion Cliente","Cliente No Creado","El cliente no se creo correctamente",Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje(" Informacion Cliente","Campos Vacios","Hay campos vacios que son necesarios",Alert.AlertType.ERROR);
        }
    }

    private boolean validarFormulario() {
        if(txtNombre.getText().isEmpty()||txtApellido.getText().isEmpty()||txtIdEmpleado.getText().isEmpty()){
            return false;
        }
        return true;
    }

    private Empleado construirDatosEmpleado() {
        return Empleado.builder()
                .nombre(txtNombre.getText())
                .apellido(txtApellido.getText())
                .idEmpleado(txtIdEmpleado.getText())
                .build();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
    }

}
