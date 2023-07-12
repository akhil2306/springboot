package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SubmissionRepositoryTest {

    private SubmissionRepository submissionRepository;
    private Map<String, SubmissionForm> hashMap;

    @BeforeEach
    public void setUp() {
        submissionRepository = new SubmissionRepository();
        hashMap = submissionRepository.hashMap;
    }

    @Test
    public void testPostSubmission() {
        SubmissionForm sf = new SubmissionForm();
        sf.setName("John Doe");
        sf.setVendorName("ABC Corp");
        sf.setRate(100);
        sf.setLeadName("Jane Smith");
        sf.setTechnology("Java");

        SubmissionForm result = submissionRepository.postSubmission(sf);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(sf, hashMap.get(result.getId()));
    }

    @Test
    public void testGetSubmissionFormById() {

        SubmissionForm expectedForm = new SubmissionForm();
        expectedForm.setId("sampleId");
        expectedForm.setName("John Doe");
        expectedForm.setVendorName("ABC Corp");
        expectedForm.setRate(100);
        expectedForm.setLeadName("Jane Smith");
        expectedForm.setTechnology("Java");


        hashMap.put(expectedForm.getId(), expectedForm);


        SubmissionForm result = submissionRepository.getSubmissionFormById(expectedForm.getId());


        Assertions.assertEquals(expectedForm, result);
    }

    @Test
    public void testGetAllSubmissions() {

        SubmissionForm expectedForm1 = new SubmissionForm();
        expectedForm1.setId("sampleId");
        expectedForm1.setName("John Doe");
        expectedForm1.setVendorName("ABC Corp");
        expectedForm1.setRate(100);
        expectedForm1.setLeadName("Jane Smith");
        expectedForm1.setTechnology("Java");
        SubmissionForm form2 = new SubmissionForm();
        form2.setId("sample");
        form2.setName("John Doe");
        form2.setVendorName("ABC Corp");
        form2.setRate(100);
        form2.setLeadName("Jane Smith");
        form2.setTechnology("Java");


        hashMap.put(expectedForm1.getId(), expectedForm1);
        hashMap.put(form2.getId(), form2);


        List<SubmissionForm> result = submissionRepository.getallSubmissions();


        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(expectedForm1));
        Assertions.assertTrue(result.contains(form2));
    }

    @Test
    public void testEditSubmission() {

        SubmissionForm existingForm = new SubmissionForm(/* Set the values for the existing form */);


        hashMap.put(existingForm.getId(), existingForm);


        existingForm.setName("New Name");
        existingForm.setRate(200);


        SubmissionForm result = submissionRepository.editSubmission(existingForm);

        Assertions.assertEquals(existingForm, result);
        Assertions.assertEquals(existingForm, hashMap.get(existingForm.getId()));
    }

    @Test
    public void testDeleteSubmission() {
        SubmissionForm expectedForm1 = new SubmissionForm();
        expectedForm1.setId("sampleId");
        expectedForm1.setName("John Doe");
        expectedForm1.setVendorName("ABC Corp");
        expectedForm1.setRate(100);
        expectedForm1.setLeadName("Jane Smith");
        expectedForm1.setTechnology("Java");
        SubmissionForm form2 = new SubmissionForm();
        form2.setId("sample");
        form2.setName("John Doe");
        form2.setVendorName("ABC Corp");
        form2.setRate(100);
        form2.setLeadName("Jane Smith");
        form2.setTechnology("Java");

     hashMap.put(expectedForm1.getId(), expectedForm1);
        hashMap.put(form2.getId(), form2);

        List<SubmissionForm> result = submissionRepository.deleteSubmission(expectedForm1.getId());

        Assertions.assertEquals(1, result.size());
        Assertions.assertFalse(result.contains(expectedForm1));
        Assertions.assertTrue(result.contains(form2));
        Assertions.assertNull(hashMap.get(expectedForm1.getId()));
    }

}
