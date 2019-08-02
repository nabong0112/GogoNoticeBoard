package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.vo.CommentVo;
import main.vo.WriteVo;

public class WriteDao {

	PreparedStatement pstmt;

	// prep = conn.prepareStatement("insert into testuser(user_id, user_pw,
	// user_name) value(?, ?, ?)");

	/*
	 * =============================================================================
	 * ================================================
	 * ===============================================데이터베이스와 연동하기위해 적은
	 * 곳========================================================
	 * =============================================================================
	 * ================================================
	 */

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 드라이버의 클래스 이름
			Class.forName("org.mariadb.jdbc.Driver");

			// driver manager로 데이터베이스에 연결
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/TestDB", "root", "1234");
		} catch (ClassNotFoundException e) {
			// 드라이버의 클래스를 찾지 못했을 경우
			System.out.println(" 드라이버 로딩 실패 ");
		}
		// 연결한것을 반환시킴
		return conn;
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ===============================================글 작성후 데이터베이스에
	 * 저장============================================================
	 * =============================================================================
	 * ================================================
	 */
	public void insertText(WriteVo vo) { // 게시판 데이터베이스에 글 저장
		Connection conn = null;

		try {

			conn = getConnection();

			// Column
			// 제목, 글 , 아이디 순으로 추가
			String sql = "insert into noticeboard (board_title, board_text, board_user) values(?, ?, ?);";
			pstmt = conn.prepareStatement(sql);

			// 물음표의 순번, 적용할 값 삽입
			// pstmt.setString(1, null);
			pstmt.setString(1, vo.getBoard_title());
			pstmt.setString(2, vo.getBoard_text());
			pstmt.setString(3, vo.getBoard_user());
			// select문은 excutequery를 사용해야함
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ===============================================게시판에 글 불러오기 위한
	 * Dao===========================================================
	 * =============================================================================
	 * ================================================
	 */
	public ArrayList<WriteVo> selectText() { // 여기는 게시판 잡는 곳

		ArrayList<WriteVo> border = new ArrayList<WriteVo>();
		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			// Column
			// 제목, 글 내용으로 추가
			String sql = "SELECT * FROM noticeboard ORDER BY board_no DESC";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				WriteVo vo = new WriteVo();
				vo.setBoard_no(rs.getInt(1));
				vo.setBoard_title(rs.getString(2));
				vo.setBoard_text(rs.getString(3));
				vo.setBoard_user(rs.getString(4));
				vo.setBoard_time(rs.getString(5));
				vo.setBoard_view(rs.getInt(6));
				border.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return border;
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ===============================================게시판 글 검색
	 * dao==================================================================
	 * =============================================================================
	 * ================================================
	 */
	public ArrayList<WriteVo> search(int searchType, String searchName) {

		ArrayList<WriteVo> search = new ArrayList<WriteVo>();
		ResultSet rs = null;
		Connection conn = null;

		try {

			conn = getConnection();

			// Column 0은 제목 1은 내용 2는 제목이랑 내용 3은 글쓴이
			if (searchType == 0) {

				String sql = "select * from noticeboard where board_title like '%" + searchName
						+ "%' ORDER BY board_no DESC;";
				pstmt = conn.prepareStatement(sql);

				// pstmt.setString(1, searchName);

				rs = pstmt.executeQuery();

			} else if (searchType == 1) {
				String sql = "select * from noticeboard where board_text like '%" + searchName
						+ "%' ORDER BY board_no DESC;";
				pstmt = conn.prepareStatement(sql);

				// pstmt.setString(1, searchName);

				rs = pstmt.executeQuery();

			} else if (searchType == 2) {
				String sql = "select * from noticeboard where board_title like '%" + searchName
						+ "%' or board_text like '%" + searchName + "%' ORDER BY board_no DESC;";
				pstmt = conn.prepareStatement(sql);

				// pstmt.setString(1, searchName);

				rs = pstmt.executeQuery();

			} else if (searchType == 3) {
				String sql = "select * from noticeboard where board_user = '" + searchName
						+ "' ORDER BY board_no DESC;";
				pstmt = conn.prepareStatement(sql);

				// pstmt.setString(1, searchName);

				rs = pstmt.executeQuery();
			}

			while (rs.next()) {
				WriteVo vo = new WriteVo();
				vo.setBoard_no(rs.getInt(1));
				vo.setBoard_title(rs.getString(2));
				vo.setBoard_text(rs.getString(3));
				vo.setBoard_user(rs.getString(4));
				vo.setBoard_time(rs.getString(5));
				vo.setBoard_view(rs.getInt(6));
				search.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return search;
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ===============================================글목록 불러오는
	 * Dao=================================================================
	 * =============================================================================
	 * ================================================
	 */
	public WriteVo getBoard(int board_no) { // 글목록 불러오기
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

			if (rs.next()) {
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ===============================================글 조회수 증가시키는
	 * Dao=================================================================
	 * =============================================================================
	 * ================================================
	 */

	public void view(int board_no) { // 조회수

		Connection conn = null;
		try {
			conn = getConnection();

			String sql = "Update noticeboard set board_view = board_view + 1 where board_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ==============================================댓글 불러오는
	 * Dao===================================================================
	 * =============================================================================
	 * ================================================
	 */
	public ArrayList<CommentVo> SelectComment(int board_no) {
		ArrayList<CommentVo> comm = new ArrayList<CommentVo>();

		Connection conn = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			// Column
			// 제목, 글 내용으로 추가
			String sql = "SELECT board_no, comment_no, comment_user, comment_text, comment_time FROM comment where board_no =?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentVo vo = new CommentVo();
				vo.setBoard_no(rs.getInt(1));
				vo.setComment_no(rs.getInt(2));
				vo.setComment_user(rs.getString(3));
				vo.setComment_text(rs.getString(4));
				vo.setComment_time(rs.getString(5));
				// vo.setComment_re_no(rs.getInt(6));
				// vo.setComment_re_user(rs.getString(7));
				// vo.setComment_re_text(rs.getString(8));
				// vo.setComment_re_time(rs.getString(9));
				comm.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comm;
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ==============================================댓글 작성해서 저장하는
	 * Dao=============================================================
	 * =============================================================================
	 * ================================================
	 */
	public int InsertComment(int board_no, CommentVo vo) {
		Connection conn = null;
		int comment = 1;
		try {

			conn = getConnection();

			// Column
			// 제목, 글 , 아이디 순으로 추가
			String sql = "insert into comment (board_no, comment_user, comment_text) values(?, '" + vo.getComment_user()
					+ "', '" + vo.getComment_text() + "');";
			pstmt = conn.prepareStatement(sql);

			// 물음표의 순번, 적용할 값 삽입
			// pstmt.setString(1, null);
			pstmt.setInt(1, board_no);

			// select문은 excutequery를 사용해야함
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comment;
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ==============================================댓글 수정하는
	 * Dao=============================================================
	 * =============================================================================
	 * ================================================
	 */
	public void UpdateComment(int board_no, CommentVo vo, String comment) {
		Connection conn = null;
		try {
			conn = getConnection();

			String sql = "Update comment set board_title =?, board_text =? where board_no = ?";

			pstmt = conn.prepareStatement(sql);

			// pstmt.setString(1, title);
			// pstmt.setString(2, text);
			pstmt.setInt(3, board_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ==============================================댓글 삭제하는
	 * Dao=============================================================
	 * =============================================================================
	 * ================================================
	 */
	

	public void DeleteComment(int board_no, CommentVo vo, int comment_no) {
		Connection conn = null;
		try {
			conn = getConnection();

			String sql = "delete from comment where comment_no = " + comment_no;

			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ==============================================답글 작성해서 저장하는
	 * Dao=============================================================
	 * =============================================================================
	 * ================================================
	 */
	public int InsertReComment(int board_no, CommentVo vo) {
		Connection conn = null;
		int comment = 1;
		try {

			conn = getConnection();

			// Column
			// 제목, 글 , 아이디 순으로 추가
			String sql = "insert into comment (board_no, comment_no, comment_user, comment_text) values(?, '"
					+ vo.getComment_user() + "', '" + vo.getComment_text() + "');";
			pstmt = conn.prepareStatement(sql);

			// 물음표의 순번, 적용할 값 삽입
			// pstmt.setString(1, null);
			pstmt.setInt(1, board_no);

			// select문은 excutequery를 사용해야함
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comment;
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ===============================================글 수정하는
	 * Dao===================================================================
	 * =============================================================================
	 * ================================================
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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ===============================================글 삭제하는
	 * Dao===================================================================
	 * =============================================================================
	 * ================================================
	 */

	public void deleteText(int board_no) {

		Connection conn = null;

		try {
			conn = getConnection();

			String sql = "Delete from noticeboard where board_no =?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("dao 완료");
	}

	/*
	 * =============================================================================
	 * ================================================
	 * ==============================================번호 정렬하는
	 * Dao===================================================================
	 * =============================================================================
	 * ================================================
	 */
	public void incrementSort(int board_no) {// 이건 내일이나 하자.............................
		Connection conn = null;
		try {
			conn = getConnection();

			String sql = "alter table noticeboard auto_increment= ?;";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, board_no - 1);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
