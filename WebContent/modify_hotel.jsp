<%@page import="vo.RoomVO"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Hotel" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    Hotel myHotel = (Hotel)session.getAttribute("myHotel");
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
    <link href="css/customer/sx-hotel-register.css" rel="stylesheet">

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

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4><strong>新店注册</strong></h4>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" action="updateHotel">
                <div class="col-md-6 sx-col-left">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">酒店名称</label>
                        <div class="col-sm-6">
                            <input name="user.hotel" type="text" class="form-control" placeholder="请输入名称" value="<%=myHotel.getHotel_name() %>" disabled>
                        </div>
                        <div class="col-sm-3">
                            <select name="hotel.star" class="form-control" disabled>
                                <option value="1"><%=myHotel.getStarString() %></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">注册资金</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input name="hotel.start_money" type="number" class="form-control"  placeholder="请填入数字，如20" value="<%=myHotel.getStart_money() %>" disabled>
                                <div class="input-group-addon">万元</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input name="hotel.name" type="text" class="form-control" placeholder="请输入姓名" value="<%=myHotel.getName() %>" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">身份证号</label>
                        <div class="col-sm-9">
                            <input name="hotel.id_num" type="number" class="form-control" placeholder="请输入身份证号" value="<%=myHotel.getId_num() %>" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input name="hotel.email" type="email" class="form-control" placeholder="xxxx@xxx.xx" value="<%=myHotel.getEmail() %>" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">酒店位置</label>
                        <div class="col-sm-9">
                            <select name="hotel.city" class="form-control" disabled>
                                <option value ="北京"><%=myHotel.getCity()%></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-9">
                            <textarea name="hotel.description" class="form-control" rows="6" value="请输入不超过200字的描述"></textarea>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 sx-col-right">
                	<%
                		for(RoomVO roomVO : myHotel.getRoomVOs()){
                			out.println("<div class=\"form-group\">");
                			out.println("<label class=\"col-sm-2 control-label\">房型"+roomVO.getType()+"</label>");
                			out.println("<div class=\"col-sm-4\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<input name=\"room"+roomVO.getType()+".capacity\" type=\"number\" class=\"form-control\" placeholder=\"容量\" value=\""+roomVO.getCapacity()+"\">");
                			out.println("<div class=\"input-group-addon\">人数(人)</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<div class=\"col-sm-5\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<input name=\"room"+roomVO.getType()+".num\" type=\"number\" class=\"form-control\" placeholder=\"数量\"  value=\""+roomVO.getNum()+"\">");
                			out.println("<div class=\"input-group-addon\">房间(间)</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<div class=\"form-group\">");
                			out.println("<label class=\"col-sm-2 control-label\"></label>");
                			out.println("<div class=\"col-sm-4\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<input name=\"room"+roomVO.getType()+".price\" type=\"number\" class=\"form-control\" placeholder=\"单价\" value=\""+roomVO.getPrice()+"\">");
                			out.println("<div class=\"input-group-addon\">单价(￥)</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<div class=\"col-sm-5\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<input name=\"room"+roomVO.getType()+".vip_price\" type=\"number\" class=\"form-control\" placeholder=\"会员价\" value=\""+roomVO.getVip_price()+"\">");
                			out.println("<div class=\"input-group-addon\">会员价(￥)</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<div class=\"form-group\">");
                			out.println("<label class=\"col-sm-2 control-label\"></label>");
                			out.println("<div class=\"col-sm-9\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<div class=\"input-group-addon\">上传图片</div>");
                			out.println("<input name=\"room"+roomVO.getType()+".image\" type=\"file\" class=\"form-control\">");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<hr>");
                		}
                	%>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <input type="reset" class="btn btn-default sx-right-float" value="重新输入">
                        </div>
                        <div class="col-sm-5">
                            <input type="submit" class="btn btn-primary" value="更新信息">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js"></script>
<script src="js/docs.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
