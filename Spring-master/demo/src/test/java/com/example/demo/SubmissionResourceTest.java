package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SubmissionResourceTest {

    @Mock
    private SubmissionService submissionService;

    @InjectMocks
    private SubmissionResource submissionResource;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostSubmission() {
        SubmissionForm inputForm = new SubmissionForm();
        inputForm.setName("John Doe");
        inputForm.setVendorName("ABC Corp");
        inputForm.setRate(100);
        inputForm.setLeadName("Jane Smith");
        inputForm.setTechnology("Java");

        when(submissionService.postSubmission(inputForm)).thenAnswer(invocation -> {
            SubmissionForm form = invocation.getArgument(0);
            form.setId("sampleId");
            return form;
        });
        SubmissionForm resultForm = submissionResource.postSubmission(inputForm);
        assertNotNull(resultForm.getId());
        verify(submissionService, times(1)).postSubmission(inputForm);    }

    @Test
    public void testGetSubmissionFormById() {
        String id = "sampleId";
        SubmissionForm expectedForm = new SubmissionForm();
        expectedForm.setId(id);
        expectedForm.setName("John Doe");
        expectedForm.setVendorName("ABC Corp");
        expectedForm.setRate(100);
        expectedForm.setLeadName("Jane Smith");
        expectedForm.setTechnology("Java");

        when(submissionService.getSubmissionFormById(id)).thenReturn(expectedForm);


        SubmissionForm resultForm = submissionResource.getSubmissionFormById(id);

        assertNotNull(resultForm.getId());
        verify(submissionService, times(1)).getSubmissionFormById(id);
    }

//    @Test
//    public void testGetAllSubmissions() {
//        List<SubmissionForm> expectedList = Arrays.asList(
//                new SubmissionForm(/* Provide the expected data */),
//                new SubmissionForm(/* Provide the expected data */)
//        );
//        when(submissionService.getallSubmissions()).thenReturn(expectedList);
//        List<SubmissionForm> resultList = submissionResource.getallSubmissions();
//
//        assertEquals(expectedList, resultList);
//        verify(submissionService, times(1)).getallSubmissions();
//    }

    @Test
    public void testEditSubmission() {
        SubmissionForm inputForm = new SubmissionForm();
        inputForm.setId("sampleId");
        inputForm.setName("John Doe");
        inputForm.setVendorName("ABC Corp");
        inputForm.setRate(100);
        inputForm.setLeadName("Jane Smith");
        inputForm.setTechnology("Java");

        when(submissionService.editSubmission(inputForm)).thenAnswer(invocation -> {
            SubmissionForm form = invocation.getArgument(0);
            return form;
        });

        SubmissionForm resultForm = submissionResource.editSubmission(inputForm);

        assertNotNull(resultForm.getId());
        verify(submissionService, times(1)).editSubmission(inputForm);
    }

    @Test
    public void testDeleteSubmission() {
        // Arrange
        String id = "sampleId";
        List<SubmissionForm> expectedList = Arrays.asList(
                new SubmissionForm("John Doe", "ABC Corp", 100, "Jane Smith", "Java"),
                new SubmissionForm("Jane Doe", "XYZ Corp", 200, "John Smith", "Python")
        );

        when(submissionService.deleteSubmission(id)).thenReturn(expectedList);

        // Act
        List<SubmissionForm> resultList = submissionResource.deleteSubmission(id);

        // Assert
        assertEquals(expectedList, resultList);
        verify(submissionService, times(1)).deleteSubmission(id);
    }
}
