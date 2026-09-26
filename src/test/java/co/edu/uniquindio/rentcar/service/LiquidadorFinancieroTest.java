package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.Reserva;
import co.edu.uniquindio.rentcar.model.ServicioAdicional;
import co.edu.uniquindio.rentcar.model.Vehiculo;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LiquidadorFinancieroTest {
    @Test
    void calculaDiasServiciosYDescuento() {
        Reserva reserva = new Reserva();
        reserva.setFechaInicio(LocalDate.of(2040, 1, 10));
        reserva.setFechaFin(LocalDate.of(2040, 1, 12));
        reserva.setVehiculo(new Vehiculo("ABC123", "Marca", "Modelo", 2024, "SUV", 100.0));
        reserva.setDescuento(10.0);
        reserva.setServiciosSeleccionados(List.of(
                new ServicioAdicional("GPS", "GPS", "Navegación", 20.0, true),
                new ServicioAdicional("SILLA", "Silla", "Infantil", 50.0, false)));

        assertEquals(210.0, LiquidadorFinanciero.calcularTotalReserva(reserva), 0.001);
    }

    @Test
    void devuelveCeroCuandoLaReservaEsNula() {
        assertEquals(0.0, LiquidadorFinanciero.calcularTotalReserva(null), 0.001);
    }
}
