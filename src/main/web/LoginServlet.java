package main.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.LoginDao;
import main.dao.TestDao;
import main.vo.TestVo;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String login_id = request.getParameter("user_id");
		String login_pw = request.getParameter("user_pw");
		

		
		TestVo vo = new TestVo();
		
		vo.getUser_id();
		vo.getUser_pw();
		
		String loginid = vo.getUser_id();
		System.out.println(loginid);
		
		
		LoginDao dao = new LoginDao();
		
		dao.userCheck(vo);
		
		response.sendRedirect("/Nabong_writer/loginform.jsp");
		if(vo.getUser_id() == login_id) {
			System.out.println("뭐여 서블렛 들어왔음");
			System.out.println(vo.getUser_id() + " 값이 똑같음");
			System.out.println("아이디는 " + login_id);
			System.out.println("비밀번호는 " + login_pw );
			dao.userCheck(vo);
			response.sendRedirect("/Nabong_writer/loginform.jsp");
			//ㅇㅣ게 나오는걸로봐서는..아이디를 못얻어오는거같음 아이디는 얻어오는데 ㅎ..
		} else { //값이 다름
			System.out.println(vo.getUser_id() + " 값이 다름 vo에서 얻어온 값이 없음");
			System.out.println("jsp에서 얻어온 아이디는 " + login_id);
			System.out.println("비밀번호는 " + login_pw);
			//데이터베이스에 있는걸 못가지고옴
			dao.userCheck(vo);
			response.sendRedirect("/Nabong_writer/loginform.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
		
		
	}

}
