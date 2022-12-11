import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
	public void UploadDatabase(String name, int score) {
		
		//declare a connection and sql statement to execute the sql
		Connection conn = null;
		Statement stmt = null;
		
		//database commands have to be run within a try/catch
		try {
			//load the SQL driver
			Class.forName("org.sqlite.JDBC");
			
			//create a connection string and connect to DB
			String dbURL = "jdbc:sqlite:score.db";
			conn = DriverManager.getConnection(dbURL);
			
			if (conn!=null) {
				System.out.println("Connected to database");
				conn.setAutoCommit(false);
				
				//create statement to execute
				stmt = conn.createStatement();
				String sql = "INSERT INTO PLAYERS (NAME, SCORE) VALUES "
							 + "('"+name+"',"+score+")" ;
				//Execute the statement
				stmt.executeUpdate(sql);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
