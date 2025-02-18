

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONObject;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Post
 */
@WebServlet("/Post")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		int ans;
		String add,query,content = null;
		ResultSet rs;
		PreparedStatement ps;
		RequestDispatcher rd;
		JSONObject json;


		try{
			add = request.getQueryString().substring(3);
			ans = Integer.parseInt(add);
		

			Class.forName("com.mysql.jdbc.Driver");	
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/classRoom", "root", "jeevan");			
		
			HttpSession ssn = request.getSession();
			String email = (String) ssn.getAttribute("email");
			
			query="select class_id from class_details where email=?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			String class_id = null;
			if(rs.next()){
			class_id = rs.getString(1);
			}
			query="select content from posts where id=? and class_id=?";
			ps = con.prepareStatement(query);
			ps.setInt(1, ans);
			ps.setString(2, class_id);
			rs = ps.executeQuery();
			if(rs.next()){
				content = rs.getString("content");
			}

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");		

			json = new JSONObject();
			json.put("answer", content);


			PrintWriter out = response.getWriter();
			out.println(json);			

		}catch(Exception e){
			e.printStackTrace();
		}



	}

}
