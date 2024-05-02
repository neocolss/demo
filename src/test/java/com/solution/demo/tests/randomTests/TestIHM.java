package com.solution.demo.tests.randomTests;

import com.solution.demo.framework.functional.Transverse;
import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.utils.Constants;
import com.solution.demo.framework.utils.Item;
import com.solution.demo.framework.utils.ItemAction;
import com.solution.demo.framework.utils.TestBase;
import org.junit.jupiter.api.*;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestIHM extends TestBase {
    @DisplayName("Random IHM TESTS=> TEST the NEW ITEM CLASS")
    @Test
    @Tags(value = {@Tag("IHM")})
    public void testItem() {
        Reporter.getInstance().logInfo("==> Start Test TPG 4489 Creer AMC Test Case. ==>");
        Transverse.get("file:///" + new File(Constants.TEST_RESOURCE + "test.html").getAbsolutePath());
        //Test the new Design of Item class
        System.out.println("id");
        Item testId = new Item("id", "idbtn", "test By Id");
        System.out.println("classname");
        Item testClassName = new Item("classname", "class1", "Test classname");
        System.out.println("partiallinktext");
        Item testParialLinkText = new Item("partiallinktext", "yahoo", "test partiallinktext");
        System.out.println("linktext");
        Item testLinkText = new Item("linktext", "google", "test linktext");
        System.out.println("cssselector");
        Item testCssSelector = new Item("cssselector", "#divid>a.class2", "test cssselector");
        System.out.println("name");
        Item testName = new Item("name", "name1", "test name");
        System.out.println("tagname");
        Item testTagName = new Item("tagname", "a", "test tagname");
        System.out.println("xpath 1");
        Item testXpath = new Item("xpath", "//*[@id='texto']", "test xpath");
        System.out.println("//*[@id='divid']");
        Item testXpath2 = new Item("//*[@id='divid']", "test default constructor xpath");


        ItemAction.clickJS(testId);
        ItemAction.validerPopUpAlert();
        ItemAction.wait(2);
        ItemAction.clickJS(testClassName);
        ItemAction.navigate("back");
        ItemAction.wait(2);
        ItemAction.click(testParialLinkText);
        ItemAction.navigate("back");
        ItemAction.wait(2);
        ItemAction.clickJS(testParialLinkText);
        System.out.println("test get Text from testLinkText : " + ItemAction.getText(testLinkText));
        System.out.println("test get Text from testCssSelector : " + ItemAction.getText(testCssSelector));
        ItemAction.click(testName);
        ItemAction.navigate("back");
        ItemAction.wait(2);
        System.out.println("test get Text from testXpath : " + ItemAction.getText(testXpath));
        System.out.println("test get Text from testXpath2 : " + ItemAction.getText(testXpath2));
        //System.out.println(ItemAction.getElements(testTagName));
    }
}
