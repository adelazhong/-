<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"
	import="com.web.action.PlaceAction" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tags/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>場地管理-場協系統</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- style -->
    <link href="css/style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn’t work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">
	function DeletePlaceClick(clickTypeID, clickName){
		window.location.href="WEB_ManageDeletePlaceAction?clickTypeID="+ clickTypeID +"&clickName="+ clickName;
	}
	function Logout(){
		window.location.href="WEB_ManagerLogoutAction";
	}
	function SetType(clickTypeID){
		window.location.href="WEB_ManagePlaceSetTypeAction?clickTypeID=" + clickTypeID;
	}
</script>
</head>

<body>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">場協系統</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
	                <li class="active"><a href="placeManage.jsp">場地設定 <span class="sr-only">(current)</span></a></li>
                    <li><a href="WEB_ManageGetOpenTimeAction">時間設定 </a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <p class="navbar-text">Signed in as ${sessionScope.manager.managerAccount}</p>
                    </li>
                    <li>
                        <button type="button" class="btn btn-gray navbar-btn" onclick="Logout()">登出</button>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
            	<div role="tabpanel">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs pm_tab" role="tablist">
                        <c:choose>
                        	<c:when test="${sessionScope.clickTypeID =='1101101'}">
                       			<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">籃球</a></li>
                        		<li role="presentation"><a onclick="SetType('1101102')" aria-controls="profile" role="tab" data-toggle="tab">排球</a></li>
                        	</c:when>
                        	<c:when test="${sessionScope.clickTypeID =='1101102'}">
                       			<li role="presentation"><a onclick="SetType('1101101')" aria-controls="home" role="tab" data-toggle="tab">籃球</a></li>
                        		<li role="presentation" class="active"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">排球</a></li>
                        	</c:when>
                        </c:choose>	
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home">
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <div class="panel-heading text-right clearfix">
                                    <span class="pull-left place_span">${sessionScope.clickTypeName}場地管理</span>
									<button type="button" class="btn btn_red" data-toggle="modal" data-target="#exampleModal">
                                        新增場地
                                    </button>
                                </div>
                                <div class="panel-body">
                                    <p>現有之場地</p>
                                </div>
                                <div class="list-group">
                                    <c:forEach items="${sessionScope.placeSetList}" var="list" varStatus="s">
										<a href="#" class="list-group-item list-group-item-success">
											<button class="btn btn-default" type="submit" onclick="DeletePlaceClick('${sessionScope.clickTypeID}','${list.placeName}')">
												<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
											</button> ${list.placeName}
										</a>
									</c:forEach>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 彈窗內容 -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">${sessionScope.clickTypeName}新增場地</h4>
                </div>
                <form id="sendForm" action="/web/WEB_ManageSetPlaceAction"
					method="post">
					<div class="modal-body">
						<div class="form-group">
                            <label for="recipient-name" class="control-label">場地名稱：</label>
                            <input type="text" name="place.typeID" value="${sessionScope.clickTypeID}" hidden>
                            <input type="text" name="place.placeName" class="form-control" id="recipient-name">
                        </div>
                        <div class="modal-footer">
                    		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    		<button type="submit" class="btn btn-primary btn_red">新增</button>
                		</div>
					</div>
            </div>
        </div>
    </div>
    <!-- jQuery (necessary for Bootstrap’s JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>

</html>
