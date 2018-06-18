layui.use(['element', 'layer', 'form'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;

    element.render();
    form.render();

    form.on('submit(simpleArticlePost)', function (data) {
        common.ajax(common.url.prefix + "/simple/add/article", data.field, function (json) {
            common.okMsgHandle(json, "保存草稿成功！");
        });
        return false;
    });

    form.on('submit(simpleNotePost)', function (data) {
        common.ajax(common.url.prefix + "/simple/add/note", data.field, function (json) {
            common.okMsgHandle(json, "发布随笔成功！");
        });
        return false;
    });

});






