package co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Empleado;
import java.util.List;

public class EmpleadoController {
    ModelFactory modelFactory;
    
    public EmpleadoController (){
        modelFactory = ModelFactory.getInstance();
    }

    public List<Empleado> obtenerEmpleados() {
        return modelFactory.obtenerEmpleados();
    }

    public boolean crearEmpleado(Empleado empleado) {
        return modelFactory.crearEmpleado(empleado);
    }
}
