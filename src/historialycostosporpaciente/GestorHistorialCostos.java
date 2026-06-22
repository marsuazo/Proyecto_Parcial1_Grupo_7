package historialycostosporpaciente;
import java.util.ArrayList;
import java.util.HashMap;
import gestiondepacientes.ExcepcionPacienteNoEncontrado; 
import gestiondepacientes.GestorPacientes; 
import gestiondetratamientos.GestorTratamientos; 
import gestiondetratamientos.Tratamiento;
import validaciongeneraldedatos.ExcepcionDatoInvalido; 
public class GestorHistorialCostos { 
    private HashMap<String, ArrayList<Tratamiento>> historialesPorCedula; 
    private GestorPacientes gestorPacientes; 
    private GestorTratamientos gestorTratamientos;
    //constructor
    public GestorHistorialCostos( GestorPacientes gestorPacientes, GestorTratamientos gestorTratamientos) { 
        this.gestorPacientes = gestorPacientes; 
        this.gestorTratamientos = gestorTratamientos; 
        this.historialesPorCedula = new HashMap<String, ArrayList<Tratamiento>>();
    }
    public ArrayList<Tratamiento> consultarHistorialPorCedula( String cedulaPaciente) throws ExcepcionDatoInvalido, ExcepcionPacienteNoEncontrado {
        /* * Se busca al paciente para validar la cédula * y comprobar que realmente esté registrado. */ 
        gestorPacientes.buscarPacientePorCedula( cedulaPaciente );
        actualizarHistorialesPorCedula(); 
        ArrayList<Tratamiento> copiaHistorial = new ArrayList<Tratamiento>();
        if (historialesPorCedula.containsKey( cedulaPaciente)) { 
            ArrayList<Tratamiento> tratamientosPaciente = historialesPorCedula.get( cedulaPaciente );
            for (int i = 0; i < tratamientosPaciente.size(); i++) { 
                copiaHistorial.add( tratamientosPaciente.get(i) );
            } 
        } 
        return copiaHistorial; 
    }
    public double calcularCostoTotalPorPaciente( String cedulaPaciente) throws ExcepcionDatoInvalido, ExcepcionPacienteNoEncontrado {
        ArrayList<Tratamiento> tratamientosPaciente = consultarHistorialPorCedula( cedulaPaciente ); 
        double costoTotal = 0.0;
        for (int i = 0; i < tratamientosPaciente.size(); i++) { 
            Tratamiento tratamiento = tratamientosPaciente.get(i); 
            costoTotal = costoTotal + tratamiento.calcularCosto(); 
        }
        return costoTotal; 
    }
    private void actualizarHistorialesPorCedula() { 
        historialesPorCedula.clear(); 
        ArrayList<Tratamiento> tratamientos = gestorTratamientos.listarTratamientos();
        for (int i = 0; i < tratamientos.size(); i++) { 
            Tratamiento tratamiento = tratamientos.get(i);
            String cedulaPaciente = tratamiento .getPaciente() .getCedula();
            if (!historialesPorCedula.containsKey( cedulaPaciente)) {
                ArrayList<Tratamiento> nuevoHistorial = new ArrayList<Tratamiento>();
                historialesPorCedula.put( cedulaPaciente, nuevoHistorial ); 
            }
            ArrayList<Tratamiento> historialPaciente = historialesPorCedula.get( cedulaPaciente ); 
            historialPaciente.add( tratamiento ); 
        } 
    }
}

