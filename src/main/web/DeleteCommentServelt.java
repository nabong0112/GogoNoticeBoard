package main.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.dao.WriteDao;
import main.vo.CommentVo;

/**
 * Servlet implementation class DeleteCommentServelt
 */
@WebServlet("/DeleteCommentServelt")
public class DeleteCommentServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommentServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//false를 하면 세션이 없을때 새롭게 세션을 안만들고 null로 반환해서 null체크를 해야함
		HttpSession session = request.getSession(false);
		
		WriteDao dao = new WriteDao();
		CommentVo vo = new CommentVo();
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		//댓글 쓴 아이디
		String comment_id = request.getParameter("comment_id");
		//세션에 있는 아이디
		String session_id = (String)session.getAttribute("user_id");
		
		if(comment_id == null ||session_id == null || !comment_id.equals(session_id)) {
			response.sendRedirect("NoticeBoardServelet");
			System.out.println(comment_id +"댓글단 아이디");
			System.out.println(session_id + "세션에 있는 아이디");
			System.out.println("잘못된 댓글 삭제");
		} else {
		System.out.println(comment_id +"의 댓글 삭제 성공");
		dao.DeleteComment(board_no, vo, comment_no);
		
		response.sendRedirect("ReadServlet?board_no=" + board_no);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
