import java.sql.*;

public class JDBCConnection extends SignUpSystem{
	
	JDBCConnection() throws Exception {
		
		String connectString = "jdbc:sqlserver://LAPTOP-CKUG71TD;Database=LogInSystem;IntegratedSecurity=false;trustServerCertificate=true;user=sa;password=Westlands01%";
		String salt = getSalt();

		try {
			
			try(Connection connection = DriverManager.getConnection(connectString)){
				
				String sql = "INSERT INTO [User] VALUES ('"+usernameField.getText()+"', '"+saltedHash(SignUpSystem.passwordField.getText(), salt)+"', '"+salt+"')";
				
				Statement statement = connection.createStatement();
				
				int rows = statement.executeUpdate(sql);
				
				connection.close();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
