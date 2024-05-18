package co.edu.uniquindio.proyectofinal.fruteriaapp.Model;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder.ClienteBuilder;

public class Cliente {
    private String idCliente;
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String numCelular;

    public static ClienteBuilder builder(){
        return new ClienteBuilder();
    }

    public Cliente() {
    }

    public Cliente(String idCliente, String nombre, String apellido, String cedula, String direccion, String numCelular) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.numCelular = numCelular;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }
}
