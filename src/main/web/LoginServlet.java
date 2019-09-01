package main.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.dao.MemberDAO;
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
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 생성
		HttpSession session = request.getSession();
		//자바스크립트 생성할수 있게 해주는거
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//이게 값을 가지고온건가? 이게 로그인창에서 얻어온 정보
		String login_id = request.getParameter("insert_id");
		String login_pw = request.getParameter("insert_pw");
		
		TestVo vo = new TestVo();
		MemberDAO dao = new MemberDAO();
		
		//usercheck의 리턴값으로 어떻게 할지 정할거임
		//유저의 아이디와 비밀번호가 맞는지 확인하기위해 넘김
		int ok = dao.userCheck(vo, login_id, login_pw);
		try {
			//아이디와 비밀번호를 확인한 dao에서 리턴값(ok)을 받아와서 그 값에 알맞은 반응을 보이도록
			if(ok == 1) {
				String user_id = vo.getUser_id();
				String user_pw = vo.getUser_pw();
				String user_name = vo.getUser_name();
				//세션 만들기
				session.setAttribute("user_id", user_id);
				session.setAttribute("user_name", user_name);
				session.setAttribute("user_pw", user_pw);
				System.out.println( "Servlet : '"+ user_id + "'님이 로그인하셨습니다");
				//게시판 서블릿으로 전달
				response.sendRedirect("NoticeBoardServelet");
				
			} else if(ok == 0) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('비밀번호가 틀립니다!');");
				out.println("history.back();");
				out.println("</script>");
				
			} else if(ok == -1) {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('없는 아이디이거나, 잘못입력하셨습니다!');");
				out.println("history.back();");
				out.println("</script>");
			}			
			
		} catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println(vo.getUser_id() + "  vo.getUser_id() 의 값이 없음");
			
		}
		
		
		
		
		
	}

}
