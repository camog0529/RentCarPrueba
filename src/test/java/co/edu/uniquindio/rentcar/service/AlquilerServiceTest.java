package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.Cliente;
import co.edu.uniquindio.rentcar.model.SolicitudAlquilerDTO;
import co.edu.uniquindio.rentcar.model.Vehiculo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AlquilerServiceTest {
    private static final String CEDULA_CLIENTE_PRUEBA = "PRUEBA-ALQ-CLIENTE";
    private final AlquilerService servicio = AlquilerService.getInstancia();

    @BeforeAll
    static void registrarClienteDePrueba() {
        ClienteService.getInstancia().registrarCliente(new Cliente.ClienteBuilder()
                .conNombre("Cliente de prueba para alquiler")
                .conDocumentoIdentidad(CEDULA_CLIENTE_PRUEBA)
                .build());
    }

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
                "PRUEBA-ALQ-1", CEDULA_CLIENTE_PRUEBA, "KMS123",
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
                "PRUEBA-ALQ-3", CEDULA_CLIENTE_PRUEBA, "ZZZ999",
                LocalDate.of(2040, 1, 10), LocalDate.of(2040, 1, 11),
                "ECONOMICA", 0.0, List.of());
        assertThrows(IllegalArgumentException.class,
                () -> servicio.generarFacturaAlquiler(solicitud));
    }
}
