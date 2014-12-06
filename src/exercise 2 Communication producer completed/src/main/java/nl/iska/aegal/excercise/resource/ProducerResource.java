package nl.iska.aegal.excercise.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by vagrant on 12/6/14.
 */
@Path("/")
public class ProducerResource {

    private final String topic;
    private final ProducerConfig producerConfig;

    public ProducerResource(String topic) {
        this.topic = topic;
        Properties props = new Properties();

        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");

        producerConfig = new ProducerConfig(props);
    }

    @GET
    @Path("{message}")
    public void sendMessage(@PathParam("message") String message) {
        Producer<String, String> producer = new Producer<>(producerConfig);

        KeyedMessage<String, String> data = new KeyedMessage<>(topic, message);
        producer.send(data);

        producer.close();
    }

}
