package co.edu.uniquindio.proyectofinal.fruteriaapp.Contexto;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.Venta;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Service.StrategyVenta;
import lombok.Data;

@Data

public class VentaContexto {

    private StrategyVenta estrategia;

    public void setEstrategia(StrategyVenta estrategia) {
        this.estrategia = estrategia;
    }

    public void realizarVenta(Venta venta) {
        if (estrategia != null) {
            estrategia.realizarVenta(venta);
        } else {
            throw new IllegalStateException("Estrategia no definida");
        }
    }

    public VentaContexto elegirTipoVenta(StrategyVenta estrategia) {
        this.setEstrategia(estrategia);
        return this;
    }
}
