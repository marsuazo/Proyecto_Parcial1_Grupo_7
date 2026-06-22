package gestiondepacientes;
    import validaciongeneraldedatos.ValidadorDatos; 
public class Main {
    public static void main(String[] args) {
        ValidadorDatos validadorDatos = new ValidadorDatos();
        GestorPacientes gestorPacientes = new GestorPacientes(validadorDatos);
        MenuGestionPacientes menu = new MenuGestionPacientes(gestorPacientes); 
        menu.iniciarMenu(); 
    }
}
