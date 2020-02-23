package communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aditionalsDataTypes.Message;
import aditionalsDataTypes.Queue;
/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<SessionWithBuffer> activeUsers;

		
	
		activeUsers=(ArrayList<ChatServlet.SessionWithBuffer>)(getServletContext().getAttribute("activeUsers"));
		
		String s = "";
		boolean f=false;
		for(SessionWithBuffer activeUser: activeUsers) {
			if(activeUser.getSession().equals(request.getSession())) {
				s=activeUser.flushBuffer();
				f=true;
				break;
			}
		}
		if(f) {
		}else {
			activeUsers.add(new SessionWithBuffer(request.getSession()));
			getServletContext().setAttribute("activeUsers",activeUsers);
		}
		response.getWriter().print(s);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		postMessage(new Message((String)(request.getParameter("senderName")),(String)(request.getParameter("info"))));
		doGet(request, response);
		
	}
	
	protected void postMessage(Message m) {

		ArrayList<SessionWithBuffer> activeUsers;

		activeUsers=(ArrayList<ChatServlet.SessionWithBuffer>)(getServletContext().getAttribute("activeUsers"));
		

		Queue<Message> temp=new Queue();
		temp.insert(m);
		for(SessionWithBuffer userBuf: activeUsers) {
			userBuf.addMessageToBuffer(m);
		}
		getServletContext().setAttribute("activeUsers", activeUsers);
	}
	
	
	
	
	
	public static class SessionWithBuffer{
		private HttpSession session;
		private Queue<Message> buffer;
		public SessionWithBuffer(HttpSession session) {
			this.session = session;
			this.buffer= new Queue<Message>();
		}
		public void addMessageToBuffer(Message m) {
			this.buffer.insert(m);
		}
		public String flushBuffer() {
			String allNewMessages = "";
			Queue<Message> buf = new Queue<Message>();
			while(!buffer.isEmpty()) {
				Message m  = buffer.remove();
				allNewMessages+="<p style='text-size:12;'>" + m.getSenderName() + ": </p>" +"<p style='text-size:11'>"+ m.getInfo() +"</p>"+ "<br>";
				
			}
			return allNewMessages;
		}
		public HttpSession getSession() {
			return this.session;
		}
		
	}
	
	
	
	

}
