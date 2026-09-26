package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.Cliente;
import co.edu.uniquindio.rentcar.repository.IRentCarRepository;
import co.edu.uniquindio.rentcar.repository.RentCarRepository;

import java.util.List;

public class ClienteService {
    private static ClienteService instancia;

    // Inversión de Dependencias (DIP): Dependemos de la interfaz del repositorio, no del modelo directo
    private final IRentCarRepository rentCarRepository;

    private ClienteService() {
        this.rentCarRepository = RentCarRepository.getInstancia();
        inyectarDatosSemilla(); // Carga de prueba inicial
    }

    public static synchronized ClienteService getInstancia() {
        if (instancia == null) {
            instancia = new ClienteService();
        }
        return instancia;
    }

    public void registrarCliente(Cliente cliente) {
        if (cliente != null) {
            rentCarRepository.guardarCliente(cliente);
        }
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return rentCarRepository.obtenerClientes();
    }

    /**
     * ALGORITMO EXIGIDO EN EL PARCIAL: Busca un cliente mediante su número de teléfono exacto.
     */
    public Cliente buscarClientePorTelefono(long telefonoBuscado) {
        for (Cliente cliente : rentCarRepository.obtenerClientes()) {
            if (cliente.getTelefono() == telefonoBuscado) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * ALGORITMO MATEMÁTICO (SRP): Evalúa si los dígitos del teléfono forman un número perfecto.
     */
    public boolean esNumeroPerfecto(long telefono) {
        if (telefono <= 0) return false;

        long sumaDivisores = 0;
        for (long i = 1; i <= telefono / 2; i++) {
            if (telefono % i == 0) {
                sumaDivisores += i;
            }
        }
        return sumaDivisores == telefono;
    }

    // Datos semilla para que tu JavaFX no aparezca vacío en la sustentación
    private void inyectarDatosSemilla() {
        Cliente c1 = new Cliente.ClienteBuilder()
                .conNombre("Carlos Restrepo")
                .conDocumentoIdentidad("1094888999")
                .conTelefono(6) // El 6 es un número perfecto conocido para pruebas iniciales
                .conEdad(28)
                .build();

        Cliente c2 = new Cliente.ClienteBuilder()
                .conNombre("Ana María Gómez")
                .conDocumentoIdentidad("419555666")
                .conTelefono(3157654321L)
                .conEdad(34)
                .build();

        registrarCliente(c1);
        registrarCliente(c2);
    }
}