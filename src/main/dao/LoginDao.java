package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import main.vo.TestVo;

public class LoginDao {
	Connection conn = null;
	PreparedStatement prep = null;
	ResultSet rs = null;
	/////////////////////////////////////////////////////////MemberDAO로 가세요////////////////////////////////////////////
	private static LoginDao instance = new LoginDao();
	
	public static LoginDao getInstance() {
		
		return instance;
		
	}
	
	 Connection getConnection() throws SQLException {
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
	
	public int userCheck(TestVo vo, String user_id, String user_pw) {
		String sql = "select * from testuser where user_id=?;";
		int ok = 0;
		
		try {
			
			conn = getConnection();
			prep = conn.prepareStatement(sql);
			
			prep.setString(1, user_id);
			rs = prep.executeQuery();
			
				while(rs.next()) {
					if(user_pw == null && user_id == null) {
						
						ok = -1;
						System.out.println("DAO : 아이디 또는 비밀번호에 값이 없음 -1");
						
					}else if(rs.getString(2).equals(user_pw)) {
	            		vo.setUser_id(rs.getString(1));
	            		vo.setUser_pw(rs.getString(2));
	            		vo.setUser_name(rs.getString(3));
	            		ok = 1;
	            		
	            	} else {
	            		
	            		ok = 0;
	            		System.out.println("DAO : 아이디 또는 비밀번호를 확인");
	            		System.out.println(rs.getString(3));
	            	}
				}
//				if(rs.getString(1).equals(user_id) && rs.getString("user_pw")!=null && 
//				rs.getString("user_pw").equals(user_pw)) {
				
		}catch(SQLException e){
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
		return ok;
	}
	
	
	
	
	
	
	
	
	
	
}
