package gestiondemedicosydisponibilidadbasica;
import java.time.LocalTime; 
import persona.Persona; 
public class Medico extends Persona implements Atendible { 
    private String especialidad; 
    private LocalTime horaInicio; 
    private LocalTime horaFin; 
    public Medico( String cedula, String nombreCompleto, String especialidad, LocalTime horaInicio, LocalTime horaFin) { 
        super(cedula, nombreCompleto); 
        this.especialidad = especialidad; 
        this.horaInicio = horaInicio; 
        this.horaFin = horaFin; 
    } 
    //getters para poder visualizar y usarlos en todas las otras clases, etc. 
    public String getEspecialidad() { 
        return especialidad; 
    }
    //setters por si se necesita algun cambio 
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad; 
    } 
    public LocalTime getHoraInicio() { 
        return horaInicio; 
    }
    public void setHoraInicio(LocalTime horaInicio) { 
        this.horaInicio = horaInicio; 
    } 
    public LocalTime getHoraFin() {
        return horaFin; 
    } 
    public void setHoraFin(LocalTime horaFin) { 
        this.horaFin = horaFin;
    } 
    //sobrescritura de los metodos de la interfaz.
    @Override
    public boolean estaDisponible(LocalTime hora) { 
        if (hora == null) { 
            return false;
        } 
        if (horaInicio == null || horaFin == null) { 
            return false; 
        } 
        if (hora.isBefore(horaInicio)) { 
            return false;
        } 
        if (!hora.isBefore(horaFin)) { 
            return false; 
        } 
        return true; 
    } 
    @Override
    public String toString() { 
        return "Cédula: " + getCedula() + ", Nombre: " + getNombreCompleto() + ", Especialidad: " + especialidad + ", Horario: " + horaInicio + " - " + horaFin; 
    }
    @Override public boolean equals(Object objeto) { 
        if (this == objeto) { return true; 
        } 
        if (!(objeto instanceof Medico)) { 
            return false; 
        }
        Medico otroMedico = (Medico) objeto; 
        return getCedula().equals( otroMedico.getCedula() ); 
    } 
    @Override
    public int hashCode() { 
        return getCedula().hashCode(); 
    } 
}
