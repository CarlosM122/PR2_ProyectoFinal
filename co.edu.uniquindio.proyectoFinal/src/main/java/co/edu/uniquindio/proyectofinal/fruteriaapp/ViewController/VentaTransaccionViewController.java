package co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController;

import java.net.URL;
import java.util.*;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Controller.VentaTransaccionController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Dto.VentaDto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.Getter;
import lombok.Setter;

public class VentaTransaccionViewController {

    VentaTransaccionController ventaController;
    ObservableList<Producto> productos = FXCollections.observableArrayList();
    Producto productoSeleccionado;
    @Setter
    @Getter
    List<Producto> productosAsociados = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Producto> TableResumen;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnCancelarVenta;

    @FXML
    private Button btnDomicilio;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRealizarVenta;

    @FXML
    private TableColumn<Producto, String> tcIDProducto;

    @FXML
    private TableColumn<Producto, String> tcNombreProducto;

    @FXML
    private TableColumn<Producto, String> tcPrecioUnitario;

    @FXML
    private TableColumn<Producto, String> tcCantidad;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtCedulaCliente;

    @FXML
    private TextField txtFechaVenta;

    @FXML
    private TextField txtIDVenta;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtIDEmpleado;

    @FXML
    void onAgregarProducto(ActionEvent event) {
        agregarProducto(txtIdProducto.getText(), txtCantidad.getText());
    }

    @FXML
    void onCancelarVenta(ActionEvent event) {
        cancelarVenta();
    }

    @FXML
    void onDomicilio(ActionEvent event) {
        realizarDomicilio();
    }


    @FXML
    void onEliminar(ActionEvent event) {
        eliminarProducto(txtIdProducto.getText());

    }

    @FXML
    void onRealizarVenta(ActionEvent event) {
        realizarVenta();
    }

    @FXML
    void initialize() {
        ventaController = new VentaTransaccionController();
        initView();
    }

    private void initView() {
        txtTotal.setText(String.valueOf(0));
        initDataBinding();
        obtenerProductos();
        listenerSelection();
    }

