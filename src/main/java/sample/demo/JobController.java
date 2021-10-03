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

    @GetMapping("/jobId")
	public JobEntity getjobApplicationbyId(@RequestParam(value = "id") Long jobId){
        return jobService.getJobInfo(jobId);
	}

    @PostMapping("")
    public Long createjobApplication() {
        return jobService.saveJobInfo();
    }

    @PutMapping("/jobId/{id}")
    public JobEntity updatejobApplication(@PathVariable(value = "id") Long jobId) throws InterruptedException {

        return jobService.updateJobInfo(jobId);
    }
}
