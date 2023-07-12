package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SubmissionRepository implements SubmissionInterface{

    public Map<String,SubmissionForm> hashMap=new HashMap<String,SubmissionForm>();



    @Override
    public SubmissionForm postSubmission(SubmissionForm sf) {
        String id= String.valueOf(UUID.randomUUID());
        sf.setId(id);
        hashMap.put(id,sf);
        return hashMap.get(id);
    }

    @Override
    public SubmissionForm getSubmissionFormById(String Id) {
       SubmissionForm record=new SubmissionForm();
       record= (SubmissionForm) hashMap.get(Id);
       return record;
    }

    @Override
    public List<SubmissionForm> getallSubmissions() {
        List<SubmissionForm> submissionForms=new ArrayList<SubmissionForm>();
        hashMap.forEach((key, value) -> {
            submissionForms.add(value);
        });
        return submissionForms;
    }

    @Override
    public SubmissionForm editSubmission(SubmissionForm sf) {
        hashMap.put(sf.Id,sf);
        return hashMap.get(sf.Id);
    }

    @Override
    public List<SubmissionForm> deleteSubmission(String Id) {
        hashMap.remove(Id);
        List<SubmissionForm> submissionForms=new ArrayList<SubmissionForm>();
        hashMap.forEach((key, value) -> {
            submissionForms.add(value);
        });
        return submissionForms;


    }

}