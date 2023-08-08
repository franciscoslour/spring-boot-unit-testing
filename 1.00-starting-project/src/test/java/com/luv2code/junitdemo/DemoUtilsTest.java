package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(MethodOrderer.Random.class)
class DemoUtilsTest {

    private DemoUtils demoUtils;

    @BeforeEach
    void setupBeforeEach(){
        demoUtils = new DemoUtils();
    }

    @Test
    void test_Equals_And_Not_Equals(){

        assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        assertNotEquals(6, demoUtils.add(2, 6), "2+4 must not be 6");

    }

    @Test
    void testNullAndNotNull(){

        DemoUtils demoUtils = new DemoUtils();

        String str1 = null;
        String str2 = "luv2code";

        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");

    }

    @Test
    void testSameAndNotSame(){

        String str = "luv2code";

        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Objects should refer to same object");
        assertNotSame(str, demoUtils.getAcademy(), "Objects should not refer to same object");

    }

    @Test
    void testTrueFalse(){
        assertTrue(demoUtils.isGreater(6, 4), "Object should be greater than 3");
        assertFalse(demoUtils.isGreater(4, 6), "Object should be greater than 6");
    }

    @Test
    void testArrayEquals() {
        String[] stringArray = {"A", "B", "C"};

        assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");
    }


    @Test
    @DisplayName("Iterable equals")
    void testIterableEquals(){
        List<String> theList = Arrays.asList("luv", "2", "code");

        assertIterableEquals(theList, demoUtils.getAcademyInList(), "Expected list shoudl be same");
    }

    @Test
    @DisplayName("Line Match")
    void testLinesMatch(){
        List<String> theList = Arrays.asList("luv", "2", "code");

        assertLinesMatch(theList, demoUtils.getAcademyInList(), "Lines should match");
    }

    @Test
    @DisplayName("Throws and Does not Throw")
    void testThrowsAndDoesNotThrow(){

        assertThrows(Exception.class, ()-> {demoUtils.throwException(-7);}, "Should throw exception");
        assertDoesNotThrow(()-> {demoUtils.throwException(9);}, "Should not throw exception");

    }


   @Test
   @DisplayName("Multiple numbers")
   public void testMultiple(){

        int result = demoUtils.multiply(2, 3);
        assertEquals(6, result, "2 * 3 should be 6");
    }

    @Test
    @DisplayName("Primitive time")
    public void testPrimitiveTime(){

        assertTimeoutPreemptively(Duration.ofSeconds(3), ()->{demoUtils.checkTimeout();}, "Should execute in 3 seconds");

    }


}