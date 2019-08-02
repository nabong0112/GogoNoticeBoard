package main.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		WriteDao dao = new WriteDao();
		CommentVo vo = new CommentVo();
		dao.DeleteComment(board_no, vo, comment_no);

		out.println("<script type=\"text/javascript\">");
		out.println("alert('삭제되었습니다');");
		out.println("</script>");
		
		response.sendRedirect("ReadServlet?board_no=" + board_no);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
