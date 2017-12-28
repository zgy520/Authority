var grid,dialog,flag=false,crossDialog;
$(function(){
	grid = $('#grid').grid({
		primaryKey:"id",
		dataSource:'users/allUsers',
		uiLibrary:'bootstrap4',
		iconsLibrary:'fontawesome',
		columns:[
			{field:'id',title:'id',hidden:true},
			{field:'userName',sortable:true},
			{field:'loginName',title:'登陆名称',sortable:true},
			{field:'email',title:'邮件地址'},
			{title:'',field:'Edit',width:38,type:'icon',icon:'fa fa-pencil',tooltip:'Edit',events:{'click':Edit}},
			{title:'',field:'Delete',width:38,type:'icon',icon:'fa fa-remove',tooltip:'Delete',events:{'click':Delete}},
			{title:'',field:'Join',width:38,type:'icon',icon:'fa fa-snowflake-o',tooltip:'join',events:{'click':'Join'}}
			
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
	crossDialog = $('#CrossForm').dialog({
		uiLibrary:'bootstrap4',
		iconsLibrary:'fontawesome',
		autoOpen:false,
		resizable:false,
		modal:true
	});
	$('#btnAdd').on('click',function(){
		$('#userName').val('');
		dialog.open('新增用户');
	});
	$('#btnSave').on('click',Save);
	$('#btnCancel').on('click',function(){
		dialog.close();
	});
	$('#btnSearch').on('click',function(){
		grid.reload({userName:$('#userName').val(),loginName:$('#loginName').val() });
	});
	$('#btnClear').on('click',function(){
		$('#userName').val('');
		grid.reload({userName:'',loginName:''});
	});
	
	var email = document.getElementById('email');
	email.addEventListener('input',function(event){
		if(email.validity.typeMismatch()){
			email.setCustomValidity('email格式不符合email的规定');
		}else{
			email.setCustomValidity("");
		}
	});
});
function Edit(e){
	$('#id').val(e.data.record.id);
	$('#userNameA').val(e.data.record.userName);
	$('#loginNameA').val(e.data.record.loginName);
	$('#email').val(e.data.record.email);
	$('#serial').val(e.data.record.serial);
	flag = true;
	dialog.open('修改用户');
}
/**
 * 建立关联
 * @param e
 * @returns
 */
function join(e){
	
}
function Delete(e){
	if(confirm('Are you sure?')){
		$.ajax({
			url:'users/deleteUser',
			data:{loginName:e.data.record.loginName},
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
			updateUser();
			flag = false;
		}else{
			saveUser('users/addUser');
		}
	}else{
		//如果使用的type为button而非submit，则需要使用该语句将自带的默认错误信息显示出来
		$('#saveForm')[0].reportValidity();  
	}
	
	
}

function updateUser(){
	var record = {
			Id:$('#id').val(),
			userName:$('#userNameA').val(),
			loginName:$('#loginNameA').val(),
			email:$('#email').val(),
			serial:$('#serial').val()	
	};
	$.ajax({
		url:"users/updateUser",
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

function saveUser(url){
	$.ajax({
		url:url,
		data:{
			userName:$('#userNameA').val(),
			loginName:$('#loginNameA').val(),
			email:$('#email').val(),
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
