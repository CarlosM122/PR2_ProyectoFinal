package co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Controller.AutenticationController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.FruteriaApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutenticacionViewController {
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
    private TextField txtIdEmpleado;


    @FXML
    void onAdministrador(ActionEvent event) {
        ingresarAdministrador(txtIdEmpleado.getText());
    }

    @FXML
    void onEmpleado(ActionEvent event) {
        ingresarEmpleado(txtIdEmpleado.getText());
    }

    @FXML
    void initialize() {
        autenticationController = new AutenticationController();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }

    private void ingresarAdministrador(String idEmpleado){
        if(buscarEmpleado(idEmpleado)){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(FruteriaApplication.class.getResource("FruteriaAdminView.fxml"));
                Parent root = fxmlLoader.load();
                Stage nuevaVentana = new Stage();
                Scene scene = new Scene(root);
                nuevaVentana.setTitle("Fruteria Amaranto");
                nuevaVentana.setScene(scene);
                nuevaVentana.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            mostrarMensaje("Informacion Login","Error De Inicio De Sesion","El id ingresado es incorrecto",Alert.AlertType.ERROR);
        }
    }

    private void ingresarEmpleado(String idEmpleado){
        if(buscarEmpleado(idEmpleado)){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(FruteriaApplication.class.getResource("FruteriaEmpleadoView.fxml"));
                Parent root = fxmlLoader.load();
                Stage nuevaVentana = new Stage();
                Scene scene = new Scene(root);
                nuevaVentana.setTitle("Fruteria Amaranto");
                nuevaVentana.setScene(scene);
                nuevaVentana.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mostrarMensaje("Informacion Login","Error De Inicio De Sesion","El id no existe, contacte con un administrador",Alert.AlertType.ERROR);
        }
    }


    private boolean buscarEmpleado(String idEmpleado) {
        return autenticationController.buscarEmpleado(idEmpleado);
    }

}
