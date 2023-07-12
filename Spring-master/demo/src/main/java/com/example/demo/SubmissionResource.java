package com.example.demo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@RestController
@RequestMapping("/submission")

public class SubmissionResource implements SubmissionInterface {
    @Autowired
    private SubmissionService submissionService;


    @PostMapping("/postSubmission")
    @Override
    public SubmissionForm postSubmission(@RequestBody SubmissionForm sf) {
        return submissionService.postSubmission(sf);
    }

    @Override
    @GetMapping("/getSubmission/{Id}")
    public SubmissionForm getSubmissionFormById(@PathVariable String Id) {
        SubmissionForm record=new SubmissionForm();
        record=submissionService.getSubmissionFormById(Id);
        return record;
    }

    @Override
    @GetMapping("/getallSubmissions")
    public List<SubmissionForm> getallSubmissions() {
        return submissionService.getallSubmissions();
    }

    @Override
    @PutMapping("/edit")
    public SubmissionForm editSubmission(@RequestBody SubmissionForm sf) {
        return submissionService.editSubmission(sf);
    }

    @Override
    @DeleteMapping("/delete/{Id}")
    public List<SubmissionForm> deleteSubmission(@PathVariable String Id) {
        return submissionService.deleteSubmission(Id);
    }
}
