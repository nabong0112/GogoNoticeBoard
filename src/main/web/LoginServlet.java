package main.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 생성
		HttpSession session = request.getSession();
		
		
		//이게 값을 가지고온건가?
		String login_id = request.getParameter("user_id");
		String login_pw = request.getParameter("user_pw");
		
		TestVo vo = new TestVo();
		
		vo.setUser_id(login_id);
		vo.setUser_pw(login_pw);
		//vo.getUser_pw();
		
		String loginid = vo.getUser_id();
		String loginpw = vo.getUser_pw();
		
		System.out.println("로그인창에서 입력한 정보");
		System.out.println(loginid);
		System.out.println(loginpw);
		System.out.println(login_id);
		System.out.println(login_pw);
		
		LoginDao dao = new LoginDao();
		
		
		dao.userCheck(vo);
		try {
			
			if(loginid.equals(login_id)) {
				System.out.println("뭐여 서블렛 들어왔음");
				System.out.println(loginid + " 값이 똑같음 " + login_id);
				System.out.println("아이디는 " + login_id);
				System.out.println("비밀번호는 " + login_pw );
			} else { //값이 다름
				System.out.println(vo.getUser_id() + " 값이 다름 vo에서 얻어온 값이 없음");
				System.out.println("jsp에서 얻어온 아이디는 " + login_id);
				System.out.println("비밀번호는 " + login_pw);
			}
			
		} catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println(vo.getUser_id() + "  vo.getUser_id() 의 값이 없음");
			
		}
		
		//세션 만들기
				session.setAttribute("user_id", loginid);
				
				String str = (String)session.getAttribute("user_id");
				
				System.out.println("세션에서 얻어오 ㄴ아이ㅣ디는 " + str);
		
		RequestDispatcher rd = request.getRequestDispatcher("NoticeBoardServelet");
		rd.forward(request, response);
		
		
		
	}

}
