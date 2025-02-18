import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//	ServletOutputStream bos = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Download() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		String query,add,file = null,link = null;
		ResultSet rs;
		PreparedStatement ps;
		RequestDispatcher rd;
		int id;


		try{

			Class.forName("com.mysql.jdbc.Driver");	
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/classRoom", "root", "jeevan");			

			query="select title,link from uploads where id=?";
			ps = con.prepareStatement(query);

			add=request.getQueryString().substring(3);
			id=Integer.parseInt(add);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				file = rs.getString(1);
				link = rs.getString(2);

			}
		}catch(Exception e){
			e.printStackTrace();
		}


		/*	File f = new File(link);
		 response.setContentType("application/download");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + file + "\"");

		// BufferedInputStream bis = getAttachment(link);
		InputStream bis = new BufferedInputStream(new FileInputStream(f)); 
		PrintWriter out = response.getWriter();
		int i;
		while((i=bis.read())>0){

		out.println(i);
		}
		/*	InputStream in = new BufferedInputStream(new FileInputStream(f));
		 ServletOutputStream out = response.getOutputStream();

		FileInputStream fi = new FileInputStream(link);
		int i;
		while((i=fi.read()) !=-1){
			out.write(i);
		}
		 */
		//out.flush();
		//out.close();
		//	in.close(); */
		//	response.setHeader("Content-Disposition", "attachment; filename=file");
		//	response.setContentType("application/octet-stream");
		//	bos = response.getOutputStream();
		download(link);

	}

	private void download(String link) throws IOException{

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try{

			URL url = new URL( "file:///home/jeevansai/uploads/4_1461741029970" );

			//	URLConnection urlc = url.openConnection();

			//ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			//	FileOutputStream fos = new FileOutputStream("info");
			//	fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			File f = new File("file");

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private BufferedInputStream getAttachment(String link) throws IOException {

		return null;
	}
}