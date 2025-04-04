package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class practicewritecode {
    WebDriver driver;

    //
    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver){
       return driver.getTitle();
    }

    public String getPageCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitForAllertPresence(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAllert(WebDriver driver){
        waitForAllertPresence(driver).accept();
    }

    public void cancelAllert(WebDriver driver){
        waitForAllertPresence(driver).dismiss();
    }

    public String getAllertText(WebDriver driver){
       return waitForAllertPresence(driver).getText();
    }

    public void sendkeyToAllert(WebDriver driver, String textValue){
        waitForAllertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByID(WebDriver driver,String windowID){

        // get het cac id dang co
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            if(!id.equals(windowID)){
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String tabTitle){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if(actualTitle.equals(tabTitle)){
                break;
            }
        }
    }

    public void closeAllTabWithoutParent(WebDriver driver, String parentID){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            if (!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByXpath(locator));
    }

    public List<WebElement> getListWebElemt(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver, locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String textValue){
        WebElement element = getWebElement(driver, locator);
        element.clear();
        element.sendKeys(textValue);
    }

    public String getElementText(WebDriver driver, String locator, String textValue){
        return getWebElement(driver, locator).getText();
    }

    public void selectItemIndefaultDropdown(WebDriver driver, String locator, String textItem){
        Select select = new Select(getWebElement(driver, locator));
        select.selectByVisibleText(textItem);
    }

    public String getSelectedItemDefaultDropdown(WebDriver driver, String locator, String textItem){
        Select select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        Select select = new Select(getWebElement(driver, locator));
        return select.isMultiple();
    }

    public void sleep(long time){
        try {
            Thread.sleep(time * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedTextItem){
        getWebElement(driver, parentXpath).click();
        sleep(5);
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));
        for (WebElement item : allItems){
            if(item.getText().trim().equals(expectedTextItem)){
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoview(true);", item);
                sleep(3);
                item.click();
                break;
            }
        }
    }

    public String getElementAttriBute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver,locator).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locator, String cssValue){
       return getWebElement(driver, locator).getCssValue(cssValue);
    }

    public String getHexaColorFromRGBA(String rgbaValue){
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver,String locaotor){
       return getListWebElemt(driver, locaotor).size();
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String locator){
        WebElement element = getWebElement(driver, locator);
        if (!element.isSelected()){
            element.click();
        }
    }

    public void unCheckToDefaultCheckbox(WebDriver driver, String locator){
        WebElement element = getWebElement(driver, locator);
        if (element.isSelected()){
            element.click();
        }
    }

    public boolean isElemetDisplayed(WebDriver driver, String locator){
       return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver,String locator){
       return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public WebDriver switchToFrameIframe(WebDriver driver, String locator){
        return driver.switchTo().frame(getWebElement(driver,locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver){
        return driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locator)).perform();
    }

    public void scrollTobuttomPage(WebDriver driver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightligtElement(WebDriver driver, String locator, String atributeStyle){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], argument[2])", element, "style", "border: 2px solid red;");
        sleep(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                }catch (Exception e){
                    return true;
                }

            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
       return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"underfined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        if(status){
            return true;
        }else {
            return false;
        }
    }

    public void isDisplay(WebDriver driver, String locator){
        driver.findElement(By.xpath("")).clear();
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver,locator)).perform();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        File file = new File("");
    }









}
