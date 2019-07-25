package main.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.dao.WriteDao;
import main.vo.WriteVo;

/**
 * Servlet implementation class ReadServlet
 */
@WebServlet("/ReadServlet")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//여기는 일단 jsp에서 번호를 불러와 그걸 저장해서 dao로 보내 그일을 해야됨 그러고 넘겨서 맞으면 출력하게
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		WriteDao dao = new WriteDao();
		WriteVo vo = new WriteVo();
		int no =  Integer.parseInt(request.getParameter("board_no"));
		int num;
		int view;
		String title;
		String text;
		String user;
		String time;
		
		System.out.println(no);
		
		dao.getBoard(no);
		
			text = vo.getBoard_text();
			title = vo.getBoard_title();
			user = vo.getBoard_user();
			time = vo.getBoard_time();
			view = vo.getBoard_view();
			num = vo.getBoard_no();
			System.out.println("\"/Nabong_writer/Readform.jsp?board_no=\" + num");
			response.sendRedirect("/Nabong_writer/Readform.jsp?board_no=" + no);
			
			/*	out.println("<script type=\"text/javascript\">");
			out.println("alert('글이 존재하지 않습니다!');");
			out.println("location.href='noticeboard.jsp';");
			out.println("</script>");*/
		
			
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
