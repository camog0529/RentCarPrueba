package co.edu.uniquindio.rentcar.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FinanzasServiceTest {
    private final FinanzasService servicio = FinanzasService.getInstancia();

    @Test
    void devuelveCeroCuandoNoHayAlquileresEnElPeriodo() {
        assertEquals(0.0, servicio.calcularIngresosPeriodo(
                LocalDate.of(1900, 1, 1), LocalDate.of(1900, 12, 31)), 0.001);
    }

    @Test
    void devuelveCeroCuandoFaltaUnaFecha() {
        assertEquals(0.0, servicio.calcularIngresosPeriodo(null, LocalDate.of(2040, 1, 1)), 0.001);
    }
}
