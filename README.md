# Roteiro por Galicia

**Repaso 1ª AV - PMDM 26-27 - 2º CS DAM - IES San Clemente**

---

## 📱 Descripción de la Aplicación

**Roteiro** es una aplicación nativa para Android desarrollada en Kotlin que sirve como guía turística interactiva por las cuatro provincias de Galicia (A Coruña, Lugo, Ourense y Pontevedra). 

La aplicación permite al usuario:
1. Introducir su nombre y seleccionar una provincia gallega desde un desplegable (`Spinner`).
2. Validar que el nombre no esté vacío antes de avanzar.
3. Navegar hacia una segunda pantalla (`ListaFragment`) pasando los datos de forma segura.
4. Visualizar un saludo personalizado y la lista de lugares emblemáticos de la provincia elegida mediante un `ListView`.
5. Consultar los detalles completos (nombre, concello y descripción) al pulsar sobre cualquier elemento de la lista.

---

## 🛠️ Tecnologías y Conceptos Clave (PMDM)

Este proyecto ha sido desarrollado como práctica de repaso para el módulo de **Programación de Dispositivos Móviles (PMDM)** en el ciclo de **2º CS DAM** (IES San Clemente), abarcando los siguientes conceptos docentes:

* **View Binding**: Acceso directo y seguro a las vistas del layout XML sin necesidad de `findViewById`.
* **Jetpack Navigation & Safe Args**:
  * Grafo de navegación (`nav_graph.xml`) con `NavHostFragment`.
  * Paso de argumentos entre Fragments (`nome` como `String` y `provincia` como `Int`) usando clases autogeneradas (`InicioFragmentDirections` y `ListaFragmentArgs`).
  * Integración de la `Toolbar` mediante `setupWithNavController`, gestionando el título de la pantalla y el botón de retroceso (Up arrow) automáticamente.
* **Adaptadores (`ArrayAdapter`)**:
  * Uso de `ArrayAdapter.createFromResource` para poblar el `Spinner` desde un recurso de array XML (`R.array.provincias`).
  * Instanciación de `ArrayAdapter` con layout personalizado (`fila_lugar.xml`) para alimentar el `ListView` a partir de colecciones Kotlin (`List<Lugar>`).
* **Listeners de Eventos**:
  * `setOnClickListener`: Captura de clics en botones y validación de formularios.
  * `onItemSelectedListener` (`AdapterView.OnItemSelectedListener`): Evento de selección de elementos en el `Spinner`.
  * `setOnItemClickListener` (`AdapterView.OnItemClickListener`): Captura del clic en cada fila del `ListView` para actualizar la ficha informativa.

---

## 📂 Estructura del Proyecto

```text
com.example.roteiro/
├── MainActivity.kt         # Actividad principal que aloja el NavHostFragment y la Toolbar.
├── InicioFragment.kt       # Pantalla de inicio con formulario (nombre y provincia).
├── ListaFragment.kt        # Pantalla con el ListView de puntos turísticos y detalle.
├── Lugar.kt                # Modelo de datos (Data Class: nome, concello, descricion).
└── Roteiro.kt              # Catálogo/repositorio estático de lugares por provincia.
```

---

## 🚀 Requisitos de Ejecución

* **Android Studio**: Ladybug / 2024.2.1 o superior.
* **JDK**: 17 o superior.
* **Compile SDK / Target SDK**: 35 (Android 15 / API 35).
* **Min SDK**: 24 (Android 7.0 / API 24).
