//menu del modulo, solo es para probar 
package gestiondecitasmedicas;
import java.time.LocalDate; 
import java.time.LocalTime;
import java.util.ArrayList; 
import java.util.Scanner;
import gestiondemedicosydisponibilidadbasica.ExcepcionMedicoNoEncontrado; 
import gestiondepacientes.ExcepcionPacienteNoEncontrado;
import validaciongeneraldedatos.ExcepcionDatoInvalido; 
import validaciongeneraldedatos.ValidadorDatos; 
public class MenuGestionCitasMedicas { 
    private Scanner teclado; 
    private GestorCitas gestorCitas; 
    private ValidadorDatos validadorDatos; 
    private boolean menuActivo; 
    public MenuGestionCitasMedicas( GestorCitas gestorCitas, ValidadorDatos validadorDatos) { 
        this.gestorCitas = gestorCitas; 
        this.validadorDatos = validadorDatos; 
        this.teclado = new Scanner(System.in); 
        this.menuActivo = true; 
    }
    public void iniciarMenu() {
        menuActivo = true;
        while (menuActivo) {
            mostrarMenu(); 
            int opcion = leerOpcion(); procesarOpcion(opcion); 
        } 
    }
    private void mostrarMenu() { 
        System.out.println();
        System.out.println( "======================================" ); 
        System.out.println( " GESTIÓN DE CITAS MÉDICAS" );
        System.out.println( "======================================" );
        System.out.println( "1. Registrar cita médica" ); 
        System.out.println( "2. Buscar cita por identificador" ); 
        System.out.println( "3. Modificar cita médica" );
        System.out.println( "4. Cancelar cita médica" ); 
        System.out.println( "5. Marcar cita como atendida" ); 
        System.out.println( "6. Listar citas médicas" );
        System.out.println( "7. Volver al menú principal" );
        System.out.println( "======================================" );
        System.out.print( "Seleccione una opción: " ); 
    }
    private int leerOpcion() { 
        String entrada = teclado.nextLine(); 
        try { 
            return Integer.parseInt(entrada); 
        } catch (NumberFormatException excepcion) { 
            System.out.println( "Error: La opción debe ser " + "un número entero." );
            return -1; 
        } 
    } 
    //switch para poder entrar a cada cosa que se quiere
    private void procesarOpcion(int opcion) { 
        switch (opcion) {
            case 1: 
                registrarCitaMedica();
                break; 
            case 2: 
                buscarCitaPorIdentificador();
                break;
            case 3: 
                modificarCitaMedica(); 
                break; 
            case 4:
                cancelarCitaMedica(); 
                break; 
            case 5: 
                marcarCitaComoAtendida();
                break; 
            case 6: 
                listarCitasMedicas();
                break; 
            case 7: 
                volverAlMenuPrincipal();
                break;
            default: 
                mostrarOpcionInvalida(); 
                break; 
        } 
    } 
    private void registrarCitaMedica() {
        System.out.println();
        System.out.println( "--- REGISTRAR CITA MÉDICA ---" );
        System.out.print( "Ingrese el identificador de la cita: " );
        String identificadorTexto = teclado.nextLine(); 
        System.out.print( "Ingrese la cédula del paciente: " ); 
        String cedulaPaciente = teclado.nextLine(); 
        System.out.print( "Ingrese la cédula del médico: " );
        String cedulaMedico = teclado.nextLine(); 
        System.out.print( "Ingrese la fecha de la cita " + "con formato AAAA-MM-DD: " );
        String fechaTexto = teclado.nextLine(); 
        System.out.print( "Ingrese la hora de la cita " + "con formato HH:mm: " );
        String horaTexto = teclado.nextLine(); 
        try { 
            int identificador = Integer.parseInt( identificadorTexto );
            LocalDate fecha = validadorDatos .validarYConvertirFecha( fechaTexto );
            LocalTime hora = validadorDatos .validarYConvertirHora( horaTexto );
            gestorCitas.registrarCita( identificador, cedulaPaciente, cedulaMedico, fecha, hora );
            System.out.println( "Cita médica registrada " + "correctamente." ); 
        } catch (NumberFormatException excepcion) { 
            System.out.println( "Error: El identificador debe ser " + "un número entero." ); 
        } catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionCitaDuplicada excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionPacienteNoEncontrado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionMedicoNoEncontrado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionMedicoNoDisponible excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void buscarCitaPorIdentificador() { 
        System.out.println();
        System.out.println( "--- BUSCAR CITA MÉDICA ---" ); 
        System.out.print( "Ingrese el identificador de la cita: " );
        String identificadorTexto = teclado.nextLine(); 
        try { 
            int identificador = Integer.parseInt( identificadorTexto ); 
            Cita cita = gestorCitas .buscarCitaPorIdentificador( identificador );
            System.out.println(); System.out.println( "Cita encontrada:" );
            System.out.println(cita); 
        } catch (NumberFormatException excepcion) { 
            System.out.println( "Error: El identificador debe ser " + "un número entero." );
        } catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionCitaNoEncontrada excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void modificarCitaMedica() {
        System.out.println(); 
        System.out.println( "--- MODIFICAR CITA MÉDICA ---" );
        System.out.print( "Ingrese el identificador de la cita: " ); 
        String identificadorTexto = teclado.nextLine();
        System.out.print( "Ingrese la nueva cédula del médico: " );
        String nuevaCedulaMedico = teclado.nextLine(); 
        System.out.print( "Ingrese la nueva fecha " + "con formato AAAA-MM-DD: " ); 
        String nuevaFechaTexto = teclado.nextLine(); 
        System.out.print( "Ingrese la nueva hora " + "con formato HH:mm: " ); 
        String nuevaHoraTexto = teclado.nextLine(); 
        try { 
            int identificador = Integer.parseInt( identificadorTexto );
            LocalDate nuevaFecha = validadorDatos .validarYConvertirFecha( nuevaFechaTexto ); 
            LocalTime nuevaHora = validadorDatos .validarYConvertirHora( nuevaHoraTexto );
            gestorCitas.modificarCita( identificador, nuevaCedulaMedico, nuevaFecha, nuevaHora );
            System.out.println( "Cita médica modificada " + "correctamente." ); 
        } catch (NumberFormatException excepcion) {
            System.out.println( "Error: El identificador debe ser " + "un número entero." ); 
        } catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionCitaNoEncontrada excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionMedicoNoEncontrado excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionMedicoNoDisponible excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionEstadoCitaInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void cancelarCitaMedica() { 
        System.out.println();
        System.out.println( "--- CANCELAR CITA MÉDICA ---" );
        System.out.print( "Ingrese el identificador de la cita: " );
        String identificadorTexto = teclado.nextLine();
        System.out.print( "¿Confirma la cancelación? (S/N): " ); 
        String respuesta = teclado.nextLine();
        boolean confirmacion = respuesta.equalsIgnoreCase("S"); 
        try { 
            int identificador = Integer.parseInt( identificadorTexto );
            gestorCitas.cancelarCita( identificador, confirmacion ); 
            System.out.println( "Cita médica cancelada " + "correctamente." ); 
        } catch (NumberFormatException excepcion) { 
            System.out.println( "Error: El identificador debe ser " + "un número entero." ); 
        } catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionCitaNoEncontrada excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionCancelacionNoPermitida excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionEstadoCitaInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } 
    } 
    private void marcarCitaComoAtendida() {
        System.out.println(); 
        System.out.println( "--- MARCAR CITA COMO ATENDIDA ---" );
        System.out.print( "Ingrese el identificador de la cita: " );
        String identificadorTexto = teclado.nextLine();
        try {
            int identificador = Integer.parseInt( identificadorTexto ); 
            gestorCitas.marcarCitaAtendida( identificador ); 
            System.out.println( "Cita médica marcada como " + "atendida correctamente." );
        } catch (NumberFormatException excepcion) {
            System.out.println( "Error: El identificador debe ser " + "un número entero." ); 
        } catch (ExcepcionDatoInvalido excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } catch (ExcepcionCitaNoEncontrada excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionEstadoCitaInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
    } 
    private void listarCitasMedicas() {
        System.out.println(); System.out.println( "--- LISTA DE CITAS MÉDICAS ---" );
        ArrayList<Cita> listaCitas = gestorCitas.listarCitas(); 
        if (listaCitas.isEmpty()) {
            System.out.println( "No existen citas médicas " + "registradas." ); 
        } else { 
            for (int i = 0; i < listaCitas.size(); i++) {
                Cita cita = listaCitas.get(i); System.out.println( (i + 1) + ". " + cita ); 
            } 
        } 
    } 
    private void volverAlMenuPrincipal() { 
        menuActivo = false; System.out.println( "Regresando al menú principal..." ); 
    } 
    private void mostrarOpcionInvalida() {
        System.out.println( "Error: La opción seleccionada " + "no es válida." ); 
    } 
}