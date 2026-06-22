package gestiondemedicosydisponibilidadbasica;
import java.time.LocalTime;
public interface Atendible { 
    boolean estaDisponible(LocalTime hora);
}
