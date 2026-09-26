# RentCar

RentCar es una aplicación de escritorio hecha con JavaFX para registrar clientes y vehículos, crear alquileres y consultar los ingresos de un periodo.

Los datos se guardan en memoria mientras la aplicación está abierta. Al cerrarla, los registros se pierden.

## Requisitos

- JDK 26.
- IntelliJ IDEA (opcional).

El proyecto incluye Maven Wrapper, así que no necesitas instalar Maven por separado.

## Abrir la aplicación en Windows

Abre la carpeta del proyecto en IntelliJ IDEA y carga el proyecto Maven. También puedes iniciarla desde la carpeta del proyecto con:

```powershell
.\mvnw.cmd javafx:run
```

## Ejecutar las pruebas

Las pruebas unitarias están en `src/test/java`. Comprueban la creación de clientes, la búsqueda por teléfono, la búsqueda de vehículos, los cálculos de alquileres y descuentos, y la consulta de ingresos.

Para ejecutarlas en Windows:

```powershell
.\mvnw.cmd test
```

## Organización del código

- `model`: clases que representan clientes, vehículos, alquileres y otros datos del negocio.
- `repository`: acceso a los datos, que por ahora se conservan en memoria.
- `service`: operaciones y reglas del negocio.
- `Controller`: conecta la interfaz JavaFX con los servicios.
- `src/main/resources`: archivos de la interfaz, como la vista FXML.
- `src/test/java`: pruebas unitarias.
