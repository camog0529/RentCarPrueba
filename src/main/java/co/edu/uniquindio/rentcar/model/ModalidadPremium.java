package co.edu.uniquindio.rentcar.model;

public class ModalidadPremium extends ModalidadAlquiler {
    // Atributos específicos exigidos por el enunciado del parcial
    private String tipoCobertura;
    private int cantidadConductoresAdicionalesPermitidos;
    private String caracteristicasEspecialesServicio;

    // Constructor completo para inicializar la base heredada y los tres campos específicos
    public ModalidadPremium(String codigo, String nombre, String description, int duracionMinimaDias, double valorDiario, String estado, String tipoCobertura, int cantidadConductoresAdicionalesPermitidos, String caracteristicasEspecialesServicio) {
        super(codigo, nombre, description, duracionMinimaDias, valorDiario, estado);
        this.tipoCobertura = tipoCobertura;
        this.cantidadConductoresAdicionalesPermitidos = cantidadConductoresAdicionalesPermitidos;
        this.caracteristicasEspecialesServicio = caracteristicasEspecialesServicio;
    }

    // Constructor vacío por defecto
    public ModalidadPremium() {
        super();
    }
    //Implementacion polimorfica (LSP): sobreescribe el contrato de la clase padre y define el protocolo para entrega de alta gama o premium

    @Override
    public String determinarLogisticaEntrega() {
        return "Logística Premium: Entrega VIP inmediata en aeropuerto o domicilio. Incluye cobertura: " + tipoCobertura;
    }

    // GETTERS Y SETTERS de los atributos específicos
    public String getTipoCobertura() {
        return tipoCobertura;
    }

    public void setTipoCobertura(String tipoCobertura) {
        this.tipoCobertura = tipoCobertura;
    }

    public int getCantidadConductoresAdicionalesPermitidos() {
        return cantidadConductoresAdicionalesPermitidos;
    }

    public void setCantidadConductoresAdicionalesPermitidos(int cantidadConductoresAdicionalesPermitidos) {
        this.cantidadConductoresAdicionalesPermitidos = cantidadConductoresAdicionalesPermitidos;
    }

    public String getCaracteristicasEspecialesServicio() {
        return caracteristicasEspecialesServicio;
    }

    public void setCaracteristicasEspecialesServicio(String caracteristicasEspecialesServicio) {
        this.caracteristicasEspecialesServicio = caracteristicasEspecialesServicio;
    }
}