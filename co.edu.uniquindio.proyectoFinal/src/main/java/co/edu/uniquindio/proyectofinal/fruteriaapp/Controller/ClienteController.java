package co.edu.uniquindio.proyectofinal.fruteriaapp.Controller;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Factory.ModelFactory;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Cliente;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Producto;

import java.util.List;

public class ClienteController {

    ModelFactory modelFactory;

    public ClienteController(){
        modelFactory = ModelFactory.getInstance();
    }
    public List<Cliente> obtenerClientes() {
        return modelFactory.obtenerClientes();
    }

    public boolean crearCliente(Cliente cliente) {
        return modelFactory.crearCliente(cliente);
    }
}