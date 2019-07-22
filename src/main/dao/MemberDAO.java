package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
 //JDBC 프로그래밍 데이터베이스 테이블과 연동해서 작업하는 테이블에서 CURD를 하는 클래스
	
	//주된 역할은  데이터베이스 데이터를 vo객체로 얻어오거나 vo에 저장된 값을 데이터베이스에 추가한다.
	//싱글톤 패턴은 인스턴스가 오로지 단 하나만 존재할수 있도록 클래스를 설계하는 것
	
	//DB와 연결할 커넥션을 가지고온다. 어떤 두부를 사용할 것이며 어떤 드라이브와 로그인 정보를 사용할 것인가
	//작업이 끝나면 사용한 리소스를 시스템에 돌려준다.
	
	Connection conn = null;
	PreparedStatement prep = null;
	ResultSet rs = null;
			
	public Connection getConnection() throws SQLException {
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
	
	public void getmember(String user_id, String user_name){
		String sql = "select * from testuser where user_id=?;";
		
		try {
			
			conn = getConnection();
			prep = conn.prepareStatement(sql);
			
			prep.setString(1, user_id);
			rs = prep.executeQuery();
			
				while(rs.next()) {
					
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
		
	}
}
