package sample.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class JobService {

    private static final String topicName = "sample-topic";

    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("group.id", "test-group-id");
	props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    @Autowired
    private JobRepository jobRepository;

    @Transactional
    public Long saveJobInfo(){
        JobEntity jobEntity = new JobEntity();

        jobEntity.setStatus("new");
        jobEntity = jobRepository.save(jobEntity);
        publishMessageToKafka(jobEntity);
        return jobEntity.getJobApplicationId();
    }

    public JobEntity getJobInfo(final Long jobId){
        JobEntity jobEntity = jobRepository.findById(jobId).get();
        updateJobFromKafkaMessage();
        return jobEntity;
    }

    public JobEntity updateJobInfo(final Long jobId) throws InterruptedException {
        JobEntity jobEntity = jobRepository.findById(jobId).get();

        jobEntity.setStatus("Done");
        Thread.sleep((long)(Math.random() * (4000)) + 1000);

        final JobEntity updatedjobEntity = jobRepository.save(jobEntity);
        return updatedjobEntity;
    }

    public void updateJobFromKafkaMessage() {
        KafkaConsumer<String, JobEntity> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(topicName));
        ConsumerRecords<String, JobEntity> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, JobEntity> record : records) {
            JobEntity job = record.value();
            job.setStatus("In progress");
            jobRepository.save(job);
        }
        consumer.commitAsync();
    }

    public void publishMessageToKafka(JobEntity jobEntity){
        KafkaProducer<String, JobEntity> producer = new KafkaProducer<String, JobEntity>(props);
        // Publish message to topic
        producer.send(new ProducerRecord<String, JobEntity>(topicName, jobEntity));
        }
    }
}
