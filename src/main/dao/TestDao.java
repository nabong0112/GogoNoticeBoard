package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.vo.TestVo;

public class TestDao { //거 ..회원관리dao 게시판 dao이런식으로 나눌수 있음 cud전용 r전용
	//sql쿼리문을 실행시키는 객체
	 PreparedStatement pstmt;
	 //처리된 레코드의 값을 테이블의 형태로  담는 객체 select문에 사용됨 rs에 꼭 저장해야함
	private ResultSet rs;
		//prep = conn.prepareStatement("insert into testuser(user_id, user_pw, user_name) value(?, ?, ?)");
	
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
		
		 public void insert(TestVo vo) {		
			 	Connection conn = null;

		        try {
		        	
		        	conn = getConnection();
		        	
		            // Column
		            // 아이디, 비밀번호, 이름순으로 추가
		            String sql = "INSERT INTO testuser VALUES (?, ?, ?);";
		            pstmt = conn.prepareStatement(sql);

		            pstmt.setString(1, vo.getUser_id());
		            pstmt.setString(2, vo.getUser_pw());
		            pstmt.setString(3, vo.getUser_name());
		            pstmt.executeUpdate();

		            }
		        catch (SQLException e) {
		            e.printStackTrace();
		        }
		        finally {
     //						try catch문에 넣ㅇ어어ㅑ됨
//		                    conn.close();
//		                
//		       
//		                    pstmt.close();
//		                
//		        
//		           
		        }
		 }
		        
		        public void select(TestVo vo) {
					 
 			        
			       // PreparedStatement pstmt;

			        try {
			        	Connection conn = null;
			        	conn = getConnection();
			        	
			            // Column
			            // PK , name , email , password
			            String sql = "select user_pw from testuser where user_id=?;";
			            pstmt = conn.prepareStatement(sql);
			            rs = pstmt.executeQuery();
			           // pstmt.executeUpdate();	      
			            

			            }
			        catch (SQLException e) {
			            e.printStackTrace();
			        }
			        finally {
//			            try {
//			                if( conn != null ) {
//			                    conn.close();
//			                }
//			                if( pstmt != null ) {
//			                    pstmt.close();
//			                }
//			            }
//			            catch(SQLException e) {
//			                e.printStackTrace();
//			            }
			        }

	
		 }
}

