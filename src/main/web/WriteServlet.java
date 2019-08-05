package main.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.dao.WriteDao;
import main.vo.WriteVo;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/WriteServlet")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet() {
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
		//글쓰기에서 가지고온 문자들
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		WriteDao dao = new WriteDao();
		WriteVo vo = new WriteVo();
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String user = (String)session.getAttribute("user_id");

		//양쪽 공백 제거
		String title2 = title.trim();
		String text2 = text.trim();

		System.out.println(title2.length());
		System.out.println(text2.length());
		System.out.println(title2);
		System.out.println(text2);
		
		//글쓰기 공백 검사
		if(title2.length() == 0 || text2.length() == 0) {
			System.out.println(title2 + "에 공백만 들어있음");
			System.out.println(text2 + "에 공백만 들어있음");
			out.println("<script type='text/javascript'>");
			out.println("alert('내용을 올바르게 입력해 주세요!');");
			out.println("history.back();");
			out.println("</script>");
			response.sendRedirect("/Nabong_writer/writeform.jsp");
		} else {
			vo.setBoard_title(title);
			vo.setBoard_text(text);
			vo.setBoard_user(user);
				
			dao.insertText(vo);
			
			ArrayList<WriteVo>board = dao.selectText();
			
			if(board != null) {
				for(WriteVo i: board) {
					System.out.println("-------------글 목록 : 총 " + board.size() + "개---------------");
					System.out.println(i.getBoard_no()+ " ");
					System.out.println(i.getBoard_title()+ " ");
					System.out.println(i.getBoard_text()+ " ");
					System.out.println(i.getBoard_user()+ " ");
					System.out.println(i.getBoard_time()+ " ");
					System.out.println(i.getBoard_view()+ " ");	
					System.out.println("---------------글목록 끝-------------");
				}
			}else {
				System.out.println("없음");
			}
			
			out.println("<script type='text/javascript'>");
			out.println("alert('글이 등록되었습니다!');");
			
			out.println("</script>");
			response.sendRedirect("/Nabong_writer/noticeboard.jsp");
			
			
		}
		
		
		
		
		
	}

}
