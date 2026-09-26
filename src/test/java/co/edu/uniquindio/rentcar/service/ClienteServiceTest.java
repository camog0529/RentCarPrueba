package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {
    private final ClienteService servicio = ClienteService.getInstancia();

    @Test
    void encuentraClientePorTelefonoRegistrado() {
        Cliente cliente = servicio.buscarClientePorTelefono(6);
        assertNotNull(cliente);
        assertEquals(6L, cliente.getTelefono());
    }

    @Test
    void devuelveNuloCuandoElTelefonoNoExiste() {
        assertNull(servicio.buscarClientePorTelefono(999999L));
    }

    @Test
    void identificaNumerosPerfectosYDescartaOtros() {
        assertTrue(servicio.esNumeroPerfecto(6));
        assertTrue(servicio.esNumeroPerfecto(28));
        assertFalse(servicio.esNumeroPerfecto(12));
        assertFalse(servicio.esNumeroPerfecto(0));
    }

    @Test
    void reconoceOtroNumeroPerfectoYRechazaNegativos() {
        assertTrue(servicio.esNumeroPerfecto(496));
        assertFalse(servicio.esNumeroPerfecto(-6));
    }
}
