package gestiondetratamientos;
import gestiondepacientes.Paciente;
public class Cirugia extends Tratamiento { 
    //como el documento no dice el costo, ni tampoco si se pide por teclado, lo dejaremos predeterminado. 
   //el costo los agregue yo porque en el documento decia que tenian que ser estaticos. 

    public static final double COSTO_BASE = 150.0;
    private double costoMateriales; 
    public Cirugia( int identificador, String nombre, int duracionDias, Paciente paciente, double costoMateriales) {
        super( identificador, nombre, duracionDias, paciente );
        this.costoMateriales = costoMateriales; 
    } 
    public double getCostoMateriales() { 
        return costoMateriales; 
    }
    @Override 
    public double calcularCosto() { 
        return COSTO_BASE + costoMateriales; 
    } 
    @Override 
    public String obtenerTipoTratamiento() {
        return "CIRUGÍA";
    } 
    @Override public String toString() { 
        return super.toString() + ", Costo de materiales: " + costoMateriales;
    }
}
