<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta content="width=device-width,initial-scale=1" name="viewport">
<title>角色信息</title>
<link href="Library/gijgo-combined-1.6.1/css/gijgo.css" rel="stylesheet" />
<link href="Library/bootstrap-4.0.0-beta.2/css/bootstrap.min.css" rel="stylesheet" />
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-9">
				<form class="form-inline">
					<input id="roleName" type="text" placeholder="角色名" class="form-control mb-2 mr-sm-2 mb-sm-0" />
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
						<label for="roleNameA">roleName</label>
						<input type="text" pattern=".{3,6}" title="3 characters minimum" class="form-control" 
							id="roleNameA" name="roleNameA" required="true">
						<div class="invalid-feedback">
							Please provide a valide userName
						</div>
					</div>
					<div class="form-group">
						<label for="serial">serial</label>
						<input type="number" class="form-control" id="serial" name="serial"/>
					</div>
					<!-- <button type="button" id="btnSave" class="btn btn-primary">保存</button> -->
					<input type="button" class="btn btn-default" value="保存" id="btnSave">
					<button type="button" id="btnCancel" class="btn btn-default">取消</button>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="Library/jquery-3.2.1/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="Library/jquery-3.2.1/jquery-1.17.0.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
	<script type="text/javascript" src="Library/bootstrap-4.0.0-beta.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="Library/gijgo-combined-1.6.1/js/gijgo.min.js"></script>
	<script type="text/javascript" src="js/user/role.js"></script>
</body>
</html>