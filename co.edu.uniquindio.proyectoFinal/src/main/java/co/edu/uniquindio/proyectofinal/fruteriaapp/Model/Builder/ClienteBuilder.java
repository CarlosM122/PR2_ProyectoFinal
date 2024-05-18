package co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Cliente;
public class ClienteBuilder {
    protected String idCliente;
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected String direccion;
    protected String numCelular;

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

    public ClienteBuilder numCelular(String numCelular){
        this.numCelular = numCelular;
        return this;
    }
    public Cliente build() { return new Cliente(idCliente, nombre, apellido, cedula, direccion,numCelular); }
}
