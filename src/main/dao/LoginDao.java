package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.vo.TestVo;

public class LoginDao {
	Connection conn = null;
	PreparedStatement prep = null;
	ResultSet rs = null;
	
	private Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

        
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/TestDB", "root", "1234");
        }
        catch (ClassNotFoundException e) {
            System.out.println(" 드라이버 로딩 실패 ");
        }

        return conn;
    }
	
	public void userCheck(TestVo vo) {
		String sql = "select * from testuser where user_id= '?' and user_pw= '?'";
		
		
		try {
			String user_id = null;
			String user_pw = null;
			String user_name = null;
			
			conn = getConnection();
			prep = conn.prepareStatement(sql);
			
			System.out.println("1");
			
			
			prep.setString(1, vo.getUser_id());
			prep.setString(2, vo.getUser_pw());
			prep.setString(3, vo.getUser_name());
			System.out.println(user_id);
			System.out.println(user_pw);
			System.out.println(user_name);
			
			rs = prep.executeQuery(sql);
			
			
			//prep.setString(3, vo.getUser_name());
			//System.out.println(rs.getString("user_id"));
			
			while(rs.next()) {
				//if(rs.getString("user_id").equals() && rs.getString("user_pw")!=null && rs.getString("user_pw").equals(user_pw)) {
					
				user_id = rs.getString("user_id");
				user_pw = rs.getString("user_pw");
				user_name = rs.getString("user_name");		
				System.out.println(user_id + user_pw + user_name);
					
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(prep != null) prep.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		//return 0;
	}
}
