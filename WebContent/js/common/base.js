var joinCommon = {  // 用于存放关联项的id
		list : []
};
var toolbar = [{  //datagrid表格所具有的按钮
	text:'ReLoad',
	iconCls:'icon-reload',
	handler:function(){
		location.reload();
	}
},{
	text:'Add',
	iconCls:'icon-add',
	handler:Add
},'-',{
	text:'Modify',
	iconCls:'icon-edit',
	handler:Modify
},{
	text:'Delete',
	iconCls:'icon-remove',
	handler:Delete
}];
/**
 * 打开关联页面，该页面包含一个列表（datagrid),其中已有的关联为选中状态
 * @param joinDlg 打开关联页面的dlg
 * @param datagrid 打开关联页面后dlg中的datagrid
 * @param selectUrl 获取某个id所对应的关联选项
 * @param updateUrl 更新关联
 * @param joinDlgTitle 打开的dlg所显示的标题
 * @returns
 */
function JoinTable(joinDlg,datagrid,selectUrl,updateUrl,joinDlgTitle){
	joinCommon.list= [];  //每次打开时，进行初始化
	$('#'+joinDlg).dialog('open').dialog('setTitle',joinDlgTitle);  //设置打开对话框的表头
	$.get(selectUrl,function(data){  //根据指定的url，获取该行已关联的选项（如用户已关联的角色列表)
		data = JSON.parse(data);
		let size = data.length;
		while(size-->0){ //将获取到的关联项的id放入到列表中
			joinCommon.list.push(data[size].Id);
		}
		$('#'+datagrid).datagrid({
			url:updateUrl,
			method:'GET',
			rownumbers:true,
			striped:true,
			fitColumns:true,
			onLoadSuccess:function(data){
				let size = data.total;
				while(size-->0){
					if(joinCommon.list.indexOf(data.rows[size].id) != -1){
						$(this).datagrid('checkRow',size);
					}
				}
			},
			onCheck:function(index,row){
				var index = joinCommon.list.indexOf(row.id);
				if(index==-1){
					joinCommon.list.push(row.id);
				}
			},
			onUncheck:function(index,row){
				var index = joinCommon.list.indexOf(row.id);
				if(index!=-1){
					joinCommon.list.splice(index,1);
				}
			}
		});
	});
}
/**
 * treegird类型的table的设置方法，应和前面的进行合并
 * @param joinDlg
 * @param treeGrid
 * @param selectUrl
 * @param updateUrl
 * @param joinDlgTitle
 * @returns
 */
function JoinTreeTable(joinDlg,treeGrid,selectUrl,updateUrl,joinDlgTitle){
	joinCommon.list= [];  //每次打开时，进行初始化
	$('#'+joinDlg).dialog('open').dialog('setTitle',joinDlgTitle);  //设置打开对话框的表头
	$.get(selectUrl,function(data){  //根据指定的url，获取该行已关联的选项（如用户已关联的角色列表)
		data = JSON.parse(data);
		let size = data.length;
		while(size-->0){ //将获取到的关联项的id放入到列表中
			joinCommon.list.push(data[size].Id);
		}
		$('#'+treeGrid).treegrid({
			url:updateUrl,
			method:'GET',
			rownumbers:true,
			striped:true,
			idField:'id',   //默认所有的id均为id,统一处理
			treeField:'text',  //所有的文本均为text,所有的treegrid均按此处理
			fitColumns:true,
			checkbox:true,
			singleSelect:true,
			onlyLeafCheck:true,
			onLoadSuccess:function(row,data){
				//获取所有选中的node
				let checkedNodes = $('#'+treeGrid).treegrid('getCheckedNodes');
				let checkedList = [];
				joinCommon.list.forEach(function(ele){
					checkedList.push(ele);
				});
				for(var node in checkedNodes){
					$('#'+treeGrid).treegrid('uncheckNode',checkedNodes[node].id);
				}
				for(var id in checkedList){
					$('#'+treeGrid).treegrid('checkNode',checkedList[id]);
				}
			},
			onCheckNode:function(row,checked){
				var index = joinCommon.list.indexOf(row.id);
				if(checked){  //被选中
					if(index==-1){
						joinCommon.list.push(row.id);
					}
				}else{
					if(index!=-1){
						joinCommon.list.splice(index,1);
					}
				}
				
			}
		});
	});
}

/**
 * 将关联关系提交
 * @param relatedId  关联的id
 * @param url 更新的url
 * @param dlg 更新之后需要自动关闭的dlg
 * @returns
 */
function updateJoinTable(relatedId,url,dlg){
	$.ajax({
		url:url,
		method:'POST',
		data:{Id:relatedId,commonIdList:joinCommon.list.join()}
	}).done(function(data){
		$('#'+dlg).dialog('close');
		try{
			data = JSON.parse(data);
		}catch(e){
			data = data;
		}finally{
			tipBox.Info('更新结果',data.msg);
		}			
	});
}

/**
 * 格式化时间
 * @param value
 * @param row
 * @param index
 * @returns
 */
function fmtTime(value,row,index){
	return new Date(value).toJSON().replace('T',' ').split('.')[0];
}