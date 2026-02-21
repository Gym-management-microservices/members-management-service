package MiembrosService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor

public class FechaInscripcion {

    private final LocalDate date_inscripcion;
    // constructor y métodos de validación
}
