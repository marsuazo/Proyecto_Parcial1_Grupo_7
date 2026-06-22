package persona;
//clase abstracta para que Paciente y Medico.
public abstract class Persona { 
    private String cedula; 
    private String nombreCompleto;
    public Persona(String cedula, String nombreCompleto) { 
        this.cedula = cedula; 
        this.nombreCompleto = nombreCompleto; 
    }
    public String getCedula() {
        return cedula; 
    }
    public void setCedula(String cedula) { 
        this.cedula = cedula; 
    } 
    public String getNombreCompleto() { 
        return nombreCompleto; 
    } 
    public void setNombreCompleto(String nombreCompleto) { 
        this.nombreCompleto = nombreCompleto; 
    }
}