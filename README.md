# Simulador de Bolas

Este proyecto es una simulación del movimiento de bolas basada en un enfoque modular y patrones arquitectónicos. A continuación, se explica la organización del código y los conceptos clave detrás de su arquitectura.

## Ejemplo de Vista:

![image](https://github.com/user-attachments/assets/74e80204-88a2-4e49-8e7a-032a8658f12e)  

## Estructura del Directorio
La organización del proyecto se basa en una separación de responsabilidades, agrupando componentes relacionados:  

![image](https://github.com/user-attachments/assets/ff0a100c-3941-4e09-9f7d-c62d7d6640d5)  

## Patrones Arquitectónicos

### Modelo-Vista-Controlador (MVP)
La arquitectura general del proyecto sigue este enfoque, separando las responsabilidades en tres capas:

- **Modelo**:
  - Define los datos y la lógica de la simulación.
  - Contiene clases para representar las bolas y su comportamiento físico.
  - **Ejemplo**: `Ball`, `BallSimulator`.

- **Vista**:
  - Gestiona la presentación visual.
  - Proporciona componentes para mostrar las bolas y su movimiento.
  - **Ejemplo**: `MainFrame`, `BallDisplay`.

- **Presentador**:
  - Media entre el modelo y la vista, actuando como coordinador.
  - Gestiona eventos de usuario, actualiza el modelo y notifica a la vista.
  - **Ejemplo**: `BallPresenter`.

---

## Servicios Auxiliares
Encapsulan funcionalidades específicas para facilitar el mantenimiento y la reutilización del código:

- **Simulación Física**:
  - Gestiona el cálculo del movimiento de las bolas.
  - **Implementado en**: `BallSimulator`.

- **Presentación de Datos**:
  - Gestiona la visualización de las bolas en la interfaz gráfica.
  - **Implementado en**: `BallDisplay`.

---

## Modularidad
Cada componente está diseñado con una única responsabilidad. Esto mejora la mantenibilidad y escalabilidad del proyecto. Por ejemplo:

- **Módulo del modelo**:
  - Representación de entidades centrales como `Ball` y `BallSimulator`.

- **Módulo de la vista**:
  - Interfaz visual como `MainFrame` y `BallDisplay`.

- **Módulo del controlador**:
  - Coordinación entre el modelo y la vista mediante `BallPresenter`.

---

## Ejemplo de Flujo
1. El usuario ejecuta la aplicación.
2. El controlador (`BallPresenter`) inicializa las bolas y las añade al simulador.
3. La vista (`MainFrame`) muestra las bolas en movimiento.
4. Durante la simulación:
   - El modelo (`BallSimulator`) actualiza las posiciones de las bolas.
   - La vista se actualiza en tiempo real para reflejar los cambios.

---

## Tecnologías Utilizadas
- **Java AWT/Swing**: Para la interfaz gráfica.
- **Java 17**: Versión requerida para ejecutar el proyecto.

---

## Configuración y Ejecución
1. Clonar el repositorio.
2. Compilar y ejecutar el proyecto:
   ```bash
   javac -d bin src/software/ulpgc/app/Main.java
   java -cp bin software.ulpgc.app.Main
