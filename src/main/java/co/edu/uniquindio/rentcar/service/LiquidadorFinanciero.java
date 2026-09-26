package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.Reserva;
import co.edu.uniquindio.rentcar.model.ServicioAdicional;

import java.time.temporal.ChronoUnit;

public class LiquidadorFinanciero {


    //aplica SRP aislando el calculo del costo final de cualquier reserva.
    public static double calcularTotalReserva(Reserva reserva) {
        if (reserva == null || reserva.getFechaInicio() == null || reserva.getFechaFin() == null) {
            return 0.0;
        }

        // 1. Cálculo de días de uso
        long dias = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());
        if (dias <= 0) dias = 1;

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
        return (costoBase + costoServicios) - reserva.getDescuento();
    }
}
