package co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder;
import co.edu.uniquindio.proyectofinal.proyectofinalapp.Model.Cliente;
public class ClienteBuilder {
    protected String idCliente;
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected String direccion;
    protected String idProveedor;

    public ClienteBuilder idCliente (String idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ClienteBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ClienteBuilder apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public ClienteBuilder cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public ClienteBuilder direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public ClienteBuilder idProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
        return this;
    }
    public Cliente build() { return new Cliente(idCliente, nombre, apellido, cedula, direccion, idProveedor); }
}