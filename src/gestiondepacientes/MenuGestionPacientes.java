
package gestiondepacientes;
import java.util.ArrayList; 
import java.util.Scanner; 
import validaciongeneraldedatos.ExcepcionDatoInvalido; 
public class MenuGestionPacientes {
    private Scanner teclado; 
    private GestorPacientes gestorPacientes; 
    private boolean menuActivo;
    public MenuGestionPacientes( GestorPacientes gestorPacientes) { 
        this.gestorPacientes = gestorPacientes; 
        this.teclado = new Scanner(System.in); 
        this.menuActivo = true; 
    } 
    public void iniciarMenu() { 
        menuActivo = true; while (menuActivo) {
            mostrarMenu(); int opcion = leerOpcion(); 
            procesarOpcion(opcion); 
        } 
    } 
    private void mostrarMenu() {
        System.out.println();
        System.out.println( "======================================" ); 
        System.out.println( " GESTIÓN DE PACIENTES" );
        System.out.println( "======================================" );
        System.out.println("1. Registrar paciente");
        System.out.println("2. Buscar paciente por cédula"); 
        System.out.println("3. Modificar paciente");
        System.out.println("4. Listar pacientes");
        System.out.println("5. Volver al menú principal"); 
        System.out.println( "======================================" );
        System.out.print("Seleccione una opción: "); 
    }
    private int leerOpcion() {
        String entrada = teclado.nextLine(); 
        try {
            return Integer.parseInt(entrada); 
        } 
        catch (NumberFormatException excepcion) { 
            System.out.println( "Error: La opción debe ser un número entero." ); 
            return -1;
        } 
    } 
    private void procesarOpcion(int opcion) { 
        switch (opcion) { 
            case 1: 
                registrarPaciente();
            break; 
            case 2: 
                buscarPaciente(); 
            break; 
            case 3: 
                modificarPaciente();
                break; 
            case 4: 
                listarPacientes(); 
                break; 
            case 5: 
                volverAlMenuPrincipal(); 
                break; 
            default: 
                mostrarOpcionInvalida(); 
                break; 
        } 
    }
    private void registrarPaciente() {
        System.out.println();
        System.out.println("--- REGISTRAR PACIENTE ---");
        System.out.print("Ingrese la cédula: ");
        String cedula = teclado.nextLine();
        System.out.print("Ingrese el nombre completo: "); 
        String nombreCompleto = teclado.nextLine(); 
        System.out.print("Ingrese la edad: "); 
        String edadTexto = teclado.nextLine();
        System.out.print("Ingrese el teléfono: "); 
        String telefono = teclado.nextLine(); 
        System.out.print( "Ingrese el tipo de seguro " + "(IESS, PRIVADO o SIN SEGURO): " );
        String tipoSeguro = teclado.nextLine();
        try { 
            int edad = Integer.parseInt(edadTexto);
            gestorPacientes.registrarPaciente( cedula, nombreCompleto, edad, telefono, tipoSeguro ); 
            System.out.println( "Paciente registrado correctamente." );
        }
        catch (NumberFormatException excepcion) { 
            System.out.println( "Error: La edad debe ser un número entero." ); 
        } catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
        catch (ExcepcionPacienteDuplicado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void buscarPaciente() { 
        System.out.println(); System.out.println("--- BUSCAR PACIENTE ---");
        System.out.print( "Ingrese la cédula del paciente: " ); 
        String cedula = teclado.nextLine();
        try { 
            Paciente paciente = gestorPacientes.buscarPacientePorCedula( cedula ); 
            System.out.println();
            System.out.println("Paciente encontrado:");
            System.out.println(paciente); 
        } 
        catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        }
        catch (ExcepcionPacienteNoEncontrado excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void modificarPaciente() { 
        System.out.println(); System.out.println("--- MODIFICAR PACIENTE ---");
        System.out.print( "Ingrese la cédula actual del paciente: " );
        String cedulaActual = teclado.nextLine(); 
        System.out.print("Ingrese la nueva cédula: ");
        String nuevaCedula = teclado.nextLine();
        System.out.print( "Ingrese el nuevo nombre completo: " ); 
        String nuevoNombre = teclado.nextLine(); 
        System.out.print("Ingrese la nueva edad: "); 
        String nuevaEdadTexto = teclado.nextLine(); 
        System.out.print("Ingrese el nuevo teléfono: ");
        String nuevoTelefono = teclado.nextLine(); 
        System.out.print( "Ingrese el nuevo tipo de seguro " + "(IESS, PRIVADO o SIN SEGURO): " ); 
        String nuevoTipoSeguro = teclado.nextLine(); 
        try { 
            int nuevaEdad = Integer.parseInt(nuevaEdadTexto); 
            gestorPacientes.modificarPaciente( cedulaActual, nuevaCedula, nuevoNombre, nuevaEdad, nuevoTelefono, nuevoTipoSeguro ); 
            System.out.println( "Paciente modificado correctamente." ); 
        }
        catch (NumberFormatException excepcion) { 
            System.out.println( "Error: La edad debe ser un número entero." ); 
        } 
        catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
        catch (ExcepcionPacienteNoEncontrado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
        catch (ExcepcionPacienteDuplicado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        }
    } 
    private void listarPacientes() { 
        System.out.println(); 
        System.out.println("--- LISTA DE PACIENTES ---");
        ArrayList<Paciente> listaPacientes = gestorPacientes.listarPacientes(); 
        if (listaPacientes.isEmpty()) {
            System.out.println( "No existen pacientes registrados." ); 
        } else { 
            for (int i = 0; i < listaPacientes.size(); i++) {
                Paciente paciente = listaPacientes.get(i);
                System.out.println( (i + 1) + ". " + paciente ); 
            } 
        } 
    } 
    private void volverAlMenuPrincipal() {
        menuActivo = false; 
        System.out.println( "Regresando al menú principal..." ); 
    } 
    private void mostrarOpcionInvalida() { 
        System.out.println( "Error: La opción seleccionada no es válida." );
    }  
}
