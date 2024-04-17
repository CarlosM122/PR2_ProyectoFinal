package co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Empleado;

public class AutenticationController {
    ModelFactory modelFactory;

    public AutenticationController(){
        modelFactory = ModelFactory.getInstance();
    }

    public boolean crearEmpleado(Empleado empleado) {
        return modelFactory.crarEmpleado(empleado);
    }

    public boolean buscarEmpleado(String idEmpleado) {
        return modelFactory.buscarEmpleado(idEmpleado);
    }
}
