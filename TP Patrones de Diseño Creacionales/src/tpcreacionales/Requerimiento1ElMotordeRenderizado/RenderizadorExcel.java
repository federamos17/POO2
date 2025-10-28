// Definimos el paquete
package tpcreacionales.Requerimiento1ElMotordeRenderizado;

import tpcreacionales.Reporte;

/**
 * Producto Concreto B.
 * Implementa la lógica específica para renderizar un reporte a Excel.
 */
public class RenderizadorExcel implements RenderizadorReporte {

    @Override
    public String renderizar(Reporte reporte) {
        // Simulación de la lógica compleja de crear un Excel
        System.out.println("Renderizando reporte a Excel...");
        System.out.println("Creando hoja de cálculo para: " + reporte.getTitulo());
        System.out.println("Cuerpo: " + reporte.getCuerpoPrincipal());
        // ... Lógica para usar librerías como Apache POI...
        return "Reporte '" + reporte.getTitulo() + "' generado en Excel.";
    }
}