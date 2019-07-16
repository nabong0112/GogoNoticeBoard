package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.vo.TestVo;

public class TestDao { //거 ..회원관리dao 게시판 dao이런식으로 나눌수 있음 cud전용 r전용 여기는 db에 접근해서 쿼리를 실행시키는 담당
	//sql쿼리문을 실행시키는 객체
	 PreparedStatement pstmt;
	 
		//prep = conn.prepareStatement("insert into testuser(user_id, user_pw, user_name) value(?, ?, ?)");
	
	private Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
        	//드라이버의 클래스 이름
            Class.forName("org.mariadb.jdbc.Driver");

            //driver manager로 데이터베이스에 연결
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/TestDB", "root", "1234");
        }
        catch (ClassNotFoundException e) {
        	//드라이버의 클래스를 찾지 못했을 경우
            System.out.println(" 드라이버 로딩 실패 ");
        }
        //연결한것을 반환시킴
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

		            //물음표의 순번, 적용할 값 삽입
		            pstmt.setString(1, vo.getUser_id());
		            pstmt.setString(2, vo.getUser_pw());
		            pstmt.setString(3, vo.getUser_name());
		            //select문은 excutequery를 사용해야함
		            pstmt.executeUpdate();

		            }
		        catch (SQLException e) {
		            e.printStackTrace();
		        }
		        finally {
		        	try {
		                if( conn != null ) {
			                 conn.close();
				                }
				                if( pstmt != null ) {
				                    pstmt.close();
				                }
				            }
				            catch(SQLException e) {
				                e.printStackTrace();
				            }	           
		        }
		 }
		        
		        public void select(TestVo vo) { //-----------------아직 수정중---------------------//
		        	
		        	Connection conn = null;
		        	//처리된 레코드의 값을 테이블의 형태로  담는 객체 select문에 사용됨 rs에 꼭 저장해야함
		        	ResultSet rs = null; 
 			        
			       // PreparedStatement pstmt;

			        try {
			        	
			        	conn = getConnection();
			        	
			            // Column
			            // PK , name , email , password
			            String sql = "select * from testuser where user_id= '?' and user_pw= '?'";
			            pstmt = conn.prepareStatement(sql);
			            rs = pstmt.executeQuery();  
			            

			            }
			        catch (SQLException e) {
			            e.printStackTrace();
			        }
			        finally {
			            try {
	                if( conn != null ) {
		                    conn.close();
			                }
			                if( pstmt != null ) {
			                    pstmt.close();
			                }
			            }
			            catch(SQLException e) {
			                e.printStackTrace();
			            }
			        }

	
		 }
}

