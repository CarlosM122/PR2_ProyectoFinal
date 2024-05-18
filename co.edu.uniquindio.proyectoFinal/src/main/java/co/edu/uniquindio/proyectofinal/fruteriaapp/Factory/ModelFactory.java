package co.edu.uniquindio.proyectofinal.fruteriaapp.Factory;

import co.edu.uniquindio.proyectofinal.fruteriaapp.Contexto.VentaContexto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Dto.VentaDto;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Model.*;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Strategys.VentaDomicilio;
import co.edu.uniquindio.proyectofinal.fruteriaapp.Strategys.VentaFisica;
import co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController.ProductoViewController;
import co.edu.uniquindio.proyectofinal.fruteriaapp.ViewController.VentasViewController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
    private static ModelFactory modelFactory;
    private Fruteria fruteria;
    private VentaContexto ventaContexto = new VentaContexto();

    private ModelFactory(){
        fruteria = new Fruteria();
        inicializarDatos();
        inicializarVentas();
    }

    public static ModelFactory getInstance(){
        if(modelFactory==null){
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }
    private void inicializarDatos(){
        fruteria.setNombre("Amaranto");


        Empleado empleado = Empleado.builder()
                .nombre("Santiago")
                .apellido("Serna")
                .idEmpleado("HUY67")
                .build();

        Empleado empleado1 = Empleado.builder()
                .nombre("Maria")
                .apellido("Serna")
                .idEmpleado("HUY68")
                .build();

        Empleado empleado2 = Empleado.builder()
                .nombre("Rosa")
                .apellido("Hernandez")
                .idEmpleado("HUY69")
                .build();

        Empleado empleado3 = Empleado.builder()
                .nombre("Juan")
                .idEmpleado("1")
                .apellido("Amaya")
                .build();

        Cliente cliente = Cliente.builder()
                .nombre("Raul")
                .apellido("Ramirez")
                .cedula("1")
                .direccion("Calle 23 #12-23")
                .numCelular("3129786")
                .idCliente("FG89")
                .build();

        Cliente cliente1 = Cliente.builder()
                .nombre("Saul")
                .apellido("Sanchez")
                .cedula("1023423")
                .direccion("Calle 78 #12-23")
                .numCelular("312435")
                .idCliente("FG83")
                .build();

        Cliente cliente2 = Cliente.builder()
                .nombre("Nael")
                .apellido("Ramirez")
                .cedula("1098212")
                .direccion("Calle 90 #12-23")
                .numCelular("3126755")
                .idCliente("SD45")
                .build();

        Producto producto = Producto.builder()
                .nombre("Naranja")
                .idProducto("B45")
                .cantidadStock(23)
                .precio(1300.0)
                .build();

        Producto producto1 = Producto.builder()
                .nombre("Pera")
                .precio(600.0)
                .idProducto("1")
                .cantidadStock(73)
                .build();

        Producto producto2 = Producto.builder()
                .nombre("Piña")
                .idProducto("2")
                .cantidadStock(56)
                .precio(1000.0)
                .build();

        fruteria.getProductoList().add(producto);
        fruteria.getProductoList().add(producto1);
        fruteria.getProductoList().add(producto2);
        fruteria.getClienteList().add(cliente);
        fruteria.getClienteList().add(cliente1);
        fruteria.getClienteList().add(cliente2);
        fruteria.getEmpleadoList().add(empleado);
        fruteria.getEmpleadoList().add(empleado1);
        fruteria.getEmpleadoList().add(empleado2);
        fruteria.getEmpleadoList().add(empleado3);
    }

    private void inicializarVentas() {
        Venta venta = new Venta();
        venta.setIdVenta("RTG67");
        venta.setFechaDeVenta(LocalDate.now());
        venta.setTotal("800000");
        venta.setClienteAsociado(fruteria.getClienteList().get(0));
        venta.setEmpleadoAsociado(fruteria.getEmpleadoList().get(0));
        venta.setProductosAsociado(fruteria.getProductoList());

        fruteria.getVentaList().add(venta);
    }

    public boolean agregarProducto(Producto producto) {
        return fruteria.agergarProducto(producto);
    }

    public List<Producto> obtenerProductos() {
        return fruteria.getProductoList();
    }

    public List<Cliente> obtenerClientes() {
        return fruteria.getClienteList();
    }

    public boolean crearCliente(Cliente cliente) {
        return fruteria.crearCliente(cliente);
    }

    public boolean buscarEmpleado(String idEmpleado) {
        return fruteria.buscarEmpleado(idEmpleado);
    }

    public boolean crarEmpleado(Empleado empleado) {
        return fruteria.crearEmpleado(empleado);
    }

    public List<Producto> buscarMenoresACantidad(int cantidad) {
        return fruteria.buscarMenoresACantidad(cantidad);
    }

    public List<Producto> obtenerProductosMayores(int precio) {
        return fruteria.obtenerProductosMayores(precio);
    }

    public List<Empleado> obtenerEmpleados() {
        return fruteria.getEmpleadoList();
    }

    public boolean crearEmpleado(Empleado empleado) {
        return fruteria.crearEmpleado(empleado);
    }

    public List<VentaDto> obtenerVentas() {
        List<Venta> ventalist = fruteria.getVentaList();
        List<VentaDto> ventaDtos = new ArrayList<>();

        for(Venta venta : ventalist) {
            ventaDtos.add(buildDtos(venta));
        }
        return ventaDtos;
    }

    private VentaDto buildDtos(Venta venta) {
        return new VentaDto(
                venta.getIdVenta(),
                venta.getFechaDeVenta().toString(),
                venta.getClienteAsociado().getNombre(),
                venta.getClienteAsociado().getCedula(),
                venta.getClienteAsociado().getDireccion(),
                venta.getEmpleadoAsociado().getIdEmpleado(),
                venta.getTotal()
        );
    }

    public List<Producto> buscarProductos() {
        return fruteria.getProductoList();
    }

    public boolean validarDatos(VentaDto ventaDto, List<Producto> productos) {
        boolean validado = false;
        for(Cliente cliente : fruteria.getClienteList()) {
            if(ventaDto.cedulaCliente().equals(cliente.getCedula())) {
                for (Empleado empleado : fruteria.getEmpleadoList()) {
                    if (ventaDto.idEmpleado().equals(empleado.getIdEmpleado())) {
                        guardarVenta(ventaDto,productos);
                        validado = true;
                        break;
                    }
                }
            }
        }
        return validado;
    }

    public  void guardarVenta(VentaDto ventaDto,List<Producto> productos) {
        Result result = getResult(ventaDto);
        Venta venta = ventaDtoToVenta(ventaDto, result.cliente(), result.empleado(),productos);
        descontarProductos(productos);
        ventaContexto.elegirTipoVenta(new VentaFisica(venta.getIdVenta(),venta.getFechaDeVenta(),venta.getClienteAsociado(),venta.getEmpleadoAsociado(),venta.getDireccion(),venta.getTotal(),venta.getProductosAsociado()));
        ventaContexto.realizarVenta(venta);
        fruteria.agregarVenta(venta);
        fruteria.notifyObservers();
    }

    private void descontarProductos(List<Producto> productosCantidad) {
    }

    public void agregarseComoObserver(VentasViewController ventasViewController) {
        fruteria.addObserver(ventasViewController);
    }

    public String obtenerNombreCliente(String id) {
        return fruteria.obtenerNombreCliente(id);
    }

    public boolean validarDatosDomicilio(VentaDto ventaDto, List<Producto> productosAsociados) {
        boolean validado = false;
        for(Cliente cliente : fruteria.getClienteList()) {
            if(ventaDto.cedulaCliente().equals(cliente.getCedula())) {
                for (Empleado empleado : fruteria.getEmpleadoList()) {
                    if (ventaDto.idEmpleado().equals(empleado.getIdEmpleado())) {
                        guardarVentaDomicilio(ventaDto,productosAsociados);
                        validado = true;
                        break;
                    }
                }
            }
        }
        return validado;
    }

    private void guardarVentaDomicilio(VentaDto ventaDto, List<Producto> productosAsociados) {
        Result result = getResult(ventaDto);
        Venta venta = ventaDtoToVenta(ventaDto, result.cliente(), result.empleado(),productosAsociados);

        ventaContexto.elegirTipoVenta(new VentaDomicilio(venta.getIdVenta(),venta.getFechaDeVenta(),venta.getClienteAsociado(),venta.getEmpleadoAsociado(),venta.getDireccion(),venta.getTotal(),venta.getProductosAsociado()));
        ventaContexto.realizarVenta(venta);
        fruteria.agregarVenta(venta);
        fruteria.notifyObservers();
    }

    private Result getResult(VentaDto ventaDto) {
        Cliente cliente = null;
        Empleado empleado = null;
        for(Cliente cliente1 : fruteria.getClienteList()) {
            if(ventaDto.cedulaCliente().equals(cliente1.getCedula())) {
                cliente = cliente1;
                break;
            }
        }
        for(Empleado empleado1 : fruteria.getEmpleadoList()) {
            if(ventaDto.idEmpleado().equals(empleado1.getIdEmpleado())) {
                empleado = empleado1;
                break;
            }
        }
        Result result = new Result(cliente, empleado);
        return result;
    }

    public void agregarseComoObserverProductos(ProductoViewController productoViewController) {
        fruteria.addObserver(productoViewController);
    }

    public void norificar() {
        fruteria.notifyObservers();
    }

    public List<Venta> obtenerListaVentas() {
        return fruteria.getVentaList();
    }

    private record Result(Cliente cliente, Empleado empleado) {
    }

    private static Venta ventaDtoToVenta(VentaDto ventaDto, Cliente cliente, Empleado empleado,List<Producto> productos) {
        String fechaVentaTexto = ventaDto.fechaDeVenta();
        DateTimeFormatter esDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaVenta = null;
        try {
            fechaVenta = LocalDate.parse(fechaVentaTexto, esDateFormat);
        } catch (DateTimeParseException e) {
        }
        Venta venta = new Venta();
        venta.setDireccion(ventaDto.direccionCliente());
        venta.setFechaDeVenta(fechaVenta);
        venta.setIdVenta(ventaDto.idVenta());
        venta.setClienteAsociado(cliente);
        venta.setEmpleadoAsociado(empleado);
        venta.setTotal(ventaDto.total());
        venta.getProductosAsociado().addAll(productos);
        return venta;
    }
}
