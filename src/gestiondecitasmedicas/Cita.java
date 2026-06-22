
package gestiondecitasmedicas;
import java.time.LocalDate; 
import java.time.LocalTime; 
import gestiondepacientes.Paciente; 
import gestiondemedicosydisponibilidadbasica.Medico;
public class Cita {
    private int identificador;
    private Paciente paciente; 
    private Medico medico; 
    private LocalDate fecha; 
    private LocalTime hora; 
    private EstadoCita estado; 
    public Cita( int identificador, Paciente paciente, Medico medico, LocalDate fecha, LocalTime hora) { 
        this.identificador = identificador; 
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha; 
        this.hora = hora; 
        this.estado = EstadoCita.PROGRAMADA; 
    } 
    //get para poder usarlos y visualizarlos en otras clases, etc.
    public int getIdentificador() { 
        return identificador; 
    } 
    public Paciente getPaciente() { 
        return paciente; 
    } 
    public Medico getMedico() { 
        return medico; 
    } 
    public LocalDate getFecha() { 
        return fecha; 
    } 
    public LocalTime getHora() { 
        return hora; 
    } 
    public EstadoCita getEstado() { 
        return estado; 
    } 
    //Para modificar citas programadas, si es una cita que no aparece en el registro, se lanza un mensaje de error, el que dice en el throw. 
    public void actualizarProgramacion( Medico nuevoMedico, LocalDate nuevaFecha, LocalTime nuevaHora) throws ExcepcionEstadoCitaInvalido { 
        if (estado != EstadoCita.PROGRAMADA) {
            throw new ExcepcionEstadoCitaInvalido( "Solo se pueden modificar citas programadas." );
        } 
        this.medico = nuevoMedico; 
        this.fecha = nuevaFecha; 
        this.hora = nuevaHora; 
    }
    // metodo para poder cancelar citas, si la cita no esta en el registro manda un mensaje de error. 
    public void cancelar() throws ExcepcionEstadoCitaInvalido { 
        if (estado != EstadoCita.PROGRAMADA) {
            throw new ExcepcionEstadoCitaInvalido( "Solo se pueden cancelar citas programadas." ); 
        } 
        estado = EstadoCita.CANCELADA; 
    } 
    public void marcarComoAtendida() throws ExcepcionEstadoCitaInvalido { 
        if (estado != EstadoCita.PROGRAMADA) { 
            throw new ExcepcionEstadoCitaInvalido( "Solo se pueden marcar como atendidas " + "las citas programadas." ); 
        } 
        estado = EstadoCita.ATENDIDA; 
    } 
    //sobrescribir metodos. 
    @Override 
    public String toString() { 
        return "Identificador: " + identificador + ", Paciente: " + paciente.getNombreCompleto() + ", Cedula del paciente: " + paciente.getCedula() + ", Médico: " + medico.getNombreCompleto() + ", Cédula del médico: " + medico.getCedula() + ", Fecha: " + fecha + ", Hora: " + hora + ", Estado: " + estado; 
    } 
    @Override 
    public boolean equals(Object objeto) { 
        if (this == objeto) { 
            return true;
        } 
        if (!(objeto instanceof Cita)) { 
            return false;
        } 
        Cita otraCita = (Cita) objeto; 
        return identificador == otraCita.getIdentificador();
    }
    @Override
    public int hashCode() { 
        return identificador; 
    }
}

