package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.demo.models.AccountDetailsModel;
import com.demo.models.AccountModel;

@WebServlet("/login")
/**
 * Servlet implementation class LoginServlet
 */
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			doGet_Login(request, response);
		} else {
			if(action.equalsIgnoreCase("logout")) {
				doGet_Logout(request, response);
			}
		}
	}

	protected void doGet_Login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountModel accountModel = new AccountModel();
		request.setAttribute("accounts", accountModel.findAll());
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);
	}
	protected void doGet_Logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("account");
		request.getSession().removeAttribute("accountdetails");
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);
	}
	protected void doGet_Account(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("login")) {
			doPost_Login(request, response);
		}
	}
	protected void doPost_Login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AccountModel accountModel = new AccountModel();
		AccountDetailsModel accountDetailsModel = new AccountDetailsModel();
		if(accountModel.login(username, password)) {
			request.getSession().setAttribute("accountdetails", 
					accountDetailsModel.findAccountByAccountID(accountModel.findAccountByUsername(username).getId()));
			request.getSession().setAttribute("account", accountModel.findAccountByUsername(username));
			response.sendRedirect("account");
		
		} else {
			request.getSession().setAttribute("msg", "Tài khoản hoặc mật khẩu không đúng");
			response.sendRedirect("login");
		}
	}

}
