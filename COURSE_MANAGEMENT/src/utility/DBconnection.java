package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	
	private static final String url="jdbc:mysql://localhost:3306/course_management"; 
	private static final String username="root";
	private static final String password="Shreeram@2706";
    
    public static Connection getConnection() throws SQLException
    {
    	return DriverManager.getConnection(url, username, password);
    }
    
    public static void closeConnection (Connection con)
    {
    	if(con!=null)
    	{
    		try {
    			con.close();
    		}
    		catch(SQLException e)
    		{
    			System.out.println("Error in closing connection "+e.getMessage());
    		}
    	}
    }
}
