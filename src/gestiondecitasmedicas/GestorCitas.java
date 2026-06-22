package gestiondecitasmedicas;
import java.time.LocalDate; 
import java.time.LocalTime; 
import java.util.ArrayList; 
import gestiondepacientes.Paciente; 
import gestiondepacientes.GestorPacientes; 
import gestiondepacientes.ExcepcionPacienteNoEncontrado; 
import gestiondemedicosydisponibilidadbasica.Medico; 
import gestiondemedicosydisponibilidadbasica.GestorMedicos; 
import gestiondemedicosydisponibilidadbasica.ExcepcionMedicoNoEncontrado; 
import validaciongeneraldedatos.ValidadorDatos; 
import validaciongeneraldedatos.ExcepcionDatoInvalido; 
public class GestorCitas { 
   private ArrayList<Cita> citas; 
   private GestorPacientes gestorPacientes; 
   private GestorMedicos gestorMedicos; 
   private ValidadorDatos validadorDatos;
   public GestorCitas( GestorPacientes gestorPacientes, GestorMedicos gestorMedicos, ValidadorDatos validadorDatos) { 
       this.gestorPacientes = gestorPacientes;
       this.gestorMedicos = gestorMedicos; 
       this.validadorDatos = validadorDatos; 
       this.citas = new ArrayList<Cita>(); 
   }
   //metodo para registrar cita, se validan todos los datos y se agrega la cita en la lista. 
   public void registrarCita( int identificador, String cedulaPaciente, String cedulaMedico, LocalDate fecha, LocalTime hora) throws ExcepcionDatoInvalido, ExcepcionCitaDuplicada, ExcepcionPacienteNoEncontrado, ExcepcionMedicoNoEncontrado, ExcepcionMedicoNoDisponible { 
       validadorDatos.validarIdentificador( identificador );
       validarIdentificadorUnico(identificador); 
       Paciente paciente = gestorPacientes.buscarPacientePorCedula( cedulaPaciente );
       Medico medico = gestorMedicos.buscarMedicoPorCedula( cedulaMedico ); 
       validarFechaHoraFutura(fecha, hora);
       validarDisponibilidadMedico( medico, fecha, hora, null );
       Cita nuevaCita = new Cita( identificador, paciente, medico, fecha, hora );
       citas.add(nuevaCita); 
   } 
   //metodo para buscar cita en la lista por el identificador
   public Cita buscarCitaPorIdentificador( int identificador) throws ExcepcionDatoInvalido, ExcepcionCitaNoEncontrada { 
       validadorDatos.validarIdentificador( identificador );
       for (int i = 0; i < citas.size(); i++) { 
           Cita cita = citas.get(i); 
           if (cita.getIdentificador() == identificador) { 
               return cita; 
           } 
       } 
       throw new ExcepcionCitaNoEncontrada( "No existe una cita con ese identificador." ); 
   } 
   //metodo para modificar la cita, se validan los datos, y si todo es correcto, se cambian
   public void modificarCita( int identificador, String nuevaCedulaMedico, LocalDate nuevaFecha, LocalTime nuevaHora) throws ExcepcionDatoInvalido, ExcepcionCitaNoEncontrada, ExcepcionMedicoNoEncontrado, ExcepcionMedicoNoDisponible, ExcepcionEstadoCitaInvalido {
       Cita cita = buscarCitaPorIdentificador( identificador ); 
       Medico nuevoMedico = gestorMedicos.buscarMedicoPorCedula( nuevaCedulaMedico ); 
       validarFechaHoraFutura( nuevaFecha, nuevaHora );
       validarDisponibilidadMedico( nuevoMedico, nuevaFecha, nuevaHora, cita );
       cita.actualizarProgramacion( nuevoMedico, nuevaFecha, nuevaHora );
   } 
   //metodo para cancelar cita
   public void cancelarCita( int identificador, boolean confirmacion) throws ExcepcionDatoInvalido, ExcepcionCitaNoEncontrada, ExcepcionCancelacionNoPermitida, ExcepcionEstadoCitaInvalido { 
       Cita cita = buscarCitaPorIdentificador( identificador );
       validarCancelacion( cita, confirmacion );
       cita.cancelar(); 
   }
   public void marcarCitaAtendida( int identificador) throws ExcepcionDatoInvalido, ExcepcionCitaNoEncontrada, ExcepcionEstadoCitaInvalido { 
       Cita cita = buscarCitaPorIdentificador( identificador ); 
       cita.marcarComoAtendida();
   } 
   public ArrayList<Cita> listarCitas() {
       ArrayList<Cita> copiaCitas = new ArrayList<Cita>(); 
       for (int i = 0; i < citas.size(); i++) {
           copiaCitas.add(citas.get(i));
       } 
       return copiaCitas;
   } 
   //valida las citas
   public void validarCitasProgramadasDentroHorario( Medico medico, LocalTime nuevaHoraInicio, LocalTime nuevaHoraFin) throws ExcepcionDatoInvalido {
       for (int i = 0; i < citas.size(); i++) { 
           Cita cita = citas.get(i); if (cita.getMedico().equals(medico) && cita.getEstado() == EstadoCita.PROGRAMADA) { 
               LocalTime horaCita = cita.getHora();
               if (horaCita.isBefore(nuevaHoraInicio) || !horaCita.isBefore( nuevaHoraFin)) { 
                   throw new ExcepcionDatoInvalido( "El nuevo horario deja una cita " + "programada fuera del " + "horario del médico." ); 
               }
           } 
       } 
   }
   private void validarIdentificadorUnico( int identificador) throws ExcepcionCitaDuplicada {
       for (int i = 0; i < citas.size(); i++) { 
           Cita cita = citas.get(i); if (cita.getIdentificador() == identificador) {
               throw new ExcepcionCitaDuplicada( "Ya existe una cita con ese " + "identificador." ); 
           } 
       } 
   } private void validarFechaHoraFutura( LocalDate fecha, LocalTime hora) throws ExcepcionDatoInvalido {
       if (fecha == null || hora == null) { 
           throw new ExcepcionDatoInvalido( "La fecha y la hora no pueden ser nulas." ); 
       } 
       LocalDate fechaActual = LocalDate.now();
       LocalTime horaActual = LocalTime.now(); 
       if (fecha.isBefore(fechaActual)) {
           throw new ExcepcionDatoInvalido( "La fecha y la hora de la cita " + "deben ser futuras." );
       } 
       if (fecha.equals(fechaActual) && !hora.isAfter(horaActual)) {
           throw new ExcepcionDatoInvalido( "La fecha y la hora de la cita " + "deben ser futuras." ); 
       } 
   } 
   private void validarDisponibilidadMedico( Medico medico, LocalDate fecha, LocalTime hora, Cita citaExcluida) throws ExcepcionMedicoNoDisponible { 
       validarHorarioMedico(medico, hora); 
       validarConflictoMedico( medico, fecha, hora, citaExcluida ); 
   } 
   private void validarHorarioMedico( Medico medico, LocalTime hora) throws ExcepcionMedicoNoDisponible { 
       if (!medico.estaDisponible(hora)) { 
           throw new ExcepcionMedicoNoDisponible( "El medico no se encuentra dentro " + "de su horario de atencion." ); 
       } 
   } 
   private void validarConflictoMedico( Medico medico, LocalDate fecha, LocalTime hora, Cita citaExcluida) throws ExcepcionMedicoNoDisponible { 
       for (int i = 0; i < citas.size(); i++) { 
           Cita cita = citas.get(i); 
           if (cita != citaExcluida && cita.getMedico().equals(medico) && cita.getFecha().equals(fecha) && cita.getHora().equals(hora) && cita.getEstado() != EstadoCita.CANCELADA) { 
               throw new ExcepcionMedicoNoDisponible( "El medico ya tiene una cita " + "en esa fecha y hora." ); 
           } 
       } 
   } 
   //valida la cancelacion
   private void validarCancelacion( Cita cita, boolean confirmacion) throws ExcepcionCancelacionNoPermitida { 
       if (!confirmacion) {
           throw new ExcepcionCancelacionNoPermitida( "La cancelacion de la cita " + "no fue confirmada." ); 
       }
       LocalDate fechaActual = LocalDate.now(); 
       LocalTime horaActual = LocalTime.now(); 
       if (cita.getFecha().isBefore(fechaActual)) { 
           throw new ExcepcionCancelacionNoPermitida( "La cita ya no puede cancelarse " + "porque su fecha y hora " + "ya llegaron o pasaron." ); 
       } 
       if (cita.getFecha().equals(fechaActual) && !cita.getHora().isAfter( horaActual)) {
           throw new ExcepcionCancelacionNoPermitida( "La cita ya no puede cancelarse " + "porque su fecha y hora " + "ya llegaron o pasaron." );
       } 
   }
}

