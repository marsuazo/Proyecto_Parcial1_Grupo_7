package gestiondetratamientos;
import java.util.ArrayList; 
import gestiondepacientes.ExcepcionPacienteNoEncontrado; 
import gestiondepacientes.GestorPacientes; 
import gestiondepacientes.Paciente; 
import validaciongeneraldedatos.ExcepcionDatoInvalido; 
import validaciongeneraldedatos.ValidadorDatos; 
public class GestorTratamientos {
    private ArrayList<Tratamiento> tratamientos;
    private GestorPacientes gestorPacientes;
    private ValidadorDatos validadorDatos;
    //constructor
    public GestorTratamientos( GestorPacientes gestorPacientes, ValidadorDatos validadorDatos) {
        this.gestorPacientes = gestorPacientes; 
        this.validadorDatos = validadorDatos; 
        this.tratamientos = new ArrayList<Tratamiento>();
    } 
    //metodo para registrar cirugia 
    public void registrarCirugia( int identificador, String nombre, int duracionDias, String cedulaPaciente, double costoMateriales) throws ExcepcionDatoInvalido, ExcepcionTratamientoDuplicado, ExcepcionPacienteNoEncontrado { 
        validadorDatos.validarIdentificador( identificador );
        validarIdentificadorUnico( identificador );
        validadorDatos.validarNombre( nombre );
        validadorDatos.validarDuracion( duracionDias );
        validadorDatos.validarCosto( costoMateriales );
        Paciente paciente = gestorPacientes .buscarPacientePorCedula( cedulaPaciente );
        Cirugia cirugia = new Cirugia( identificador, nombre, duracionDias, paciente, costoMateriales ); 
        tratamientos.add(cirugia); 
    } 
    //metodo para registrar terapia
    public void registrarTerapia( int identificador, String nombre, int duracionDias, String cedulaPaciente, int numeroSesiones) throws ExcepcionDatoInvalido, ExcepcionTratamientoDuplicado, ExcepcionPacienteNoEncontrado { 
        validadorDatos.validarIdentificador( identificador );
        validarIdentificadorUnico( identificador );
        validadorDatos.validarNombre( nombre );
        validadorDatos.validarDuracion( duracionDias );
        validadorDatos.validarNumeroSesiones( numeroSesiones ); 
        Paciente paciente = gestorPacientes .buscarPacientePorCedula( cedulaPaciente ); 
        Terapia terapia = new Terapia( identificador, nombre, duracionDias, paciente, numeroSesiones ); 
        tratamientos.add(terapia);
    }
    //metodo para la medicacion
    public void registrarMedicacion( int identificador, String nombre, int duracionDias, String cedulaPaciente) throws ExcepcionDatoInvalido, ExcepcionTratamientoDuplicado, ExcepcionPacienteNoEncontrado { 
        validadorDatos.validarIdentificador( identificador );
        validarIdentificadorUnico( identificador );
        validadorDatos.validarNombre( nombre );
        validadorDatos.validarDuracion( duracionDias ); 
        Paciente paciente = gestorPacientes .buscarPacientePorCedula( cedulaPaciente );
        Medicacion medicacion = new Medicacion( identificador, nombre, duracionDias, paciente );
        tratamientos.add(medicacion); 
    }
    //se listan los tratamientos
    public ArrayList<Tratamiento> listarTratamientos() { 
        ArrayList<Tratamiento> copiaTratamientos = new ArrayList<Tratamiento>();
        for (int i = 0; i < tratamientos.size(); i++) {
            copiaTratamientos.add( tratamientos.get(i) ); 
        } 
        return copiaTratamientos; 
    }
    private void validarIdentificadorUnico( int identificador) throws ExcepcionTratamientoDuplicado { 
        for (int i = 0; i < tratamientos.size(); i++) { 
            Tratamiento tratamiento = tratamientos.get(i); 
            if (tratamiento.getIdentificador() == identificador) {
                throw new ExcepcionTratamientoDuplicado( "Ya existe un tratamiento " + "con ese " + "identificador." ); 
            } 
        } 
    } 
}

