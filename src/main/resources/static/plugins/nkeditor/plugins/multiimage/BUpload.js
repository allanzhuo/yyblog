/**
 * HTML5上传插件
 * @site https://git.oschina.net/blackfox/ajaxUpload
 * @author yangjian<yangjian102621@gmail.com>
 * @version 1.0.1
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

	window.BUpload = function(options) {

		options = $.extend({
			src : "src",
			upload_url : null,
			list_url : null,
			search_url : null,
			data_type : "json",
			top : 20,
			fileType : "image", //文件类型，默认是图片，可选flash,media,file
			max_filesize : 2048,    //unit:KB
			max_filenum : 20,
			no_data_text : "(⊙o⊙)亲，没有多数据了。",
			ext_allow : "jpg|png|gif|jpeg",
			ext_refuse : "exe|txt",
			errorHandler : function(messsage, type) {
				alert(messsage);
			},
			callback : function(data) {
				console.log(data);
			}
		}, options);

		//错误代码和提示消息
		var codeMessageMap = {
			'000' : '文件上传成功',
			'001' : '文件上传失败',
			'003' : '文件大小超出限制',
			'004' : '非法文件名后缀'
		};

		var mimeType = {
			"3gpp":"audio/3gpp, video/3gpp",
			"ac3":"audio/ac3",
			"asf":"allpication/vnd.ms-asf",
			"au":"audio/basic",
			"css":"text/css",
			"csv":"text/csv",
			"doc":"application/msword",
			"dot":"application/msword",
			"dtd":"application/xml-dtd",
			"dwg":"image/vnd.dwg",
			"dxf":"image/vnd.dxf",
			"gif":"image/gif",
			"htm":"text/html",
			"html":"text/html",
			"jp2":"image/jp2",
			"jpe":"image/jpeg",
			"jpeg":"image/jpeg",
			"jpg":"image/jpeg",
			"js":"text/javascript, application/javascript",
			"json":"application/json",
			"mp2":"audio/mpeg, video/mpeg",
			"mp3":"audio/mpeg",
			"mp4":"audio/mp4, video/mp4",
			"mpeg":"video/mpeg",
			"mpg":"video/mpeg",
			"mpp":"application/vnd.ms-project",
			"ogg":"application/ogg, audio/ogg",
			"pdf":"application/pdf",
			"png":"image/png",
			"pot":"application/vnd.ms-powerpoint",
			"pps":"application/vnd.ms-powerpoint",
			"ppt":"application/vnd.ms-powerpoint",
			"rtf":"application/rtf, text/rtf",
			"svf":"image/vnd.svf",
			"tif":"image/tiff",
			"tiff":"image/tiff",
			"txt":"text/plain",
			"wdb":"application/vnd.ms-works",
			"wps":"application/vnd.ms-works",
			"xhtml":"application/xhtml+xml",
			"xlc":"application/vnd.ms-excel",
			"xlm":"application/vnd.ms-excel",
			"xls":"application/vnd.ms-excel",
			"xlt":"application/vnd.ms-excel",
			"xlw":"application/vnd.ms-excel",
			"xml":"text/xml, application/xml",
			"zip":"aplication/zip",
			"xlsx":"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
		}

		var o = {};
		o.dialog = null;
		o.todoList = new Array(); //the file queue to be uploaded
		o.uploadSuccessNum = 0; //已经上传成功的图片数量
		o.selectedList = new Array(); //the file queue upload successfully
		o.addedFileNumber = 0; //the numbers of files that has added
		o.totalFilesize = 0; //total file size
		o.uploadLock = false; //upload thread lock
		o.page = 1; //服务器图片列表页码
		o.marker = null, //七牛云上传的分页标识
		o.searchPage = 1; //图片搜索页码
		o.searchText = null; //搜索文字
		o.noRecord = false;
		o.searchList = []; //选中的搜索图片
		var dialogSCode = Math.ceil(Math.random() * 1000000000000); //对话框的令牌，如果创建多个BUpload上传对象用来保持唯一性

		//close the dialog
		o.close = function () {
			o.dialog.remove();
			if (typeof options.close == 'function') {
				options.close();
			}
		}

		//create dialog
		function createDialog() {

			var builder = new StringBuilder();
			builder.append('<div class="uedbody ke-animated"><div class="ued_title">');
			builder.append('<div class="uedbar"><span>'+options.lang.title+'</span></div><div class="close_btn icon"' +
				' title="'+options.lang.closeText+'"></div>');
			builder.append('</div><div class="wrapper"><div id="wra_head" class="wra_head"><span class="tab' +
				' tab-upload focus" tab="upload-panel">'+options.lang.localUpload+'</span>');
			if ( options.list_url != null ) {
				builder.append('<span class="tab tab-online" tab="online">'+options.lang.fileServer+'</span>');
			}
			if ( options.search_url != null ) {
				builder.append('<span class="tab tab-search" tab="searchbox">'+options.lang.imgSearch+'</span>');
			}
			builder.append('</div><div class="wra_body"><div class="tab-panel upload-panel"><div class="wra_pla"><div class="upload-image-placeholder">');
			builder.append('<div class="btn btn-primary image-select">'+options.lang.selectFile+'</div><input type="file" name="'+options.src+'" class="webuploader-element-invisible"' +
				' multiple="multiple" accept="'+getAccept()+'">');
			builder.append('</div></div><div class="image-list-box" style="display: none;"><div class="wra_bar"><div class="info fl"></div>');
			builder.append('<div class="fr"><span class="btn btn-default btn-continue-add">'+options.lang.continueAdd+'</span><span class="btn btn-primary btn-start-upload">'+options.lang.startUpload+'</span></div></div>');
			builder.append('<ul class="filelist"></ul></div></div><div class="tab-panel online"><div class="imagelist"><ul class="list clearfix"></ul><div class="no-data"></div></div></div>');
			builder.append('<div class="tab-panel searchbox"><div class="search-bar"><input class="searTxt"' +
				' type="text" placeholder="'+options.lang.searchPlaceholder+'" />');
			builder.append('<input value="'+options.lang.searchBtn+'" class="btn btn-primary btn-search" type="button" /><input value="'+options.lang.searchClear+'" class="btn btn-default btn-reset" type="button" />');
			builder.append('</div><div class="search-imagelist-box"><ul class="search-list"></ul><div class="no-data"></div></div>');
			builder.append('</div><div class="loading-icon"></div></div><!-- end of wrapper --></div><div class="wra-btn-group"><span class="btn btn-primary btn-confirm">'+options.lang.confirmBtnText+'</span>');
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
			o.dialog.draggable({handler : o.dialog.find(".ued_title")})

		}

		//绑定元素事件
		function bindEvent() {

			//选项卡事件
			G(".tab").on("click", function() {
				var tab = $(this).attr("tab");
				G(".tab-panel").hide();
				G("."+tab).show();
				G(".tab").removeClass("focus");
				$(this).addClass("focus");
			});

			//关闭对话框
			G(".close_btn").on("click", function() {
				o.close();
			});

			//选择文件事件
			G(".webuploader-element-invisible").on("change", function() {
				addFiles(this);
			});

			//弹出上传文件选择框
			G(".image-select").on("click", function() {
				G(".webuploader-element-invisible").trigger("click");
			});
			G(".btn-continue-add").on("click", function() {
				G(".webuploader-element-invisible").trigger("click");
			});

			//开始上传按钮事件
			G(".btn-start-upload").on("click", function() {
				if ( o.uploadLock ) return;

				if ( o.todoList.length == 0 ) {
					options.errorHandler(options.lang.noFileAdded, "error");
					return false;
				}
				$(this).addClass("disabled").text(options.lang.uploading);
				uploadFile(o.todoList.shift());
			});

			//点击确认|取消按钮事件
			G(".btn-confirm").on("click", function() {
				if ( o.todoList.length > 0 ) {
					options.errorHandler(options.lang.fileNotUpload, "error");
					return false;
				}
				if (o.selectedList.length == 0) {
					options.errorHandler(options.lang.noFileSelected, "error");
					return false;
				}
				//console.log(o.selectedList);
				//抓取网络图片，并更新图片链接
				if (o.searchList.length > 0) {
					var $message = $('<span class="loading-message">正在抓取网络图片……</span>')
					G(".loading-icon").show().html($message); //显示加载图标
					$.get(options.grap_url, {
						act : "grapImage",
						urls : encodeURI(o.searchList.join(","))
					}, function (res) {
						if (res.code != "000") {
							options.errorHandler(res.message, "error");
						} else {
							options.errorHandler(res.message, "ok");
							//删除之前的 url
							$.each(o.searchList, function(idx, item) {
								o.selectedList.remove(item);
							});
							//更新成新的 url
							$.each(res.items, function(idx, item) {
								o.selectedList.push(item);
							});
							options.callback(o.selectedList);
							o.close();
						}
						G(".loading-icon").hide().empty();
					}, "json");
				} else {
					options.callback(o.selectedList);
					o.close();
				}

			});
			G(".btn-cancel").on("click", function() {
				o.close();
			});

			//从服务器加载文件
			G(".tab-online").on("click", function() {

				if ( G(".imagelist .list").children().length == 0 ) {
					loadFilesFromServer()
				}

			});

			//当滚动条滚到底部时自动去加载图片
			G(".imagelist").on("scroll", function() {

				if ( this.scrollTop + this.clientHeight >= this.scrollHeight ) {
					loadFilesFromServer();
				}
			});

			G(".search-imagelist-box").on("scroll", function() {

				if ( this.scrollTop + this.clientHeight >= this.scrollHeight ) {
					imageSearch();
				}
			});

			//图片搜索事件
			G(".btn-search").on("click", function() {
				var text = G(".searTxt").val().trim();
				if ( text == "" ) {
					G(".searchbox .no-data").html('<span class="error">'+options.lang.searchPlaceholder+'</span>').show();
					G(".searTxt").focus();
					return false;
				}
				o.searchText = text;
				o.searchPage = 1;
				G(".search-imagelist-box").find(".search-list").empty();
				imageSearch();
			});
			//重置搜索
			G(".btn-reset").on("click", function() {
				G(".searTxt").val("");
			});
		}

		//add file to upload list
		function addFiles(input) {

			var files = input.files;
			var totalFileNum = o.todoList.length + o.uploadSuccessNum + files.length; //本次上传文件总数
			for ( var i = o.addedFileNumber; i < o.addedFileNumber+files.length; i++ ) {

				if ( totalFileNum > options.max_filenum ) {
					options.errorHandler(KindEditor.tmpl(options.lang.uploadLimit, {uploadLimit: options.max_filenum}), "error");
					return;
				}
				var builder = new StringBuilder();
				var tempFile = files[i- o.addedFileNumber];
				builder.append('<li id="img-comtainer-'+dialogSCode+i+'"><div class="imgWrap">');

				//如果上传的不是图片，则通过判断文件后缀来显示不同的图标
				var extension = getFileExt(tempFile.name);
				if ( extension == '' ) extension = "default";
				extension = extension.toLowerCase();
				if ( "jpg|jpeg|gif|png|bmp".indexOf(extension) == -1 ) {
					builder.append('<span class="icon-placeholder icon-default icon-'+extension+'"></span>');
				} else {
					builder.append('<img src="'+window.URL.createObjectURL(tempFile)+'" border="0" />');
				}

				builder.append('</div><div class="file-opt-box clearfix"><span class="remove" index="'+i+'">'+options.lang.remove+'</span><span class="rotateRight">'+options.lang.rotateRight+'</span>');
				builder.append('<span class="rotateLeft">'+options.lang.rotateLeft+'</span></div><div class="success"></div><div class="error"></div>');
				builder.append('<div class="progress"><span style="display: none; width: 0px;"></span></div></li>');

				var $image = $(builder.toString());
				//bind onelele event
				$image.find(".remove").on("click", function() {
					$(this).parents("li").remove(); //remove element
					//remove file from todoList
					var index = $(this).attr("index");
					for ( var i = 0; i < o.todoList.length; i++ ) {
						if ( o.todoList[i].index == index ) {
							o.totalFilesize -= o.todoList[i].file.size;
							updateInfoText(o.uploadSuccessNum + o.todoList.length-1, o.totalFilesize);
							o.todoList.splice(i, 1);
							break;
						}
					}
					if (G(".filelist li").length == 0) {
						G(".image-list-box").hide();
						G(".wra_pla").show();
					}
				});
				$image.on("mouseover", function() {
					$(this).find(".file-opt-box").show();
				}).on("mouseout", function() {
					$(this).find(".file-opt-box").hide();
				});

				G(".wra_pla").hide();
				G(".image-list-box").show();
				G(".filelist").append($image);

				o.todoList.push({index:i, file:tempFile});
				o.totalFilesize += tempFile.size;

				//console.log(tempFile);
			}
			o.addedFileNumber += files.length;
			updateInfoText(o.uploadSuccessNum + o.todoList.length, o.totalFilesize);

			//缩放并裁剪图片
			$(".imgWrap img").imageCrop(113,113);

		}

		/**
		 * upload file function(文件上传主函数)
		 * @param node 数据节点
		 */
		function uploadFile(node) {

			if ( !fileCheckHandler(node) ) {
				uploadNextFile();   //skip the file and upload the next file
				return;
			}

			// prepare XMLHttpRequest
			var xhr = new XMLHttpRequest();
			xhr.open('POST', options.upload_url);
			//upload successfully
			xhr.addEventListener('load',function(e) {

				if ( options.data_type == "json" ) {
					//console.log(e);
					var data = $.parseJSON(e.target.responseText);
					if ( data.code == "000" ) {
						o.selectedList.push(data.item.url);   //添加文件到上传文件列表
						o.uploadSuccessNum++;
						$("#img-comtainer-"+dialogSCode+ node.index).find(".file-opt-box").remove();
						$("#img-comtainer-"+dialogSCode+ node.index).find(".progress").remove();
						$("#img-comtainer-"+dialogSCode+ node.index).find(".success").show();
					} else {
						__error__(codeMessageMap[data.code], node);
					}
				}

			}, false);

			// file upload complete
			xhr.addEventListener('loadend', function () {
				uploadNextFile();   //upload the next file
			}, false);

			//上传失败
			xhr.addEventListener('error', function() {
				__error__(options.lang.uploadFail, node);
			}, false);

			xhr.upload.addEventListener('progress', function(e) {
				updateProgress(e, node);
			}, false);

			// prepare FormData
			var formData = new FormData();
			formData.append(options.src, node.file);
			xhr.send(formData);

		}

		//upload next file(上传下一个文件)
		function uploadNextFile() {

			if ( o.todoList.length ) {
				var nextFile = o.todoList.shift();
				uploadFile(nextFile);
			} else {
				o.uploadLock = false; //release the upload lock
				G(".btn-start-upload").removeClass("disabled").text(options.lang.startUpload);
				//console.log(o.selectedList);
			}
		}

		// progress handler(文件上传进度控制)
		function updateProgress(e, node) {
			if ( e.lengthComputable ) {
				$("#img-comtainer-"+dialogSCode+ node.index).find(".progress span").css({"width" : (e.loaded/e.total)*100+'%', "display":"block"});
			}
		}

		//update file info text
		function updateInfoText(filenum, filesize) {
			var text = KindEditor.tmpl(options.lang.uploadDesc, {numSelect:filenum, totalSize:formatFileSize(filesize), numLeft:(options.max_filenum - filenum)});
			G(".info").text(text);
		}

		//format file size(格式化文件大小)
		function formatFileSize(size) {

			if ( size/1048576 > 1 ) {
				return (size/1048576).toFixed(2)+"MB";
			} else {
				return (size/1024).toFixed(2)+"KB";
			}

		}

		//file check handler(文件检测处理函数)
		function fileCheckHandler(node) {

			//检查文件大小
			var maxsize = options.max_filesize * 1024;
			if ( maxsize > 0 && node.file.size > maxsize ) {
				__error__(KindEditor.tmpl(options.lang.sizeLimit, {sizeLimit:options.max_filesize}), node);
				return false;
			}

			//检查文件后缀名
			var ext = getFileExt(node.file.name);
			if ( ext && options.ext_allow.indexOf(ext) != -1
				&& options.ext_refuse.indexOf(ext) == -1 ) {
				return true;
			} else {
				__error__(KindEditor.tmpl(options.lang.invalidExt, {invalidExt:ext}), node);
				return false;
			}

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

		//获取可接受的文件后缀
		function getAccept() {
			var extensions = options.ext_allow.split("|");
			var accept = [];
			$.each(extensions, function(idx, item) {
				accept.push(mimeType[item]);
			});
			if ( accept.length > 1 ) {
				return accept.uinque().join(",");
			}
			return "*";
		}

		//显示上传错误信息
		function __error__(message, node) {
			G("#img-comtainer-"+dialogSCode+ node.index).find(".error").show().text(message);
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
				fileType : options.fileType,
			}, function(res) {

				G(".loading-icon").hide(); //隐藏加载图标
				if ( res.code == "000" ) {

					if (!res.items[0]) { //没有加载到数据
						G(".online .no-data").text(options.lang.noDataText).show();
						return;
					}
					o.marker = res.item; //存储marker
					o.page++;
					appendFiles(res.items, "online");
				} else {
					G(".online .no-data").text(options.lang.noDataText).show();
					o.noRecord = true;
				}

			}, "json");
		}

		//图片搜索
		function imageSearch() {
			if ( !options.search_url ) {
				G(".searchbox .no-data").html('<span class="error">'+options.lang.noSearchUrl+'</span>').show();
				return false;
			}

			G(".loading-icon").show(); //显示加载图标
			$.get(options.search_url, {
				page : o.searchPage,
				kw : o.searchText
			}, function(res) {

				G(".loading-icon").hide(); //隐藏加载图标
				if ( res.code == "000" ) {
					G(".searchbox .no-data").hide();
					o.searchPage++;
					appendFiles(res.items, "search");
				} else {
					G(".no-data").text(options.no_data_text).show();
				}

			}, "json");
		}

		//追加元素到图片列表
		function appendFiles(data, module) {

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
					builder.append('<span class="icon-placeholder icon-'+extension+'" data-src="'+item.oriURL+'"></span>');
				} else {
					builder.append('<img src="'+item.thumbURL+'" data-src="'+item.oriURL+'" border="0">');
				}

				builder.append('<span class="ic" data-module="'+module+'"><em class="img-size">'+imgSize+'</em></span></li>');
				var $image = $(builder.toString());

				//绑定选择图片事件
				$image.find(".ic").on("click", function() {
					var src = $(this).prev().data("src");
					var module = $(this).data("module");
					if ( $(this).hasClass("selected") ) {
						$(this).removeClass("selected");
						o.selectedList.remove(src);
						if (module == "search") {
							o.searchList.remove(src);
						}
					} else {
						$(this).addClass("selected");
						o.selectedList.push(src);
						if (module == "search") {
							o.searchList.push(src);
						}
					}
					//console.log(o.selectedList);
				});
				//裁剪显示图片
				$image.find("img").imageCrop(113, 113);
				if ( module == "online" ) {
					G(".imagelist .list").append($image);
				} else if ( module == "search" ) {
					G(".search-imagelist-box .search-list").append($image);
				}
			});

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