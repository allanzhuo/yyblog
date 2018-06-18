layui.define(function (exports) {
    exports('article', function (page, next, tpl) {
        return nextPage(page, next, tpl)
    });
});

var ani =
    '{{# layui.each(d.data, function(index, item){ }}' +
    '<div class="layui-collapse layui-panel layui-article animated fadeInUp">' +
    '   <div class="layui-colla-item">' +
    '       <div class="layui-colla-content layui-show layui-article">' +
    '           <fieldset class="layui-elem-field layui-field-title">' +
    '               <legend class="center-to-head">' +
    '                   <span class="layui-badge layui-bg-green"> {{ item.cateName }}</span>' +
    '                   {{# if(item.top){ }}' +
    '                   <span class="layui-badge layui-bg-cyan"> 置顶</span>' +
    '                   {{# } }}' +
    '                   <a href="/article/{{ item.id }}">{{ item.title }}</a>' +
    '               </legend>' +
    '               {{# if(item.cover === null || item.cover === ""){ }}' +
    '               <div class="layui-field-box">' +
    '                   &nbsp;&nbsp;&nbsp;&nbsp;{{ item.summary }}...&nbsp;<a class="loading" href="/article/{{ item.id }}">更多<i class="fa fa-angle-double-right"></i> </a>' +
    '               </div>' +
    '               {{# } }}' +
    '               {{# if(item.cover !== null && item.cover !== ""){ }}' +
    '               <div class="layui-field-box has-pic">' +
    '                   <div class="layui-row layui-col-space10">' +
    '                       <div class="layui-col-lg10 layui-col-md10 layui-col-sm10 layui-col-xs12">' +
    '                           &nbsp;&nbsp;&nbsp;&nbsp;{{ item.summary }}...&nbsp;<a class="loading" href="/article/{{ item.id }}">更多<i class="fa fa-angle-double-right"></i> </a>' +
    '                       </div>' +
    '                       <div class="layui-col-lg2 layui-col-md2 layui-col-sm2">' +
    '                           <img src="{{ item.cover }}" class="panel-pic">' +
    '                       </div>' +
    '                   </div>' +
    '               </div>' +
    '               {{# } }}' +
    '               <div class="operation">' +
    '                   <div class="tags">' +
    '                       {{# layui.each(d.tags[item.id], function(index1, item1){ }}' +
    '                       <span class="layui-badge-rim" onclick="searchTag(this)"><i class="fa fa-tag"></i> {{ item1.name }}</span>' +
    '                       {{# }); }}' +
    '                   </div>' +
    '                   <div class="info">' +
    '                       <span class="views"><i class="fa fa-thermometer-half"></i> {{ item.views }}<code style="font-family: cursive;">℃</code></span>' +
    '                       <span class="datetime"><i class="fa fa-bullhorn"></i> {{ common.dateFormatter(item.createTime,"/", false) }}</span>' +
    '                   </div>' +
    '               </div>' +
    '           </fieldset>' +
    '       </div>' +
    '   </div> ' +
    '</div>' +
    '{{# });  }}';

function nextPage(page, next, tpl) {
    var search = common.getParam("search");
    var cateId = common.getParam("cateId");
    var tag = common.getParam("tag");
    $.post("/next", {
        limit: 10,
        page: page,
        title: search,
        textContent: search,
        cateId: cateId,
        tag: tag
    }, function (json) {
        if (json.code === common.status.ok) {
            tpl(ani).render(json, function (html) {
                next(html, page < json.totalPage)
            });
            if (common.affix !== undefined && common.affix !== null) {
                common.affix.reinit();
            }
            if (search !== "" && search !== undefined && search !== null
                || cateId !== "" && search !== undefined && search !== null) {
                common.indexVM.quote.showSearch = true;
                common.indexVM.quote.searchCount = json.totalCount;
            }
            $(window).resize();
        }

    });
}

function searchTag(span) {
	var tag = $(span).text();
	tag = tag.substring(1);
    location.href = "/index?tag=" + tag
}