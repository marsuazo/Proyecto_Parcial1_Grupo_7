//main del modulo para comprobar si funciona
package gestiondecitasmedicas;
import gestiondemedicosydisponibilidadbasica.GestorMedicos; 
import gestiondepacientes.GestorPacientes; 
import validaciongeneraldedatos.ValidadorDatos; 
public class MainderuebaParaModulo { 
    public static void main(String[] args) { 
        ValidadorDatos validadorDatos = new ValidadorDatos(); 
        GestorPacientes gestorPacientes = new GestorPacientes(validadorDatos); 
        GestorMedicos gestorMedicos = new GestorMedicos(validadorDatos); 
        GestorCitas gestorCitas = new GestorCitas( gestorPacientes, gestorMedicos, validadorDatos ); 
        MenuGestionCitasMedicas menu = new MenuGestionCitasMedicas( gestorCitas, validadorDatos ); 
        menu.iniciarMenu(); 
    } 
}