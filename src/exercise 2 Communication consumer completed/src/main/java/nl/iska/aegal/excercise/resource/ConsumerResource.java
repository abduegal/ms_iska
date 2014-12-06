package nl.iska.aegal.excercise.resource;

import kafka.consumer.*;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.producer.ProducerConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.*;

/**
 * Created by vagrant on 12/7/14.
 */
@Path("/")
public class ConsumerResource {

    private List<String> messages = new ArrayList<>();

    private class MyConsumer {

        public void run(String topic){
            Properties props = new Properties();

            props.put("zookeeper.connect", "localhost:2181");
            props.put("group.id", "1");
            props.put("zookeeper.session.timeout.ms", "400");
            props.put("zookeeper.sync.time.ms", "200");
            props.put("auto.commit.interval.ms", "1000");
            ConsumerConfig consumerConfig = new ConsumerConfig(props);

            ConsumerConnector connector = Consumer.createJavaConsumerConnector(consumerConfig);

            List<KafkaStream<byte[], byte[]>> streams =
                    connector.createMessageStreams(Collections.singletonMap(topic, 1)).get(topic);


            for (final KafkaStream stream : streams) {
                ConsumerIterator<byte[], byte[]> it = stream.iterator();
                while (it.hasNext())
                    messages.add(new String(it.next().message()));
            }

        }

    }
    public ConsumerResource(String topic) {
        new Thread(() -> new MyConsumer().run(topic)).start();
    }

    @GET
    @Produces("application/json")
    public List<String> getMessages() {
        return messages;
    }

}
