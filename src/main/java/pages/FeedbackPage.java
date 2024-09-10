package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bases.BaseConfig;
import utilization.Use;

public class FeedbackPage extends BaseConfig {
	public FeedbackPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
    @FindBy(xpath="//div[@class=\"wog--tabbed-button wog--tabbed-button-bottom-left\"]")
    WebElement feedbackButton;
    
    public void test() throws InterruptedException {
    	//Use.Click(feedbackButton);
    	feedbackButton.click();
    }
}
