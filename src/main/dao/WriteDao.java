package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import main.vo.WriteVo;

public class WriteDao {

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
	
	 public void insertText(WriteVo vo) {		
		 	Connection conn = null;

	        try {
	        	
	        	conn = getConnection();
	        	
	            // Column
	            // 제목, 글 내용으로 추가
	            String sql = "insert into write values(?, ?);";
	            pstmt = conn.prepareStatement(sql);

	            //물음표의 순번, 적용할 값 삽입
	            pstmt.setString(1, vo.getTitle());
	            pstmt.setString(2, vo.getText());
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
	
	
}
