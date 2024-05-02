package com.solution.demo.framework.utils;

import com.solution.demo.framework.reporting.Reporter;
import com.solution.demo.framework.webdriver.WebDriverFactory;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ItemAction {

    private static WebDriver driver = WebDriverFactory.getInstance();

    /**
     * The scroll() method scrolls the document by the specified number of pixels.
     *
     * @param x How many pixels to scroll by, along the x-axis (horizontal).
     *          Positive values will scroll to the right, while negative values will
     *          scroll to the left
     * @param y How many pixels to scroll by, along the y-axis (vertical). Positive
     *          values will scroll down, while negative values scroll up
     */
    public static void scroll(int x, int y) {
        WebDriver driver = WebDriverFactory.getInstance();
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(" + x + "," + y + ")", "");

    }

    /**
     * Scroll to visual Element
     *
     * @param repoItem
     */
    public static void scrollToElement(Item repoItem) {
        WebDriver driver = WebDriverFactory.getInstance();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(repoItem.getXpath())));
    }

    /**
     * Scroll to visual Element by xpath
     *
     * @param xpath
     */
    public static void scrollToElement(String xpath) {
        WebDriver driver = WebDriverFactory.getInstance();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpath)));
    }

    /**
     * Scroll to the end of the web page
     */
    public static void scrollToBottomPage() {
        WebDriver driver = WebDriverFactory.getInstance();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Scroll to the end of the web page
     */
    public static void scrollToTopPage() {
        WebDriver driver = WebDriverFactory.getInstance();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    /**
     * The getText() method get text value of the item passed in parameter
     *
     * @param repoItem Item instance referring to DOM element
     * @return DOM element text value
     */
    public static String getText(Item repoItem) {
        return getText(repoItem, true);
    }

    /**
     * The getText() method get text value of the item passed in parameter
     *
     * @param repoItem Item instance referring to DOM element
     * @param log      true to log result in report
     * @return DOM element text value
     */
    public static String getText(Item repoItem, boolean log) {
        WebElement item;
        String value = null;
        //item = getElement(repoItem.getXpath());
        item = getElement(repoItem);
        if (item != null)
            value = getEncodedString(item.getText());

        if (log) {
            if (value == null)
                Reporter.getInstance().logError("-> GET VALUE",
                        "[ " + repoItem.getLabel() + " ] Erreur récuperation de la valeur.");
            else
                Reporter.getInstance().logSuccess("=> GET VALUE",
                        "[ " + repoItem.getLabel() + " ] valeur récuperée : " + value);
        }
        return value;
    }

    public static String getText(String xpath, boolean log) {
        WebElement item;
        String value = null;
        //item = getElement(repoItem.getXpath());
        item = getElement(xpath);
        if (item != null)
            value = getEncodedString(item.getText());

        if (log) {
            if (value == null)
                Reporter.getInstance().logError("-> GET VALUE",
                        "[ " + xpath + " ] Erreur récuperation de la valeur.");
            else
                Reporter.getInstance().logSuccess("=> GET VALUE",
                        "[ " + xpath + " ] valeur récuperée : " + value);
        }
        return value;
    }

    public static String getText(String xpath) {
        return getText(xpath, true);
    }

    /**
     * The checkText() method check DOM element text value
     *
     * @param repoItem      Item instance referring to DOM element
     * @param expectedValue Expected value
     * @param isContains    is DOM element text value contains expected value
     * @return true if expected value match current value
     */
    public static boolean checkText(Item repoItem, String expectedValue, boolean isContains) {
        String currentValue = getText(repoItem);
        return checkText(currentValue, expectedValue, isContains);
    }

    /**
     * The checkText() method compare current value and expected value
     *
     * @param currentValue  Current value in SUT
     * @param expectedValue Expected value in SUT
     * @param isContains    Is current value contains expected value
     * @return true if expected value match current value
     */
    public static boolean checkText(String currentValue, String expectedValue, boolean isContains) {
        boolean result = (isContains && currentValue.toLowerCase().contains(expectedValue.toLowerCase().trim()))
                || (!isContains && currentValue.toLowerCase().equals(expectedValue.toLowerCase().trim()));
        if (result)
            Reporter.getInstance().logSuccess("CHECK VALUE", " Le texte attendu est trouvé : " + expectedValue);
        else
            Reporter.getInstance().logFail("CHECK VALUE", "Le texte attendu n'est pas trouvé : [ attendu : "
                    + expectedValue + " ] [ obtenu : " + currentValue + " ]");
        return result;
    }

    /**
     * The compareText() method compare current value and expected value
     *
     * @param currentValue   Current value in SUT
     * @param expectedValue  Expected value in SUT
     * @param expectedResult
     * @return true if expected value match current value
     */
    public static void compareText(String currentValue, String expectedValue, boolean expectedResult) {
        try {
            currentValue = new String(currentValue.getBytes(), "UTF-8");
            // expectedValue = new String(expectedValue.getBytes(), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // expectedValue = removeAccents(expectedValue);

        boolean result = (currentValue.toLowerCase().contains(expectedValue.toLowerCase())
                || currentValue.toLowerCase().equals(expectedValue.toLowerCase()))
                || (currentValue.toUpperCase().contains(expectedValue.toUpperCase())
                || currentValue.toUpperCase().equals(expectedValue.toUpperCase()));

        Reporter.getInstance().logSuccess(
                "expectedValue :" + expectedValue.toUpperCase() + " currentValue :" + currentValue.toUpperCase());
        if (result == expectedResult)
            Reporter.getInstance().logSuccess("COMPARE TEXT", "Texte attendu trouvé : " + expectedValue);
        else
            Reporter.getInstance().logFail("COMPARE TEXT", "Text attendu non trouvé.\n" + "[Attendu] : " + expectedValue
                    + "\n" + "[Obtenu] : " + currentValue);
    }

    public static String removeAccents(String value) {
        return value == null ? null
                : Normalizer.normalize(value, Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

    public static boolean checkText(String currentValue, String value) {
        System.out.println("currentValue=" + currentValue + ", expected value=" + value);
        return checkText(currentValue, value, true);
    }

    /**
     * The checkText() method check DOM element text value
     *
     * @param repoItem Item instance referring to DOM element
     * @param value    Expected value
     * @return true if expected value match current value
     */
    public static boolean checkText(Item repoItem, String value) {
        return checkText(repoItem, value, true);
    }

    /**
     * The getAttribute() method return the value of the attribute in parameter
     *
     * @param repoItem  Item instance referring to DOM element
     * @param attribute attribute name
     * @param log       true to log result in report
     * @return value of attribute or null if not found
     */
    public static String getAttribute(Item repoItem, String attribute, boolean log) {
        //WebElement item = getElement(repoItem.getXpath());
        WebElement item = getElement(repoItem);
        String value = getEncodedString(item.getAttribute(attribute).trim());

        if (value == null)
            Reporter.getInstance().logError("GET ATTRIBUTE",
                    "[ " + repoItem.getLabel() + " ] Erreur récupération attribut [ " + attribute + " ]");
        else if (log)
            Reporter.getInstance().logSuccess("GET ATTRIBUTE",
                    "[ " + repoItem.getLabel() + " ] Valeur attribut [ " + attribute + " ] récupérée : " + value);

        return value;
    }

    /**
     * The getValue() method get value of the attribute value
     *
     * @param repoItem Item instance referring to DOM element
     * @return DOM element value attribute
     */
    public static String getValue(Item repoItem) {
        return getAttribute(repoItem, "value", true);
    }

    /**
     * The getValue() method get value of the attribute value
     *
     * @param repoItem Item instance referring to DOM element
     * @param log      true to log result in report
     * @return DOM element value attribute
     */
    public static String getValue(Item repoItem, boolean log) {
        return getAttribute(repoItem, "value", log);
    }

    /**
     * The setValue() method set value of Item passed in parameter
     *
     * @param repoItem Item instance referring to DOM element
     * @param value    Value to set
     */
    public static void setValue(Item repoItem, String value) {
        Reporter.getInstance().newModule();
        //WebElement item = getElement(repoItem.getXpath());
        WebElement item = getElementJS(repoItem);

        if (item == null) {
            Reporter.getInstance().logError("SET VALUE", "[ " + repoItem.getLabel() + " ] Erreur saisie valeur : " + value);
        } else {
            if (item.getText() != null) {
                item.clear();
                item.sendKeys(value);
                Reporter.getInstance().logSuccess("SET VALUE", "[ " + repoItem.getLabel() + " ] Valeur saisie : " + value);
            } else if (item.getText() == null) {
                item.sendKeys(value);
                Reporter.getInstance().logSuccess("SET VALUE", "[ " + repoItem.getLabel() + " ] Valeur saisie : " + value);
            }
        }
        Reporter.getInstance().closeModule();
    }

    /**
     * The clearValue() method reset value of Item passed in parameter
     *
     * @param repoItem Item instance referring to DOM element
     */
    public static void clearValue(Item repoItem) {
        WebElement item = getElement(repoItem.getXpath());
        if (item != null) {
            item.click();
            item.clear();
            item.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            Reporter.getInstance().logSuccess("CLEAR VALUE", "[ " + repoItem.getLabel() + " ] Champs vidé");
        } else
            Reporter.getInstance().logError("CLEAR VALUE", "[ " + repoItem.getLabel() + " ] Champ non vidé");
    }

    /**
     * The click() method click on Item passed in parameter
     *
     * @param repoItem Item instance referring to DOM element
     */
    public static void click(Item repoItem) {
        click(repoItem, false);
    }

    public static void click(String xpath) {
        click(new Item(xpath, "my element by xpath:" + xpath), false);
    }

    public static void clickJS(Item repoItem) {
        clickJS(repoItem, false);
    }

    public static void clickJS(String xpath) {
        clickJS(new Item(xpath, "my element by xpath:" + xpath), false);
    }

    /**
     * The click() method click on Item passed in parameter
     *
     * @param repoItem Item instance referring to DOM element
     * @param isCritic if true test will be aborted on error
     */
    public static void click(Item repoItem, boolean isCritic) {
        if (clickNoLog(repoItem)) {
            Reporter.getInstance().logSuccess("CLICK", "[ " + repoItem.getLabel() + " ] => OK ");
        } else if (isCritic) {
            Reporter.getInstance().logFail("CLICK", "[ " + repoItem.getLabel() + " ] => Erreur lors du click ");
        } else
            Reporter.getInstance().logError("CLICK", "[ " + repoItem.getLabel() + " ] => Erreur lors du click ");
    }

    public static void clickJS(Item repoItem, boolean isCritic) {
        if (clickNoLogJS(repoItem)) {
            Reporter.getInstance().logSuccess("CLICK", "[ " + repoItem.getLabel() + " ] OK ");
        } else if (isCritic) {
            Reporter.getInstance().logFail("CLICK", "[ " + repoItem.getLabel() + " ] Erreur lors du click ");
        } else
            Reporter.getInstance().logError("CLICK", "[ " + repoItem.getLabel() + " ] Erreur lors du click ");
    }

    public static boolean clickNoLog(Item repoItem) {
        //WebElement item = getElement(repoItem.getXpath());
        WebElement item = getElement(repoItem);
        try {
            if (item != null) {
                //waitForAngularLoad();
                item.click();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean clickNoLogJS(Item repoItem) {
        WebElement item = getElementJS(repoItem);
        try {
            if (item != null) {
                //waitForAngularLoad();
                item.click();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void click(WebElement webElement) {
        //driver.
        webElement.click();
    }

    /**
     * The performclick() method perform click on Item passed in parameter with
     * relative offset
     *
     * @param x        Relative offset along the x-axis (horizontal).
     * @param y        Relative offset along the y-axis (horizontal).
     * @param repoItem Item instance referring to DOM element
     */
    public static void Performeclick(int x, int y, Item repoItem) {

        WebElement element = ItemAction.getElement(repoItem.getXpath());

        // move to element and clic
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(WebDriverFactory.getInstance());
        builder.moveToElement(element).build().perform();

        click(repoItem);
        builder.moveByOffset(x, y).click().build().perform();

    }

    /**
     * The performDoubleclick() method perform double click on Item passed in
     * parameter
     *
     * @param repoItem Item instance referring to DOM element
     */
    public static void PerformeDoubleclick(Item repoItem) {

        WebElement element = ItemAction.getElement(repoItem.getXpath());

        // move to element and clic
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(WebDriverFactory.getInstance());
        builder.moveToElement(element).doubleClick().build().perform();

    }

    public static boolean isElementAbsent(int timeOut, Item repoItem) {

        boolean result = waitElement(repoItem, timeOut, States.invisible);
        if (result)
            Reporter.getInstance().logSuccess("INEXISTANCE", "Element Absent");
        else
            Reporter.getInstance().logError("EXISTANCE", "Element présent");

        return true;
    }

    /**
     * @param repoItem Item instance referring to DOM element
     * @return true if element is present
     */
    public static Boolean isElementPresent(Item repoItem) {
        WebElement item = getElement(repoItem.getXpath());
        // waitElement(repoItem, timeOut);
        waitForAngularLoad();
        if (item != null && item.isDisplayed()) {
            Reporter.getInstance().logSuccess("EXIST", "[ " + repoItem.getLabel() + " ] présent");
            return true;
        }
        return false;
    }

    /**
     * @param repoItem Item instance referring to DOM element
     * @return true if element is disabled
     */
    public static Boolean isElementDisabled(Item repoItem) {
        boolean isDisabled = false;
        WebElement item = getElement(repoItem.getXpath());
        if (item != null)
            isDisabled = !item.isEnabled();

        if (isDisabled)
            Reporter.getInstance().logSuccess("DISABLED", "[ " + repoItem.getLabel() + " ] Désactivé ");
        else
            Reporter.getInstance().logError("DISABLED", "[ " + repoItem.getLabel() + " ] Non  désactivé ");

        return isDisabled;
    }

    /**
     * The waitElement() method wait for specified state of DOM element
     *
     * @param repoItem Item instance referring to DOM element
     * @param timeout  in seconds
     * @param state    expected state of item (Visible, invisible, clickable, ...)
     * @return true if element state is verified
     * @see States
     */
    public static boolean waitElement(Item repoItem, int timeout, States state) {
        return waitElement(repoItem, timeout, state, "");
    }


    /**
     * @param repoItem Item instance referring to DOM element
     * @param state    item expected state
     * @param text     text to search, used in State.text_visible
     * @return true if text is found
     * @see States
     */
    public static boolean waitElement(Item repoItem, int timeout, States state, String text) {
        boolean result = false;
        WebDriver driver = WebDriverFactory.getInstance();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.valueOf(timeout)));
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.valueOf(timeout)));
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        try {
            switch (state) {
                case clickable:
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(repoItem.getXpath())));
                    Reporter.getInstance().logSuccess("ELEMENT CLICKABLE");
                    break;

                case visible:
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(repoItem.getXpath())));
                    getElement(repoItem.getXpath()).isDisplayed();
                    Reporter.getInstance().logSuccess("VISIBLE");
                    break;

                case text_visible:
                    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(repoItem.getXpath()), text));
                    Reporter.getInstance().logSuccess("TEXT_VISIBLE");
                    break;

                case displayed:
                    getElement(repoItem.getXpath()).isDisplayed();
                    Reporter.getInstance().logSuccess("DISPLAYED");

                    break;

                case invisible:
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(repoItem.getXpath())));
                    Reporter.getInstance().logSuccess("INVISIBLE");
                    break;

                case invisibleOfList:
                    List<WebElement> elements = getElements(repoItem.getXpath());
                    wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
                    break;
                default:
                    break;
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            WebDriverFactory.setDefaultTimeout();
        }

        return result;
    }


    public static void waitElement(Item repoItem, int timeOut) {
        waitElement(repoItem, timeOut, States.visible);
    }

    public static void waitUntilAngularReady() {

        WebDriver driver = WebDriverFactory.getInstance();

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.valueOf(Constants.DEFAULT_TIMEOUT)));
        WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIMEOUT);

        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        // Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor jsExec = (JavascriptExecutor) driver;
                String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
                try {
                    return (Boolean) jsExec.executeScript(angularReadyScript.toString());
                } catch (Exception e) {
                    System.out.println("ERROR WAIT FOR ANGULAR ");
                    return true;

                }
            }
        };

        // Get Angular is Ready
        boolean angularReady = Boolean.valueOf(angularReadyScript);

        // Wait ANGULAR until it is Ready!
        if (!angularReady) {
            // Wait for Angular to load
            try {
                wait.until(angularLoad);
            } catch (Exception e) {
                e.printStackTrace();
                Reporter.getInstance().logFail("Erreur chargement de la page ");
            }
        }
    }

    // Wait Until JS Ready
    public static void waitUntilJSReady() {
        WebDriver driver = WebDriverFactory.getInstance();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.valueOf(Constants.DEFAULT_TIMEOUT)));
        WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIMEOUT);
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;

        // Wait for Javascript to load

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor jsExec = (JavascriptExecutor) driver;
                String jsReadyScript = "return document.readyState";
                try {
                    return (Boolean) jsExec.executeScript(jsReadyScript.toString());
                } catch (Exception e) {
                    return true;
                }
            }
        };
        boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

        // Wait Javascript until it is Ready!
        if (!jsReady) {
            try {
                wait.until(jsLoad);
            } catch (Exception e) {
                e.printStackTrace();
                Reporter.getInstance().logFail("Erreur chargement de la page ");
            }
        }
    }

    // Wait Until Angular and JS Ready
    public static void waitForAngularLoad() {

        if (!WebDriverFactory.isAngularDefined) {
            waitUntilJSReady();
            return;
        }

        WebDriver driver = WebDriverFactory.getInstance();
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;

        Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
        if (!angularUnDefined) {
            Boolean angularInjectorUnDefined = (Boolean) jsExec
                    .executeScript("return angular.element(document).injector() === undefined");
            if (!angularInjectorUnDefined) {

                // Wait Angular Load
                waitUntilAngularReady();

                // Wait JS Load
                waitUntilJSReady();

            } else {
                System.out.println("Angular injector is not defined on this site!");
                WebDriverFactory.isAngularDefined = false;
            }
        } else {
            System.out.println("Angular is not defined on this site!");
            WebDriverFactory.isAngularDefined = false;
        }
    }

    /***
     *
     * @param timeOut in seconds
     */
    public static void wait(int timeOut) {
        try {
            Thread.sleep(timeOut * 1000);
            Reporter.getInstance().logInfo("Waiting : " + timeOut + " seconds.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void checkUrl(String title) {

        WebDriver driver = WebDriverFactory.getInstance();
        boolean result = false;
        wait(4);
        result = driver.getCurrentUrl().equals(title);
        if (result)
            Reporter.getInstance().logSuccess(" On est sur la bonne page ==>   " + title);
        else
            Reporter.getInstance().logFail(" On n'est pas sur la bonne page ==>  " + title);
    }

    public static WebElement getElement(String itemXpath) {
        WebDriver driver = WebDriverFactory.getInstance();
        WebElement item = null;
        try {
            //waitForAngularLoad();
            item = driver.findElement(By.xpath(itemXpath));
        } catch (Exception e) {
            Reporter.getInstance().logFail("L'élement qui se base sur Xpath :  " + itemXpath + " n'existe pas ");
            e.printStackTrace();
        }

        return item;
    }

    public static WebElement getElement(Item item) {
        WebDriver driver = WebDriverFactory.getInstance();
        WebElement item_ = null;
        try {
            //waitForAngularLoad();
            //item_ = driver.findElement(By.xpath(item.getXpath()));
            item_ = driver.findElement(item.getBy());
            Reporter.getInstance().logSuccess("L'élement avec label :  " + item.getLabel() + " est trouvé.");
        } catch (Exception e) {
            Reporter.getInstance().logFail("L'élement avec label :  " + item.getLabel() + ", et xpath : " + item.getXpath() + " n'existe pas.");
            e.printStackTrace();
        }
        return item_;
    }

    public static WebElement getElementJS(Item item) {
        WebDriver driver = WebDriverFactory.getInstance();
        WebElement itemJS = null;
        try {
            //String javascript = "document.evaluate('"+item.getXpath()+"',document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;";
            //String javascript = "document.evaluate(arguments[0],document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;";
            String javascript = "var xpath = arguments[0];\n" +
                    "var result = document.evaluate(xpath, document, null,XPathResult.FIRST_ORDERED_NODE_TYPE, null);\n" +
                    "var element = result.singleNodeValue;\n" +
                    "return element;";
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            itemJS = (WebElement) jsExecutor.executeScript(javascript, item.getXpath());
            //itemJS = driver.findElement(By.xpath(item.getXpath()));
        } catch (Exception e) {
            Reporter.getInstance().logFail("L'élement qui se base sur Xpath :  " + item.getXpath() + ", et label : " + item.getLabel() + " n'existe pas ");
            e.printStackTrace();
        }

        return itemJS;
    }

    public static WebElement getElementJS(String xpath) {
        WebDriver driver = WebDriverFactory.getInstance();
        WebElement itemJS = null;
        try {
            //String javascript = "document.evaluate('"+item.getXpath()+"',document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;";
            //String javascript = "document.evaluate(arguments[0],document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;";
            String javascript = "var xpath = arguments[0];\n" +
                    "var result = document.evaluate(xpath, document, null,XPathResult.FIRST_ORDERED_NODE_TYPE, null);\n" +
                    "var element = result.singleNodeValue;\n" +
                    "return element;";
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            itemJS = (WebElement) jsExecutor.executeScript(javascript, xpath);
        } catch (Exception e) {
            Reporter.getInstance().logFail("L'élement qui se base sur Xpath :  " + xpath + " n'existe pas ");
            e.printStackTrace();
        }

        return itemJS;
    }


    public static List<WebElement> getElements(String itemXpath) {
        WebDriver driver = WebDriverFactory.getInstance();
        List<WebElement> item = null;
        try {
            waitForAngularLoad();
            item = driver.findElements(By.xpath(itemXpath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public static List<WebElement> getElements(Item item) {
        WebDriver driver = WebDriverFactory.getInstance();
        List<WebElement> items = null;
        try {
            waitForAngularLoad();
            items = driver.findElements(item.getBy());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void hover(String itemXpath1, Item itemXpath2) {

        WebDriver driver = WebDriverFactory.getInstance();
        Actions action = new Actions(driver);
        WebElement we = ItemAction.getElement(itemXpath1);
        action.moveToElement(we).build().perform();
        ItemAction.wait(2);
        // waitElement(itemXpath2, Constants.DEFAULT_TIMEOUT, States.clickable);
    }

    public static String getEncodedString(String string) {
        String result = null;

        try {
            result = new String(string.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    public static void robot_SendKey(String _value) {

        char[] characters = (_value.toCharArray());

        for (int i = 0; i < characters.length; i++) {

            int codeAscii = (characters[i]);

            if (Character.isDigit(characters[i])) {

                try {
                    Robot r = new Robot();
                    r.keyPress(KeyEvent.VK_SHIFT);
                    r.keyPress(codeAscii);
                    r.keyRelease(codeAscii);
                    r.keyRelease(KeyEvent.VK_SHIFT);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    Robot r = new Robot();
                    r.keyPress(codeAscii);
                    r.keyRelease(codeAscii);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(Long.valueOf(Constants.DEFAULT_TIMEOUT)))
                .pollingEvery(Duration.ofSeconds(Long.valueOf(5L))).ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return foo;
    }

    public static void goToURL(String url) {
        try {
            Reporter.getInstance().logInfo("Ouverture de la page : " + url);
            driver.get(url);
        } catch (Exception e) {
            Reporter.getInstance().logFail("Un soucis dans l'ouverture de la page : " + url + ", error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /***
     *
     * @return the number of iframes
     */
    public static int getIframes() {
        int size = driver.findElements(By.tagName("iframe")).size();
        return size;
    }

    /***
     * Switch to Iframe by Name or ID
     * @param NameOrID
     */
    public static void switchToFrameByName(String NameOrID) {
        Reporter.getInstance().newModule();
        driver.switchTo().frame(NameOrID);
        Reporter.getInstance().closeModule();
    }

    /***
     * Switch to Iframe by index
     * @param index
     */
    public static void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    public static void switchToParentFrame() {
        Reporter.getInstance().newModule();
        driver.switchTo().defaultContent();
        Reporter.getInstance().closeModule();
    }

    public static void validerPopUpAlert() {
        Reporter.getInstance().newModule();
        //boolean reportAlertPresent = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            //reportAlertPresent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reporter.getInstance().closeModule();
    }

    public static void navigate(String action) {
        Reporter.getInstance().newModule();
        action = action.toLowerCase();
        switch (action) {
            case "forward":
                driver.navigate().forward();
                break;
            case "back":
                driver.navigate().back();
                break;
            case "refresh":
                driver.navigate().refresh();
                break;
        }
        Reporter.getInstance().closeModule();
    }

    /***
     * Hover on element
     * @param element
     */
    public static void hoverOnElement(WebElement element) {
        Reporter.getInstance().newModule();
        //Instantiating Actions class
        Actions actions = new Actions(driver);

        //Hovering on element
        actions.moveToElement(element);
        Reporter.getInstance().closeModule();
    }

    /***
     * Hover on item
     * @param item
     */
    public static void hoverOnElement(Item item) {
        //Instantiating Actions class
        Actions actions = new Actions(driver);

        //Hovering on element
        actions.moveToElement(getElement(item));
    }


    public static void click_Keyboard(Item item, String key) {
        switch (key) {

        }
    }


}
