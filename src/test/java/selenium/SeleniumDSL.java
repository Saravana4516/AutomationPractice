package selenium;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;
import utilities.general.Property;
import utilities.iris.APICaller;
import utilities.reporting.ExtentTestManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static utilities.reporting.LogUtil.logger;
/*import ru.yandex.qatools.ashot.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;*/
/**
 * Base class providing set of common selenium methods
 */

public class SeleniumDSL {

    public static WebDriver driver(){
        return DriverFactory.getInstance().getDriver();
    }

    /** load page for specified URL */
    public static void goURL(String URL){
        driver().get(URL);
    }

    /** get current page title */
    public static String getTitle() {
        return driver().getTitle();
    }

    public static boolean CheckIfElementExists(By by)
    {
        if(driver().findElements(by).size() > 0)
            return true;
        else
            return false;
    }

    public static int CheckElementsSize(By by)
    {
        if(driver().findElements(by).size() > 0)
            return driver().findElements(by).size();
        else
            return 0;
    }

    public static void MoveToNexttab()
    {

        ArrayList<String> tabs = new ArrayList<String> (driver().getWindowHandles());
  //      driver().switchTo().window(tabs.get(Baseclass.getInstance().NoOfTabs));

    }
    /** get current window handle */
    public static String getWindow(){
        return driver().getWindowHandle();
    }

    /** get list of open windows */
    public static Set<String> getWindows(){
        return driver().getWindowHandles();
    }
//	@SuppressWarnings("unchecked")
//	public static ArrayList<String> getWindows(){
//		new ArrayList<String> (driver().getWindowHandles());
//		return (ArrayList<String>) driver().getWindowHandles();
//	}

    /** switch to window based on handle */
    public static void switchWindow(String windowHandle){
        driver().switchTo().window(windowHandle);
    }

    /** Wait for frame to be available and then switch to it */
    public static WebDriver switchFrame(By by, int... timeOutInSeconds)  {
        return waitForDriver(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }

    //switch frmae by ID
    public static WebDriver switchFrameByID(String ID, int... timeOutInSeconds)  {
        return waitForDriver(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ID));
    }



    /** switch to specified frame */
    public static void switchFrame(WebElement targetFrame){
        driver().switchTo().frame(targetFrame);
    }

    /** switch to first frame */
    public static void switchDefault(){
        driver().switchTo().defaultContent();
    }

    /** browser back navigation action */
    public static void back(){
        driver().navigate().back();
    }

    /** browser forward navigation action */
    public static void forward(){
        driver().navigate().forward();
    }

    /** browser page refresh action */
    public static void refresh(){
        driver().navigate().refresh();
    }

    /** browser close */
    public static void close() {
        driver().quit();
    }

    /** Returns first element occurence matching the supplied locator*/
    public static WebElement find(By by) {
        return (WebElement) waitFor(ExpectedConditions.presenceOfElementLocated(by));
    }

    /** Returns all element occurences matching the supplied locator */

