// Definimos el paquete
package tpcreacionales.Requerimiento1ElMotordeRenderizado;

import tpcreacionales.Reporte;

/**
 * Producto Concreto A.
 * Implementa la lógica específica para renderizar un reporte a PDF.
 */
public class RenderizadorPDF implements RenderizadorReporte {

    @Override
    public String renderizar(Reporte reporte) {
        // Simulación de la lógica compleja de crear un PDF
        System.out.println("Renderizando reporte a PDF...");
        System.out.println("Título: " + reporte.getTitulo());
        System.out.println("Orientación: " + reporte.getOrientacion());
        System.out.println("Autor: " + reporte.getAutor());
        // ... Lógica para usar librerías como iText o PDFBox...
        return "Reporte '" + reporte.getTitulo() + "' generado en PDF.";
    }
}