//validaciones para el modulo, para asi verificar su correcto funcionamiento. 
package validaciongeneraldedatos;
import java.time.LocalDate; 
import java.time.LocalTime; 
import java.util.Scanner;
public class MenuValidacionGeneralDatos {
    private Scanner teclado; 
    private ValidadorDatos validador; 
    private boolean menuActivo; 
    public MenuValidacionGeneralDatos(ValidadorDatos validador) { 
        this.validador = validador; 
        this.teclado = new Scanner(System.in); 
        this.menuActivo = true; 
    } 
    public void iniciarMenu() { 
        menuActivo = true;
        while (menuActivo) {
            mostrarMenu(); 
            int opcion = leerOpcion(); 
            procesarOpcion(opcion); 
        } 
    }
    private void mostrarMenu() { 
        System.out.println();
        System.out.println("=========================================="); 
        System.out.println(" MÓDULO DE VALIDACION GENERAL DE DATOS"); 
        System.out.println("=========================================="); 
        System.out.println("1. Validar cedula"); 
        System.out.println("2. Validar telefono");
        System.out.println("3. Validar nombre");
        System.out.println("4. Validar edad");
        System.out.println("5. Validar identificador");
        System.out.println("6. Validar duracion"); 
        System.out.println("7. Validar numero de sesiones");
        System.out.println("8. Validar costo");
        System.out.println("9. Validar y convertir fecha"); 
        System.out.println("10. Validar y convertir hora");
        System.out.println("11. Volver al menu principal");
        System.out.println("=========================================="); 
        System.out.print("Seleccione una opcion: "); 
    }
    private int leerOpcion() {
        String entrada = teclado.nextLine(); 
        try { 
            return Integer.parseInt(entrada); 
        }
        catch (NumberFormatException excepcion) { 
            System.out.println( "La opcion debe ser un numero entero." ); 
            return -1; 
        } 
    } 
    private void procesarOpcion(int opcion) { 
        switch (opcion) { 
            case 1: validarCedula(); 
            break; 
            case 2: validarTelefono(); 
            break; 
            case 3: validarNombre(); 
            break;
            case 4: validarEdad();
            break; 
            case 5: validarIdentificador();
            break;
            case 6: validarDuracion(); 
            break;
            case 7: validarNumeroSesiones(); 
            break;
            case 8: validarCosto();
            break;
            case 9: validarFecha();
            break; 
            case 10: validarHora();
            break; 
            case 11: volverAlMenuPrincipal(); 
            break;
            default: mostrarOpcionInvalida(); 
            break;
        } 
    } //se valida la cedula para que si exista
    private void validarCedula() { 
        System.out.print("Ingrese la cedula: "); 
        String cedula = teclado.nextLine(); 
        try { 
            validador.validarCedula(cedula);
        System.out.println("La cedula es valida."); 
        } 
        catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    //se valida el celular, que exista en los datos.
    private void validarTelefono() { 
        System.out.print("Ingrese el telefono: "); 
        String telefono = teclado.nextLine(); 
        try { 
            validador.validarTelefono(telefono); 
            System.out.println("El telefono es valido."); 
        } 
        catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    //se valida el nombre
    private void validarNombre() { 
        System.out.print("Ingrese el nombre: "); 
        String nombre = teclado.nextLine(); 
        try { 
            validador.validarNombre(nombre); 
            System.out.println("El nombre es valido."); 
        } 
        catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
    }
    //se valida la edad para que sea un numero entero, no se puede poner letras ni numeros decimales.
    private void validarEdad() { 
        System.out.print("Ingrese la edad: "); 
        String entrada = teclado.nextLine();
        try {
            int edad = Integer.parseInt(entrada); 
            validador.validarEdad(edad); 
            System.out.println("La edad es valida.");
        } 
        catch (NumberFormatException excepcion) { 
            System.out.println( "Error: La edad debe ser un numero entero." ); 
        } 
        catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    //se valida el identificador para que no sea una letra o algun otro caracter extraño
    private void validarIdentificador() { 
        System.out.print("Ingrese el identificador: "); 
        String entrada = teclado.nextLine();
        try { 
            int identificador = Integer.parseInt(entrada);
            validador.validarIdentificador(identificador); 
            System.out.println( "El identificador es valido." ); 
        }
        catch (NumberFormatException excepcion) { 
            System.out.println( "Error: El identificador debe ser un numero entero." ); 
        } 
        catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
    } 
    //se valida la duracion
    private void validarDuracion() { 
        System.out.print( "Ingrese la duracion en dias: " ); 
        String entrada = teclado.nextLine(); 
        try { 
            int duracion = Integer.parseInt(entrada); 
            validador.validarDuracion(duracion);
            System.out.println("La duracion es valida.");
        } 
        catch (NumberFormatException excepcion) { 
            System.out.println( "Error: La duracion debe ser un numero entero." );
        } 
        catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    //se valida el numero de sesiones, para que sea un numero entero.
    private void validarNumeroSesiones() { 
        System.out.print( "Ingrese el numero de sesiones: " ); 
        String entrada = teclado.nextLine(); 
        try { 
            int numeroSesiones = Integer.parseInt(entrada);
            validador.validarNumeroSesiones( numeroSesiones ); 
            System.out.println( "El numero de sesiones es valido." ); 
        } 
        catch (NumberFormatException excepcion) {
            System.out.println( "Error: El numero de sesiones debe ser un numero entero." ); 
        } 
        catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    }//se valida del costo
    private void validarCosto() { 
        System.out.print("Ingrese el costo: ");
        String entrada = teclado.nextLine();
        try { 
            double costo = Double.parseDouble(entrada); 
            validador.validarCosto(costo); 
            System.out.println("El costo es valido.");
        } 
        catch (NumberFormatException excepcion) { 
            System.out.println( "Error: El costo debe ser un numero." ); 
        } 
        catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    }
    //se usa para validar la fecha
    private void validarFecha() {
        System.out.print( "Ingrese la fecha con el formato AAAA-MM-DD: " ); 
        String fechaTexto = teclado.nextLine();
        try {
            LocalDate fecha = validador.validarYConvertirFecha( fechaTexto );
            System.out.println( "La fecha es valida: " + fecha ); 
        } 
        catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
        //se usa para validar la hora
    } private void validarHora() { 
        System.out.print( "Ingrese la hora con el formato HH:mm: " ); 
        String horaTexto = teclado.nextLine();
        try {
            LocalTime hora = validador.validarYConvertirHora( horaTexto ); 
            System.out.println( "La hora es valida: " + hora );
        } 
        catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void volverAlMenuPrincipal() {
        menuActivo = false; 
        System.out.println( "Regresando al menu principal..." );
    }
    private void mostrarOpcionInvalida() { 
        System.out.println( "La opción seleccionada no es valida." ); 
    }
}

