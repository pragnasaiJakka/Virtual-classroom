

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Create_class
 */
@WebServlet("/Create_class")
public class Create_class extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Create_class() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub

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
			
				query = "select count(*) from class_details where class_id=? ";
				ps = con.prepareStatement(query);
				ps.setString(1,name );
				rs = ps.executeQuery();

				if(rs.next()){
					if(rs.getInt(1)>0){

						pw.println("Class name is already in use<br>Please try another");
					}else if(rs.getInt(1)==0){
						query = "insert into class_details values(?,?)";
						ps = con.prepareStatement(query);
						ps.setString(1,user);
						ps.setString(2,name);
						int x=ps.executeUpdate();
						if(x != 1){
						pw.println("error");
						return;
						}
						query="insert into class_admin values(?,?,?)";
						ps = con.prepareStatement(query);
						ps.setString(1,name);
						ps.setString(2, user);
						ps.setString(3, pass);
						int y = ps.executeUpdate();
						if(y!=1){
						pw.println("error");
						return;
						}			
						if(x==1 && y==1)
						pw.println("Success");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		} 
	}
}
