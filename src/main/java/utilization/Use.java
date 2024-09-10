package utilization;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import bases.BaseConfig;

public class Use extends BaseConfig {
	private static int maxAttempt = 10;
	
	public static void SendKeys(WebElement element, String keysToSend) throws InterruptedException {
		if(!element.getAttribute("value").isEmpty()) {
			element.clear();
		}
		
		if(!keysToSend.isEmpty()) {
			int attempt = 0;
			while(attempt < maxAttempt) {
				try {
					getWait().until(ExpectedConditions.visibilityOf(element));
					element.sendKeys(keysToSend);
					break;
				} catch(StaleElementReferenceException ex) {
					System.out.println("waitVisibleThenSendKeys StaleElementReferenceException");
				}
				attempt++;
			}
		}
	}
	
	public static void waitElement(WebElement element) throws InterruptedException {
		int i= 0;
		boolean stop = false;
		while(stop == false) {
			try{
				Thread.sleep(1000);
				getWait().until(ExpectedConditions.visibilityOf(element));
				stop = true;
			}catch(Exception e) {
				Thread.sleep(1000);
				stop = false;
				i++;
				if(i==maxAttempt) 
				{
					Assert.assertTrue(false,"failed to find element "+element);
					break;
				}
			}
		}
	}
	
	public static void Click(WebElement element) throws InterruptedException {
		int attempt = 1;
		while(attempt <= maxAttempt) {
			try {
				getWait().until(ExpectedConditions.elementToBeClickable(element));
				Thread.sleep(500);
				element.click();
				break;
			} catch(StaleElementReferenceException ex) {
				System.out.println("waitClickableThenClick StaleElementReferenceException");
				if (attempt == maxAttempt) {
					Assert.assertEquals(true, false, "Element cannot be clicked");
				}
			} catch(ElementClickInterceptedException ex) {
				System.out.println("waitClickableThenClick ElementClickInterceptedException");
				if (attempt == maxAttempt) {
					Assert.assertEquals(true, false, "Element cannot be clicked");
				}
			} catch(ElementNotInteractableException ex) {
				System.out.println("waitClickableThenClick ElementNotInteractableException");
				if (attempt == maxAttempt) {
					Assert.assertEquals(true, false, "Element cannot be clicked");
				}
			}
			attempt++;
		}
	}

}
