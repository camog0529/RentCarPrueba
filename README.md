# RentCar

Aplicación de escritorio para gestionar clientes, vehículos, alquileres y consultas de ingresos. Desarrollada en Java 26 con JavaFX y Maven.

## Requisitos

- JDK 26.
- IntelliJ IDEA (opcional).

## Ejecutar la aplicación

En Windows, abre la carpeta del proyecto como proyecto Maven en IntelliJ IDEA o ejecuta desde la carpeta raíz:

```powershell
.\mvnw.cmd javafx:run
```

## Pruebas unitarias

Las pruebas están separadas del código de la aplicación en `src/test/java` y organizadas por área:

- `model/ClienteBuilderTest`: construcción y validaciones del cliente.
- `service/ClienteServiceTest`: búsqueda de clientes y números perfectos.
- `service/AlquilerServiceTest`: búsqueda de vehículos, facturación y validación del cliente.
- `service/LiquidadorFinancieroTest`: tarifas, días, servicios y descuentos.
- `service/FinanzasServiceTest`: ingresos en un periodo.

Para ejecutarlas en Windows desde la carpeta raíz:

```powershell
.\mvnw.cmd test
```

## Estructura del proyecto

- `model`: entidades del negocio.
- `repository`: acceso a los datos en memoria.
- `service`: operaciones y reglas de negocio.
- `Controller`: conexión de JavaFX con los servicios.
- `src/test/java`: pruebas unitarias.
