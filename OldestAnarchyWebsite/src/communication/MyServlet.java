package communication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Context;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context ctx;
		try {
			ctx = new Context(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		String command = request.getParameter("cmd");
		if (command == null)
			ctx.handleUnknownRequest();
		else {
			switch(command) {
			case "register":
				ctx.handleRegistration();
				break;
			case "login":
				ctx.handleLogin();
				break;
			case "logout":
				ctx.handleLogout();
				break;
			case "other":
				response.getWriter().print("<p style='font-size: 24px'>I don't understand you!!!</p>");
				break;
			default:
				ctx.handleUnknownRequest();
			}
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
