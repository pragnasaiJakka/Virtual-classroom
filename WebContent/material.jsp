<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,java.io.*"  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="sidebar.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="material.js"></script>

</head>
<body>

	<div class="container" style="margin-top: 35px;">
		<div class="page-header page-heading ">
			<h1 class="text-center">
				Uploads
				<button type="button" class="btn btn-default pull-right"
					data-toggle="modal" data-target="#upload">Upload</button>
			</h1>
		</div>
		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>File</th>
					</tr>
				</thead>
				<tbody>
					<%					
					ArrayList<ArrayList<String>> al = (ArrayList<ArrayList<String>>) request.getAttribute("uploads");
					for (ArrayList s : al) {    

					%>

					<tr>
						<td>
							<h4>
								<a href="<%out.print(s.get(1)); %>" class="file" > 
								<%out.print(s.get(0));%></a>
								<br>
							</h4>
						</td>
					</tr>

					<%
						}
					%>

				</tbody> 
			</table>
		</div>

		<button type="button" class="btn btn-default pull-right"
			style="margin-bottom: 20px;">Next</button>

		<div class="modal fade" id="upload" role="dialog">
			<div class="modal-dialog">

				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Upload</h4>
					</div>

					<div class="modal-body">
						<form method="post" action="Upload_file"
							enctype="multipart/form-data">

							<label for="title">Title:</label> <input type="text"
								class="form-control box " id="title" name="title"
								required="required"><br> Select file to upload: <input
								type="file" name="file" size="60" /><br /> <br /> <input
								type="submit" value="Upload" />
						</form>

					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
					</div>

				</div>

			</div>
		</div>

	</div>

</body>
</html>