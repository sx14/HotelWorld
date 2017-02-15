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
    <link href="css/customer/sx-style.css" rel="stylesheet">
    <style>
    	body{
    		padding-top:0 !important;
    	}
    </style>

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
                  <li class="active"><a href="#">登录</a></li>
                  <li><a href="register.jsp">快速注册</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">Hotel World</h1>
            <p class="lead">专注酒店三十年，始于1980</p>
            <form action="login" method="post">
              <div class="sx-content-center">
                <div class="row">
                  <div class="col-md-4 col-md-offset-4 form-horizontal">
                    <input name="user.phone" type="tel" class="form-control sx-cover-form" placeholder="手机号码">
                    <input name="user.password" type="password" class="form-control sx-cover-form" placeholder="密码">
                    <input type="submit" class="btn btn-default sx-cover-form sx-cover-button" value="登录">
                  </div>
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
  </body>
</html>
