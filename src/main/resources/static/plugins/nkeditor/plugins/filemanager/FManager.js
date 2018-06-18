/**
 * HTML5上传插件
 * @author yangjian<yangjian102621@gmail.com>
 * @version 1.0.1
 * @site https://git.oschina.net/blackfox/ajaxUpload
 */
(function($) {

	//判断浏览器是否支持html5
	if ( !window.applicationCache )
		throw new Error("您当前的浏览器不支持HTML5,请先升级浏览器才能使用该上传插件!");

	//image crop
	$.fn.imageCrop = function(__width, __height) {
		$(this).on("load", function () {

			var width, height, left, top;
			var orgRate = this.width/this.height;
			var cropRate = __width/__height;
			if ( orgRate >= cropRate ) {
				height = __height;
				width = __width * orgRate;
				top = 0;
				left = (width - __width)/2;
			} else {
				width = __width;
				height = __height / orgRate;
				left = 0;
				//top = (height - __height)/2;
				top = 0;
			}
			$(this).css({
				"position" : "absolute",
				top : -top + "px",
				left : -left + "px",
				width : width + "px",
				height : height + "px"
			});
		});
	}

	//make element draggable
	$.fn.draggable = function(options) {
		var defaults = {
			handler : null
		}
		options = $.extend(defaults, options);
		var __self = this;
		$(options.handler).mousedown(function(e) {
			var offsetLeft = e.pageX - $(__self).position().left;
			var offsetTop = e.pageY - $(__self).position().top;
			$(document).mousemove(function(e) {
				//清除拖动鼠标的时候选择文本
				window.getSelection ? window.getSelection().removeAllRanges():document.selection.empty();
				$(__self).css({
					'top'  : e.pageY-offsetTop + 'px',
					'left' : e.pageX-offsetLeft + 'px'
				});
			});

		}).mouseup(function() {
			$(document).unbind('mousemove');
		});

	}

	if ( Array.prototype.remove == undefined ) {
		Array.prototype.remove = function(item) {
			for ( var i = 0; i < this.length; i++ ) {
				if ( this[i] == item ) {
					this.splice(i, 1);
					break;
				}
			}
		}
	}
	if ( Array.prototype.uinque == undefined ) {
		Array.prototype.uinque = function() {
			var result = [], hash = {};
			for ( var i = 0, item; (item = this[i]) != null; i++ ) {
				if ( !hash[item] ) {
					result.push(item);
					hash[item] = true;
				}
			}
			return result;
		}
	}

	window.FManager = function(options) {

		options = $.extend({
			lang : {},
			list_url : null,
			data_type : "json",
			fileType : "image", //文件类型，默认是图片，可选flash,media,file
			top : 20,
			callback : function(data) {
				console.log(data);
			}
		}, options);

		var o = {};
		o.dialog = null;
		o.selectedList = new Array(); //the file queue upload successfully
		o.page = 1; //服务器图片列表页码
		o.marker = null, //七牛云上传的分页标识
		o.noRecord = false;

		//close the dialog
		o.close = function () {
			o.dialog.remove();
			try {JDialog.lock.hide();} catch (e) {}
			if (typeof options.close == 'function') {
				options.close();
			}
		}

		//create dialog
		function createDialog() {

			var builder = new StringBuilder();
			builder.append('<div class="uedbody ke-animated"><div class="ued_title">');
			builder.append('<div class="uedbar"><span>'+options.lang.title+'</span></div><div class="close_btn icon" title="'+options.lang.closeText+'"></div>');
			builder.append('</div><div class="wrapper"><div class="wra_body wra_body_server">');
			builder.append('<div class="tab-panel online"><div class="imagelist"><ul class="list clearfix"></ul><div class="no-data"></div></div></div>');
			builder.append('<div class="loading-icon"></div></div></div><div class="wra-btn-group">');
			builder.append('<div class="tip-text">'+options.lang.loadMoreData+'</div>')
			builder.append('<span class="btn btn-primary btn-confirm">'+options.lang.confirmBtnText+'</span>')
			builder.append('<span class="btn btn-default btn-cancel">'+options.lang.cancelBtnText+'</span></div></div>');

			o.dialog = $(builder.toString());
			$("body").append(o.dialog);
			if (options.top == 0) {
				options.top = ($(window).height() - o.dialog.height())/2;
			}
			o.dialog.css({
				left : ($(window).width() - o.dialog.width())/2 + "px",
				top : options.top + "px"
			});
			//给对话框添加拖拽事件
			o.dialog.draggable({handler : o.dialog.find(".ued_title")});
			loadFilesFromServer();

		}

		//绑定元素事件
		function bindEvent() {

			//关闭对话框
			G(".close_btn").on("click", function() {
				o.close();
			});

			//点击确认|取消按钮事件
			G(".btn-confirm").on("click", function() {
				options.callback(o.selectedList);
				o.close();
			});
			G(".btn-cancel").on("click", function() {
				o.close();
			});

			//当滚动条滚到底部时自动去加载图片
			G(".imagelist").on("scroll", function() {

				if ( this.scrollTop + this.clientHeight >= this.scrollHeight ) {
					loadFilesFromServer();
				}
			});

		}

		//query
		function G(query) {
			return o.dialog.find(query);
		}

		//从服务器上获取图片地址
		function loadFilesFromServer() {
			if ( !options.list_url ) {
				G(".online .no-data").html('<span class="error">'+options.lang.noListUrl+'</span>').show();
				return false;
			}
			if ( o.noRecord ) return false;

			G(".loading-icon").show(); //显示加载图标
			$.get(options.list_url, {
				page : o.page,
				marker : o.marker,
				fileType : options.fileType
			}, function(res) {

				G(".loading-icon").hide(); //隐藏加载图标
				if ( res.code == "000" ) {
					if (!res.items[0]) {
						G(".online .no-data").html(options.lang.noDataText).show();
						return;
					}
					o.page++;
					o.marker = res.item; //存储marker
					appendFiles(res.items);
				} else {
					G(".online .no-data").text(options.lang.noDataText).show();
					o.noRecord = true;
				}

			}, "json");
		}

		//追加元素到图片列表
		function appendFiles(data) {

			$.each(data, function(idx, item) {

				var builder = new StringBuilder();
				builder.append('<li>');
				var extension = getFileExt(item.thumbURL);
				if ( extension == '' ) extension = "default";
				extension = extension.toLowerCase();
				//如果不是图片，则根据文件的后缀名去加载对应的缩略图
				var imgSize = item.width+'x'+item.height; //图片尺寸
				if ( "jpg|jpeg|gif|png|bmp".indexOf(extension) == -1 ) {
					imgSize = formatFileSize(item.filesize); //如果是文件则显示文件大小
					builder.append('<span class="icon-placeholder icon-default icon-'+extension+'" data-src="'+item.oriURL+'"></span>');
				} else {
					builder.append('<img src="'+item.thumbURL+'" data-src="'+item.oriURL+'" border="0">');
				}

				builder.append('<span class="ic"><em class="img-size">'+imgSize+'</em></span></li>');
				var $image = $(builder.toString());

				//绑定选择图片事件
				$image.find(".ic").on("click", function() {
					var src = $(this).prev().attr("data-src");
					var oldSrc = $('.selected:eq(0)').prev().attr("data-src");
					//多选
					// if ( $(this).hasClass("selected") ) {
					// 	$(this).removeClass("selected");
					// 	o.selectedList.remove(src);
					// } else {
					// 	$(this).addClass("selected");
					// 	o.selectedList.push(src);
					// }
					//这里暂时改成单选
					$('.selected:eq(0)').removeClass("selected"); //移除之前的选中的图片
					o.selectedList.remove(oldSrc);
					$(this).addClass("selected");
					o.selectedList.push(src);
					//console.log(o.selectedList);
				});
				//裁剪显示图片
				$image.find("img").imageCrop(113, 113);
				G(".imagelist .list").append($image);
			});

		}

		//获取文件后缀名
		function getFileExt(filename) {
			if ( !filename ) return false;
			var position = filename.lastIndexOf('.')
			if ( position != -1 ) {
				return filename.substr(position+1).toLowerCase();
			}
			return false;
		}

		//format file size(格式化文件大小)
		function formatFileSize(size) {

			if ( size/1048576 > 1 ) {
				return (size/1048576).toFixed(2)+"MB";
			} else {
				return (size/1024).toFixed(2)+"KB";
			}

		}
		//initialize dialog
		createDialog();
		bindEvent();
		return o;
	}; //end of JUpload

	//string builder
	var StringBuilder = function() {

		var buffer = new Array();
		StringBuilder.prototype.append = function(str) {
			buffer.push(str);
		}
		
		StringBuilder.prototype.toString = function () {
			return buffer.join("");
		}

	}


})(jQuery);