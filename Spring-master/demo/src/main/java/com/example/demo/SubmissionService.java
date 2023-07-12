package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SubmissionService implements SubmissionInterface{
    @Autowired
    private SubmissionRepository submissionRepository;



    @Override
    public SubmissionForm postSubmission(SubmissionForm sf) {
        return submissionRepository.postSubmission(sf);
    }

    @Override
    public SubmissionForm getSubmissionFormById(String Id) {
        return submissionRepository.getSubmissionFormById(Id);
    }

    @Override
    public List<SubmissionForm> getallSubmissions() {
        return submissionRepository.getallSubmissions();
    }

    @Override
    public SubmissionForm editSubmission(SubmissionForm sf) {
        return submissionRepository.editSubmission(sf);
    }

    @Override
    public List<SubmissionForm> deleteSubmission(String Id) {
        return submissionRepository.deleteSubmission(Id);
    }
}
