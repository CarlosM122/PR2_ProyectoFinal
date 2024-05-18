package co.edu.uniquindio.proyectofinal.fruteriaapp.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

public class Venta {

    private String idVenta;
    private LocalDate fechaDeVenta;
    private Cliente clienteAsociado;
    private Empleado empleadoAsociado;
    private String direccion;
    private String total;
    List<Producto> productosAsociado = new ArrayList<>();
}
