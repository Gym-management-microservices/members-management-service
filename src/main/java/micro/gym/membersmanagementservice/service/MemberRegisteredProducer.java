package micro.gym.membersmanagementservice.service;

import micro.gym.membersmanagementservice.configuration.RabbitMQConfig;
import micro.gym.membersmanagementservice.dto.MemberRegisteredEventDTO;
import micro.gym.membersmanagementservice.model.Member;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegisteredProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishMemberRegistered(Member member) {
        MemberRegisteredEventDTO event = new MemberRegisteredEventDTO(
                member.getId().getMember_value(),
                member.getName(),
                member.getEmail().getEmail_value()
        );
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.INSCRIPCIONES_EXCHANGE,
                RabbitMQConfig.INSCRIPCIONES_ROUTING,
                event
        );
        System.out.println("Publicada inscripción de miembro: " + member.getName());
    }
}

