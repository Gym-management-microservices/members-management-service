package micro.gym.membersmanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosEntrenamiento {
    private MemberId memberId;
    private String ejercicio;
    private int duracionMinutos;
    private int calorias;
    private LocalDateTime fecha;
}