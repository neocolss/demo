package com.solution.demo.framework.utils;

import com.solution.demo.framework.functional.Transverse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TestBase extends TestClassBase {

    static Set<String> tags = new HashSet<>();

    @BeforeEach
    public void getTags(TestInfo testInfo) {
        System.out.println("Tags: " + testInfo.getTags());
        tags = testInfo.getTags();

        List<String> categories = new ArrayList<>();
        String packageName = getClass().getName();
        String testName = getClass().getSimpleName();

        categories.add(packageName + ", " + testName);

        System.out.println("Categories=" + categories);


        if (tags != null && tags.contains("IHM")) {
            super.setUp(testName, categories);
        }

        System.out.println("Test under Execution ==> " + testName + "<===|\n");
    }

    /*
        @BeforeAll
        public void setUp() {

            List<String> categories = new ArrayList<>();
            String packageName = getClass().getName();
            String testName = getClass().getSimpleName();

            categories.add(packageName+", " + testName);

            System.out.println("Categories="+categories);


            if(tags!=null && tags.contains("IHM")){
                super.setUp(testName, categories);
            }

            System.out.println("test name ==> " + testName + "\n");
        }


     */
/*
    //@Override
    @AfterAll
    public void tearDown() {
        if(tags!=null && tags.contains("IHM")){
            super.tearDown();
            Transverse.quit();
        }
    }
 */
    @AfterEach
    public void tearDown() {
        if (tags != null && tags.contains("IHM")) {
            super.tearDown();
            Transverse.quit();
        }
    }
}

