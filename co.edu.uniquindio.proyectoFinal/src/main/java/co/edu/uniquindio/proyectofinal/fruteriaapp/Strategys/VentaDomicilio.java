package co.edu.uniquindio.proyectofinal.fruteriaapp.Strategys;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Cliente;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Empleado;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Venta;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Service.StrategyVenta;
import javafx.scene.control.Alert;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data

public class VentaDomicilio implements StrategyVenta {

    private String idVenta;
    private LocalDate fechaDeVenta;
    private Cliente clienteAsociado;
    private Empleado empleadoAsociado;
    private String direccion;
    private String total;
    List<Producto> productosAsociado;

    public VentaDomicilio() {
    }

    public VentaDomicilio(String idVenta, LocalDate fechaDeVenta, Cliente clienteAsociado, Empleado empleadoAsociado, String direccion, String total, List<Producto> productosAsociado) {
        this.idVenta = idVenta;
        this.fechaDeVenta = fechaDeVenta;
        this.clienteAsociado = clienteAsociado;
        this.empleadoAsociado = empleadoAsociado;
        this.direccion = direccion;
        this.total = total;
        this.productosAsociado = productosAsociado;
    }

    @Override
    public void realizarVenta(Venta venta) {
        double porcentajeSobreCargo = 0.20;
        double total = Double.parseDouble(venta.getTotal());
        double sobrecargo = total*porcentajeSobreCargo;

        String mensaje = "Domicilio Realizado\n\n" +
                "El domicilio con ID: " + venta.getIdVenta() + " se realizó con éxito.\n" +
                "Se debe cobrar un sobrecargo de: " + sobrecargo + ", que no se agregará al total.\n" +
                "El domicilio será enviado a: " + venta.getClienteAsociado().getDireccion();

        mostrarMensaje("Domicilio", "Información de Domicilio", mensaje, Alert.AlertType.INFORMATION);    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.show();
    }
}
