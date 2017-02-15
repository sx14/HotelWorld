<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

    <title>Hotel World</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/cover.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand">Hotel World</h3>
                    <nav>
                        <ul class="nav masthead-nav">
                            <li><a href="index.jsp">登录</a></li>
                            <li class="active"><a href="#">快速注册</a></li>
                        </ul>
                    </nav>
                </div>
            </div>

            <div class="inner cover">
                <h1 class="cover-heading">Hotel World</h1>
                <form class="form-horizontal col-md-10 col-md-offset-1" action="registerQuickly" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-8">
                            <input name="user.username" type="text" class="form-control" placeholder="字母开头10字符以上">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-8">
                            <input name="user.password" type="password" class="form-control" placeholder="不少于5位">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系电话</label>
                        <div class="col-sm-8">
                            <input name="user.phone" type="text" class="form-control" placeholder="请输入您的手机号码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control"  placeholder="请输入短信验证码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox"> 已阅读同意书
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-10">
                            <button type="submit" class="btn btn-default col-md-4 col-md-offset-4" onclick="checkExists()">注册</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="mastfoot">
                <div class="inner">
                    <p>Hotel World 全国连锁酒店</p>
                </div>
            </div>

        </div>

    </div>

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/bootstrap.min.js"></script>
<script src="js/customer/sx-ajax.js"></script>
</body>
</html>