    @SuppressWarnings("unchecked")
    public static List<WebElement> findAll(By by) {
        return (List<WebElement>) waitForAll(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /** Finds first element within current element matching the supplied locator */
    public static WebElement find(By by, By sub){
        return (WebElement) waitForSub(ExpectedConditions.presenceOfNestedElementLocatedBy(by, sub));
    }

    /** Finds first element within current element matching the supplied locator */
    public static WebElement find(WebElement by, By sub){
        return (WebElement) waitForSub(ExpectedConditions.presenceOfNestedElementLocatedBy(by, sub));
    }

    /** Finds all elements within current element matching the supplied locator */
    @SuppressWarnings("unchecked")
    public static List<WebElement> findAll(By by, By sub){
        return (List<WebElement>) waitForAllSub(ExpectedConditions.visibilityOfNestedElementsLocatedBy(by, sub));
    }

    /** Finds all elements within current element matching the supplied locator */
    @SuppressWarnings("unchecked")
    public static List<WebElement> findAll(WebElement by, By sub){
        return (List<WebElement>) waitForAllSub(ExpectedConditions.visibilityOfNestedElementsLocatedBy(by, sub));
    }

    /** Returns element occurence in list of elements based on index position */
    public static WebElement getElementOccurence(By by, int index) {
        return findAll(by).get(index);
    }

    /** Returns inner text for any element */
    public static String getText(By by) {
        return find(by).getText();
    }

    /** Returns inner text for any element */
    public static String getText(WebElement el) {
        return el.getText();
    }

    /** Returns text held in the attribute 'value' for any element */
    public static String getValue(By by) {
        return find(by).getAttribute("value");
    }

    /** Returns text held in the specified attribute for any element (or null if attribute doesn't exist) */
    public static String getAttribute(By by, String attribute) {
        return find(by).getAttribute(attribute);

    }

    /** Returns true or false on whether the element is enabled */
    public static boolean isEnabled(By by){
        return find(by).isEnabled();
    }

    /** Returns true or false on whether the element is visible */
    public static boolean isVisible(By by){
        return find(by).isDisplayed();
    }

    /** Returns true or false on whether the element is selected */
    public static boolean isSelected(By by){
        return find(by).isSelected();
    }

    /** Returns the top left location coordinates of the element */
    public static Point getLocation(By by){
        return find(by).getLocation();
    }

    /** Returns the dimension (width and height) of the element */
    public static Dimension getRenderedSize(By by){
        return find(by).getSize();
    }

    /** get Select object for dropdown */
    public static Select getSelect(By by){
        return new Select((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by)));
    }

    public static Select getSelectByName(String selectName){
        return new Select((WebElement) waitFor(ExpectedConditions.elementToBeClickable(By.name(selectName))));
    }

    /** select specified dropdown option */
    public static String getDropdownValue(By by) {
        return getSelect(by).getFirstSelectedOption().getText();
    }

    /** select specified dropdown option */
    public static void selectDropdownByIndex(By by, int val) {
        getSelect(by).selectByIndex(val);
    }
    public static void selectDropdownByIndexWithName(String name, int val) {
        getSelectByName(name).selectByIndex(val);
    }


    /** select specified dropdown option */
    public static void selectDropdownByText(By by, String val) {
        getSelect(by).selectByVisibleText(val);

    }

    public static void selectByPartOfVisibleText(By by, String val) {
        List<WebElement> alldropdownelements = getSelect(by).getOptions();
        for(WebElement ele: alldropdownelements)
        {
            String currentOption = ele.getText();
            if(currentOption.contains(val))
            {
                getSelect(by).selectByVisibleText(currentOption);
                break;
            }
        }
    }


    /** select specified dropdown option */
    public static void selectDropdownByValue(By by, String val) throws InterruptedException {
        Select select = getSelect(by);
        int number_of_options=select.getOptions().size();
        int i =1;

        while(number_of_options<=1 && i<60)
        {
            Thread.sleep(500);
            i++;
            number_of_options=select.getOptions().size();
        }
        select.selectByValue(val);
    }

    /** deselect specified dropdown option */
    public static void deselectDropdownByIndex(By by, int val) {
        getSelect(by).deselectByIndex(val);
    }

    /** deselect specified dropdown option */
    public static void deselectDropdownByText(By by, String val) {
        getSelect(by).deselectByVisibleText(val);
    }

    /** deselect specified dropdown option */
    public static void deselectDropdownByValue(By by, String val) {
        getSelect(by).deselectByValue(val);
    }

    /** deselect all dropdown options */
    public static void deselectDropdownAll(By by) {
        getSelect(by).deselectAll();
    }

    /** get count of dropdown options */
    public static int getDropdownCount(By by) {
        List <WebElement> l = getSelect(by).getOptions();
        return l.size();
    }

    /** check whether dropdown allows multi selection */
    public static Boolean isDropdownMultiple(By by) {
        if (getSelect(by).isMultiple())
            return true;
        else
            return false;
    }

    /** Return all options within a dropdown as string array */
    public static List<String> getDropdownOptionsText(By by){
        List<String> optionsText = new ArrayList<String>();
        List<WebElement> options = getSelect(by).getOptions();
        for (WebElement option : options){
            optionsText.add(option.getText());
        }
        return optionsText;
    }

    /** Return all options within a dropdown as list of elements */
    public static List<WebElement> getDropdownOptionsElements(By by){
        List<WebElement> options = getSelect(by).getOptions();
        return options;
    }

    /** Return all options groups within a dropdown as string array */
    public static List<String> getDropdownOptGroupsText(By by){
        List<String> optGroupsText = new ArrayList<String>();
        List<WebElement> optGroups = findAll(by, By.tagName("optgroup"));
        for (WebElement optGroup : optGroups){
            optGroupsText.add(optGroup.getText());
        }
        return optGroupsText;
    }

    /** Return all options groups within a dropdown as list of elements */
    public static List<WebElement> getDropdownOptGroupsElements(By by){
        List<WebElement> optGroups = findAll(by, By.tagName("optgroup"));
        return optGroups;
    }

    /** Return all options within an option group of a dropdown as string array */
    public static List<String> getDropdownOptionsTextWithinGroup(By by, String group){
        List<String> optionsText = new ArrayList<String>();
        List<WebElement> options = findAll(by, By.xpath("//optgroup[@label="+group+"]/option"));
        for (WebElement option : options){
            optionsText.add(option.getText());
        }
        return optionsText;
    }

    /** Return all options within an option group of a dropdown as list of elements */
    public static List<WebElement> getDropdownOptionsElementsWithinGroup(By by, String group){
        List<WebElement> options = findAll(by, By.xpath("//optgroup[@label="+group+"]/option"));
        return options;
    }

    /** Returns the page body text */
    public static WebElement getPageBody() {
        return find(By.xpath("/html/body"));
    }

    /** Get count of number of table rows */
    public static int getTableRowCount(By table){
        return getDataRowCount(table) + getHeadRowCount(table);
    }

    /** Get count of number of table header rows */
    public static int getHeadRowCount(By table){
        return  findAll(table,By.tagName("th")).size();
    }

    /** Get count of number of table data rows */
    public static int getDataRowCount(By table){
        return  findAll(table, By.tagName("tr")).size();
    }

    /** Get count of number of table header columns */
    public static int getHeadColumnCount(By by, int rowIndex){
        return getHeadRowElements(by, rowIndex).size();
    }

    /** Get count of number of table data columns */
    public static int getDataColumnCount(By table, int rowIndex){
        return getDataRowElements(table, rowIndex).size();
    }

    /** Get the list of header columns within a given row of a table */
    public static List<WebElement> getHeadRowElements(By table, int rowIndex){
        return findAll(table,By.tagName("tr")).get(rowIndex).findElements(By.tagName("th"));
    }

    /** Get the list of data columns within a given row of a table */
    public static List<WebElement> getDataRowElements(By table, int rowIndex){
        return findAll(table,By.tagName("tr")).get(rowIndex).findElements(By.tagName("td"));
    }

    /** Get value of a table data cell */
    public static WebElement getDataCellElement(By by, int rowIndex, int columnIndex){
        return getDataRowElements(by, rowIndex).get(columnIndex);
    }

    /** Get value of a table header cell */
    public static WebElement getHeadCellElement(By by, int rowIndex, int columnIndex){
        return getHeadRowElements(by, rowIndex).get(columnIndex);
    }

    /** Performs mouse click action on the element using javascript for consistent cross browser results*/
    public static void click(By by) {
        ((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by))).click();

    }

