package co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Dto.VentaDto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController.VentasViewController;

import java.util.List;

public class VentasController {
    ModelFactory modelFactory;

    public VentasController() {
        modelFactory = ModelFactory.getInstance();
    }

    public List<VentaDto> obtenerVentas() {
        return modelFactory.obtenerVentas();
    }

    public void agregarseComoObserver(VentasViewController ventasViewController) {
        modelFactory.agregarseComoObserver(ventasViewController);
    }
}
