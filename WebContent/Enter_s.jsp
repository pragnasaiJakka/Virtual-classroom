
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="bootstrap.css">
<script
	src="jquery.js"></script>
<script
	src="bootstrap.js"></script>

<link type="text/css" rel="stylesheet" href="sidebar.css">
<link type="text/css" rel="stylesheet" href="Enter_s.css">
<script src="create_class.js"></script>

</head>
<body>

	<div id="wrapper">

		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">

				<li><a href="" data-toggle="modal" data-target="#create">Create
						class</a></li>
				<li><a href="" data-toggle="modal" data-target="#join">Join
						class</a></li>
				<li><a href="Posts_page">Posts</a></li>
				<li><a href="Files">Material</a></li>
				<li><a href="Profile">Profile</a></li>
				<li><a href="Logout">Logout</a></li>
			</ul>
		</div>

		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<div>
							<img id="menu-toggle" src="button-1.png" width="30px"
								height="30px">
						</div>
					</div>
				</div>


			</div>
		</div>


		<div class="modal fade" id="create" role="dialog">
			<div class="modal-dialog modal-sm">

				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">New class</h4>
					</div>

					<div class="modal-body">
						<div class="panel panel-default" align="center" id="create_form">
							<form action="#" role="form" method="post">
								<div class="panel-body">
									<div class="form-group">
										<label for="create_name">Class Name:</label> <input
											type="text" class="form-control box " id="create_name"
											name="create_name" required="required">
									</div>
									<div class="form-group">
										<label for="create_pass">Pass Key:</label> <input
											type="password" class="form-control box " id="create_pass"
											name="create_pass" required="required">
									</div>
								</div>
								<button type="submit" class="btn btn-default" id="create_btn"
									name="create_btn">Submit</button>
							</form>
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">close</button>
					</div>

				</div>

			</div>
		</div>

		<div class="modal fade" id="join" role="dialog">
			<div class="modal-dialog modal-sm">

				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Join class</h4>
					</div>

					<div class="modal-body">

						<div class="panel panel-default " align="center" id="join_form">
							<form action="#" role="form" method="post">
								<div class="panel-body">
									<div class="form-group">
										<label for="join_name">Class Name:</label> <input type="text"
											class="form-control box " id="join_name" name="join_name"
											required="required">
									</div>
									<div class="form-group">
										<label for="join_pass">Pass Key:</label> <input
											type="password" class="form-control box " id="join_pass"
											name="join_pass" required="required">
									</div>
								</div>
								<button type="submit" class="btn btn-default" id="join_btn"
									name="join_btn">Submit</button>
							</form>
						</div>

					</div>

					<div class="modal-footer">
						<button type="button" id="join_close" class="btn btn-default"
							data-dismiss="modal">close</button>
					</div>

				</div>

			</div>
		</div>
	</div>

</body>
</html>