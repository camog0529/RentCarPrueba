package co.edu.uniquindio.rentcar.model;

import java.util.ArrayList;
import java.util.List;

public class RentCar {
    // 1. Instancia estática única para el patrón SINGLETON
    private static RentCar instancia;

    // Atributos institucionales de la empresa
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String paginaWeb;

    // Listas globales privadas encapsuladas de forma segura
    private final List<Cliente> listaClientes;
    private final List<Vehiculo> listaVehiculos;
    private final List<Reserva> listaReservas;
    private final List<ServicioAdicional> catalogoServicios;

    // 2. Constructor PRIVADO: Nadie desde fuera puede vaciar o recrear la empresa
    private RentCar() {
        this.nombreComercial = "RentCar Quindío";
        this.nit = "900.123.456-7";
        this.direccion = "Armenia, Quindío";
        this.telefono = "6067400000";
        this.correoElectronico = "contacto@rentcar.com";
        this.paginaWeb = "://rentcar.com.co";

        this.listaClientes = new ArrayList<>();
        this.listaVehiculos = new ArrayList<>();
        this.listaReservas = new ArrayList<>();
        this.catalogoServicios = new ArrayList<>();
    }

    // 3. Punto de acceso global único (SINGLETON)
    public static synchronized RentCar getInstancia() {
        if (instancia == null) {
            instancia = new RentCar();
        }
        return instancia;
    }

    // MÉTODOS DE OPERACIÓN SEGUROS (SRP - Encapsulamiento Defensivo)
    public void registrarCliente(Cliente cliente) {
        if (cliente != null && !listaClientes.contains(cliente)) {
            this.listaClientes.add(cliente);
        }
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null && !listaVehiculos.contains(vehiculo)) {
            this.listaVehiculos.add(vehiculo);
        }
    }

    public void registrarReserva(Reserva reserva) {
        if (reserva != null && !listaReservas.contains(reserva)) {
            this.listaReservas.add(reserva);
        }
    }

    public void registrarServicio(ServicioAdicional servicio) {
        if (servicio != null && !catalogoServicios.contains(servicio)) {
            this.catalogoServicios.add(servicio);
        }
    }

    // GETTERS SEGUROS (Retornan copias para evitar modificaciones externas)
    public List<Cliente> getListaClientes() {
        return new ArrayList<>(listaClientes);
    }

    public List<Vehiculo> getListaVehiculos() {
        return new ArrayList<>(listaVehiculos);
    }

    public List<Reserva> getListaReservas() {
        return new ArrayList<>(listaReservas);
    }

    public List<ServicioAdicional> getCatalogoServicios() {
        return new ArrayList<>(catalogoServicios);
    }

    // GETTERS Y SETTERS Convencionales de Datos Institucionales
    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }
}