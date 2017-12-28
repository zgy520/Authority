var grid,dialog,flag=false;
$(function(){
	grid = $('#grid').grid({
		primaryKey:"id",
		dataSource:'roles/allRoles',
		uiLibrary:'bootstrap4',
		iconsLibrary:'fontawesome',
		columns:[
			{field:'id',title:'id',hidden:true},
			{field:'roleName',sortable:true},
			{field:'serial',title:'序号',sortable:true},
			{field:'createTime',title:'创建时间'},
			{field:'updateTime',title:'更新时间'},
			{title:'',field:'Edit',width:38,type:'icon',icon:'fa fa-pencil',tooltip:'Edit',events:{'click':Edit}},
			{title:'',field:'Delete',width:38,type:'icon',icon:'fa fa-remove',tooltip:'Delete',events:{'click':Delete}},
			
		],
		pager:{limit:5,sizes:[2,5,10,15]}
	});
	dialog = $('#dialog').dialog({
		uiLibrary:'bootstrap4',
		iconsLibrary:'fontawesome',
		autoOpen:false,
		resizable:false,
		modal:true
	});
	$('#btnAdd').on('click',function(){
		$('#roleNameA').val('');
		dialog.open('新增用户');
	});
	$('#btnSave').on('click',Save);
	$('#btnCancel').on('click',function(){
		dialog.close();
	});
	$('#btnSearch').on('click',function(){
		grid.reload({roleName:$('#roleName').val() });
	});
	$('#btnClear').on('click',function(){
		$('#roleName').val('');
		grid.reload({roleName:''});
	});
});
function Edit(e){
	$('#id').val(e.data.record.id);
	$('#roleNameA').val(e.data.record.roleName);
	$('#serial').val(e.data.record.serial);
	flag = true;
	dialog.open('修改角色');
}
function Delete(e){
	if(confirm('Are you sure?')){
		$.ajax({
			url:'roles/deleteRole',
			data:{id:e.data.record.id},
			method:'POST'
		}).done(function(){
			grid.reload();
		}).fail(function(){
			alert('Failed to delete.');
		})
	}
}
function Save(){
	if($('#saveForm')[0].checkValidity()){
		if(flag){
			updateRole();
			flag = false;
		}else{
			saveRole('roles/addRole');
		}
	}else{
		//如果使用的type为button而非submit，则需要使用该语句将自带的默认错误信息显示出来
		$('#saveForm')[0].reportValidity();  
	}
	
	
}

function updateRole(){
	var record = {
			Id:$('#id').val(),
			roleName:$('#roleNameA').val(),
			serial:$('#serial').val()	
	};
	$.ajax({
		url:"roles/updateRole",
		data:record,
		method:'POST'
	}).done(function(){
		flag = false;
		dialog.close();
		grid.reload();
	}).fail(function(){
		flag = false;
		alert('Failed to Update.');
		dialog.close();
	});
}

function saveRole(url){
	$.ajax({
		url:url,
		data:{
			roleName:$('#roleNameA').val(),
			serial:$('#serial').val()
		},
		method:'POST'
	}).done(function(){
		dialog.close();
		grid.reload();
	}).fail(function(){
		alert('Failed to save.');
		//dialog.close();
	});
}