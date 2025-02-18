

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Newpost
 */
@WebServlet("/Newpost")
public class Newpost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Newpost() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession ssn = null;
		String title,post,class_id,email,user = null,query;
		PreparedStatement ps;
		ResultSet rs;
		long time;

		ssn = request.getSession();
		title = request.getParameter("title").toString();
		post = request.getParameter("post").toString();
		class_id = request.getParameter("class_id").toString();
		email = (String) ssn.getAttribute("email");
		PrintWriter pw = response.getWriter();

		
    		if(class_id.equals("")){
			pw.println("Class name is blank");
			return; 
	    	}

		try{

			Class.forName("com.mysql.jdbc.Driver");	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classRoom", "root", "jeevan");	

			
			query="select count(*) from class_admin where class_id=?";
			ps = con.prepareStatement(query);
			ps.setString(1, class_id);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)==0){
					pw.println("Class does not exist");
					return;
				}
			}



			query="select name from user_details where email=?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs=ps.executeQuery();

			if(rs.next()){
				user = rs.getString(1);
			}

			time = System.currentTimeMillis();

			query="insert into posts(title,content,user,time,class_id) values(?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, post);
			ps.setString(3, user);
			ps.setLong(4, time);
			ps.setString(5, class_id);
			ps.executeUpdate();

			pw.println("posted");
			//PrintWriter pw = response.getWriter();
			//pw.println("<p>posted</p>");

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
