package co.edu.uniquindio.rentcar.service;

import co.edu.uniquindio.rentcar.model.RentCar;
import co.edu.uniquindio.rentcar.model.Reserva;

import java.time.LocalDate;

public class FinanzasService {
    // 1. Instancia estática única para el patrón SINGLETON
    private static FinanzasService instancia;

    // 2. Constructor PRIVADO: Garantiza el aislamiento del Singleton
    private FinanzasService() {
    }

    // 3. Punto de acceso global único al servicio
    public static synchronized FinanzasService getInstancia() {
        if (instancia == null) {
            instancia = new FinanzasService();
        }
        return instancia;
    }

    /*
     * ALGORITMO DEL PARCIAL: Calcula y acumula los ingresos generados en un periodo determinado.
     * Recorre las reservas globales, filtra por el rango de fechas y delega la liquidación monetaria.
     *
     * @param fechaInicio Rango inicial de la consulta contable.
     * @param fechaFin Rango final de la consulta contable.
     * @return Sumatoria final (double) de los ingresos netos del periodo.
     */

    public double calcularIngresosPeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
        // Validación de seguridad para evitar NullPointerException si las fechas vienen vacías de la UI
        if (fechaInicio == null || fechaFin == null) {
            return 0.0;
        }

        double ingresosAcumulados = 0.0;

        // Recuperamos el historial unificado directamente desde el Singleton de RentCar (Capa Model)
        RentCar empresa = RentCar.getInstancia();//conecta con los datos de lo guardado

        // Recorremos secuencialmente cada transacción registrada (Principio SRP)
        for (Reserva reserva : empresa.getListaReservas()) {
            LocalDate fechaReserva = reserva.getFechaInicio(); // Evaluamos con base en la fecha de inicio del alquiler

            // Estructuramos los filtros de validación cronológica inclusiva
            boolean esDespuesOIgual = fechaReserva.isAfter(fechaInicio) || fechaReserva.isEqual(fechaInicio);
            boolean esAntesOIgual = fechaReserva.isBefore(fechaFin) || fechaReserva.isEqual(fechaFin);

            // Si la transacción cae dentro del rango solicitado por la gerencia
            if (esDespuesOIgual && esAntesOIgual) {
                // LLAMADA DESACOPLADA: Sumamos al total invocando al Liquidador Financiero independiente
                ingresosAcumulados += LiquidadorFinanciero.calcularTotalReserva(reserva);
            }
        }

        return ingresosAcumulados;
    }
}
