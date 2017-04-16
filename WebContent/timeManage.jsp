<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"
	import="com.web.action.PlaceAction" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tags/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>排球場協-場協系統</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- style -->
<link href="css/style.css" rel="stylesheet">
<!-- datepicker -->
<script src="datetimepicker/js/bootstrap-datepicker.js"></script>
<script src="datetimepicker/js/bootstrap-datepicker.min.js"></script>
<script src="datetimepicker/locales/bootstrap-datepicker.zh-TW.min.js"
	charset="UTF-8"></script>
<link rel="stylesheet"
	href="datetimepicker/css/bootstrap-datepicker.css" />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn’t work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">
	function Logout() {
		window.location.href = "WEB_ManagerLogoutAction";
	}
	function SetOpenTime(placeType, day, time) {
		window.location.href = "WEB_ManageSetOpenTimeAction?clickTypeID="
				+ placeType + "&day=" + day + "&time=" + time;
	}
	function SetType(clickTypeID){
		window.location.href="WEB_ManageTimeSetTypeAction?clickTypeID=" + clickTypeID;
	}
</script>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="placeManage.html">場協系統</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="WEB_ManageGetPlaceAction">場地設定</a></li>
					<li class="active"><a href="timeManage.jsp">時間設定 <span
							class="sr-only">(current)</span></a></li>
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
		<div role="tabpanel">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs pm_tab" role="tablist">
				<c:choose>
					<c:when test="${sessionScope.clickTypeID =='1101101'}">
						<li role="presentation" class="active"><a href="#home"
							aria-controls="home" role="tab" data-toggle="tab">籃球</a></li>
						<li role="presentation"><a onclick="SetType('1101102')"
							aria-controls="profile" role="tab" data-toggle="tab">排球</a></li>
					</c:when>
					<c:when test="${sessionScope.clickTypeID =='1101102'}">
						<li role="presentation"><a onclick="SetType('1101101')"
							aria-controls="home" role="tab" data-toggle="tab">籃球</a></li>
						<li role="presentation" class="active"><a href="#profile"
							aria-controls="profile" role="tab" data-toggle="tab">排球</a></li>
					</c:when>
				</c:choose>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="home">
					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-default">
								<!-- Default panel contents -->
								<div class="panel-heading">${sessionScope.clickTypeName}場地開放時段狀況</div>
								<div class="panel-body">
									<p>一周各時段之場地開放時段狀況</p>
								</div>
								<div class="tm_scroll">
									<!-- Table -->
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>時段 \ 星期</th>
												<th>7:00</th>
												<th>7:30</th>
												<th>8:00</th>
												<th>8:30</th>
												<th>9:00</th>
												<th>9:30</th>
												<th>10:00</th>
												<th>10:30</th>
												<th>11:00</th>
												<th>11:30</th>
												<th>12:00</th>
												<th>12:30</th>
												<th>13:00</th>
												<th>13:30</th>
												<th>14:00</th>
												<th>14:30</th>
												<th>15:00</th>
												<th>15:30</th>
												<th>16:00</th>
												<th>16:30</th>
												<th>17:00</th>
												<th>17:30</th>
												<th>18:00</th>
												<th>18:30</th>
												<th>19:00</th>
												<th>19:30</th>
												<th>20:00</th>
												<th>20:30</th>
												<th>21:00</th>
												<th>21:30</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${sessionScope.placeOpenList}"
												var="list" varStatus="s">
												<tr>
													<th scope="row">
													<span class="label label-danger">${list.day}</span>
													</th>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a0700}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a0700')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a0730}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a0730')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a0800}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a0800')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a0830}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a0830')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a0900}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a0900')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a0930}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a0930')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a1000}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a1000')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a1030}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a1030')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a1100}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a1100')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.a1130}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','a1130')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1200}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1200')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1230}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1230')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1300}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1300')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1330}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1330')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1400}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1400')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1430}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1430')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1500}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1500')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1530}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1530')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1600}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1600')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1630}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1630')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1700}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1700')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1730}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1730')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1800}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1800')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1830}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1830')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1900}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1900')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p1930}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p1930')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p2000}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p2000')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p2030}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p2030')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p2100}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p2100')">
															</label>
														</div>
													</td>
													<td>
														<div class="checkbox tm_check">
															<label> <input class="tm_checkbox"
																type="checkbox" ${list.p2130}
																onClick="SetOpenTime('${sessionScope.clickTypeID}', '${list.day}','p2130')">
															</label>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- datetimepicker -->
	<script type="text/javascript">
		$('.input-daterange').datepicker({
			language : "zh-TW"
		});
	</script>
	<!-- jQuery (necessary for Bootstrap’s JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
