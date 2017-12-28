<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>基于bootstrap4的用户管理页面</title>
<link href="Library/gijgo-combined-1.6.1/css/gijgo.css" rel="stylesheet" />
<link href="Library/bootstrap-4.0.0-beta.2/css/bootstrap.min.css" rel="stylesheet" />
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link href="css/user/user.css" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-9">
				<form class="form-inline">
					<input id="userName" type="text" placeholder="用户名" class="form-control mb-2 mr-sm-2 mb-sm-0" />
					<input id="loginName" type="text" placeholder="登录名" class="form-control mb-2 mr-sm-2 mb-sm-0"/>
					<button id="btnSearch" type="button" class="btn btn-default">搜索</button>&nbsp;
					<button id="btnClear" type="button" class="btn btn-default">清空</button>
				</form>
			</div>
			<div class="col-3">
				<button id="btnAdd" type="button" class="btn btn-default pull-right">新增</button>
			</div>
			<div class="row" style="margin-top:10px">
				<div class="col-12">
					<table id="grid"></table>
				</div>
			</div>
			
			<div id="dialog" style="display:none">
				<input type="hidden" id="id" name="id" />
				<form class="was-validated" id="saveForm">
					<div class="form-group">
						<label for="userNameA">userName</label>
						<input type="text" pattern=".{3,6}" title="3 characters minimum" class="form-control" 
							id="userNameA" name="userNameA" required="true">
						<div class="invalid-feedback">
							Please provide a valide userName
						</div>
					</div>
					<div class="form-group">
						<label for="loginName">loginName</label>
						<input type="text" class="form-control" id="loginNameA" name="loginNameA" required>
						<div class="invalid-feedback">
							请提供合法的登录名
						</div>
					</div>
					<div class="form-group">
						<label for="email">email</label>
						<input type="email" class="form-control" id="email" name="email"/>
					</div>
					<div class="form-group">
						<label for="serial">serial</label>
						<input type="text" class="form-control" id="serial" name="serial"/>
					</div>
					<!-- <button type="button" id="btnSave" class="btn btn-primary">保存</button> -->
					<input type="button" class="btn btn-default" value="保存" id="btnSave">
					<button type="button" id="btnCancel" class="btn btn-default">取消</button>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</div>
			<div id="CrossForm" style="display: none">
				<p>测试</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="Library/jquery-3.2.1/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="Library/jquery-3.2.1/jquery-1.17.0.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
	<script type="text/javascript" src="Library/bootstrap-4.0.0-beta.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="Library/gijgo-combined-1.6.1/js/gijgo.min.js"></script>
	<script type="text/javascript" src="js/user/user.js"></script>
</body>
</html>