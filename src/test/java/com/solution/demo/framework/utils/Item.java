package com.solution.demo.framework.utils;

import org.openqa.selenium.By;
import org.sam.rosenthal.cssselectortoxpath.utilities.CssElementCombinatorPairsToXpath;

/***
 * This class is the generic representation of an element in webPage
 */
public class Item {
    private String locatorType;
    private String locator;
    private String xpath;
    private String label;
    private By by;

    /***
     *
     * @param xpathLocator : is purely the xpath locator Relatif or Absolute
     * @param label : Name to give to this Element
     */
    public Item(String xpathLocator, String label) {
        super();
        this.locatorType = "xpath";
        this.xpath = xpathLocator;
        this.label = label;
        this.by = By.xpath(xpathLocator);
    }

    /***
     *
     * @param locatorType : 8 possiblities (id, name, linkText, partialLinkText, cssSelector, className, Xpath, TagName). (Try to avoid TagName => it will return the first element)
     * @param locator : locator, for example if you use locatorType=id, so locator should be the id value.
     *                Xpath and By will be built automaticcaly, in this example, getXpath() will be "//*[@id='"+id_value+"']" and getBy() will By.id(id_value)
     * @param label : Name to give to this Element
     */
    public Item(String locatorType, String locator, String label) {
        super();
        this.locatorType = locatorType;
        String newXpath = "";

        locatorType.toLowerCase();

        if ("id".equalsIgnoreCase(locatorType)) {
            this.by = By.id(locator);
            newXpath = "//*[@id='" + locator + "']";
        } else if ("linktext".equalsIgnoreCase(locatorType)) {
            this.by = By.linkText(locator);
            newXpath = "//a[contains(.,'" + locator + "')]";
        } else if ("partiallinktext".equalsIgnoreCase(locatorType)) {
            this.by = By.partialLinkText(locator);
            newXpath = "//a[contains(.,'" + locator + "')]";
        } else if ("classname".equalsIgnoreCase(locatorType)) {
            this.by = By.className(locator);
            newXpath = "//*[@class='" + locator + "']";
        } else if ("cssselector".equalsIgnoreCase(locatorType)) {
            this.by = By.cssSelector(locator);
            try {
                CssElementCombinatorPairsToXpath elementCombinatorPair = new CssElementCombinatorPairsToXpath();
                newXpath = elementCombinatorPair.convertCssSelectorStringToXpathString(locator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("name".equalsIgnoreCase(locatorType)) {
            this.by = By.name(locator);
            newXpath = "//*[@name = '" + locator + "']";
        } else if ("tagname".equalsIgnoreCase(locatorType)) {
            this.by = By.tagName(locator);
            newXpath = "//" + locator;
        } else if ("xpath".equalsIgnoreCase(locatorType)) {
            this.by = By.xpath(locator);
            newXpath = locator;
        } else {
            newXpath = "//*[@" + locatorType + " = '" + locator + "']";
            this.by = By.xpath(newXpath);
        }

        this.xpath = newXpath;
        this.label = label;
        System.out.println("xpath:" + this.xpath + ", label:" + this.label);

    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public By getBy() {
        return by;
    }

    public void setBy(By by) {
        this.by = by;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "locatorType='" + locatorType + '\'' +
                ", locator='" + locator + '\'' +
                ", xpath='" + xpath + '\'' +
                ", label='" + label + '\'' +
                ", by=" + by +
                '}';
    }

    private String containingWord(String attribute, String word) {
        return "contains(concat(' ',normalize-space(@" + attribute + "),' '),' " + word + " ')";
    }
}
