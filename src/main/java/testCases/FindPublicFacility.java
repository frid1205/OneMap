package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bases.BaseConfig;
import pages.MapLocationPage;

public class FindPublicFacility extends BaseConfig{
	MapLocationPage MapLocationPage;
	String FilePath;
	
	public FindPublicFacility() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		MapLocationPage = new MapLocationPage();
	}
	
	@Test
	public void findMedicalLocation() throws InterruptedException, IOException 
	{
		MapLocationPage.findMedicalLocation();
	}
	
}
