package co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;

public class ProductoBuilder {
    protected String nombre;
    protected String idProducto;
    protected double precio;
    protected int cantidadStock;

    public ProductoBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoBuilder idProducto(String idProducto) {
        this.idProducto = idProducto;
        return this;
    }

    public ProductoBuilder precio(double precio) {
        this.precio = precio;
        return this;
    }

    public ProductoBuilder cantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
        return this;
    }
    public Producto build(){return new Producto(nombre,idProducto,precio, cantidadStock);}
}
