package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bases.BaseConfig;
import utilization.Use;
import utilization.Utilize;

public class MapLocationPage extends BaseConfig{
	public MapLocationPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
    @FindBy(xpath="//canvas[contains(@style,'image-rendering')]")
    WebElement canvas;
    
    @FindBy(xpath="//input[@id='search_property']")
	WebElement searchInput;
    
    @FindBy(xpath="//div[@id='getMyLoc']/img")
    WebElement getMyLocationButton;
    
    WebElement searchResult;
    
    WebElement searchResultLocation;
    
    //Measure distance tool
    @FindBy(xpath="//img[@id='drawToolsSelected']")
    WebElement drawToolButton;
    
    @FindBy(xpath="//div[@id='drawLine']/img")
    WebElement drawLineButton;
    
    //Zoom in and zoom out
    @FindBy(xpath="//div[@class='zoomBtnWrapper']/img[@class='zoomInBtn']")
    WebElement zoomInButton;
    
    @FindBy(xpath="//div[@class='zoomBtnWrapper']/img[@class='zoomOutBtn']")
    WebElement zoomOutButton;
    
    String file1, file2;
    
    public void testSearchLocation(String locationName) throws InterruptedException {
    	Use.waitElement(canvas);
    	Thread.sleep(3000);
    	Use.SendKeys(searchInput, locationName); Thread.sleep(3000);
    	searchResult = getDriver().findElement(By.xpath("//span[@id='searchresult_name' and contains(text(),'"+locationName+"')]"));
    	Use.Click(searchResult);
    	
    	searchResultLocation = getDriver().findElement(By.xpath("(//div[@id='markerInfoContent']/span[contains(text(),'"+locationName+"')])[1]"));
    	Use.waitElement(searchResultLocation);
    	
    	sa.assertEquals(searchResultLocation.getText(), locationName,"Location does not macth based to text input");
    	sa.assertAll();
    	extent.flush();
    }
    
    public void getMyLocation() throws InterruptedException, IOException {
    	Use.waitElement(canvas);
    	Use.Click(getMyLocationButton);
    	Thread.sleep(3000);
    }
    
    public void moveMapFromOneLocationToAnotherLocation() throws InterruptedException, IOException {
    	Thread.sleep(2000);
    	
    	// Lokasi awal untuk memulai drag
    	int startX = 50;
    	int startY = 100; 

    	// Offset untuk menggeser peta
    	int offsetX = 700; 
    	int offsetY = 120; 
    	
    	for(int i=1;i<4;i++) {
    		getActions().moveToElement(canvas, startX, startY)
    		.clickAndHold()
    		.moveByOffset(offsetX, offsetY)
    		.release()
    		.click()
    		.perform();
    	}
    	Thread.sleep(5000);
    	 sa.assertTrue(Utilize.verifyImage("moveMapFromOneLocationToAnotherLocation"),"Images are not matched");
         sa.assertAll();
    }
    
    public void drawLine() throws InterruptedException, IOException {
    	getMyLocation();
    	Use.waitElement(canvas);
    	Use.Click(drawToolButton);
    	Use.Click(drawLineButton);
    	
    	int canvasWidth = canvas.getSize().getWidth();
    	int canvasHeight = canvas.getSize().getHeight();
    	
    	//titik ke 1
        int x1 = canvasWidth/30; 
        int y1 = canvasHeight/140;
        
        //titik ke 2
        int x2 = canvasWidth/3; 
        int y2 = canvasHeight/3;

        // Klik pada titik pertama
        getActions().moveToElement(canvas, x1 ,y1).click().perform();
        
        // Klik pada titik kedua
        getActions().moveToElement(canvas, x2 ,y2).click().perform();
        Thread.sleep(1000);
        
        sa.assertTrue(Utilize.verifyImage("drawLine"),"Images are not matched");
        sa.assertAll();
    }
    
    public void testZoomIn() throws InterruptedException, IOException {
    	getMyLocation();
    	Use.Click(zoomInButton);
    	Thread.sleep(3000);
    	
        sa.assertTrue(Utilize.verifyImage("ZoomIn"),"Images are not matched");
        sa.assertAll();
    }
    
    public void testZoomOut() throws InterruptedException, IOException {
    	getMyLocation();
    	Use.Click(zoomOutButton);
    	Thread.sleep(3000);
    	
    	sa.assertTrue(Utilize.verifyImage("ZoomOut"),"Images are not matched");
    	sa.assertAll();
    }
}