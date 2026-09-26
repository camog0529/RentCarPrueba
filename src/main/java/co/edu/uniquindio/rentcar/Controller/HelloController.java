package co.edu.uniquindio.rentcar.Controller;

import co.edu.uniquindio.rentcar.model.*;
import co.edu.uniquindio.rentcar.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class HelloController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDocumento;
    @FXML
    private TextField txtTelefonoRegistro;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtTelefonoBusqueda;
    @FXML
    private Label lblResultadoBusqueda;
    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtAnio;
    @FXML
    private TextField txtTipoVehiculo;
    @FXML
    private TextField txtTarifaDiaria;
    @FXML
    private ListView<String> lvVehiculos;
    @FXML
    private TextField txtCodigoReserva;
    @FXML
    private TextField txtDocumentoClienteAlquiler;
    @FXML
    private TextField txtPlacaVehiculoAlquiler;
    @FXML
    private DatePicker dpFechaInicioAlquiler;
    @FXML
    private DatePicker dpFechaFinAlquiler;
    @FXML
    private ComboBox<String> cbModalidad;
    @FXML
    private TextField txtDescuentoAlquiler;
    @FXML
    private ListView<String> lvServiciosAdicionales;
    @FXML
    private Label lblResultadoAlquiler;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private Label lblResultadoIngresos;

    private final ClienteService clienteService;
    private final AlquilerService alquilerService;
    private final FinanzasService finanzasService;

    public HelloController() {
        this.clienteService = ClienteService.getInstancia();
        this.alquilerService = AlquilerService.getInstancia();
        this.finanzasService = FinanzasService.getInstancia();
    }

    @FXML
    public void initialize() {
        // Blindaje contra nulos para asegurar que la ventana abra pase lo que pase
        if (cbModalidad != null) {
            cbModalidad.setItems(FXCollections.observableArrayList("ECONOMICA", "EJECUTIVA", "PREMIUM"));
        }
        if (lvServiciosAdicionales != null) {
            lvServiciosAdicionales.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

        actualizarListasVisuales();
    }

    private void actualizarListasVisuales() {
        // Validación de seguridad: Solo llena la lista si JavaFX ya la inyectó con éxito
        if (lvVehiculos != null) {
            ObservableList<String> itemsVehiculos = FXCollections.observableArrayList();
            for (Vehiculo v : alquilerService.obtenerVehiculosDisponibles()) {
                itemsVehiculos.add("[" + v.getPlaca() + "] " + v.getMarca() + " - $" + v.getTarifaDiaria());
            }
            lvVehiculos.setItems(itemsVehiculos);
        }

        if (lvServiciosAdicionales != null) {
            ObservableList<String> itemsServicios = FXCollections.observableArrayList();
            for (ServicioAdicional s : alquilerService.obtenerCatalogoServicios()) {
                itemsServicios.add(s.getCodigo() + " - " + s.getNombre() + " ($" + s.getPrecio() + ")");
            }
            lvServiciosAdicionales.setItems(itemsServicios);
        }
    }

    @FXML
    void onActionRegistrarCliente(ActionEvent event) {
        try {
            // Recolectamos la información y delegamos de inmediato la construcción y validación
            Cliente cliente = new Cliente.ClienteBuilder()
                    .conNombre(txtNombre.getText().trim())
                    .conDocumentoIdentidad(txtDocumento.getText().trim())
                    .conTelefono(Long.parseLong(txtTelefonoRegistro.getText().trim()))
                    .conCorreoElectronico(txtCorreo.getText().trim())
                    .conEdad(Integer.parseInt(txtEdad.getText().trim()))
                    .build();

            clienteService.registrarCliente(cliente);
            lblResultadoBusqueda.setText("Éxito: Registro completado.");
            txtNombre.clear();
            txtDocumento.clear();
            txtTelefonoRegistro.clear();
            txtCorreo.clear();
            txtEdad.clear();
        } catch (Exception e) {
            lblResultadoBusqueda.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    void onActionRegistrarVehiculo(ActionEvent event) {
        try {
            Vehiculo v = new Vehiculo(txtPlaca.getText().trim().toUpperCase(), txtMarca.getText().trim(), txtModelo.getText().trim(), Integer.parseInt(txtAnio.getText().trim()), txtTipoVehiculo.getText().trim(), Double.parseDouble(txtTarifaDiaria.getText().trim()));
            RentCar.getInstancia().registrarVehiculo(v);
            lblResultadoBusqueda.setText("Éxito: Flota actualizada.");
            actualizarListasVisuales();
            txtPlaca.clear();
            txtMarca.clear();
            txtModelo.clear();
            txtAnio.clear();
            txtTipoVehiculo.clear();
            txtTarifaDiaria.clear();
        } catch (Exception e) {
            lblResultadoBusqueda.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    void onActionRegistrarAlquiler(ActionEvent event) {
        try {
            // CONTROLADOR SLIM: Empaquetamos los datos en el DTO en una sola línea sin condicionales
            double desc = txtDescuentoAlquiler.getText().isEmpty() ? 0.0 : Double.parseDouble(txtDescuentoAlquiler.getText().trim());
            List<Integer> seleccionados = lvServiciosAdicionales.getSelectionModel().getSelectedIndices();

            SolicitudAlquilerDTO solicitud = new SolicitudAlquilerDTO(
                    txtCodigoReserva.getText().trim(), txtDocumentoClienteAlquiler.getText().trim(),
                    txtPlacaVehiculoAlquiler.getText().trim(), dpFechaInicioAlquiler.getValue(),
                    dpFechaFinAlquiler.getValue(), cbModalidad.getValue(), desc, seleccionados
            );

            // DELEGACIÓN ABSOLUTA: El servicio se encarga de cruzar datos, instanciar y facturar
            double totalFactura = alquilerService.generarFacturaAlquiler(solicitud);

            lblResultadoAlquiler.setText(String.format("¡Contrato Creado! Factura de Cobro: $%,.2f COP.", totalFactura));
            txtCodigoReserva.clear();
            txtDocumentoClienteAlquiler.clear();
            txtPlacaVehiculoAlquiler.clear();
        } catch (Exception e) {
            lblResultadoAlquiler.setText("Error Operacional: " + e.getMessage());
        }
    }

    @FXML
    void onActionBuscarCliente(ActionEvent event) {
        try {
            long tel = Long.parseLong(txtTelefonoBusqueda.getText().trim());
            Cliente c = clienteService.buscarClientePorTelefono(tel);
            boolean perfecto = clienteService.esNumeroPerfecto(tel);
            lblResultadoBusqueda.setText("Cliente: " + c.getNombreCompleto() + (perfecto ? " | Teléfono Perfecto." : " | Teléfono Común."));
        } catch (Exception e) {
            lblResultadoBusqueda.setText("Error en Consulta: " + e.getMessage());
        }
    }

    @FXML
    void onActionConsultarIngresos(ActionEvent event) {
        try {
            double total = finanzasService.calcularIngresosPeriodo(dpFechaInicio.getValue(), dpFechaFin.getValue());
            lblResultadoIngresos.setText(String.format("Ingresos Liquidados en el Periodo: $%,.2f COP", total));
        } catch (Exception e) {
            lblResultadoIngresos.setText("Error Financiero: " + e.getMessage());
        }
    }
}