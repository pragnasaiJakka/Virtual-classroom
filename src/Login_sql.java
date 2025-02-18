import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class Login_sql
 */
@WebServlet("/Login_sql")
public class Login_sql extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login_sql() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResultSet count;
		HttpSession session; 
		int check=-1;
		String email=request.getParameter("user");
		String password=request.getParameter("password");
		String session_id,query,req_uri = null;
		PreparedStatement ps;	
		long time;
		int co=0;
		Cookie ck[];
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classRoom", "root", "jeevan");	

			query = "select count(*) from user_details where email=? and password=? ";

			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			count=ps.executeQuery();

			if(count.next()){
				check = count.getInt(1);
			}

			if(check==1){

				session = request.getSession();
				session.setAttribute("email",email);

				session_id=session.getId();
				time = session.getCreationTime();

				query="insert ignore into session values(?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1,email);
				ps.setString(2,session_id);
				ps.setLong(3, time);
				ps.executeUpdate();

			ck = request.getCookies();
			if(ck != null){
				for(Cookie c : ck){	
					if("url".equals(c.getName())){
                        req_uri = c.getValue();
						co = 1;
					    Cookie ce = new Cookie("url","");
					    ce.setMaxAge(0);
					    response.addCookie(ce);
					}
				}
			}
				if(co==1){
					if(req_uri.toLowerCase().endsWith("enter_print")){
						 response.sendRedirect("Enter_print");
					}else{
						if(!req_uri.toLowerCase().endsWith("login_bs.html")){        
		     			response.sendRedirect("../"+req_uri);
						}
					}  
				}else{
					response.sendRedirect("Enter_print");
				}
				
			}else{
				response.sendRedirect("login_bs.html");
				
			//	Cookie cki = new Cookie("check","1");
			//	response.addCookie(cki);
				
			//	response.sendRedirect("Enter_print");
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
