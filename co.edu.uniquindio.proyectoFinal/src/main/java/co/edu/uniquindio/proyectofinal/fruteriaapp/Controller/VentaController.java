package co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
public class VentaController {



    public class VentaView {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button btnAgregarCliente;

        @FXML
        private TableColumn<?, ?> tcFecha;

        @FXML
        private TableColumn<?, ?> tcIdApellido;

        @FXML
        private TableColumn<?, ?> tcIdCliente;

        @FXML
        private TextField txtCantidadProducto;

        @FXML
        private TextField txtFecha;

        @FXML
        private TextField txtidCliente;

        @FXML
        private TextField txtproductoComprado;

        @FXML
        void onAgregarCliente(ActionEvent event) {

        }

        @FXML
        void initialize() {

        }

    }
}