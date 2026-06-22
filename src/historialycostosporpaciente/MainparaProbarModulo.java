package historialycostosporpaciente;
import gestiondepacientes.GestorPacientes; 
import gestiondetratamientos.GestorTratamientos; 
import validaciongeneraldedatos.ValidadorDatos; 
public class MainparaProbarModulo { 
    public static void main(String[] args) {
        ValidadorDatos validadorDatos = new ValidadorDatos();
        GestorPacientes gestorPacientes = new GestorPacientes( validadorDatos );
        GestorTratamientos gestorTratamientos = new GestorTratamientos( gestorPacientes, validadorDatos );
        GestorHistorialCostos gestorHistorialCostos = new GestorHistorialCostos( gestorPacientes, gestorTratamientos ); 
        MenuHistorialCostosPorPaciente menu = new MenuHistorialCostosPorPaciente( gestorHistorialCostos );
        menu.iniciarMenu();
    } 
}
