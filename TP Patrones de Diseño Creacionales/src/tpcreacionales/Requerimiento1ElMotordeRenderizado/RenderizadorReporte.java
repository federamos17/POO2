// Definimos el paquete
package tpcreacionales.Requerimiento1ElMotordeRenderizado;

import tpcreacionales.Reporte;

/**
 * Interfaz "Producto" para el patrón Factory.
 * Define el contrato que todos los renderizadores concretos deben cumplir.
 * El cliente programará contra esta interfaz, no contra las clases concretas.
 */
public interface RenderizadorReporte {

    /**
     * Renderiza un objeto Reporte dado.
     * La lógica específica (generar un PDF, un CSV, etc.)
     * dependerá de la clase concreta que implemente esta interfaz.
     *
     * @param reporte El objeto Reporte a renderizar.
     * @return Una cadena de texto simulando el resultado del renderizado.
     */
    String renderizar(Reporte reporte);
}