package main.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;

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
		
		
		//이게 값을 가지고온건가? 이게 로그인창에서 얻어온 정보
		String login_id = request.getParameter("insert_id");
		String login_pw = request.getParameter("insert_pw");
		
		TestVo vo = new TestVo();
		LoginDao dao = new LoginDao();
		
		//usercheck의 리턴값으로 어떻게 할지 정할거임
		int ok = dao.userCheck(vo, login_id, login_pw);
		
		
		//vo.getUser_pw();	
		
		System.out.println("로그인창에서 입력한 정보");
		System.out.println(login_id);
		System.out.println(login_pw);	
		
		dao.userCheck(vo, login_id, login_pw);
		try {
			
			if(ok == 1) {
				//세션 만들기
				session.setAttribute("insert_id", login_id);
				
				String str = (String)session.getAttribute("insert_id");
				//int check = session.get(dao.userCheck(vo, login_id, login_pw));
				
				System.out.println("세션에서 얻어오 ㄴ아이ㅣ디는 " + str);
		
				RequestDispatcher rd = request.getRequestDispatcher("NoticeBoardServelet");
				rd.forward(request, response);
			} else if(ok == 0) {
				
				System.out.println("삑 비밀번호 틀림");
				response.sendRedirect("/Nabong_writer/loginform.jsp");
				
			}
			
			
			
		} catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println(vo.getUser_id() + "  vo.getUser_id() 의 값이 없음");
			
		}
		
		
		
		
		
	}

}
