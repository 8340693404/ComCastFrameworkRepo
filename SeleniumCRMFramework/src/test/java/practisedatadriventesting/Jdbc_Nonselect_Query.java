package practisedatadriventesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class Jdbc_Nonselect_Query {
	public static void main(String[] args)throws SQLException {
		// TODO Auto-generated method stub
		Driver driverRef= new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn =DriverManager.getConnection("Jdbc:mysql://localhost:3306/mithun", "root","root");
		Statement stat=conn.createStatement();
		int result = stat.executeUpdate("insert into tek value ('mithun','mishra','abc123@gmail.com','2024-02-22','80000.00')");
		System.out.println(result);

	
      conn.close();


}
}