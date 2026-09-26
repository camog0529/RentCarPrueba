package co.edu.uniquindio.rentcar.model;

public class ModalidadEjecutiva extends ModalidadAlquiler {
    // Atributo específico para personalizar la entrega corporativa
    private String direccionEntregaPersonalizada;

    // Constructor completo que inicializa los campos heredados y el atributo propio
    public ModalidadEjecutiva(String codigo, String nombre, String description, int duracionMinimaDias, double valorDiario, String estado, String direccionEntregaPersonalizada) {
        super(codigo, nombre, description, duracionMinimaDias, valorDiario, estado);
        this.direccionEntregaPersonalizada = direccionEntregaPersonalizada;
    }

    // Constructor vacío por defecto
    public ModalidadEjecutiva() {
        super();
    }
//Implementacion polimorfica (LSP): sobreescribe el contrato de la clase padre y define el protocolo para entrega a domicilio de clase ejecutiva

    @Override
    public String determinarLogisticaEntrega() {
        return "Logística Ejecutiva: Entrega del vehículo programada a domicilio en la dirección: " + direccionEntregaPersonalizada;
    }

    // Getter y Setter específico del atributo propio
    public String getDireccionEntregaPersonalizada() {
        return direccionEntregaPersonalizada;
    }

    public void setDireccionEntregaPersonalizada(String direccionEntregaPersonalizada) {
        this.direccionEntregaPersonalizada = direccionEntregaPersonalizada;
    }
}
