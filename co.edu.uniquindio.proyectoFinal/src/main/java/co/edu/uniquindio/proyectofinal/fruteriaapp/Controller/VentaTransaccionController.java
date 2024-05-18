package co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Dto.VentaDto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;

import java.util.List;

public class VentaTransaccionController {
    ModelFactory modelFactory;

    public VentaTransaccionController() {
        modelFactory = ModelFactory.getInstance();
    }

    public List<Producto> buscarProductos() {
        return modelFactory.buscarProductos();
    }

    public boolean validarDatos(VentaDto ventaDto, List<Producto> productos) {
        return modelFactory.validarDatos(ventaDto, productos);
    }

    public String obtenerNombreCliente(String id) {
        return modelFactory.obtenerNombreCliente(id);
    }

    public boolean validarDatosDomicilio(VentaDto ventaDto, List<Producto> productosAsociados) {
        return modelFactory.validarDatosDomicilio(ventaDto, productosAsociados);
    }

    public void notificar() {
        modelFactory.norificar();
    }
}
