package main.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import main.dao.MemberDAO;
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
		
		
		
		
		 //거 폼에 들어갈라면 서블릿을 호출해야도미ㅋㅋㅋ
		//("/Nabong_writer/login.jsp");
//		RequestDispatcher rd = request.getRequestDispatcher("/Nabong_writer/noitceboard.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//자바스크립트 사용하기
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int check = 0;
		
		TestVo vo = new TestVo();
		MemberDAO dao = new MemberDAO();
		
		String userid = request.getParameter("user_id");
		String userpw = request.getParameter("user_pw");
		String username = request.getParameter("user_name");
		String checkpw = request.getParameter("check_pw");
		
		String resultid = userid.trim();
		String resultpw = userpw.trim();
		String resultname = username.trim();
		String resultcheck = checkpw.trim();
		System.out.println("------------------------------------------");
		System.out.println(resultid.indexOf(" "));
		System.out.println(resultpw.indexOf(" "));
		System.out.println(resultname.indexOf(" "));
		System.out.println(resultcheck.indexOf(" "));
		System.out.println("indexof에서 -1이외의 수가 나오면 안됨");
		System.out.println(resultid.length());
		System.out.println(resultpw.length());
		System.out.println(resultname.length());
		System.out.println(resultcheck.length());
		System.out.println("아이디는 5글자 이상 12글자 이하 이름은 2글자 이상 7글자 이하 비밀번호는 6글자 이상 10글자 이하");
		System.out.println(resultid);
		System.out.println(resultpw);
		System.out.println(resultname);
		System.out.println(resultcheck);
		System.out.println("------------------------------------------");
		
		if(resultid.length() == 0 || resultpw.length() == 0 || resultname.length() == 0 || resultcheck.length() == 0) {
			check = 1;
			System.out.println(check + " 공백 이외의 값이 없음");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('내용을 입력해주세요');");
			out.println("history.back();");
			out.println("</script>");
			
		} else if(resultid.indexOf(" ") != -1 || resultpw.indexOf(" ") != -1 || resultname.indexOf(" ") != -1 || resultcheck.indexOf(" ") != -1) {
			check = 2;
			System.out.println(check + " 공백 이외의 값은 없지만 문자 사이에 공백이 존재함");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('공백은 입력할 수 없습니다');");
			out.println("history.back();");
			out.println("</script>");
		} else if(resultid.length() < 4 || resultpw.length() < 5 || resultname.length() < 2 || resultcheck.length() < 5
				|| resultid.length() > 11 || resultpw.length() > 13 || resultname.length() > 7 || resultcheck.length() > 13) {
			check = 3;
			System.out.println(check + " 공백 이외의 값은 없고 문자 사이에 공백이 존재하지 않지만 일정 글자 조건에 맞지 않음");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('해당 글자 조건에 맞게 입력해 주세요');");
			out.println("history.back();");
			out.println("</script>");
		} else if(!resultpw.equals(resultcheck)) {
			check = 4;
			System.out.println(check + " 공백 이외의 값이 없고 문자 사이에 공백이 존재하지 않으며 일정 글자 조건에 맞으나 비밀번호와 비밀번호 확인란의 내용이 다름");
			System.out.println(resultpw + " 와  " + resultcheck);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('비밀번호와 확인란이 맞지 않습니다');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			check = 5;
			int idcheck = dao.idcheck(vo);
			
			System.out.println(check + " 아 됐다 이제 중복확인 하자");
			vo.setUser_id(request.getParameter("user_id"));			
			dao.idcheck(vo);
			System.out.println(idcheck);
			if(idcheck == 0) {
				vo.setUser_id(request.getParameter("user_id"));
				vo.setUser_pw(request.getParameter("user_pw"));
				vo.setUser_name(request.getParameter("user_name"));
				//클라이언트가 입력한 데이터를 넘겨줌
				dao.insert(vo);
				//join폼으로 이동해서 값을 받아야됨
				out.println("<script type='text/javascript'>");
				out.println("alert('회원가입을 축하드립니다!');");
				out.println("</script>");
				response.sendRedirect("/Nabong_writer/loginform.jsp");
			} else {
				System.out.println("중복 아이디");
				out.println("<script type='text/javascript'>");
				out.println("alert('이미 있는 아이디입니다');");
				out.println("history.back();");
				out.println("</script>");
			}
			
		}
	}

}
