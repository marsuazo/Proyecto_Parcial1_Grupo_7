package historialycostosporpaciente;
import java.util.ArrayList; 
import java.util.Scanner; 
import gestiondepacientes.ExcepcionPacienteNoEncontrado;
import gestiondetratamientos.Tratamiento;
import validaciongeneraldedatos.ExcepcionDatoInvalido;
public class MenuHistorialCostosPorPaciente { 
    private Scanner teclado; 
    private GestorHistorialCostos gestorHistorialCostos; 
    private boolean menuActivo; 
    public MenuHistorialCostosPorPaciente( GestorHistorialCostos gestorHistorialCostos) { 
        this.gestorHistorialCostos = gestorHistorialCostos;
        this.teclado = new Scanner(System.in); 
        this.menuActivo = true; 
    } 
    //metodos 
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
        System.out.println( "==============================================" );
        System.out.println( " HISTORIAL Y COSTOS POR PACIENTE" ); 
        System.out.println( "==============================================" ); 
        System.out.println( "1. Consultar historial y costo total" );
        System.out.println( "2. Volver al menu principal" ); 
        System.out.println( "==============================================" ); 
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
    //switch para ver que quiere hacer el usuario
    private void procesarOpcion(int opcion) { 
        switch (opcion) { 
            case 1: 
                consultarHistorialYCostoTotal();
                break; 
            case 2: 
                volverAlMenuPrincipal(); 
                break; 
            default: 
                mostrarOpcionInvalida();
                break; 
        } 
    } 
    private void consultarHistorialYCostoTotal() { 
        System.out.println();
        System.out.println( "--- CONSULTAR HISTORIAL DEL PACIENTE ---" ); 
        System.out.print( "Ingrese la cedula del paciente: " ); 
        String cedulaPaciente = teclado.nextLine(); 
        try { 
            ArrayList<Tratamiento> historial = gestorHistorialCostos .consultarHistorialPorCedula( cedulaPaciente ); 
            double costoTotal = gestorHistorialCostos .calcularCostoTotalPorPaciente( cedulaPaciente ); 
            if (historial.isEmpty()) {
                mostrarHistorialVacio( costoTotal );
            } else {
                mostrarTratamientosDelPaciente( historial );
                mostrarCostoTotal( costoTotal ); 
            } 
        } catch (ExcepcionDatoInvalido excepcion) {
            System.out.println( "Error: " + excepcion.getMessage() ); 
        } catch (ExcepcionPacienteNoEncontrado excepcion) { 
            System.out.println( "Error: " + excepcion.getMessage() );
        } 
    } 
    private void mostrarTratamientosDelPaciente( ArrayList<Tratamiento> historial) { 
        System.out.println(); 
        System.out.println( "--- TRATAMIENTOS DEL PACIENTE ---" ); 
        for (int i = 0; i < historial.size(); i++) { 
            Tratamiento tratamiento = historial.get(i);
            System.out.println();
            System.out.println( "Tratamiento " + (i + 1) + ":" ); 
            System.out.println( "Nombre: " + tratamiento.getNombre() );
            System.out.println( "Tipo: " + tratamiento .obtenerTipoTratamiento() );
            System.out.println( "Duracion: " + tratamiento.getDuracionDias() + " dias" ); 
            System.out.println( "Costo: " + tratamiento.calcularCosto() ); 
        } 
    } 
    private void mostrarCostoTotal( double costoTotal) { 
        System.out.println(); 
        System.out.println( "Costo total de los tratamientos: " + costoTotal );
    }
    private void mostrarHistorialVacio( double costoTotal) {
        System.out.println(); 
        System.out.println( "El paciente no tiene tratamientos registrados." );
        System.out.println( "Costo total de los tratamientos: " + costoTotal ); 
    }
    private void volverAlMenuPrincipal() { 
        menuActivo = false; 
        System.out.println( "Regresando al menu principal..." ); 
    } 
    private void mostrarOpcionInvalida() { 
        System.out.println( "Error: La opcion seleccionada no es valida." ); 
    }  
}
