package micro.gym.membersmanagementservice.configuration;

import micro.gym.membersmanagementservice.model.DatosEntrenamiento;
import micro.gym.membersmanagementservice.model.ResumenEntrenamiento;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    @Bean
    public KStream<String, DatosEntrenamiento> kStream(StreamsBuilder streamsBuilder) {

        JsonSerde<DatosEntrenamiento> datosSerde = new JsonSerde<>(DatosEntrenamiento.class);
        JsonSerde<ResumenEntrenamiento> resumenSerde = new JsonSerde<>(ResumenEntrenamiento.class);

        KStream<String, DatosEntrenamiento> stream = streamsBuilder.stream(
                "datos-entrenamiento",
                Consumed.with(Serdes.String(), datosSerde));

        stream
                .groupByKey()
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofDays(7)))
                .aggregate(
                        ResumenEntrenamiento::new,
                        (key, value, aggregate) -> aggregate.actualizar(value),
                        Materialized.<String, ResumenEntrenamiento, WindowStore<Bytes, byte[]>>as(
                                        "resumen-entrenamiento-store")
                                .withValueSerde(resumenSerde)
                )
                .toStream()
                .map((key, value) -> KeyValue.pair(key.key(), value))
                .to("resumen-entrenamiento", Produced.with(Serdes.String(), resumenSerde));

        return stream;
    }
}