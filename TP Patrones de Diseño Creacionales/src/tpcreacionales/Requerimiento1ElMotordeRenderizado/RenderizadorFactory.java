// Definimos el paquete
package tpcreacionales.Requerimiento1ElMotordeRenderizado;

/**
 * Requerimiento 1: El Motor de Renderizado (Fábrica).
 * Implementa el patrón Factory Method (en su variante de Fábrica Simple).
 * Centraliza la lógica de instanciación de los renderizadores.
 */
public class RenderizadorFactory {

    /**
     * El "Factory Method" (método de fábrica).
     * Este es el único método que el cliente necesita llamar.
     * Recibe un tipo de reporte y devuelve la instancia concreta
     * correspondiente, pero la devuelve como la interfaz RenderizadorReporte.
     *
     * @param tipo El formato deseado (ej. "PDF", "EXCEL", "CSV").
     * @return Una instancia de un objeto que implementa RenderizadorReporte.
     * @throws IllegalArgumentException si el tipo no es soportado.
     */
    public static RenderizadorReporte crearRenderizador(String tipo) {
        // El cliente no sabe que este 'switch' existe.
        // Toda la lógica de decisión está encapsulada aquí.
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("El tipo de renderizador no puede ser nulo o vacío");
        }

        // Usamos .toUpperCase() para ser flexibles (acepta "pdf" o "PDF")
        switch (tipo.toUpperCase()) {
            case "PDF":
                return new RenderizadorPDF();
            case "EXCEL":
                return new RenderizadorExcel();
            case "CSV":
                return new RenderizadorCSV();
            // --- PUNTO CLAVE DE EXTENSIÓN ---
            // Si en el futuro agregamos XML,
            // solo agregamos un nuevo 'case' aquí:
            // case "XML":
            //     return new RenderizadorXML();
            // El código cliente (Main) no necesita NINGÚN cambio.
            default:
                throw new IllegalArgumentException("Tipo de renderizador no soportado: " + tipo);
        }
    }
}