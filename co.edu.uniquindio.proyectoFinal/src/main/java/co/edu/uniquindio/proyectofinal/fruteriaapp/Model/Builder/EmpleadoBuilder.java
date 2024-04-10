package co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder;

import co.edu.uniquindio.proyectofinal.proyectofinalapp.Model.Empleado;

public class EmpleadoBuilder {
    protected String nombre;
    protected String apellido;
    protected String idEmpleado;

    public EmpleadoBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public EmpleadoBuilder apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public EmpleadoBuilder idEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
        return this;
    }
    public Empleado build(){return new Empleado(nombre, apellido,idEmpleado);}
}
