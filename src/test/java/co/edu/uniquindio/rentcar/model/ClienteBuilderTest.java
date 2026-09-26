package co.edu.uniquindio.rentcar.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteBuilderTest {
    @Test
    void construyeClienteConDatosObligatorios() {
        Cliente cliente = new Cliente.ClienteBuilder()
                .conNombre("Cliente de prueba")
                .conDocumentoIdentidad("TEST-001")
                .conTelefono(123456L)
                .conEdad(25)
                .build();

        assertEquals("Cliente de prueba", cliente.getNombreCompleto());
        assertEquals("TEST-001", cliente.getDocumentoIdentidad());
        assertEquals(123456L, cliente.getTelefono());
        assertNotNull(cliente.getFechaRegistro());
    }

    @Test
    void exigeNombreYDocumento() {
        assertThrows(IllegalStateException.class,
                () -> new Cliente.ClienteBuilder().conDocumentoIdentidad("TEST-002").build());
        assertThrows(IllegalStateException.class,
                () -> new Cliente.ClienteBuilder().conNombre("Cliente sin documento").build());
    }
}
