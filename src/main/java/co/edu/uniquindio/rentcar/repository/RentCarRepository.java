package co.edu.uniquindio.rentcar.repository;

import co.edu.uniquindio.rentcar.model.RentCar;
import co.edu.uniquindio.rentcar.model.Cliente;
import co.edu.uniquindio.rentcar.model.Vehiculo;
import co.edu.uniquindio.rentcar.model.Reserva;
import co.edu.uniquindio.rentcar.model.ServicioAdicional;

import java.util.List;

public class RentCarRepository implements IRentCarRepository {
    // Instancia única compartida del repositorio (Singleton para la persistencia)
    private static RentCarRepository instancia;
    private final RentCar baseDatosMemoria;

    // Constructor privado que se enlaza con la base de datos del Modelo
    private RentCarRepository() {
        this.baseDatosMemoria = RentCar.getInstancia();
    }

    public static synchronized RentCarRepository getInstancia() {
        if (instancia == null) {
            instancia = new RentCarRepository();
        }
        return instancia;
    }

    // ==========================================
    // IMPLEMENTACIÓN DE MÉTODOS DE ESCRITURA
    // ==========================================
    @Override
    public void guardarCliente(Cliente cliente) {
        baseDatosMemoria.registrarCliente(cliente);
    }

    @Override
    public void guardarVehiculo(Vehiculo vehiculo) {
        baseDatosMemoria.registrarVehiculo(vehiculo);
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        baseDatosMemoria.registrarReserva(reserva);
    }

    @Override
    public void guardarServicio(ServicioAdicional servicio) {
        baseDatosMemoria.registrarServicio(servicio);
    }

    // ==========================================
    // IMPLEMENTACIÓN DE MÉTODOS DE LECTURA
    // ==========================================
    @Override
    public List<Cliente> obtenerClientes() {
        return baseDatosMemoria.getListaClientes();
    }

    @Override
    public List<Vehiculo> obtenerVehiculos() {
        return baseDatosMemoria.getListaVehiculos();
    }

    @Override
    public List<Reserva> obtenerReservas() {
        return baseDatosMemoria.getListaReservas();
    }

    @Override
    public List<ServicioAdicional> obtenerCatalogoServicios() {
        return baseDatosMemoria.getCatalogoServicios();
    }
}