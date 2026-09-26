package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.RentCar;
import co.edu.uniquindio.rentcar.model.Reserva;
import co.edu.uniquindio.rentcar.model.Vehiculo;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FinanzasServiceTest {
    private final FinanzasService servicio = FinanzasService.getInstancia();

    @Test
    void sumaIngresosDeReservasDentroDelPeriodo() {
        LocalDate inicio = LocalDate.of(2041, 3, 10);
        LocalDate fin = LocalDate.of(2041, 3, 11);
        double ingresosPrevios = servicio.calcularIngresosPeriodo(inicio, fin);

        Reserva reserva = new Reserva();
        reserva.setFechaInicio(inicio);
        reserva.setFechaFin(fin);
        reserva.setVehiculo(new Vehiculo("TST2041", "Marca", "Modelo", 2024, "SUV", 100.0));
        RentCar.getInstancia().registrarReserva(reserva);

        assertEquals(ingresosPrevios + 100.0, servicio.calcularIngresosPeriodo(inicio, fin), 0.001);
    }

    @Test
    void devuelveCeroCuandoFaltaUnaFecha() {
        assertEquals(0.0, servicio.calcularIngresosPeriodo(null, LocalDate.of(2040, 1, 1)), 0.001);
    }
}
