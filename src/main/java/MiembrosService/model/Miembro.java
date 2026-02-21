package MiembrosService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Miembro {

    @EmbeddedId
    private MiembroId miembroid;

    @Column(name = "nombre")
    private String nombre;

    @Embedded
    private Email email;

    @Embedded
    private FechaInscripcion fechaInscripcion;

    @Embedded
    private Suscripcion suscripcion;

    public void cambiarEmail(Email nuevoEmail) {
        this.email = nuevoEmail;
    }

}
