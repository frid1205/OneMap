package testCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bases.BaseConfig;
import pages.MapLocationPage;
import utilization.Utilize;

public class SearchLocation extends BaseConfig{
	MapLocationPage MapLocationPage;
	String FilePath;
	
	public SearchLocation() {
		super();
	}
	
	@BeforeClass
	public void setUpBeforeClass() {
		MapLocationPage = new MapLocationPage();
	}
	
	@DataProvider(name = "data")
	public Object[][] getData() {
	    Object data[][] = Utilize.getDataFromExcel("\\testdata\\SearchAddress.xlsx");
	    return data;
	}
	
	@Test(dataProvider="data")
	public void searchLocation(String SearchAddress) throws InterruptedException 
	{
		MapLocationPage.testSearchLocation(SearchAddress);
	}
	
	@Test
	public void getMyLocation() throws InterruptedException, IOException 
	{
		MapLocationPage.getMyLocation();
	}
	
	@Test
	public void dragLocation() throws InterruptedException, IOException 
	{
		MapLocationPage.moveMapFromOneLocationToAnotherLocation();
	}

}
