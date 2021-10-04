package sample.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RequestMapping("/jobs")
@RestController
public class JobController {

	@Autowired
    private JobService jobService;

    @GetMapping("")
	public JobEntity getjobApplicationbyId(@RequestParam(value = "id", defaultValue = "1") Long jobId){
        return jobService.getJobInfo(jobId);
	}

    @PostMapping("")
    public Long createjobApplication() {
        return jobService.saveJobInfo();
    }

    @PutMapping("/{id}")
    public JobEntity updatejobApplication(@PathVariable(value = "id") Long jobId) throws InterruptedException {

        return jobService.updateJobInfo(jobId);
    }
}
