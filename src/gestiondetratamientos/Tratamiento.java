
package gestiondetratamientos;
import gestiondepacientes.Paciente; 
public abstract class Tratamiento implements Pagable {
    //como el documento no dice el costo, ni tampoco si se pide por teclado, lo dejaremos predeterminado, los valores los agregue yo mismo. 
    protected static final double COSTO_BASE = 0.0; 
    private int identificador;
    private String nombre;
    private int duracionDias;
    private Paciente paciente;
    public Tratamiento( int identificador, String nombre, int duracionDias, Paciente paciente) { 
        this.identificador = identificador; 
        this.nombre = nombre;
        this.duracionDias = duracionDias; 
        this.paciente = paciente; 
    }
    public int getIdentificador() { 
        return identificador; 
    }
    public String getNombre() { 
        return nombre; 
    } 
    public int getDuracionDias() { 
        return duracionDias; 
    } 
    public Paciente getPaciente() { 
        return paciente; 
    } 
    @Override 
    public abstract double calcularCosto(); 
    public abstract String obtenerTipoTratamiento(); 
    @Override 
    public String toString() { 
        return "Identificador: " + identificador + ", Nombre: " + nombre + ", Tipo: " + obtenerTipoTratamiento() + ", Duración: " + duracionDias + " días" + ", Paciente: " + paciente.getNombreCompleto() + ", Cédula del paciente: " + paciente.getCedula() + ", Costo: " + calcularCosto(); 
    } 
    @Override public boolean equals(Object objeto) { 
        if (this == objeto) {
            return true;
        } 
        if (!(objeto instanceof Tratamiento)) { 
            return false; 
        } 
        Tratamiento otroTratamiento = (Tratamiento) objeto; 
        return identificador == otroTratamiento.getIdentificador();
    } 
    @Override 
    public int hashCode() {
        return identificador; 
    }
}

