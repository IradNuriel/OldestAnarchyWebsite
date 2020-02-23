package logic;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;


import model.MySQLDB;
import model.User;

//This class is a middle layer class between the "communication" layers 
//and the back-end/model/db layers. it provides mainly business logic to
//your web site.
//it has two constructors, one usually used from servlets/listeners modules 
//and the other used from jsp pages
public class Context {
	HttpServletRequest request;
	HttpServletResponse  response;
	HttpSession session;
	ServletContext application;
	PrintWriter out;
	static MySQLDB dbc= new MySQLDB();
	
	
	private final String SESSION_KEY_USER= "currentUser";
	
//used mainly from JSP	
	public Context(PageContext pContext) throws Exception {
		this((HttpServletRequest)pContext.getRequest(), 
				(HttpServletResponse)pContext.getResponse());
	}
	
//used mainly from servlets...
	public Context(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.request = request;
		
		this.response = response;
		this.session = request.getSession();
		this.application = this.session.getServletContext();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			this.out = response.getWriter();
			
			if (dbc.IsAlive() == false) {
				throw(new Exception("no db connection"));
			}
		} catch (IOException e) {};
		
	}
	
	public void insertAlertDlg(String msg, String forwardToPage){
		out.write("<script charset=\"UTF-8\">");
		out.write("alert('" + msg + "');");
		//out.write("setTimeout(function(){window.location.href='secondpage.jsp'},1000);");
		if (forwardToPage!= null)
			out.write("window.location.href='"+ forwardToPage + "';");
		out.write("</script>");
	}
	
	
	public void handleLogout(){
		this.session.removeAttribute(SESSION_KEY_USER);
		try {
			response.sendRedirect("home.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean isLoggedIn(){
		return (this.session.getAttribute(SESSION_KEY_USER)!= null);
	}
	
	public boolean isManager(){
		return (this.session.getAttribute(SESSION_KEY_USER)!= null);
	}
	
	public void handleLogin() {
		String nickname= request.getParameter("nickname");
		String password= request.getParameter("password");
		try {
			if(dbc.UserAuthenticate(nickname, password)) {
				this.session.setAttribute(SESSION_KEY_USER, nickname);
				String url = "home.jsp?name=" + URLEncoder.encode(nickname, "UTF-8");
				response.sendRedirect(url);
				//you might need to encode the url in some unresolved cases where sessionID needs to be enforced
				//response.sendRedirect(response.encodeRedirectURL(url));
			}
			else {
				 request.setAttribute("errorMsg",  "שם משתמש או סיסמא אינם נכונים, אנא נסה שוב!");
				 request.getRequestDispatcher("tofes.jsp").forward(request, response);
		
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		} 
	}
	public void handleRegistration() {
		String nickname= request.getParameter("nickname");
		if (userCanBeRegistered(nickname)){
			dbc.AddNewUser(userFromRequest());
			handleLogin();
		}
		else {
			request.setAttribute("error", "This user name is already taken, please choose another one and try again");
			try {
				request.getRequestDispatcher("tofes.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getFieldFromRequest(String key)
	{
		String x = request.getParameter(key);
		return (request.getParameter(key) != null? request.getParameter(key): "");
	}
	
	private User userFromRequest() {
		User u = new User();
		u.setNickName(getFieldFromRequest("nickname"));
		u.setPassword(getFieldFromRequest("password"));
		u.setRole(getFieldFromRequest("role"));
		//update this method to reflect your user object
		return u;
	}
	
	private boolean userCanBeRegistered(String nickname){
		return !dbc.UserExists(nickname);
	}
	
	public void handleUnknownRequest() {
		try {
			response.sendRedirect("home.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
