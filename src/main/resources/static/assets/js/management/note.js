layui.use(['element', 'form'], function () {
    var form = layui.form;
    var element = layui.element;
    element.render();
    form.render();

    var E = window.wangEditor;
    var editor = new E('#note-editor');
    editor.create();


    var post = function (data, msg) {
        data.field.content = editor.txt.html();
        data.field.tagName = $("input[name=tagNames]").val();
        $.ajax({
            type: "post"
            , url: common.url.prefix + "/note/add"
            , dataType: "json"
            , data: data.field
            , success: function (json) {
                common.okMsgHandle(json, msg)
            }
        });
    };
    //监听提交
    form.on('submit(noteSubmit)', function (data) {
        post(data, "发布笔记成功！");
        location.hash = vipspa.stringifyParam("notes", {});
        return false;
    });

});


$(function () {
    $('input#tagName').tagsInput({
        defaultText: "点我新增标签",
        width: "auto",
        height: "32px",
        minChars: 1,
        onChange: function (a, e) {
            var length = $("input#tagName").val().split(",").length;
            if (length > 4) {
                layer.msg("最多只能4个！");
                $("#tagName").removeTag(e)
            }
        }
    });
})



