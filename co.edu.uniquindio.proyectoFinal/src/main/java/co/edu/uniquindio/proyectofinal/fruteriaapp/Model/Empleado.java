package co.edu.uniquindio.proyectofinal.fruteriaapp.Model;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder.EmpleadoBuilder;

public class Empleado {
    private String nombre;
    private String apellido;
    private String idEmpleado;

    public Empleado() {
    }

    public static EmpleadoBuilder builder(){
        return new EmpleadoBuilder();
    }

    public Empleado(String nombre, String apellido, String idEmpleado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idEmpleado = idEmpleado;
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

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
