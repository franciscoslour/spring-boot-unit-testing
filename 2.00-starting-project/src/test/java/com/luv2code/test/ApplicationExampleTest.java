package com.luv2code.test;


import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
class ApplicationExampleTest {

    private static Integer count = 0;

    @Value("${info.app.name}")
    private String appInfo;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.version}")
    private String appVersion;
    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;


    @Autowired
    ApplicationContext applicationContext;

    @BeforeEach
    public void beforeEach(){

        count = count +1;
        System.out.println(
                "Testing: "+appInfo
                + " which is "+appDescription
                +" Version: "+appVersion
                +". Execution of test method "+count
        );

        student.setFirstname("Francisco");
        student.setLastname("Lourenço");
        student.setEmailAddress("franciscolour2011@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 76.50, 91.75)));
        student.setStudentGrades(studentGrades);
    }

    @Test
    void basicTest(){

    }

    @Test
    @DisplayName("Add grade results for student grades")
    public void addGradeResultsForStudentGrades(){
       assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(
               student.getStudentGrades().getMathGradeResults()
       ));
    }

    @Test
    @DisplayName("Add grade results for student grades not equal")
    public void addGradeResultsForStudentGradesAssertNotEquals(){
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }


    @Test
    @DisplayName("Is grade greater")
    public void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90,75), "failure - should be true");
    }
    @Test
    @DisplayName("Is grade greater false")
    public void isGradeGreaterStudentGradesAssertFalse(){
        assertFalse(studentGrades.isGradeGreater(89,92), "failure - should be false");
    }

    @Test
    @DisplayName("Check null for student grades")
    public void checkNullForStudentGrades(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades()), "Should not be null");
    }

    @Test
    public void checkTheApllicationContext(){

        CollegeStudent studentTwo = this.applicationContext.getBean("collegeStudent", CollegeStudent.class);
        studentTwo.setFirstname("Francisco");
        studentTwo.setLastname("Lourenço");
        studentTwo.setEmailAddress("franciscolour2011@gmail.com");

        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNotNull(studentTwo.getEmailAddress());
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));

    }

    @Test
    @DisplayName("Verify studentes are prototypes")
    public void verifyStudentArePrototype(){

        CollegeStudent studentTwo = this.applicationContext.getBean("collegeStudent", CollegeStudent.class);
        assertNotSame(student, studentTwo);

    }

    @Test
    @DisplayName("Find Grade Point Average")
    public void findGradePointAverage(){

        assertAll("Testing All assertEquals",
                () ->assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults())),
                ()-> assertEquals(88.31, studentGrades.findGradePointAverage(student.getStudentGrades().getMathGradeResults()))
        );
    }

}