import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducerApp {
    public KafkaProducerApp(){
        try {
            Properties prop = new Properties();
            prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.1:9092");
            prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            KafkaProducer producer = new KafkaProducer(prop);
            for(int i = 1; i<1000; i++) {
                String record = "Java Message "+ i;
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("t1", "demo1", record);
                //System.out.println("Sending " + record);
                RecordMetadata metadata = (RecordMetadata) producer.send(producerRecord).get();
                System.out.printf("sent record(key=%s value=%s) meta(partition=%d, offset=%d)\n",
                        producerRecord.key(), producerRecord.value(), metadata.partition(), metadata.offset());
                //producer.send(producerRecord);
                Thread.sleep(1000);
            }
            System.out.println("Closing producer");
            producer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        KafkaProducerApp app = new KafkaProducerApp();
    }
}
