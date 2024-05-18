package co.edu.uniquindio.proyectofinal.fruteriaapp.Model;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Contexto.VentaContexto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Service.Observable;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Service.Observer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class Fruteria implements Observable {
    private String nombre;
    private List<Empleado> empleadoList = new ArrayList<>();
    private List<Producto> productoList = new ArrayList<>();
    private List<Cliente> clienteList = new ArrayList<>();
    private List<Venta> ventaList = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public Fruteria() {
    }

    public Fruteria(String nombre, List<Empleado> empleadoList, List<Producto> productoList, List<Cliente> clienteList, List<Venta> ventaList) {
        this.nombre = nombre;
        this.empleadoList = empleadoList;
        this.productoList = productoList;
        this.clienteList = clienteList;
        this.ventaList = ventaList;
    }

    public void agregarVenta(Venta venta) {
        ventaList.add(venta);
        notifyObservers();
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


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer:observers){
            observer.uptade();
        }
    }

    public String obtenerNombreCliente(String id) {
        String nombre = "";
        for(Cliente cliente:clienteList){
            if(cliente.getIdCliente().equalsIgnoreCase(id)){
                nombre=cliente.getNombre();
            }
        }
        return nombre;
    }
}