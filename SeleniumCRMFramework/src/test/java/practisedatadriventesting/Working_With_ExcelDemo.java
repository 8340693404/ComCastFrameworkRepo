package practisedatadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Working_With_ExcelDemo {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		FileInputStream fis =new FileInputStream("C:\\Users\\ASUS\\Desktop\\Excel_DDT_Practise.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		Row row =sh.getRow(1);
		String data = row.getCell(2).toString();
		System.out.println(data);
		wb.close();
		
		

	}

}
