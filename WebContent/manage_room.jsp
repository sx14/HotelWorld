<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Customer"%>
<%@page import="model.User"%>
<%@page import="model.RoomType"%>
<%@page import="model.Hotel" %>
<%@page import="model.Room" %>
<%@page import="model.Order" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    Hotel hotel = (Hotel)session.getAttribute("hotel");
    String inDate = (String)request.getAttribute("inDate");
    String outDate = (String)request.getAttribute("outDate");
    
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

    <title>Theme Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/theme.css" rel="stylesheet">
    <link href="css/customer/sx-update-room.css" rel="stylesheet">

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
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
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

    <div class="panel panel-default">
        <div class="panel-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-3">日期</label>
                    <div class="col-md-3">
                        <input name="inDate" type="date" class="form-control" value="<%=inDate%>">
                    </div>
                    <div class="col-md-3">
                        <input name="outDate" type="date" class="form-control" value="<%=outDate %>">
                    </div>
                    <input type="submit" class="btn btn-primary" value="搜索">
                </div>
            </form>
        </div>
    </div>
    
    <%
    	for(RoomType roomType : hotel.getRoomTypes()){
    		out.println("<div class=\"panel panel-info\">");
    		out.println("<div class=\"panel-heading\">");
    		out.println("<h4><strong>房型"+roomType.getType()+"<span class=\"label label-success\">空房"+roomType.getEmptyRoomNum()+"间</span></strong></h4>");
    		out.println("</div>");
    		out.println("<div class=\"panel-body\">");
    		out.println("<table class=\"table\">");
    		out.println("<thead>");
    		out.println("<tr>");
    		out.println("<th>房间号</th>");
    		out.println("<th>当前状态</th>");
    		out.println("<th>操作</th>");
    		out.println("</tr>");
    		out.println("</thead>");
    		out.println("<tbody>");
    		for(Room room : roomType.getRooms()){
    			out.println("<tr>");
    			out.println("<th>"+room.getRoomName()+"</th>");
    			out.println("<td>"+room.getState()+"</td>");
    			out.println("<td>");
    			out.println("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#div"+room.getRid()+"\" data-whatever=\"@getbootstrap\">操作</button>");
    			out.println("</td>");
    			out.println("</tr>");
    		}
    		out.println("</tbody>");
    		out.println("</table>");
    		out.println("</div>");
    		out.println("</div>");
    	}
    %>
</div> <!-- /container -->


