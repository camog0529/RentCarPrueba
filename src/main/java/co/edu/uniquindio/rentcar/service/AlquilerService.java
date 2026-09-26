package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.*;
import co.edu.uniquindio.rentcar.repository.IRentCarRepository;
import co.edu.uniquindio.rentcar.repository.RentCarRepository;

import java.util.List;

public class AlquilerService {
    private static AlquilerService instancia;
    private final IRentCarRepository rentCarRepository;

    private AlquilerService() {
        this.rentCarRepository = RentCarRepository.getInstancia();
        inyectarDatosSemillaFlota();
    }

    public static synchronized AlquilerService getInstancia() {
        if (instancia == null) {
            instancia = new AlquilerService();
        }
        return instancia;
    }

    /**
     * RESPONSABILIDAD ÚNICA (SRP): Genera la factura de cobro cruzando toda la información del negocio.
     *
     * @param dto El objeto con los datos crudos extraídos de la interfaz JavaFX.
     * @return El valor total (double) de la factura generada.
     */
    public double generarFacturaAlquiler(SolicitudAlquilerDTO dto) {
        // 1. Cruzar la información buscando en las listas del repositorio
        Cliente clienteSeleccionado = buscarClientePorCedula(dto.getCedulaCliente());
        Vehiculo vehiculoSeleccionado = buscarVehiculoPorPlaca(dto.getPlacaVehiculo());

        if (clienteSeleccionado == null)
            throw new IllegalArgumentException("La cédula no corresponde a ningún cliente registrado.");
        if (vehiculoSeleccionado == null)
            throw new IllegalArgumentException("La placa digitada no existe en la flota.");

        // 2. Invocación del PATRÓN FACTORY METHOD para la modalidad (Llamando a la clase abstracta por OCP)
        // Nota: Como no creamos una clase Factory separada, creamos la instancia polimórfica según el tipo directo
        ModalidadAlquiler modalidad;
        String tipo = dto.getTipoModalidad().toUpperCase().trim();
        if (tipo.equals("ECONOMICA")) {
            modalidad = new ModalidadEconomica(dto.getCodigoReserva(), "Económica", "Plan base", 1, 45000.0, "Disponible");
        } else if (tipo.equals("EJECUTIVA")) {
            modalidad = new ModalidadEjecutiva(dto.getCodigoReserva(), "Ejecutiva", "Plan intermedio", 1, 75000.0, "Disponible", "Sede Central");
        } else {
            modalidad = new ModalidadPremium(dto.getCodigoReserva(), "Premium", "Plan alta gama", 1, 120000.0, "Disponible", "Cobertura Total", 2, "VIP");
        }

        // 3. Creación limpia de la Reserva usando su Constructor tradicional (Garantizando el Slim Controller y SRP)
        Reserva contratoFinal = new Reserva();
        contratoFinal.setCodigoReserva(dto.getCodigoReserva());
        contratoFinal.setFechaInicio(dto.getFechaInicio());
        contratoFinal.setFechaFin(dto.getFechaFin());
        contratoFinal.setDescuento(dto.getDescuento());
        contratoFinal.setCliente(clienteSeleccionado);
        contratoFinal.setVehiculo(vehiculoSeleccionado);
        contratoFinal.setModalidad(modalidad);
        contratoFinal.setEstado("ACTIVA");

        // Cruzar y mapear los servicios adicionales seleccionados
        List<ServicioAdicional> catalogo = rentCarRepository.obtenerCatalogoServicios();
        for (int idx : dto.getIndicesServicios()) {
            contratoFinal.getServiciosSeleccionados().add(catalogo.get(idx));
        }

        // 4. Guardar los datos consolidados en las listas del repositorio
        rentCarRepository.guardarReserva(contratoFinal);

        // 5. Retornar la liquidación financiera delegando en el Liquidador modular
        return LiquidadorFinanciero.calcularTotalReserva(contratoFinal);
    }

    public List<Vehiculo> obtenerVehiculosDisponibles() {
        return rentCarRepository.obtenerVehiculos();
    }

    public List<ServicioAdicional> obtenerCatalogoServicios() {
        return rentCarRepository.obtenerCatalogoServicios();
    }

    private Cliente buscarClientePorCedula(String cedula) {
        for (Cliente c : rentCarRepository.obtenerClientes()) {
            if (c.getDocumentoIdentidad().equals(cedula)) return c;
        }
        return null;
    }

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Vehiculo v : rentCarRepository.obtenerVehiculos()) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }

    private void inyectarDatosSemillaFlota() {
        rentCarRepository.guardarVehiculo(new Vehiculo("KMS123", "Mazda", "CX-30", 2024, "SUV", 150000.0));
        rentCarRepository.guardarVehiculo(new Vehiculo("ABC987", "Renault", "Kwid", 2023, "Económico", 90000.0));
        rentCarRepository.guardarServicio(new ServicioAdicional("SERV01", "Navegador GPS Satelital", "Ubicación", 25000.0, true));
        rentCarRepository.guardarServicio(new ServicioAdicional("SERV02", "Silla Ergonómica para Bebé", "Seguridad", 15000.0, true));
    }
}