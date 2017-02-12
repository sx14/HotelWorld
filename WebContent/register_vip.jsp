<%@page import="model.User"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/theme.css" rel="stylesheet">
    <link href="css/customer/sx-hotel-register.css" rel="stylesheet">

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
            <h4><strong>会员注册</strong></h4>
        </div>
        <div class="panel-body">
            <form class="form-horizontal" action="registerVIP" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-md-4 control-label">用户名</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" value="<%=user.getUsername() %>" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">联系电话</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" value="<%=user.getPhone() %>" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">姓名</label>
                    <div class="col-md-4">
                    	<%
                    		if(user.getName() != null){
                    			out.println("<input name=\"user.name\" type=\"text\" class=\"form-control\"placeholder=\"请输入姓名\" value=\""+user.getName()+"\">");
                    		}else{
                    			out.println("<input name=\"user.name\" type=\"text\" class=\"form-control\"placeholder=\"请输入姓名\">");
                    		}
                    	%>
                        
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">身份证号</label>
                    <div class="col-md-4">
                    	<%
                    		if(user.getId_num() != null){
                    			out.println("<input name=\"user.id_num\" type=\"number\" class=\"form-control\"placeholder=\"请输入身份证号\" value=\""+user.getId_num()+"\">");
                    		}else{
                    			out.println("<input name=\"user.id_num\" type=\"number\" class=\"form-control\"placeholder=\"请输入身份证号\">");
                    		}
                    	%>
                        
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">邮箱</label>
                    <div class="col-md-4">
                    	<%
                    		if(user.getEmail() != null){
                    			out.println("<input name=\"user.email\" type=\"email\" class=\"form-control\"placeholder=\"如xxxx@xxx.xxx\" value=\""+user.getEmail()+"\">");
                    		}else{
                    			out.println("<input name=\"user.email\" type=\"email\" class=\"form-control\"placeholder=\"如xxxx@xxx.xxx\">");
                    		}
                    	%>
                        
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">银行卡号</label>
                    <div class="col-md-4">
                    	<%
                    		if(user.getVisa() != null){
                    			out.println("<input name=\"user.visa.num\" type=\"number\" class=\"form-control\"placeholder=\"绑定银行卡账号\" value=\""+user.getVisa().getVid()+"\">");
                    		}else{
                    			out.println("<input name=\"user.visa.num\" type=\"number\" class=\"form-control\"placeholder=\"绑定银行卡账号\">");
                    		}
                    	%>
                        
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label">上传头像</label>
                    <div class="col-md-4">
                        <input name="image" type="file" class="form-control">
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="col-md-4 col-md-offset-4 sx-mid">
                        <input type="reset" class="btn btn-default" value="重置">
                        <input type="submit" class="btn btn-primary" value="提交">
                        <a class="sx-right-float sx-a">注销会员</a>
                    </div>
                </div>
            </form>
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
<script src="../../assets/js/docs.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
