# RentCar

RentCar es una aplicación de escritorio desarrollada en Java con JavaFX para apoyar la gestión de una empresa de alquiler de vehículos. Permite registrar clientes y vehículos, crear contratos de alquiler y consultar los ingresos liquidados en un periodo.

> **Importante:** la información se conserva en memoria mientras la aplicación está abierta. Al cerrarla, los clientes, vehículos y alquileres registrados durante esa sesión se pierden.

## Funcionalidades

- **Clientes:** registrar clientes con nombre, documento, teléfono, correo y edad; buscar clientes por teléfono.
- **Vehículos:** agregar vehículos a la flota con placa, marca, modelo, año, tipo y tarifa diaria, y consultar el catálogo registrado.
- **Alquileres:** crear un alquiler indicando el cliente, el vehículo, las fechas de entrega y devolución, la modalidad y el descuento. También se pueden incluir servicios adicionales.
- **Finanzas:** consultar los ingresos liquidados entre dos fechas.
- **Validaciones:** el sistema comprueba los datos necesarios para evitar registros incompletos o alquileres con fechas y valores inválidos.

## Requisitos

- JDK 26.
- IntelliJ IDEA (opcional).

El proyecto incluye Maven Wrapper, por lo que no es necesario instalar Maven por separado.

## Ejecutar en Windows

1. Clona o descarga el repositorio y abre la carpeta del proyecto.
2. Si usas IntelliJ IDEA, carga el proyecto como un proyecto Maven y configura el JDK 26.
3. En una terminal abierta en la carpeta del proyecto, ejecuta:

   ```powershell
   .\mvnw.cmd javafx:run
   ```

Al abrirse la ventana de RentCar, utiliza las pestañas **Clientes**, **Vehículos y Extras**, **Alquileres** y **Finanzas** para acceder a cada módulo.

## Ejecutar las pruebas

Las pruebas unitarias están en `src/test/java`. Para ejecutarlas en Windows, usa:

```powershell
.\mvnw.cmd test
```

Las pruebas cubren la creación y búsqueda de clientes, la gestión de vehículos, el registro y liquidación de alquileres, los descuentos y el cálculo de ingresos.

## Organización del proyecto

- `src/main/java/.../model`: entidades y objetos del dominio, como clientes, vehículos y reservas.
- `src/main/java/.../repository`: acceso y almacenamiento actual de los datos en memoria.
- `src/main/java/.../service`: operaciones y reglas del negocio.
- `src/main/java/.../Controller`: conexión entre la interfaz y los servicios.
- `src/main/resources`: interfaz JavaFX definida con FXML.
- `src/test/java`: pruebas unitarias.
