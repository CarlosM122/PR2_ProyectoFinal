package co.edu.uniquindio.proyectofinal.fruteriaapp.Factory;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Controller.ProductoController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Cliente;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Fruteria;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;

import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Fruteria fruteria;

    private ModelFactory(){
        fruteria = new Fruteria();
        inicializarDatos();
    }

    public static ModelFactory getInstance(){
        if(modelFactory==null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private void inicializarDatos(){
        fruteria.setNombre("Amaranto");

        Producto producto = Producto.builder()
                .nombre("Naranja")
                .idProducto("B45")
                .cantidadStock(23)
                .precio(600)
                .build();

        fruteria.getProductoList().add(producto);
    }

    public boolean agregarProducto(Producto producto) {
        return fruteria.agergarProducto(producto);
    }

    public List<Producto> obtenerProductos() {
        return fruteria.getProductoList();
    }

    public List<Cliente> obtenerClientes() {
        return fruteria.getClienteList();
    }

    public boolean crearCliente(Cliente cliente) {
        return fruteria.crearCliente(cliente);
    }
}
