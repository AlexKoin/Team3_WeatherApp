<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="team3.weatherapp.WeatherAppController"%>
<%@ page import="team3.dbmanagement.DatabaseManager"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<!--  Google Web Fonts -->
<link href="https://fonts.googleapis.com/css?family=Baloo+Da+2|Catamaran:900|Titillium+Web:900&display=swap" rel="stylesheet">

<style><%@include file="/css/style.css"%></style>

<title>Weather Forecast App</title>
</head>
<body class="bg-accent">
	<!-- Page Header -->
	<div class="jumbotron jumbotron-fluid bg-light text-center">
		<p>Accenture Java/Software Bootcamp</p>
		<h1>Team 3: Weather Forecast App</h1>
	</div>
	
	<!-- User input -->
	<div class="container bg-light rounded p-3">
		<form>
			<div class="input-group mb-3">
				<select class="custom-select" id="inputGroupSelect03" name="location">
				<option disabled="disabled" selected="selected">Choose city...</option>
				 <c:forEach items="<%= DatabaseManager.getCityNameList() %>" var="listItem">
        			<option>${listItem}</option>
   				 </c:forEach>
<!-- 				<select name="display" class="custom-select" id="inputGroupSelect03">
					<option value="table">Table display</option>
					<option value="average">Average data</option>
				</select>
				 -->
				</select>
				<div class="input-group-append">
					<button class="btn btn-outline-primary" type="submit">Get</button>
				</div>
			</div>
			<div class="input-group mb-3">
				<select name="display" class="custom-select" id="inputGroupSelect03">
					<option value="table">Table display</option>
					<option value="average">Average data</option>
				</select>
			</div>
		</form>
		
		<!-- Weather results -->
		<%= WeatherAppController.formatWeatherResults(request, response) %>
		
	</div>
	
	<!-- Page Footer -->
	<footer class="page-footer fixed-bottom font-small bg-light">
		<!-- Copyright -->
		<div class="footer-copyright text-center py-3 text-muted">© 2020 Team 3 | 6THS | 3DQV | Riuz | aXeY | 5bFA</div>
		<!-- Copyright -->
	</footer>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>