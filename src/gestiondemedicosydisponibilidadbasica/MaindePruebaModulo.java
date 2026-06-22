//main de prueba para el modulo
package gestiondemedicosydisponibilidadbasica;
import gestiondecitasmedicas.GestorCitas;
import gestiondepacientes.GestorPacientes;
import validaciongeneraldedatos.ValidadorDatos;
public class MaindePruebaModulo{ 
    public static void main(String[] args) { 
        ValidadorDatos validadorDatos = new ValidadorDatos();
        GestorPacientes gestorPacientes = new GestorPacientes(validadorDatos); 
        GestorMedicos gestorMedicos = new GestorMedicos(validadorDatos); 
        GestorCitas gestorCitas = new GestorCitas( gestorPacientes, gestorMedicos, validadorDatos );
        MenuGestionMedicosDisponibilidad menu = new MenuGestionMedicosDisponibilidad( gestorMedicos, gestorCitas, validadorDatos ); menu.iniciarMenu();
    } 
}
