<?xml version="1.0" encoding="utf-8" ?>
<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />

<title>Appointment Management</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script type='text/javascript' src='./Components/appointment.js'></script>
</head>


<body>
	<div class="container">
		<div class="row">
			<div class="col">

				<h1>Appointment Management</h1>

				<form id="formAppointment" name="formAppointment">

					User ID: <input id="userID" name="userID" type="text" class="form-control form-control-sm"/> 
					Doctor ID: <input id="doctorID" name="doctorID" type="text" class="form-control form-control-sm"/>
					Appointment Date:<input id="appointmentDate" name="appointmentDate" type="text" class="form-control form-control-sm"/> 
					Appointment Time:<input id="appointmentTime" name="appointmentTime" type="text" class="form-control form-control-sm"/>
					Token No:  <input id="tokenNo" name="tokenNo" type="text" class="form-control form-control-sm"/>
					Payment Type:  <input id="payType" name="payType" type="text" class="form-control form-control-sm"/>
					Amount:<input id="amount" name="amount" type="text" class="form-control form-control-sm"/>
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"/>
					<input type="hidden" id="hIDappointmentIDSave" name="hIDappointmentIDSave" value=""/>

				</form>



				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>


				
				<div id="divAppointmentGrid">
					<%
						Appointment appointmentObj = new Appointment();
					out.print(appointmentObj.readAppointment());
					%>
				</div>

			</div>
		</div>
	</div>


</body>

</html>