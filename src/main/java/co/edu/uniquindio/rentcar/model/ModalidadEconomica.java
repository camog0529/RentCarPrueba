package co.edu.uniquindio.rentcar.model;

public class ModalidadEconomica extends ModalidadAlquiler {

    // Constructor completo que invoca al constructor de la clase padre abstracta mediante 'super'
    public ModalidadEconomica(String codigo, String nombre, String description, int duracionMinimaDias, double valorDiario, String estado) {
        super(codigo, nombre, description, duracionMinimaDias, valorDiario, estado);
    }

    // Constructor vacío por defecto
    public ModalidadEconomica() {
        super();
    }


    //Implementacion polimorfica (LSP) sobreescribe el contrato de la clase padre y define la regla logistica obligatoria para el tema economico
    @Override
    public String determinarLogisticaEntrega() {
        return "Logística Económica: El cliente debe reclamar el vehículo físicamente en la sede central de RentCar.";
    }
}
