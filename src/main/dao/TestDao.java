package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.vo.TestVo;

public class TestDao { /*//거 ..회원관리dao 게시판 dao이런식으로 나눌수 있음 cud전용 r전용 여기는 db에 접근해서 쿼리를 실행시키는 담당
	//sql쿼리문을 실행시키는 객체
	 PreparedStatement pstmt;
	 Connection conn = null;
		ResultSet rs = null;
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
		
		public int idcheck(TestVo vo) { //아이디 중복 체크 dao
			int check = 0;
			Connection conn = null;
			ResultSet rs = null;
			
			try {
				
				conn = getConnection();
				String selectsql = "select user_id from testuser where user_id=?";
	        	pstmt = conn.prepareStatement(selectsql);
	        	
	        	pstmt.setString(1, vo.getUser_id());
	        	
	        	rs = pstmt.executeQuery();
	        	
	        	if(rs.next()) {
	        		check = 1;
	        		
	        	} else {
	        		
	        		check = 0;
	        	}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       	
			
			return check;
			
		}
		
		 public void insert(TestVo vo) { //회원가입 삽입 dao
			 	Connection conn = null;
		        try {
		        	conn = getConnection();	
		        		// Column
			            // 아이디, 비밀번호, 이름순으로 추가
			            String sql = "INSERT INTO testuser VALUES (?, ?, ?)";
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
		        
		 public int userCheck(TestVo vo, String user_id, String user_pw) { //로그인 체크
				String sql = "select * from testuser where user_id=?;";
				int ok = 0;
				
				try {
					
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, user_id);
					rs = pstmt.executeQuery();
					
						if(rs.next()) {
							if(rs.getString(2).equals(user_pw)) {
								
			            		System.out.println("로그인 성공");
			            		vo.setUser_id(rs.getString(1));
			            		vo.setUser_pw(rs.getString(2));
			            		vo.setUser_name(rs.getString(3));
			            		ok = 1;
			            		
			            	} else {
			            		ok = 0;
			            		System.out.println("비밀번호 확인");
			            		System.out.println(rs.getString(3));
			            		System.out.println(rs.getString(2) +" 와 "+ user_pw);
			            		}
						} else {
							ok = -1;
		            		System.out.println("아이디 또는 비밀번호를 확인0");
						}
//						if(rs.getString(1).equals(user_id) && rs.getString(2)!=null && rs.getString(2).equals(user_pw))
						
				}catch(SQLException e){
					e.printStackTrace();
				}finally {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				return ok;
			}*/
}

