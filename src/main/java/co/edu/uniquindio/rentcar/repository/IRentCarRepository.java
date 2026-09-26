package co.edu.uniquindio.rentcar.repository;

import co.edu.uniquindio.rentcar.model.Cliente;
import co.edu.uniquindio.rentcar.model.Vehiculo;
import co.edu.uniquindio.rentcar.model.Reserva;
import co.edu.uniquindio.rentcar.model.ServicioAdicional;

import java.util.List;

public interface IRentCarRepository {
    // Operaciones de Escritura / Registro Seguro
    void guardarCliente(Cliente cliente);

    void guardarVehiculo(Vehiculo vehiculo);

    void guardarReserva(Reserva reserva);

    void guardarServicio(ServicioAdicional servicio);

    // Operaciones de Lectura / Consulta
    List<Cliente> obtenerClientes();

    List<Vehiculo> obtenerVehiculos();

    List<Reserva> obtenerReservas();

    List<ServicioAdicional> obtenerCatalogoServicios();
}