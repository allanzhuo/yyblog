layui.use(['element', 'form'], function () {
    var form = layui.form;
    var element = layui.element;
    element.render();
    form.render();

    //监听提交
    form.on('submit(taskForm)', function (data) {
        common.ajax(common.url.prefix + "/task/save", data.field, function (json) {
        	if (json.code === common.status.ok) {
        		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        		parent.layui.table.reload('task-table',{page: {curr: 1}});
        		parent.layer.close(index); //再执行关闭 
            } else {
                layer.msg("新增任务失败：" + json.message);
            }
        });
        return false;
    });

});


