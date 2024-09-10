package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bases.BaseConfig;
import pages.MapLocationPage;

public class MeasureDistance extends BaseConfig{
	MapLocationPage MapLocationPage;
	String FilePath;
	
	public MeasureDistance() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		MapLocationPage = new MapLocationPage();
	}
	
	@Test
	public void drawLine() throws InterruptedException, IOException 
	{
		MapLocationPage.drawLine();
	}

}
