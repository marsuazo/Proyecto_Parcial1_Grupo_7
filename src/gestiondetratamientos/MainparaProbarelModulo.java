//main que solo es para verificar de que el modulo funciona correctamente.
package gestiondetratamientos;
import gestiondepacientes.GestorPacientes; 
import validaciongeneraldedatos.ValidadorDatos;
public class MainparaProbarelModulo { 
    public static void main(String[] args) { 
        ValidadorDatos validadorDatos = new ValidadorDatos();
        GestorPacientes gestorPacientes = new GestorPacientes( validadorDatos ); 
        GestorTratamientos gestorTratamientos = new GestorTratamientos( gestorPacientes, validadorDatos ); 
        MenuGestionTratamientos menu = new MenuGestionTratamientos( gestorTratamientos ); 
        menu.iniciarMenu(); 
    }
}
