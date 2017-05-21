<%@page import="constant.ApplyState"%>
<%@page import="model.Room"%>
<%@page import="model.RoomType"%>
<%@page import="model.Hotel"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    Hotel myHotel = (Hotel)session.getAttribute("myHotel");
    String readonly = "";
    String disabled = "";
    if(myHotel.getState() == ApplyState.WAIT.getValue() || myHotel.getState() == ApplyState.UNPASS.getValue()){
    	readonly = "readonly=\"readonly\"";
    	disabled = "disabled";
    }
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

    <title>Hotel World 修改酒店信息</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/theme.css" rel="stylesheet">
    <link href="css/customer/sx-hotel-register.css" rel="stylesheet">
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
                <li><a href="manageRoom">房间预订</a></li>
                <li class="active"><a href="#">酒店管理</a></li>
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
            <h4><strong>店铺</strong>
            	<%
            		if(myHotel.getState() == ApplyState.WAIT.getValue()){
            			out.println("<span class=\"label label-warning\">"+myHotel.getStateString()+"</span>");
            		}else if(myHotel.getState() == ApplyState.PASS.getValue()){
            			out.println("<span class=\"label label-success\">"+myHotel.getStateString()+"</span>");
            		}else if(myHotel.getState() == ApplyState.UNPASS.getValue()){
            			out.println("<span class=\"label label-default\">"+myHotel.getStateString()+"</span>");
            		}else if(myHotel.getState() == ApplyState.OPEN.getValue()){
            			out.println("<span class=\"label label-danger\">"+myHotel.getStateString()+"</span>");
            		}
            	%>
            </h4>
        </div>
        <div class="panel-body">
            <form id="hotelForm" class="form-horizontal" enctype="multipart/form-data">
                <div class="col-md-6 sx-col-left">
                    <div class="form-group">
                    	<input name="hotel.hid" value="<%=myHotel.getHid()%>" type="hidden" >
                    	<input name="hotel.star" value="<%=myHotel.getStar()%>" type="hidden" >
                    	<input name="hotel.state" value="<%=myHotel.getState()%>" type="hidden" >
                    	<input name="hotel.register_date" value="<%=myHotel.getRegister_date()%>" type="hidden">
                    	<input name="hotel.uid" value="<%=myHotel.getUser().getUid()%>" type="hidden">
                        <label class="col-sm-2 control-label">酒店名称</label>
                        <div class="col-sm-6">
                            <input name="hotel.hotel_name" type="text" class="form-control" placeholder="请输入名称" value="<%=myHotel.getHotel_name() %>" readonly="readonly">
                        </div>
                        <div class="col-sm-3">
                            <select name="hotel.star" class="form-control" disabled>
                                <option value="<%=myHotel.getStar() %>"><%=myHotel.getStarString() %></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">注册资金</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input name="hotel.start_money" type="number" class="form-control"  placeholder="请填入数字，如20" value="<%=myHotel.getStart_money() %>" readonly="readonly">
                                <div class="input-group-addon">万元</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input name="hotel.name" type="text" class="form-control" placeholder="请输入姓名" value="<%=myHotel.getName() %>" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">身份证号</label>
                        <div class="col-sm-9">
                            <input name="hotel.id_num" type="number" class="form-control" placeholder="请输入身份证号" value="<%=myHotel.getId_num() %>" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input name="hotel.email" type="email" class="form-control" placeholder="xxxx@xxx.xx" value="<%=myHotel.getEmail() %>" <%=readonly %>>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">酒店位置</label>
                        <div class="col-sm-9">
                            <select name="hotel.city" class="form-control" <%=disabled %>>
                                <option value ="<%=myHotel.getCity() %>" selected><%=myHotel.getCity()%></option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-9">
                            <textarea name="hotel.description" class="form-control" rows="6" placeholder="请输入不超过200字的描述" <%=readonly %>><%=myHotel.getDescription() %></textarea>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                            <label class="col-sm-2 control-label">酒店图片</label>
                            <div class="col-sm-9">
                                <div class="input-group">
                                    <input name="hotel.imgS" type="file" class="form-control" <%=readonly %>>
                                    <div class="input-group-addon">小图</div>
                                </div>
                            </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input name="hotel.imgM" type="file" class="form-control" <%=readonly %>>
                                <div class="input-group-addon">中图</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input name="hotel.imgB" type="file" class="form-control"<%=readonly %>>
                                <div class="input-group-addon">大图</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 sx-col-right">
                	<%
                		for(RoomType room : myHotel.getRoomTypes()){
                			out.println("<div class=\"form-group\">");
                			out.println("<input type=\"hidden\" name=\"room"+room.getType()+".type\" value=\""+room.getType()+"\">");
                			out.println("<input type=\"hidden\" name=\"room"+room.getType()+".tid\" value=\""+room.getTid()+"\">");
                			out.println("<label class=\"col-sm-2 control-label\">房型"+room.getType()+"</label>");
                			out.println("<div class=\"col-sm-4\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<input name=\"room"+room.getType()+".capacity\" type=\"number\" class=\"form-control\" placeholder=\"容量\" value=\""+room.getCapacity()+"\" "+readonly+">");
                			out.println("<div class=\"input-group-addon\">人数(人)</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<div class=\"col-sm-5\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<input name=\"room"+room.getType()+".num\" type=\"number\" class=\"form-control\" placeholder=\"数量\"  value=\""+room.getNum()+"\" "+readonly+">");
                			out.println("<div class=\"input-group-addon\">房间(间)</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<div class=\"form-group\">");
                			out.println("<label class=\"col-sm-2 control-label\"></label>");
                			out.println("<div class=\"col-sm-4\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<input name=\"room"+room.getType()+".price\" type=\"number\" class=\"form-control\" placeholder=\"单价\" value=\""+room.getPrice()+"\" "+readonly+">");
                			out.println("<div class=\"input-group-addon\">单价(￥)</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<div class=\"col-sm-5\">");
                			out.println("<div class=\"input-group\">");
                			out.println("<input name=\"room"+room.getType()+".vip_price\" type=\"number\" class=\"form-control\" placeholder=\"会员价\" value=\""+room.getVip_price()+"\" "+readonly+">");
                			out.println("<div class=\"input-group-addon\">会员价(￥)</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<div class=\"form-group\">");
                			out.println("<label class=\"col-sm-2 control-label\"></label>");
                			out.println("<div class=\"col-sm-9\">");
                			out.println("<input name=\"room"+room.getType()+".img\" type=\"file\" class=\"form-control\" "+readonly+">");
                			out.println("</div>");
                			out.println("</div>");
                			out.println("<hr>");
                		}
                	%>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <input name="btnInForm" class="btn btn-default sx-right-float" onclick="reset('hotelForm')" value="修改">
                        </div>
                        <div class="col-sm-5">
                            <input name="btnInForm" class="btn btn-primary" onclick="confirmAndSubmit('hotelForm','updateHotel')" value="更新信息" <%=disabled %>>
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
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
