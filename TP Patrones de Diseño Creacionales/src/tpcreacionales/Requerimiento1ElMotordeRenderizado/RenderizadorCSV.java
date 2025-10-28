// Definimos el paquete
package tpcreacionales.Requerimiento1ElMotordeRenderizado;

import tpcreacionales.Reporte;

/**
 * Producto Concreto C.
 * Implementa la lógica específica para renderizar un reporte a CSV.
 */
public class RenderizadorCSV implements RenderizadorReporte {

    @Override
    public String renderizar(Reporte reporte) {
        // Simulación de la lógica compleja de crear un CSV
        System.out.println("Renderizando reporte a CSV...");
        System.out.println("Escribiendo cabeceras...");
        String csvData = "TITULO;CUERPO;AUTOR\n"; // Encabezados
        csvData += reporte.getTitulo() + ";" + reporte.getCuerpoPrincipal() + ";" + reporte.getAutor();
        // ... Lógica para escribir en un archivo...
        return "Reporte '" + reporte.getTitulo() + "' generado en CSV.\n" + csvData;
    }
}