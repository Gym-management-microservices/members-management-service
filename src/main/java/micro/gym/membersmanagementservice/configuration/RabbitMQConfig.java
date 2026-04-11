package micro.gym.membersmanagementservice.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;


@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "class-schedule-queue";
    public static final String INSCRIPCIONES_EXCHANGE = "inscripciones.exchange";
    public static final String INSCRIPCIONES_QUEUE    = "inscripciones.queue";
    public static final String INSCRIPCIONES_ROUTING  = "inscripciones.routingkey";

    @Bean
    public Queue clasesHorarioQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange inscripcionesExchange() {
        return new TopicExchange(INSCRIPCIONES_EXCHANGE);
    }

    @Bean
    public Queue inscripcionesQueue() {
        return new Queue(INSCRIPCIONES_QUEUE, true);
    }

    @Bean
    public Binding inscripcionesBinding(Queue inscripcionesQueue, TopicExchange inscripcionesExchange) {
        return BindingBuilder
                .bind(inscripcionesQueue)
                .to(inscripcionesExchange)
                .with(INSCRIPCIONES_ROUTING);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        return factory;
    }
}
