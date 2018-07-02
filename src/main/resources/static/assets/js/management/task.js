layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , form = layui.form
        , element = layui.element;

    element.render();
    var taskTable = table.render({
        elem: '#task-table'
        , height: 'full'
        , url: common.url.prefix + "/task/list"
        , cellMinWidth: 90
        , limit: 10
        , size: 'lg'
        , method: "post"
        , cols: [[
             {type:'checkbox'}
            ,{field: 'id', width: 40, title: 'ID', align:'center'}
            , {field: 'jobName', width: '10%', title: '任务名称', edit: 'text'}
            , {field: 'description', width: '10%', title: '任务描述', edit: 'text'}
            , {field: 'jobGroup', width: '10%', title: '任务分组', edit: 'text'}
            , {field: 'beanClass', width: '30%', title: '任务类', edit: 'text'}
            , {field: 'cronExpression', title: 'cron表达式', edit: 'text'}
            , {title: '任务状态', width: 90, align: 'center', toolbar: '#jobStatusTpl'}
            , {title: '操作', width: 200, align: 'center', toolbar: '#taskBar'}
        ]]
        , page: true
    });
    var $ = layui.$, active = {
        reload: function () {
            var descSearch = $('#task-name-search');
            //执行重载
            table.reload('task-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                	description: descSearch.val()
                }
            });
        },
    	batchRemove: function () {
    		var descSearch = $('#task-name-search');
    		var checkStatus = table.checkStatus('task-table');
    		var data = checkStatus.data;
    		if (data.length == 0) {
    	        layer.msg("请选择要删除的数据");
    	        return;
    	    }
    		layer.confirm("确认要删除选中的【" + data.length + "】条数据吗?", function (index) {
    			 var ids = new Array();
    		     // 遍历所有选择的行数据，取每条数据对应的ID
    			 for (var i = 0; i < data.length; i++) {
    				 ids[i] = data[i].id;
				 }
                 common.ajax(common.url.prefix + "/task/removeBatch", {ids: ids}, function (json) {
                 	if (json.code === common.status.ok) {
                         layer.msg('删除成功！');
                         active.reload();
                     } else {
                         layer.msg("删除失败！" + json.message);
                     }
                 });
                 layer.close(index);
             });
        },
        insert: function () {
        	layer.open({
        		 type: 2,
        		 title: '新增计划任务',
        	     area: ['455px', '360px'],
        	     maxmin: true,
        	     shadeClose: true, //点击遮罩关闭层        
        	     content: '/management/task/add'
        	});
        }
    };
    
    //监听单元格编辑
    table.on('edit(task)', function (obj) {
        var value = obj.value;
        var data = obj.data;
        common.ajax(common.url.prefix + "/task/edit", obj.data, function (json) {
            if (json.code === common.status.ok) {
                layer.msg('修改成功！<br/>' + '[ID: ' + data.id + '] 行字段更改为：' + value)
            } else {
                layer.msg("修改出错，错误信息：" + json.message);
            }
        })
    });

    $('#task-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //监听工具条
    table.on('tool(task)', function (obj) {
        var data = obj.data;
        if (obj.event === 'run') {
        	common.ajax(common.url.prefix + "/task/run/" + data.id, {}, function (json) {
                if (json.code === common.status.ok) {
                    layer.msg('运行成功！')
                } else {
                    layer.msg("运行失败！" + json.message);
                }
            });
        } else if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                common.ajax(common.url.prefix + "/task/remove/" + data.id, {}, function (json) {
                	if (json.code === common.status.ok) {
                		obj.del();
                        layer.msg('删除成功！')
                    } else {
                        layer.msg("删除失败！" + json.message);
                    }
                });
                layer.close(index);
            });
        }
    });

    form.on('switch(jobStatus)', function (obj) {
        common.ajax(common.url.prefix + "/task/changeStatus/" + this.value, {jobStatus: obj.elem.checked}, function (json) {
            common.okMsgHandle(json);
            layer.tips('任务：' + ((obj.elem.checked) ? "开启" : "关闭"), obj.othis);
        });
    });

});