<%
	for(RoomType roomType : hotel.getRoomTypes()){
		for(Room room : roomType.getRooms()){
			out.println("<div class=\"modal fade\" id=\"div"+room.getRid()+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\">");
			out.println("<div class=\"modal-dialog\" role=\"document\">");
			out.println("<div class=\"modal-content\">");
			out.println("<div class=\"modal-header\">");//header
			out.println("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>");
			out.println("<h4 class=\"modal-title\" id=\"exampleModalLabel\">房间"+room.getRoomName()+"<span class=\"label label-danger\">"+room.getState()+"</span></h4>");
			out.println("</div>");//header
			out.println("<div class=\"modal-body\">");//body
			out.println("<form class=\"form-horizontal\" action=\"handleOrder\" method=\"post\">");
			out.println("<input name=\"order.hid\" type=\"hidden\" class=\"form-control\" value=\""+hotel.getHid()+"\">");
			out.println("<input name=\"rid\" type=\"hidden\" class=\"form-control\" value=\""+room.getRid()+"\">");
			if(room.getState().equals("已预定") || room.getState().equals("已入住")){
				Order order = room.getOrder();
				out.println("<input name=\"order.oid\" type=\"hidden\" class=\"form-control\" value=\""+order.getOid()+"\">");
				out.println("<div class=\"form-group\">");
				out.println("<label class=\"control-label col-md-2\">姓名</label>");
				out.println("<div class=\"col-md-10\">");
				out.println("<input name=\"order.user.name\" type=\"text\" class=\"form-control\" value=\""+order.getUser().getName()+"\">");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class=\"form-group\">");
				out.println("<label class=\"control-label col-md-2\" value=\""+order.getUser().getPhone()+"\">联系电话</label>");
				out.println("<div class=\"col-md-10\">");
				out.println("<input name=\"order.user.phone\" type=\"text\" class=\"col-md-10 form-control\" value=\""+order.getUser().getPhone()+"\">");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class=\"form-group\">");
				out.println("<label class=\"control-label col-md-2\">日期</label>");
				out.println("<div class=\"col-md-5\">");
				out.println("<input name=\"order.in_date\" type=\"date\" class=\"form-control col-md-5\" value=\""+order.getInDateString()+"\">");
				out.println("</div>");
				out.println("<div class=\"col-md-5\">");
				out.println("<input name=\"order.out_date\" type=\"date\" class=\"form-control col-md-5\" value=\""+order.getOutDateString()+"\">");
				out.println("</div>");
				out.println("</div>");
				out.println("<hr>");
				out.println("<h5>入住人信息</h5>");
				for(Customer customer : order.getCustomers()){
					out.println("<div class=\"form-group\">");
					out.println("<div class=\"col-md-4\">");
					out.println("<input type=\"text\" class=\"form-control\" placeholder=\"姓名\" value=\""+customer.getName()+"\">");
					out.println("</div>");
					out.println("<div class=\"col-md-8\">");
					out.println("<input type=\"text\" class=\"form-control\" placeholder=\"身份证号\" value=\""+customer.getId_num()+"\" >");
					out.println("</div>");
					out.println("</div>");
				}
				}else{
				out.println("<div class=\"form-group\">");
				out.println("<label class=\"control-label col-md-2\">姓名</label>");
				out.println("<div class=\"col-md-10\">");
				out.println("<input name=\"order.user.name\" type=\"text\" class=\"form-control\">");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class=\"form-group\">");
				out.println("<label class=\"control-label col-md-2\">联系电话</label>");
				out.println("<div class=\"col-md-10\">");
				out.println("<input name=\"order.user.phone\" type=\"text\" class=\"col-md-10 form-control\">");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class=\"form-group\">");
				out.println("<label class=\"control-label col-md-2\">日期</label>");
				out.println("<div class=\"col-md-5\">");
				out.println("<input name=\"order.in_date\" type=\"date\" class=\"form-control col-md-5\" value=\""+inDate+"\">");
				out.println("</div>");
				out.println("<div class=\"col-md-5\">");
				out.println("<input name=\"order.out_date\" type=\"date\" class=\"form-control col-md-5\" value=\""+outDate+"\">");
				out.println("</div>");
				out.println("</div>");
				out.println("<hr>");
				out.println("<h5>入住人信息</h5>");
				for(int i = 0 ; i < 3 ; i ++){
					out.println("<div class=\"form-group\">");
					out.println("<div class=\"col-md-4\">");
					out.println("<input name=\"customer"+(i+1)+".name\" type=\"text\" class=\"form-control\" placeholder=\"姓名\">");
					out.println("</div>");
					out.println("<div class=\"col-md-8\">");
					out.println("<input name=\"customer"+(i+1)+".id_num\" type=\"text\" class=\"form-control\" placeholder=\"身份证号\">");
					out.println("</div>");
					out.println("</div>");
				}
			}
			out.println("<hr>");
			out.println("<h5>支付信息</h5>");
			out.println("<div class=\"form-group\">");
			out.println("<label class=\"control-label col-md-2\">支付方式</label>");
			out.println("<div class=\"col-md-2\">");
			out.println("<select name=\"order.is_vip\" class=\"form-control\">");
			out.println("<option value=\"0\">现金</option>");
			out.println("<option value=\"1\">会员卡</option>");
			out.println("</select>");
			out.println("</div>");
			out.println("<div class=\"col-md-8\">");
			out.println("<input type=\"number\" class=\"form-control\" placeholder=\"会员卡号\">");
			out.println("</div>");
			out.println("</div>");
			out.println("<span class=\"label label-success\">房费</span><label>1000￥</label><label>(会员价1￥)</label>");
			out.println("</div>");
			out.println("<div class=\"modal-footer\">");
			out.println("<input type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"返回\">");
			if(room.getState().equals("已预定")){
				out.println("<input name=\"operate\" type=\"submit\" class=\"btn btn-danger\" value=\"取消预定\">");
				out.println("<input name=\"operate\" type=\"submit\" class=\"btn btn-primary\" value=\"入住\">");
			}else if(room.getState().equals("已入住")){
				out.println("<input name=\"operate\" type=\"submit\" class=\"btn btn-primary\" value=\"退房\">");
			}else if(room.getState().equals("空闲")){
				out.println("<input name=\"operate\" type=\"submit\" class=\"btn btn-primary\" value=\"入住\">");
			}else{
				out.println("<input name=\"operate\" type=\"submit\" class=\"btn btn-primary\" value=\"错误\">");
			}
			out.println("</form>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
		}
	}
%>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="assets/js/docs.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
