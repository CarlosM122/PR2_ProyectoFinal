package co.edu.uniquindio.proyectofinal.fruteriaapp.Factory;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Cliente;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Empleado;
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

        Cliente cliente = Cliente.builder()
                .nombre("Raul")
                .apellido("Ramirez")
                .cedula("1098372")
                .direccion("Calle 23 #12-23")
                .numCelular(3129786)
                .idCliente("FG89")
                .build();

        Cliente cliente1 = Cliente.builder()
                .nombre("Saul")
                .apellido("Sanchez")
                .cedula("1023423")
                .direccion("Calle 78 #12-23")
                .numCelular(312435)
                .idCliente("FG83")
                .build();

        Cliente cliente2 = Cliente.builder()
                .nombre("Nael")
                .apellido("Ramirez")
                .cedula("1098212")
                .direccion("Calle 90 #12-23")
                .numCelular(3126755)
                .idCliente("SD45")
                .build();

        Producto producto = Producto.builder()
                .nombre("Naranja")
                .idProducto("B45")
                .cantidadStock(23)
                .precio(1300)
                .build();

        Producto producto1 = Producto.builder()
                .nombre("Pera")
                .precio(600)
                .idProducto("KJ90")
                .cantidadStock(73)
                .build();

        Producto producto2 = Producto.builder()
                .nombre("Piña")
                .idProducto("BL90")
                .cantidadStock(56)
                .precio(1000)
                .build();

        Empleado empleado = Empleado.builder()
                .nombre("Juan")
                .idEmpleado("GV78")
                .apellido("Amaya")
                .build();

        fruteria.getProductoList().add(producto);
        fruteria.getProductoList().add(producto1);
        fruteria.getProductoList().add(producto2);
        fruteria.getClienteList().add(cliente);
        fruteria.getClienteList().add(cliente1);
        fruteria.getClienteList().add(cliente2);
        fruteria.getEmpleadoList().add(empleado);
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

    public boolean buscarEmpleado(String idEmpleado) {
        return fruteria.buscarEmpleado(idEmpleado);
    }

    public boolean crarEmpleado(Empleado empleado) {
        return fruteria.crearEmpleado(empleado);
    }

    public List<Producto> buscarMenoresACantidad(int cantidad) {
        return fruteria.buscarMenoresACantidad(cantidad);
    }

    public List<Producto> obtenerProductosMayores(int precio) {
        return fruteria.obtenerProductosMayores(precio);
    }
}
