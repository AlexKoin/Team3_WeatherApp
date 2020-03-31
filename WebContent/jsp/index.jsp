<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="team3.weatherapp.WeatherAppController"%>
<%@ page import="team3.dbmanagement.DatabaseManager"%>
<%@ page import="team3.weatherapis.Weather" %>
<%@ page import="java.util.*" %>
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

<!-- Charts Script -->

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/highcharts-more.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/solid-gauge.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-base.min.js?hcode=c11e6e3cfefb406e8ce8d99fa8368d33"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-ui.min.js?hcode=c11e6e3cfefb406e8ce8d99fa8368d33"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-exports.min.js?hcode=c11e6e3cfefb406e8ce8d99fa8368d33"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-circular-gauge.min.js?hcode=c11e6e3cfefb406e8ce8d99fa8368d33"></script>
<script type="text/javascript">
//added java here for easier testing
<% 
float total = 0; int count = 0; // For average wind speed
List<Weather> weatherResults = WeatherAppController.getWeatherResults(request, response);   		//Create a List of params
List<String> chartData = new ArrayList<>();															//Temp, Humidity, Speed
for (Weather w : weatherResults) { 																	//For all APIs w 
	chartData.add(w.getTemperature());
	chartData.add(w.getHumidity());
	chartData.add(w.getWindSpeed());
	total += Float.parseFloat(w.getWindSpeed());
	count++;
}
%>
function addTemperatureChart(a,b,c,d,e,f) {
    var myChart = Highcharts.chart('chart1', {
    chart: {
        type: 'bar'
    },
    title: {
        text: 'Temerature in <%= request.getParameter("location") %>'
    },
    xAxis: {
        categories: ['openweathermap.org', 'weatherstack.com', 'weatherapi.com', 'climacell.co' , 'weatherbit.io' , 'darksky.net' ]
    },
    yAxis: {
        title: {
            text: '°C'
        }
    },
    series: [{
        name: 'Temperature',
        data: [a,b,c,d,e,f]
    },]
});
}
function addHumidityChart(a,b,c,d,e,f) {
    var myChart = Highcharts.chart('chart2', {
    chart: {
        type: 'bar'
    },
    title: {
        text: 'Humidity in <%= request.getParameter("location") %>'
    },
    xAxis: {
        categories: ['openweathermap.org', 'weatherstack.com', 'weatherapi.com', 'climacell.co' , 'weatherbit.io' , 'darksky.net' ]
    },
    yAxis: {
        title: {
            text: '%'
        }
    },
    series: [{
        name: 'Humidity',
        data: [a,b,c,d,e,f]
    },]
});
}
function addWindSpeedChart(a,b,c,d,e,f) {

    var myChart = Highcharts.chart('chart3', {
    chart: {
        type: 'bar'
    },
    title: {
        text: 'Wind Speed in <%= request.getParameter("location") %>'
    },
    xAxis: {
        categories: ['openweathermap.org', 'weatherstack.com', 'weatherapi.com', 'climacell.co' , 'weatherbit.io' , 'darksky.net' ]
    },
    yAxis: {
        title: {
            text: 'M/s'
        }
    },
    series: [{
        name: 'Wind Speed',
        data: [a,b,c,d,e,f]
    },]
});

}

