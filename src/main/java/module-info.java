module co.edu.uniquindio.rentcar {
    // 1. Módulos del sistema requeridos de forma obligatoria por la aplicación
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base; // Incluye las colecciones estándar y clases de tiempo (LocalDate)

    // 2. Exportación de paquetes para que sean visibles por otros componentes del sistema
    exports co.edu.uniquindio.rentcar;
    exports co.edu.uniquindio.rentcar.Controller;
    exports co.edu.uniquindio.rentcar.model;
    exports co.edu.uniquindio.rentcar.service;

    // 3. APERTURAS CRÍTICAS (opens): Permite el acceso por reflexión gráfica a JavaFX
    opens co.edu.uniquindio.rentcar to javafx.fxml;
    opens co.edu.uniquindio.rentcar.Controller to javafx.fxml;

    // Abrimos el modelo por si JavaFX necesita mapear propiedades dinámicas en celdas de Tablas (TableViews)
    opens co.edu.uniquindio.rentcar.model to javafx.fxml, javafx.base;
}