<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.HotelDraft" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    List<HotelDraft> hotels = (List<HotelDraft>)session.getAttribute("hotelDrafts");
%>

<!DOCTYPE html>
<html lang="en">
<base href="<%=basePath%>"></base>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Hotel World 酒店管理</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/theme.css" rel="stylesheet">
    <link href="css/customer/sx-main.css" rel="stylesheet">
    <link href="css/customer/sx-style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="sx-background">

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
		            <a class="navbar-brand" href="">HotelWorld</a>
		        </div>
		        <div id="navbar" class="navbar-collapse collapse">
		            <ul class="nav navbar-nav" style="float:right">
		                <li class="active"><a href="chooseHotel">酒店信息</a></li>
		                <li><a href="personalHome">查看订单</a></li>
		                <li><a href="logout">退出登录</a></li>
		                <li class="dropdown">
		                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
		                    <ul class="dropdown-menu">
		                        <li><a href="#">Action</a></li>
		                        <li><a href="#">Another action</a></li>
		                        <li><a href="#">Something else here</a></li>
		                        <li role="separator" class="divider"></li>
		                        <li class="dropdown-header">Nav header</li>
		                        <li><a href="#">Separated link</a></li>
		                        <li><a href="#">One more separated link</a></li>
		                    </ul>
		                </li>
		            </ul>
		        </div><!--/.nav-collapse -->
		    </div>
		</nav>

<div class="container theme-showcase" role="main">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron sx-jumbotron">
    </div>

    <ul class="nav nav-tabs nav-justified">
        <li role="presentation"><a>新店注册</a></li>
        <li role="presentation" class="active"><a>店铺修改</a></li>
        <li role="presentation"><a>经营统计</a></li>
    </ul>
    <div class="panel sx-panel">
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr>
                    <th>提交日期</th>
                    <th>店名</th>
                    <th>法人</th>
                    <th>地区</th>
                    <th>操作</th>
                    <th>详情</th>

                </tr>
                </thead>
                <tbody>
                <% 
                	for(HotelDraft hotel : hotels){
                		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                		String date = format.format(hotel.getSubmit_date());
                		out.println("<tr>");
                		out.println("<th>"+date+"</th>");
                		out.println("<td>"+hotel.getHotel_name()+"</td>");
                		out.println("<td>"+hotel.getName()+"</td>");
                		out.println("<td>"+hotel.getCity()+"</td>");
                		out.println("<td><span class=\"label label-success\">申请加盟</span></td>");
                		out.println("<td>");
                		out.println("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#div"+hotel.getHid()+"\" data-whatever=\"@getbootstrap\">查看详情</button>");
                		out.println("</td>");
                		out.println("</tr>");
                	}
                
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
 <!-- /container -->


