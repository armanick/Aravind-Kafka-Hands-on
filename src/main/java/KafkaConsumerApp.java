    import java.time.Duration;
    import java.util.Arrays;
    import java.util.Properties;
    import org.apache.kafka.clients.consumer.ConsumerConfig;
    import org.apache.kafka.clients.consumer.ConsumerRecord;
    import org.apache.kafka.clients.consumer.ConsumerRecords;
    import org.apache.kafka.clients.consumer.KafkaConsumer;

    public class KafkaConsumerApp {
        public KafkaConsumerApp() {
            Properties prop = new Properties();
            prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.1:9092");
            prop.put(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroup3");
            prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            //prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        try {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        consumer.subscribe(Arrays.asList("t1"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Key: " + record.key() + ", Value:" + record.value() + ", Partition:" + record.partition() + ", Offset:" + record.offset());
            }
        }
    }catch(Exception e){
        e.printStackTrace();
    }
        }

        public static void main(String[] args) {
            KafkaConsumerApp kafkaConsumerApp = new KafkaConsumerApp();
        }
    }
