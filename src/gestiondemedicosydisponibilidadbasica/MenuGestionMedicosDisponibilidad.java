//menu de prueba solo para la verificacion de que funciona el modulo
package gestiondemedicosydisponibilidadbasica;
import java.time.LocalTime; 
import java.util.ArrayList; 
import java.util.Scanner; 
import gestiondecitasmedicas.GestorCitas; 
import validaciongeneraldedatos.ExcepcionDatoInvalido; 
import validaciongeneraldedatos.ValidadorDatos;
public class MenuGestionMedicosDisponibilidad { 
    private Scanner teclado; 
    private GestorMedicos gestorMedicos;
    private GestorCitas gestorCitas; 
    private ValidadorDatos validadorDatos;
    private boolean menuActivo; 
    public MenuGestionMedicosDisponibilidad( GestorMedicos gestorMedicos, GestorCitas gestorCitas, ValidadorDatos validadorDatos) { 
        this.gestorMedicos = gestorMedicos; 
        this.gestorCitas = gestorCitas;
        this.validadorDatos = validadorDatos; 
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
    //se presenta por pantalla el menu
    //se evitan las tildes para que se vea mejor al mostrar por pantalla
    private void mostrarMenu() { 
        System.out.println();
        System.out.println( "============================================" ); 
        System.out.println( " GESTIÓN DE MEDICOS Y DISPONIBILIDAD BASICA" );
        System.out.println( "============================================" ); 
        System.out.println("1. Registrar medico");
        System.out.println("2. Buscar medico por cédula");
        System.out.println("3. Listar medicos");
        System.out.println("4. Listar médicos por especialidad"); 
        System.out.println("5. Consultar disponibilidad básica");
        System.out.println("6. Modificar medico"); 
        System.out.println("7. Volver al menu principal"); 
        System.out.println( "============================================" );
        System.out.print("Seleccione una opcion: "); 
    }
    private int leerOpcion() {
        String entrada = teclado.nextLine(); 
        try {
            return Integer.parseInt(entrada); 
        }
        catch (NumberFormatException excepcion) {
            System.out.println( "Error: La opcion debe ser un número entero." );
            return -1; 
        } 
    } 
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarMedico();
                break; 
            case 2: 
                buscarMedico(); 
                break; 
            case 3: 
                listarMedicos(); 
                break;
            case 4: 
                listarMedicosPorEspecialidad();
                break; 
            case 5: 
                consultarDisponibilidadBasica();
                break; 
            case 6: 
                modificarMedico(); 
                break; 
            case 7: 
                volverAlMenuPrincipal(); 
                break; 
            default: 
                mostrarOpcionInvalida(); 
                break;
        } 
    } 
    private void registrarMedico() { 
        System.out.println(); 
        System.out.println("--- REGISTRAR MEDICO ---");
        System.out.print("Ingrese la cedula: ");
        String cedula = teclado.nextLine(); 
        System.out.print("Ingrese el nombre completo: "); 
        String nombreCompleto = teclado.nextLine();
        System.out.print( "Ingrese la especialidad " + "(CARDIOLOGIA, PEDIATRIA " + "o MEDICINA GENERAL): " );
        String especialidad = teclado.nextLine(); 
        System.out.print( "Ingrese la hora inicial con formato HH:mm: " );
        String horaInicioTexto = teclado.nextLine();
        System.out.print( "Ingrese la hora final con formato HH:mm: " );
        String horaFinTexto = teclado.nextLine();
        try {
            LocalTime horaInicio = validadorDatos.validarYConvertirHora( horaInicioTexto ); 
            LocalTime horaFin = validadorDatos.validarYConvertirHora( horaFinTexto ); 
            gestorMedicos.registrarMedico( cedula, nombreCompleto, especialidad, horaInicio, horaFin ); 
            System.out.println( "Medico registrado correctamente." ); 
        } 
        catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
        catch (ExcepcionMedicoDuplicado excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void buscarMedico() { 
        System.out.println(); 
        System.out.println("--- BUSCAR MEDICO ---"); 
        System.out.print( "Ingrese la cédula del médico: " ); 
        String cedula = teclado.nextLine(); 
        try { 
            Medico medico = gestorMedicos.buscarMedicoPorCedula( cedula ); 
            System.out.println(); System.out.println("Medico encontrado:"); 
            System.out.println(medico); 
        } 
        catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
        catch (ExcepcionMedicoNoEncontrado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void listarMedicos() { 
        System.out.println(); 
        System.out.println("--- LISTA DE MEDICOS ---");
        ArrayList<Medico> listaMedicos = gestorMedicos.listarMedicos();
        if (listaMedicos.isEmpty()) { 
            System.out.println( "No existen medicos registrados." ); 
        }else{ 
            for (int i = 0; i < listaMedicos.size(); i++) { 
                Medico medico = listaMedicos.get(i); 
                System.out.println( (i + 1) + ". " + medico ); 
            } 
        } 
    } 
    private void listarMedicosPorEspecialidad() {
        System.out.println(); 
        System.out.println( "--- MEDICOS POR ESPECIALIDAD ---" ); 
        System.out.print( "Ingrese la especialidad " + "(CARDIOLOGIA, PEDIATRIA " + "o MEDICINA GENERAL): " ); 
        String especialidad = teclado.nextLine();
        try { ArrayList<Medico> medicosEncontrados = gestorMedicos .listarMedicosPorEspecialidad( especialidad ); 
        if (medicosEncontrados.isEmpty()) {
            System.out.println( "No existen medicos registrados " + "con esa especialidad." ); 
        } else {
            for (int i = 0; i < medicosEncontrados.size(); i++) { 
                Medico medico = medicosEncontrados.get(i);
                System.out.println( (i + 1) + ". " + medico ); 
            } 
        } 
        } catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void consultarDisponibilidadBasica() { 
        System.out.println(); 
        System.out.println( "--- CONSULTAR DISPONIBILIDAD BASICA ---" ); 
        System.out.print( "Ingrese la cedula del médico: " ); 
        String cedula = teclado.nextLine();
        System.out.print( "Ingrese la hora que desea consultar " + "con formato HH:mm: " );
        String horaTexto = teclado.nextLine(); 
        try { 
            Medico medico = gestorMedicos.buscarMedicoPorCedula( cedula ); 
            LocalTime hora = validadorDatos.validarYConvertirHora( horaTexto );
            boolean disponible = medico.estaDisponible(hora); 
            if (disponible) {
                System.out.println( "El medico se encuentra dentro " + "de su horario básico de atención." ); 
            } else { 
                System.out.println( "El medico no se encuentra dentro " + "de su horario básico de atención." ); 
            }
        } catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionMedicoNoEncontrado excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
    } 
    private void modificarMedico() {
        System.out.println(); 
        System.out.println("--- MODIFICAR MEDICO ---"); 
        System.out.print( "Ingrese la cedula actual del médico: " );
        String cedulaActual = teclado.nextLine(); 
        System.out.print("Ingrese la nueva cedula: "); 
        String nuevaCedula = teclado.nextLine(); 
        System.out.print( "Ingrese el nuevo nombre completo: " );
        String nuevoNombre = teclado.nextLine();
        System.out.print( "Ingrese la nueva especialidad " + "(CARDIOLOGIA, PEDIATRIA " + "o MEDICINA GENERAL): " ); 
        String nuevaEspecialidad = teclado.nextLine();
        System.out.print( "Ingrese la nueva hora inicial " + "con formato HH:mm: " );
        String nuevaHoraInicioTexto = teclado.nextLine(); 
        System.out.print( "Ingrese la nueva hora final " + "con formato HH:mm: " ); 
        String nuevaHoraFinTexto = teclado.nextLine();
        try { 
            LocalTime nuevaHoraInicio = validadorDatos.validarYConvertirHora( nuevaHoraInicioTexto );
            LocalTime nuevaHoraFin = validadorDatos.validarYConvertirHora( nuevaHoraFinTexto ); 
            gestorMedicos.modificarMedico( cedulaActual, nuevaCedula, nuevoNombre, nuevaEspecialidad, nuevaHoraInicio, nuevaHoraFin, gestorCitas ); 
            System.out.println( "Medico modificado correctamente." );
        } catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionMedicoNoEncontrado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionMedicoDuplicado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void volverAlMenuPrincipal() { 
        menuActivo = false; System.out.println( "Regresando al menu principal..." );
    } 
    private void mostrarOpcionInvalida() { 
        System.out.println( "Error: La opción seleccionada no es válida." ); 
    }
}
