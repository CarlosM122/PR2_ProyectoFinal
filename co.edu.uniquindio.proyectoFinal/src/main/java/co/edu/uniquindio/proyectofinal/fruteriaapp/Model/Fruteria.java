package co.edu.uniquindio.proyectofinal.fruteriaapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Fruteria {
    private String nombre;
    private List<Empleado> empleadoList = new ArrayList<>();
    private List<Producto> productoList = new ArrayList<>();
    private List<Cliente> clienteList = new ArrayList<>();

    public Fruteria() {
    }

    public Fruteria(String nombre, List<Empleado> empleadoList, List<Producto> productoList, List<Cliente> clienteList) {
        this.nombre = nombre;
        this.empleadoList = empleadoList;
        this.productoList = productoList;
        this.clienteList = clienteList;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean agergarProducto(Producto producto) {
        Producto encontrarProducto =obtenerProducto(producto.getIdProducto());
        if(encontrarProducto==null){
            getProductoList().add(producto);
            return true;
        }
        else {
            return false;
        }
    }

    private Producto obtenerProducto(String idProducto) {
        Producto producto =null;
        for (Producto producto1:productoList){
            if(producto1.getIdProducto().equalsIgnoreCase(idProducto)){
                producto=producto1;
                break;
            }
        }
        return producto;
    }

    public boolean crearCliente(Cliente cliente) {
        Cliente encontrarCliente= obtenerCliente(cliente.getIdCliente());
        if(encontrarCliente==null){
            getClienteList().add(cliente);
            return true;
        }
        else {
            return false;
        }
    }

    private Cliente obtenerCliente(String idCliente) {
        Cliente cliente=null;
        for (Cliente cliente1:clienteList){
            if(cliente1.getIdCliente().equalsIgnoreCase(idCliente)){
                cliente=cliente1;
                break;
            }
        }
        return cliente;
    }

    public boolean buscarEmpleado(String idEmpleado) {
        Empleado empleadoEncontrado = null;
        for(Empleado empleado:empleadoList){
            if (idEmpleado.equalsIgnoreCase(empleado.getIdEmpleado())){
                empleadoEncontrado=empleado;
                break;
            }
        }
        if(empleadoEncontrado!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean crearEmpleado(Empleado empleado) {
        Empleado empleadoEcontrado = obtenerEmpleado(empleado.getIdEmpleado());
        if(empleadoEcontrado==null){
            empleadoList.add(empleado);
            return true;
        }else {
            return false;
        }
    }

    private Empleado obtenerEmpleado(String idEmpleado) {
        Empleado empleado=null;
        for (Empleado empleado1:empleadoList){
            if(empleado1.getIdEmpleado().equalsIgnoreCase(idEmpleado)){
                empleado=empleado1;
                break;
            }
        }
        return empleado;
    }

    public List<Producto> buscarMenoresACantidad(int cantidad) {
        List<Producto> producto = new ArrayList<>();
        for(Producto producto1: productoList){
            if(producto1.getCantidadStock()<=cantidad){
                producto.add(producto1);
            }
        }
        return producto;
    }

    public List<Producto> obtenerProductosMayores(int precio) {
        List<Producto> productoList1 = new ArrayList<>();
        for (Producto producto:productoList){
            if (producto.getPrecio()>precio){
                productoList1.add(producto);
            }
        }
        return productoList1;
    }
}