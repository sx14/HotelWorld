<%@page import="constant.OrderState"%>
<%@page import="model.Customer"%>
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

    <title>Hotel World 订单详情</title>

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
		                <li><a href="chooseHotel">酒店信息</a></li>
		                <li class="active"><a href="personalHome">查看订单</a></li>
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
                <h4><strong>房间信息<span class="label label-primary sx-margin-left"><%=order.getStateString() %></span></strong></h4>
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
				<div>
				<%int i = 1;
					for(Customer customer : order.getCustomers()){ %>
                	<p><label class="sx-label-key">入住人<%=i %></label><span class="sx-label-value"><%=customer.getName() %></span><span class="sx-label-value"><%=customer.getId_num() %></span></p>
                	
                <% i++;}%>
                </div>
                <br>
                <%if(order.getState() == OrderState.OUT.getValue()){ %>
                <h4><strong>用户评价</strong></h4>
                <hr>
                <div>
                  	<form action="saveComment" method="post">
                    	<div class="form-group row">
                     	<div class="col-md-2">
                        	<select name="order.star" class="form-control">
                         		<option value=1>1颗星</option>
                         		<option value=2>2颗星</option>
                               	<option value=3>3颗星</option>
                            	<option value=4>4颗星</option>
                          		<option value=5>5颗星</option>
                           </select>
                      	</div>
         				<div class="col-md-8">
                          	<input name="order.comment_head" type="text" class="form-control" placeholder="请输入标题">
                 		</div>
                        </div>
                        <div class="row">
                        	<div class="col-md-10">
                         		<textarea name="order.comment_content" class="form-control" rows="5">请输入评价内容</textarea>
                  			</div>
                        </div>
                        <hr>
                        <div class="row">
                           	<div class="col-md-10">
                               	<input type="submit" class="btn btn-default sx-right sx-margin-left" value="提交评价">
                             	<a type="button" class="btn btn-primary sx-right" href="personalHome">返回</a>
                     		</div>
               			</div>                  		
                  	</form>
               	</div>
               	<%} else if (order.getState() == OrderState.JUDGE.getValue()){%>
                <h4><strong>用户评价</strong></h4>
                <hr>
                <div>
                  	<form action="saveComment" method="post">
                    	<div class="form-group row">
                     	<div class="col-md-2">
                        	<select name="order.star" class="form-control" disabled>
                        		<%for(int j = 1 ; j <=5 ; j++){
                        			if(j == order.getStar()){
                        				out.println("<option value=\""+j+"\" selected>"+j+"颗星</option>");
                        			}else{
                        				out.println("<option value=\""+j+"\">"+j+"颗星</option>");
                        			}
                        		}%>
                           </select>
                      	</div>
         				<div class="col-md-8">
                          	<input name="order.comment_head" type="text" class="form-control" value="<%=order.getComment_head()%>" readonly>
                 		</div>
                        </div>
                        <div class="row">
                        	<div class="col-md-10">
                         		<textarea name="order.comment_content" class="form-control" rows="5" readonly><%=order.getComment_content()%></textarea>
                  			</div>
                        </div>
                        <hr>
                        <div class="row">
                           	<div class="col-md-10">
                               	<input type="button" class="btn btn-default sx-right sx-margin-left" value="已评价" disabled>
                             	<a type="button" class="btn btn-primary sx-right" href="personalHome">返回</a>
                     		</div>
               			</div>                  		
                  	</form>
               	</div>               	
               	<%}else{%>
               	<h4><strong>用户评价</strong></h4>
                <hr>
                <div>
                  	<form action="saveComment" method="post">
                    	<div class="form-group row">
                     	<div class="col-md-2">
                        	<select name="order.star" class="form-control" disabled>
                        		<option value=1>1颗星</option>
                           </select>
                      	</div>
         				<div class="col-md-8">
                          	<input name="order.comment_head" type="text" class="form-control" placeholder="请输入标题" disabled>
                 		</div>
                        </div>
                        <div class="row">
                        	<div class="col-md-10">
                         		<textarea name="order.comment_content" class="form-control" rows="5" disabled></textarea>
                  			</div>
                        </div>
                        <hr>
                        <div class="row">
                           	<div class="col-md-10">
                           		<%if(order.getState() == OrderState.RESERVE.getValue()){%>
                               	<a type="button" class="btn btn-danger sx-right sx-margin-left" href="cancelOrder?oid=<%=order.getOid()%>">取消预订</a>
                               	<%}else{%>
                               	<input type="button" class="btn btn-danger sx-right sx-margin-left" value="取消预订" disabled>
                               	<%}%>
                             	<a type="button" class="btn btn-primary sx-right" href="personalHome">返回</a>
                     		</div>
               			</div>                  		
                  	</form>
               	</div>               	
               	<%}%>
              </div>
            </div>
          </div>
          <div class="col-md-4 sx-content-center sx-shadow sx-remove-padding sx-border-silver">
            <div class="row">
              <img src="../img/hotel-mid-1.jpg" class="sx-img-star-hotel">
              <div class="sx-padding-all">
              <%
              	Hotel hotel = order.getHotel();
              %>
                <p class="sx-big-blue"><%=(hotel.getCity()+hotel.getHotel_name()) %></p>
                <p>地址：江苏省南京市鼓楼区汉口路22号</p>
                <p><span class="sx-big-blue"><%=hotel.getAvgStar() %></span>/5分</p>
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
