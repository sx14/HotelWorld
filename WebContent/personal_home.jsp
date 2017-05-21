
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="constant.Config"%>
<%@page import="constant.ApplyState"%>
<%@page import="model.Customer"%>
<%@page import="constant.OrderState"%>
<%@page import="constant.UserRole"%>
<%@page import="model.User"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Order" %>
<%@page import="model.Hotel" %>
<%@page import="model.RoomType" %>
<%@page import="model.Room" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    List<Order> orders = (List)request.getAttribute("orders");
    int consumeSum = (Integer)request.getAttribute("consumeSum");
    User user = (User)session.getAttribute("user");
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

    <title>Hotel World 个人主页</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="css/carousel.css" rel="stylesheet">
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
		        <div id="navbar" class="navbar-collapse collapse">
		            <ul class="nav navbar-nav" style="float:right">
		                <li><a href="chooseHotel">酒店信息</a></li>
		                <li class="active"><a href="personalHome">用户信息</a></li>
		                <li><a href="logout">退出登录</a></li>
		            </ul>
		        </div><!--/.nav-collapse -->
		    </div>
		</nav>

<div class="container theme-showcase" role="main">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron sx-jumbotron">
    </div>

    <div class="row">
        <div class="col-md-9">
            <div class="sx-col-left">
            	            	<%
            	if(myHotel != null){
            		out.println("<div class=\"panel panel-default\">");
            		out.println("<div class=\"panel-heading\">");
            		out.println("<strong>我的酒店</strong>");
            		out.println("</div>");
            		out.println("<div class=\"panel-body sx-border-bottom\">");
            		out.print("<label class=\"sx-label\"><strong>"+myHotel.getCity()+myHotel.getHotel_name()+"</strong><span class=\"label label-danger\">"+myHotel.getStarString());
            		if(myHotel.getState() == ApplyState.WAIT.getValue()){
            			out.println("</span>待审批</label>");
            		}else if(myHotel.getState() == ApplyState.PASS.getValue()){
            			out.println("</span>待开业</label>");
            		}else if(myHotel.getState() == ApplyState.OPEN.getValue()){            			
	            		out.println("</span>开业</label>");
            		}else{
            			out.println("</span>申请未通过</label>");
            		}
	            	out.println("<div class=\"sx-right\"><a href=\"modifyHotel\" class=\"btn btn-default\">查看详细信息</a></div>");
            		out.println("</div>");
            		out.println("<div class=\"row\">");
            		out.println("<div class=\"col-md-8 sx-vertical-line sx-content-center\">");
            		for(RoomType room : myHotel.getRoomTypes()){
            			out.println("<div class=\"row\">");
                		out.println("<div class=\"col-md-8\">");
                		out.println("<label class=\"sx-label\">房型"+room.getType()+"(容量"+room.getCapacity()+"人) 共"+room.getNum()+"间</label>");
                		out.println("</div>");
                		out.println("<div class=\"col-md-4\">");
                		out.println("<p><label class=\"sx-big-red\">"+room.getTimes()+"</label>次入住</p>");
                		out.println("</div>");
                		out.println("</div>");
            		}
            		out.println("</div>");
            		out.println("<div class=\"col-md-4 sx-block\">");
            		out.println("<h4 class=\"sx-head\"><strong>当月累计营业额</strong></h4>");
            		out.println("<hr>");
            		out.println("<h2 class=\"sx-content-center\"><strong>￥"+myHotel.getConsumeSum()+"</strong></h2>");
            		out.println("</div>");
            		out.println("</div>");
            		out.println("</div>");
            	}
            	%>
            
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong>我的订单</strong>
                    </div>
                    <div class="panel-body">
                        <table class="table sx-content-center">
                            <tbody>
                            <%
                            	for(Order o : orders){
                            		Hotel h = o.getHotel();
                            		Room r = o.getRoom();
                            		out.println("<tr>");
                            		out.println("<th>"+h.getCity()+h.getHotel_name()+"</th>");
                            		out.println("<td>"+r.getRoomType().getRoomType()+"</td>");
                            		out.println("<td>");
                            		out.println("<p><span class=\"label label-primary\">住</span>"+o.getInDateString()+"</p>");
                            		out.println("<p><span class=\"label label-success\">离</span>"+o.getOutDateString()+"</p>");
                            		out.println("</td>");
                            		if(o.getIs_vip() == 1){                            			
                            			out.println("<td><strong>￥"+o.getVip_price()+"</strong></td>");
                            		}else{
                            			out.println("<td><strong>￥"+o.getPrice()+"</strong></td>");
                            		}
                            		out.println("<td>"+o.getStateString()+"</td>");
                            		out.println("<td>");
                            		out.println("<a type=\"button\" class=\"btn btn-primary\" href=\"showOrder?oid="+o.getOid()+"\">订单详情</a>");
									out.println("</td>");
                            		out.println("</tr>");
                            	}
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="sx-col-left">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-md-4 sx-col-left sx-block">
                            <h4 class="sx-head"><strong>账户余额</strong></h4>
                            <hr>
							<%
								if(user.getVisa() != null){
									out.println("<h2 class=\"sx-content-center\"><strong>￥"+user.getMoney()+"</strong></h2>");
								}else{
									if(user.getMoney() > 0){
										out.println("<h2 class=\"sx-content-center\"><strong>￥"+user.getMoney()+"(已冻结)</strong></h2>");
									}else{
										out.println("<h2 class=\"sx-content-center\"><strong>￥"+user.getMoney()+"</strong></h2>");
									}
								}
							%>
                            
                        </div>
                        <div class="col-md-4 sx-block">
                            <h4 class="sx-head"><strong>累计消费</strong></h4>
                            <hr>
                            <h2 class="sx-content-center"><strong>￥<%=consumeSum %></strong></h2>
                        </div>
                        <div class="col-md-4 sx-col-left sx-content-center">
                            <h4 class="sx-head"><strong>充值</strong></h4>
                            <p><strong>银行卡号</strong>&nbsp;&nbsp;<strong>
                            <%
                            	if(user.getVisa() != null){
                            		out.print(user.getVisa().getNum());
                            	}else{
                            		out.print("尚未注册会员，未绑定银行卡");
                            	}
                            %>
                            </strong><p>
                            <%
                            	if(user.getVisa() != null){
                            		out.println("<button type=\"button\" class=\"btn btn-success sx-right-float\" data-toggle=\"modal\" data-target=\".bs-example-modal-sm\" data-whatever=\"@getbootstrap\">点我充值</button>");
                            	}else{
                            		out.println("<button type=\"button\" class=\"btn btn-success sx-right-float\" data-toggle=\"modal\" data-target=\".bs-example-modal-sm\" data-whatever=\"@getbootstrap\" disabled>点我充值</button>");
                            	}
                            %>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="sx-col-right sx-right sx-content-center">
                <div class="panel panel-default">
                    <div class="panel-body">
                    	<%
                    		if(user.getImage() == null){
                    			out.println("<img src=\"img/head/default_user.jpg\" class=\"img-rounded\" height=\"200\" width=\"200\">");
                    		}else{
                    			out.println("<img src=\""+user.getImage()+"\" class=\"img-rounded\" height=\"200\" width=\"200\">");
                    		}
                    	%>
                        
                        <h3><strong><%=user.getUsername() %></strong></h3>
                        <%
                       		if(user.getVisa() != null){
                       			out.println("<p>会员ID："+user.getVIPNum()+"</p>");
                       		}else{
                       			if(user.getEmail() != null){
                            		out.println("<p>"+user.getEmail()+"</p>");
                            	}
                       		}
                        %>
                        
                        <%
                        	if(user.getVisa() != null){
                        		if(user.isVIP()){                        			
                        			out.println("<label><span class=\"label label-success\">认证会员</span>"+user.getHiddenName()+"</label>");
                        		}else{
                        			out.println("<label><span class=\"label label-warning\">未激活会员</span>"+user.getHiddenName()+"</label>");
                        		}
                        		out.println("<p><a class=\"btn btn-default sx-big-button\" role=\"button\" href=\"register_vip.jsp\">修改个人信息</a></p>");
                        	}else{
                        		out.println("<label><span class=\"label label-danger\">普通用户</span>"+user.getHiddenPhone()+"</label>");
                        		out.println("<p><a class=\"btn btn-default sx-big-button\" role=\"button\" href=\"register_vip.jsp\">点我成为会员</a></p>");
                        	}
	                        out.println("<a href=\"register_hotel.jsp\" class=\"control-label\">想开店？点我注册&raquo;</a>");
                        %>
                    </div>
                </div>
           		<%if(user.isVIP()){
            		if(user.getMoney() < Config.VIP_MONEY_LEAST){
            			Date fail = user.getVIPFailDate();
            			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            			String failDate = format.format(fail);
            	%>
            		<div class="alert alert-info" role="alert"><strong>余额不足1000元！</strong>会员有效期至<%=failDate %>，请保持账户余额在1000元以上，否则过期将终止您的会员优惠权益。</div>
            	<%	}
            	}
            	%>
            </div>       
        </div>
    </div>
</div> <!-- /container -->     

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4>充值</h4>
            </div>
            <form action="chargeVIP" method="post">
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-3 control-label">金额</label>
                        <div class="col-md-8">
                            <input name="chargeMoney" type="number" class="form-control" placeholder="充值金额">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">密码</label>
                        <div class="col-md-8">
                            <input name="password" type="password" class="form-control" placeholder="6位支付密码">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-success" value="确认充值"/>
            </div>
            </form>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/docs.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
