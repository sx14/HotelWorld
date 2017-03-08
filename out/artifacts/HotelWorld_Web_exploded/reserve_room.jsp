<%@page import="model.Hotel"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Order" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    Order order = (Order)session.getAttribute("order");
    int customerNum = (Integer)session.getAttribute("customerNum");
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

    <title>Hotel World 房间预定</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/theme.css" rel="stylesheet">
    <link href="css/customer/sx-reserve-room.css" rel="stylesheet">
    <link href="css/customer/sx-style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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

      <div>
        <div>
          <div class="col-md-8 sx-col-left">
            <div class="panel panel-primary sx-border-blue">
              <div class="panel-body">
                <h4><strong>房间信息</strong></h4>
                <hr>
				<% 

    		  		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				%>
                <p><label class="sx-label-key">入住房型</label><span class="sx-label-value"><%=order.getRoom().getRoomType().getRoomType() %></span><span class="sx-label-value">(可入住<%=order.getRoom().getRoomType().getCapacity() %>人)</span></p>
                <p><label class="sx-label-key">入住日期</label><span class="sx-label-value"><%=format.format(order.getIn_date()) %></span><span class="sx-small-grey">至</span><span class="sx-label-value"><%=format.format(order.getOut_date()) %></span></p>
                <%
                	if(order.getIs_vip() == 1){
                		out.println("<p><label class=\"sx-label-key\">房间单价</label><span class=\"sx-label-value\"><span class=\"label label-success\">会员价</span>￥"+order.getVip_price()+"</span></p>");
                	}else{
                		out.println("<p><label class=\"sx-label-key\">房间单价</label><span class=\"sx-label-value\"><span class=\"label label-warning\">非会员</span>￥"+order.getPrice()+"</span></p>");
                	}
                %>
                
                <br>

                <h4><strong>入住信息</strong></h4>
                <hr>
                <form id="orderForm">
                <%
                	for(int i = 0 ; i < customerNum ; i ++){
                		out.println("<div class=\"form-group form-inline\">");
                		out.println("<label for=\"exampleInputName1\" class=\"sx-label-key\" >入住人"+(i+1)+"</label>");
                		out.println("<input name=\"customer"+(i+1)+".name\" type=\"text\" class=\"form-control\" id=\"exampleInputName1\" placeholder=\"入住人姓名\">");
                		out.println("<input name=\"customer"+(i+1)+".id_num\" type=\"number\" class=\"form-control\" placeholder=\"身份证号\">");
                		out.println("<input name=\"customer"+(i+1)+".oid\" type=\"hidden\" value=\""+order.getOid()+"\">");
                		out.println("</div>");
                	}
                %>

                  <div>
                    <label class="sx-label-key"></label>
                    <input type="button" class="btn btn-info" value="提交订单" onclick="submitOrder()">
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div class="col-md-4 sx-content-center sx-shadow sx-remove-padding sx-border-silver">
            <div class="row">
              <img src="../img/hotel-mid-1.jpg" class="sx-img-star-hotel">
              <div class="sx-padding-all">
              <%
              	Hotel hotel = (Hotel)session.getAttribute("hotel");
              %>
                <p class="sx-big-blue"><%=(hotel.getCity()+hotel.getHotel_name()) %></p>
                <p>地址：江苏省南京市鼓楼区汉口路22号</p>
                <p><span class="sx-big-blue"><%=String.format("%.1f",hotel.getAvgStar())%></span>/5分</p>
                <p>￥<span class="sx-big-red"><%=hotel.getLowestPrice() %></span>起</p>
                <p><span class="sx-small-blue"><%=hotel.getCommentNum() %>条评价</span></p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   	<script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/customer/sx-ajax.js"></script>
  </body>
</html>
