package co.edu.uniquindio.proyectofinal.fruteriaapp.Model;

public class Proveedor {
    private String nombre;
    private String idProveedor;
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(String nombre, String idProveedor, String telefono) {
        this.nombre = nombre;
        this.idProveedor = idProveedor;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
