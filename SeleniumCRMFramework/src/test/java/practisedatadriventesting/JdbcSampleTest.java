package practisedatadriventesting;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JdbcSampleTest {

	public static void main(String[] args)throws SQLException {
		// TODO Auto-generated method stub
		Driver driverRef= new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn =DriverManager.getConnection("Jdbc:mysql://localhost:3306/mithun", "root","root");
		Statement stat=conn.createStatement();
		ResultSet result=stat.executeQuery("select * from tek");
		while(result.next())
		
		System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t" + result.getString(4) + "\t" + result.getString(5));

	
      conn.close();
	}
}
