package www.ilab.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PositionSet
{

	File file = null;
	FileInputStream fis = null;
	Properties p = null;

	public PositionSet() 
	{
		file = new File("./File/Config.property");
		try {
			fis = new FileInputStream(file);
			p = new Properties();
			p.load(fis);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	public String sendurl() 
	{

		return p.getProperty("URL").toString();

	}
	public String objevalue()
	{
		return p.getProperty("objectValue");
	}
	public String sendname()
	{
		return p.getProperty("name");
	}
	public String sendemail()
	{
		return p.getProperty("email");
	}
	public String cellNumber()
	{
		String cellNumber = String.format("073"+" %03d %04d",(int) Math.floor(999*Math.random()),(int) Math.floor(9999*Math.random()));
		return cellNumber;
	}
	
}
