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
			
		}
		getServletContext().setAttribute("activeUsers",activeUsers);
		response.getWriter().print(s);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		postMessage(new Message((String)(request.getParameter("senderName")),(String)(request.getParameter("info")),request.getSession()));
		for(int i=0;i<1000;i++) {}
		doGet(request, response);
		
	}
	
	protected void postMessage(Message m) {

		ArrayList<SessionWithBuffer> activeUsers;

		activeUsers=(ArrayList<ChatServlet.SessionWithBuffer>)(getServletContext().getAttribute("activeUsers"));
		

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
			while(!buffer.isEmpty()) {
				Message m  = buffer.remove();
				if(this.session.equals(m.getSenderSession())) {
					allNewMessages+="<p class='yourChatMessage'>" + m.getInfo() +"</p>"+ "<br>";
				}else {
					allNewMessages+="<p class='notYourChatMessage'> <b>" + m.getSenderName() + " says: </b>" + m.getInfo() +"</p>"+ "<br>";
				}
			}
			return allNewMessages;
		}
		public HttpSession getSession() {
			return this.session;
		}
		
	}
	
	
	
	

}
