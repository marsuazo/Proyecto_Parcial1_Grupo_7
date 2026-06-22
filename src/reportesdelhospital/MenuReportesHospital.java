//menu para poder verificar si el modulo funciona
package reportesdelhospital;
import java.util.HashMap;
import java.util.Scanner;
public class MenuReportesHospital {
    private Scanner teclado; 
    private GestorReportesHospital gestorReportesHospital; 
    private boolean menuActivo;
    public MenuReportesHospital( GestorReportesHospital gestorReportesHospital) {
        this.gestorReportesHospital = gestorReportesHospital; 
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
        System.out.println( "==========================================" ); 
        System.out.println( " REPORTES DEL HOSPITAL" ); 
        System.out.println( "==========================================" ); 
        System.out.println( "1. Mostrar citas atendidas por especialidad" );
        System.out.println( "2. Mostrar ingreso total de tratamientos" ); 
        System.out.println( "3. Mostrar ingresos por tipo de tratamiento" );
        System.out.println( "4. Volver al menu principal" );
        System.out.println( "==========================================" ); 
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
                mostrarCitasAtendidasPorEspecialidad();
                break; 
            case 2:
                mostrarIngresoTotalTratamientos();
                break;
            case 3: 
                mostrarIngresosPorTipoTratamiento();
                break; 
            case 4: 
                volverAlMenuPrincipal(); 
                break; 
            default: 
                mostrarOpcionInvalida(); 
                break; 
        } 
    } 
    private void mostrarCitasAtendidasPorEspecialidad() {
        HashMap<String, Integer> cantidades = gestorReportesHospital .contarCitasAtendidasPorEspecialidad(); 
        System.out.println();
        System.out.println( "--- CITAS ATENDIDAS POR ESPECIALIDAD ---" ); 
        System.out.println( "Cardiologia: " + cantidades.get("CARDIOLOGIA") );
        System.out.println( "Pediatria: " + cantidades.get("PEDIATRIA") ); 
        System.out.println( "Medicina general: " + cantidades.get("MEDICINA GENERAL") );
    } 
    private void mostrarIngresoTotalTratamientos() { 
        double ingresoTotal = gestorReportesHospital .calcularIngresoTotalTratamientos();
        System.out.println();
        System.out.println( "--- INGRESO TOTAL DE TRATAMIENTOS ---" ); 
        System.out.println( "Ingreso total: " + ingresoTotal ); 
    } 
    private void mostrarIngresosPorTipoTratamiento() {
        HashMap<String, Double> ingresosPorTipo = gestorReportesHospital .calcularIngresosPorTipoTratamiento();
        System.out.println();
        System.out.println( "--- INGRESOS POR TIPO DE TRATAMIENTO ---" );
        System.out.println( "Cirugia: " + ingresosPorTipo.get( "CIRUGÍA" ) ); 
        System.out.println( "Terapia: " + ingresosPorTipo.get( "TERAPIA" ) );
        System.out.println( "Medicacion: " + ingresosPorTipo.get( "MEDICACIÓN" ) ); 
    }
    private void volverAlMenuPrincipal() {
        menuActivo = false; 
        System.out.println( "Regresando al menu principal..." ); 
    } 
    private void mostrarOpcionInvalida() {
        System.out.println( "Error: La opcion seleccionada no es valida." );
    }
}
