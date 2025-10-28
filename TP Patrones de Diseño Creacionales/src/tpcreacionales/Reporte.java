// Definimos el paquete
package tpcreacionales;

// Importamos la clase para manejar fechas
import java.time.LocalDate;

/**
 * Requerimiento 2: Construcción de Reportes.
 * Implementa el patrón Builder para crear un objeto Reporte complejo
 * de una manera legible y flexible, manejando datos obligatorios y opcionales.
 */
public class Reporte {

    // 1. Atributos del objeto final.
    // Los hacemos 'final' para que el objeto sea inmutable una vez creado.
    private final String titulo;         // Obligatorio
    private final String cuerpoPrincipal; // Obligatorio

    private final String encabezado;     // Opcional
    private final String pieDePagina;    // Opcional
    private final LocalDate fecha;       // Opcional
    private final String autor;          // Opcional
    private final Orientacion orientacion; // Opcional

    // 2. El constructor de Reporte es PRIVADO.
    // Solo el Builder puede llamarlo.
    // Recibe el Builder como parámetro y "copia" sus valores.
    private Reporte(ReporteBuilder builder) {
        this.titulo = builder.titulo;
        this.cuerpoPrincipal = builder.cuerpoPrincipal;
        this.encabezado = builder.encabezado;
        this.pieDePagina = builder.pieDePagina;
        this.fecha = builder.fecha;
        this.autor = builder.autor;
        this.orientacion = builder.orientacion;
    }

    // --- Getters ---
    // Métodos públicos para leer los datos del reporte.
    // No hay setters, respetando la inmutabilidad.
    public String getTitulo() { return titulo; }
    public String getCuerpoPrincipal() { return cuerpoPrincipal; }
    public String getEncabezado() { return encabezado; }
    public String getPieDePagina() { return pieDePagina; }
    public LocalDate getFecha() { return fecha; }
    public String getAutor() { return autor; }
    public Orientacion getOrientacion() { return orientacion; }

    //Un método toString para imprimir el reporte fácil
    @Override
    public String toString() {
        return "Reporte [" +
                "titulo='" + titulo + '\'' +
                ", cuerpo='" + cuerpoPrincipal.substring(0, 20) + "...'" + // Acortamos el cuerpo
                ", encabezado='" + encabezado + '\'' +
                ", pieDePagina='" + pieDePagina + '\'' +
                ", fecha=" + fecha +
                ", autor='" + autor + '\'' +
                ", orientacion=" + orientacion +
                ']';
    }

    // --- Enum para Orientacion ---
    // Lo definimos aquí mismo por simplicidad, podría ir en su propio archivo.
    public enum Orientacion {
        VERTICAL,
        HORIZONTAL
    }


    // 3. La clase estática e interna "Builder"
    // -------------------------------------------------------------------------
    public static class ReporteBuilder {

        // 4. El Builder tiene los MISMOS atributos que el Reporte.
        // Aquí SÍ son obligatorios los datos requeridos.
        private final String titulo;
        private final String cuerpoPrincipal;

        // Aquí se inicializan los opcionales con valores por defecto (null, etc.)
        private String encabezado = null;
        private String pieDePagina = null;
        private LocalDate fecha = null;
        private String autor = "Sistema"; // Podemos poner un valor por defecto
        private Orientacion orientacion = Orientacion.VERTICAL; // Otro valor por defecto

        // 5. El constructor del Builder PIDE los datos OBLIGATORIOS.
        // Esto garantiza que el Reporte final siempre tendrá lo mínimo necesario.
        public ReporteBuilder(String titulo, String cuerpoPrincipal) {
            this.titulo = titulo;
            this.cuerpoPrincipal = cuerpoPrincipal;
        }

        // 6. Métodos "fluidos" para los datos OPCIONALES.
        // Cada método devuelve 'this' (el propio builder) para
        // permitir el "encadenamiento" de llamadas (ej. .conAutor().conFecha()...).
        public ReporteBuilder conEncabezado(String encabezado) {
            this.encabezado = encabezado;
            return this; // Devuelve el mismo objeto builder
        }

        public ReporteBuilder conPieDePagina(String pieDePagina) {
            this.pieDePagina = pieDePagina;
            return this;
        }

        public ReporteBuilder conFecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public ReporteBuilder conAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public ReporteBuilder conOrientacion(Orientacion orientacion) {
            this.orientacion = orientacion;
            return this;
        }

        // 7. El método 'build()'
        // Es el paso final. Llama al constructor PRIVADO de Reporte
        // y le pasa 'this' (el builder mismo) para que Reporte
        // tome todos los datos recolectados.
        public Reporte build() {
            // Aquí se podrían poner validaciones extra
            // if (titulo.isEmpty()) { throw new ... }

            // Llama al constructor privado y devuelve el objeto final
            return new Reporte(this);
        }
    }
}
