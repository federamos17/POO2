package tpcreacionales;

// Importamos las clases que necesitamos de otros paquetes/clases
import java.time.LocalDate;

import tpcreacionales.Reporte.Orientacion; // Importamos el Enum anidado
import tpcreacionales.Requerimiento1ElMotordeRenderizado.RenderizadorFactory;
import tpcreacionales.Requerimiento1ElMotordeRenderizado.RenderizadorReporte;

/**
 * Clase principal para demostrar el funcionamiento de los patrones creacionales.
 * Aquí unimos las tres soluciones implementadas. 
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("Iniciando demostración de Patrones Creacionales");
        System.out.println("=================================================");

        // -----------------------------------------------------------------
        // PASO 1: Demostración del SINGLETON
        // -----------------------------------------------------------------
        System.out.println("\n--- Probando Patrón Singleton ---");

        // Obtenemos la instancia del gestor de configuración
        // El constructor es privado, así que getInstance() es la única forma.
        GestorConfiguracion config = GestorConfiguracion.getInstance();

        // Demostramos que es único: si pedimos la instancia de nuevo...
        GestorConfiguracion config2 = GestorConfiguracion.getInstance();

        // ... ambas variables (config y config2) apuntan EXACTAMENTE al mismo
        // objeto en memoria.
        if (config == config2) {
            System.out.println("¡Éxito! config y config2 son LA MISMA instancia.");
        } else {
            System.out.println("¡Error! El Singleton no funciona.");
        }

        // Usamos la configuración obtenida
        System.out.println("Directorio de Reportes (desde Singleton): " + config.getPathReportes());

        // -----------------------------------------------------------------
        // PASO 2: Demostración del BUILDER
        // -----------------------------------------------------------------
        System.out.println("\n--- Probando Patrón Builder ---");

        // Creamos un reporte complejo usando el Builder.
        // Nota lo legible que es: los obligatorios van en el constructor...
        Reporte reporteVentas = new Reporte.ReporteBuilder(
                "Reporte Anual de Ventas 2024",
                "Datos detallados de ventas por trimestre...")
                // ...y los opcionales se añaden con métodos fluidos.
                .conAutor("Módulo de Finanzas")
                .conFecha(LocalDate.of(2025, 1, 15))
                .conPieDePagina("Documento Interno Confidencial")
                .conOrientacion(Orientacion.HORIZONTAL)
                .build(); // El método build() nos da el objeto final

        System.out.println("Reporte Completo Creado:");
        System.out.println(reporteVentas.toString());

        // Creamos un reporte simple (solo con los datos obligatorios)
        Reporte reporteSimple = new Reporte.ReporteBuilder(
                "Reporte de RRHH",
                "Nuevas incorporaciones.")
                .build();

        System.out.println("\nReporte Simple Creado:");
        System.out.println(reporteSimple.toString());

        // -----------------------------------------------------------------
        // PASO 3: Demostración del FACTORY METHOD
        // -----------------------------------------------------------------
        System.out.println("\n--- Probando Patrón Factory Method ---");

        // El cliente (nuestro Main) no sabe de "RenderizadorPDF" o "RenderizadorExcel".
        // Solo conoce la interfaz "RenderizadorReporte".
        RenderizadorReporte miRenderizador;

        // --- Caso A: El módulo de Finanzas quiere un PDF ---
        try {
            // Pedimos a la fábrica el renderizador que necesitamos
            miRenderizador = RenderizadorFactory.crearRenderizador("PDF");

            // Usamos el renderizador (sin saber que es un PDF, solo que "renderiza")
            String resultadoPDF = miRenderizador.renderizar(reporteVentas);
            System.out.println("\nResultado de la Fábrica (PDF):");
            System.out.println(resultadoPDF);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- Caso B: El módulo de Marketing quiere un CSV ---
        try {
            // Pedimos un tipo diferente a la misma fábrica
            miRenderizador = RenderizadorFactory.crearRenderizador("CSV");

            // Usamos el renderizador (ahora es un CSV, pero el cliente lo usa igual)
            String resultadoCSV = miRenderizador.renderizar(reporteSimple);
            System.out.println("\nResultado de la Fábrica (CSV):");
            System.out.println(resultadoCSV);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // --- Caso C: Un módulo pide un formato que no existe ---
        try {
            System.out.println("\nProbando formato no soportado (XML)...");
            miRenderizador = RenderizadorFactory.crearRenderizador("XML");
            miRenderizador.renderizar(reporteSimple); // Esta línea no se ejecutará

        } catch (IllegalArgumentException e) {
            // La fábrica maneja el error elegantemente.
            System.out.println("Error capturado correctamente: " + e.getMessage());
        }

        System.out.println("\n=================================================");
        System.out.println("Demostración finalizada.");
        System.out.println("=================================================");
    }
}