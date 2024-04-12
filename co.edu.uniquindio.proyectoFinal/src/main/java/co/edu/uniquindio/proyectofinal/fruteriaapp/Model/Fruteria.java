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
}
