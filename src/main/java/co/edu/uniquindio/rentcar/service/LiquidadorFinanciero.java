package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.Reserva;
import co.edu.uniquindio.rentcar.model.ServicioAdicional;

import java.time.temporal.ChronoUnit;

public class LiquidadorFinanciero {


    //aplica SRP aislando el calculo del costo final de cualquier reserva.
    public static double calcularTotalReserva(Reserva reserva) {
        if (reserva == null) {
            return 0.0;
        }

        // 1. Cálculo de días de uso
                if (reserva.getFechaInicio() == null || reserva.getFechaFin() == null) {
            throw new IllegalArgumentException("Las fechas de inicio y devolución son obligatorias.");
                }
        if (!reserva.getFechaFin().isAfter(reserva.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de devolución debe ser posterior a la fecha de inicio.");
        }


        long dias = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
        // El rango validado garantiza días de alquiler positivos.

        // 2. Liquidación de tarifas base del modelo
        double tarifaVehiculo = (reserva.getVehiculo() != null) ? reserva.getVehiculo().getTarifaDiaria() : 0.0;
        double tarifaModalidad = (reserva.getModalidad() != null) ? reserva.getModalidad().getValorDiario() : 0.0;
        double costoBase = (tarifaVehiculo + tarifaModalidad) * dias;

        // 3. Sumatoria de servicios adicionales consumidos
        double costoServicios = 0.0;
        if (reserva.getServiciosSeleccionados() != null) {
            for (ServicioAdicional servicio : reserva.getServiciosSeleccionados()) {
                if (servicio.isDisponibilidad()) {
                    costoServicios += servicio.getPrecio();
                }
            }
        }

        // 4. Aplicación de descuentos corporativos
        double subtotal = costoBase + costoServicios;
        double descuento = reserva.getDescuento();
        if (!Double.isFinite(descuento) || descuento < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo.");
        }
        if (descuento > subtotal) {
            throw new IllegalArgumentException("El descuento no puede superar el subtotal del alquiler.");
        }
        return subtotal - descuento;
    }
}
