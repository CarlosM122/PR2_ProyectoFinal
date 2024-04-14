package co.edu.uniquindio.proyectofinal.fruteriaapp.Factory;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Fruteria;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Fruteria fruteria;

    private ModelFactory(){
        fruteria = new Fruteria();
        inicializarDatos();
    }

    public static ModelFactory getInstance(){
        if(modelFactory==null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private void inicializarDatos(){
        fruteria.setNombre("");
    }
}
