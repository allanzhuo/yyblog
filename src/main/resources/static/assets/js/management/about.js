layui.use(['element', 'layer'], function () {
    var element = layui.element;
    var layer = layui.layer;

    element.render();

    var E1 = window.wangEditor;
    var editor1 = new E1('#website-editor');
    editor1.create();
    var editor2 = new E1('#blog-editor');
    editor2.create();
    var editor3 = new E1('#me-editor');
    editor3.create();

    $("#website-submit").click(function () {
        common.ajax(common.url.prefix + "/about/update", {tab: "about_website", content: editor1.txt.html()}, function (json) {
            common.okMsgHandle(json, "编辑成功！");
        })
    })

    $("#blog-submit").click(function () {
        common.ajax(common.url.prefix + "/about/update", {tab: "about_blog", content: editor2.txt.html()}, function (json) {
            common.okMsgHandle(json, "编辑成功！");
        })
    })

    $("#me-submit").click(function () {
        common.ajax(common.url.prefix + "/about/update", {tab: "about_me", content: editor3.txt.html()}, function (json) {
            common.okMsgHandle(json, "编辑成功！");
        })
    })

    $(".layui-tab-title>li:eq(0)").addClass("layui-this")

});






