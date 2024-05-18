package co.edu.uniquindio.proyectofinal.fruteriaapp.Model;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder.ProductoBuilder;
import lombok.Data;

@Data

public class Producto {

    private String nombre;
    private String idProducto;
    private double precio;
    private int cantidadStock;

    public static ProductoBuilder builder() {
        return new ProductoBuilder();
    }

    public Producto() {
    }

    public Producto(String nombre, String idProducto, double precio, int cantidadStock) {
        this.nombre = nombre;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

}