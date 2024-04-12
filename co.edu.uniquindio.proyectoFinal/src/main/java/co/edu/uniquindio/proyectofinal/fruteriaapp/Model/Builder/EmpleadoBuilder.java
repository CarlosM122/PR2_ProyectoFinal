package co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Builder;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Empleado;

public class EmpleadoBuilder {
    protected String nombre;
    protected String apellido;
    protected String idEmpleado;
    protected String contraseña;

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

    public EmpleadoBuilder contraseña(String contraseña){
        this.contraseña= contraseña;
        return this;
    }
    public Empleado build(){return new Empleado(nombre, apellido,idEmpleado,contraseña);}
}