    private void listenerSelection() {
        TableResumen.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            mostrarInformacionProducto(productoSeleccionado);
        });
    }

    private void mostrarInformacionProducto(Producto productoSeleccionado) {
        if (productoSeleccionado != null) {
            txtIdProducto.setText(productoSeleccionado.getIdProducto());
            txtCantidad.setText(tcCantidad.getCellData(productoSeleccionado));
        }
    }

    private void obtenerProductos() {
        productos.addAll(ventaController.buscarProductos());
    }

    private void eliminarProducto(String idProducto) {
        for (Producto producto : productos) {
            if (producto.getIdProducto().equalsIgnoreCase(idProducto)) {
                producto.setCantidadStock(producto.getCantidadStock() + Integer.parseInt(tcCantidad.getCellData(producto)));
                notificar();
                double total = calcularTotaleliminado(txtCantidad.getText(), idProducto);
                txtTotal.setText(String.valueOf(total));
                TableResumen.getItems().remove(producto);
                mostrarMensaje("Información Producto", "Producto Eliminado", "El producto se eliminó correctamente", Alert.AlertType.INFORMATION);
                limpiarCamposProducto();
            }
        }
    }

    private double calcularTotaleliminado(String cantidad, String idProducto) {
        double total = Double.parseDouble(txtTotal.getText());
        for (Producto producto : productos) {
            if (producto.getIdProducto().equalsIgnoreCase(idProducto)) {
                total -= producto.getPrecio() * Double.parseDouble(cantidad);
            }
        }
        return total;
    }

    private void limpiarCamposProducto() {
        txtIdProducto.setText("");
        txtCantidad.setText("");
    }

    private void initDataBinding() {
        tcIDProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProducto()));
        tcNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcPrecioUnitario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecio())));
    }

    private void agregarProducto(String idProducto, String cantidad) {
        boolean encontrado = false;

        if (validarFormularioProducto()) {
            int cantidadParsed = parsearEntero(cantidad);

            if (cantidadParsed == 0) {
                mostrarMensaje("Producto", "Cantidad Nula", "Usted ingresó una cantidad nula, por favor ingrese una cantidad válida", Alert.AlertType.WARNING);
                return;
            }

            for (Producto producto : productos) {
                if (producto.getIdProducto().equals(idProducto)) {
                    if (producto.getCantidadStock() < cantidadParsed) {
                        mostrarMensaje("Producto", "Falta En stock", "No hay suficiente stock del producto para cubrir la venta", Alert.AlertType.ERROR);
                        encontrado = true;
                    } else {
                        TableResumen.getItems().add(producto);
                        productosAsociados.add(producto);
                        tcCantidad.setCellValueFactory(cellData -> new SimpleStringProperty(cantidad));
                        producto.setCantidadStock(producto.getCantidadStock() - cantidadParsed);
                        notificar();
                        double totalCalculado = calcularTotal(cantidad, idProducto);
                        txtTotal.setText(String.valueOf(totalCalculado));
                        limpiarCamposProducto();
                        encontrado = true;
                    }
                    break;
                }
            }
        } else {
            mostrarMensaje("Información Producto", "Campos Vacíos", "Hay campos necesarios vacíos", Alert.AlertType.ERROR);
        }

        if (!encontrado && validarFormularioProducto()) {
            mostrarMensaje("Información Producto", "Error", "El producto no se encuentra en los productos de la tienda", Alert.AlertType.ERROR);
        }
    }

    private void notificar() {
        ventaController.notificar();
    }

    private double calcularTotal(String cantidad, String idProducto) {
        double total = Double.parseDouble(txtTotal.getText());
        for (Producto producto : productos) {
            if (producto.getIdProducto().equalsIgnoreCase(idProducto)) {
                total += producto.getPrecio() * Double.parseDouble(cantidad);
            }
        }
        return total;
    }


    private void realizarVenta() {
        if (validarFormulario()) {
            double totalVenta;
            try {
                totalVenta = Double.parseDouble(txtTotal.getText());
            } catch (NumberFormatException e) {
                mostrarMensaje("Error De Venta", "Venta Nula", "El total de la venta no es un número válido", Alert.AlertType.ERROR);
                return;
            }

            if (totalVenta == 0) {
                mostrarMensaje("Error De Venta", "Venta Nula", "Está intentando hacer una venta nula", Alert.AlertType.ERROR);
                return;
            }

            if (productosAsociados.isEmpty()) {
                mostrarMensaje("Error De Venta", "Venta Nula", "No hay productos asociados para la venta", Alert.AlertType.ERROR);
                return;
            }

            crearDto();
            boolean ventaValida = ventaController.validarDatos(crearDto(), productosAsociados);

            if (ventaValida) {
                mostrarMensaje("Venta", "Venta Realizada", "Venta realizada correctamente", Alert.AlertType.INFORMATION);
                limpiarlista();
                limpiarCamposVenta();
                limpiarCamposProducto();
            } else {
                mostrarMensaje("Venta", "Error", "No se pudo realizar la venta correctamente, revise las identificaciones, los campos o registre al cliente", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Error", "Campos Vacíos", "Hay campos vacíos", Alert.AlertType.ERROR);
        }
    }

    private void realizarDomicilio() {
        if (validarFormulario()) {
            if (Double.parseDouble(txtTotal.getText()) == 0) {
                mostrarMensaje("Error De Venta", "Venta Nula", "Está intentando hacer una venta nula", Alert.AlertType.ERROR);
            } else {
                if (ventaController.validarDatosDomicilio(crearDto(), productosAsociados)) {
                    mostrarMensaje("Venta", "Venta Realizada", "Venta realizada correctamente", Alert.AlertType.INFORMATION);
                    limpiarlista();
                    limpiarCamposVenta();
                    limpiarCamposProducto();
                } else {
                    mostrarMensaje("Venta", "Error", "No se pudo realizar la venta correctamente, revise las identificaciones", Alert.AlertType.ERROR);
                }
            }
        } else {
            mostrarMensaje("Error", "Campos Vacíos", "Hay campos vacíos", Alert.AlertType.ERROR);
        }
    }

    private void limpiarlista() {
        productosAsociados.clear();
    }


    private VentaDto crearDto() {
        String nombre = ventaController.obtenerNombreCliente(txtIDVenta.getText());
        return new VentaDto(
                txtIDVenta.getText(),
                txtFechaVenta.getText(),
                nombre,
                txtCedulaCliente.getText(),
                "",
                txtIDEmpleado.getText(),
                txtTotal.getText()
        );
    }

    private boolean validarFormulario() {
        return !txtIDVenta.getText().isEmpty() || txtFechaVenta.getText().isEmpty() || txtIDEmpleado.getText().isEmpty() || txtCedulaCliente.getText().isEmpty();
    }


    private int parsearEntero(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }

    private boolean mostrarConfirmacion(String titulo, String header, String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private boolean validarFormularioProducto() {
        return !txtIdProducto.getText().isEmpty();
    }

    private void cancelarVenta() {
        if (mostrarConfirmacion("Confirmación Requerida", "Cancelar Venta", "¿Estás seguro de que quieres cancelar la venta?")) {
            TableResumen.getItems().clear();
            txtTotal.setText("0");
            limpiarCamposVenta();
        }
    }

    private void limpiarCamposVenta() {
        txtIDVenta.setText("");
        txtFechaVenta.setText("");
        txtCedulaCliente.setText("");
        txtIDEmpleado.setText("");
        txtIdProducto.setText("");
        txtCantidad.setText("");
    }

}