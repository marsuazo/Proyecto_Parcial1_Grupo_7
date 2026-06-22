
package gestiondemedicosydisponibilidadbasica;
import java.time.LocalTime; 
import java.util.ArrayList; 
import gestiondecitasmedicas.GestorCitas; 
import validaciongeneraldedatos.ExcepcionDatoInvalido;
import validaciongeneraldedatos.ValidadorDatos;
public class GestorMedicos {
    private ArrayList<Medico> medicos; 
    private ValidadorDatos validadorDatos;
    public GestorMedicos(ValidadorDatos validadorDatos) { 
        this.validadorDatos = validadorDatos;
        this.medicos = new ArrayList<Medico>(); 
    } 
    //se registran a los medicos y se los guardan en un arrayList.
    public void registrarMedico( String cedula, String nombreCompleto, String especialidad, LocalTime horaInicio, LocalTime horaFin) throws ExcepcionDatoInvalido, ExcepcionMedicoDuplicado { 
        validadorDatos.validarCedula(cedula); 
        validadorDatos.validarNombre(nombreCompleto); 
        validarEspecialidad(especialidad); 
        validarHorario(horaInicio, horaFin);
        for (int i = 0; i < medicos.size(); i++) { 
            Medico medicoRegistrado = medicos.get(i);
            if (medicoRegistrado.getCedula().equals(cedula)) {
                throw new ExcepcionMedicoDuplicado( "Ya existe un medico con esa cedula." );
            }
        } 
        Medico nuevoMedico = new Medico( cedula, nombreCompleto, especialidad, horaInicio, horaFin );
        medicos.add(nuevoMedico);
    } 
    //se busca al medico por medio de la cedula, tambien pueden haber errores, asi que si hay algo mal, se manda un mensaje de error.
    public Medico buscarMedicoPorCedula( String cedula) throws ExcepcionDatoInvalido, ExcepcionMedicoNoEncontrado { 
        validadorDatos.validarCedula(cedula);
        for (int i = 0; i < medicos.size(); i++) {
            Medico medico = medicos.get(i);
            if (medico.getCedula().equals(cedula)) {
                return medico;
            } 
        }
        throw new ExcepcionMedicoNoEncontrado( "No existe un medico con esa cedula." ); 
    }
    public ArrayList<Medico> listarMedicos() { 
        ArrayList<Medico> copiaMedicos = new ArrayList<Medico>(); 
        for (int i = 0; i < medicos.size(); i++) { copiaMedicos.add(medicos.get(i));
        }
        return copiaMedicos;
    } 
    public ArrayList<Medico> listarMedicosPorEspecialidad( String especialidad) throws ExcepcionDatoInvalido { validarEspecialidad(especialidad); 
    ArrayList<Medico> medicosEncontrados = new ArrayList<Medico>();
    for (int i = 0; i < medicos.size(); i++) {
        Medico medico = medicos.get(i);
        if (medico.getEspecialidad() .equals(especialidad)) { 
            medicosEncontrados.add(medico); 
        }
    }
    return medicosEncontrados; 
    }
    public void modificarMedico( String cedulaActual, String nuevaCedula, String nuevoNombre, String nuevaEspecialidad, LocalTime nuevaHoraInicio, LocalTime nuevaHoraFin, GestorCitas gestorCitas) throws ExcepcionDatoInvalido, ExcepcionMedicoNoEncontrado, ExcepcionMedicoDuplicado { 
        Medico medico = buscarMedicoPorCedula( cedulaActual );
        validadorDatos.validarCedula(nuevaCedula); 
        validadorDatos.validarNombre(nuevoNombre);
        validarEspecialidad(nuevaEspecialidad); 
        validarHorario( nuevaHoraInicio, nuevaHoraFin );
        for (int i = 0; i < medicos.size(); i++) { 
            Medico medicoRegistrado = medicos.get(i); 
            if (medicoRegistrado != medico && medicoRegistrado.getCedula() .equals(nuevaCedula)) { 
                throw new ExcepcionMedicoDuplicado( "La nueva cedula ya pertenece " + "a otro medico." ); 
            } 
        } 
        if (gestorCitas == null) { 
            throw new ExcepcionDatoInvalido( "No se puede comprobar el cambio " + "de horario sin el gestor de citas." ); 
        }
        gestorCitas.validarCitasProgramadasDentroHorario( medico, nuevaHoraInicio, nuevaHoraFin );
        medico.setCedula(nuevaCedula);
        medico.setNombreCompleto(nuevoNombre);
        medico.setEspecialidad(nuevaEspecialidad);
        medico.setHoraInicio(nuevaHoraInicio); 
        medico.setHoraFin(nuevaHoraFin); 
    } 
    private void validarEspecialidad( String especialidad) throws ExcepcionDatoInvalido {
        if (especialidad == null || especialidad.trim().isEmpty()) { 
            throw new ExcepcionDatoInvalido( "La especialidad no puede estar vacía." ); 
        } 
       if (!especialidad.equals("CARDIOLOGIA") && !especialidad.equals("PEDIATRIA") && !especialidad.equals("MEDICINA GENERAL")) { 
           throw new ExcepcionDatoInvalido( "La especialidad debe ser " + "CARDIOLOGIA, PEDIATRIA " + "o MEDICINA GENERAL." ); 
       }
    }
    private void validarHorario( LocalTime horaInicio, LocalTime horaFin) throws ExcepcionDatoInvalido { 
        if (horaInicio == null || horaFin == null) { 
            throw new ExcepcionDatoInvalido( "Las horas del medico no pueden ser nulas." );
        } 
        if (!horaInicio.isBefore(horaFin)) { 
            throw new ExcepcionDatoInvalido( "La hora inicial debe ser menor " + "que la hora final." );
        } 
    }
}

