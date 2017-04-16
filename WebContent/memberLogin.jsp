<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登入-場協系統</title>
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
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 login_wrap">
                <div class="panel panel-default panel-red">
                    <div class="row">
                        <div class="col-md-6">
                            <h3 class="login_h3">輔仁大學場協系統</h3>
                            <p class="login_p">登入：請於方框中輸入<span class="login_span">LDAP</span>帳號、密碼登入 
                        </div>
                        <div class="col-md-6">
                            <div class="panel-body">
                                <form class="form-horizontal"  action="/web/WEB_MemberLoginAction" method="post">
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-md-2 control-label login_label">帳號</label><br><br>
                                        <div class="col-md-12">
                                            <input type="text" name="student.studentAccount" class="form-control login_input" id="inputEmail3" placeholder="LDAP帳號">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPassword3" class="col-md-2 control-label login_label">密碼</label><br><br>
                                        <div class="col-md-12">
                                            <input type="password" name="student.studentPassword" class="form-control login_input" id="inputPassword3">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-10">
                                            <button type="submit" class="btn btn-default login_button btn_blue">登入</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
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
