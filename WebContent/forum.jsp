<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="table.css">

</head>
<body>

	<div class="container" style="margin-top: 35px;">
		<div class="page-header page-heading">
			<h1 class="pull-left">Forums</h1>
		</div>

		<div class="tab">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Question</th>
						<th class="cell-stat text-center">asked by</th>
					</tr>
				</thead>
				<tbody>

					<%
						
					%>



				</tbody>
			</table>
		</div>

		<div class="modal fade" id="answer" role="dialog">
			<div class="modal-dialog">

				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Modal</h4>
					</div>

					<div class="modal-body"></div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
					</div>

				</div>

			</div>
		</div>

	</div>

</body>
</html>