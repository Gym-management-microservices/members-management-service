package micro.gym.membersmanagementservice.service;

import micro.gym.membersmanagementservice.model.ResumenEntrenamiento;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ResumenConsumer {

    @KafkaListener(
            topics = "resumen-entrenamiento",
            groupId = "resumen-grupo",
            properties = {
                    "spring.json.value.default.type=micro.gym.membersmanagementservice.model.ResumenEntrenamiento",
                    "spring.json.use.type.headers=false"
            }
    )
    public void recibirResumen(ConsumerRecord<String, ResumenEntrenamiento> record) {
        ResumenEntrenamiento resumen = record.value();
        System.out.println("=== RESUMEN SEMANAL ===");
        System.out.println("Member    : " + record.key());
        System.out.println("Sesiones  : " + resumen.getSesiones());
        System.out.println("Minutos   : " + resumen.getTotalMinutos());
        System.out.println("Calorías  : " + resumen.getTotalCalorias());
        System.out.println("======================");
    }
}