function addWindChart(a,b) {
    var gauge = anychart.gauges.circular();
    gauge.fill('#fff')
            .stroke(null)
            .padding(5)
            .margin(0)
            .startAngle(0)
            .sweepAngle(360);

    gauge.axis().labels()
            .padding(3)
            .position('outside')
            .format('{%Value}\u00B0');

    gauge.data([a, b]);     //change for Direction , Speed

    gauge.axis().scale()
            .minimum(0)
            .maximum(360)
            .ticks({interval: 45})
            .minorTicks({interval: 10});

    gauge.axis()
            .fill('#7c868e')
            .startAngle(0)
            .sweepAngle(-360)
            .width(1)
            .ticks(
                    {
                        type: 'line',
                        fill: '#7c868e',
                        length: 4,
                        position: 'outside'
                    }
            );

    gauge.axis(1)
            .fill('#7c868e')
            .startAngle(270)
            .radius(40)
            .sweepAngle(180)
            .width(1)
            .ticks(
                    {
                        type: 'line',
                        fill: '#7c868e',
                        length: 4,
                        position: 'outside'
                    }
            );

    gauge.axis(1).labels()
            .padding(3)
            .position('outside')
            .format('{%Value} m/s');

    gauge.axis(1).scale()
            .minimum(0)
            .maximum(25)
            .ticks({interval: 5})
            .minorTicks({interval: 1});

    gauge.title()
            .padding(0)
            .margin([0, 0, 10, 0]);

    gauge.marker()
            .fill('#64b5f6')
            .stroke(null)
            .size('15%')
            .zIndex(120)
            .radius('97%');

    gauge.needle()
            .fill('#1976d2')
            .stroke(null)
            .axisIndex(1)
            .startRadius('6%')
            .endRadius('38%')
            .startWidth('2%')
            .middleWidth(null)
            .endWidth('0');

    gauge.cap()
            .radius('4%')
            .fill('#1976d2')
            .enabled(true)
            .stroke(null);

    var bigTooltipTitleSettings = {
        fontFamily: "'Verdana', Helvetica, Arial, sans-serif",
        fontWeight: 'normal',
        fontSize: '12px',
        hAlign: 'left',
        fontColor: '#212121'
    };

    gauge.label()
            .text('<span style="color: #64B5F6; font-size: 10px">Wind Direction: </span>' +
                    '<span style="color: #5AA3DD; font-size: 12px">' + a + '\u00B0 (+/- 0.5\u00B0)' + '</span><br>' +
                    '<span style="color: #1976d2; font-size: 10px">Average Wind Speed:</span> ' +
                    '<span style="color: #166ABD; font-size: 12px">' + b + 'm/s' + '</span>').useHtml(true)
            .textSettings(bigTooltipTitleSettings);
    gauge.label()
            .hAlign('center')
            .anchor('center-top')
            .offsetY(-20)
            .padding(15, 20)
            .background(
                    {
                        fill: '#fff',
                        stroke: {
                            thickness: 1,
                            color: '#E0F0FD'
                        }
                    }
            );

    // set container id for the chart
    gauge.container('chart4');

    // initiate chart drawing
    gauge.draw();
}

//Draws all charts into divs id 'chart1' --> to 'chart4'
function addCharts() {
	addTemperatureChart(<%=chartData.get(0)%>, <%=chartData.get(3)%> ,<%=chartData.get(6)%>, <%=chartData.get(9)%>, <%=chartData.get(12)%>, <%=chartData.get(15)%>);
	addHumidityChart(<%=chartData.get(1)%>, <%=chartData.get(4)%> ,<%=chartData.get(7)%>, <%=chartData.get(10)%>, <%=chartData.get(13)%>, <%=chartData.get(16)%>);
	addWindSpeedChart(<%=chartData.get(2)%>, <%=chartData.get(5)%> ,<%=chartData.get(8)%>, <%=chartData.get(11)%>, <%=chartData.get(14)%>, <%=chartData.get(17)%>);
	addWindChart(120,<%= String.format("%.02f", total/count) %>);  //default direction 120 for testing
	return false;
}

</script>

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
			
        			<option>${listItem}</option>
   			
<!-- 				<select name="display" class="custom-select" id="inputGroupSelect03">
					<option value="table">Table display</option>
					<option value="average">Average data</option>
				</select>
				 -->
				</select>
				<div class="input-group-append">
					<button class="btn btn-outline-primary" type="submit">Get</button>
					<button id = "chart" class="btn btn-outline-primary" type = "button" onclick = "addCharts()">View Charts</button>
				</div>
			</div>
			<div class="input-group mb-3">
				<select name="display" class="custom-select" id="inputGroupSelect03">
					<option value="table">Table display</option>
					<option value="average">Average data</option>
					<option value="charts">Charts display</option>
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
