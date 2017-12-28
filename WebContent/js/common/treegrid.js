/***
 * 在该文件中，统一处理treegrid控件的操作方式
 */
/**
 * 初始化treegird列表
 * @param treeGridId table的Id
 * @param url  获取分页数据的url
 * @param height 该treegrid的高度
 * @returns
 */
function initTreeGrid(treeGridId,url,height){
	$('#'+treeGridId).treegrid({
		url:url,
		method:'GET',
		idField:'id',   //默认所有的id均为id,统一处理
		treeField:'text',  //所有的文本均为text,所有的treegrid均按此处理
		rownumbers:true,
		height:height,
		singleSelect:true,
		striped:true,
		fitColumns:true,
		toolbar:toolbar
	});
}
/**
 * 新增和编辑的最终执行方法
 * @param fm  表单的Id
 * @param treeGridId  table的Id
 * @param dlg 所弹出的新增和编辑的对话框的id
 * @returns
 */
function finalSave(fm,treeGridId,dlg){
	$("#"+fm).form('submit',{
		url:url,
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(result){
			result = JSON.parse(result);
			if(result.code){
				$('#'+treeGridId).treegrid('reload');
				$('#'+dlg).dialog('close');
				tipBox.Info('信息',result.msg);
			}else{
				tipBox.Error('错误',result.detailMsg);
			}
		}
	});
}
/**
 * 根据选中的行删除相应的项
 * @param treeGridId
 * @param url
 * @returns
 */
function finalDelete(treeGridId,url){
	var row = $('#'+treeGridId).datagrid('getSelected');
	if(row){
		$.ajax({
			url : url,
			method:'post',
			data:{Id:row.id}
		}).done(function(result){
			result = JSON.parse(result);
			if(result.code){
				$('#'+treeGridId).treegrid('reload');
				tipBox.Info('信息',result.msg);
			}else{
				tipBox.Error('错误',result.detailMsg);
			}
		});
	}
}