    /** Performs mouse click action on the element using javascript for consistent cross browser results*/
    public static void click(WebElement element) {
        element.click();
    }

    /** Performs mouse click action on the element using javascript for consistent cross browser results*/
    public static void clickJS(By by) {
        JavascriptExecutor executor = (JavascriptExecutor)driver();
        executor.executeScript("arguments[0].click();", (WebElement) waitFor(ExpectedConditions.elementToBeClickable(by)));
    }

    /** Performs mouse click action on the element using javascript for consistent cross browser results*/
    public static void clickJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver();
        executor.executeScript("arguments[0].click();", element);
    }

    /** clear text from field */
    public static WebElement clear(By by)  {
        WebElement el = (WebElement) waitFor(ExpectedConditions.elementToBeClickable(by));
        el.clear();
        return el;
    }

    /** enter text to field */
    public static void enterText(By by, String val)  {
        ((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by))).sendKeys(val);
    }

    public static void enterTextUsingAction(By by, String val)  {

        Actions performAct = new Actions(driver());
        performAct.sendKeys(driver().findElement(by),val).build().perform();
    }

    public static void HoverUsingAction(By by)  {

        Actions performAct = new Actions(driver());
        performAct.moveToElement(driver().findElement(by)).build().perform();
//		.sendKeys(driver().findElement(by),val).build().perform();
    }

    public static void  mouseHoverJScript(WebElement HoverElement){

        String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";


//		WebElement element = driver().findElement(By);
        JavascriptExecutor JS = (JavascriptExecutor)driver();
        JS.executeScript(mouseOverScript,HoverElement);
//		JS.executeScript("arguments[0].value='"+val+"';", element);


    }

    public static void enterText1(By by, Keys val)  {
        ((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by))).sendKeys(val);
    }

    /** clear and enter text to field */
    public static void clearEnterText(By by, String val)  {
        clear(by).sendKeys(val);
    }

    /** mimic hitting the enter key */
    public static void sendEnter(By by)  {
        ((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by))).sendKeys(Keys.ENTER);
    }

    public static void sendCtrlPlusPageDown()  {
        Actions actionObj = new Actions(driver());

        actionObj.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).keyUp(Keys.CONTROL).build().perform();

    }

    public static void sendPageDownKey()  {
        Actions actionObj = new Actions(driver());
        actionObj.sendKeys(Keys.PAGE_DOWN).build().perform();

    }

    public static void sendBackSpace(By by)  {
        ((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by))).sendKeys(Keys.BACK_SPACE);
    }

    public static void EnterTextUsingJS(By by,String val)  {
        WebElement element = driver().findElement(by);
        JavascriptExecutor JS = (JavascriptExecutor)driver();
        JS.executeScript("arguments[0].value='"+val+"';", element);
    }

    public static void DragAndDropUsingJS(WebElement source,WebElement destination)  {
        JavascriptExecutor js = (JavascriptExecutor)driver();
        js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                + "var dropEvent = createEvent('drop');\n"
                + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                + "var dragEndEvent = createEvent('dragend');\n"
                + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                + "simulateHTML5DragAndDrop(source,destination);", source, destination);   }

    public static int RandomNumberGenerator(){
        Random rnd = new Random();
        return 10000 + rnd.nextInt(90000);
    }

    public static void sendDelete()  {
        Actions ac = new Actions(driver());
        ac.sendKeys(Keys.DELETE);
        ac.build().perform();
    }

    public static void clickAddButton()  {
        Actions ac = new Actions(driver());
        ac.sendKeys(Keys.ADD);
        ac.build().perform();
    }

    public static void sendPageDown()  {
        Actions ac = new Actions(driver());
        ac.sendKeys(Keys.PAGE_DOWN);
        ac.build().perform();
    }
    public static void sendEntr()  {
        Actions ac = new Actions(driver());
        ac.sendKeys(Keys.ENTER);
        ac.build().perform();
    }
    public static void sendBackSpace()  {
        Actions ac = new Actions(driver());
        ac.sendKeys(Keys.BACK_SPACE);
        ac.build().perform();
    }
    public static void sendTab(By by)  {
        ((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by))).sendKeys(Keys.TAB);
    }
    public static void sendBlankTab()  {
        Actions ac = new Actions(driver());
        ac.sendKeys(Keys.TAB);
        ac.build().perform();
    }

    public static void MovetoNext()
    {
        Actions actionObj = new Actions(driver());
        actionObj.sendKeys(Keys.ENTER).sendKeys(Keys.TAB).build().perform();

    }

    public static void sendEsc()  {
//		((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by))).sendKeys(Keys.ESCAPE);
        Actions action = new Actions(driver());
        action.sendKeys(Keys.ESCAPE);
        action.build().perform();
    }

    /** select action for checkboxes / radio buttons */
    public static void select(By by) {
        if (!find(by).isSelected())
            click(by);
    }

    /** deselect action for checkboxes / radio buttons */
    public static void unselect(By by) {
        if (find(by).isSelected())
            find(by).click();
    }

    /** Submits the form using any WebElement within the form or the form element itself */
    public static void submit(By by){
        find(by).submit();
    }

    /** Write assertion failure to output report and optionally fail test+grab screenshot */
    public static void reportAssertResult(String exceptionReason, Boolean fail, String msg) {
        ExtentTest extentTest = ExtentTestManager.getTest();
        extentTest.log(LogStatus.FAIL, Context.getInstance().getScenario() +" : " + msg + exceptionReason);
        if (fail){
            grabScreenshotForExtentReport();
            Assert.fail(exceptionReason);
        }
    }

    /** Asserts that two objects are equal otherwise write to output report and optionally stop test+grab screenshot */
    public static void assertEquals(Object act, Object exp, Boolean stopTest, String... msg) {
        try{
            Assert.assertEquals(act, exp);
        }catch(AssertionError e) {
            reportAssertResult(e.getMessage(), stopTest, (msg.length > 0 ? msg[0] : Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }

    /** Asserts that two objects are not equal otherwise write to output report and optionally stop test+grab screenshot */
    public static void assertNotEquals(Object act, Object exp, Boolean stopTest, String... msg) {
        try{
            Assert.assertNotEquals(act, exp);
        }catch(AssertionError e) {
            reportAssertResult(e.getMessage(), stopTest, (msg.length > 0 ? msg[0] : Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }

    /** Asserts condition is true otherwise write to output report and optionally stop test+grab screenshot */
    public static void assertTrue(Boolean expression, Boolean stopTest, String... msg) {
        try{
            Assert.assertTrue(expression);
        }catch(AssertionError e) {
            reportAssertResult(e.getMessage(), stopTest, (msg.length > 0 ? msg[0] : Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }

    /** Asserts condition is false otherwise write to output report and optionally stop test+grab screenshot */
    public static void assertFalse(Boolean expression, Boolean stopTest, String... msg) {
        try{
            Assert.assertFalse(expression);
        }catch(AssertionError e) {
            reportAssertResult(e.getMessage(), stopTest, (msg.length > 0 ? msg[0] : Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }

    /** Asserts object is  null otherwise write to output report and optionally stop test+grab screenshot */
    public static void assertNull(Object obj, Boolean stopTest, String... msg) {
        try{
            Assert.assertNull(obj);
        }catch(AssertionError e) {
            reportAssertResult(e.getMessage(), stopTest, (msg.length > 0 ? msg[0] : Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }

    /** Asserts object is not null otherwise write to output report and optionally stop test+grab screenshot */
    public static void assertNotNull(Object obj, Boolean stopTest, String... msg) {
        try{
            Assert.assertNotNull(obj);
        }catch(AssertionError e) {
            reportAssertResult(e.getMessage(), stopTest, (msg.length > 0 ? msg[0] : Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }

    /** Asserts 2 objects refer to same object null otherwise write to output report and optionally stop test+grab screenshot */
    public static void assertSame(Object act, Object exp, Boolean stopTest, String... msg) {
        try{
            Assert.assertSame(act, exp);
        }catch(AssertionError e) {
            reportAssertResult(e.getMessage(), stopTest, (msg.length > 0 ? msg[0] : Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }

    /** Asserts 2 objects do not refer to same object null otherwise write to output report and optionally stop test+grab screenshot */
    public static void assertNotSame(Object act, Object exp, Boolean stopTest, String... msg) {
        try{
            Assert.assertNotSame(act, exp);
        }catch(AssertionError e) {
            reportAssertResult(e.getMessage(), stopTest, (msg.length > 0 ? msg[0] : Thread.currentThread().getStackTrace()[2].getMethodName()));
        }
    }


    /** Returns duration for specified waits */
    public static int getWaitDuration(){

        final int defaultWait = 10;

        int duration;
        try {
            duration = Integer.parseInt(Property.getProperty("defaultWait"));
        } catch (Exception e) {
            duration = defaultWait;
        }
        return duration;
    }

    /** Sets up wait object with timeout duration*/
    public static WebDriverWait getWait(int... timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver(), timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : getWaitDuration());
        return wait;
    }

    public static void ExpWaitForCondition(By by){
        WebDriverWait wait = new WebDriverWait(driver(), 360);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void ExpWaitForElementToDisappear(By by){
        WebDriverWait wait = new WebDriverWait(driver(), 360);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static Object waitFor(ExpectedCondition<WebElement> condition, int... timeOutInSeconds) {
        return getWait(timeOutInSeconds).until(condition);
    }

    public static Object waitForAll(ExpectedCondition<List<WebElement>> condition, int... timeOutInSeconds) {
        return getWait(timeOutInSeconds).until(condition);
    }

    public static Object waitForSub(ExpectedCondition<WebElement> condition, int... timeOutInSeconds) {
        return getWait(timeOutInSeconds).until(condition);
    }

    public static Object waitForAllSub(ExpectedCondition<List<WebElement>> condition, int... timeOutInSeconds) {
        return getWait(timeOutInSeconds).until(condition);
    }

    private static WebDriver waitForDriver(ExpectedCondition<WebDriver> condition, int... timeOutInSeconds) {
        return getWait(timeOutInSeconds).until(condition);
    }

    /** Wait for page to load based on document.readyState=complete*/
    public static void waitPageToLoad() {
        final JavascriptExecutor js = (JavascriptExecutor)driver();
        getWait().until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {return (js.executeScript("return document.readyState").equals("complete"));}
        });
    }

    /** Highlights an element with a blue border.....useful when debugging/taking screenshots */
    public static void highlight(By by){
        JavascriptExecutor js = (JavascriptExecutor) driver();
        String script = "arguments[0].style.border";
        String border = "3px solid blue";
        js.executeScript(script + "='" + border + "'", driver().findElement(by));
    }

    /** Performs mouse action move to element on the screen */
    public static void move(By by) {
        Actions action = new Actions(driver());
        action.moveToElement(driver().findElement(by)).build().perform();
    }

    /** Performs mouse action move to a parent element on the screen, locate child element and click */
    public static void moveAndClick(By byParent, By byChild) {
        Actions action = new Actions(driver());
        action.moveToElement(driver().findElement(byParent)).build().perform();
        driver().findElement(byChild).click();
    }

    /** Performs mouse action click and hold */
    public static void clickAndHold(By by) {
        Actions action = new Actions(driver());
        action.clickAndHold(driver().findElement(by)).build().perform();
    }

    /** Performs mouse action release button */
    public static void release() {
        Actions action = new Actions(driver());
        action.release().build().perform();
    }

    //clicks right key
    public static void moveRight(){
        Actions action = new Actions(driver());
        action.sendKeys(Keys.ARROW_RIGHT);
        action.build().perform();
    }

    public static void doubleClick(By by) {
        Actions action = new Actions(driver());
        action.doubleClick(driver().findElement(by)).build().perform();
    }

    public static void rightClick(By by) {
        Actions action = new Actions(driver());
        action.contextClick(driver().findElement(by)).build().perform();
    }
    public static void singleClick(By by) {
        Actions action = new Actions(driver());
        action.click(driver().findElement(by)).build().perform();
    }

    public static void ClearTextAndEnterData(String textToBeEntered) {
        Actions action = new Actions(driver());
        action.keyDown(Keys.CONTROL);
        action.sendKeys("a");
        action.keyUp(Keys.CONTROL);
        action.sendKeys(Keys.BACK_SPACE);
        action.sendKeys(textToBeEntered);
        action.sendKeys(Keys.ENTER);
        action.build().perform();
    }

    public static void ControlPlusAllAndDelete() {
        Actions action = new Actions(driver());
        action.keyDown(Keys.CONTROL);
        action.sendKeys("a");
        action.sendKeys(Keys.DELETE);
        action.keyUp(Keys.CONTROL);
        action.build().perform();
    }

    public static void DragAndDrop(By from, By to ) {
        Actions action = new Actions(driver());
        WebElement From = driver().findElement(from);
        WebElement To = driver().findElement(to);
        action.dragAndDrop(From,To).build().perform();
    }

    /** capture screenshot and return a file object */
    public static File grabScreenshot() {
        try {
            Thread.sleep(Integer.parseInt(Property.getProperty("screenshotDelay")));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //File screenshot = ((TakesScreenshot)driver()).getScreenshotAs(OutputType.FILE);

        BufferedImage screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000)).takeScreenshot(driver()).getImage();
        File outputfile = new File ("Dummyimage.png");
        try {
            ImageIO.write(screenshot,"png",outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputfile;
    }

    /** capture screenshot and save to specified location */
    public static void saveScreenshot(String screen, String filePath) {

        try {
            FileUtils.moveFile(grabScreenshot(), new File(filePath+screen+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**captures full page screenshot**/
    public static void saveScreenshotFullPage(String screen, String filePath) {

        try {
            File outputfile = new File (filePath+File.separator+"x.png");
            outputfile.getParentFile().mkdirs();
            File parentDir = outputfile.getParentFile();
            System.out.println(parentDir);
            if(parentDir !=null && ! parentDir.exists() ){
                if(!parentDir.mkdirs()){
                    throw new IOException("error creating directories");
                }
            }
            Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000)).takeScreenshot(driver());
            ImageIO.write(screenshot.getImage(),"PNG",new File(filePath+screen+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** grab screenshot snippet */
    public static File snipScreenshot(By by) {

        File screenshot = grabScreenshot();
        try {
            BufferedImage buffer = ImageIO.read(screenshot);

            Dimension dim = getRenderedSize(by);
            Point point = getLocation(by);

            // Crop the entire page screenshot to get only element screenshot
            BufferedImage snippet = buffer.getSubimage(0, point.getY(), point.getX()+dim.width, dim.height);
            ImageIO.write(snippet, "png", screenshot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshot;
    }

    /** capture screenshot and add to IRIS */
    public static void grabScreenshotForIRIS(int stepNumber, String stepDescription) throws NumberFormatException, Exception {
        if (Boolean.valueOf(System.getProperty("iristrigger"))){
            int execID = Integer.parseInt(System.getProperty("browsercombo"));
            String irisURL = Property.getProperty("irisURL");
            String currentURL = driver().getCurrentUrl();
            String pageTitle = driver().getTitle();
            APICaller api = new APICaller(irisURL);
            api.uploadScreenshot(execID, Integer.parseInt(Context.getInstance().getPlatformID()), Integer.parseInt(Context.getInstance().getBrowserID()), stepNumber, stepDescription, currentURL, pageTitle, grabScreenshot());
        }
    }

    /** capture screenshot and add to ExtentReport */
    public static void grabScreenshotForExtentReport() {
        writeScreenshotToExtentReport(grabScreenshot());
    }

    /** capture screenshot and add to ExtentReport */
    public static void grabScreenshotForExtentReport(File screenshot) {
        writeScreenshotToExtentReport(screenshot);
    }

    /** write screenshot to ExtentReport */
    public static void writeScreenshotToExtentReport(File screenshot) {
        UUID uuid = UUID.randomUUID();
        File file = new File(System.getProperty("user.dir") + File.separator + "ExtentReports" + File.separator + "Screenshots" + File.separator + uuid + ".png");
        try {
            FileUtils.copyFile(screenshot, file);
        } catch (IOException e) {
            ExtentTest extentTest = ExtentTestManager.getTest();
            extentTest.log(LogStatus.INFO, "Unable to log screenshot");
        }
        String relativeScreenshotsPath = "." + File.separator + "Screenshots" + File.separator + file.getName();
        ExtentTest extentTest = ExtentTestManager.getTest();
        extentTest.log(LogStatus.INFO, extentTest.addScreenCapture(relativeScreenshotsPath));
    }


    /** capture screenshot and add to Cucucmber Report */
    public static void grabScreenshotForCucumberReport(Scenario scenario) {

        byte[] screenshot = ((TakesScreenshot) driver())
                .getScreenshotAs(OutputType.BYTES);

        scenario.embed(screenshot, "image/png");
    }
    public static void prepareWebElementWithDynamicXpathAndClickJS(String xpathValue, String substitutionValue, String targetString )
    {
//		((WebElement) waitFor(ExpectedConditions.elementToBeClickable(by))).click();
//		((WebElement) waitFor(ExpectedConditions.elementToBeClickable(driver().findElement(By.xpath(xpathValue.replace("xxx", substitutionValue)))))).click();
//		driver().findElement(By.xpath(xpathValue.replace("xxx", substitutionValue))).click();

        ((WebElement) waitFor(ExpectedConditions.elementToBeClickable(driver().findElement(prepareWebElementWithDynamicXpath(xpathValue, substitutionValue, targetString))))).click();

    }

    /** prepares dynamic xpath based on string given in UIMap and replace xxx with dynamic text*/
    public static By prepareWebElementWithDynamicXpath(String xpathValue, String substitutionValue,String targetString)
    {
        By generatedXpath;
        targetString="{"+targetString+"}";
        if (xpathValue.contains("By.xpath")) {
            xpathValue=xpathValue.replace("By.xpath: ", "");
            generatedXpath=By.xpath(xpathValue.replace(targetString, substitutionValue));
        }else {
            generatedXpath=By.xpath(xpathValue.replace(targetString, substitutionValue));
        }
//		System.out.println("Generated xpath is"+generatedXpath);
        /*ExtentTestManager.logInfo("Generated xpath is"+generatedXpath);*/
        logger.info("Generated xpath is"+generatedXpath);
        return generatedXpath;

    }
    public static By prepareWebElementWithDynamicXpath2(String xpathValue, String substitutionValue1,String substitutionValue2, String targetString1,String targetString2 )
    {
        By generatedXpath;
        targetString1="{"+targetString1+"}";
        targetString2="{"+targetString2+"}";
        if (xpathValue.contains("By.xpath")) {
            xpathValue=xpathValue.replace("By.xpath: ", "");
            String a = xpathValue.replace(targetString1, substitutionValue1);
            String b = a.replace(targetString2, substitutionValue2);
            generatedXpath=By.xpath(b);

        }else {
            String a = xpathValue.replace(targetString1, substitutionValue1);
            String b = a.replace(targetString2, substitutionValue2);
            generatedXpath=By.xpath(b);
        }
//		System.out.println("Generated xpath is"+generatedXpath);
        /*ExtentTestManager.logInfo("Generated xpath is"+generatedXpath);*/
//    	logger.info("Generated xpath is"+generatedXpath);
        return generatedXpath;

    }

    public static void ScrollIntoView(By by)
    {
        WebElement element = driver().findElement(by);
        ((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void ScrollToEndOfPage()
    {
        ((JavascriptExecutor) driver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void Scroll1()
    {
        ((JavascriptExecutor) driver()).executeScript("scrollBy(0,250)");
    }


    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

    public static void NavigatetoLastActiveTab(String currentHandle)
    {
        Set<String> handles=driver().getWindowHandles();
        for(String actual: handles) {
            if(!actual.equalsIgnoreCase(currentHandle)) {

                driver().switchTo().window(actual);
            }
        }
    }
}
