package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sevlet.UserService;

/**
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/NewUserServlet")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//Borrower insert data
		UserService userService_insert = new UserService();
		String Bname = request.getParameter("name");
		String Baddress = request.getParameter("address");
		String Bphone = request.getParameter("phone");
		String Bpassword = request.getParameter("password");
		try {
			String cardNo = userService_insert.insertBorrower(Bname, Baddress, Bphone, Bpassword);
			RequestDispatcher rd = request.getRequestDispatcher("NewUserResult.jsp");
			if(cardNo != null){
				request.setAttribute("msg", "Success!\n Borrower registered with cardno: "+cardNo);
				
			}else{
				request.setAttribute("msg", "fail");
			}
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
