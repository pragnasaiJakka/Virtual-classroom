import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Upload_file
 */

@WebServlet("/Upload_file")
@MultipartConfig
public class Upload_file extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String DIR = "uploads";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload_file() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String query;
		PreparedStatement ps;
		ResultSet rs;
		String appPath = request.getServletContext().getRealPath("");
		String savePath = "/home/jeevansai" + File.separator + DIR;
		PrintWriter pw  =response.getWriter();
		Long time ;
		File save = new File(savePath);
		if(!save.exists()){
			save.mkdir();
		}
		String name = null;
		time = System.currentTimeMillis();
		for(Part part: request.getParts()){
			String fileName = getFileName(part);
			if(fileName.length() > 0){
				fileName = fileName.concat("_").concat(String.valueOf(time));
				name = fileName;
				part.write(savePath + File.separator + fileName);
			}
		}


		try{

			Class.forName("com.mysql.jdbc.Driver");	
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/classRoom", "root", "jeevan");				
			String title = (String) request.getParameter("title");
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

			query = "insert into uploads(title,link,time,class_id) values(?,?,?,?)";	

			String p = savePath.concat("/");
			ps = con.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, p.concat(name));
			ps.setLong(3, time);
			ps.setString(4, class_id);
			ps.executeUpdate();
			response.sendRedirect("Files");

		}catch(Exception e){
			e.printStackTrace();
		}


	}

	private String getFileName(Part part) {
		String content = part.getHeader("content-disposition");
		String[] items = content.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}

}
