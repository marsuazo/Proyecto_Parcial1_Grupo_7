package gestiondetratamientos;
import gestiondepacientes.Paciente; 
public class Terapia extends Tratamiento { 
    //como el documento no dice el costo, ni tampoco si se pide por teclado, lo dejaremos predeterminado.
    //el costo los agregue yo porque en el documento decia que tenian que ser estaticos. 
    public static final double COSTO_BASE = 25.0;
    private int numeroSesiones;
    public Terapia( int identificador, String nombre, int duracionDias, Paciente paciente, int numeroSesiones) { 
        super( identificador, nombre, duracionDias, paciente );
        this.numeroSesiones = numeroSesiones; 
    } 
    public int getNumeroSesiones() { 
        return numeroSesiones; 
    } 
    @Override 
    public double calcularCosto() { 
        return COSTO_BASE * numeroSesiones; 
    }
    @Override
    public String obtenerTipoTratamiento() {
        return "TERAPIA"; 
    }
    @Override public String toString() { 
        return super.toString() + ", Número de sesiones: " + numeroSesiones; 
    } 
}
