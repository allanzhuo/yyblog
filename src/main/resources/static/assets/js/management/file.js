layui.use(['table', 'element'], function () {
    var table = layui.table
        , element = layui.element;
    element.render();

    var fileTable = table.render({
        elem: '#file-table'
        , url: common.url.prefix + '/file/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 80, title: 'ID', sort: true}
            , {field: 'name', title: '资源名称', edit: 'text', sort: true}
            , {field: 'url', title: '资源地址', edit: 'text', sort: true}
            , {
                field: 'post', title: '添加时间', sort: true, templet: function (d) {
                    if (d.post === null) {
                        return "未知时间";
                    } else {
                        return common.dateFormatter(d.post);
                    }
                }
            }
            , {fixed: 'right', title: '操作', width: 178, align: 'center', toolbar: '#fileTableBar'}
        ]]
    });

    //监听单元格编辑
    table.on('edit(file)', function (obj) {
        var value = obj.value;
        var data = obj.data;
        common.ajax(common.url.prefix + "/file/edit", obj.data, function (json) {
            if (json.code === common.status.ok) {
                layer.msg('修改成功！<br/>' + '[ID: ' + data.id + '] 行字段更改为：' + value)
            } else {
                layer.msg("修改出错，错误信息：" + json.message);
            }
        })
    });

    //监听工具条
    table.on('tool(file)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除么?', function (index) {
                common.ajax(common.url.prefix + "/file/delete", {id: data.id}, function (json) {
                    common.okMsgHandle(json, "删除成功");
                    if (json.code === common.status.ok) obj.del();
                    layer.close(index);
                })
            });
        }
    });

    $(".layui-btn[data-type=addFile]").on("click", function () {
        var index = layer.confirm($("#addFilePage").html(), {
            title: '新增资源'
            , type: 1
            , area: '480px'
        }, function () {
            common.ajax(common.url.prefix + "/file/add", {
                name: $("input.layui-input[name=name]").val()
                , url: $("input.layui-input[name=url]").val()
            }, function (json) {
                common.okHandle(json, index, "file-table");
            })
        });
    });

    table.on('sort(file)', function (obj) {
        fileTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });


});







