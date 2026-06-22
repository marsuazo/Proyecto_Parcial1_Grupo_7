package reportesdelhospital;
import java.util.ArrayList;
import java.util.HashMap;
import gestiondecitasmedicas.Cita; 
import gestiondecitasmedicas.EstadoCita; 
import gestiondecitasmedicas.GestorCitas;
import gestiondemedicosydisponibilidadbasica.Medico; 
import gestiondetratamientos.GestorTratamientos; 
import gestiondetratamientos.Tratamiento;
public class GestorReportesHospital { 
    private GestorCitas gestorCitas;
    private GestorTratamientos gestorTratamientos;
    public GestorReportesHospital( GestorCitas gestorCitas, GestorTratamientos gestorTratamientos) { 
        this.gestorCitas = gestorCitas; 
        this.gestorTratamientos = gestorTratamientos; 
    } 
    //HashMap: lo que hace es como una lista pero cada dato tiene una clave. 
    //put: significa guardar un dato con una clave
    public HashMap<String, Integer> contarCitasAtendidasPorEspecialidad() { 
        HashMap<String, Integer> cantidades = new HashMap<String, Integer>();
        cantidades.put("CARDIOLOGIA", 0); 
        cantidades.put("PEDIATRIA", 0); 
        cantidades.put("MEDICINA GENERAL", 0);
        ArrayList<Cita> citas = gestorCitas.listarCitas(); 
        for (int i = 0; i < citas.size(); i++) { 
            Cita cita = citas.get(i);
            if (cita.getEstado() == EstadoCita.ATENDIDA) {
                Medico medico = cita.getMedico(); 
                String especialidad = medico.getEspecialidad();
                if (cantidades.containsKey( especialidad)) { 
                    int cantidadActual = cantidades.get( especialidad );
                    cantidades.put( especialidad, cantidadActual + 1 ); 
                }
            } 
        } 
        return cantidades; 
    } 
    public double calcularIngresoTotalTratamientos() {
        ArrayList<Tratamiento> tratamientos = gestorTratamientos .listarTratamientos();
        double ingresoTotal = 0.0; 
        for (int i = 0; i < tratamientos.size(); i++) {
            Tratamiento tratamiento = tratamientos.get(i); 
            ingresoTotal = ingresoTotal + tratamiento.calcularCosto();
        }
        return ingresoTotal; 
    } 
    public HashMap<String, Double> calcularIngresosPorTipoTratamiento() { 
        HashMap<String, Double> ingresosPorTipo = new HashMap<String, Double>();
        //guardamos los datos con clave con .put
        ingresosPorTipo.put("CIRUGÍA", 0.0); 
        ingresosPorTipo.put("TERAPIA", 0.0);
        ingresosPorTipo.put("MEDICACIÓN", 0.0);
        ArrayList<Tratamiento> tratamientos = gestorTratamientos .listarTratamientos();
        //aqui se van actualizando los datos y se va agregando.
        for (int i = 0; i < tratamientos.size(); i++) { 
            Tratamiento tratamiento = tratamientos.get(i);
            String tipo = tratamiento .obtenerTipoTratamiento();
            double costo = tratamiento.calcularCosto(); 
            if (ingresosPorTipo.containsKey( tipo)) { 
                
                double subtotalActual = ingresosPorTipo.get( tipo );
                ingresosPorTipo.put( tipo, subtotalActual + costo ); 
            }
        }
        return ingresosPorTipo;
    } 
}
