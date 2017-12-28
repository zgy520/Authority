<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta content="width=device-width,initial-scale=1" name="viewport">
<title>用户信息</title>
<!-- Bootstrap -->
<link href="Library/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
<link href="Library/bootstrap-3.3.7-dist/css/bootstrap-table.min.css" rel="stylesheet"/>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<h1>用户信息管理页面</h1>
	<table id="userTable" class="table table-hover"
			data-pagination="true"
			data-show-refresh="true"
			data-show-toggle="true"
			data-showColumns="true">
		<thead>
			<tr>
				<th data-field="userName">用户名称</th>
				<th data-field="loginName">登陆名称</th>
				<th data-field="email">邮箱</th>
				<th data-field="serial">序列号</th>
				<th data-field="loginCount">登陆次数</th>
				<th data-field="creator">创建者</th>
				<th data-field="createTime">创建时间</th>
				<th data-field="updator">更新者</th>
				<th data-field="updateTime">更新时间</th>
				<th class="col-xs-2" data-field="action" data-formatter="actionFormatter"
					data-events="actionEvents">Action</th>
			</tr>
		</thead>
	</table>
	
	<form action="users/addUser" method="POST">
		LoginName:<input type="text" name="loginName"><br />
		UserName:<input type="text" name="userName"><br />
		Email:<input type="text" name="email"><br />
		Serial:<input type="text" name="serial"> <br />
		<input type="submit" value="Submit" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	<!-- Jquery -->
	<script type="text/javascript" src="Library/jquery-3.2.1/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="Library/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="Library/bootstrap-3.3.7-dist/js/bootstrap-table.min.js"></script>
	<script type="text/javascript">
		function initTable(){
			//先销毁表格
			$('#userTable').bootstrapTable('destroy');
			//初始化表格，动态从服务器加载数据
			$('#userTable').bootstrapTable({
				method:"GET",
				url:"users/allUsers",  //获取数据的地址
				striped:true,  //表格显示条纹
				pagination:true, //启动分页
				pageSize:2, //每页显示的记录数
				pageNumber:1, //当前第几页
				pageList:[5,10,15,20,25],  //记录数可选列表
				search:true,  //是否启用查询
				showColumns:true,  //显示下拉框勾选要显示的列
				showRefresh:true,  //显示刷新按钮
				sidePagination:"server",  //表示服务端请求
				queryParamsType:"undefined", //设置undefined可以获取pageNumber,pageSize,searchText,sortName，sortOrder
											 //设置limit,可以获取limit,offset,serach,sort,order
				 queryParams:function queryParams(params){ //设置查询参数
					var param = {
						pageNumber: params.pageNumber,
						pageSize: params.pageSize,
						orderNum: $("#orderNum").val()
						};
						return param;					
				},
				onLoadSucess:function(data){ //加载成功时执行
					console.log("加载成功"+data);
				},
				onLoadError:function(){ //加载失败时执行
					console.log("加载失败");
				}				
			});
		}
		$(function(){
			console.log("开始加载表格数据");
			initTable();
		})
	</script>
</body>
</html>