import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Posts_page
 */
@WebServlet("/Posts_page")
public class Posts_page extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Posts_page() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String query,email;
		ResultSet rs;
		PreparedStatement ps;
		ArrayList<ArrayList<String>> al;
		RequestDispatcher rd;
		int i;
		HttpSession session;

		try{

			PrintWriter pw = response.getWriter();

			session = request.getSession();
			email = (String) session.getAttribute("email");

			Class.forName("com.mysql.jdbc.Driver");	
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/classRoom", "root", "jeevan");		

			query="select posts.title as title,posts.user as user,posts.id as id from posts inner join class_details on posts.class_id=class_details.class_id where class_details.email=? order by posts.time desc limit 10";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			al=new ArrayList<ArrayList<String>>();

			for(i=0; i<10 && rs.next() ;i++){

				al.add(new ArrayList<String>());

				al.get(i).add(rs.getString(1));
				al.get(i).add(rs.getString(2));
				al.get(i).add(String.valueOf(rs.getString(3)));

			}		
			request.setAttribute("posts", al);
			rd=request.getRequestDispatcher("posts.jsp");

			rd.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}