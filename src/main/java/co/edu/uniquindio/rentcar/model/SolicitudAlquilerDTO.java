package co.edu.uniquindio.rentcar.model;

import java.time.LocalDate;
import java.util.List;

public class SolicitudAlquilerDTO {
    private final String codigoReserva;
    private final String cedulaCliente;
    private final String placaVehiculo;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFin;
    private final String tipoModalidad;
    private final double descuento;
    private final List<Integer> indicesServicios;

    public SolicitudAlquilerDTO(String codigoReserva, String cedulaCliente, String placaVehiculo,
                                LocalDate fechaInicio, LocalDate finale, String tipoModalidad,
                                double descuento, List<Integer> indicesServicios) {
        this.codigoReserva = codigoReserva;
        this.cedulaCliente = cedulaCliente;
        this.placaVehiculo = placaVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = finale;
        this.tipoModalidad = tipoModalidad;
        this.descuento = descuento;
        this.indicesServicios = indicesServicios;
    }

    // Getters puros para que el Servicio lea los datos
    public String getCodigoReserva() {
        return codigoReserva;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getTipoModalidad() {
        return tipoModalidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public List<Integer> getIndicesServicios() {
        return indicesServicios;
    }
}