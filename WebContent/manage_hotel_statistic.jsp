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

    <title>Hotel World酒店管理</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/theme.css" rel="stylesheet">
    <link href="css/customer/sx-main.css" rel="stylesheet">
    <link href="css/customer/sx-style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="sx-background">

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

    <ul class="nav nav-tabs nav-justified">
        <li role="presentation" class="active"><a>新店注册</a></li>
        <li role="presentation"><a>店铺修改</a></li>
        <li role="presentation"><a>经营统计</a></li>
    </ul>
    <div class="panel sx-panel">
        <div class="panel-body">
            <table class="table">
                <thead>
                <tr>
                    <th>提交日期</th>
                    <th>店名</th>
                    <th>法人</th>
                    <th>地区</th>
                    <th>操作</th>
                    <th>详情</th>

                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>2016-10-10</th>
                    <td>南京啦啦啦店</td>
                    <td>阿旭</td>
                    <td>南京</td>
                    <td><span class="label label-success">申请加盟</span></td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">查看详情</button>
                    </td>
                </tr>
                <tr>
                    <th>2016-10-10</th>
                    <td>南京啦啦啦店</td>
                    <td>阿旭</td>
                    <td>南京</td>
                    <td><span class="label label-success">申请加盟</span></td>
                    <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">查看详情</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</div> <!-- /container -->

<div class="modal fade " id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel"><strong>南京啦啦啦店</strong><span class="label label-success">请求加盟</span></h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">酒店名称</label>
                        <div class="col-sm-6">
                            <input name="user.hotel" type="text" class="form-control" placeholder="请输入名称" disabled>
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
                                <input name="hotel.start_money" type="number" class="form-control"  placeholder="请填入数字，如20" disabled>
                                <div class="input-group-addon">万元</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input name="hotel.name" type="text" class="form-control" placeholder="请输入姓名" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-9">
                            <input name="hotel.name" type="text" class="form-control" placeholder="请输入姓名" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">身份证号</label>
                        <div class="col-sm-9">
                            <input name="hotel.id_num" type="number" class="form-control" placeholder="请输入身份证号" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input name="hotel.email" type="email" class="form-control" placeholder="xxxx@xxx.xx" disabled>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">酒店位置</label>
                        <div class="col-sm-9">
                            <select name="hotel.city" class="form-control" disabled>
                                <option value ="北京">北京</option>
                                <option value ="上海">上海</option>
                                <option value="广州">广州</option>
                                <option value="深圳">深圳</option>
                                <option value="南京">南京</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">房型1</label>
                        <div class="col-sm-4">
                            <input name="room1.capacity" type="number" class="form-control"  placeholder="房间人数" disabled>
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input name="room1.num" type="number" class="form-control" placeholder="数量" disabled>
                                <div class="input-group-addon">间</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">房型2</label>
                        <div class="col-sm-4">
                            <input name="room2.capacity" type="number" class="form-control"  placeholder="房间人数" disabled>
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input name="room2.num" type="number" class="form-control" placeholder="数量" disabled>
                                <div class="input-group-addon">间</div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">房型3</label>
                        <div class="col-sm-4">
                            <input name="room3.capacity" type="number" class="form-control"  placeholder="房间人数" disabled>
                        </div>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input name="room3.num" type="number" class="form-control" placeholder="数量" disabled>
                                <div class="input-group-addon">间</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <input type="button" class="btn btn-default" data-dismiss="modal" value="返回">
                <input type="submit" class="btn btn-primary" value="通过">
                <input type="submit" class="btn btn-danger" value="拒绝">
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
<script src="../../assets/js/docs.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

