package co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder;

import co.edu.uniquindio.proyectofinal.proyectofinalapp.Model.*;

import java.util.ArrayList;
import java.util.List;

public class FruteriaBuilder {
        protected String nombre;
        protected List<Empleado> empleadoList = new ArrayList<>();
        protected List<Producto> productoList = new ArrayList<>();
        protected List<Cliente> clienteList = new ArrayList<>();
        protected List<Proveedor> proveedorList = new ArrayList<>();

    public FruteriaBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public FruteriaBuilder empleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
        return this;
    }

    public FruteriaBuilder productoList(List<Producto> productoList) {
        this.productoList = productoList;
        return this;
    }

    public FruteriaBuilder clienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
        return this;
    }

    public FruteriaBuilder proveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
        return this;
    }
    public Fruteria build(){return new Fruteria(nombre, empleadoList,productoList, clienteList, proveedorList);}
}
