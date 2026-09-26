package co.edu.uniquindio.rentcar.model;
//define el paquete fisico del arbol

import java.time.LocalDate;
//importa la clase estandar de java para manejo de fechas se usara para el registro de usuario

public class Cliente {
    //declara la clase Cliente que representa el molde de instanciacion del cliente en el modelo negocio
    private String nombreCompleto;
    private String documentoIdentidad;
    private long telefono;
    private String correoElectronico;
    private int edad;
    private LocalDate fechaRegistro;
    //de las lineas 7 a 12 se declaran atributos de la clase cliente los cuales segun el enunciado son los anteriores citados

    // El constructor depende de la estructura del Builder, protegiendo al sistema de cambios rígidos y recibe como parametro un patron BUILDER
    public Cliente(ClienteBuilder builder) {
        this.nombreCompleto = builder.getNombreCompleto();
        this.documentoIdentidad = builder.getDocumentoIdentidad();
        this.telefono = builder.getTelefono();
        this.correoElectronico = builder.getCorreoElectronico();
        this.edad = builder.getEdad();
        this.fechaRegistro = builder.getFechaRegistro();
    }

    //De las linas 18 a 23 se transfieren los datos acumulados transitoriamente dentro del BUILDER hacia los atributos reales de esta nueva instancia de Cliente usando los getters del BUILDER
    // Constructor vacion () en linea 27 que permite instanciar un cliente convencional sin necesidad de pasar por el BUILDER si alguna herramienta lo ha de requerir
    public Cliente() {
    }

    //Setters publicos lienas 29 a 34 metodos estandar publicos de acceso ( los get para leer y los set para modificar ) para poder interactuar de forma segura
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico() {
        this.correoElectronico = correoElectronico;
    }

    public void setEdad(String correoElectronico) {
        this.edad = edad;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Getters públicos lienas 37 a 42 metodos estandar publicos de acceso ( los get para leer y los set para modificar ) para poder interactuar de forma segura
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    //Clase interna estatica declaracion de clase interna publica y estatica llamada ClienteBuilder al ser estatica puede sert instanciada desde afuera sin necesidad de crear primero un cliente
    public static class ClienteBuilder {
        private String nombreCompleto;
        private String documentoIdentidad;
        private long telefono;
        private String correoElectronico;
        private int edad;
        private LocalDate fechaRegistro;

        //
        public ClienteBuilder conNombre(String nombre) {
            this.nombreCompleto = nombre;
            return this;
        }

        public ClienteBuilder conDocumentoIdentidad(String documentoIdentidad) {
            this.documentoIdentidad = documentoIdentidad;
            return this;
        }

        public ClienteBuilder conTelefono(long telefono) {
            this.telefono = telefono;
            return this;
        }

        public ClienteBuilder conCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }

        public ClienteBuilder conEdad(int edad) {
            this.edad = edad;
            return this;
        }

        public ClienteBuilder conFechaRegistro(LocalDate fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
            return this;
        }

        //Metodo encargado de procesar la logica validar reglas y construir fisicamente la entidad Cliente
        public Cliente build() {//validaciones
            if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
                throw new IllegalStateException("El nombre es obligatorio.");
            }
            if (documentoIdentidad == null || documentoIdentidad.trim().isEmpty()) {
                throw new IllegalStateException("El documento es obligatorio.");
            }
            if (fechaRegistro == null) {
                this.fechaRegistro = LocalDate.now();
            }
            //pasadas las validaciones invocamos al constructor al que le pasamos por parametro el builder pasandoles a si mismo el this como argumento entregando el objeto cliente construido
            // builder.this es como contrui esto el cliente
            return new Cliente(this);
        }

        // Getters para que el constructor de arriba lea los datos estos lees los datos temporales que estan guardados dentro del ClienteBuilder antes de que el cliente ex
        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public String getDocumentoIdentidad() {
            return documentoIdentidad;
        }

        public long getTelefono() {
            return telefono;
        }

        public String getCorreoElectronico() {
            return correoElectronico;
        }

        public int getEdad() {
            return edad;
        }

        public LocalDate getFechaRegistro() {
            return fechaRegistro;
        }
    }
}