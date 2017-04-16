<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tags/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>場協系統</title>
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
		window.location.href = "WEB_MemberLogoutAction";
	}
	function SetType(clickTypeID) {
		window.location.href = "WEB_MemberChangeTypeAction?clickTypeID="
				+ clickTypeID;
	}

	function search() {
		var searchDate = $('#record_date').val();
		var searchDepartmentName = $('#searchDepartment').val();
		window.location.href = "WEB_MemberSreachAction?searchDate="
				+ searchDate + "&searchDepartmentName=" + searchDepartmentName;
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
				<a class="navbar-brand" href="#">輔大場協系統</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<c:choose>
						<c:when test="${sessionScope.clickTypeID =='1101101'}">
							<li class="active active_web"><a href="index.html">籃球 <span
									class="sr-only">(current)</span></a></li>
							<li><a onClick="SetType('1101102')">排球</a></li>
						</c:when>
						<c:when test="${sessionScope.clickTypeID =='1101102'}">
							<li><a onClick="SetType('1101101')">籃球 </a></li>
							<li class="active active_web"><a
								href="index _volleyBall.html">排球<span class="sr-only">(current)</span></a></li>
						</c:when>
					</c:choose>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<p class="navbar-text">Signed in as
							${sessionScope.student.studentAccount}</p>
					</li>
					<li>
						<button type="button" class="btn btn-gray navbar-btn"
							onclick="Logout()">登出</button>
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
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4">
								<label>日期</label> <input type="date" name="Date"
									class="form-control" id="record_date">
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label>系級(登記單位)</label> <br> <select class="selectpicker"
									id="searchDepartment">
									<option value="0">系級(登記單位)</option>
									<c:forEach items="${sessionScope.departmentList}" var="list"
										varStatus="s">
										<option value="${list.departmentName}">${list.departmentName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-6">
								<button id="sreachBtn" class="btn btn-success btn_margin"
									type="submit" onClick="search()">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									查詢
								</button>
								<!-- <input class="btn btn-success btn_margin" type="submit" value="查詢"> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">場地登記狀況</div>
					<div class="panel-body">
						<p>每日各時段之場地登記狀況</p>
					</div>
					<div class="panel-group" id="accordion" role="tablist"
						aria-multiselectable="true">
						<div class="panel panel-default">
							<c:choose>
								<c:when test="${sessionScope.searchDate != null}">
									<div class="panel-heading" role="tab" id="headingOne">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseOne" aria-expanded="true"
												aria-controls="collapseOne"> ${sessionScope.searchDate}(<span>${sessionScope.searchDay}</span>)
												<c:choose>
													<c:when test="${sessionScope.searchDay != null}">
														<a class="btn btn-warning btn_red" data-toggle="modal"
															style="color: #fff;" data-target="#exampleModal" href="#"
															role="button"> <span
															class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
															我要登記
														</a>
													</c:when>
												</c:choose>
											</a>
										</h4>
									</div>
								</c:when>
							</c:choose>
							<div id="collapseOne" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<ul class="list-group">
										<li class="list-group-item" style="background: #f1f1f1;">
											<span>登記單位 / </span> <span>登記人 / </span> <span>登記人聯絡資訊
												/ </span> <span>登記時段</span>
										</li>
										<c:forEach items="${sessionScope.searchList}" var="list"
											varStatus="s">
											<li class="list-group-item"><span>${list.departmentName} / </span> <span>${list.studentName}
												/ </span> <span>${list.studentPhone}</span>
											<c:choose>
												<c:when test="${list.a0700 == 'on'}">
													<span class="label label-default">07:00-07:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a0730 == 'on'}">
													<span class="label label-default">07:30-08:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a0800 == 'on'}">
													<span class="label label-default">08:00-08:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a0830 == 'on'}">
													<span class="label label-default">08:30-09:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a0900 == 'on'}">
													<span class="label label-default">09:00-09:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a0930 == 'on'}">
													<span class="label label-default">09:30-10:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a1000 == 'on'}">
													<span class="label label-default">10:00-10:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a1030 == 'on'}">
													<span class="label label-default">10:30-11:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a1100 == 'on'}">
													<span class="label label-default">11:00-11:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.a1130 == 'on'}">
													<span class="label label-default">11:30-12:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1200 == 'on'}">
													<span class="label label-default">12:00-12:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1230 == 'on'}">
													<span class="label label-default">12:30-13:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1300 == 'on'}">
													<span class="label label-default">13:00-13:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1330 == 'on'}">
													<span class="label label-default">13:30-14:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1400 == 'on'}">
													<span class="label label-default">14:00-14:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1430 == 'on'}">
													<span class="label label-default">14:30-15:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1500 == 'on'}">
													<span class="label label-default">15:00-15:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1530 == 'on'}">
													<span class="label label-default">15:30-16:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1600 == 'on'}">
													<span class="label label-default">16:00-16:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1630 == 'on'}">
													<span class="label label-default">16:30-17:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1700 == 'on'}">
													<span class="label label-default">17:00-17:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1730 == 'on'}">
													<span class="label label-default">17:30-18:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1800 == 'on'}">
													<span class="label label-default">18:00-18:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1830 == 'on'}">
													<span class="label label-default">18:30-19:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1900 == 'on'}">
													<span class="label label-default">19:00-19:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p1930 == 'on'}">
													<span class="label label-default">19:30-20:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p2000 == 'on'}">
													<span class="label label-default">20:00-20:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p2030 == 'on'}">
													<span class="label label-default">20:30-21:00</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p2100 == 'on'}">
													<span class="label label-default">21:00-21:30</span>
												</c:when>
											</c:choose>
											<c:choose>
												<c:when test="${list.p2130 == 'on'}">
													<span class="label label-default">21:30-22:00</span>
												</c:when>
											</c:choose>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 彈窗內容 -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">登記</h4>
				</div>
				<div class="modal-body">
					<form action="/web/WEB_MemberRecordAction" method="post">
						<div class="form-group">
							<label for="recipient-name" class="control-label">登記單位：</label> <select
								class="selectpicker" id="searchDepartment"
								name="usePlace.departmentName">
								<option value="0">系級(登記單位)</option>
								<c:forEach items="${sessionScope.departmentList}" var="list"
									varStatus="s">
									<option value="${list.departmentName}">${list.departmentName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">登記人：</label> <input
								type="text" class="form-control" id="recipient-name"
								placeholder="輸入姓名" name="usePlace.studentName">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">連絡電話：</label> <input
								type="text" class="form-control" id="recipient-name"
								placeholder="輸入手機號碼" name="usePlace.studentPhone">
						</div>
						<div class="form-group">
							<label for="recipient-name" class="control-label">登記時段：</label>
							<ul class="list-group">
								<c:choose>
									<c:when test="${sessionScope.openTime.a0700 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a0700">
										</label> <span>7:00</span>-<span>7:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a0730 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a0730">
										</label> <span>7:30</span>-<span>8:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a0800 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a0800">
										</label> <span>8:00</span>-<span>8:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a0830 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a0830">
										</label> <span>8:30</span>-<span>9:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a0900 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a0900">
										</label> <span>9:00</span>-<span>9:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a0930 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a0930">
										</label> <span>9:30</span>-<span>10:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a1000 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a1000">
										</label> <span>10:00</span>-<span>10:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a1030 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a1030">
										</label> <span>10:30</span>-<span>11:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a1100 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a1100">
										</label> <span>11:00</span>-<span>11:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.a1130 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.a1130">
										</label> <span>11:30</span>-<span>12:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1200 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1200">
										</label> <span>12:00</span>-<span>12:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1230 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1230">
										</label> <span>12:30</span>-<span>13:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1300 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1300">
										</label> <span>13:00</span>-<span>13:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1330 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1330">
										</label> <span>13:30</span>-<span>14:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1400 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1400">
										</label> <span>14:00</span>-<span>14:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1430 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1430">
										</label> <span>14:30</span>-<span>15:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1500 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1500">
										</label> <span>15:00</span>-<span>15:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1530 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1530">
										</label> <span>15:30</span>-<span>16:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1600 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1600">
										</label> <span>16:00</span>-<span>16:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1630 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1630">
										</label> <span>16:30</span>-<span>17:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1700 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1700">
										</label> <span>17:00</span>-<span>17:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1730 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1730">
										</label> <span>17:30</span>-<span>18:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1800 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1800">
										</label> <span>18:00</span>-<span>18:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1830 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1830">
										</label> <span>18:30</span>-<span>19:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1900 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1900">
										</label> <span>19:00</span>-<span>19:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p1930 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p1930">
										</label> <span>19:30</span>-<span>20:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p2000 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p2000">
										</label> <span>20:00</span>-<span>20:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p2030 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p2030">
										</label> <span>20:30</span>-<span>21:00</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p2100 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p2100">
										</label> <span>21:00</span>-<span>21:30</span></li>
									</c:when>
								</c:choose>
								<c:choose>
									<c:when test="${sessionScope.openTime.p2130 == 'checked'}">
										<li class="list-group-item"><label> <input
												type="checkbox" name="usePlace.p2130">
										</label> <span>21:30</span>-<span>22:00</span></li>
									</c:when>
								</c:choose>
							</ul>
						</div>
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="submit" class="btn btn-primary btn_red">新增</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- datetimepicker -->
	<script type="text/javascript">
		$('#recipient-name').datepicker({
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
