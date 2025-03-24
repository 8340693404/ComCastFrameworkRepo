package practisedatadriventesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Working_With_Excel1 {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("C:\\Users\\ASUS\\Desktop\\Excel_DDT_Practise.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh =wb.getSheet("Sheet1");
		int rowcount =sh.getLastRowNum();
		for(int i =1;i<=rowcount;i++)
		{
			Row row=sh.getRow(i);
			String coldata1=row.getCell(0).toString();
			String coldata2=row.getCell(1).toString();
			System.out.println(coldata1+ "\t" +coldata2);
		}

	}

}
