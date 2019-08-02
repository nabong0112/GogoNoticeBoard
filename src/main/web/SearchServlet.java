package main.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.WriteDao;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WriteDao dao = new WriteDao();
		
		String searchString = request.getParameter("searchType");
		int searchInt =  Integer.parseInt(searchString);
		String searchName = request.getParameter("search");
		
		System.out.println("검색 옵션 : " + searchInt);
		System.out.println("검색 내용 : " + searchName);
		
		dao.search(searchInt, searchName);
		
		RequestDispatcher rd = request.getRequestDispatcher("/searchform.jsp?SearchType=" + searchInt + "&search" + searchName);
		rd.forward(request, response);
		
//		response.sendRedirect("/Nabong_writer/searchform.jsp?SearchType=" + searchInt + "&search" + searchName);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