<%
	for(HotelDraft hotel : hotels){
		out.println("<div id =\"div"+hotel.getHid()+"\" class=\"modal fade \" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\">");	
		out.println("<div class=\"modal-dialog\" role=\"document\">");
		out.println("<div class=\"modal-content\">");
		out.println("<form >");
		
		out.println("<input name=\"hotel.hid\" type=\"hidden\" value=\""+hotel.getHid()+"\">");
		out.println("<div class=\"modal-header\">");
		out.println("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>");
		out.println("<h4 class=\"modal-title\" id=\"exampleModalLabel\"><strong>"+hotel.getCity()+hotel.getHotel_name()+"</strong><span class=\"label label-success\">请求加盟</span></h4>");
		out.println("</div>");
		out.println("<div class=\"modal-body\">");
		out.println("<div class=\"form-horizontal\">");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">酒店名称</label>");
		out.println("<div class=\"col-sm-6\">");
		out.println("<input name=\"user.hotel_name\" type=\"text\" class=\"form-control\" value=\""+hotel.getHotel_name()+"\" disabled>");
		out.println("</div>");
		out.println("<div class=\"col-sm-3\">");
		out.println("<select name=\"hotel.star\" class=\"form-control\" disabled>");
		out.println("<option value=\"1\">"+hotel.getStarString()+"</option>");
		out.println("</select>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">注册资金</label>");
		out.println("<div class=\"col-sm-9\">");
		out.println("<div class=\"input-group\">");
		out.println("<input name=\"hotel.start_money\" type=\"number\" class=\"form-control\"  value=\""+hotel.getStart_money()+"\" disabled>");
		out.println("<div class=\"input-group-addon\">万元</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">姓名</label>");
		out.println("<div class=\"col-sm-9\">");
		out.println("<input name=\"hotel.name\" type=\"text\" class=\"form-control\" value=\""+hotel.getName()+"\" disabled>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">电话</label>");
		out.println("<div class=\"col-sm-9\">");
		out.println("<input name=\"hotel.phone\" type=\"text\" class=\"form-control\" value=\""+hotel.getUser().getPhone()+"\" disabled>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">身份证号</label>");
		out.println("<div class=\"col-sm-9\">");
		out.println("<input name=\"hotel.id_num\" type=\"number\" class=\"form-control\" value=\""+hotel.getId_num()+"\" disabled>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">邮箱</label>");
		out.println("<div class=\"col-sm-9\">");
		out.println("<input name=\"hotel.email\" type=\"email\" class=\"form-control\" value=\""+hotel.getEmail()+"\" disabled>");
		out.println("</div>");
		out.println("</div>");
		out.println("<hr>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">酒店位置</label>");
		out.println("<div class=\"col-sm-9\">");
		out.println("<select name=\"hotel.city\" class=\"form-control\" disabled>");
		out.println("<option>"+hotel.getCity()+"</option>");
		out.println("</select>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">房型1</label>");
		out.println("<div class=\"col-sm-4\">");
		out.println("<input name=\"room1.capacity\" type=\"text\" class=\"form-control\"  value=\""+hotel.getRoomCapacityByType(1)+"人\" disabled>");
		out.println("</div>");
		out.println("<div class=\"col-sm-5\">");
		out.println("<div class=\"input-group\">");
		out.println("<input name=\"room1.num\" type=\"number\" class=\"form-control\" value=\""+hotel.getRoomNumByType(1)+"\" disabled>");
		out.println("<div class=\"input-group-addon\">间</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">房型2</label>");
		out.println("<div class=\"col-sm-4\">");
		out.println("<input name=\"room2.capacity\" type=\"text\" class=\"form-control\"  value=\""+hotel.getRoomCapacityByType(2)+"人\" disabled>");
		out.println("</div>");
		out.println("<div class=\"col-sm-5\">");
		out.println("<div class=\"input-group\">");
		out.println("<input name=\"room2.num\" type=\"number\" class=\"form-control\" value=\""+hotel.getRoomNumByType(2)+"\" disabled>");
		out.println("<div class=\"input-group-addon\">间</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class=\"form-group\">");
		out.println("<label class=\"col-sm-2 control-label\">房型3</label>");
		out.println("<div class=\"col-sm-4\">");
		out.println("<input name=\"room3.capacity\" type=\"text\" class=\"form-control\"  value=\""+hotel.getRoomCapacityByType(3)+"人\" disabled>");
		out.println("</div>");
		out.println("<div class=\"col-sm-5\">");
		out.println("<div class=\"input-group\">");
		out.println("<input name=\"room3.num\" type=\"number\" class=\"form-control\" value=\""+hotel.getRoomNumByType(3)+"\" disabled>");
		out.println("<div class=\"input-group-addon\">间</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");
		out.println("<div class=\"modal-footer\">");
		out.println("<form id=\"form"+hotel.getHid()+"\" method=\"get\" action=\"handleModifyHotel\">");
		out.println("<input name=\"state\" type=\"hidden\">");
		out.println("<input name=\"hdid\" type=\"hidden\" value=\""+hotel.getHdid()+"\">");
		out.println("<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">返回</button>");
		out.println("<input type=\"submit\" class=\"btn btn-primary\" onclick=\"pass("+hotel.getHid()+")\" value=\"通过\">");
		out.println("<input type=\"submit\" class=\"btn btn-danger\" onclick=\"unpass("+hotel.getHid()+")\" value=\"拒绝\">");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
	}
%>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/customer/manage_hotel.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="../../assets/js/docs.min.js"></script>
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

