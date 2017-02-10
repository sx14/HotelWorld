<%@page import="model.User"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Order" %>
<%@page import="model.Hotel" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    List<Order> orders = (List)request.getAttribute("orders");
    int consumeSum = (Integer)request.getAttribute("consumeSum");
    User user = (User)session.getAttribute("user");
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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="../css/theme.css" rel="stylesheet">
    <link href="../css/customer/sx-reserve-room.css" rel="stylesheet">
    <link href="../css/customer/sx-style.css" rel="stylesheet">

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

    <div>
        <div>
            <div class="col-md-9 sx-col-left">
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
                            		out.println("<tr>");
                            		out.println("<th>"+h.getCity()+h.getHotel_name()+"</th>");
                            		out.println("<td>单人房</td>");
                            		out.println("<td>");
                            		out.println("<p><span class=\"label label-primary\">住</span>2016-09-23</p>");
                            		out.println("<p><span class=\"label label-success\">离</span>2016-09-24</p>");
                            		out.println("</td>");
                            		out.println("<td><strong>￥100</strong></td>");
                            		out.println("<td>入住完成</td>");
                            		out.println("<td>");
                            		out.println("<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#exampleModal\" data-whatever=\"@getbootstrap\">订单详情</button>");
                            		out.println("</td>");
                            		out.println("</tr>");
                            	}
                            %>
                            
                            <tr>
                                <th>南京啦啦啦店</th>
                                <td>单人房</td>
                                <td>
                                    <p><span class="label label-primary">住</span>2016-09-23</p>
                                    <p><span class="label label-success">离</span>2016-09-24</p>
                                </td>
                                <td><strong>￥100</strong></td>
                                <td>入住完成</td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="exampleModalLabel" data-whatever="@getbootstrap">订单详情</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-3 sx-col-right sx-right sx-content-center">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <img src="../img/user.jpg" class="img-rounded" height="200" width="200">
                        <h3><strong>我是哈哈哈</strong></h3>
                        <p>100010101010@110.com</p>
                        <label><span class="label label-success">已认证</span>孙**</label>
                        <a>修改个人信息</a>
                        <a class="btn btn-default sx-big-button" role="button">我的酒店</a>
                    </div>
                </div>
            </div>
            <div class="col-md-9 sx-col-left">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-md-4 sx-col-left sx-block">
                            <h4 class="sx-head"><strong>账户余额</strong></h4>
                            <hr>
                            <h2 class="sx-content-center"><strong>￥20.10</strong></h2>
                        </div>
                        <div class="col-md-4 sx-block">
                            <h4 class="sx-head"><strong>累计消费</strong></h4>
                            <hr>
                            <h2 class="sx-content-center"><strong>￥20.10</strong></h2>
                        </div>
                        <div class="col-md-4 sx-col-left sx-content-center">
                            <h4 class="sx-head"><strong>充值</strong></h4>
                            <p><strong>银行卡号</strong>&nbsp;&nbsp;<strong>53259827394824932</strong><p>
                            <button type="button" class="btn btn-success sx-right-float" data-toggle="modal" data-target=".bs-example-modal-sm" data-whatever="@getbootstrap">点我充值</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> <!-- /container -->

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">房间0001<span class="label label-danger">已预订</span></h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-2">姓名</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">联系电话</label>
                        <div class="col-md-10">
                            <input type="text" class="col-md-10 form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">日期</label>
                        <div class="col-md-5">
                            <input type="date" class="form-control col-md-5">
                        </div>
                        <div class="col-md-5">
                            <input type="date" class="form-control col-md-5">
                        </div>
                    </div>
                    <hr>
                    <h5>入住人信息</h5>
                    <div class="form-group">
                        <div class="col-md-4">
                            <input type="text" class="form-control" value="姓名">
                        </div>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="身份证号">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4">
                            <input type="text" class="form-control" value="姓名">
                        </div>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="身份证号">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-4">
                            <input type="text" class="form-control" value="姓名">
                        </div>
                        <div class="col-md-8">
                            <input type="text" class="form-control" value="身份证号">
                        </div>
                    </div>
                    <hr>
                    <h5>支付信息</h5>
                    <div class="form-group">
                        <label class="control-label col-md-2">支付方式</label>
                        <div class="col-md-2">
                            <select class="form-control">
                                <option>现金</option>
                                <option>会员卡</option>
                            </select>
                        </div>
                        <div class="col-md-8">
                            <input type="number" class="form-control" placeholder="会员卡号">
                        </div>
                    </div>
                    <span class="label label-success">房费</span><label>100000.5￥</label><label>(会员价1￥)</label>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                <button type="button" class="btn btn-primary">入住（退房）</button>
                <button type="button" class="btn btn-danger">取消预定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4>充值</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-3 control-label">金额</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" placeholder="充值金额">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">密码</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" placeholder="6位支付密码">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success">确认充值</button>
            </div>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/docs.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
