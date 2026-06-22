
package validaciongeneraldedatos;
import java.time.LocalDate; 
import java.time.LocalTime; 
import java.time.format.DateTimeParseException;
public class ValidadorDatos {
    //throws le "avisa" al programa que podria haber un error.
    //verifica si la cedula es valida, con throws avisa que podria haber un error, los if son las condiciones para que el mensaje de error salga
    public void validarCedula(String cedula) throws ExcepcionDatoInvalido { 
        if (cedula == null || cedula.trim().isEmpty()) { 
            throw new ExcepcionDatoInvalido( "La cédula no puede estar vacía." ); 
        } 
        if (cedula.length() != 10) { 
            throw new ExcepcionDatoInvalido( "La cédula debe tener exactamente diez dígitos." ); 
        } 
        for (int i = 0; i < cedula.length(); i++) { 
            char caracter = cedula.charAt(i); 
            if (!Character.isDigit(caracter)) { 
                throw new ExcepcionDatoInvalido( "La cédula debe contener solamente números." ); 
            } 
        } 
    }
    //verifica si el numero de telefono es valido, con throws avisa que podria haber un error, los if son las condiciones para que el mensaje de error salga y con el for se recorre uno a uno

    public void validarTelefono(String telefono) throws ExcepcionDatoInvalido {
        if (telefono == null || telefono.trim().isEmpty()) { 
            throw new ExcepcionDatoInvalido( "El teléfono no puede estar vacío." );
        } 
        if (telefono.length() != 10) {
            throw new ExcepcionDatoInvalido( "El teléfono debe tener exactamente diez dígitos." );
        } 
        for (int i = 0; i < telefono.length(); i++) {
            char caracter = telefono.charAt(i);
            if (!Character.isDigit(caracter)) { 
                throw new ExcepcionDatoInvalido( "El teléfono debe contener solamente números." ); 
            } 
        } 
    }
    //de la misma forma que los otros dos, se avisa que puede ocurrir un error y se lo valida con if, y luego se recorre uno a uno con un for. 
    public void validarNombre(String nombre) throws ExcepcionDatoInvalido {
        if (nombre == null || nombre.trim().isEmpty()) { 
            throw new ExcepcionDatoInvalido( "El nombre no puede estar vacío." ); 
        } 
        for (int i = 0; i < nombre.length(); i++) { 
            char caracter = nombre.charAt(i);
            if (Character.isDigit(caracter)) { 
                throw new ExcepcionDatoInvalido( "El nombre no puede contener números." ); 
            } 
        } 
    } public void validarEdad(int edad) throws ExcepcionDatoInvalido { 
        if (edad <= 0) { 
            throw new ExcepcionDatoInvalido( "La edad debe ser mayor que cero." ); 
        }
    }
    public void validarIdentificador(int identificador) throws ExcepcionDatoInvalido { 
        if (identificador <= 0) {
            throw new ExcepcionDatoInvalido( "El identificador debe ser mayor que cero." ); 
        } 
    }
    public void validarDuracion(int duracion) throws ExcepcionDatoInvalido { 
        if (duracion <= 0) { 
            throw new ExcepcionDatoInvalido( "La duración debe ser mayor que cero." );
        } 
    } 
    public void validarNumeroSesiones(int numeroSesiones) throws ExcepcionDatoInvalido {
        if (numeroSesiones <= 0) {
            throw new ExcepcionDatoInvalido( "El número de sesiones debe ser mayor que cero." );
        } 
    } 
    public void validarCosto(double costo) throws ExcepcionDatoInvalido { 
        if (costo <= 0) { 
            throw new ExcepcionDatoInvalido( "El costo debe ser mayor que cero." );
        } 
    }
    public LocalDate validarYConvertirFecha(String fecha) throws ExcepcionDatoInvalido { 
        if (fecha == null || fecha.trim().isEmpty()) { 
            throw new ExcepcionDatoInvalido( "La fecha no puede estar vacía." ); 
        } 
        if (fecha.length() != 10) { 
            throw new ExcepcionDatoInvalido( "La fecha debe tener el formato AAAA-MM-DD." ); 
        } 
        if (fecha.charAt(4) != '-' || fecha.charAt(7) != '-') {
            throw new ExcepcionDatoInvalido( "La fecha debe tener el formato AAAA-MM-DD." ); 
        } 
        try {
            return LocalDate.parse(fecha); 
        } 
        catch (DateTimeParseException excepcion) { 
            throw new ExcepcionDatoInvalido( "La fecha ingresada no es válida." ); 
        } 
    }
    public LocalTime validarYConvertirHora(String hora) throws ExcepcionDatoInvalido { 
        if (hora == null || hora.trim().isEmpty()) { 
            throw new ExcepcionDatoInvalido( "La hora no puede estar vacía." ); 
        }
        if (hora.length() != 5) { 
            throw new ExcepcionDatoInvalido( "La hora debe tener el formato HH:mm." ); 
        } 
        if (hora.charAt(2) != ':') {
            throw new ExcepcionDatoInvalido( "La hora debe tener el formato HH:mm." ); 
        } 
        try {
            return LocalTime.parse(hora); 
        } 
        catch (DateTimeParseException excepcion) { 
            throw new ExcepcionDatoInvalido( "La hora ingresada no es válida." );
        }
    }

}

