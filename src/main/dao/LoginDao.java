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
		String sql = "select user_pw from testuser where user_id= ?";
		
		
		try {
			conn = getConnection();
			prep = conn.prepareStatement(sql);
			
			System.out.println("1");
			rs = prep.executeQuery(sql);
			
			prep.setString(1, vo.getUser_id());
			prep.setString(2, vo.getUser_pw());
			
			
			System.out.println("아오 다오 들어왔음2");
			//System.out.println(rs.getString("user_id"));
			
			while(rs.next()) {
				//if(/*rs.getString("user_id").equals() && */rs.getString("user_pw")!=null /*&& rs.getString("user_pw").equals(user_pw)*/) {
					
					System.out.println(rs.getString("user_id"));
					
					
					
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
