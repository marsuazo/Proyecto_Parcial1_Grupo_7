
package validaciongeneraldedatos;

//usamos Exception para las validaciones mediante excepciones personalizadas.
public class ExcepcionDatoInvalido extends Exception{
    public ExcepcionDatoInvalido(String mensaje) { 
        super(mensaje); 
    }
}
