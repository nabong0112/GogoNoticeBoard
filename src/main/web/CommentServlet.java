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
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		WriteDao dao = new WriteDao();
		CommentVo vo = new CommentVo();
		//댓글이랑 이름 나옴ㅇㅇ글번호도 나옴
		String comment = request.getParameter("comment");
		String user_id = (String)session.getAttribute("user_id"); 
		String recomment = request.getParameter("recomment");
		String comment_rs = comment.trim();
		if(user_id == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('비회원은 댓글을 작성하실 수 없습니다!');");
			out.println("location.href = 'loginform.jsp';");
			out.println("</script>");
		} else if(comment_rs.length() == 0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('댓글을 입력해 주세요');");
			out.println("history.back();");
			out.println("</script>");
		}
		vo.setBoard_no(board_no);
		vo.setComment_user(user_id);
		vo.setComment_text(comment);
		
		System.out.println("작성자 : " + user_id);
		System.out.println("댓글" + comment);
		System.out.println("댓글" + comment_rs);
		System.out.println("글 번호 " + board_no);
		
		if (!comment.isEmpty()){
			dao.InsertComment(board_no, vo);
		} else if(!recomment.isEmpty()){
			dao.InsertReComment(board_no, vo);
		}
		
		dao.SelectComment(board_no);
		
		response.sendRedirect("ReadServlet?board_no=" + board_no);
		
		/*RequestDispatcher rd = request.getRequestDispatcher("/ReadServlet?board_no=" + board_no);
		rd.forward(request, response);*/
	}

}
