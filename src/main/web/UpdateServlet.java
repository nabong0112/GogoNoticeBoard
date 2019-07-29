package main.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.WriteDao;
import main.vo.WriteVo;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WriteDao dao = new WriteDao();
		/* 서블릿에서 javascript사용할때 쓰이는것
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		*/
		int board_no =  Integer.parseInt(request.getParameter("board_no"));
		
		WriteVo vo = dao.getBoard(board_no);
		dao.getBoard(board_no);
		
		String user = vo.getBoard_user();
		String title = vo.getBoard_title();
		String text = vo.getBoard_text();
		
		System.out.println(text);
		System.out.println(title);
		System.out.println(user);
		
		request.setAttribute("user", user);
		request.setAttribute("title", title);
		request.setAttribute("text", text);
		request.setAttribute("board_no", board_no);
		RequestDispatcher rd = request.getRequestDispatcher("/Updateform.jsp?board_no=" + board_no);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*if(title.trim() == null || text.trim() == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('공백만 적으시면 안됩니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
		dao.updateText(no, title, text);
		response.sendRedirect("noticeboard.jsp");
		}*/
	}

}
