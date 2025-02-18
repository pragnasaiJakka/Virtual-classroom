
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Files
 */
@WebServlet("/Files")
public class Files extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Files() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher rd;
		String query;
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<ArrayList<String>> al;
		int i;
		try{

			Class.forName("com.mysql.jdbc.Driver");	
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/classRoom", "root", "jeevan");		
			query="select title,link from uploads order by time desc limit 10 ";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			al=new ArrayList<ArrayList<String>>();

			for(i=0; i<10 && rs.next() ;i++){

				al.add(new ArrayList<String>());
				al.get(i).add(rs.getString(1));
				al.get(i).add("file://" + rs.getString(2));
                //	al.get(i).add(rs.getString(2));			
			}
			request.setAttribute("uploads", al);
				rd=request.getRequestDispatcher("material.jsp");
				rd.forward(request, response);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
