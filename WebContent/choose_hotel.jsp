<%@page import="java.text.SimpleDateFormat"%>
<%@page import="constant.City"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Hotel" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    List<Hotel> hotels = (List<Hotel>)request.getAttribute("hotels");//根据评星升序排列
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
    <link href="css/carousel.css" rel="stylesheet">
    <link href="css/customer/sx-style.css" rel="stylesheet">
  </head>
<!-- ==========================NAVBAR ========================= -->
  <body>
    <div>
      <div class="container">
        <nav class="navbar navbar-inverse navbar-fixed-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Hotel World</a>
            </div>
            <div id="navbar1" class="collapse navbar-collapse">
              <ul class="nav navbar-nav" style="float:right">
                <li class="active"><a href="chooseHotel">主页</a></li>
                <li><a href="personalHome">我的Hotel</a></li>
                <li><a href="#contact">退出登录</a></li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </nav>
      </div>
    </div>


    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide sx-margin-clear" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img class="first-slide" src="img/long1.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
            </div>
          </div>
        </div>
        <div class="item">
          <img class="second-slide" src="img/long2.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
            </div>
          </div>
        </div>
        <div class="item">
          <img class="third-slide" src="img/long3.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption">
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->

    <div class="sx-background sx-padding-all">
      <form class="form-inline container" action="chooseHotel" method="post">
        <div class="form-group sx-search">
          <label>城市</label>
          <select class="form-control" name="expectedCity">
          <%
          	String expectedCity = (String)session.getAttribute("expectedCity");
          	for(City city : (City[])request.getAttribute("cities")){
          		if(expectedCity.equals(city.getValue())){
	          		out.println("<option value=\""+city.getValue()+"\" selected>"+city.getValue()+"</option>");
          		}else{
          			out.println("<option value=\""+city.getValue()+"\">"+city.getValue()+"</option>");
          		}
          	}
          %>
          </select>
        </div>
        <div class="form-group sx-search">
          <label>星级</label>
          <select class="form-control" name="star">
          	<option value="0" selected>不限</option>
            <option value="1">A级（500￥起）</option>
            <option value="2">B级（300￥起）</option>
            <option value="3">C级（100￥起）</option>
          </select>
        </div>
        <div class="form-group sx-search">
       	<%
       		Date inDate = (Date)session.getAttribute("inDate");
       		Date outDate = (Date)session.getAttribute("outDate");
       		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       		
       	%>
          <label>入住</label><input name="inDate" class="form-control sx-search" type="date" placeholder="入住日期" value="<%=format.format(inDate) %>">
          <label>离店</label><input name="outDate" class="form-control sx-search" type="date" placeholder="离店日期" value="<%=format.format(outDate)%>">
        </div>
        <input type="submit" class="btn btn-primary" value="搜索店铺">
      </form>
    </div>


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

      <div class="row">
        <p class="sx-star-head"><img src="img/star-star.png"><%=(String)request.getAttribute("expectedCity") %>-明星店铺</p>
      </div>

      <!-- Three columns of text below the carousel -->
      <div class="row">
      <%
      	int top = 3;
      	if(top > hotels.size()){
      		top = hotels.size();
      	}
		for(int i = 0 ; i < top ; i ++){
			Hotel hotel = hotels.get(i);
			out.println("<div class=\"col-lg-4 sx-star-block ");
			if(i == (top-1)){out.print("sx-star-block-right");}
			out.println("\">");
			out.println("<img class=\"img-responsive sx-img-star-hotel\" src=\""+hotel.getImage_mid()+"\" alt=\"Generic placeholder image\">");
			out.println("<p><a class=\"sx-big-blue\" href=\"chooseRoom?hid="+hotel.getHid()+"\">"+hotel.getName()+"</a></p>");
			out.println("<p>"+hotel.getDescription()+"</p>");
			out.println("<p><a class=\"btn btn-default\" href=\"chooseRoom?hid="+hotel.getHid()+"\" role=\"button\">进店瞧瞧 &raquo;</a></p>");
			out.println("</div>");
		}
      %>	
      </div><!-- /.row -->

      <!-- START THE FEATURETTES -->

      <table>
      <%
      	for(int i = 0 ; i < hotels.size() ; i++){
      		Hotel hotel = hotels.get(i);
      		out.println("<tr>");
      		out.println("<td class=\"media sx-media\">");
      		out.println("<div class=\"media-left\">");
      		out.println("<a href=\"chooseRoom?hid="+hotel.getHid()+"\">");
      		out.println("<img class=\"media-object sx-img-long\" src=\""+hotel.getImage_small()+"\" alt=\"...\">");
      		out.println("</a>");
      		out.println("</div>");
      		out.println("</div>");
      		out.println("<div class=\"media-body\">");
      		out.println("<div class=\"row\">");
      		out.println("<div class=\"col-md-6 sx-vertical-line\">");
      		out.println("<h4 class=\"media-heading\"><span class=\"label sx-label-hotel\">1</span><a href=\"chooseRoom?"+hotel.getHid()+"\">"+hotel.getName()+"</a><span class=\"label label-danger\">"+hotel.getStar()+"星级</span></h4>");
      		out.println("<p>"+hotel.getDescription()+"</p>");
      		out.println("<p class=\"sx-small-grey\">“"+hotel.getGoodComment()+"”</p>");
      		out.println("</div>");
      		out.println("<div class=\"col-md-3 sx-vertical-line sx-content-center\">");
      		out.println("<p><span class=\"sx-big-blue\">"+hotel.getAvgStar()+"</span>/5分</p>");
      		out.println("<p>"+hotel.getCommentNum()+"次评价</p>");
      		out.println("<p class=\"sx-small-green\">"+hotel.getGoodCommentNum()+"条好评</p>");
      		out.println("</div>");
      		out.println("<div class=\"col-md-3 sx-content-center\">");
      		out.println("<p>￥<span class=\"sx-big-red\">"+hotel.getLowestPrice()+"</span>起</p>");
      		out.println("<p><a class=\"btn btn-primary\" href=\"chooseRoom?hid="+hotel.getHid()+"\">进店看看</a></p>");
      		out.println("</div>");
      		out.println("</div>");
      		out.println("</div>");
      		out.println("</td>");
      		out.println("</tr>");
      	}
      %>
        <tr>
          <td class="media sx-media">
            <div class="media-left">
              <a href="#">
                <img class="media-object sx-img-long" src="../img/hotel1.jpg" alt="...">
              </a>
            </div>
            <div class="media-body">
              <div class="row">
                <div class="col-md-6 sx-vertical-line">
                  <h4 class="media-heading"><span class="label sx-label-hotel">1</span><a>南京啦啦啦分店</a><span class="label label-danger">5星级</span></h4>
                  <p>南京最好的酒店，啥都没有，要啥没啥，床都没有，就睡地板，价钱合适的很，绝对上档次的房间！欢迎入住！</p>
                  <p class="sx-small-grey">“贼拉过瘾，相当舒服了”</p>
                </div>
                <div class="col-md-3 sx-vertical-line sx-content-center">
                  <p><span class="sx-big-blue">4.9</span>/5分</p>
                  <p>5000000人评价</p>
                  <p class="sx-small-green">1条好评</p>
                </div>
                <div class="col-md-3 sx-content-center">
                  <p>￥<span class="sx-big-red">500</span>起</p>
                  <p><button class="btn btn-primary">进店看看</button></p>
                </div>
              </div>

            </div>
          </td>
        </tr>
        <tr>
          <td class="media sx-media">
            <div class="media-left">
              <a href="#">
                <img class="media-object sx-img-long" src="../img/hotel1.jpg" alt="...">
              </a>
            </div>
            <div class="media-body">
              <div class="row">
                <div class="col-md-6 sx-vertical-line">
                  <h4 class="media-heading"><span class="label sx-label-hotel">1</span><a>南京啦啦啦分店</a><span class="label label-danger">5星级</span></h4>
                  <p>南京最好的酒店，啥都没有，要啥没啥，床都没有，就睡地板，价钱合适的很，绝对上档次的房间！欢迎入住！</p>
                  <p class="sx-small-grey">“贼拉过瘾，相当舒服了”</p>
                </div>
                <div class="col-md-3 sx-vertical-line sx-content-center">
                  <p><span class="sx-big-blue">4.9</span>/5分</p>
                  <p>5000000人评价</p>
                  <p class="sx-small-green">1条好评</p>
                </div>
                <div class="col-md-3 sx-content-center">
                  <p>￥<span class="sx-big-red">500</span>起</p>
                  <p><button class="btn btn-primary">进店看看</button></p>
                </div>
              </div>

            </div>
          </td>
        </tr>
      </table>

      <hr>

      <!-- /END THE FEATURETTES -->


      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>&copy; 2016 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
      </footer>

    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
