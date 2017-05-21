<%@page import="constant.HotelStar"%>
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
          		if(expectedCity.equals(city.getValue())){%>
          			<option value="<%=city.getValue() %>" selected><%=city.getValue() %></option>
          		<%}else{%>
          			<option value="<%=city.getValue() %>"><%=city.getValue() %></option>
          		<%}
          	}%>
          </select>
        </div>
        <div class="form-group sx-search">
          <label>星级</label>
          <select class="form-control" name="level">
          <%
          	HotelStar[] levels = (HotelStar[])request.getAttribute("levels");
          	int expectedLevel = (Integer)session.getAttribute("expectedLevel");
          	for(int i = 0 ; i < levels.length ; i++){
          		if(levels[i].getValue() == expectedLevel){%>
          			<option value="<%=levels[i].getValue() %>" selected><%=levels[i].getName()+levels[i].getLowestPriceDesc() %></option>
          		<%}else{%>
          			<option value="<%=levels[i].getValue() %>"><%=levels[i].getName()+levels[i].getLowestPriceDesc() %></option>
          		<%}
          	}%>
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
			out.println("<p><a class=\"sx-big-blue\" href=\"chooseRoom?hid="+hotel.getHid()+"\">"+hotel.getCity()+hotel.getHotel_name()+"</a></p>");
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
      		out.println("<div class=\"col-md-6 sx-vertical-line-fix\">");
      		out.println("<h4 class=\"media-heading\"><span class=\"label sx-label-hotel\">"+(i+1)+"</span><a href=\"chooseRoom?hid="+hotel.getHid()+"\">"+hotel.getCity()+hotel.getHotel_name()+"</a><span class=\"label label-danger\">"+hotel.getLevel()+"</span></h4>");
      		out.println("<p>"+hotel.getDescription()+"</p>");
      		out.println("<p class=\"sx-small-grey\">“"+hotel.getGoodComment()+"”</p>");
      		out.println("</div>");
      		out.println("<div class=\"col-md-3 sx-vertical-line-fix sx-content-center\">");
      		out.println("<p><span class=\"sx-big-blue\">"+String.format("%.1f", hotel.getAvgStar())+"</span>/5分</p>");
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
      </table>

      <hr>

      <!-- /END THE FEATURETTES -->


      <!-- FOOTER -->
      <footer>
        <p>&copy; 2016 Company, Inc. &middot; <a>Privacy</a> &middot; <a>Terms</a></p>
      </footer>

    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
