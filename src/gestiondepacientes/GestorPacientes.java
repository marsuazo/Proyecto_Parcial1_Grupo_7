package gestiondepacientes;
import java.util.ArrayList;
import validaciongeneraldedatos.ValidadorDatos;
import validaciongeneraldedatos.ExcepcionDatoInvalido;
public class GestorPacientes {
    private ArrayList<Paciente> pacientes; 
    private ValidadorDatos validadorDatos; 
    public GestorPacientes(ValidadorDatos validadorDatos) { 
        this.validadorDatos = validadorDatos; 
        this.pacientes = new ArrayList<Paciente>();
    } 
    //se registra paciente y se pide los datos necesarios, tambien se avisa que alguno de los datos pueden ser invalidos o duplicados, con el for se busca si hay un paciente duplicado
    //o si es un nuevo paciente se lo agrega en la arrayList. 
    public void registrarPaciente( String cedula, String nombreCompleto, int edad, String telefono, String tipoSeguro) throws ExcepcionDatoInvalido, ExcepcionPacienteDuplicado { 
        validadorDatos.validarCedula(cedula);
        validadorDatos.validarNombre(nombreCompleto);
        validadorDatos.validarEdad(edad);
        validadorDatos.validarTelefono(telefono); 
        validarTipoSeguro(tipoSeguro);
        for (int i = 0; i < pacientes.size(); i++) { 
            Paciente pacienteRegistrado = pacientes.get(i);
            if (pacienteRegistrado.getCedula() .equals(cedula)) {
                throw new ExcepcionPacienteDuplicado( "Ya existe un paciente con esa cédula." ); 
            } 
        } 
        Paciente nuevoPaciente = new Paciente( cedula, nombreCompleto, edad, telefono, tipoSeguro ); 
        pacientes.add(nuevoPaciente);
    } 
    //se busca al paciente con la cedula, si se lo encuentra, el if actua y entrega el dato paciente, si no se lo encuentra, el throw muestra el mensaje de error.
    public Paciente buscarPacientePorCedula( String cedula) throws ExcepcionDatoInvalido, ExcepcionPacienteNoEncontrado { 
        validadorDatos.validarCedula(cedula); 
        for (int i = 0; i < pacientes.size(); i++) { 
            Paciente paciente = pacientes.get(i);
            if (paciente.getCedula().equals(cedula)) { 
                return paciente; 
            } 
        } 
        throw new ExcepcionPacienteNoEncontrado( "No existe un paciente con esa cédula." ); 
    }
    public void modificarPaciente( String cedulaActual, String nuevaCedula, String nuevoNombre, int nuevaEdad, String nuevoTelefono, String nuevoTipoSeguro) throws ExcepcionDatoInvalido, ExcepcionPacienteNoEncontrado, ExcepcionPacienteDuplicado {
        Paciente paciente = buscarPacientePorCedula( cedulaActual );
        validadorDatos.validarCedula(nuevaCedula);
        validadorDatos.validarNombre(nuevoNombre);
        validadorDatos.validarEdad(nuevaEdad);
        validadorDatos.validarTelefono(nuevoTelefono);
        validarTipoSeguro(nuevoTipoSeguro); 
        for (int i = 0; i < pacientes.size(); i++) {
            Paciente pacienteRegistrado = pacientes.get(i);
            if (pacienteRegistrado != paciente && pacienteRegistrado.getCedula() .equals(nuevaCedula)) { 
                throw new ExcepcionPacienteDuplicado( "La nueva cédula ya pertenece " + "a otro paciente." );
            }
        }
        paciente.setCedula(nuevaCedula);
        paciente.setNombreCompleto(nuevoNombre);
        paciente.setEdad(nuevaEdad); 
        paciente.setTelefono(nuevoTelefono);
        paciente.setTipoSeguro(nuevoTipoSeguro); 
    }
    public ArrayList<Paciente> listarPacientes() {
        ArrayList<Paciente> copiaPacientes = new ArrayList<Paciente>();
        for (int i = 0; i < pacientes.size(); i++) { 
            copiaPacientes.add(pacientes.get(i)); 
        } 
        return copiaPacientes; 
    } 
    //se valida el seguro, se avisa que puede ser un dato invalido, como en los anteriores, y se verifica con if, si es invalido se envia el mensaje de error. 
    //si ingresa por pantalla un seguro que no esta en la lista de opciones, se envia un mensaje de error. 
    private void validarTipoSeguro( String tipoSeguro) throws ExcepcionDatoInvalido { 
        if (tipoSeguro == null || tipoSeguro.trim().isEmpty()) { 
            throw new ExcepcionDatoInvalido( "El tipo de seguro no puede estar vacío." ); 
        } 
        if (!tipoSeguro.equals("IESS") && !tipoSeguro.equals("PRIVADO") && !tipoSeguro.equals("SIN SEGURO")) { 
            throw new ExcepcionDatoInvalido( "El tipo de seguro debe ser IESS, " + "PRIVADO o SIN SEGURO." ); 
        } 
    }
}
