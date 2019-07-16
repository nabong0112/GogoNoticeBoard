package main.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.TestDao;
import main.vo.TestVo;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet") //회원가입폼으로 이동해서 값을 입력하면 데이터베이스에 저장하는 일
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//요청받아서 jsp를 뿜어내는 역할
		request.setCharacterEncoding("utf-8");		
		
		TestVo vo = new TestVo();
		
		vo.setUser_id(request.getParameter("user_id"));
		vo.setUser_pw(request.getParameter("user_pw"));
		vo.setUser_name(request.getParameter("user_name"));
		
		TestDao dao = new TestDao();
		//클라이언트가 입력한 데이터를 넘겨줌
		dao.insert(vo);
		//join폼으로 이동해서 값을 받아야됨
		response.sendRedirect("/Nabong_writer/loginform.jsp"); //거 폼에 들어갈라면 서블릿을 호출해야도미ㅋㅋㅋ
		//("/Nabong_writer/login.jsp");
//		RequestDispatcher rd = request.getRequestDispatcher("/Nabong_writer/noitceboard.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
