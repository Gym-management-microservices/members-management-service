package micro.gym.membersmanagementservice.service;

import micro.gym.membersmanagementservice.dto.ClassScheduleEventDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClassScheduleConsumer {

    @RabbitListener(queues = "class-schedule-queue")
    public void receiveScheduleChange(ClassScheduleEventDTO event) {
        System.out.println("Recibido cambio de horario para clase: " + event.getClassName());
        System.out.println("  Clase: " + event.getClassName());
        System.out.println("  Entrenador: " + event.getTrainerId());
        System.out.println("  Nuevo inicio: " + event.getNewScheduleStart());
        System.out.println("  Nuevo fin: " + event.getNewScheduleEnd());

    }
}
