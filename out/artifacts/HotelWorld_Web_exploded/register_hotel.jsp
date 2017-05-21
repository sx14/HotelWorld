<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
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

    <title>Hotel World 注册酒店</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <!-- Custom styles for this template -->
    <link href="css/customer/sx-hotel-register.css" rel="stylesheet">
	<link href="css/customer/sx-style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
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
		                <li><a href="personalHome">用户信息</a></li>
		                <li><a href="logout">退出登录</a></li>
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
            <form id="hotelForm" class="form-horizontal">
                <div class="col-md-6 sx-col-left">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">酒店名称</label>
                        <div class="col-sm-6">
                            <input name="hotel.hotel_name" type="text" class="form-control" placeholder="请输入名称">
                        </div>
                        <div class="col-sm-3">
                            <select name="hotel.star" class="form-control">
                                <option value="1">A级</option>
                                <option value="2">B级</option>
                                <option value="3">C级</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">注册资金</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input name="hotel.start_money" type="number" class="form-control"  placeholder="请填入数字，如20">
                                <div class="input-group-addon">万元</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input name="hotel.name" type="text" class="form-control" placeholder="请输入姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">身份证号</label>
                        <div class="col-sm-9">
                            <input name="hotel.id_num" type="number" class="form-control" placeholder="请输入身份证号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input name="hotel.email" type="email" class="form-control" placeholder="xxxx@xxx.xx">
                        </div>
                    </div>
                </div>
                <div class="col-md-6 sx-col-right">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所在城市</label>
                        <div class="col-sm-9">
                            <select name="hotel.city" class="form-control" >
                                <option value ="北京">北京</option>
                                <option value ="上海">上海</option>
                                <option value="广州">广州</option>
                                <option value="深圳">深圳</option>
                                <option value="南京">南京</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">位置</label>
                        <div class="col-sm-9">
                            <input name="hotel.position" type="text" class="form-control"  placeholder="请填入详细位置">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">房型1</label>
                        <div class="col-sm-4">
                            <input name="room1.capacity" type="number" class="form-control"  placeholder="房间人数" value="1" readonly>
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input name="room1.num" type="number" class="form-control" placeholder="数量">
                                <div class="input-group-addon">间</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">房型2</label>
                        <div class="col-sm-4">
                            <input name="room2.capacity" type="number" class="form-control"  placeholder="房间人数" value="2" readonly>
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input name="room2.num" type="number" class="form-control" placeholder="数量">
                                <div class="input-group-addon">间</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">房型3</label>
                        <div class="col-sm-4">
                            <input name="room3.capacity" type="number" class="form-control"  placeholder="房间人数" value="3" readonly>
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input name="room3.num" type="number" class="form-control" placeholder="数量">
                                <div class="input-group-addon">间</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <input class="btn btn-default sx-right-float"  onclick ="reset(hotelForm)" value="重新输入">
                        </div>
                        <div class="col-sm-5">
                            <input class="btn btn-primary" value="确认注册" onclick="registerHotel()">
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
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/customer/sx-ajax.js"></script>
</body>
</html>
