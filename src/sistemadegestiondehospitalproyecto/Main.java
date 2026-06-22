
package sistemadegestiondehospitalproyecto;
import java.util.Scanner; 
import gestiondecitasmedicas.GestorCitas; 
import gestiondecitasmedicas.MenuGestionCitasMedicas; 
import gestiondemedicosydisponibilidadbasica.GestorMedicos; 
import gestiondemedicosydisponibilidadbasica.MenuGestionMedicosDisponibilidad;
import gestiondepacientes.GestorPacientes; 
import gestiondepacientes.MenuGestionPacientes;
import gestiondetratamientos.GestorTratamientos; 
import gestiondetratamientos.MenuGestionTratamientos; 
import historialycostosporpaciente.GestorHistorialCostos; 
import historialycostosporpaciente.MenuHistorialCostosPorPaciente; 
import reportesdelhospital.GestorReportesHospital; 
import reportesdelhospital.MenuReportesHospital; 
import validaciongeneraldedatos.MenuValidacionGeneralDatos; 
import validaciongeneraldedatos.ValidadorDatos;
public class Main { 
    public static void main(String[] args) {
            
         ValidadorDatos validadorDatos = new ValidadorDatos();
        //creacion de los gestores basicos.
         GestorPacientes gestorPacientes = new GestorPacientes( validadorDatos );
         GestorMedicos gestorMedicos = new GestorMedicos( validadorDatos );
         //creacion de los gestores de otros modulos
         GestorCitas gestorCitas = new GestorCitas( gestorPacientes, gestorMedicos, validadorDatos ); 
         GestorTratamientos gestorTratamientos = new GestorTratamientos( gestorPacientes, validadorDatos );
         GestorHistorialCostos gestorHistorialCostos = new GestorHistorialCostos( gestorPacientes, gestorTratamientos ); 
         GestorReportesHospital gestorReportesHospital = new GestorReportesHospital( gestorCitas, gestorTratamientos );
         //creacion de los menus propios de cada modulo
         MenuValidacionGeneralDatos menuValidacion = new MenuValidacionGeneralDatos( validadorDatos ); 
         MenuGestionPacientes menuPacientes = new MenuGestionPacientes( gestorPacientes );
         MenuGestionMedicosDisponibilidad menuMedicos = new MenuGestionMedicosDisponibilidad( gestorMedicos, gestorCitas, validadorDatos ); 
         MenuGestionCitasMedicas menuCitas = new MenuGestionCitasMedicas( gestorCitas, validadorDatos ); 
         MenuGestionTratamientos menuTratamientos = new MenuGestionTratamientos( gestorTratamientos );
         MenuHistorialCostosPorPaciente menuHistorial = new MenuHistorialCostosPorPaciente( gestorHistorialCostos ); 
         MenuReportesHospital menuReportes = new MenuReportesHospital( gestorReportesHospital );
         Scanner teclado = new Scanner(System.in); 
         int opcion; 
         do { 
             System.out.println(); 
             System.out.println( "==============================================" );
             System.out.println( " SISTEMA DE GESTION DE HOSPITAL" );
             System.out.println( "==============================================" ); 
             System.out.println( "1. Validacion general de datos" ); 
             System.out.println( "2. Gestion de pacientes" ); 
             System.out.println( "3. Gestion de medicos y disponibilidad" ); 
             System.out.println( "4. Gestion de citas medicas" ); 
             System.out.println( "5. Gestion de tratamientos" );
             System.out.println( "6. Historial y costos por paciente" ); 
             System.out.println( "7. Reportes del hospital" );
             System.out.println( "8. Salir del sistema" ); 
             System.out.println( "==============================================" ); 
             System.out.print( "Seleccione una opcion: " );
             String entrada = teclado.nextLine(); 
             try { 
                 opcion = Integer.parseInt( entrada ); 
             } catch (NumberFormatException excepcion) { 
                 opcion = -1; } 
             switch (opcion) { 
                 case 1:
                     menuValidacion.iniciarMenu();
                     break; 
                 case 2:
                     menuPacientes.iniciarMenu(); 
                     break;
                 case 3: 
                     menuMedicos.iniciarMenu(); 
                     break;
                 case 4: 
                     menuCitas.iniciarMenu(); 
                     break; 
                 case 5: 
                     menuTratamientos.iniciarMenu();
                     break; 
                 case 6: 
                     menuHistorial.iniciarMenu();
                     break;
                 case 7:
                     menuReportes.iniciarMenu();
                     break; 
                 case 8:
                     System.out.println();
                     System.out.println( "El sistema ha finalizado correctamente." );
                     break; 
                 default: 
                     System.out.println(); 
                     System.out.println( "Error: La opcion seleccionada " + "no es valida." ); 
                     break; 
             } 
         } while (opcion != 8); 
    }
}
        

       
    