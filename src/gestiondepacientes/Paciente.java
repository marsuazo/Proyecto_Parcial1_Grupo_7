
package gestiondepacientes;

import persona.Persona;

public class Paciente extends Persona{
    private int edad;
    private String telefono; 
    private String tipoSeguro; 
    public Paciente( String cedula, String nombreCompleto, int edad, String telefono, String tipoSeguro) {
        super(cedula, nombreCompleto); 
        this.edad = edad;
        this.telefono = telefono; 
        this.tipoSeguro = tipoSeguro; 
    }
    public int getEdad() {
        return edad;
    } 
    public void setEdad(int edad) { 
        this.edad = edad; 
    } 
    public String getTelefono() { 
        return telefono; 
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    } 
    public String getTipoSeguro() { 
        return tipoSeguro; 
    } 
    public void setTipoSeguro(String tipoSeguro) { 
        this.tipoSeguro = tipoSeguro; 
    } 
    //se sobrescriben los metodos de Persona.
    @Override 
    public String toString() { 
        return "Cédula: " + getCedula() + ", Nombre: " + getNombreCompleto() + ", Edad: " + edad + ", Teléfono: " + telefono + ", Seguro: " + tipoSeguro;
    } 
    @Override public boolean equals(Object objeto) { 
        if (this == objeto) {
            return true; 
        } 
        if (!(objeto instanceof Paciente)) {
            return false; 
        }
        Paciente otroPaciente = (Paciente) objeto; 
        return getCedula().equals( otroPaciente.getCedula() ); 
    } 
    @Override public int hashCode() {
        return getCedula().hashCode(); 
    }
    
}
