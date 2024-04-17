package co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;

import java.util.List;

public class ProductoController {

    ModelFactory modelFactory;

    public ProductoController(){
        modelFactory = ModelFactory.getInstance();
    }
    public boolean agregarProducto(Producto producto) {
        return modelFactory.agregarProducto(producto);
    }

    public List<Producto> obtenerProductos() {
        return modelFactory.obtenerProductos();
    }

    public List<Producto> buscarMenoresACantidad(int cantidad) {
        return modelFactory.buscarMenoresACantidad(cantidad);
    }

    public List<Producto> obtenerProductosMayores(int precio) {
        return modelFactory.obtenerProductosMayores(precio);
    }
}
