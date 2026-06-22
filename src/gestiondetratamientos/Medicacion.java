package gestiondetratamientos;
import gestiondepacientes.Paciente;
public class Medicacion extends Tratamiento { 
    //como el documento no dice el costo, ni tampoco si se pide por teclado, lo dejaremos predeterminado. 
    //el costo los agregue yo porque en el documento decia que tenian que ser estaticos. 
    public static final double COSTO_BASE = 10.0;
    public Medicacion( int identificador, String nombre, int duracionDias, Paciente paciente) { 
        super( identificador, nombre, duracionDias, paciente ); 
    } 
    @Override 
    public double calcularCosto() { 
        return COSTO_BASE * getDuracionDias(); 
    } 
    @Override
    public String obtenerTipoTratamiento() {
        return "MEDICACIÓN"; 
    } 
    @Override 
    public String toString() { 
        return super.toString();
    }
}
