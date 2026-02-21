package MiembrosService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor(force = true)

public class Suscripcion {
    LocalDate fechaInicio;
    LocalDate fechaFin;

    public Suscripcion(LocalDate fechaInicio, LocalDate fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    public boolean isActive(){
        LocalDate ahora = LocalDate.now();
        return fechaInicio != null &&
                fechaFin != null &&
                ( !ahora.isBefore(fechaInicio) ) &&
                ( !ahora.isAfter(fechaFin) );
    }
}
