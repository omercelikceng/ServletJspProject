<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Employee</title>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/resources/js/userOperationAjaxJSON.js"></script>
</head>
<body id="main-posts">
	<div class="container-fluid" style="margin-top: 40px">
		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-8"></div>
					<div class="col-md-4">
						<c:out value="${userInformation.eMail}" /> <a href="${pageContext.request.contextPath}/logoutController">Logout</a>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<button type="button" class="btn btn-info btn-md"
							data-toggle="modal" data-target="#myModal">Task Add</button>
					</div>
					<div class="col-md-2"></div>
				</div>
				<div class=row>
					<div class="col-md-8"></div>
					<div class="col-md-2">
						<a type="button" class="btn btn-warning"  href="${pageContext.request.contextPath}/excelDownload">Export Excel File</a>
					</div>
					<div class="col-md-2"></div>
					
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<legend>Task List</legend>
						<div class="table-responsive">
							<table id="example" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>Id</th>
										<th>Task Name</th>
										<th>Task</th>
										<th>TaskDate</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>


									<c:forEach items="${taskLists}" var="task">	
										<tr>
											<td><c:out value="${task['id']}" /></td>
											<td><c:out value="${task['taskName']}" /></td>
											<td><c:out value="${task['taskDescription']}" /></td>
											<td><c:out value="${task['taskDate']}"/></td>
											<td><a
												href="taskController?deleteOperation=1&taskId=${task['id']}"
												class="btn btn-danger" type="button">Delete</a>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				
			</div>
		</div>

		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">TASK ADD OPERATION</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="taskController"
							method="POST">
							<fieldset>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="name">Task Name</label>
									<div class="col-md-4">
										<input id="taskName"  name="inputTaskName" required type="text" placeholder=""
											class="form-control input-md required">

									</div>
								</div>

								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" for="surname">Task</label>
									<div class="col-md-4">
										<input id="taskDescription" required name="inputTaskDescription" type="text" placeholder=""
											class="form-control input-md">

									</div>
								</div>
								
								<!-- Text input-->
								<div class="form-group">
									<label class="col-md-4 control-label" required for="surname">Task Date</label>
									<div class="col-md-4">
										<input id="taskDate" name="inputTaskDate" type="date" placeholder=""
											class="form-control input-md">

									</div>
								</div>
								

								<!-- Button -->
								<div class="form-group">
									<label class="col-md-4 control-label" for="add"></label>
									<div class="col-md-4">
										<button type="button" onclick="sendAjaxQueryForTask()" class="btn btn-info btn-md"
											data-toggle="modal" data-target="#myModal">Task Add</button>
									</div>
								</div>
							

							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		
		
		
	</div>

</body>
</html>