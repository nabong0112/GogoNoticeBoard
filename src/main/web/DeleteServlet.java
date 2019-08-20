package main.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.dao.WriteDao;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WriteDao dao = new WriteDao();
		HttpSession session = request.getSession(false);
		
		int no =  Integer.parseInt(request.getParameter("board_no"));
		String user_id = request.getParameter("user_id");
		String session_id = (String) session.getAttribute("user_id");
		
		System.out.println(user_id);
		System.out.println((String) session.getAttribute("user_id"));
		if(user_id == null || !user_id.equals(session_id) || session_id == null) {
			response.sendRedirect("NoticeBoardServelet");
			System.out.println("비정상적으로 지움");
		} else {
		dao.deleteText(no);
		System.out.println("삭제 완료");
		response.sendRedirect("NoticeBoardServelet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
