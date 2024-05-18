package co.edu.uniquindio.proyectofinal.fruteriaapp.Dto;


public record VentaDto(
         String idVenta,
         String fechaDeVenta,
         String nombreCliente,
         String cedulaCliente,
         String direccionCliente,
         String idEmpleado,
         String total) {

}