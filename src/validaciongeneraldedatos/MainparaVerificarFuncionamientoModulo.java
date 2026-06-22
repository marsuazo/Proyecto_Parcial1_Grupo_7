//main del modulo en el cual se ejecutara para probar el funcionamiento del modulo.
package validaciongeneraldedatos;

public class MainparaVerificarFuncionamientoModulo {
    public static void main(String[] args) { 
        ValidadorDatos validador = new ValidadorDatos(); 
        MenuValidacionGeneralDatos menu = new MenuValidacionGeneralDatos(validador); 
        menu.iniciarMenu();
    }
}
