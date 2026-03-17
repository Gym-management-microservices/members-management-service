package micro.gym.membersmanagementservice.service;

import micro.gym.membersmanagementservice.model.DatosEntrenamiento;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EntrenamientoProducer {

    private final KafkaTemplate<String, DatosEntrenamiento> kafkaTemplate;

    public EntrenamientoProducer(KafkaTemplate<String, DatosEntrenamiento> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarDatos(DatosEntrenamiento datos) {
        kafkaTemplate.send("datos-entrenamiento", datos.getMemberId().getMember_value(), datos)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println("Error enviando datos: " + ex.getMessage());
                    } else {
                        System.out.println("Entrenamiento enviado — Member: " + datos.getMemberId());
                    }
                });
    }
}
