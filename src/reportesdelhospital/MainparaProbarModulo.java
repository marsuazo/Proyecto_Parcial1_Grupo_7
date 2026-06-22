//main solo para verificar si el modulo funciona
package reportesdelhospital;
import gestiondecitasmedicas.GestorCitas; 
import gestiondemedicosydisponibilidadbasica.GestorMedicos;
import gestiondepacientes.GestorPacientes; 
import gestiondetratamientos.GestorTratamientos; 
import validaciongeneraldedatos.ValidadorDatos;
public class MainparaProbarModulo {
    public static void main(String[] args) {
        ValidadorDatos validadorDatos = new ValidadorDatos();
        GestorPacientes gestorPacientes = new GestorPacientes( validadorDatos ); 
        GestorMedicos gestorMedicos = new GestorMedicos( validadorDatos ); 
        GestorCitas gestorCitas = new GestorCitas( gestorPacientes, gestorMedicos, validadorDatos ); 
        GestorTratamientos gestorTratamientos = new GestorTratamientos( gestorPacientes, validadorDatos ); 
        GestorReportesHospital gestorReportesHospital = new GestorReportesHospital( gestorCitas, gestorTratamientos ); 
        MenuReportesHospital menu = new MenuReportesHospital( gestorReportesHospital );
        menu.iniciarMenu(); 
    }  
}
