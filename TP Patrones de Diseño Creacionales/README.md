# Trabajo Práctico: Patrones Creacionales - POO II

Este proyecto implementa una solución para un sistema de renderizado de reportes, aplicando los patrones de diseño creacionales fundamentales para resolver problemas comunes de instanciación.

**Integrantes:**
* Maidana, Martin Jose Quimey
* Núñez, Gabriel Omar
* Ramos Federico Javier

---

## 1. Justificación de Decisiones de Diseño

### Requerimiento 1: El Motor de Renderizado

* **¿Qué patrón elegimos?**
    Usamos el patrón **Factory Method** (implementado como una Fábrica Simple y Estática).

* **¿Por qué este patrón?**
    El requerimiento era claro: el código cliente (ej. el módulo de Finanzas) no debía acoplarse a las clases concretas como `RenderizadorPDF` o `RenderizadorExcel`. Necesitaba una forma de pedir un objeto por un "tipo" (un `String` como "PDF") y recibir la instancia correcta.

* **¿Qué problemas evitamos?**
    1.  **Alto Acoplamiento:** Evitamos que el cliente tenga que hacer `new RenderizadorPDF()`. Si hacíamos eso, el cliente dependería directamente de la clase concreta, y cualquier cambio (como renombrar esa clase) rompería el código cliente.
    2.  **Violación del Principio Abierto/Cerrado (OCP):** Con nuestra fábrica, si el día de mañana queremos agregar un `RenderizadorXML`, solo tenemos que crear la clase `RenderizadorXML` y agregar un `case` en `RenderizadorFactory`. El código cliente *no se modifica en absoluto*.

### Requerimiento 2: La Construcción de los Reportes

* **¿Qué patrón elegimos?**
    El patrón **Builder**.

* **¿Por qué este patrón?**
    El objeto `Reporte` es complejo. Tiene 2 atributos obligatorios (`titulo`, `cuerpoPrincipal`) y 5 opcionales (`encabezado`, `pieDePagina`, etc.). La consigna nos pedía explícitamente una forma de creación limpia y legible.

* **¿Qué problemas específicos resuelve?**
    1.  **Evita el "Constructor Telescópico":** Sin este patrón, habríamos tenido que crear múltiples constructores: uno con 2 parámetros, otro con 3, otro con 4... lo cual es un infierno de mantener.
    2.  **Evita el "Constructor con parámetros `null`":** La otra "solución" sería un único constructor gigante con 7 parámetros, y el cliente tendría que pasar `null` a todo lo que no usa (ej. `new Reporte("Titulo", "Cuerpo", null, null, null, "Autor", null)`). Esto es horrible, ilegible y propenso a errores (¿qué `null` era cuál?).
    3.  **Mejora la Legibilidad:** El código de creación ahora se auto-documenta: `new Reporte.Builder(...).conAutor(...).conFecha(...)`. Queda clarísimo qué estás seteando.

### Requerimiento 3: El Gestor de Configuración Global

* **¿Qué patrón elegimos?**
    El patrón **Singleton**.

* **¿Por qué este patrón?**
    El requerimiento era una definición de manual del Singleton: "garantizar que solo exista **una y solo una instancia** del objeto" y que haya un "único punto de acceso" a ella. Usar este patrón no era una opción, era el requisito.

* **¿Cómo garantizamos la unicidad?**
    1.  Declaramos el **constructor como `private`**. Esto es la clave, ya que impide que cualquier otra clase pueda hacer `new GestorConfiguracion()`.
    2.  Creamos una instancia `private static final` dentro de la misma clase (se inicializa una sola vez cuando la clase se carga).
    3.  Expusimos un método `public static getInstance()` que devuelve esa única instancia.

---

## 2. Código Fuente

El proyecto está estructurado en `src` con el paquete `tpcreacionales`. Incluye:
* Las clases de cada patrón (`GestorConfiguracion`, `Reporte` + `ReporteBuilder`, `RenderizadorFactory`, etc.).
* Una clase `Main.java` que demuestra el funcionamiento conjunto de los tres patrones.

## 3. Diagrama de Clases UML

Se adjunta la imagen `Diagrama de Clases - TP Patrones de Diseños` que modela la solución completa, mostrando las clases, interfaces y sus relaciones.