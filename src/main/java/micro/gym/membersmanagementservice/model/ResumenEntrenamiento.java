package micro.gym.membersmanagementservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumenEntrenamiento {
    private int totalMinutos;
    private int totalCalorias;
    private int sesiones;

    public ResumenEntrenamiento actualizar(DatosEntrenamiento datos) {
        this.totalMinutos += datos.getDuracionMinutos();
        this.totalCalorias += datos.getCalorias();
        this.sesiones++;
        return this;
    }
}
