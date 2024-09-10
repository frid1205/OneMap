package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bases.BaseConfig;
import pages.MapLocationPage;

public class ZoomInOut extends BaseConfig{
	MapLocationPage MapLocationPage;
	String FilePath;
	
	public ZoomInOut() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		MapLocationPage = new MapLocationPage();
	}
	
	@Test
	public void zoomIn() throws InterruptedException, IOException 
	{
		MapLocationPage.testZoomIn();
	}
	
	@Test
	public void zoomOut() throws InterruptedException, IOException 
	{
		MapLocationPage.testZoomOut();
	}
}
