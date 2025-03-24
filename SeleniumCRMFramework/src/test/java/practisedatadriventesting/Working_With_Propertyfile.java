package practisedatadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Working_With_Propertyfile {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\Desktop\\SelProperty.properties");
		Properties pObj= new Properties();
		pObj.load(fis);
		System.out.println(pObj.getProperty("Browser"));

	}

}
