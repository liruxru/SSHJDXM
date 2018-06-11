
	
    
(function () {
    function ea() {
        this.init()
    }
    ea.prototype = {
        init: function () {
            //判断是否需要追踪
            g.init();
        },
        run: function () {
            g.track();
        },
    };

    var g = {
        xmlHttp: '',
        sourcecode: '',
        isgoTrack: false,
        init: function () {
            sourcecode = this.getQueryString("sourcecode");
            if (sourcecode.length > 0) {
                //判断是否已经存在相同sourcecode的cookie 防止把刷新认为是一次点击
                var localSourceCode = this.getCookie("998comsourcecode");
                if (localSourceCode.length == 0) {
                    this.isgoTrack = true;
                } else if (sourcecode != localSourceCode) {
                    this.isgoTrack = true;
                }
            }
        },
        /*获取url参数 */
        getQueryString: function (name) {
            if (window.location.href.indexOf("i.998.com") > -1 && window.location.href.indexOf("sourcecode") > 0) {
                var locationSearch = window.location.href.split("?")[1];
                var reg_I = new RegExp(name + "=([^&]*)(&|$)");
                var r_I = locationSearch.match(reg_I);
                if (r_I != null) return unescape(r_I[1]);
                return '';
            }
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return '';
        },
        /*设置cookieId   单位：分钟*/
        setCookie: function (c_name, value, expiredays) {
            var exdate = new Date();
            //exdate.setDate(exdate.getDate() + expiredays);
            //exdate.setDate(exdate.getDate() + expiredays);
            exdate.setTime(exdate.setMinutes(exdate.getMinutes() + expiredays));
            document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString()) + ";path=/;domain=998.com";
        },

        /*获取cookieId */
        getCookie: function (c_name) {
            if (document.cookie.length > 0) {
                c_start = document.cookie.indexOf(c_name + "=");
                if (c_start != -1) {
                    c_start = c_start + c_name.length + 1;
                    c_end = document.cookie.indexOf(";", c_start);
                    if (c_end == -1) {
                        c_end = document.cookie.length;
                    }
                    return unescape(document.cookie.substring(c_start, c_end));
                }
            }
            return "";
        },

        /* 获取当前时间戳 */
        getTimestamp: function () {
            var timestamp = Date.parse(new Date());
            return timestamp;
        },

        /* 构造XMLHttpRequest对象  */
        createXMLHttpRequest: function () {
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
            } else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            }
        },

        track: function () {
            //有sourcecode统计的才进入统计逻辑
            if (sourcecode.length > 0) {
                if (this.isgoTrack) {
                    var p;
                    var vlstatURL = encodeURIComponent(document.URL);
                    var vlstatAction = "https://www.998.com/Base/Track";
                    p = "url=" + vlstatURL + "&sourcecode=" + sourcecode;
                    var urlGo = vlstatAction + "?" + p;
                    this.createXMLHttpRequest();
                    xmlHttp.open('GET', urlGo);
                    xmlHttp.send(null);
                }
                this.setCookie("998comsourcecode", sourcecode, 5); //sourcecode的cookie追踪有效期为5分钟
            }
        }

    };

    ea && (new ea).run();
})();