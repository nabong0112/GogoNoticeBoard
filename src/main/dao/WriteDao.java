package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

import main.vo.WriteVo;

public class WriteDao {

	PreparedStatement pstmt;
	 
	//prep = conn.prepareStatement("insert into testuser(user_id, user_pw, user_name) value(?, ?, ?)");
	
	/*
	============================================================================================================================= 
	===============================================데이터베이스와 연동하기위해 적은 곳========================================================
	============================================================================================================================= 
	*/ 

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
/*
	============================================================================================================================= 
	===============================================글 작성후 데이터베이스에 저장============================================================ 
	============================================================================================================================= 
*/ 
	 public void insertText(WriteVo vo) {	//게시판 데이터베이스에 글 저장	
		 	Connection conn = null;

	        try {
	        	
	        	conn = getConnection();
	        	
	            // Column
	            // 제목, 글 , 아이디 순으로 추가
	            String sql = "insert into noticeboard (board_title, board_text, board_user) values(?, ?, ?);";
	            pstmt = conn.prepareStatement(sql);

	            //물음표의 순번, 적용할 값 삽입
	           // pstmt.setString(1, null);
	            pstmt.setString(1, vo.getBoard_title());
	            pstmt.setString(2, vo.getBoard_text());
	            pstmt.setString(3, vo.getBoard_user());
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
	 /*
	============================================================================================================================= 
	===============================================게시판에 글 불러오기 위한 Dao=========================================================== 
	============================================================================================================================= 
		*/ 
	 public ArrayList<WriteVo> selectText() { //여기는 게시판 잡는 곳
		 
		 ArrayList<WriteVo>border = new ArrayList<WriteVo>();
		 Connection conn = null;
		 ResultSet rs = null;
	        try {
	        	
	        	conn = getConnection();
	        	
	            // Column
	            // 제목, 글 내용으로 추가
	            String sql = "SELECT * FROM noticeboard ORDER BY board_no DESC";
	            
	            pstmt = conn.prepareStatement(sql);
	            
	            rs = pstmt.executeQuery();
	            

	            while(rs.next()) {
	            	WriteVo vo = new WriteVo();
	            	vo.setBoard_no(rs.getInt(1));
	            	vo.setBoard_title(rs.getString(2));
	            	vo.setBoard_text(rs.getString(3));
	            	vo.setBoard_user(rs.getString(4));
	            	vo.setBoard_time(rs.getString(5));
	            	vo.setBoard_view(rs.getInt(6));
	            	border.add(vo);
	            }
	            
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
			return border;
	 }
	/*
	============================================================================================================================= 
	===============================================글목록 불러오는 Dao================================================================= 
	============================================================================================================================= 
	*/ 
	 public WriteVo getBoard(int board_no) { //글목록 불러오기
		int ok = 0;
		 Connection conn = null;
		 ResultSet rs = null;
		 WriteVo vo = new WriteVo();
		 String sql = "SELECT * FROM noticeboard where board_no=?";
		 
	        try {
	        	
	        	conn = getConnection();	
	            
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setInt(1, board_no);
	            
	            rs = pstmt.executeQuery();
	        	
	        	if(rs.next()) {
	        		ok = 1;
	            	vo.setBoard_no(rs.getInt(1));
	            	vo.setBoard_title(rs.getString(2));
	            	vo.setBoard_text(rs.getString(3));
	            	vo.setBoard_user(rs.getString(4));
	            	vo.setBoard_time(rs.getString(5));
	            	vo.setBoard_view(rs.getInt(6));	
	            	
	            	String text = vo.getBoard_text();
	            	System.out.println("========dao구간========");
	            	System.out.println(vo.getBoard_no());
	            	System.out.println(text);
	            	System.out.println(vo.getBoard_title());
	            	System.out.println(vo.getBoard_user());
	            	System.out.println(vo.getBoard_time());
	            	System.out.println(vo.getBoard_view());
	            	System.out.println("========dao구간 끝========");
	            	
	            	
	        	} else {
	        		
	        		ok = -1;
	        		
	        	}
	        	
	        	
	        }catch(SQLException e){
	        	e.printStackTrace();
	        }finally {
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
	        return vo;
	 }
	 
	 /*
		============================================================================================================================= 
		===============================================글 조회수 증가시키는 Dao================================================================= 
		============================================================================================================================= 
		*/  
	 
	 public void view(int board_no) { //조회수
		
		 Connection conn = null;
	        try {
	        	conn = getConnection();
	        	
	        	String sql = "Update noticeboard set board_view = board_view + 1 where board_no = ?";
	        	
	        	pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setInt(1, board_no);
	            
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
	 /*
		============================================================================================================================= 
		===============================================글 수정하는 Dao=================================================================== 
		============================================================================================================================= 
		*/  
	 
	 public void updateText(int board_no, String title, String text) {
		 Connection conn = null;
	        try {
	        	conn = getConnection();
	        	
	        	String sql = "Update noticeboard set board_title =?, board_text =? where board_no = ?";
	        	
	        	pstmt = conn.prepareStatement(sql);
	            
	        	pstmt.setString(1, title);
	        	pstmt.setString(2, text);
	            pstmt.setInt(3, board_no);
	            
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
	 
	 /*
		============================================================================================================================= 
		===============================================글 삭제하는 Dao=================================================================== 
		============================================================================================================================= 
		*/   
	 
	 public void deleteText(int board_no) {
		 
		 Connection conn = null;
		 
	        try {
	        	conn = getConnection();
	        	
	        	String sql = "Delete from noticeboard where board_no =?";
	        	
	        	pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setInt(1, board_no);
	            
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
	 /*
		============================================================================================================================= 
		==============================================번호 정렬하는 Dao=================================================================== 
		============================================================================================================================= 
		*/ 
	 public void incrementSort(int board_no) {// 이건 내일이나 하자.............................
		 Connection conn = null;
	        try {
	        	conn = getConnection();
	        	
	        	String sql = "alter table noticeboard auto_increment= ?;";
	        	
	        	pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setInt(1, board_no-1);
	            
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
