package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes =  MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    //@Mock
    @MockBean
    private ApplicationDao applicationDao;

    //@InjectMocks
    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach(){
        studentOne.setFirstname("Francisco");
        studentOne.setLastname("Lourenço");
        studentOne.setEmailAddress("franciscolour2011@gmail.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @Test
    @DisplayName("When & Verify")
    public void assertEqualsTestAddGrades(){
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults())).thenReturn(100.00);

        List<Double> list = studentOne.getStudentGrades().getMathGradeResults();
        assertEquals(100, applicationService.addGradeResultsForSingleClass(list));

        verify(applicationDao).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
        verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());

    }


    @Test
    @DisplayName("Find Gpa")
    public void assertEqualsTestFindGpa(){

        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults())).thenReturn(88.31);

        assertEquals(88.31, applicationService.findGradePointAverage(studentOne.getStudentGrades().getMathGradeResults()));


    }


    @Test
    @DisplayName("Not Null")
    public void testAssertNotNull(){
        when(applicationDao.checkNull(studentGrades.getMathGradeResults())).thenReturn(true);

        assertNotNull(applicationService.checkNull(studentOne.getStudentGrades().getMathGradeResults()), "Object should not be null ");
    }

    @Test
    @DisplayName("Throw runtime error")
    public void throwRuntimeError(){
        CollegeStudent student = (CollegeStudent) applicationContext.getBean("collegeStudent");

        doThrow(new RuntimeException()).when(applicationDao).checkNull(student);

        assertThrows(RuntimeException.class, ()->{
            applicationService.checkNull(student);
        });

        verify(applicationDao, times(1)).checkNull(student);

    }


    @Test
    @DisplayName("Multiple Stubbing")
    public void stubbingConsecutiveCalls(){

        CollegeStudent student = (CollegeStudent) applicationContext.getBean("collegeStudent");

        when(applicationDao.checkNull(student))
                .thenThrow(new RuntimeException())
                .thenReturn("Do not throw exception second time");


        assertThrows(RuntimeException.class, ()->{
            applicationService.checkNull(student);
        });

        assertEquals("Do not throw exception second time",
                applicationService.checkNull(student)
        );

        verify(applicationDao, times(2)).checkNull(student);
    }

}
