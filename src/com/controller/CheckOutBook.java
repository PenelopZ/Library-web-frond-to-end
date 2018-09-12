package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sevlet.UserService;

/**r
 * Servlet implementation class CheckOutBook
 */
@WebServlet("/CheckOutBook")
public class CheckOutBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		
		int bookId = Integer.valueOf(request.getParameter("BookId"));
		int branchId = Integer.valueOf(request.getParameter("BranchId"));
		
		/*int BOOK_ID = Integer.valueOf(bookId);
		int BR_ID = Integer.valueOf(branchId);*/
		
		System.out.println(bookId + " " + branchId);
		
		UserService userService = new UserService();
		RequestDispatcher rd = request.getRequestDispatcher("CheckoutResult.jsp");
		if(userService.checkOutBook(bookId, branchId)){
			request.setAttribute("msg", "Successfully checked out the book");
		}else{
			request.setAttribute("msg", "fail to check out the book");
		}
		
		rd.forward(request, response);
	}

}
