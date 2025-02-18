<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,java.sql.*,java.util.*,javax.servlet.*,com.mysql.jdbc.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
out.println("a\na\na\na\n");

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


File f = new File(link);
response.setContentType("application/octet-stream");

response.setHeader("Content-Disposition",
		"attachment; filename=\"" + file + "\"");
InputStream in = new BufferedInputStream(new FileInputStream(f));

FileInputStream fi = new FileInputStream(f);
int i;


while((i=fi.read()) !=-1){
	out.println(i);
}


//out.flush();
//out.close();
in.close();


%>



</body>
</html>