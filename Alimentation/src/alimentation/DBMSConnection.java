package alimentation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class DBMSConnection {
	private static Connection c;
	private static String url ="jdbc:oracle:thin:@localhost:1521:xe";
	private static String user="root";
	private static String password="root";
	private static String driver="oracle.jdbc.driver.OracleDriver";
	    public static Connection getConnection() {return c;}
	    public DBMSConnection(){  
	        try{  
	            Class.forName(driver);   
	            c = DriverManager.getConnection(url, user, password);
	           // s =c.createStatement();  
	        }catch(Exception e){ 
	            e.printStackTrace();
	        }  
	    }
		/*
		Statement s;
		public PreparedStatement prepareStatement(String sql) {
			// TODO Auto-generated method stub
			return null;
		}  */
	}  