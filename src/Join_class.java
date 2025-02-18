

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

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Join_class
 */
@WebServlet("/Join_class")
public class Join_class extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Join_class() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw;
		String query;
		ResultSet rs;
		PreparedStatement ps;
		RequestDispatcher rd;
		Connection con ;
		String name = null,pass = null,user;
		HttpSession ssn;

		name =  request.getParameter("name").toString();
		pass =  request.getParameter("pass").toString();
		ssn =  request.getSession();
		user= (String) ssn.getAttribute("email");
		pw = response.getWriter();

		try{

			if((name == "") || (pass == "")){
				pw.println("Class name or pass key field is blank");
				return;
			}else{

				Class.forName("com.mysql.jdbc.Driver");	
				con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/classRoom", "root", "jeevan");				
				query = "select count(*) from class_admin where class_id=? and password=?";
				ps = con.prepareStatement(query);
				ps.setString(1,name );
				ps.setString(2, pass);
				rs = ps.executeQuery();

				if(rs.next()){
					if(rs.getInt(1)==0){
						pw.println("Please enter valid details");

					}else{
						query="select count(*) from class_details where class_id=? and email=?";
						ps = con.prepareStatement(query);
						ps.setString(1,name );
						ps.setString(2,user);
						rs = ps.executeQuery();       

						if(rs.next()){
							if(rs.getInt(1)==1){
								pw.println("You have already joined this class.");
							}else{
								query="insert into class_details values(?,?)";
								ps = con.prepareStatement(query);
								ps.setString(1, user);
								ps.setString(2, name);
								int x = ps.executeUpdate();
								if(x!=1){
									pw.println("Error");
								}else{ 
									pw.println("Success");
								}
							}
						}
					}
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
