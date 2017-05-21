<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Hotel" %>
<%@page import="constant.City" %>
<%@page import="constant.HotelStar" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    List<Hotel> hotels = (List<Hotel>)request.getAttribute("hotels");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Hotel World酒店管理</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/customer/sx-style.css" rel="stylesheet">
    <link href="css/customer/sx-show-hotel-occupy.css" rel="stylesheet">
</head>

<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Bootstrap theme</a>
        </div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav" style="float:right">
		      	<li><a href="logout">退出登录</a></li>
		  	</ul>
		</div>
    </div>
</nav>

<div class="container theme-showcase" role="main">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron sx-jumbotron">
    </div>
    <ul class="nav nav-tabs nav-justified">
        <li role="presentation"><a href="manageNewHotel">新店注册</a></li>
        <li role="presentation"><a href="manageModifyHotel">店铺修改</a></li>
        <li role="presentation" class="active"><a href="#">经营统计</a></li>
    </ul>

    <div class="panel sx-panel">
        <div class="panel-body">
            <form class="form-inline container">
                    <div class="form-group sx-search">
                        <label>城市</label>
                        <select class="form-control" name="expectedCity">
                            <%
					          	String expectedCity = (String)session.getAttribute("expectedCity");
					          	for(City city : (City[])request.getAttribute("cities")){
					          		if(expectedCity.equals(city.getValue())){%>
					          			<option value="<%=city.getValue() %>" selected><%=city.getValue() %></option>
					          		<%}else{%>
					          			<option value="<%=city.getValue() %>"><%=city.getValue() %></option>
					          		<%}
					         }%>
                        </select>
                    </div>
                    <div class="form-group sx-search">
                        <label>星级</label>
                        <select class="form-control" name="level">
				          <%
				          	HotelStar[] levels = (HotelStar[])request.getAttribute("levels");
				          	int expectedLevel = (Integer)session.getAttribute("expectedLevel");
				          	for(int i = 0 ; i < levels.length ; i++){
				          		if(levels[i].getValue() == expectedLevel){%>
				          			<option value="<%=levels[i].getValue() %>" selected><%=levels[i].getName()+levels[i].getLowestPriceDesc() %></option>
				          		<%}else{%>
				          			<option value="<%=levels[i].getValue() %>"><%=levels[i].getName()+levels[i].getLowestPriceDesc() %></option>
				          		<%}
				          	}%>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">搜索店铺</button>
                </form>
            <hr>
            <table class="table">
                <thead>
                <tr>
                    <th>店名</th>
                    <th>级别</th>
                    <th>法人</th>
                    <th>地区</th>
                    <th>当月营业额</th>
                    <th>查看详细</th>

                </tr>
                </thead>
                <tbody>
                <%for(Hotel hotel : hotels){ %>
                <tr>
                    <td><%=hotel.getCity()+hotel.getHotel_name() %></td>
                    <td><span class="label label-danger"><%=hotel.getStarString() %></span></td>
                    <td><%=hotel.getName() %></td>
                    <td><%=hotel.getCity() %></td>
                    <td>￥<%=hotel.getConsumeSum() %></td>
                    <td>
                        <a type="button" class="btn btn-primary" href="showHotelStatistic?hid=<%=hotel.getHid() %>">查看详情</a>
                    </td>
                </tr>
                <%} %>
                </tbody>
            </table>
        </div>
    </div>
</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
