// Definimos el paquete donde estará nuestra clase
package tpcreacionales;

/**
 * Requerimiento 3: Gestor de Configuración Global.
 * Implementa el patrón Singleton para garantizar una única instancia
 * de configuración en toda la aplicación.
 */
public class GestorConfiguracion {

    // 1. La instancia ÚNICA de la clase.
    // 'static' significa que esta variable pertenece a la CLASE, no a un objeto.
    // 'final' significa que no se puede reasignar (es una constante).
    // 'private' para que nadie fuera de la clase pueda acceder a ella directamente.
    // Se inicializa "ansiosamente" (eager) aquí mismo, lo que garantiza que es 100%
    // seguro contra problemas de concurrencia (thread-safe).
    private static final GestorConfiguracion instancia = new GestorConfiguracion();

    // Atributos de configuración que necesita la aplicación
    private String urlBd;
    private String userBd;
    private String pathReportes;

    // 2. El constructor es PRIVADO.
    // Esta es la parte más importante del patrón.
    // Al ser privado, ninguna otra clase puede hacer "new GestorConfiguracion()".
    // Solo esta misma clase puede llamarlo (y lo hace una vez, arriba).
    private GestorConfiguracion() {
        // En un caso real, leeríamos esto de un archivo (.properties, .yml, etc.)
        // Para el TP, "hardcodeamos" (fijamos) los valores.
        this.urlBd = "jdbc:mysql://localhost:3306/mi_bd_reportes";
        this.userBd = "admin_reportes";
        this.pathReportes = "/var/app/reportes/";

        System.out.println("Gestor de Configuración inicializado UNA SOLA VEZ.");
    }

    // 3. El punto de acceso público y estático (global).
    // Este es el "único punto de acceso"  que pide el TP.
    // Cualquier parte del sistema (Finanzas, Marketing) llamará a este método
    // para obtener la instancia única.
    public static GestorConfiguracion getInstance() {
        return instancia;
    }

    // --- Getters públicos ---
    // Métodos para que el resto de la aplicación pueda LEER los valores.
    // Nota: No hay setters. La configuración no debe poder ser cambiada en
    // tiempo de ejecución por cualquier módulo. Es de solo lectura.

    public String getUrlBd() {
        return urlBd;
    }

    public String getUserBd() {
        return userBd;
    }

    public String getPathReportes() {
        return pathReportes;
    }
}