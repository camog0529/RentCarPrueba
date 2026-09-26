package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.SolicitudAlquilerDTO;
import co.edu.uniquindio.rentcar.model.Vehiculo;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AlquilerServiceTest {
    private final AlquilerService servicio = AlquilerService.getInstancia();

    @Test
    void buscaVehiculoSinDistinguirMayusculas() {
        Vehiculo vehiculo = servicio.buscarVehiculoPorPlaca("kms123");
        assertNotNull(vehiculo);
        assertEquals("KMS123", vehiculo.getPlaca());
    }

    @Test
    void devuelveNuloCuandoLaPlacaNoExiste() {
        assertNull(servicio.buscarVehiculoPorPlaca("ZZZ999"));
    }

    @Test
    void calculaFacturaConDiasServiciosYDescuento() {
        SolicitudAlquilerDTO solicitud = new SolicitudAlquilerDTO(
                "PRUEBA-ALQ-1", "1094888999", "KMS123",
                LocalDate.of(2040, 1, 10), LocalDate.of(2040, 1, 12),
                "ECONOMICA", 10000.0, List.of(0));
        assertEquals(405000.0, servicio.generarFacturaAlquiler(solicitud), 0.001);
    }

    @Test
    void rechazaAlquilerSiElClienteNoExiste() {
        SolicitudAlquilerDTO solicitud = new SolicitudAlquilerDTO(
                "PRUEBA-ALQ-2", "NO-EXISTE", "KMS123",
                LocalDate.of(2040, 1, 10), LocalDate.of(2040, 1, 11),
                "ECONOMICA", 0.0, List.of());
        assertThrows(IllegalArgumentException.class,
                () -> servicio.generarFacturaAlquiler(solicitud));
    }

    @Test
    void rechazaAlquilerSiElVehiculoNoExiste() {
        SolicitudAlquilerDTO solicitud = new SolicitudAlquilerDTO(
                "PRUEBA-ALQ-3", "1094888999", "ZZZ999",
                LocalDate.of(2040, 1, 10), LocalDate.of(2040, 1, 11),
                "ECONOMICA", 0.0, List.of());
        assertThrows(IllegalArgumentException.class,
                () -> servicio.generarFacturaAlquiler(solicitud));
    }
}
