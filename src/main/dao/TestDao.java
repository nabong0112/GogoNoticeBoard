package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.vo.TestVo;

public class TestDao {
		
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
			 
			 			        
		        PreparedStatement pstmt;

		        try {
		        	Connection conn = null;
		        	conn = getConnection();
		        	
		            // Column
		            // PK , name , email , password
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
//		            try {
//		                if( conn != null ) {
//		                    conn.close();
//		                }
//		                if( pstmt != null ) {
//		                    pstmt.close();
//		                }
//		            }
//		            catch(SQLException e) {
//		                e.printStackTrace();
//		            }
		        }

	
		 }
	}

