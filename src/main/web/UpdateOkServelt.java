package main.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.WriteDao;
import main.vo.WriteVo;

/**
 * Servlet implementation class UpdateOkServelt
 */
@WebServlet("/UpdateOkServelt")
public class UpdateOkServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOkServelt() {
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
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
		
		WriteDao dao = new WriteDao();
		WriteVo vo = new WriteVo();
		
		int board_no =  Integer.parseInt(request.getParameter("board_no"));
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		
		System.out.println(board_no);
		System.out.println(title);
		System.out.println(text);
		
		if(title.trim() == null || text.trim() == null) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('공백만 적으시면 안됩니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			
		dao.updateText(board_no, title, text);
		response.sendRedirect("noticeboard.jsp");
		}
	}
}
