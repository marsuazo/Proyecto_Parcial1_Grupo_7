//menu para poder probar el modulo
package gestiondetratamientos;
import java.util.ArrayList; 
import java.util.Scanner; 
import gestiondepacientes.ExcepcionPacienteNoEncontrado; 
import validaciongeneraldedatos.ExcepcionDatoInvalido;
public class MenuGestionTratamientos { 
    private Scanner teclado; 
    private GestorTratamientos gestorTratamientos;
    private boolean menuActivo;
    public MenuGestionTratamientos( GestorTratamientos gestorTratamientos) {
        this.gestorTratamientos = gestorTratamientos; 
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
        System.out.println( "======================================" );
        System.out.println( " GESTION DE TRATAMIENTOS" );
        System.out.println( "======================================" );
        System.out.println( "1. Registrar cirugia" );
        System.out.println( "2. Registrar terapia" );
        System.out.println( "3. Registrar medicacion" ); 
        System.out.println( "4. Listar tratamientos" );
        System.out.println( "5. Volver al menu principal" );
        System.out.println( "======================================" ); 
        System.out.print( "Seleccione una opcion: " ); 
    }
    private int leerOpcion() {
        String entrada = teclado.nextLine();
        try { 
            return Integer.parseInt( entrada ); 
        } catch (NumberFormatException excepcion) {
            return -1; 
        } 
    }
    private void procesarOpcion(int opcion) { 
        switch (opcion) { 
            case 1: 
                registrarCirugia(); 
                break; 
            case 2: 
                registrarTerapia();
                break;
            case 3: 
                registrarMedicacion();
                break;
            case 4: 
                listarTratamientos();
                break; 
            case 5: 
                volverAlMenuPrincipal(); 
                break; 
            default:
                mostrarOpcionInvalida(); 
                break; 
        } 
    } 
    private void registrarCirugia() { 
        System.out.println();
        System.out.println( "--- REGISTRAR CIRUGIA ---" ); 
        System.out.print( "Ingrese el identificador: " );
        String identificadorTexto = teclado.nextLine();
        System.out.print( "Ingrese el nombre de la cirugia: " );
        String nombre = teclado.nextLine();
        System.out.print( "Ingrese la duracion en dias: " ); 
        String duracionTexto = teclado.nextLine(); 
        System.out.print( "Ingrese la cedula del paciente: " ); 
        String cedulaPaciente = teclado.nextLine(); 
        System.out.print( "Ingrese el costo de los materiales: " ); 
        String costoMaterialesTexto = teclado.nextLine(); 
        try { 
            int identificador = Integer.parseInt( identificadorTexto );
            int duracionDias = Integer.parseInt( duracionTexto ); 
            double costoMateriales = Double.parseDouble( costoMaterialesTexto );
            gestorTratamientos.registrarCirugia( identificador, nombre, duracionDias, cedulaPaciente, costoMateriales ); 
            Tratamiento tratamientoRegistrado =buscarTratamientoRegistrado(identificador);
            System.out.println("Cirugia registrada correctamente.");
            System.out.println( "Costo base predeterminado: " + Cirugia.COSTO_BASE );
            if (tratamientoRegistrado != null) {
                 System.out.println("Costo total: "+ tratamientoRegistrado.calcularCosto());
            }

            //se hace esto si el usuario ingresa por pantalla algo mal, se arroja un mensaje de error.
        } catch (NumberFormatException excepcion) { 
            System.out.println( "Error: El identificador y la duracion " + "deben ser numeros enteros, " + "y el costo debe ser numerico." );
        } catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionTratamientoDuplicado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionPacienteNoEncontrado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
    }
    private void registrarTerapia() {
        System.out.println(); 
        System.out.println( "--- REGISTRAR TERAPIA ---" ); 
        System.out.print( "Ingrese el identificador: " ); 
        String identificadorTexto = teclado.nextLine(); 
        System.out.print( "Ingrese el nombre de la terapia: " ); 
        String nombre = teclado.nextLine(); 
        System.out.print( "Ingrese la duración en dias: " );
        String duracionTexto = teclado.nextLine(); 
        System.out.print( "Ingrese la cedula del paciente: " );
        String cedulaPaciente = teclado.nextLine();
        System.out.print( "Ingrese el numero de sesiones: " ); 
        String numeroSesionesTexto = teclado.nextLine(); 
        try { 
            int identificador = Integer.parseInt( identificadorTexto ); 
            int duracionDias = Integer.parseInt( duracionTexto );
            int numeroSesiones = Integer.parseInt( numeroSesionesTexto ); 
            gestorTratamientos.registrarTerapia( identificador, nombre, duracionDias, cedulaPaciente, numeroSesiones ); 
            Tratamiento tratamientoRegistrado =buscarTratamientoRegistrado(identificador);
            System.out.println("Terapia registrada correctamente.");
            System.out.println( "Costo por sesion predeterminado: " + Terapia.COSTO_BASE ); 
            if (tratamientoRegistrado != null) {
                System.out.println("Costo total: "+ tratamientoRegistrado.calcularCosto());
            } 
        } catch (NumberFormatException excepcion) {
            System.out.println( "Error: El identificador, la duracion " + "y el número de sesiones " + "deben ser numeros enteros." ); 
        } catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionTratamientoDuplicado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionPacienteNoEncontrado excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
    } 
    private void registrarMedicacion() {
        System.out.println(); 
        System.out.println( "--- REGISTRAR MEDICACION ---" ); 
        System.out.print( "Ingrese el identificador: " ); 
        String identificadorTexto = teclado.nextLine(); 
        System.out.print( "Ingrese el nombre de la medicacion: " ); 
        String nombre = teclado.nextLine();
        System.out.print( "Ingrese la duracion en dias: " ); 
        String duracionTexto = teclado.nextLine(); 
        System.out.print( "Ingrese la cedula del paciente: " );
        String cedulaPaciente = teclado.nextLine(); 
        try { 
            int identificador = Integer.parseInt( identificadorTexto ); 
            int duracionDias = Integer.parseInt( duracionTexto ); 
            gestorTratamientos.registrarMedicacion( identificador, nombre, duracionDias, cedulaPaciente );  
            Tratamiento tratamientoRegistrado =buscarTratamientoRegistrado(identificador);
            System.out.println("Medicacion registrada correctamente.");
            System.out.println( "Costo diario predeterminado: " + Medicacion.COSTO_BASE );
            if (tratamientoRegistrado != null) {
                System.out.println("Costo total: "+ tratamientoRegistrado.calcularCosto());
            }
        } catch (NumberFormatException excepcion) { 
            System.out.println( "Error: El identificador y la duracion " + "deben ser numeros enteros." ); 
        } catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionTratamientoDuplicado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionPacienteNoEncontrado excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void listarTratamientos() { 
        System.out.println(); 
        System.out.println( "--- LISTA DE TRATAMIENTOS ---" ); 
        ArrayList<Tratamiento> tratamientos = gestorTratamientos.listarTratamientos();
        if (tratamientos.isEmpty()) {
            System.out.println( "No existen tratamientos registrados." ); 
        } else { 
            for (int i = 0; i < tratamientos.size(); i++) { 
                Tratamiento tratamiento = tratamientos.get(i); 
                System.out.println(); 
                System.out.println( "Tratamiento " + (i + 1) + ":" ); 
                System.out.println( tratamiento ); 
            }
        }
    } 
    private void volverAlMenuPrincipal() { 
        menuActivo = false;
        System.out.println( "Regresando al menu principal..." ); 
    } 
    private void mostrarOpcionInvalida() {
        System.out.println( "Error: La opcion seleccionada " + "no es valida." ); 
    }
    private Tratamiento buscarTratamientoRegistrado( int identificador) { 
        ArrayList<Tratamiento> tratamientos = gestorTratamientos.listarTratamientos(); 
        for (int i = 0; i < tratamientos.size(); i++) { 
            Tratamiento tratamiento = tratamientos.get(i); 
            if (tratamiento.getIdentificador() == identificador) { 
                return tratamiento; 
            } 
        
        } return null; 
    }
}
