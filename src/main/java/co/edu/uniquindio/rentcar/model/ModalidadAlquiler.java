package co.edu.uniquindio.rentcar.model;//declaracion de paquetes

//define una clase abstracta accesible por toda la aplicacion se declara abstract poque actua como plantilla o molde general del negocio
//esta clase instancia clases concretas de tipo economica ejecutiva o premium el modificador impide que se use new Modalidad Alquiler() forzando el uso de herencia
public abstract class ModalidadAlquiler {
    // Atributos protegidos (protected) para que las clases hijas puedan heredarlos directamente
    protected String codigo;
    protected String nombre;
    protected String description;
    protected int duracionMinimaDias;
    protected double valorDiario;
    protected String estado; // Puede controlarse comercialmente como "Disponible", "Suspendida" o "Finalizada"

    //Constructores de la clase
    // Constructor completo de la clase abstracta permite inicializar todos los estados de la modalidad de renta al momento de si creacion las hijas invocaran este constructor
    //utilizando la palabra clave super(....)en sus primeras lineas de codigo para garantizar que el molde base se ensamble bien
    public ModalidadAlquiler(String codigo, String nombre, String description, int duracionMinimaDias, double valorDiario, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.description = description;
        this.duracionMinimaDias = duracionMinimaDias;
        this.valorDiario = valorDiario;
        this.estado = estado;
    }

    // Constructor vacío por defecto para flexibilidad del modelo
    public ModalidadAlquiler() {
    }

    //METODO abstracto polimorfico exige a las clases hijas definir sus propios reglas logisticas o de creacion
// Cumple estrictamente con SOLID (LSP), evitando condicionales "if/else" en el sistema
// @return Un String que describe cómo se realiza la entrega del vehículo según la modalidad.
    public abstract String determinarLogisticaEntrega();

    /*
        exiqge que cada clase hija justifique su comportamiento algoritmico ejemplo (economica entrega en sede, ejecutiva a domicilio y premium)
        esto cuando la clase reserva invoque a este metodo java ejecuta automaticamente la correcta en tiempo de ejecucion (LSP)
    */
    // GETTERS Y SETTERS Convencionales de la Clase Base
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuracionMinimaDias() {
        return duracionMinimaDias;
    }

    public void setDuracionMinimaDias(int duracionMinimaDias) {
        this.duracionMinimaDias = duracionMinimaDias;
    }

    public double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
