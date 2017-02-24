<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>dialog弹出框benpaobenpao</title>
    <style>
        *{margin:0px;padding: 0px;}
        .clearfix{*zoom:1;}
        .clearfix:after{content:"\0020";display:block;height:0;clear:both;overflow:hidden;visibility:hidden;}
        .dialogbg{display:block; *zoom:1; position: absolute; left: 0px; top: 0px; height: 100%; width: 100%; opacity: 0.35;filter:alpha(opacity=30); background: #ccc;z-index: 9999;}
        .dialogbox{position: fixed;_position: absolute;-webkit-box-shadow: 0px 0px 6px #999;border: 1px solid #ccc;box-shadow: 0px 0px 3px #999;border-radius: 6px; z-index: 99999;left: 50%;top: 50%;background: #f5fffa;overflow: hidden;}
        .dialogboxrunstart{-webkit-transform: scale(0px,0px);-webkit-transition:all 0.2s ease-in-out;-moz-transform: scale(0px,0px);-moz-transition:all 0.2s ease-in-out;-ms-transform: scale(0px,0px);-ms-transition:all 0.2s ease-in-out;-o-transform: scale(0px,0px);-o-transition:all 0.2s ease-in-out;transform: scale(0px,0px);transition:all 0.2s ease-in-out;}
        .dialogtit{position: relative;height: 30px; line-height: 30px; overflow: hidden;padding: 0px 10px;border-bottom: 1px solid #274863;font-size: 16px;background: #4682b4;}
        .dialogclosed{position: absolute;right: 10px;color: #fff;font-weight: 700;font-size: 20px;cursor: pointer;}
        .dialogclosed:hover{color: #f00;}
        .dialogtxt{color: #fff;font-weight: 700;margin-right: 20px;}
        .dialogmain{padding: 10px 20px 10px 20px;}
        .dialogBtns{text-align: center;padding-top: 5px;}
        .dialogconform,.dialogcancel{display: inline-block;*display: inline;*zoom:1;padding: 0px 10px;height: 24px;line-height: 24px;color: #fff;font-size: 12px;border-radius: 4px;background: #005eac;border-width: 1px;border-style: solid;border-color: #b8d4e8 #124680 #124680  #b8d4e8;cursor: pointer;}
        .dialogcancel{margin-left: 10px;}
        .dialogconform:hover,.dialogcancel:hover{background: #0000ff;}

    </style>
    <script src="${ctx}/static/js/jquery-2.1.4.min.js"></script>
    <script src="http://files.cnblogs.com/kuikui/dot.min.js"></script>
    <style>
        body{height: 1500px;}
        .main{width: 960px; margin: 60px auto 0px; }
        .main .btn{width: 100px; height: 30px; line-height: 30px; text-align: center; cursor: pointer;}
        .mtable{ height: 100px; overflow-y: auto;}
        .mtable table{width: 100%; text-align: center;}
        .mtable table th, .mtable table td{padding:10px 0px;}
    </style>
</head>
<body>
    <div class="main">
        <div>
            <img src="http://images.cnblogs.com/cnblogs_com/kuikui/354173/r_test1.jpg" />
            <select><option>one</option><option>two</option></select>
            弹出框插件测试。。。
        </div>
        <div>
            点击测试：
            <input class="btn" id="btndef" type="button" value="default" />
            <input class="btn" id="btnconf" type="button" value="confirm" />
            <input class="btn" id="btnal" type="button" value="alert" />
            <p>使用conform、alert必须制定其type类型，type默认值是default。</p>
        </div>
    </div>
    
    <script id="arrtmpl" type="text/x-dot-template">
        <div class="mtable">
            <table >
                <thead>
                    <tr>
                        <th>第一项</th>
                        <th>第二项</th>
                        <th>第三项</th>
                    </tr>
                </thead>
                <tbody>
                    {{~it.array:value:index }}
                    <tr>
                        <td>{{= value.one }}</td><td>{{= value.two }}</td><td>{{= value.three }}</td>
                    </tr>
                    {{~}}
                </tbody>
            </table>
        </div>
    </script>

    <script id="dialogtmpl" type="text/x-dot-template">
        {{? it.isIE6===false }} 
        <div id="dialogbg" class="dialogbg"></div>
        {{??  }}
        <iframe id="dialogbg" class="dialogbg"></iframe>
        {{?}}
        <div id="dialogbox" class="dialogbox">
            <div class="dialogcont">
                <div class="dialogtit clearfix">
                    <a class="dialogclosed" title="关闭">⊗</a>
                    <div class="dialogtxt">{{=it.title}}</div>
                </div>
                <div class="dialogmain">{{=it.html}}</div>
                {{? it.type === "conform"}}
                <div class="dialogBtns"><a class="dialogconform">{{=it.conform.name||"确定"}}</a></div>
                {{?? it.type === "alert"}}
                <div class="dialogBtns"><a class="dialogconform">{{=it.conform.name||"确定"}}</a><a class="dialogcancel">{{=it.cancel.name||"取消"}}</a></div>
                {{?}} 
            </div>
        </div>
    </script>

    <script>
        (function($) {
            $.Dialog = {
                init: function(options) {
                    var _this = this,
                        defaults = {
                            width: "400",
                            height: "100",
                            title: "",
                            html: "",
                            type: "default", //  default   conform   alert
                            closed: null,
                            conform: {
                                name: "确定",
                                callback: null
                            },
                            cancel: {
                                name: "取消",
                                callback: null
                            }
                        };
                    options.isIE6 = !! window.ActiveXObject && !window.XMLHttpRequest;
                    _this.options = $.extend({}, defaults, options);
                    _this.creatHtml();
                    _this.show();
                    _this.events();
                    if(_this.options.isIE6){
                       _this.ie6fixed();
                    }
                },
                creatHtml: function() {
                    var _this = this;
                    var interText = doT.template($("#dialogtmpl")[0].text);
                    $("body").append(interText(_this.options));
                },
                show: function() {
                    var _this = this;
                    _this.dialogbg = $("#dialogbg");
                    _this.dialogbox = $("#dialogbox");

                    _this.dialogbg.css({
                        "height": $(document).height()
                    });

                    _this.dialogbox.css({
                        "margin-top": (-_this.options.height / 2) + "px",
                        "margin-left": (-_this.options.width / 2) + "px",
                        "width": _this.options.width + "px",
                        "height": _this.options.height + "px"
                    });

                    _this.dialogbox.addClass("dialogboxrunstart");
                },
                events: function() {
                    var _this = this;
                    _this.dialogclosed = _this.dialogbox.find(".dialogclosed");
                    _this.dialogconform = _this.dialogbox.find(".dialogconform");
                    _this.dialogcancel = _this.dialogbox.find(".dialogcancel");

                    _this.dialogclosed.on("click", function() {
                        _this.removeCallback(_this.options.closed);
                    });

                    _this.dialogconform.on("click", function() {
                        _this.removeCallback(_this.options.conform.callback);
                    });

                    _this.dialogcancel.on("click", function() {
                        _this.removeCallback(_this.options.cancel.callback);
                    });
                },
                removeCallback: function(call) {
                    var _this = this;
                    _this.dialogbg.remove();
                    _this.dialogbox.remove(); !! call && call();
                },
                ie6fixed: function() {
                    var _this = this;
                    $(window).scroll(function() {
                        _this.dialogbox.css({
                            "top": (($(window).height() - _this.options.height) / 2 + $(document).scrollTop()) + "px"
                        });
                    });
                }
            }
        })(jQuery);
    </script>

    <script>
        (function($) {
            $("#btndef").click(function() {
                $.Dialog.init({
                    height: 180,
                    title: "弹出框",
                    html: "亲，默认弹出框哦",
                    closed: function() {
                        alert("关闭");
                    }
                });
            });


            $("#btnconf").click(function() {
                $.Dialog.init({
                    height: 100,
                    html: "",
                    type: "conform",
                    conform: {
                        name: "确认按钮哦哦哦哦"
                    }
                });
            });

            $("#btnal").click(function() {
                var arr = {
                    "array": [{
                        "one": "1",
                        "two": "2",
                        "three": "3"
                    }, {
                        "one": "11",
                        "two": "22",
                        "three": "33"
                    }, {
                        "one": "111",
                        "two": "222",
                        "three": "333"
                    }, {
                        "one": "1111",
                        "two": "2222",
                        "three": "3333"
                    }, {
                        "one": "11111",
                        "two": "22222",
                        "three": "33333"
                    }, {
                        "one": "111111",
                        "two": "222222",
                        "three": "333333"
                    }]
                };
                var interText = doT.template($("#arrtmpl")[0].text);
                var html = interText(arr);
                $.Dialog.init({
                    height: 190,
                    html: html,
                    type: "alert",
                    conform: {
                        callback: function() {
                            alert("确定");
                        }
                    },
                    cancel: {
                        callback: function() {
                            alert("取消");
                        }
                    }
                });
            });

        })(jQuery);
    </script>
</body>
</html>