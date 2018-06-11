/**
 *
 */
var suggestCityMap = {};

var overseasCityMap = {};
var popCityMap = {};
var suggestKeyWordMap = {};
var popKeyWordMap = {};
//var globalCityId = "226";
//var globalCityText = "上海";
var defaultLat = 31.23333;
var defaultLng = 121.4833;


//关键字文本框的值
var hodelKeyWordValue = "";
var hodelKeyWordText = "";

var numberMap = {
    '1': 'A',
    '2': 'B',
    '3': 'C',
    '4': 'D',
    '5': 'E',
    '6': 'F',
    '7': 'G',
    '8': 'H',
    '9': 'I',
    '10': 'J'
};

//31.23333,121.4833SendSms

/**
 * 扩展Array，增加contain方法，该方法用于判断对象element是否在数组内
 */
Array.prototype.contain = function (element) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == element) {
            return true;
        }
    }
    return false;
}

/**
 * 扩展String，增加replaceAll方法
 */
String.prototype.replaceAll = function (s1, s2) {
    return this.replace(new RegExp(s1, "gm"), s2);
}
//判断是否为空,为空则返回true
String.prototype.isNull = function () {
    if (this.replace(/(^\s*)|(\s*$)/g, '').length <= 0) {//为空
        return true;
    } else {//不为空
        return false;
    }
}

//判断数字,只能为整数
String.prototype.isNumber = function () {
    if (!this.isNull()) {
        for (i = 0; i < this.length; i++) {
            if (this.charAt(i) < "0" || this.charAt(i) > "9") {
                return false;
            }
        }
        return true;
    } else {
        return false;
    }
}
//判断数字,可以为小数
String.prototype.isDouble = function () {
    var pointCount = 0;
    for (var i = 0; i < this.length; i++) {
        if ((this.charAt(i) < '0' || this.charAt(i) > '9') && this.charAt(i) != '.') {
            return false;
        }
        else {
            if (this.charAt(i) == '.') pointCount++;
        }
    }
    if (pointCount > 1) {
        return false;
    } else if (pointCount == 1 && $.trim(this).length == 1) {
        return false;
    }
    return true;
}
//判断邮政编码格式
String.prototype.isZip = function () {
    if (!this.isNull()) {
        if (this.length != 6) {
            return false;
        }
        else {
            var rexTel = /^[0-9]+$/;
            if (!rexTel.test(this)) {
                return false;
            }
        }
    } else {
        return false;
    }
}

//判断联系电话、传真格式，格式正确返回true
String.prototype.isPhone = function () {
    if (!this.isNull()) {
        var reg = /(^[0-9]{3,4}\-[0-9]{7,8}\-[0-9]{3,4}$)|(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}\-[0-9]{3,4}$)|(^[0-9]{7,15}$)/;
        if (!reg.test(this)) {
            return false;
        }
        return true;
    }
    else {
        return false;
    }
}

//判断是否是手机号码，格式正确返回true
String.prototype.isMobile = function () {
    if (!this.isNull()) {
        var reg = /^1(\d{10})$/;
        if (!reg.test(this)) {
            return false;
        }
        return true;
    }
    else {
        return false;
    }
}


Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份   
        "d+": this.getDate(), // 日   
        "h+": this.getHours(), // 小时   
        "m+": this.getMinutes(), // 分   
        "s+": this.getSeconds(), // 秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度   
        "S": this.getMilliseconds() // 毫秒 
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/////////////////////////////////////////////////////////////////////////////////////////
//
///四舍六入
//
//////////////////////////////////////////////////////////////////////////////////////////
Math.roundEx = function (orignNum) {


    if (orignNum.toString() == "NaN" || orignNum.toString() == "Infinity" || orignNum.toString() == "0") {
        return 0;
    }
    var n = 3;

    var srcNum = parseInt(orignNum) + "";
    n = srcNum.length;

    var returnNum;  //返回值
    var loc = parseInt(n) + 1;//位置，多次用到
    //用小数点拆分数字;修约分小数点前部分大于0和小数点前数字小于0两种情况
    var str;
    str = orignNum.toString().split(".");
    if (str.length < 1 || str.length > 2) //数字格式不正确 
    {
        return 0;
    }
    else if (str.length == 1) {
        str[1] = "";
    }
    //合并数字
    var uniteNum;
    if (parseInt(str[0]) == 0) {
        uniteNum = str[1];
    }
    else {
        uniteNum = str[0].concat(str[1]);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    //小数点前部分大于0
    ////////////////////////////////////////////////////////////////////////////////////////////
    if (parseInt(str[0]) != 0) {
        var lenofnum = uniteNum.length;
        if (lenofnum <= n) {
            var shorter = n - lenofnum;
            var append = '';
            for (i = 0; i < shorter; i++) {
                append += '0';
            }
            returnNum = str[0] + '.' + str[1] + append;
        }
        //取舍的数字和舍弃的数字
        var mender = uniteNum.charAt(n);
        var leaver = uniteNum.slice(loc);
        //拟舍弃数字的最左一位数字大于5或者小于5
        if (parseInt(mender) > 5 || parseInt(mender) < 5) {
            returnNum = (parseFloat(orignNum)).toPrecision(n);
        }
            //拟舍弃数字的最左一位数字为5
        else if (parseInt(leaver) > 0) {
            returnNum = (parseFloat(orignNum)).toPrecision(n);
        }
            //拟舍弃数字的最左一位数字为5,5右面无数字
        else {
            var preNum = parseInt(uniteNum.charAt(n - 1));
            //末位数字为奇数
            if (preNum % 2 == 1) {
                returnNum = (parseFloat(orignNum)).toPrecision(n);
            }
                //末位数字为偶数,先修改原始数据，把末位数后的5去掉 
            else if (preNum % 2 == 0) {
                var len = str[0].length;
                //有效位数n小于等于原始数据中整数部分的长度，修正原始数据eg:1245354,有效数字为3位，凑个5000，1245354-5000=1240000来参与修约
                if (n < len) {
                    var subtract = "5";
                    for (i = 1; i < len - n; i++) {
                        subtract += "0";
                    }
                    orignNum = orignNum - parseInt(subtract);
                    returnNum = (parseFloat(orignNum)).toPrecision(n);
                }
                    //有效位数n大于原始数据中整数部分的长度，直接取前n+1位数字,包括小数点
                else {
                    orignNum = parseFloat(orignNum.toString().slice(0, loc));
                    returnNum = (parseFloat(orignNum)).toPrecision(n);
                }
            }
        }
    }
        ////////////////////////////////////////////////////////////////////////////////////////////
        //小数点前部分=0
        ////////////////////////////////////////////////////////////////////////////////////////////
    else {
        var lenstr1 = str[1].length;
        var valdata = 0;
        var last0 = 0;
        for (i = 0; i < lenstr1; i++) {
            var strtemp = str[1].charAt(i);
            if (strtemp != '0') {
                var valdata = str[1].substr(i);
                var last0 = i + 1;
                break;
            }
        }
        var lenofnum = valdata.length;
        if (valdata.length <= n) {
            var shorter = n - lenofnum;
            var append = '';
            for (i = 0; i < shorter; i++) {
                append += '0';
            }
            returnNum = str[0] + '.' + str[1] + append;
        }
        var mender = valdata.charAt(n);
        var leaver = valdata.slice(loc);
        //拟舍弃数字的最左一位数字大于5或者小于5
        if (parseInt(mender) > 5 || parseInt(mender) < 5) {
            returnNum = (parseFloat(orignNum)).toPrecision(n);
        }
            //拟舍弃数字的最左一位数字为5
        else if (parseInt(leaver) > 0) {
            returnNum = (parseFloat(orignNum)).toPrecision(n);
        }
            //5右面无数字或者皆为0
        else {
            var preNum = parseInt(valdata.charAt(n - 1));
            //5右面无数字或者皆为0或末位数字为奇数
            if (preNum % 2 == 1) {
                returnNum = (parseFloat(orignNum)).toPrecision(n);
            }
                //末位数字为偶数,先修改原始数据，*****把末位数后的5去掉***** 
            else if (preNum % 2 == 0) {
                var subtract = "0.";
                for (i = 0; i < last0; i++) {
                    subtract += "0";
                }
                var len = valdata.length;
                if (n < len) {
                    for (i = 0; i < n; i++) {
                        subtract += "0";
                    }
                    subtract += "5";
                    orignNum = orignNum - parseFloat(subtract);
                    returnNum = (parseFloat(orignNum)).toPrecision(n);
                }
            }
        }
    }
    return parseFloat(returnNum);
}

//判断电子邮箱格式，格式正确返回true
String.prototype.isEmail = function () {
    if (!this.isNull()) {
        if (this.search(/^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/) != -1) {
            return true;
        }
        else {
            return false;
        }
    }
    else {
        return false;
    }
}

Function.prototype.method = function (name, func) {
    this.prototype[name] = func;
    return this;
};





if (!String.prototype.trim) { //判断下浏览器是否自带有trim()方法
    String.method('trim', function () {
        return this.replace(/^\s+|\s+$/g, '');
    });
    String.method('ltrim', function () {
        return this.replace(/^\s+/g, '');
    });
    String.method('rtrim', function () {
        return this.replace(/\s+$/g, '');
    });
}

if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (elt /*, from*/) {
        var len = this.length >>> 0;
        var from = Number(arguments[1]) || 0;
        from = (from < 0) ? Math.ceil(from) : Math.floor(from);
        if (from < 0) from += len;

        for (; from < len; from++) {
            if (from in this && this[from] === elt) return from;
        }
        return -1;
    };
}

jQuery.navlevel2 = function (level1, dytime) {
    var delytime;
    $(level1).mouseenter(function () {
        varthis = $(this);
        delytime = setTimeout(function () {
            //  varthis.find('ul').slideDown();
            varthis.find('ul').show();
        },
        dytime);

    });
    $(level1).mouseleave(function () {
        clearTimeout(delytime);
        // $(this).find('ul').slideUp();
        $(this).find('ul').hide();

    });

};
/*首页下拉控制*/
jQuery.navlevel3 = function (level1, dytime) {
    var delytime;
    $(level1).mouseenter(function () {
        varthis = $(this);
        delytime = setTimeout(function () {
            //  varthis.find('ul').slideDown();
            varthis.find('.divDroplist').show();
        },
            dytime);

    });
    $(level1).mouseleave(function () {
        clearTimeout(delytime);
        // $(this).find('ul').slideUp();
        $(this).find('.divDroplist').hide();

    });

};


(function ($) {
    $.getHotelActivityCode = function () {//返回酒店998活动编号值
        return "C0000001";
    }
    $.ShellHotelActivityCode = function () {//返回酒店69活动编号值
        return "C0000045";
    }


    $.getUrlParam = function (name) {//获取URL的参数
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }

    $.fn.Logger = function (info) {//Logger

        try {
            if (console) {
                //console.log(info);
            }
        } catch (e) {

        }

    }

    $.fn.isIE6 = function () {
        var isIe6 = false;
        if (/msie/.test(navigator.userAgent.toLowerCase())) {
            if (typeof jQuery.browser !== 'undefined' && jQuery.browser && jQuery.browser.version && jQuery.browser.version == '6.0') {
                isIe6 = true
            } else if (!$.support.leadingWhitespace) {
                isIe6 = true;
            }
        }
        return isIe6;
    }

    //收藏酒店
    $.fn.AddMyHotel = function (o, hotelCode) {
        $.ajax({
            type: "POST",
            url: '/UserCenter/AddMyHotel',
            data: {
                hotelCode: hotelCode
            },
            beforeSend: function () {
            },
            success: function (data) {
                var Successed = data.Successed;
                var IsLogin = data.IsLogin;
                var Msg = data.Msg;
                if (Successed || (!Successed && IsLogin)) {
                    $.fn.showAlert(Msg);
                } else {
                    //AfterLogin('1', '0');
                    $.fn.ShowPopSign('', 0);
                }
            },
            complete: function () {
            }
        });
    }

    //弹出登陆框
    $.fn.ShowPopSign = function (backurl, tag, postData) {
        if (typeof backurl === 'undefined' || !backurl || backurl === '') {
            backurl = window.location.href;
        }

        $('.payment-overlay').css('display', '');
        $('#loginSigninWindow').css("display", "block");
        //可以直接预定
        if (tag == 1) {
            $('#directBooking').css("display", "");
            $("#directbookingP").removeAttr("hidden");
        }
        $('#backUrl').val(backurl);

        $("#signBtn").click(function () {
            checkLogin(backurl, postData);
        });

        var oauhPostData = {};
        if (typeof postData !== 'undefined' && postData != "" && postData != null) {
            oauhPostData.hotelCode = postData.hotelCode;
            oauhPostData.roomType = postData.roomType;
            oauhPostData.isSp = postData.isSP;
            oauhPostData.hotelStartDate = postData.startDate;
            oauhPostData.hotelEndDat = postData.endDate;
            oauhPostData.activityCode = postData.activityCode;
            oauhPostData.totaldays = postData.totaldays;
        }

        //设置合作商户网站入口
        $.ajax({
            type: "POST",
            url: '/Account/GetOAuth2UIForBackUrl',
            data: {
                backUrl: backurl,
                models: JSON.stringify(oauhPostData)
            },
            beforeSend: function () {
            },
            success: function (data) {
                var Successed = data.Successed;
                if (Successed) {
                    $("#oauthUiList").empty();//ADD:防止多次开关造成合作商户网站入口重复添加；清空。
                    //console.log(data.oauthUI);
                    $("#oauthUiList").append(data.oauthUI);
                }
            }
        });
        //alert("backUrl:"+$("#backUrl").val());
        //window.location.reload();
        //    ___title: "登录注册",
        //    ___content: 'iframe:/Account/SignPopup?backurl=' + encodeURIComponent(backurl) + '&tag=' + tag,
        //    ___width: "460",
        //    ___height: "400",
        //    ___showbg: true

        //});
        //快捷登录
        $("#btnQuickSign").click(function () {
            if ($("#QLphoneNo").val() == '') {
                $.fn.showAlert($.i18n.prop('label.register.error.QcLoginNumber'));
                return false;
            }
            var qyzm_lay = $.trim($("#qyzm_lay").val());
            if (qyzm_lay == "" || qyzm_lay == null)
            {
                $.fn.showAlert($.i18n.prop('label.register.error.QcLoginNumberCode'));
                $("#qyzm_lay").focus();
                return false;
            }
            if ($("#QLauthCode").val() == '') {
                $.fn.showAlert($.i18n.prop('label.register.error.QcLoginNumberCode'));
                return false;
            }

            $.ajax({
                type: "POST",
                url: '/Account/GOQuickLogin',
                data: {
                    phoneNo: $.trim($("#QLphoneNo").val()),code:qyzm_lay,authCode: $.trim($("#QLauthCode").val())
                },
                success: function (data) {
                    if (data.Successed) {              //登陆成功跳转到不同地方
                        if (typeof postData === '' || !postData) {
                            if (parent.document) {
                                parent.document.location.href = backurl;
                            } else {
                                document.location.href = backurl;
                            }
                        } else {
                            var form = $('<form action="' + backurl + '" method="POST"></form>');
                            for (var key in postData) {
                                $.fn.Logger('Key:' + key + ',Value:' + postData[key]);
                                form.append('<input type="hidden" id="' + key + '" name="' + key + '" value="' + postData[key] + '" />');
                            }
                            form.appendTo('body').submit().remove();
                        }
                    }
                    else {
                        $.fn.showAlert(data.msg);
                    }
                }
            });
        });
    }

    


    //弹出图片墙
    $.fn.ShowHotelGallery = function (hotelCode, picId) {
        //$.XYTipsWindow({
        //    ___title: "酒店图片",
        //    ___content: 'iframe:/HotelDetail/HotelGallery?hotelCode=' + hotelCode,
        //    ___width: "840",
        //    ___height: "470",
        //    ___showbg: true

        //});

        $('.pic-overlay').css("display", "block");
        $('#hotelPicWindow').css("display", "block");
    }

    //弹出酒店地图周边窗口-修改-2014-2-21
    $.fn.ShowMapCircum = function (cityId, hotelCode, hotelName, pointX, pointY, b_pointXY) {
        if (typeof hotelCode === 'undefined' || !hotelCode || hotelCode == null || hotelCode == '' ||
            typeof pointX === 'undefined' || !pointX || pointX == null || pointX == '' ||
            typeof pointY === 'undefined' || !pointY || pointY == null || pointY == '') {
            $.fn.showAlert($.i18n.prop('label.common.hotel.infomissing'));
            return;
        }

        $.XYTipsWindow({
            ___title: $.i18n.prop('title.hotel.circum'),
            ___content: 'iframe:/HotelMap/HotelCircum?cityId=' + cityId + '&hotelcode=' + hotelCode + '&hotelName=' + encodeURIComponent(hotelName) + '&pointX=' + pointX + '&pointY=' + pointY + '&b_pointXY=' + b_pointXY,
            ___width: "920",
            ___height: "630",
            ___showbg: true

        });

        /**
        $('.map-overlay').css("display", "block");
        $('#hotelMapCircum').css("display", "block");
        */
    }

    //弹出酒店地图窗口-修改2014-4-21
    $.fn.ShowHotelMapIframe = function (languageType, hotelIndex, hotelCode, hotelName, pointX, pointY) {
        if (!pointX || !pointY || pointX == '' || pointY == '') {
            return;
        }
        $.XYTipsWindow({
            ___title: $.i18n.prop('title.hotel.map'),
            ___content: 'iframe:/HotelList/HotelMapView?hotelCode=' + hotelCode + '&hotelIndex=' + hotelIndex + '&hotelName=' + hotelName + '&pointX=' + pointX + '&pointY=' + pointY,
            ___width: "800",
            ___height: "480",
            ___showbg: true
        });
    }

    $.fn.ShowHotelMap = function (languageType, hotelIndex, hotelCode, hotelName, pointX, pointY) {
        var overlay = $('#hotelMapWindow');
        if (!overlay || !overlay[0]) {
            overlay = $('<div id="hotelMapWindow" style="position:absolute;left:220px;top:200px;z-index:99999;display:none;"></div>');
            overlay.appendTo($('body'));
        }

        var content = $('#hotelMapDialog_' + hotelCode);
        if (!content || !content[0]) {
            var content = $('<div class="up-box w600" id="hotelMapDialog_' + hotelCode + '" style="z-index: 200000000;position:fixed; left: 230px; top: 60px;background-color: #FFFFFF; width: 800px;border: 8px solid #EEEEEE;display:none"></div>');
            content.append('<div class="up-box-hd" style=" background:#008857; border-radius:inherit; border-bottom:1px solid #ddd; color:#fff; font-family:\"微软雅黑\";">' + $.i18n.prop('label.common.hotel.showmap') + '<a id="closeMapDialogW_' + hotelCode + '" style="float:right;cursor:pointer;font-size:24px;color:#FFF">x</a></div>');
            content.append('<div id="J_mapBox" style="position: relative;"><div id="mapContent_' + hotelCode + '" style="width: 800px; height: 480px;"></div></div>');

            content.appendTo($('body'));

            var map = $.fn.createMap('mapContent_' + hotelCode, languageType, 10, null);

            $.fn.addHotelOverlay(map, languageType, hotelIndex, hotelName, pointX, pointY);

            content.find('#closeMapDialogW_' + hotelCode).bind('click', function () {
                overlay.hide();
                content.hide();
            });
        }

        overlay.show();
        content.show();


        ////关闭弹出的地图查看
        //$('#closeMapDialogW').click(function () {
        //    $('.map-overlay').css('display', 'none');
        //    $('#hotelMapDialog').css("display", "none");
        //});

    }

    //显示支付方式窗口
    //$.fn.ShowPayWindow = function(orderCode,hotelName,hotelRoomType,totalMoney,payHtml){
    //    $.XYTipsWindow({
    //        ___title: "选择支付方式",
    //        ___content: 'iframe:/HotelOrder/HotelPay?orderCode=' + orderCode + '&hotelName=' + hotelName + '&hotelRoomType=' + hotelRoomType + '&totalMoney=' + totalMoney + '&payHtml=' + payHtml,
    //        ___width: "700",
    //        ___height: "600",
    //        ___showbg: true
    //    });
    //}

    //支付问题
    //$.fn.ShowPaySomething = function (orderCode) {
    //    $.XYTipsWindow({
    //        ___title: "网上支付提示",
    //        ___content: 'iframe:/HotelOrder/HotelPay?orderCode=' + orderCode,
    //        ___width: "300",
    //        ___height: "200",
    //        ___showbg: true
    //    });
    //}


    //订单成功发送短信
    $.fn.SendSms = function (orderCode) {
        $.XYTipsWindow({
            ___title: $.i18n.prop('label.common.sendmsg'),
            ___content: 'iframe:/HotelOrder/SendSms?orderCode=' + orderCode,
            ___width: "400",
            ___height: "200",
            ___showbg: true
        });
    }

    //弹出酒店360全景地图窗口-heying 2014-11-24
    $.fn.ShowHotel360MapIframe = function (hotel360UrlAll) {
        if (!hotel360UrlAll || hotel360UrlAll == '') {
            return;
        }
        $.XYTipsWindow({
            ___title: $.i18n.prop('title.hotel.map360'),
            ___content: 'iframe:' + hotel360UrlAll,
            ___width: "800",
            ___height: "480",
            ___showbg: true
        });
    }
    //弹出酒店宣传片窗口-heying 2014-11-28
    $.fn.ShowHotelVideoIframe = function (VideoUrl) {
        if (!VideoUrl || VideoUrl == '') {
            return;
        }
        $.XYTipsWindow({
            ___title: $.i18n.prop('title.hotel.Video'),
            ___content: 'iframe:' + VideoUrl,
            ___width: "800",
            ___height: "480",
            ___showbg: true
        });
    }


})(jQuery);

//列表预订地图页面展示
(function ($) {

    //创建地图控件
    //如果languageType为1则表示是中文，用BaiDu Map，否则用Google Map
    //data是单个酒店的数据
    $.fn.createMap = function (mapId, languageType, zoom, data) {
        try {
            var latitude;
            var longitude;
            if (languageType != 'CHS') {
                if (data && data.GmapPoint && data.GmapPoint.split(',').length == 2) {
                    latitude = Number(data.GmapPoint.split(',')[1]);
                    longitude = Number(data.GmapPoint.split(',')[0]);
                } else {
                    latitude = defaultLat;
                    longitude = defaultLng;
                }
                google.maps.Map.prototype.overlays = new Array();

                google.maps.Map.prototype.addOverlay = function (overlay) {
                    this.overlays[this.overlays.length] = overlay;
                };

                google.maps.Map.prototype.getOverlays = function () {
                    return this.overlays
                };

                google.maps.Map.prototype.clearOverlays = function () {
                    for (var i = 0; i < this.overlays.length; i++) {
                        this.overlays[i].setMap(null);
                    }
                    this.overlays = new Array();
                };

                var map = new google.maps.Map(document.getElementById(mapId), {
                    center: new google.maps.LatLng(latitude, longitude),
                    zoom: 10,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                })

                return map;
            } else {
                if (data && data.BmapPoint && data.BmapPoint.split(',').length == 2) {
                    latitude = Number(data.BmapPoint.split(',')[1]);
                    longitude = Number(data.BmapPoint.split(',')[0]);
                } else {
                    latitude = defaultLat;
                    longitude = defaultLng;
                }

                var map = new BMap.Map(mapId, {
                    enableMapClick: false
                });
                map.centerAndZoom(new BMap.Point(latitude, longitude), 15);

                map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
                map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
                //向地图中添加比例尺控件
                //map.addControl(new BMap.ScaleControl({ anchor: BMAP_ANCHOR_BOTTOM_LEFT }));
                //向地图中添加缩略图控件
                //map.addControl(new BMap.OverviewMapControl({ anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: 1 }));
                //向地图中添加缩放控件
                //map.addControl(new BMap.NavigationControl({ anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_LARGE }));
                return map;
            }
        } catch (e) {
            return null;
        }
    }

    //如果是Hotel对象，则取point
    $.fn.centerAndZoom = function (map, languageType, data, zoom) {
        if (map == null)
            return;

        if (!zoom) {
            zoom = 15;
        }

        if (languageType != 'CHS') {
            var pointXY = data.GmapPoint;
            var latitude = Number(pointXY.split(',')[0]);
            var longitude = Number(pointXY.split(',')[1]);
            var bounds = new google.maps.LatLngBounds();
            bounds.extend(new google.maps.LatLng(latitude, longitude));
            map.setCenter(bounds.getCenter());
            map.fitBounds(bounds);
        } else {

            var pointXY = data.BmapPoint;
            var latitude = Number(pointXY.split(',')[1]);
            var longitude = Number(pointXY.split(',')[0]);
            var point = new BMap.Point(latitude, longitude);
            map.centerAndZoom(point, zoom);
        }
    }

    //添加一组酒店覆盖物标注
    //应用场景：地图预订的地图列表
    //map:地图实例
    //languageType：中英文类型
    //list:酒店列表
    $.fn.addHotelOverlays = function (map, languageType, list, isShowDetail) {
        if (map == null)
            return;
        var overlays = [];
        $.fn.clearOverlays(map, languageType);
        function getHotelInnerHTML(map, hotel) {
            var div = $('<div class="amap-marker" style="position: absolute;opacity: 1; cursor: pointer; transform: translate(69px, 114px);"></div>');
            var d = $('<div class="map_mark"></div>');
            d.append('<span class="map_num">' + hotel.Index + '</span>');
            d.append('<div class="map_mark_inner"><span class="map_mark_name" style="white-space: nowrap;">' + hotel.HotelName + '</span></div>');
            div.append(d);

            return div;
        }

        if (languageType != 'CHS') {

            function GHotelOverlay(map, options) {
                this.latlng = options.latlng; //设置图标的位置  
                this.hotel = options.hotel;
                this.labelClass = options.labelClass || 'shadow';//设置文字的样式  
                this.click = options.click;//注册点击事件  
                this._map = map;

                this._div = null;
                this.setMap(map);
            }
            GHotelOverlay.prototype = new google.maps.OverlayView();

            //初始化图标  
            GHotelOverlay.prototype.onAdd = function () {
                var div = document.createElement('DIV'); //创建存放图片和文字的div  
                div.style.border = "none";
                div.style.borderWidth = "0px";
                div.style.position = "absolute";
                div.style.cursor = "hand";
                div.onclick = this.click || function () { };//注册click事件，没有定义就为空函数

                var d = getHotelInnerHTML(this._map, this.hotel);

                div.appendChild(d[0]);

                this._div = div;
                var panes = this.getPanes();
                //panes.overlayLayer.appendChild(div);
                panes.overlayMouseTarget.appendChild(div);
                google.maps.event.addDomListener(div, 'click', function () {
                });
            }

            //绘制图标，主要用于控制图标的位置  
            GHotelOverlay.prototype.draw = function () {
                var overlayProjection = this.getProjection();
                var position = overlayProjection.fromLatLngToDivPixel(this.latlng);   //将地理坐标转换成屏幕坐标    
                var div = this._div;
                div.style.left = position.x - 5 + 'px';
                div.style.top = position.y - 5 + 'px';
                //控制图标的大小  
                div.style.width = '10px';
                div.style.height = '10px';
            }

            GHotelOverlay.prototype.onRemove = function () {
                this._div.parentNode.removeChild(this._div);
                this._div = null;
            }

            GHotelOverlay.prototype.hide = function () {
                if (this._div) {
                    this._div.style.visibility = "hidden";
                }
            }

            GHotelOverlay.prototype.show = function () {
                if (this._div) {
                    this._div.style.visibility = "visible";
                }
            }

            GHotelOverlay.prototype.toggle = function () {
                if (this._div) {
                    if (this._div.style.visibility == "hidden") {
                        this.show();
                    } else {
                        this.hide();
                    }
                }
            }

            GHotelOverlay.prototype.getHotel = function () {
                return this.hotel;
            }

            GHotelOverlay.prototype.showHotelInfoWin = function (list) {
                for (var j = 0; j < list.length; j++) {
                    var _d = list[j];
                    if (typeof _d.InfoWindow !== 'undefined' && _d.InfoWindow) {
                        _d.InfoWindow.close();
                    }
                }
                if (this.hotel && typeof this.hotel.InfoWindow !== 'undefined' && this.hotel.InfoWindow) {
                    this.hotel.InfoWindow.open(this._map);
                }
            }

            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                var pointXY = _data.GmapPoint;
                if (pointXY != null) {
                    var latitude = Number(pointXY.split(',')[0]);
                    var longitude = Number(pointXY.split(',')[1]);

                    var latlng = new google.maps.LatLng(latitude, longitude);

                    if (latlng && typeof isShowDetail != 'undefined' && isShowDetail != '' && isShowDetail == true) {
                        var content = $('<div class="map_tc" style="display:block"></div>');
                        $("#RoomTypeMapListTemplate").tmpl(list[i], {}).appendTo(content);

                        var infoWindow = new google.maps.InfoWindow({
                            content: content[0],
                            size: new google.maps.Size(50, 50),
                            position: latlng
                        });
                        _data.InfoWindow = infoWindow;
                        //infoWindow.open(map);
                    }

                    var overlay = new GHotelOverlay(map, {
                        latlng: new google.maps.LatLng(latitude, longitude),
                        hotel: _data,
                        click: function () {
                            if (typeof isShowDetail != 'undefined' && isShowDetail != '' && isShowDetail == true) {
                                var index = Number($(this).find('.map_num')[0].innerHTML) - 1;
                                for (var j = 0; j < list.length; j++) {
                                    var _d = list[j];
                                    if (typeof _d.InfoWindow !== 'undefined' && _d.InfoWindow) {
                                        _d.InfoWindow.close();
                                    }
                                }
                                if (list[index] && typeof list[index].InfoWindow !== 'undefined' && list[index].InfoWindow) {
                                    list[index].InfoWindow.open(map);
                                }
                            }
                        }
                    });
                    _data.HotelOverlay = overlay;
                    map.addOverlay(overlay);
                    overlays.push(overlay);
                }
            }
        } else {
            function HotelOverlay(point, hotel) {
                this._point = point;
                this._hotel = hotel;
            }

            HotelOverlay.prototype = new BMap.Overlay();

            HotelOverlay.prototype.initialize = function (map) {
                this._map = map;
                var div = getHotelInnerHTML(map, this._hotel);

                this._div = div[0];
                this._map.getPanes().labelPane.appendChild(this._div);

                return this._div;
            }

            HotelOverlay.prototype.draw = function () {
                var map = this._map;
                var pixel = map.pointToOverlayPixel(this._point);
                this._div.style.left = pixel.x + "px";
                this._div.style.top = pixel.y - 30 + "px";
            }

            HotelOverlay.prototype.hide = function () {
                if (this._div) {
                    this._div.style.visibility = "hidden";
                }
            }

            HotelOverlay.prototype.show = function () {
                if (this._div) {
                    this._div.style.visibility = "visible";
                }
            }

            HotelOverlay.prototype.toggle = function () {
                if (this._div) {
                    if (this._div.style.visibility == "hidden") {
                        this.show();
                    } else {
                        this.hide();
                    }
                }
            }

            HotelOverlay.prototype.getHotel = function () {
                return this._hotel;
            }

            HotelOverlay.prototype.getInfoBox = function () {
                return this._InfoBox;
            }

            HotelOverlay.prototype.setInfoBox = function (infoBox) {
                this._InfoBox = infoBox;
            }

            HotelOverlay.prototype.addEventListener = function (event, fun) {
                this._div['on' + event] = fun;
            }

            HotelOverlay.prototype.showHotelInfoWin = function (list) {
                //this._map.centerAndZoom(this._point, 12);
                for (var i = 0; i < list.length; i++) {
                    if (typeof list[i].HotelOverlay !== 'undefined' && list[i].HotelOverlay) {
                        list[i].HotelOverlay.hideHotelInfoWin();
                    }
                }
                if (this._InfoBox) {
                    //map.centerAndZoom(this._point, map.getZoom());
                    this._InfoBox.open(this._point);
                }
            }

            HotelOverlay.prototype.hideHotelInfoWin = function () {
                if (this._InfoBox) {
                    this._InfoBox.hide();
                }
            }

            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                var pointXY = _data.BmapPoint;
                if (pointXY != null) {
                    var latitude = Number(pointXY.split(',')[1]);
                    var longitude = Number(pointXY.split(',')[0]);
                    var point = new BMap.Point(latitude, longitude);
                    _data.point = point;
                    var hotelOverlay = new HotelOverlay(point, _data);
                    map.addOverlay(hotelOverlay);
                    overlays.push(hotelOverlay);
                    _data.HotelOverlay = hotelOverlay;

                    if (typeof isShowDetail != 'undefined' && isShowDetail != '' && isShowDetail == true) {
                        var content = $('<div class="map_tc" style="display:block"></div>');
                        $("#RoomTypeMapListTemplate").tmpl(_data, {}).appendTo(content);
                        $.fn.Logger(content[0]);
                        $.fn.Logger('Width:' + content.width() + ',Height:' + content.innerHeight());
                        var infoBox = new BMapLib.InfoBox(map, content[0], {
                            boxStyle: {
                                width: "700px"
                              , height: "300px"
                            }
                            , closeIconMargin: "1px 1px -30 0"
                            , enableAutoPan: true
                            , closeIconUrl: '/Content/themes/img/close.gif'
                            , offset: new BMap.Size(0, 40)
                            , align: INFOBOX_AT_TOP
                        });
                        hotelOverlay.setInfoBox(infoBox);

                        hotelOverlay.addEventListener('click', function () {
                            var num = Number($('.map_num', $(this))[0].innerHTML) - 1;
                            list[num].HotelOverlay.showHotelInfoWin(list)
                        });
                    }

                }
            }

            map.addEventListener('dragging', function () {
                $.fn.Logger('dragging');
                for (var i = 0; i < list.length; i++) {
                    if (typeof list[i].HotelOverlay !== 'undefined' && list[i].HotelOverlay) {
                        list[i].HotelOverlay.hideHotelInfoWin();
                    }
                }
            });

        }

        $.fn.setViewport(map, languageType, list);
        return overlays;
    }

    //添加单个的酒店覆盖物标注
    //使用场景：查看酒店地图
    $.fn.addHotelOverlay = function (map, languageType, hotelIndex, hotelName, pointX, pointY) {
        if (map == null)
            return;
        function getHotelInnerHTML(index, name) {
            var div = $('<div class="amap-marker" style="position: absolute;opacity: 1; cursor: pointer; transform: translate(69px, 114px);">');
            var d = $('<div class="map_mark"></div>');
            d.append('<span class="map_num">' + index + '</span>');
            d.append('<div class="map_mark_inner"><span class="map_mark_name" style="white-space: nowrap;">' + name + '</span></div>');
            div.append(d);
            return div;
        }

        if (languageType != 'CHS') {
            function GHotelOverlay(map, options) {
                this.latlng = options.latlng; //设置图标的位置  
                this._index = options.index;
                this._name = options.name;
                this.labelClass = options.labelClass || 'shadow';//设置文字的样式  
                this.click = options.click;//注册点击事件  
                this._map = map;

                this._div = null;
                this.setMap(map);
            }
            GHotelOverlay.prototype = new google.maps.OverlayView();

            //初始化图标  
            GHotelOverlay.prototype.onAdd = function () {
                var div = document.createElement('DIV'); //创建存放图片和文字的div  
                div.style.border = "none";
                div.style.borderWidth = "0px";
                div.style.position = "absolute";
                div.style.cursor = "hand";
                div.onclick = this.click || function () { };//注册click事件，没有定义就为空函数

                var d = getHotelInnerHTML(this._index, this._name);

                div.appendChild(d[0]);

                this._div = div;
                var panes = this.getPanes();
                panes.overlayMouseTarget.appendChild(div);
                google.maps.event.addDomListener(div, 'click', function () {
                });
            }

            //绘制图标，主要用于控制图标的位置  
            GHotelOverlay.prototype.draw = function () {
                var overlayProjection = this.getProjection();
                var position = overlayProjection.fromLatLngToDivPixel(this.latlng);   //将地理坐标转换成屏幕坐标    
                var div = this._div;
                div.style.left = position.x - 5 + 'px';
                div.style.top = position.y - 5 + 'px';
                div.style.width = '10px';
                div.style.height = '10px';
            }

            GHotelOverlay.prototype.onRemove = function () {
                this._div.parentNode.removeChild(this._div);
                this._div = null;
            }

            GHotelOverlay.prototype.hide = function () {
                if (this._div) {
                    this._div.style.visibility = "hidden";
                }
            }

            GHotelOverlay.prototype.show = function () {
                if (this._div) {
                    this._div.style.visibility = "visible";
                }
            }

            GHotelOverlay.prototype.toggle = function () {
                if (this._div) {
                    if (this._div.style.visibility == "hidden") {
                        this.show();
                    } else {
                        this.hide();
                    }
                }
            }

            var latitude = Number(pointX);
            var longitude = Number(pointY);

            var overlay = new GHotelOverlay(map, {
                latlng: new google.maps.LatLng(latitude, longitude),
                index: hotelIndex,
                name: hotelName,
                click: function () {

                }
            });
            map.addOverlay(overlay);
            return overlay;
        } else {
            function HotelOverlay(point, index, name) {
                this._point = point;
                this._index = index;
                this._name = name;
            }

            HotelOverlay.prototype = new BMap.Overlay();

            HotelOverlay.prototype.initialize = function (map) {
                this._map = map;
                var div = getHotelInnerHTML(this._index, this._name);

                this._div = div[0];
                this._map.getPanes().labelPane.appendChild(this._div);
                var hotel = this._hotel;
                var point = this._point;

                div.bind('click', function (e) {
                });
                return this._div;
            }

            HotelOverlay.prototype.draw = function () {
                var map = this._map;
                var pixel = map.pointToOverlayPixel(this._point);
                this._div.style.left = pixel.x + "px";
                this._div.style.top = pixel.y - 30 + "px";
            }

            HotelOverlay.prototype.hide = function () {
                if (this._div) {
                    this._div.style.visibility = "hidden";
                }
            }

            HotelOverlay.prototype.show = function () {
                if (this._div) {
                    this._div.style.visibility = "visible";
                }
            }

            HotelOverlay.prototype.toggle = function () {
                if (this._div) {
                    if (this._div.style.visibility == "hidden") {
                        this.show();
                    } else {
                        this.hide();
                    }
                }
            }

            var point = new BMap.Point(pointY, pointX);
            var hotelOverlay = new HotelOverlay(point, hotelIndex, hotelName);
            map.addOverlay(hotelOverlay);
            map.centerAndZoom(point, 16);

            return hotelOverlay;
        }

    }

    $.fn.addHotelCircumOverlay = function (map, languageType, hotelIndex, hotelName, hotelPointX, hotelPointY, list) {
        if (map == null)
            return;
        $.fn.clearOverlays(map, languageType);
        //$.fn.addHotelOverlay(map, languageType, hotelIndex, hotelName, hotelPointX, hotelPointY);
        $.fn.addHotelMarker(map, languageType, hotelIndex, hotelName, hotelPointX, hotelPointY);
        if (languageType != 'CHS') {
            //map.addOverlay(overlay);

            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                var pointXY = _data.Pointxy;
                if (pointXY != null) {
                    var latitude = Number(pointXY.split(',')[0]);
                    var longitude = Number(pointXY.split(',')[1]);

                    var latlng = new google.maps.LatLng(latitude, longitude);

                    if (latlng) {
                        var marker = new MarkerWithLabel({
                            position: latlng,
                            map: map,
                            draggable: false,
                            raiseOnDrag: true,
                            labelContent: _data.Name,
                            labelAnchor: new google.maps.Point(22, 0),
                            labelClass: "labels",
                            labelStyle: { opacity: 0.75 }
                        });

                        var iw = new google.maps.InfoWindow({
                            content: ""
                        });

                        google.maps.event.addListener(marker, "click", function (e) {
                            //iw.open(map, this);
                        });

                        map.addOverlay(marker);
                    }

                }
            }
        } else {
            var markers = [];
            var points = [];
            var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png",
                new BMap.Size(23, 25),
                {
                    imageOffset: new BMap.Size(-46, -21),
                    infoWindowOffset: new BMap.Size(17, 1),
                    offset: new BMap.Size(9, 25)
                });
            points.push(new BMap.Point(hotelPointY, hotelPointX));
            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                $.fn.Logger('pointY:' + _data.Pointxy);
                if (_data.Pointxy && _data.Pointxy.split(',').length > 0) {
                    var point = new BMap.Point(_data.Pointxy.split(',')[1], _data.Pointxy.split(',')[0]);
                    points.push(point);
                    var marker = new BMap.Marker(point, { icon: icon });
                    var label = new BMap.Label(_data.Name, { "offset": new BMap.Size(12, 20) });
                    marker.setLabel(label);
                    markers.push(marker);
                    map.addOverlay(marker);
                }
            }
            map.setViewport(points);
            return markers;
        }

    }


    $.fn.addHotelMarker = function (map, languageType, hotelIndex, hotelName, pointX, pointY) {
        if (map == null)
            return;
        if (languageType != 'CHS') {
            var latitude = Number(pointX);
            var longitude = Number(pointY);

            var latlng = new google.maps.LatLng(latitude, longitude);

            var icon = "/Content/themes/img/icon/biao_hotel.png";

            var marker = new google.maps.Marker({
                position: latlng,
                map: map,
                icon: icon
            });

            marker.infoWindow = new google.maps.InfoWindow({
                content: hotelName
            });
            marker.Name = hotelName;

            google.maps.event.addListener(marker, "click", function (e) {
                //this.infoWindow.open(map, this);
            });

            map.addOverlay(marker);
            //marker.infoWindow.open(map, marker);
            return marker;
        } else {
            var point = new BMap.Point(pointY, pointX);
            var icon = new BMap.Icon('/Content/themes/img/icon/biao_hotel.png',
                new BMap.Size(30, 38),
                {
                    infoWindowOffset: new BMap.Size(0, 70)
                });

            var a = {};
            //a.offset = new BMap.Size(30, 38)
            a.icon = icon;
            var marker = new BMap.Marker(point, a);
            map.addOverlay(marker);

            var infoWindow = new BMap.InfoWindow(hotelName, { enableMessage: false });
            map.centerAndZoom(point, 15);
            map.addOverlay(marker);
            //marker.openInfoWindow(infoWindow);
            marker.addEventListener("click", function () {
                //this.openInfoWindow(infoWindow);
            });

            return marker;
        }

    }

    $.fn.addHotelCircumMarkers = function (map, languageType, hotelIndex, hotelName, hotelPointX, hotelPointY, list, cityId) {
        if (map == null)
            return;
        $.fn.clearOverlays(map, languageType);
        $.fn.addHotelMarker(map, languageType, hotelIndex, hotelName, hotelPointX, hotelPointY);
        var markers = [];
        if (languageType != 'CHS') {
            //var directionsService = new google.maps.DirectionsService();
            //var directionsDisplay = new google.maps.DirectionsRenderer();
            var directionsService = map.DirectionsService;
            if (typeof directionsService === 'undefined' || !directionsService) {
                directionsService = new google.maps.DirectionsService();
                map.DirectionsService = directionsService;
            }
            var directionsDisplay = map.DirectionsRenderer;
            if (typeof directionsDisplay === 'undefined' || !directionsDisplay) {
                directionsDisplay = new google.maps.DirectionsRenderer();
                map.DirectionsRenderer = directionsDisplay;
            }

            directionsDisplay.setMap(null);
            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                var pointXY = _data.Pointxy_G;
                if (pointXY != null) {
                    var latitude = Number(pointXY.split(',')[0]);
                    var longitude = Number(pointXY.split(',')[1]);

                    var latlng = new google.maps.LatLng(latitude, longitude);

                    if (latlng) {
                        var icon = "/Content/themes/img/icon/biao_" + _data.HotelIndex + ".png";

                        var marker = new google.maps.Marker({
                            position: latlng,
                            map: map,
                            icon: icon
                        });

                        marker.infoWindow = new google.maps.InfoWindow({
                            content: _data.Name
                        });
                        marker.Name = _data.Name;
                        marker.longitude = longitude;
                        marker.latitude = latitude;
                        markers.push(marker);
                        google.maps.event.addListener(marker, "mouseover", function (e) {
                            for (var i = 0; i < map.overlays.length; i++) {
                                if (map.overlays[i].infoWindow) {
                                    map.overlays[i].infoWindow.close();
                                }
                            }
                            this.infoWindow.open(map, this);
                        });
                        marker.show = function () {
                            var url = "http://maps.google.com/maps?saddr=" + (this.latitude + ',' + this.longitude) + "&daddr=" + (hotelPointX + ',' + hotelPointY) + "&hl=en&dirflg=r&sensor=true&language=en";
                            $.fn.Logger(url);
                            window.open(url, "_blank");
                        }

                        marker.click = function () {
                            var request = {
                                origin: this.getPosition(),
                                destination: new google.maps.LatLng(hotelPointX, hotelPointY),
                                travelMode: google.maps.TravelMode.DRIVING
                            };
                            directionsService.route(request, function (result, status) {
                                if (directionsDisplay) {
                                    directionsDisplay.setMap(null);
                                }
                                directionsDisplay.setMap(map);
                                if (status == google.maps.DirectionsStatus.OK) {
                                    directionsDisplay.setDirections(result);
                                }
                            });
                        }
                        google.maps.event.addListener(marker, "click", marker.click);
                        map.addOverlay(marker);
                    }

                }
            }
        } else {
            var points = [];

            points.push(new BMap.Point(hotelPointY, hotelPointX));

            var transit = new BMap.DrivingRoute(map, {
                renderOptions: {
                    map: map,
                },
                onSearchComplete: function (results) {
                    if (transit.getStatus() != BMAP_STATUS_SUCCESS) {
                        return;
                    }
                },
                onPolylinesSet: function () {
                    setTimeout(function () {
                    }, 500);
                }
            });

            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                if (_data.Pointxy && _data.Pointxy.split(',').length > 0) {
                    var pointXY = _data.Pointxy;
                    var latitude = Number(pointXY.split(',')[1]);
                    var longitude = Number(pointXY.split(',')[0]);

                    var point = new BMap.Point(latitude, longitude);
                    points.push(point);
                    var icon = new BMap.Icon("/Content/themes/img/icon/biao_" + _data.HotelIndex + ".png", new BMap.Size(23, 24));
                    var a = {};
                    a.icon = icon;
                    var marker = new BMap.Marker(point, a);
                    _data.marker = marker;
                    marker.infoWindow = new BMap.InfoWindow(_data.Name, { enableMessage: false });
                    marker.Name = _data.Name
                    marker.longitude = longitude;
                    marker.latitude = latitude;
                    marker.addEventListener('mouseover', function () {
                        this.openInfoWindow(this.infoWindow);
                    });

                    marker.show = function () {
                        var city = parent.popCityMap['p_city_' + cityId];
                        if (typeof city === 'undefined' || !city) {
                            city = parent.popCityMap['p_city_' + cityId + '_c'];
                        }
                        var cityText = city && city.Name ? city.Name : '上海';
                        var url = 'https://api.map.baidu.com/direction?origin=latlng:' + (this.longitude + ',' + this.latitude) + '|name:' + this.Name + '&destination=latlng:' + (hotelPointX + ',' + hotelPointY) + '|name:' + hotelName + '&mode=transit&region=' + cityText + '&output=html';
                        $.fn.Logger(url);
                        window.open(url, '_blank');
                    }

                    marker.click = function () {
                        transit.clearResults();
                        transit.search(new BMap.Point(hotelPointY, hotelPointX), this.getPosition());
                    }

                    marker.addEventListener('click', marker.click);
                    markers.push(marker);
                    map.addOverlay(marker);
                }
            }
            map.setViewport(points);

        }
        return markers;
    }

    $.fn.addHotelMarkers = function (map, languageType, list) {
        if (map == null)
            return;
        var overlays = [];
        $.fn.clearOverlays(map, languageType);

        if (languageType != 'CHS') {

            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                if (_data.GmapPoint && _data.GmapPoint.split(',').length > 0) {
                    var icon = "/Content/themes/img/icon/hs_" + _data.Index + ".png";

                    var marker = new google.maps.Marker({
                        position: new google.maps.LatLng(_data.GmapPoint.split(',')[0], _data.GmapPoint.split(',')[1]),
                        map: map,
                        icon: icon
                    });
                    _data.HotelOverlay = marker;
                    map.addOverlay(marker);
                    marker.infoWindow = new google.maps.InfoWindow({
                        content: _data.HotelName
                    });
                    marker.HotelCode = _data.HotelCode;
                    marker.mouseover = function () {
                        /*
                        for (var i = 0; i < map.overlays.length; i++) {
                            if (map.overlays[i].infoWindow) {
                                map.overlays[i].infoWindow.close();
                            }
                        }
                        this.infoWindow.open(map, this);
                        */
                    }
                    marker.click = function () {
                        $.fn.forwardToHotelDetail('', '', '', '', this.HotelCode, '', '', '', '');
                    }
                    marker.show = function () {
                        this.setMap(map);
                    }
                    marker.hide = function () {
                        this.setMap(null);
                    }
                    google.maps.event.addListener(marker, 'mouseover', marker.mouseover);
                    google.maps.event.addListener(marker, 'click', marker.click);

                    overlays.push(marker);
                    map.addOverlay(marker);
                }
            }

        } else {
            //百度地图
            if (list.PointX && list.PointX != "undefined") {
                var point = new BMap.Point(list.PointY, list.PointX);
                map.centerAndZoom(point, 12);
                var icon = new BMap.Icon("/Content/themes/img/icon/biao_point.png", new BMap.Size(33, 48));
                var a = {};
                a.icon = icon;
                var marker = new BMap.Marker(point, a);// 创建标注
                map.addOverlay(marker);
            }

            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                if (_data.BmapPoint && _data.BmapPoint.split(',').length > 0) {
                    var point = new BMap.Point(_data.BmapPoint.split(',')[1], _data.BmapPoint.split(',')[0]);
                    var icon = new BMap.Icon("/Content/themes/img/icon/hs_" + _data.Index + ".png", new BMap.Size(23, 24));
                    var a = {};
                    a.icon = icon;
                    var marker = new BMap.Marker(point, a);
                    _data.HotelOverlay = marker;
                    marker.infoWindow = new BMap.InfoWindow(_data.HotelName, { enableMessage: false });
                    marker.HotelCode = _data.HotelCode;
                    marker.mouseover = function () {
                        /*
                        this.openInfoWindow(this.infoWindow);
                        */
                    }
                    marker.click = function () {
                        $.fn.forwardToHotelDetail('', '', '', '', this.HotelCode, '', '', '', '');
                    }
                    marker.addEventListener('mouseover', marker.mouseover);
                    marker.addEventListener('click', marker.click);
                    overlays.push(marker);
                    map.addOverlay(marker);
                }
            }
        }

        $.fn.setViewport(map, languageType, list);
        return overlays;
    }

    $.fn.addHotelMapMarkers = function (map, languageType, list) {
        if (map == null)
            return;
        var overlays = [];


        if (languageType != 'CHS') {
            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                if (_data.GmapPoint && _data.GmapPoint.split(',').length > 0) {
                    var icon = "/Content/themes/img/icon/lh_" + _data.Index + ".png";
                    var marker = new google.maps.Marker({
                        position: new google.maps.LatLng(_data.GmapPoint.split(',')[0], _data.GmapPoint.split(',')[1]),
                        map: map,
                        icon: icon
                    });
                    _data.HotelOverlay = marker;
                    map.addOverlay(marker);
                    var content = $('<div class="map_tc" style="display:block"></div>');
                    $("#RoomTypeMapListTemplate").tmpl(_data, {}).appendTo(content);

                    marker.infoWindow = new google.maps.InfoWindow({
                        content: content[0]
                    });

                    $('.opo', content).click(function () {
                        var _this = $(this);
                        var hotelCode = _this.attr('data-hotelcode');
                        var roomType = _this.attr('data-roomtype');
                        var isSpecial = _this.attr('data-sp');
                        var startDate = _this.attr('data-start');
                        var endDate = _this.attr('data-end');
                        var activitycode = _this.attr("data-activitycode");
                        var totaldays = _this.attr('data-totaldays');
                        if (isSpecial == "true" && activitycode == "C0000001") //998房间
                        {
                            var nowDate = new Date();
                            var nowMonth = nowDate.getMonth() + 1;
                            var nowDay = nowDate.getDate();
                            if (nowMonth < 10) {
                                nowMonth = "0" + nowMonth;
                            }
                            if (nowDay < 10) {
                                nowDay = "0" + nowDay;
                            }
                            var nowStringDate = nowDate.getFullYear() + "-" + nowMonth + "-" + nowDay;
                            if (startDate <= nowStringDate) {
                                alert($.i18n.prop('label.hotel.list.nodata998'));
                            }
                            else {
                                if (typeof hotelCode !== 'undefined' && hotelCode) {
                                    $.fn.forwardToHotelOrder(hotelCode, activitycode, roomType, startDate, endDate, isSpecial, totaldays);
                                }
                            }
                        }
                        else {
                            if (typeof hotelCode !== 'undefined' && hotelCode) {
                                $.fn.forwardToHotelOrder(hotelCode, activitycode, roomType, startDate, endDate, isSpecial, totaldays);
                            }
                        }
                    });

                    marker.HotelCode = _data.HotelCode;
                    /*
                    marker.mouseover = function () {
                        for (var i = 0; i < map.overlays.length; i++) {
                            if (map.overlays[i].infoWindow) {
                                map.overlays[i].infoWindow.close();
                            }
                        }
                        this.infoWindow.open(map, this);
                    }
                    */
                    marker.click = function () {
                        //$.fn.forwardToHotelDetail('', '', '', '', this.HotelCode, '', '', '', '');
                        for (var i = 0; i < map.overlays.length; i++) {
                            if (map.overlays[i].infoWindow) {
                                map.overlays[i].infoWindow.close();
                            }
                        }
                        this.infoWindow.open(map, this);
                    }
                    marker.show = function () {
                        this.setMap(map);
                    }
                    marker.hide = function () {
                        this.setMap(null);
                    }
                    /*google.maps.event.addListener(marker, 'mouseover', marker.mouseover);*/
                    google.maps.event.addListener(marker, 'click', marker.click);

                    overlays.push(marker);
                    map.addOverlay(marker);
                }
            }

        } else {
            //百度地图
            if (list.PointX && list.PointX != "undefined") {
                var point = new BMap.Point(list.PointY, list.PointX);
                map.centerAndZoom(point, 12);
                var icon = new BMap.Icon("/Content/themes/img/icon/biao_point.png", new BMap.Size(33, 48));
                var a = {};
                a.icon = icon;
                var marker = new BMap.Marker(point, a);// 创建标注
                map.addOverlay(marker);
            }

            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                if (_data.BmapPoint && _data.BmapPoint.split(',').length > 0) {
                    var point = new BMap.Point(_data.BmapPoint.split(',')[1], _data.BmapPoint.split(',')[0]);
                    var icon = new BMap.Icon("/Content/themes/img/icon/lh_" + _data.Index + ".png", new BMap.Size(30, 38));

                    var a = {};
                    a.icon = icon;
                    var marker = new BMap.Marker(point, a);
                    _data.HotelOverlay = marker;
                    //marker.infoWindow = new BMap.InfoWindow(_data.HotelName);

                    var content = $('<div class="map_tc" style="display:block"></div>');
                    $("#RoomTypeMapListTemplate").tmpl(_data, {}).appendTo(content);
                    /**
                    var infoBox = new BMapLib.InfoBox(map, content[0], {
                        boxStyle: {
                            width: "700px"
                          , height: "300px"
                        }
                        , closeIconMargin: "1px 1px -30 0"
                        , enableAutoPan: true
                        , closeIconUrl: '/Content/themes/img/close.gif'
                        , offset: new BMap.Size(0, 40)
                        , align: INFOBOX_AT_TOP
                    });
                    */
                    $('.opo', content).click(function () {
                        var _this = $(this);
                        var hotelCode = _this.attr('data-hotelcode');
                        var roomType = _this.attr('data-roomtype');
                        var isSpecial = _this.attr('data-sp');
                        var startDate = _this.attr('data-start');
                        var endDate = _this.attr('data-end');
                        var activitycode = _this.attr("data-activitycode");
                        var totaldays = _this.attr('data-totaldays');
                        if (isSpecial == "true" && activitycode == "C0000001") //998房间
                        {
                            var nowDate = new Date();
                            var nowMonth = nowDate.getMonth() + 1;
                            var nowDay = nowDate.getDate();
                            if (nowMonth < 10) {
                                nowMonth = "0" + nowMonth;
                            }
                            if (nowDay < 10) {
                                nowDay = "0" + nowDay;
                            }
                            var nowStringDate = nowDate.getFullYear() + "-" + nowMonth + "-" + nowDay;
                            if (startDate <= nowStringDate) {
                                alert($.i18n.prop('label.hotel.list.nodata998'));
                            }
                            else {
                                if (typeof hotelCode !== 'undefined' && hotelCode) {
                                    $.fn.forwardToHotelOrder(hotelCode, activitycode, roomType, startDate, endDate, isSpecial, totaldays);
                                }
                            }
                        }
                        else {
                            if (typeof hotelCode !== 'undefined' && hotelCode) {
                                $.fn.forwardToHotelOrder(hotelCode, activitycode, roomType, startDate, endDate, isSpecial, totaldays);
                            }
                        }
                    });

                    marker.infoWindow = new BMap.InfoWindow(content[0], { width: 700, height: 280, enableMessage: false });

                    marker.HotelCode = _data.HotelCode;
                    /*
                    marker.mouseover = function () {
                        this.openInfoWindow(this.infoWindow);
                    }
                    */
                    marker.click = function () {
                        //$.fn.forwardToHotelDetail('', '', '', '', this.HotelCode, '', '', '', '');
                        this.openInfoWindow(this.infoWindow);
                    }
                    marker.addEventListener('mouseover', marker.mouseover);
                    marker.addEventListener('click', marker.click);
                    overlays.push(marker);
                    map.addOverlay(marker);
                }
            }
        }

        $.fn.setViewport(map, languageType, list);
        return overlays;
    }
    $.fn.addHotelMapMarkers_all = function (map, languageType, list) {
        if (map == null)
            return;
        var overlays = [];
        for (var i = 0; i < list.length; i++) {
            var _alldata = list[i];
            if (_alldata.BmapPoint && _alldata.BmapPoint.split(',').length > 0) {
                var point = new BMap.Point(_alldata.BmapPoint.split(',')[1], _alldata.BmapPoint.split(',')[0]);
                var icon = new BMap.Icon("/Content/themes/img/icon/lh_other.png", new BMap.Size(30, 38));
                var a = {};
                a.icon = icon;
                addMarker(point, a, _alldata);
            }
        }
        //$.fn.setViewport(map, languageType, list);
        //return overlays;
    };

    function addMarker(point, a, _alldata) {
        var _marker = new BMap.Marker(point, a);
        _alldata.HotelOverlay = _marker;
        _marker.click = function () {
            startDate = $("#txtCheckIn").val();
            endDate = $("#txtCheckOut").val();
            $.ajax({
                type: "POST",
                url: '/HotelMap/SearchHotelDetail',
                traditional: true,
                data: {
                    hotelCode: _alldata.HotelCode,
                    startDate: startDate,
                    endDate: endDate,
                    PageIndex: 1,
                    PageSize: 1
                },
                success: function (data) {
                    var h = data.HotelInfo;

                    for (var j = 0; j < h.RoomTypeList.length; j++) {
                        var r = h.RoomTypeList[j];
                        if (r.Vouchers && r.Vouchers.length > 0) {
                            r.Vouchers = r.Vouchers.split(',');
                            r.Vouchers.sort(function (a, b) { return Number(b) - Number(a); });
                        }
                        r.Checkout = $.fn.format(new Date(eval(r.Checkout.replace(/\/Date\((\d+)\)\//gi, "new Date($1)"))), 'yyyy-MM-dd');
                        r.Checkin = $.fn.format(new Date(eval(r.Checkin.replace(/\/Date\((\d+)\)\//gi, "new Date($1)"))), 'yyyy-MM-dd');
                    }

                    var content = $('<div class="map_tc" style="display:block"></div>');
                    $("#RoomTypeMapListTemplate").tmpl(data.HotelInfo, {}).appendTo(content);
                    $('.opo', content).click(function () {
                        var _this = $(this);
                        var hotelCode = _this.attr('data-hotelcode');
                        var roomType = _this.attr('data-roomtype');
                        var isSpecial = _this.attr('data-sp');
                        var startDate = _this.attr('data-start');
                        var endDate = _this.attr('data-end');

                        var totaldays = _this.attr('data-totaldays');
                        if (isSpecial == "true") //998房间
                        {
                            var nowDate = new Date();
                            var nowMonth = nowDate.getMonth() + 1;
                            var nowDay = nowDate.getDate();
                            if (nowMonth < 10) {
                                nowMonth = "0" + nowMonth;
                            }
                            if (nowDay < 10) {
                                nowDay = "0" + nowDay;
                            }
                            var nowStringDate = nowDate.getFullYear() + "-" + nowMonth + "-" + nowDay;
                            if (startDate <= nowStringDate) {
                                alert($.i18n.prop('label.hotel.list.nodata998'));
                            }
                            else {
                                if (typeof hotelCode !== 'undefined' && hotelCode) {
                                    $.fn.forwardToHotelOrder(hotelCode, roomType, startDate, endDate, isSpecial, totaldays);
                                }
                            }
                        }
                        else {
                            if (typeof hotelCode !== 'undefined' && hotelCode) {
                                $.fn.forwardToHotelOrder(hotelCode, roomType, startDate, endDate, isSpecial, totaldays);
                            }
                        }
                    });
                    var infoWindow = new BMap.InfoWindow(content[0], { width: 700, height: 280, enableMessage: false }); // 创建信息窗口对象    
                    map.openInfoWindow(infoWindow, point);
                }
            });
        };
        _marker.addEventListener('mouseover', _marker.mouseover);
        _marker.addEventListener('click', _marker.click);

        map.addOverlay(_marker);
    }

    $.fn.addHotelPrintOverlay = function (map, languageType, hotelIndex, hotelName, pointX, pointY) {
        if (map == null)
            return;
        if (languageType != 'CHS') {

            var latitude = Number(pointX);
            var longitude = Number(pointY);

            var overlay = new GHotelOverlay(map, {
                latlng: new google.maps.LatLng(latitude, longitude),
                index: hotelIndex,
                name: hotelName,
                click: function () {

                }
            });
            map.addOverlay(overlay);
            return overlay;
        } else {

            var point = new BMap.Point(pointY, pointX);
            var hotelOverlay = new HotelOverlay(point, hotelIndex, hotelName);
            map.addOverlay(hotelOverlay);
            map.centerAndZoom(point, 16);

            return hotelOverlay;
        }

    }

    $.fn.clearOverlays = function (map, languageType, list) {
        if (map == null)
            return;
        if (languageType != 'CHS') {
            map.clearOverlays();
        } else {
            map.clearOverlays();
        }
    }

    $.fn.setViewport = function (map, languageType, list) {
        if (map == null)
            return;
        if (languageType != 'CHS') {
            var latLngs = [];
            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                var pointXY = _data.GmapPoint;
                if (pointXY) {
                    var latitude = Number(pointXY.split(',')[0]);
                    var longitude = Number(pointXY.split(',')[1]);
                    var latLng = new google.maps.LatLng(latitude, longitude);
                    latLngs.push(latLng);
                }
            }
            //map.fitBounds(points);
            //map.fitLatLngBounds(latLngs);
            var total = latLngs.length;
            var bounds = new google.maps.LatLngBounds();

            for (var i = 0; i < total; i++) {
                bounds.extend(latLngs[i]);
            }

            map.fitBounds(bounds);

        } else {
            var points = [];
            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                var pointXY = _data.BmapPoint;
                if (pointXY) {
                    var latitude = Number(pointXY.split(',')[1]);
                    var longitude = Number(pointXY.split(',')[0]);
                    var point = new BMap.Point(latitude, longitude);
                    points.push(point);
                }
            }
            if (points && points.length > 0) {
                map.centerAndZoom(points[0], 15);
            }
            map.setViewport(points);
        }

    }

    //重置酒店列表的Overlay
    //根据酒店信息中的：hidden属性隐藏和显示地图信息
    $.fn.resetHotelOverlay = function (map, languageType, list) {
        if (map == null)
            return;
        if (languageType != 'CHS') {
            var latLngs = [];
            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                var hidden = _data.hidden;
                if ((typeof hidden === 'undefined' || hidden == true) && _data.HotelOverlay && typeof _data.HotelOverlay.hide !== 'undefined') {
                    _data.HotelOverlay.hide();
                } else if (_data.HotelOverlay && typeof _data.HotelOverlay.show !== 'undefined') {
                    _data.HotelOverlay.show();
                    var pointXY = _data.GmapPoint;
                    if (pointXY) {
                        var latitude = Number(pointXY.split(',')[0]);
                        var longitude = Number(pointXY.split(',')[1]);
                        var latLng = new google.maps.LatLng(latitude, longitude);
                        latLngs.push(latLng);
                    }
                }
            }

            var total = latLngs.length;
            var bounds = new google.maps.LatLngBounds();

            for (var i = 0; i < total; i++) {
                bounds.extend(latLngs[i]);
            }

            map.fitBounds(bounds);

        } else {
            var points = [];
            for (var i = 0; i < list.length; i++) {
                var _data = list[i];
                var hidden = _data.hidden;
                if (_data.HotelOverlay !== 'undefined' && _data.HotelOverlay) {
                    if ((typeof hidden === 'undefined' || hidden == true) && _data.HotelOverlay && typeof _data.HotelOverlay.hide !== 'undefined') {
                        _data.HotelOverlay.hide();
                    } else if (_data.HotelOverlay && typeof _data.HotelOverlay.show !== 'undefined') {
                        _data.HotelOverlay.show();
                        var pointXY = _data.BmapPoint;
                        if (pointXY) {
                            var latitude = Number(pointXY.split(',')[1]);
                            var longitude = Number(pointXY.split(',')[0]);
                            var point = new BMap.Point(latitude, longitude);
                            points.push(point);
                        }
                    }
                }
            }
            points.push(new BMap.Point(list.PointY, list.PointX));
            map.setViewport(points);
        }

    }

})(jQuery);

(function ($) {
    /**
     * 初始化“入住时间”和“退房时间”
     * @param beginDate
     * @param endDate
     */
    $.fn.initDate = function (beginDate, endDate, defaultBeginDate, defaultEndDate) {
        var d = new Date();
        defaultBeginDate = typeof defaultBeginDate !== 'undefined' && defaultBeginDate != '' ? defaultBeginDate : $.fn.format(d, 'yyyy-MM-dd');
        defaultEndDate = typeof defaultEndDate !== 'undefined' && defaultEndDate != '' ? defaultEndDate : $.fn.format(new Date(d.getFullYear(), d.getMonth(), d.getDate() + 1), 'yyyy-MM-dd');

        beginDate.val(defaultBeginDate);
        endDate.val(defaultEndDate);

        //控制选择离店时间 2017-07-19 2w
        var endMaxDate = $.fn.format(new Date(d.getFullYear(), d.getMonth(), d.getDate() + 14), 'yyyy-MM-dd');
        var endMaxDateNew = new Date(d.getFullYear(), d.getMonth(), d.getDate() + 90);

        beginDate.bind('focus', function () {


            WdatePicker({
                doubleCalendar: true,
                minDate: '%y-%M-%d',
                maxDate: '%y-%M-{%d + 89}',
                //maxDate: maxDate,
                dateFmt: 'yyyy-MM-dd',
                onpicking: function (dp) {
                    $.fn.Logger(dp.cal.getNewDateStr());
                    var bd = $.fn.convertDate(dp.cal.getNewDateStr());
                    defaultEndDate = $.fn.format(new Date(bd.getFullYear(), bd.getMonth(), bd.getDate() + 1), 'yyyy-MM-dd');
                    var endMaxDateA = new Date(bd.getFullYear(), bd.getMonth(), bd.getDate() + 14);
                    if (endMaxDateA > endMaxDateNew )
                    {
                        endMaxDateA=endMaxDateNew;
                    }
                    endMaxDate = $.fn.format(endMaxDateA, 'yyyy-MM-dd');
                    //alert($.fn.format(new Date(endMaxDateA.getFullYear(), endMaxDate.getMonth(), endMaxDate.getDate() + 1), 'yyyy-MM-dd'));
                },
                onpicked: function () {
                    endDate.val(defaultEndDate);
                    endDate.focus();
                }
            });
        });

        endDate.bind('focus', function () {
            WdatePicker({
                doubleCalendar: true,
                minDate: '#F{$dp.$D(\'' + beginDate.attr('id') + '\',{d:1});}',
                startDate: defaultEndDate,
                maxDate: endMaxDate,//'#F{$dp.$D(\'' + beginDate.attr('id') + '\',{d:14});}',
                dateFmt: 'yyyy-MM-dd',
                onpicked: function () {
                    /*
                    beginDate.unbind('focus');
                    beginDate.bind('focus', function () {
                        WdatePicker({
                            doubleCalendar: true,
                            minDate: '%y-%M-%d',
                            maxDate: $dp.cal.getNewDateStr(),
                            //maxDate: '#F{$dp.$D(\'' + endDate.attr('id') + '\',{d:0});}',
                            dateFmt: 'yyyy-MM-dd'
                        })
                    });
                    */
                }
            });
        });
        /*
        .bind('focus', function () {
            var bd = $.fn.convertDate(beginDate.val());
            var isError = false;
            if ($(this).val() && $(this).val() != '') {
                var ed = $.fn.convertDate($(this).val());
                if (ed.getTime() <= bd.getTime()) {
                    isError = true;
                }
            }
            if (!$(this).val() || $(this).val() == '' || isError) {
                $.fn.format(new Date(bd.getFullYear(), bd.getMonth(), bd.getDate() + 1), 'yyyy-MM-dd');
            }
        });
        */
    }
    $.fn.initDateOrder = function (beginDate, endDate, defaultBeginDate, defaultEndDate,orderDate) {
        var d = new Date();
        defaultBeginDate = typeof defaultBeginDate !== 'undefined' && defaultBeginDate != '' ? defaultBeginDate : $.fn.format(d, 'yyyy-MM-dd');
        defaultEndDate = typeof defaultEndDate !== 'undefined' && defaultEndDate != '' ? defaultEndDate : $.fn.format(new Date(d.getFullYear(), d.getMonth(), d.getDate() + 1), 'yyyy-MM-dd');
        /*提前预定功能 2017-06-20 2w*/
        var d1 = new Date(orderDate.replace(/\-/g, "\/"));
        var d2 = new Date(defaultBeginDate.replace(/\-/g, "\/"));
        if (d1 > d2) {
            alert("该酒店目前只支持" + orderDate + "及以后预定！");
            defaultBeginDate = orderDate;
        }
        if (d1 < d)
        {
            orderDate = $.fn.format(d, 'yyyy-MM-dd');
        }
        var d3 = $.fn.format(new Date(d1.getFullYear(), d1.getMonth(), d1.getDate() + 1), 'yyyy-MM-dd');
        var d4 = new Date(d3.replace(/\-/g, "\/"));
        var d5 = new Date(defaultEndDate.replace(/\-/g, "\/"));
        if (d4 >= d5) {
            defaultEndDate = d3;
        }
       
        /*给文本框付默认值*/
        beginDate.val(defaultBeginDate);
        endDate.val(defaultEndDate);
        
        //控制选择离店时间 2017-07-19 2w
        var endMaxDate = $.fn.format(new Date(d.getFullYear(), d.getMonth(), d.getDate() + 14), 'yyyy-MM-dd');
        var endMaxDateNew = new Date(d.getFullYear(), d.getMonth(), d.getDate() + 90);

        beginDate.bind('focus', function () {


            WdatePicker({
                doubleCalendar: true,
                //minDate: '%y-%M-%d',
                minDate: orderDate,
                maxDate: '%y-%M-{%d + 89}',
                //maxDate: maxDate,
                dateFmt: 'yyyy-MM-dd',
                onpicking: function (dp) {
                    $.fn.Logger(dp.cal.getNewDateStr());
                    var bd = $.fn.convertDate(dp.cal.getNewDateStr());
                    defaultEndDate = $.fn.format(new Date(bd.getFullYear(), bd.getMonth(), bd.getDate() + 1), 'yyyy-MM-dd');
                    var endMaxDateA = new Date(bd.getFullYear(), bd.getMonth(), bd.getDate() + 14);
                    if (endMaxDateA > endMaxDateNew) {
                        endMaxDateA = endMaxDateNew;
                    }
                    endMaxDate = $.fn.format(endMaxDateA, 'yyyy-MM-dd');
                },
                onpicked: function () {
                    endDate.val(defaultEndDate);
                    endDate.focus();
                }
            });
        });

        endDate.bind('focus', function () {
            WdatePicker({
                doubleCalendar: true,
                minDate: '#F{$dp.$D(\'' + beginDate.attr('id') + '\',{d:1});}',
                startDate: defaultEndDate,
                maxDate: endMaxDate,//'#F{$dp.$D(\'' + beginDate.attr('id') + '\',{d:14});}',
                dateFmt: 'yyyy-MM-dd',
                onpicked: function () {
                }
            });
        });
    }
   

    //初始化特价房的时间
    //规则：开始时间2~7  结束时间：开始时间的后一天
    $.fn.initSpecialDate = function (beginDate, endDate, defaultBeginDate, defaultEndDate,days,totalDays) {
        var d = new Date();
        defaultBeginDate = typeof defaultBeginDate !== 'undefined' && defaultBeginDate != '' ? defaultBeginDate : $.fn.format(new Date(d.getFullYear(), d.getMonth(), d.getDate() + 1), 'yyyy-MM-dd');
        defaultEndDate = typeof defaultEndDate !== 'undefined' && defaultEndDate != '' ? defaultEndDate : $.fn.format(new Date(d.getFullYear(), d.getMonth(), d.getDate() + 2), 'yyyy-MM-dd');

        beginDate.val(defaultBeginDate);
        endDate.val(defaultEndDate);

        beginDate.bind('focus', function () {
            var maxDateFmtStr = '%y-%M-{%d + ' + days + '}';
            var minDateFmtStr =totalDays==1? '%y-%M-{%d + 1}':'%y-%M-{%d}';
            $.fn.Logger("时间格式化字符串：" + maxDateFmtStr);
            $.fn.Logger("当前日期：", defaultBeginDate);
            WdatePicker({
                doubleCalendar: true,
                minDate: minDateFmtStr,
                maxDate: maxDateFmtStr,
                dateFmt: 'yyyy-MM-dd',
                onpicking: function (dp) {
                    $.fn.Logger(dp.cal.getNewDateStr());
                    var bd = $.fn.convertDate(dp.cal.getNewDateStr());
                     defaultEndDate = $.fn.format(new Date(bd.getFullYear(), bd.getMonth(), bd.getDate() + totalDays), 'yyyy-MM-dd')
                },
                onpicked: function () {
                    endDate.val(defaultEndDate);
                    if (totalDays <2) {
                        endDate.focus();
                    }
                    
                }
            })
        });

        endDate.bind('focus', function () {
            WdatePicker({
                doubleCalendar: true,
                minDate: '#F{$dp.$D(\'' + beginDate.attr('id') + '\',{d:'+totalDays+'});}',
                startDate: defaultEndDate,
                maxDate: '#F{$dp.$D(\'' + beginDate.attr('id') + '\',{d:' + totalDays + '});}',
                dateFmt: 'yyyy-MM-dd',
                onpicked: function () {
                }
            })
        });
    }

    $.fn.ValidateQueryDate = function (beginDateObject, endDateObject) {
        var beginDate = beginDateObject.val();
        var endDate = endDateObject.val();
        var sysDate = new Date();
        var beginMinDate = new Date(sysDate.getFullYear(), sysDate.getMonth(), sysDate.getDate());
        var beginMaxDate = new Date(sysDate.getFullYear(), sysDate.getMonth(), sysDate.getDate() + 89);

        if (!beginDate || beginDate == '') {
            alert($.i18n.prop('label.common.error.begin.null'));
            beginDateObject.focus();
            return false;
        }
        if (!endDate || endDate == '') {
            alert($.i18n.prop('label.common.error.end.null'));
            endDateObject.focus();
            return false;
        }
        var bd = $.fn.convertDate(beginDate);
        var ed = $.fn.convertDate(endDate);
        if (bd.getTime() < beginMinDate.getTime()) {
            alert($.i18n.prop('label.common.error.begin.lt.now'));
            beginDateObject.focus();
            return false;
        }
        if (bd.getTime() > beginMaxDate.getTime()) {
            alert($.i18n.prop('label.common.error.begin.gt.ninety'));
            beginDateObject.focus();
            return false;
        }

        if (bd.getTime() > ed.getTime()) {
            alert($.i18n.prop('label.common.error.begin.gt.end'));
            endDateObject.focus();
            return false;
        }
        var endMaxDate = new Date(bd.getFullYear(), bd.getMonth(), bd.getDate() + 7);
        if (ed.getTime() > endMaxDate.getTime()) {
            alert($.i18n.prop('label.common.error.end.gt.seven'));
            endDateObject.focus();
            return false;
        }
        return true;
    }

    /**
     * 初始化“入住时间”和“退房时间”
     * @param beginDate
     * @param endDate
     */
    $.fn.initDatePicker = function (beginDate, endDate) {
        var beginMinDate = new Date();
        var beginMaxDate = new Date(beginMinDate.getFullYear(), beginMinDate.getMonth(), beginMinDate.getDate() + 89);
        beginDate.val($.datepicker.formatDate('yy-mm-dd', beginMinDate));
        endDate.val($.datepicker.formatDate('yy-mm-dd', new Date(beginMinDate.getFullYear(), beginMinDate.getMonth(), beginMinDate.getDate() + 1)));
        var endMinDate = new Date(beginMinDate.getFullYear(), beginMinDate.getMonth(), beginMinDate.getDate() + 1);
        var endMaxDate = new Date(beginMinDate.getFullYear(), beginMinDate.getMonth(), beginMinDate.getDate() + 90);

        beginDate.datepicker({
            minDate: beginMinDate,
            maxDate: beginMaxDate,
            changeMonth: true,
            numberOfMonths: 2,
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            regional: 'zh',
            onSelect: function (selectedDate) {
                endDate.datepicker("option", "maxDate", $.fn.addDate(selectedDate, 7));
            },
            onClose: function (selectedDate) {
                endDate.datepicker("option", "minDate", selectedDate);
            }
        });

        endDate.datepicker({
            minDate: endMinDate,
            maxDate: endMaxDate,
            changeMonth: true,
            numberOfMonths: 2,
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            regional: 'zh',
            onClose: function (selectedDate) {
                if (selectedDate > endMinDate) {
                    beginDate.datepicker("option", "maxDate", selectedDate);
                }
            }
        });

    }

    $.fn.initQueryDate = function (beginDate, endDate) {

        beginDate.datepicker({
            changeMonth: true,
            numberOfMonths: 1,
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            regional: 'zh',
            onSelect: function (selectedDate) {
                //endDate.datepicker("option", "maxDate", $.fn.addDate(selectedDate, 7));
            },
            onClose: function (selectedDate) {
                endDate.datepicker("option", "minDate", selectedDate);
            }
        });

        endDate.datepicker({
            changeMonth: true,
            numberOfMonths: 1,
            showButtonPanel: true,
            dateFormat: 'yy-mm-dd',
            regional: 'zh',
            onClose: function (selectedDate) {
                beginDate.datepicker("option", "maxDate", selectedDate);
            }
        });

    }

    $.fn.addHisCity = function (cityId, cityText, cityObjectId, keywordObjectId) {
        var hisCity = new Object();
        hisCity.ID = null;
        hisCity.His_ID = cityId;
        hisCity.His_Text = cityText;

        var c = $('.pop_history_city_container ul').find('a[data-id="' + cityId + '"]');
        $.fn.Logger(c);
        if ($('.pop_history_city_container ul') && (typeof c === 'undefined' || !c || c.length == 0)) {
            //window.$historyList.push(hisCity);
            var content = $('<li><a data-id="' + cityId + '" data-name="' + cityText + '" title="' + cityText + '" href="javascript:void(0)">' + cityText + '</a></li>');
            $('.pop_history_city_container ul').append(content);
            content.on('click', 'a', function () {
                $('#' + cityObjectId).attr('data-value', $(this).attr('data-id'));
                $('#' + cityObjectId).attr('data-name', $(this).attr('data-name'));
                $('#' + cityObjectId).val($(this).attr('data-name'));
                $('#' + keywordObjectId).val('');
                $('#popKeyWord_' + keywordObjectId).remove();
                $('#suggestKeyWord_' + keywordObjectId).remove();
                $.fn.InitKeyWordSelector(keywordObjectId, '', '', $(this).attr('data-id'));
            });
        }
    }

    $.fn.redrawHisCity = function (list) {
        var c = $('.pop_history_city_container ul');
        c.empty();
        for (var i = 0; i < list.length; i++) {
            var _city = list[i];
            var content = $('<li><a data-id="' + _city.His_ID + '" data-name="' + _city.His_Text + '" title="' + _city.His_Text + '" href="javascript:void(0)">' + _city.His_Text + '</a></li>');
            c.append(content);
        }
        /*
        c.on('click', 'a', function () {
            $('#' + cityObjectId).attr('data-value', $(this).attr('data-id'));
            $('#' + cityObjectId).attr('data-name', $(this).attr('data-name'));
            $('#' + cityObjectId).val($(this).attr('data-name'));
            $('#' + keywordObjectId).val('');
            $('#popKeyWord_' + keywordObjectId).remove();
            $('#suggestKeyWord_' + keywordObjectId).remove();
            $.fn.InitKeyWordSelector(keywordObjectId, '', '', $(this).attr('data-id'));
        });
        */
        /*
        $.ajax({
            type: "POST",
            url: '/HotelList/GetSearchHis',
            data: {
                timestamp: new Date().getTime()
            },
            success: function (data) {
            }
        });
        */
    }

    $.fn.convertDate = function (date) {
        return eval('new Date(' + date.replace(/\d+(?=-[^-]+$)/, function (a) {
            return parseInt(a, 10) - 1;
        }).match(/\d+/g) + ')');
    }

    $.fn.addDate = function (date, day) {
        var d = $.fn.convertDate(date);
        return new Date(d.getFullYear(), d.getMonth(), (d.getDate()) + day);
    }

    $.fn.addDay = function (d, day) {
        return new Date(d.getFullYear(), d.getMonth(), (d.getDate()) + day);
    }

    $.fn.format = function (d, fmt) {
        var o = {
            "M+": d.getMonth() + 1, //月份 
            "d+": d.getDate(), //日 
            "h+": d.getHours(), //小时 
            "m+": d.getMinutes(), //分 
            "s+": d.getSeconds(), //秒 
            "q+": Math.floor((d.getMonth() + 3) / 3), //季度 
            "S": d.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (d.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    $.fn.getWeek = function (d) {
        var week;
        if (d.getDay() == 0) week = $.i18n.prop('label.common.week.sunday');
        else if (d.getDay() == 1) week = $.i18n.prop('label.common.week.monday');
        else if (d.getDay() == 2) week = $.i18n.prop('label.common.week.tuesday');
        else if (d.getDay() == 3) week = $.i18n.prop('label.common.week.wednesday');
        else if (d.getDay() == 4) week = $.i18n.prop('label.common.week.thursday');
        else if (d.getDay() == 5) week = $.i18n.prop('label.common.week.friday');
        else if (d.getDay() == 6) week = $.i18n.prop('label.common.week.saturday');
        return week;
    }

    //计算时间差天数
    $.fn.getDateDiff = function (startDateStr, endDateStr) {
        var startDate = $.fn.convertDate(startDateStr);
        var endDate = $.fn.convertDate(endDateStr);
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    $.fn.getJsonDate = function (jDate) {
        return new Date(eval(jDate.replace(/\/Date\((\d+)\)\//gi, "new Date($1)")))
    }

    // 比较开始时间和结束时间 
    // beginDate yyyy-mm-dd
    // endDate yyyy-mm-dd
    $.fn.compareDate = function (startDate, endDate) {
        var sd = $.fn.convertDate(startDate);
        var ed = $.fn.convertDate(endDate);
        return sd.getTime() > ed.getTime();
    }

    /**
     * 前往详情页
     */
    $.fn.forwardToHotelDetail = function (cityId, cityText, startDate, endDate, hotelCode, keywordId, keywordText, mark, pointType) {
        $('<form action="/HotelDetail/Index" method="POST" target="_blank"></form>')
                        .append('<input type="hidden" id="hotelcode" name="hotelcode" value="' + hotelCode + '" />')
                        .append('<input type="hidden" id="startDate" name="startDate" value="' + startDate + '" />')
                        .append('<input type="hidden" id="endDate" name="endDate" value="' + endDate + '" />')
                        .append('<input type="hidden" id="cityId" name="cityId" value="' + cityId + '" />')
                        .append('<input type="hidden" id="cityText" name="cityText" value="' + cityText + '" />')
                        .append('<input type="hidden" id="keywordId" name="keywordId" value="' + keywordId + '" />')
                        .append('<input type="hidden" id="keywordText" name="keywordText" value="' + keywordText + '" />')
                        .append('<input type="hidden" id="mark" name="mark" value="' + mark + '" />')
                        .append('<input type="hidden" id="pointType" name="pointType" value="' + pointType + '" />')
                        .appendTo('body').submit().remove();
    }

    /**
     * 前往订单预订页面
     */
    $.fn.forwardToHotelOrder = function (hotelCode, activityCode, roomType, startDate, endDate, isSpecial, totaldays) {
        $('<form action="/HotelOrder/Index" method="POST" target="_blank"></form>')
                        .append('<input type="hidden" id="hotelCode" name="hotelCode" value="' + hotelCode + '" />')
                        .append('<input type="hidden" id="activityCode" name="activityCode" value="' + activityCode + '" />')
                        .append('<input type="hidden" id="startDate" name="startDate" value="' + startDate + '" />')
                        .append('<input type="hidden" id="endDate" name="endDate" value="' + endDate + '" />')
                        .append('<input type="hidden" id="roomType" name="roomType" value="' + roomType + '" />')
                        .append('<input type="hidden" id="isSP" name="isSP" value="' + isSpecial + '" />')
                        .append('<input type="hidden" id="toteldays" name="totaldays" value="' + totaldays + '" />')
                        .appendTo('body').submit().remove();
    }

    /**
     * 前往订单银联支付输入银行卡界面
     */
    $.fn.forwardToUnionPayIndex = function (orderCode, totalMoney) {
        $('<form action="/UnionPay/Index" method="POST"></form>')
                       .append('<input type="hidden" id="orderCode" name="orderCode" value="' + orderCode + '" />')
                       .append('<input type="hidden" id="totalMoney" name="totalMoney" value="' + totalMoney + '" />')
                       .appendTo('body').submit().remove();
    }

    /**
     * (储值卡充值)前往订单银联支付输入银行卡界面
     */
    $.fn.storedToUnionPayIndex = function (operateNo, payType, rechargeMoney) {
        $('<form action="/UnionPay/StoredIndex" method="POST"></form>')
                       .append('<input type="hidden" id="operateNo" name="operateNo" value="' + operateNo + '" />')
                       .append('<input type="hidden" id="payType" name="payType" value="' + payType + '" />')
                       .append('<input type="hidden" id="rechargeMoney" name="rechargeMoney" value="' + rechargeMoney + '" />')
                       .appendTo('body').submit().remove();
    }

    /**
     * 前往订单银联支付输入银行卡界面(信用卡 首次支付)
     */
    $.fn.forwardToUnionPayBindCardNo = function (parentResvNo, bankCardNo, payMoney) {
        $('<form action="/UnionPay/BindCardNo" method="POST"></form>')
                       .append('<input type="hidden" id="orderNumber" name="orderNumber" value="' + parentResvNo + '" />')
                       .append('<input type="hidden" id="bankCard" name="bankCard" value="' + bankCardNo + '" />')
                       .append('<input type="hidden" id="payMoney" name="payMoney" value="' + payMoney + '" />')
                       .appendTo('body').submit().remove();
    }
    /**
 * (储值卡充值)前往订单银联支付输入银行卡界面(信用卡 首次支付)
 */
    $.fn.storedToUnionPayBindCardNo = function (parentResvNo, bankCardNo, payMoney,payType) {
        $('<form action="/UnionPay/StoredBindCardNo" method="POST"></form>')
                       .append('<input type="hidden" id="orderNumber" name="orderNumber" value="' + parentResvNo + '" />')
                       .append('<input type="hidden" id="bankCard" name="bankCard" value="' + bankCardNo + '" />')
                       .append('<input type="hidden" id="payMoney" name="payMoney" value="' + payMoney + '" />')
                       .append('<input type="hidden" id="payType" name="payType" value="' + payType + '" />')
                       .appendTo('body').submit().remove();
    }
    /**
     * 前往订单银联支付输入银行卡界面（借记卡 首次支付）
     */
    $.fn.forwardToUnionPayBindDebitCardNo = function (parentResvNo, bankCardNo, payMoney) {
        $('<form action="/UnionPay/BindDebitCardNo" method="POST"></form>')
                       .append('<input type="hidden" id="orderNumber" name="orderNumber" value="' + parentResvNo + '" />')
                       .append('<input type="hidden" id="bankCard" name="bankCard" value="' + bankCardNo + '" />')
                       .append('<input type="hidden" id="payMoney" name="payMoney" value="' + payMoney + '" />')
                       .appendTo('body').submit().remove();
    }
    /**
    * (储值卡充值)前往订单银联支付输入银行卡界面（借记卡 首次支付）
    */
    $.fn.storedToUnionPayBindDebitCardNo = function (parentResvNo, bankCardNo, payMoney,payType) {
        $('<form action="/UnionPay/StoredBindDebitCardNo" method="POST"></form>')
                       .append('<input type="hidden" id="orderNumber" name="orderNumber" value="' + parentResvNo + '" />')
                       .append('<input type="hidden" id="bankCard" name="bankCard" value="' + bankCardNo + '" />')
                       .append('<input type="hidden" id="payMoney" name="payMoney" value="' + payMoney + '" />')
                       .append('<input type="hidden" id="payType" name="payType" value="' + payType + '" />')
                       .appendTo('body').submit().remove();
    }
    /**
     * 前往订单银联支付输入银行卡界面(信用卡 非首次支付)
     */
    $.fn.forwardToUnionPayConfirmNonpaymentFirst = function (parentResvNo, cardBank, bankCardNo, payMoney, phone, redirectType) {
        $('<form action="/UnionPay/ConfirmNonpaymentFirst" method="POST"></form>')
                       .append('<input type="hidden" id="orderNumber" name="orderNumber" value="' + parentResvNo + '" />')
                       .append('<input type="hidden" id="bankName" name="bankName" value="' + cardBank + '" />')
                       .append('<input type="hidden" id="bankCard" name="bankCard" value="' + bankCardNo + '" />')
                       .append('<input type="hidden" id="payMoney" name="payMoney" value="' + payMoney + '" />')
                       .append('<input type="hidden" id="phoneNum" name="phoneNum" value="' + phone + '" />')
                       .append('<input type="hidden" id="redirectType" name="redirectType" value="' + redirectType + '" />')
                       .appendTo('body').submit().remove();
    }
    /**
    * (储值卡充值)前往订单银联支付输入银行卡界面(信用卡 非首次支付)
    */
    $.fn.storedToUnionPayConfirmNonpaymentFirst = function (operateNo, cardBank, bankCardNo, payMoney, phone, redirectType, payType) {
        $('<form action="/UnionPay/StoredNonpaymentFirst" method="POST"></form>')
                       .append('<input type="hidden" id="operateNo" name="operateNo" value="' + operateNo + '" />')
                       .append('<input type="hidden" id="bankName" name="bankName" value="' + cardBank + '" />')
                       .append('<input type="hidden" id="bankCard" name="bankCard" value="' + bankCardNo + '" />')
                       .append('<input type="hidden" id="payMoney" name="payMoney" value="' + payMoney + '" />')
                       .append('<input type="hidden" id="phoneNum" name="phoneNum" value="' + phone + '" />')
                       .append('<input type="hidden" id="redirectType" name="redirectType" value="' + redirectType + '" />')
                       .append('<input type="hidden" id="payType" name="payType" value="' + payType + '" />')
                       .appendTo('body').submit().remove();
    }
    /**
     * 前往订单银联支付输入银行卡界面（借记卡 非首次支付）
     */
    $.fn.forwardToUnionPayConfirmNonpaymentFirstDebit = function (parentResvNo, cardBank, bankCardNo, payMoney, phone, redirectType) {
        $('<form action="/UnionPay/ConfirmNonpaymentFirstDebit" method="POST"></form>')
                       .append('<input type="hidden" id="orderNumber" name="orderNumber" value="' + parentResvNo + '" />')
                       .append('<input type="hidden" id="bankName" name="bankName" value="' + cardBank + '" />')
                       .append('<input type="hidden" id="bankCard" name="bankCard" value="' + bankCardNo + '" />')
                       .append('<input type="hidden" id="payMoney" name="payMoney" value="' + payMoney + '" />')
                       .append('<input type="hidden" id="phoneNum" name="phoneNum" value="' + phone + '" />')
                       .append('<input type="hidden" id="redirectType" name="redirectType" value="' + redirectType + '" />')
                       .appendTo('body').submit().remove();
    }

    /**
    *(储值卡充值) 前往订单银联支付输入银行卡界面（借记卡 非首次支付）
    */
    $.fn.storedToUnionPayConfirmNonpaymentFirstDebit = function (operateNo, cardBank, bankCardNo, rechargeMoney, phone, redirectType, payType) {
        $('<form action="/UnionPay/StoredNonpaymentFirstDebit" method="POST"></form>')
                       .append('<input type="hidden" id="operateNo" name="operateNo" value="' + operateNo + '" />')
                       .append('<input type="hidden" id="bankName" name="bankName" value="' + cardBank + '" />')
                       .append('<input type="hidden" id="bankCard" name="bankCard" value="' + bankCardNo + '" />')
                       .append('<input type="hidden" id="rechargeMoney" name="rechargeMoney" value="' + rechargeMoney + '" />')
                       .append('<input type="hidden" id="phoneNum" name="phoneNum" value="' + phone + '" />')
                       .append('<input type="hidden" id="redirectType" name="redirectType" value="' + redirectType + '" />')
                       .append('<input type="hidden" id="payType" name="payType" value="' + payType + '" />')
                       .appendTo('body').submit().remove();
    }

    /**
     * 前往酒店预订列表
     */
    $.fn.forwardToHotelList = function (cityId, cityText, startDate, endDate, hotelCode, keywordId, keywordText, mark, pointType, lineId) {
        $('<form action="/HotelList" method="POST"></form>')
                        .append('<input type="hidden" id="hotelcode" name="hotelcode" value="' + hotelCode + '" />')
                        .append('<input type="hidden" id="startDate" name="startDate" value="' + startDate + '" />')
                        .append('<input type="hidden" id="endDate" name="endDate" value="' + endDate + '" />')
                        .append('<input type="hidden" id="cityId" name="cityId" value="' + cityId + '" />')
                        .append('<input type="hidden" id="cityText" name="cityText" value="' + cityText + '" />')
                        .append('<input type="hidden" id="keywordId" name="keywordId" value="' + keywordId + '" />')
                        .append('<input type="hidden" id="keywordText" name="keywordText" value="' + keywordText + '" />')
                        .append('<input type="hidden" id="mark" name="mark" value="' + mark + '" />')
                        .append('<input type="hidden" id="pointType" name="pointType" value="' + pointType + '" />')
                        .append('<input type="hidden" id="lineId" name="lineId" value="' + lineId + '" />')
                        .appendTo('body').submit().remove();
    }

    /**
     * 前往酒店地图预订
     */
    $.fn.forwardToHotelMap = function (cityId, cityText, startDate, endDate, hotelCode, keywordId, keywordText, mark, pointType) {
        $('<form action="/HotelMap" method="POST"></form>')
                        .append('<input type="hidden" id="hotelcode" name="hotelcode" value="' + hotelCode + '" />')
                        .append('<input type="hidden" id="startDate" name="startDate" value="' + startDate + '" />')
                        .append('<input type="hidden" id="endDate" name="endDate" value="' + endDate + '" />')
                        .append('<input type="hidden" id="cityId" name="cityId" value="' + cityId + '" />')
                        .append('<input type="hidden" id="cityText" name="cityText" value="' + cityText + '" />')
                        .append('<input type="hidden" id="keywordId" name="keywordId" value="' + keywordId + '" />')
                        .append('<input type="hidden" id="keywordText" name="keywordText" value="' + keywordText + '" />')
                        .append('<input type="hidden" id="mark" name="mark" value="' + mark + '" />')
                        .append('<input type="hidden" id="pointType" name="pointType" value="' + pointType + '" />')
                        .appendTo('body').submit().remove();
    }
    /**
    * 前往帮助中心
    */
    $.fn.forwardToHelp = function () {
        $('<form action="/Help" method="POST"></form>').appendTo('body').submit().remove();
    }
    $.fn.checkHotelSearch = function (cityObject, keywordObject, checkInObject, checkOutObject) {
        var cityId = cityObject.attr('data-value');
        var cityText = cityObject.val();
        var checkIn = checkInObject.val();
        var checkOut = checkOutObject.val();
        if (cityId == '' || cityText == '') {
            $.fn.showAlert($.i18n.prop('label.common.error.city.null'));
            cityObject.focus();
            return false;
        }
        {
            var isFound = false;
            for (var k in popCityMap) {
                var c = popCityMap[k];
                if (c && c.ID.replace("_c", "") == cityId.replace("_c", "")) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                for (var k in suggestCityMap) {
                    var c = suggestCityMap[k];
                    if (c && c.ID.replace("_c", "") == cityId.replace("_c", "")) {
                        isFound = true;
                        break;
                    }
                }
            }
            //验证海外城市 2017-06-14 ww
            if (!isFound)
            {
                for (var k in overseasCityMap)
                {
                    var c = overseasCityMap[k];
                    if (c && c.ID == cityId.replace("_c", "")) {
                        isFound = true;
                        break;
                    }
                }
                
            }
            
            if (!isFound) {//查找搜索历史
                for (var k in window.$historyList) {
                    var c = window.$historyList[k];
                    if (c && c.His_ID && c.His_ID.replace("_c", "") == cityId.replace("_c", "")) {
                        isFound = true;
                        break;
                    }

                }
            }
            //如果是默认城市上海则可以进行查找
            if (cityId === '226') {
                isFound = true;
            }

            if (!isFound) {
                $.fn.showAlert($.i18n.prop('label.common.error.city.null'));
                cityObject.focus();
                return false;
            }
        }

        if (!checkIn || checkIn == '') {
            $.fn.showAlert($.i18n.prop('label.common.error.begin.null'));
            checkInObject.focus();
            return false;
        }
        if (!checkOut || checkOut == '') {
            $.fn.showAlert($.i18n.prop('label.common.error.end.null'));
            checkOutObject.focus();
            return false;
        }
        return true;
    }

    $.fn.searchHotelList = function (cityObject, beginDateObject, endDateObject, keywordObject) {
        if (!cityObject || !cityObject.val() || !cityObject.attr('data-value')) {
            alert($.i18n.prop('label.common.error.city.null'));
            cityObject.focus();
            return;
        }
        if (!beginDateObject || !beginDateObject.val()) {
            alert($.i18n.prop('label.common.error.begin.null'));
            beginDateObject.focus();
            return;
        }
        if (!endDateObject || !endDateObject.val()) {
            alert($.i18n.prop('label.common.error.end.null'));
            endDateObject.focus();
            return;
        }
        $.fn.ValidateQueryDate(beginDateObject, endDateObject);
        var cityId = cityObject.attr('data-value');
        var cityText = cityObject.val();
        var startDate = beginDateObject.val();
        var endDate = endDateObject.val();
        var keywordId = keywordObject.attr('data-id');
        var keywordText = keywordObject.val();
        var mark = keywordObject.attr('data-mark');
        var pointType = keywordObject.attr('data-pointType');
        var lineId = keywordObject.attr('data-lineId');

        $.fn.forwardToHotelList(cityId, cityText, startDate, endDate, '', keywordId, keywordText, mark, pointType, lineId)
    }

    $.fn.forwardToHotelTopic = function (hotelCode, hotelName) {
        $('<form action="/HotelDetail/HotelTopic" method="POST" target="_blank"></form>')
                        .append('<input type="hidden" id="hotelCode" name="hotelCode" value="' + hotelCode + '" />')
                        .append('<input type="hidden" id="hotelName" name="hotelName" value="' + hotelName + '" />')
                        .appendTo('body').submit().remove();
    }

    $.fn.SearchRecommendHotelList = function (cityId, cityText) {
        var d = new Date();
        $.ajax({
            type: "POST",
            url: '/HotelList/GetHotelListForRecommend',
            data: {
                cityId: cityId,
                cityText: cityText,
                startDate: $.fn.format(d, 'yyyy-MM-dd'),
                endDate: $.fn.format(new Date(d.getFullYear(), d.getMonth(), d.getDate() + 1), 'yyyy-MM-dd'),
                timestamp: new Date().getTime()
            },
            beforeSend: function () {
                $('.gl_list_main ul').empty();
                $('#RecommendLoading').show();
            },
            success: function (data) {
                $('.gl_list_main ul').empty();
                $.fn.Logger(data);
                $('#RecommendLoading').hide();
                var cityId = data.cityId;
                var cityText = data.cityText;
                var startDate = data.startDate;
                var endDate = data.endDate;
                function getDetailUrl(hotelCode) {
                    return '/HotelDetail/Index?hotelcode=' + _data.HotelCode + '';
                }

                /**
                 * 前往酒店预订
                 */
                function forwardToHotelOrder(hotelCode) {
                    $('<form action="/HotelOrder/Index" method="POST"></form>')
                        .append('<input type="hidden" id="hotelCode" name="hotelCode" value="' + hotelCode + '" />')
                        .append('<input type="hidden" id="startDate" name="startDate" value="' + startDate + '" />')
                        .append('<input type="hidden" id="endDate" name="endDate" value="' + endDate + '" />')
                        .appendTo('body').submit().remove();
                }

                /**
                 * 前往酒店详情
                 */
                function forwardToHotelDetail(hotelCode, anchor) {
                    if (typeof anchor !== '' && anchor && anchor != '') {
                        $('<form action="/HotelDetail/Index#' + anchor + '" method="POST" target="_blank"></form>')
                            .append('<input type="hidden" id="hotelcode" name="hotelcode" value="' + hotelCode + '" />')
                            .appendTo('body').submit().remove();
                    } else {
                        $.fn.forwardToHotelDetail(cityId, cityText, startDate, endDate, hotelCode, '', '', '', '');
                    }
                }
                /*
                m1   wifi
m2   停车场
m3   会议室
m4   外宾
m5 早餐
                */
                function getServiceMap(KeyId) {
                    var m = new Object();
                    if (KeyId == 41) {
                        m.name = $.i18n.prop('label.service.parking');
                        m.icon = 'm2';
                    } else if (KeyId == 215) {
                        m.name = $.i18n.prop('label.service.wifi');
                        m.icon = 'm1';
                    } else if (KeyId == 51) {//餐厅提供早餐
                        m.name = $.i18n.prop('label.service.res');
                        m.icon = 'm5';
                    } else if (KeyId == 91) {//提供接待外宾
                        m.name = $.i18n.prop('label.service.foreign');
                        m.icon = 'm4';
                    } else if (KeyId == 152) {//是否有会议室
                        m.name = $.i18n.prop('label.service.meeting');
                        m.icon = 'm3';
                    }
                    return m;
                }

                function getPriceLabel(IsFullRoomStatus, LowestPrice) {
                    if (IsFullRoomStatus == 1) {
                        return '<div class="oa"><span class="j3"></span><span class="j2">' + LowestPrice + '</span><span class="j1"></span></div>';
                    } else {
                        return '<div class="oa"><span class="j3">￥</span><span class="j2">' + LowestPrice + '</span><span class="j1">' + $.i18n.prop('label.common.set') + '</span></div>';
                    }
                }
                ///HotelDetail?hotelcode=121229&startDate=2014-04-25&endDate=2014-04-26&cityId=226&cityText=上海&keywordId=&keywordText=
                //cityId = cityId, cityText = cityText,satrtDate=satrtDate,endDate=endDate, HotelList = list

                var content = $('.gl_list_main ul');

                if (!data.HotelList || data.HotelList.length == 0) {
                    $('.gl_list_main ul').empty();
                    $('.gl_list_main ul').html('<div>' + $.i18n.prop('label.hotel.list.nodata') + '</div>');

                    return;
                }
                for (var i = 0; i < data.HotelList.length; i++) {
                    var _data = data.HotelList[i];
                    var hc = $('<li class="clearfix"></li>');
                    content.append(hc);
                    //hc.append('<div class="list_img_left"><a data-id="' + _data.HotelCode + '" href="javascript:void(0);"><img src="' + _data.HotelImg + '"></a></div>');
                    hc.append('<div class="list_img_left"><a data-id="' + _data.HotelCode + '" href="javascript:void(0);"><img src="' + _data.HotelImg_200_150 + '"></a></div>');

                    var lc = $('<div class="list_text_center"></div>');
                    hc.append(lc);

                    //lc.append('<div class="ia"><a data-id="' + _data.HotelCode + '" href="/HotelDetail/Index?hotelcode=' + _data.HotelCode + '"  target="_blank" title="' + _data.HotelName + '">' + _data.HotelName + '</a></div>');
                    lc.append('<div class="ia"><a data-id="' + _data.HotelCode + '" href="javascript:void(0);" title="' + _data.HotelName + '">' + _data.HotelName + '</a></div>');
                    lc.append('<div class="io" title="' + _data.Address + '"><i></i>' + _data.Address + '</div>');
                    lc.append('<div class="ib"><i></i>' + _data.Phone + '</div>');
                    //lc.append('<div class="ic"><span>' + _data.CommentScore + '分</span>/5' + $.i18n.prop('label.hotel.list.point') + '  ' + $.i18n.prop('label.hotel.list.from') + _data.CommentCount + $.i18n.prop('label.hotel.list.person') + $.i18n.prop('label.hotel.list.comments') + '</div>');
                    lc.append($.i18n.prop('label.hotel.list.comments', _data.CommentScore, _data.CommentCount, _data.HotelCode));

                    $('.ic', lc).on('click', 'a', function () {
                        var hotelCode = $(this).attr('data-hotelcode');
                        $.fn.Logger('hotelCode:' + hotelCode);
                        forwardToHotelDetail(hotelCode, 'dianpin');
                    });

                    var ls = $('<div class="is"></div>');
                    lc.append(ls);

                    var lsc = $('<ul></ul>');
                    ls.append(lsc);
                    for (var j = 0; j < _data.HotelServiceList.length; j++) {
                        var _s = _data.HotelServiceList[j];
                        var m = getServiceMap(_s.KeyId);
                        if (m && m.name) {
                            lsc.append('<li class="' + m.icon + '"><a href="javascript:void(0);" title="' + m.name + '"></a></li>');
                        }
                    }

                    var lr = $('<div class="list_Price_right"></div>');
                    hc.append(lr);

                    lr.append(getPriceLabel(_data.IsFullRoomStatus, _data.LowestPrice));

                    lr.append('<div class="ob"><a data-id="' + _data.HotelCode + '" href="javascript:void(0);"><span>' + $.i18n.prop('label.common.book') + '</a></span></div>');

                    lr.find('.ob a').click(function () {
                        forwardToHotelDetail($(this).attr('data-id'));
                    });

                    hc.find('.list_img_left a,.ia a').click(function () {
                        forwardToHotelDetail($(this).attr('data-id'));
                    });

                }
            }
        });
    }

    $.fn.loadingNearbyData = function (cityId, cityText, mark) {
        $.ajax({
            type: "POST",
            //url: '/HotelList/GetLocationByCityIdMark?cityId=' + cityId + '&mark=' + mark,
            url: '/HotelList/GetLocationByCityIdMark',
            data: {
                cityId: cityId,
                mark: mark,
                timestamp: new Date().getTime()
            },
            success: function (data) {
                var startDate = $('#txtCheckIn').val();
                var endDate = $('#txtCheckOut').val();

                var content = $('.nearby').find('div[data-mark="' + mark + '"]');
                $('.nearby div').css('display', 'none');
                if (content && content[0]) {
                    content.css('display', '');
                } else {
                    content = $('<div class="fl_one" data-mark="' + mark + '"></div>');
                    $('.nearby').append(content);

                    var uc = $('<ul class="first_list"></ul>');
                    content.append(uc);

                    for (var i = 0; i < data.length; i++) {
                        var _data = data[i];
                        var id = _data.Pointxy;
                        if (mark == 9 || mark == 10) {
                            id = _data.ID;
                        } else {
                            id = $.trim(_data.Pointxy.split(',')[0]) + ',' + $.trim(_data.Pointxy.split(',')[1]);
                        }
                        uc.append('<li><a rel="nofollow" href="javascript:;" name="locationId" data-id="' + id + '" data-type="' + _data.PointType + '" data-mark="' + mark + '" title="' + _data.Name + '">' + _data.Name + '</a></li>');
                    }

                    if (mark == 10) {//地铁
                        if (data.length == 0) {
                            return;
                        }
                        uc.on('click', 'a', function () {
                            var _this = $(this);
                            //移除class属性 表示重新选中
                            $('.first_list li a').removeClass();
                            var _p = _this.parent().parent().parent();
                            var c = $('.boh[data-id="' + _this.attr("data-id") + '"]', _p);
                            $('.boh', _p).css('display', 'none');
                            if (c && c[0]) {
                                c.css('display', '');
                                _this.addClass('ui-tab');
                            } else {
                                _this.addClass('ui-tab');
                                $.fn.loadingNearbySubwayData(cityId, cityText, startDate, endDate, mark, _this.attr("data-id"));
                            }
                        });
                        //添加class属性 表示选择
                        $('.nearby').find('div[data-mark="10"]').find('ul li:first a').addClass('ui-tab');
                        $.fn.loadingNearbySubwayData(cityId, cityText, startDate, endDate, mark, data[0].ID);

                    } else {
                        uc.on('click', 'a', function () {
                            var _this = $(this);
                            //alert('mark:' + mark + ',id:' + _this.attr('data-id'));
                            $.fn.forwardToHotelList(cityId, cityText, startDate, endDate, '', _this.attr('data-id'), _this.html(), mark, _this.attr('data-type'), '');
                        });
                    }
                }

            }
        });

    }

    //加载地铁数据
    $.fn.loadingNearbySubwayData = function (cityId, cityText, startDate, endDate, mark, lineId) {
        $.ajax({
            type: "POST",
            //url: '/HotelList/GetLocationByCityIdMark?cityId=' + cityId + '&standId=' + lineId,
            url: '/HotelList/GetLocationByCityIdMark',
            data: {
                cityId: cityId,
                standId: lineId,
                timestamp: new Date().getTime()
            },
            success: function (d) {
                var u = $('<ul class="boh" data-mark="' + mark + '" data-id="' + lineId + '"></ul>');
                $('.nearby').find('div[data-mark="' + mark + '"]').append(u);
                for (var j = 0; j < d.length; j++) {
                    var _d = d[j];
                    var id = $.trim(_d.Pointxy.split(',')[0]) + ',' + $.trim(_d.Pointxy.split(',')[1]);

                    u.append('<li><a href="javascript:;" name="locationId" data-id="' + id + '" data-type="' + _d.PointType + '" data-mark="' + mark + '" title="' + _d.Name + '" data-lineId="' + $.trim(lineId) + '">' + _d.Name + '</a></li>');
                }
                //点击地铁
                u.on('click', 'a', function () {
                    var _this = $(this);
                    //alert('mark:' + mark + ',id:' + _this.attr('data-id'));
                    $.fn.forwardToHotelList(cityId, cityText, startDate, endDate, '', _this.attr('data-id'), _this.html(), mark, _this.attr('data-type'), _this.attr('data-lineId'));
                });
            }
        });
    }

    //加载周边变量
    $.fn.loadingVariableText = function (cityId) {
        var menu = $('.hot_ba_h').find('ul li a[data-mark="8"]');
        $.ajax({
            type: "POST",
            //url: '/HotelList/GetVariableByCity?cityId=' + cityId,
            url: '/HotelList/GetVariableByCity',
            data: {
                cityId: cityId,
                timestamp: new Date().getTime()
            },
            success: function (data) {
                menu.text(data);
            }
        });
    }

    //加载列表周边变量
    $.fn.loadingListVariableText = function (cityId) {
        $.ajax({
            type: "POST",
            url: '/HotelList/GetVariableByCity',
            data: {
                cityId: cityId,
                timestamp: new Date().getTime()
            },
            success: function (data) {
                $('div', $('.menu li[data-mark="8"]')).html(data);
            }
        });
    }


    //初始化城市控件
    //elementId 页面元素的id
    //nearbyId 可以为空，城市周边组件的id，主要主页面上使用
    //keywordId 关键字组件的id
    //defaultCityId 默认的城市id，如果为空则默认为上海的城市id
    //defaultCityText 默认的城市名称，如果为空则默认为上海
    //使用：在页面js中直接$.fn.InitCitySelector('txtCityId')
    $.fn.InitCitySelector = function (elementId, nearbyId, keywordId, defaultCityId, defaultCityText, align, isGlobal) {
        defaultCityId = typeof defaultCityId !== 'undefined' && defaultCityId != '' ? defaultCityId : globalCityId;
        defaultCityText = typeof defaultCityText !== 'undefined' && defaultCityText != '' ? defaultCityText : globalCityText;

        if (!defaultCityId) {
            globalCityId = defaultCityId;
        }
        if (!defaultCityText) {
            globalCityText = defaultCityText;
        }
        $('#' + elementId).attr('data-value', defaultCityId);
        $('#' + elementId).attr('data-name', defaultCityText);
        $('#' + elementId).val(defaultCityText);
        $.ajax({
            type: "POST",
            url: '/HotelList/GetCityList',
            data: {
                timestamp: new Date().getTime()
            },
            success: function (data) {

                if (window.location.href.indexOf('/HotelList') > 0) {//如果是酒店列表预订页面则初始化tab页
                    //alert('loadingLocationTab');
                    $('.ui-tab-bd').find('.ui-tab-content').each(function (index) {
                        //  var menu = $($("#demo1 .ui-tab-container .ui-tab-list li[data-mark='" + $(this).attr('data-mark') + "']"));//.get(index + 1));
                        var menu = $($("#demo0  li[data-mark='" + $(this).attr('data-mark') + "']"));//.get(index + 1));
                        //alert($(this).attr('data-mark') + '   ' + menu.attr('data-mark'));
                        loadingLocationTab(globalCityId, $(this).attr('data-mark'), $(this), menu);
                    });
                }

                var cities = [];
                var hotList = [];
                var hotCityIds = '';
                var historyCityIds = '';
                var bcdeCityIds = '';
                var fghkCityIds = '';
                var klmnCityIds = '';
                var pqrsCityIds = '';
                var twxyzCityIds = '';
                var datachina = data.China.CityList;
                //海外酒店 ww 2017-06-15
                var overseas = data.OverSeas;
                
                for (var i = 0; i < datachina.length; i++) {
                    popCityMap['p_city_' + datachina[i].ID] = datachina[i];
                    //Cityid==null
                    cities.push(new Array(datachina[i].ID, datachina[i].Name, datachina[i].ShortName, datachina[i].ShortName, datachina[i].Cityid, datachina[i].FullSpell));
                    if (datachina[i].FullSpell && datachina[i].FullSpell.length > 0) {
                        var prefix = datachina[i].FullSpell.substring(0, 1).toUpperCase();
                        if ('ABCDE'.indexOf(prefix) >= 0) {
                            bcdeCityIds += i + ',';
                        } else if ('FGHJ'.indexOf(prefix) >= 0) {
                            fghkCityIds += i + ',';
                        } else if ('KLMN'.indexOf(prefix) >= 0) {
                            klmnCityIds += i + ',';
                        } else if ('PQRS'.indexOf(prefix) >= 0) {
                            pqrsCityIds += i + ',';
                        } else if ('TWXYZ'.indexOf(prefix) >= 0) {
                            twxyzCityIds += i + ',';
                        }
                    } else {
                        twxyzCityIds += i + ',';
                    }

                    if (datachina[i].IsHotCity && datachina[i].IsHotCity === 1) {
                        datachina[i].Index = i;
                        hotList.push(datachina[i]);
                    }
                }
                //for (var i = 0; i < data.length; i++) {
                //    popCityMap['p_city_' + data[i].ID] = data[i];
                //    cities.push(new Array(data[i].ID, data[i].Name, data[i].ShortName, data[i].ShortName, data[i].Cityid, data[i].FullSpell));
                //    if (data[i].FullSpell && data[i].FullSpell.length > 0) {
                //        var prefix = data[i].FullSpell.substring(0, 1).toUpperCase();
                //        if ('ABCDE'.indexOf(prefix) >= 0) {
                //            bcdeCityIds += i + ',';
                //        } else if ('FGHJ'.indexOf(prefix) >= 0) {
                //            fghkCityIds += i + ',';
                //        } else if ('KLMN'.indexOf(prefix) >= 0) {
                //            klmnCityIds += i + ',';
                //        } else if ('PQRS'.indexOf(prefix) >= 0) {
                //            pqrsCityIds += i + ',';
                //        } else if ('TWXYZ'.indexOf(prefix) >= 0) {
                //            twxyzCityIds += i + ',';
                //        }
                //    } else {
                //        twxyzCityIds += i + ',';
                //    }

                //    if (data[i].IsHotCity && data[i].IsHotCity === 1) {
                //        data[i].Index = i;
                //        hotList.push(data[i]);
                //    }
                //}

                hotList.sort(function (a, b) { return a.SortHot - b.SortHot; });
                for (var i = 0; i < hotList.length; i++) {
                    hotCityIds += hotList[i].Index + ',';
                }
                //alert(hotList.length + ",hotCityIds:" + hotCityIds);
                var tabs = new Object();
                tabs[$.i18n.prop('label.city.hotcity')] = hotCityIds.split(",");
                tabs['ABCDE'] = bcdeCityIds.split(',');
                tabs['FGHJ'] = fghkCityIds.split(',');
                tabs['KLMN'] = klmnCityIds.split(',');
                tabs['PQRS'] = pqrsCityIds.split(',');
                tabs['TWXYZ'] = twxyzCityIds.split(',');

                $.ajax({
                    type: "POST",
                    url: '/HotelList/GetSearchHis',
                    data: {
                        timestamp: new Date().getTime()
                    },
                    success: function (data) {
                        $.fn.Logger(data);
                        for (var i = 0; i < data.length; i++) {
                            $.fn.Logger('His_ID:' + data[i].His_ID + ',His_Text:' + data[i].His_Text + ',ID:' + data[i].ID);
                        }
                        window.$historyList = data;

                        $('#' + elementId).JCitySelector({ 'data': cities, 'tabs': tabs, 'keywordId': keywordId, align: align, isGlobal: isGlobal, 'city': overseas });
                    }
                });
            }
        });
    }

    //初始化关键字组件
    //elementId: 页面元素的id
    //defaultKeywordId：默认值为空
    //defaultKeywordText：默认值为空
    //cityId：默认值为上海
    $.fn.InitKeyWordSelector = function (elementId, defaultKeywordId, defaultKeywordText, cityId) {

        cityId = typeof cityId !== 'undefined' && cityId != '' ? cityId : globalCityId;
        defaultKeywordId = typeof defaultKeywordId !== 'undefined' && defaultKeywordId != '' ? defaultKeywordId : '';
        defaultKeywordText = typeof defaultKeywordText !== 'undefined' && defaultKeywordText != '' ? defaultKeywordText : '';

        $('#' + elementId).attr('data-value', defaultKeywordId);
        $('#' + elementId).val(defaultKeywordText);

        $.ajax({
            type: "POST",
            //url: '/HotelList/GetLocationListByCityId?cityId=' + cityId,
            url: '/HotelList/GetLocationListByCityId',
            data: {
                cityId: cityId,
                timestamp: new Date().getTime()
            },
            success: function (data) {
                var dataMap = {};
                var zoneList = [];//热门商圈
                var locationList = [];//行政区
                var slList = [];//机场火车站
                var metroList = [];//地铁线
                var subCityList = [];//下辖市/县
                //1＝机场车站 2=地铁站点/主干道 3=市中心/商业区 4=景区景点/重要场馆 5=学校/医院 6=餐饮娱乐购物 7=其他  8变量 9行政区 10 地铁 Mark
                var zoneSize = 0, slSize = 0, locationSize = 0, metroSize = 0;

                for (var i = 0; i < data.length; i++) {
                    var _data = data[i];
                    popKeyWordMap['p_keyword_' + _data.ID] = _data;

                    if (/.*[\u4e00-\u9fa5]+.*$/.test(_data.Name)) {
                        if (_data.Mark == 1) {//1＝机场车站
                            slSize = slSize + _data.Name.length;
                            if (slSize < 100) {
                                slList.push(_data);
                            }
                        } else if (_data.Mark == 9) {//9行政区
                            locationSize = locationSize + _data.Name.length;
                            if (locationSize < 100) {
                                locationList.push(_data);
                            }
                        } else if (_data.Mark == 10) {//10 地铁
                            metroSize = metroSize + _data.Name.length;
                            if (metroSize < 100) {
                                metroList.push(_data);
                            }
                        } else if (_data.Mark == 3) {//3=市中心/商业区
                            zoneSize = zoneSize + _data.Name.length;
                            if (zoneSize < 100) {
                                zoneList.push(_data);
                            }
                        }
                    } else {
                        if (_data.Mark == 1 && _data.Name) {//1＝机场车站
                            slSize = slSize + _data.Name.split(' ').length;
                            if (slSize < 100) {
                                slList.push(_data);
                            }
                        } else if (_data.Mark == 9 && _data.Name) {//9行政区
                            locationSize = locationSize + _data.Name.split(' ').length;
                            if (locationSize < 100) {
                                locationList.push(_data);
                            }
                        } else if (_data.Mark == 10 && _data.Name) {//10 地铁
                            metroSize = metroSize + _data.Name.split(' ').length;
                            if (metroSize < 100) {
                                metroList.push(_data);
                            }
                        } else if (_data.Mark == 3 && _data.Name) {//3=市中心/商业区
                            zoneSize = zoneSize + _data.Name.split(' ').length;
                            if (zoneSize < 100) {
                                zoneList.push(_data);
                            }
                        }
                    }

                }

                if (zoneList && zoneList.length > 0) {
                    dataMap.zoneList = zoneList;
                }
                if (locationList && locationList.length > 0) {
                    dataMap.locationList = locationList;
                }
                if (slList && slList.length > 0) {
                    dataMap.slList = slList;
                }
                if (metroList && metroList.length > 0) {
                    dataMap.metroList = metroList;
                }
                if (subCityList && subCityList.length > 0) {
                    dataMap.subCityList = subCityList;
                }
                dataMap.id = elementId;

                $('#' + elementId).JKeyWordSelector({ 'data': dataMap });
            }
        });

    }
})(jQuery);

//城市jQuery插件
(function ($) {
    $.JCitySelector = function (element, options) {
        //'data': '',
        // 'tabs': '',
        // 'cityId': '',
        //   'defaultText': '',
        //   'popTitleText': $.i18n.prop('label.city.pop.title'),
        //   'suggestTitleText': $.i18n.prop('label.city.suggest.title'),
        //   'suggestLength': 10,
        //   'pageLength': 5,
        //   'keywordId': '',
        //   'align': 'left',
        //   'isGlobal': true

        var e = $(element);
        var val = $.trim(e.val());
        var id = e.attr('id');
        e.attr('autocomplete', 'off');

        if (val == '' || val == options.defaultText) {
            e.val(options.defaultText).css('color', '#aaa');
        }

        var t_pop_focus = false;
        var t_suggest_focus = false;
        var t_suggest_page_click = false;
        $('body').append("<div id='pop_city_" + id + "' class='pop_city' style='display:none'><p class='pop_head'></p><ul class='list_history_label'><li>" + $.i18n.prop('label.city.history') + "</li></ul><div class='pop_history_city_container'></div><ul class='list_country'><li><a id='lable_txtContry1' class='current' href='javascript:void(0)'>" + $.i18n.prop('label.city.china') + "</a></li><li><a id='lable_txtContry2' href='javascript:void(0)'>" + $.i18n.prop('label.city.overseas') + "</a></li></ul><ul id='list_label1' class='list_label'></ul><ul id='list_label2' class='list_label' style='display:none'></ul><div id='pop_city_container1' class='pop_city_container'></div></div>");
        $('body').append("<div id='suggest_city_" + id + "' class='list_city' style='display:none'><div class='list_city_head'></div><div class='list_city_container'></div><div class='page_break'></div></div>");
        var popMain = $("#pop_city_" + id);  //城市弹出层
        var popContainer = popMain.find('.pop_city_container');  //城市名称容器

        //国家
        var lableContry = popMain.find('.list_country');
        //var labelMain = popMain.find('.list_label');//城市标签  ：热门城市  ABCD ....
        var labelMain = popMain.find('#list_label1');
        var labelMainCountry = popMain.find('#list_label2');//

        var suggestMain = $("#suggest_city_" + id);//推荐城市
        popMain.bgIframe();
        suggestMain.bgIframe();
        popInit();
        resetPosition();

        $(window).resize(function () {
            resetPosition();
        });

        e.focus(function () {
            if (t_suggest_page_click) {
                t_suggest_page_click = false;
                return true;
            }
            suggestMain.hide();
            if ($.trim($(this).val()) == options.defaultText) {
                $(this).val('').css('color', '#000');
            }
            popMain.show();
        }).click(function () {
            if (t_suggest_page_click) {
                t_suggest_page_click = false;
                return;
            }

            var selectedVal = $.trim($(this).val());
            suggestMain.hide();
            popMain.show();
        }).blur(function () {
            if (t_pop_focus == false) {
                popMain.hide();
                if (val == '' || val == options.defaultText) {
                    //e.val(options.defaultText).css('color', '#aaa');
                    e.val(e.attr('data-name')).css('color', '#aaa');
                }
            }
        });
        lableContry.on('click', 'a', function () {
            e.focus();//使焦点在输入框，避免blur事件无法触发
            t_pop_focus = true;
            var labelId = $(this).attr('id');
            lableContry.find('li a').removeClass('current');
            $(this).addClass('current');
            //alert(labelId);
            if (labelId == 'lable_txtContry1') {
                $("#list_label1").show();
                $("#list_label2").hide();
            }
            else
            {
                $("#list_label2").show();
                $("#list_label1").hide();
            }
            //popContainer.find('ul').hide();
            //$("#" + labelId + '_container').show();
        });
        labelMain.on('click', 'a', function () {
            e.focus();//使焦点在输入框，避免blur事件无法触发
            t_pop_focus = true;
            var labelId = $(this).attr('id');
            //labelMain.find('li').removeClass('current');
            //$(this).addClass('current');
            labelMain.find('li').removeClass('active');
            $(this).parent().addClass('active');
            popContainer.find('ul').hide();
            $("#" + labelId + '_container').show();
        });
        labelMainCountry.on('click', 'a', function () {
            e.focus();//使焦点在输入框，避免blur事件无法触发
            t_pop_focus = true;
            var labelId = $(this).attr('id');
            labelMainCountry.find('li').removeClass('active');
            $(this).parent().addClass('active');
            popContainer.find('ul').hide();
            $("#" + labelId + '_container').show();
        });
        //选择弹出城市框城市事件
        popContainer.on('click', 'a', function () {
            popSelected($(this));
            popMain.hide();
        });

        //
        popMain.find('.pop_history_city_container').on('click', 'a', function () {
            /*
            if (popCityMap['p_city_' + $(this).attr('id')]) {
                fillValue(popCityMap['p_city_' + $(this).attr('id')]);
            } else {
                for (var key in popCityMap) {
                    if (popCityMap[key].Cityid == $(this).attr('id')) {
                        fillValue(popCityMap[key]);
                        break;
                    }
                }
            }
            */
            onSelect($(this), false);
            popMain.hide();
        });

        popMain.mouseover(function () {
            t_pop_focus = true;
        }).mouseout(function () {
            t_pop_focus = false;
        });

        e.blur(function () {
        
            //当未选择suggest 层和 pop 层才触发该事件
            if (t_suggest_focus == false && t_pop_focus == false) {
                if ($(this).val() == '') {
                    //$(this).val(suggestMain.find('.list_city_container a.selected').children('b').text());
                    $(this).val($(this).attr('data-name')).css('color', '#aaa');
                } else {
                    var text = $(this).val();
                    $.fn.Logger('areaCityText:' + text);
                    var find = false;

                    for (var k in suggestCityMap) {
                        var city = suggestCityMap[k];
                        if (city.Name == text) {
                            var c = new Object();
                            c.ID = city.ID;
                            c.Cityid = city.ID;
                            c.Name = city.Name;
                            fillValue(c);

                            find = true;
                            break;
                        }
                    }

                    if (!find) {
                        e.val(e.attr('data-name'));
                        /*
                        e.attr('data-value', '');
                        e.attr('data-name', '');
                        $('#popKeyWord_' + options.keywordId).remove();
                        $('#suggestKeyWord_' + options.keywordId).remove();
                        */
                    }
                }
                suggestMain.hide();
            }
        }).keydown(function (event) {
            popMain.hide();
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (keyCode == 37) {//左
                prevPage();
            } else if (keyCode == 39) {//右
                nextPage();
            } else if (keyCode == 38) {//上
                prevResult();
            } else if (keyCode == 40) {//下
                nextResult();
            }
        }).keypress(function (event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (13 == keyCode) {
                if (suggestMain.find('.list_city_container a.selected').length > 0) {
                    suggestSelected(suggestMain.find('.list_city_container a.selected'));
                    suggestMain.hide();
                }
            }
        }).keyup(function (event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (keyCode != 13 && keyCode != 37 && keyCode != 39 && keyCode != 9 && keyCode != 38 && keyCode != 40) {
                //keyCode == 9是tab切换键
                queryCity();
            }
        });

        //选择Suggest城市事件
        suggestMain.find('.list_city_container').on('click', 'a', function () {
            suggestSelected($(this));
            suggestMain.hide();

        }).on('mouseover', 'a', function () {
            t_suggest_focus = true;
        }).on('mouseout', 'a', function () {
            t_suggest_focus = false;
        });

        suggestMain.find('.page_break').on('mouseover', 'a', function () {
            t_suggest_focus = true;
        }).on('mouseout', 'a', function () {
            t_suggest_focus = false;
        });

        suggestMain.find('.page_break').on('click', 'a', function (event) {
            t_suggest_page_click = true;
            e.click();
            if ($(this).attr('inum') != null) {
                setAddPage($(this).attr('inum'));
            }
        });

        function nextPage() {
            var add_cur = suggestMain.find(".page_break a.current").next();
            if (add_cur != null) {
                if ($(add_cur).attr("inum") != null) {
                    setAddPage($(add_cur).attr("inum"));
                }
            }
        }

        function prevPage() {
            var add_cur = suggestMain.find(".page_break a.current").prev();
            if (add_cur != null) {
                if ($(add_cur).attr("inum") != null) {
                    setAddPage($(add_cur).attr("inum"));
                }
            }
        }

        function nextResult() {
            var t_index = suggestMain.find('.list_city_container a').index(suggestMain.find('.list_city_container a.selected')[0]);
            suggestMain.find('.list_city_container').children().removeClass('selected');
            t_index += 1;
            var t_end = suggestMain.find('.list_city_container a').index(suggestMain.find('.list_city_container a:visible').filter(':last')[0]);
            if (t_index > t_end) {
                t_index = suggestMain.find('.list_city_container a').index(suggestMain.find('.list_city_container a:visible').eq(0));
            }
            suggestMain.find('.list_city_container a').eq(t_index).addClass('selected');
        }

        function prevResult() {

            var t_index = suggestMain.find('.list_city_container a').index(suggestMain.find('.list_city_container a.selected')[0]);
            suggestMain.find('.list_city_container').children().removeClass('selected');
            t_index -= 1;
            var t_start = suggestMain.find('.list_city_container a').index(suggestMain.find('.list_city_container a:visible').filter(':first')[0]);
            if (t_index < t_start) {
                t_index = suggestMain.find('.list_city_container a').index(suggestMain.find('.list_city_container a:visible').filter(':last')[0]);
            }
            suggestMain.find('.list_city_container a').eq(t_index).addClass('selected');
        }

        function queryCity() {
            popMain.hide();

            var self = this, $this = $(self);
            if ($this.data('QueryCityTimeout')) {
                clearTimeout($this.data('QueryCityTimeout'));
            }
            $this.data('QueryCityTimeout', setTimeout(function () {
                var value = e.val().toLowerCase();
                var key = e.val();
                if (key && $.trim(key) != "") {
                    $.ajax({
                        type: "POST",
                        url: '/HotelList/SearchCityByKey',
                        data: {
                            key: key,
                            timestamp: new Date().getTime()
                        },
                        success: function (data) {
                            var city_container = suggestMain.find('.list_city_container');
                            var isHave = false;
                            var _tmp = new Array();
                            for (var i = 0; i < data.length; i++) {
                                var _data = data[i];
                                suggestCityMap['s_city_' + _data.ID] = _data;
                                if (typeof (_data) != 'undefined') {
                                    isHave = true;
                                    _tmp.push(new Array(_data.ID, _data.CityAreaName, _data.ShortName, _data.ShortName));
                                }
                            }

                            if (isHave) {
                                city_container.empty();
                                for (var j = 0; j < _tmp.length; j++) {
                                    var _data = _tmp[j];
                                    city_container.append("<a href='javascript:void(0)' id='s_city_" + _data[0] + "' style='display:none'><span>" + _data[2] + "</span><b>" + _data[1] + "</b></a>");
                                }
                                suggestMain.find('.list_city_head').html(value + $.i18n.prop('label.city.sort'));
                                setAddPage(1);
                                setTopSelect();
                            } else {
                                suggestMain.find('.list_city_head').html("<span class='msg'>" + $.i18n.prop('label.city.notfound') + value + "</span>");
                            }
                            suggestMain.show();
                            setTopSelect();//Levin 解决未定位到第一个元素问题
                        }
                    });
                }
            }, 200, self));

        }

        function setAddPage(pageIndex) {
            suggestMain.find('.list_city_container a').removeClass('selected');
            suggestMain.find('.list_city_container').children().each(function (i) {
                var k = i + 1;
                if (k > options.suggestLength * (pageIndex - 1) && k <= options.suggestLength * pageIndex) {
                    $(this).css('display', 'block');
                } else {
                    $(this).hide();
                }
            });
            setTopSelect();
            setAddPageHtml(pageIndex);
        }

        function setAddPageHtml(pageIndex) {
            var cityPageBreak = suggestMain.find('.page_break');
            cityPageBreak.empty();
            if (suggestMain.find('.list_city_container').children().length > options.suggestLength) {
                var pageBreakSize = Math.ceil(suggestMain.find('.list_city_container').children().length / options.suggestLength);
                if (pageBreakSize <= 1) {
                    return;
                }
                var start = end = pageIndex;
                for (var index = 0, num = 1; index < options.pageLength && num < options.pageLength; index++) {
                    if (start > 1) {
                        start--;
                        num++;
                    }
                    if (end < pageBreakSize) {
                        end++;
                        num++;
                    }
                }
                if (pageIndex > 1) {
                    cityPageBreak.append("<a href='javascript:void(0)' inum='" + (pageIndex - 1) + "'>&lt;-</a>");
                }
                for (var i = start; i <= end; i++) {
                    if (i == pageIndex) {
                        cityPageBreak.append("<a href='javascript:void(0)' class='current' inum='" + (i) + "'>" + (i) + "</a");
                    } else {
                        cityPageBreak.append("<a href='javascript:void(0)' inum='" + (i) + "'>" + (i) + "</a");
                    }
                }
                if (pageIndex < pageBreakSize) {
                    cityPageBreak.append("<a href='javascript:void(0);' inum='" + (pageBreakSize) + "'>-&gt;</a>");
                }
                cityPageBreak.show();
            } else {
                cityPageBreak.hide();
            }
            return;
        }

        function setTopSelect() {
            if (suggestMain.find('.list_city_container').children().length > 0) {
                suggestMain.find('.list_city_container').children(':visible').eq(0).addClass('selected');
            }
        }

        function popInit() {
            var index = 0;
            popMain.find('.pop_head').html(options.popTitleText);
            if (!options.tabs) {
                popContainer.append("<ul id='label_" + id + "_container' class='current'></ul>");
                labelMain.remove();
                for (var item in options.data) {
                    $("#label_" + id + "_container").append("<li><a id='" + options.data[item][0] + "' href='javascript:void(0)'>" + options.data[item][1] + "</a></li>");
                }
                return;
            }

            //var popHistoryContainer = popMain.find('.pop_history_city_container').append("<ul class='current'></ul>");
            var ul = $("<ul class=''></ul>");
            popMain.find('.pop_history_city_container').append(ul);
            //ul.append("<li><a id='111' title='111' href='javascript:void(0)'>aaa</a></li>");
            //ul.append("<li><a id='111' title='111' href='javascript:void(0)'>aaa</a></li>");
            //popHistoryContainer.append("<li><a id='222' title='222' href='javascript:void(0)'>bbb</a></li>");
            if (window.$historyList) {
                var tempList = [];
                var tempIdList = [];
                for (var i = 0; i < window.$historyList.length; i++) {
                    if (!tempIdList.contain(window.$historyList[i].His_ID)) {
                        tempIdList.push(window.$historyList[i].His_ID);
                        tempList.push(window.$historyList[i]);
                    }
                }
                window.$historyList = tempList;
                for (var i = 0; i < window.$historyList.length; i++) {
                    ul.append("<li><a id='" + window.$historyList[i].His_ID + "' data-id='" + window.$historyList[i].His_ID + "' data-name='" + window.$historyList[i].His_Text + "' title='" + window.$historyList[i].His_Text + "' href='javascript:void(0)'>" + window.$historyList[i].His_Text + "</a></li>");
                }
            }
            //0609
            for (var itemLabel in options.tabs) {
                index++;
                if (index == 1) {
                    /*0609*/
                    popContainer.append("<ul id='label_" + id + index + "_container' class='current' data-type='" + itemLabel + "'></ul>");
                    //labelMain.append("<li><a id='label_" + id + index + "' class='current' href='javascript:void(0)'>" + itemLabel + "</a></li>");
                    labelMain.append("<li><a id='label_" + id + index + "' href='javascript:void(0)'>" + itemLabel + "</a></li>");
                } else {
                    popContainer.append("<ul style='display:none' id='label_" + id + index + "_container' data-type='" + itemLabel + "'></ul>");
                    labelMain.append("<li><a id='label_" + id + index + "' href='javascript:void(0)'>" + itemLabel + "</a></li>");
                }
                for (var item in options.tabs[itemLabel]) {
                    var cityCode = options.tabs[itemLabel][item];
                    if (!options.data[cityCode]) {
                        break;
                    }
                    //$("#label_" + id + index + "_container").append("<li><a id='" + options.data[cityCode][0] + "'href='javascript:void(0)'>" + options.data[cityCode][1] + "</a></li>");
                    //$('#label_' + id + index + '_container').append('<li><a id="' + options.data[cityCode][0] + '" href="javascript:void(0)" title="' + options.data[cityCode][1] + '" style="text-align:center">' + (options.data[cityCode][1].length > 4 ? options.data[cityCode][1].substr(0, 4) : options.data[cityCode][1]) + '</a></li>');
                    $('#label_' + id + index + '_container').append('<li><a id="p_city_' + options.data[cityCode][0] + '" href="javascript:void(0)" data-id="' + options.data[cityCode][0] + '" data-name="' + options.data[cityCode][1] + '" title="' + options.data[cityCode][1] + '" style="text-align:center">' + options.data[cityCode][1] + '</a></li>');
                }
            }
            //options.city
            //海外国家 2017-06-15 w2
            for (var i = 0; i < options.city.length; i++) {
                index = 7 + i;
                
                labelMainCountry.append("<li><a id='label_" + id + index + "' href='javascript:void(0)'>" + options.city[i].Name + "</a></li>");
                popContainer.append("<ul style='display:none' id='label_" + id + index + "_container' data-type='" + options.city[i].Name + "'></ul>");
                var itemLabelcc = options.city[i].CityList;
              
                for (var k = 0; k < itemLabelcc.length; k++) {
                    overseasCityMap['s_city_' + itemLabelcc[k].ID] = itemLabelcc[k];
                    $('#label_' + id + index + '_container').append('<li><a id="p_city_' + itemLabelcc[k].ID + ' href="javascript:void(0)" data-id="' + itemLabelcc[k].ID + '" data-name="' + itemLabelcc[k].Name + '" title="' + itemLabelcc[k].Name + '" style="text-align:center">' + itemLabelcc[k].Name + '</a></li>');
                        
                    }  
            }
        }

        function resetPosition() {
            //            popMain.css({'top': e.position().top + e.outerHeight(), 'left': e.position().left});
            //alert('left:' + e.offset().left + ',width:' + (e.offset().left + popMain.outerWidth()));
            if (options.align && options.align == 'right') {
                popMain.css({ 'top': e.offset().top + e.outerHeight(), 'left': e.offset().left - popMain.outerWidth() + e.outerWidth() });
            } else {
                popMain.css({ 'top': e.offset().top + e.outerHeight(), 'left': e.offset().left });
            }
            suggestMain.css({ 'top': e.offset().top + e.outerHeight(), 'left': e.offset().left });
        }

        //选择弹出框
        function popSelected(o) {
            onSelect(o, false);
        }

        //选择Suggest框
        function suggestSelected(o) {
            onSelect(o, true);
        }

        var ecount = 0;
        //选择城市
        function onSelect(o, isSuggest) {

            var c;
            if (isSuggest) {
                $.fn.Logger("选择推荐城市");
                c = suggestCityMap[o.attr('id')];
            } else {
                $.fn.Logger("选择城市列表");
                //c = popCityMap[o.attr('id')];
                c = new Object();
                c.ID = o.attr('data-id');
                c.Cityid = o.attr('data-id');
                c.Name = o.attr('data-name');
            }

            fillValue(c);
        }

        function fillValue(c) {

            e.val(c.Name);
            e.attr('data-value', c.ID);
            e.attr('data-name', c.Name);
            //e.attr('data-mark', c.Mark);
            //e.attr('data-pointType', c.PointType);
            if (options.isGlobal) {
                globalCityId = c.Cityid;
                globalCityText = c.Name;
            }

            if (window.location.href.indexOf('/HotelList') > 0) {//如果是酒店列表预订页面则初始化tab页
                //alert('loadingLocationTab');
                //if (globalCityId.indexOf('_c') >= 0) globalCityId = globalCityId.substring(0, globalCityId.indexOf('_c'));
                $.fn.loadingListVariableText(globalCityId.indexOf('_c') >= 0 ? globalCityId = globalCityId.substring(0, globalCityId.indexOf('_c')) : globalCityId);
                $('.ui-tab-bd').find('.ui-tab-content').each(function (index) {
                    //  var menu = $($("#demo1 .ui-tab-container .ui-tab-list li[data-mark='" + $(this).attr('data-mark') + "']"));//.get(index + 1));
                    var menu = $($("#demo0  li[data-mark='" + $(this).attr('data-mark') + "']"));//.get(index + 1));
                    loadingLocationTab(globalCityId, $(this).attr('data-mark'), $(this), menu);
                });
            } else if (window.location.href.indexOf('/HotelMap') > 0) {
                loadingPositionData(c.Cityid);
            }
            /**
            if (options.clearObjectList) {
                if (options.clearObjectList && options.clearObjectList.length > 0) {
                    for (var i = 0; i < options.clearObjectList.length; i++) {
                        //options.clearObjectList[i].empty();
                        $(options.clearObjectList[i]).empty();
                    }
                }
            }
            */
            //清除关键字和关键字推荐层
            if (options.keywordId) {
                $('#' + options.keywordId).val('');
                $('#popKeyWord_' + options.keywordId).remove();
                $('#suggestKeyWord_' + options.keywordId).remove();
                //渲染关键字控件
                $.fn.InitKeyWordSelector(options.keywordId, '', '', c.Cityid);
            }
        }

    }

    /**
     *
     */
    $.fn.JCitySelector = function (options) {
        var defaults = {
            'data': '',
            'tabs': '',
            'cityId': '',
            'defaultText': '',
            'popTitleText': $.i18n.prop('label.city.pop.title'),
            'suggestTitleText': $.i18n.prop('label.city.suggest.title'),
            'suggestLength': 10,
            'pageLength': 5,
            'keywordId': '',
            'align': 'left',
            'isGlobal': true
        };

        var options = $.extend(defaults, options);
        this.each(function () {
            new $.JCitySelector(this, options);
        });
        return this;

    };
})(jQuery);

//Keyword jQuery插件
(function ($) {
    $.JKeyWordSelector = function (element, options) {
        var e = $(element);
        var val = $.trim(e.val());
        var id = e.attr('id');
        e.attr('autocomplete', 'off');

        if (val == '' || val == options.defaultText) {
            e.val(options.defaultText).css('color', '#aaa');
        }

        var t_pop_focus = false;
        var t_suggest_focus = false;
        var t_suggest_page_click = false;

        init();
        var popMain = $("#popKeyWord_" + id);
        var suggestMain = $("#suggestKeyWord_" + id);
        popMain.bgIframe();
        suggestMain.bgIframe();

        resetPosition();

        $(window).resize(function () {
            resetPosition();
        });

        e.focus(function () {
            $(this).val("");

            if (t_suggest_page_click) {
                t_suggest_page_click = false;
                return true;
            }
            suggestMain.hide();
            if ($.trim($(this).val()) == options.defaultText) {
                $(this).val('').css('color', '#000');
            }
            popMain.show();
        }).click(function () {
            if (t_suggest_page_click) {
                t_suggest_page_click = false;
                return;
            }
            suggestMain.hide();
            popMain.show();
        }).blur(function () {
            if (t_pop_focus == false) {
                popMain.hide();

                //if (val == '' || val == options.defaultText) {
                if ($.trim($(this).val()) != '' && $.trim($(this).val()) != $(this).attr('data-name')) {
                    e.val($(this).attr('data-name')).css('color', '#aaa');
                }
            }
        });

        //选择弹出城市框城市事件
        popMain.on('click', 'a', function () {
            if ($(this).attr('id') && popKeyWordMap[$(this).attr('id')]) {
                popSelected($(this));
            }

            popMain.hide();
        });

        popMain.mouseover(function () {
            t_pop_focus = true;
        }).mouseout(function () {
            t_pop_focus = false;
        });

        e.blur(function () {

            hodelKeyWordValue = $(".sisa a:first").attr("data-value");
            hodelKeyWordText = $(".sisa a:first").attr("data");

            if (t_suggest_focus == false) {
                if ($(this).val() == '' || $(this).val() != hodelKeyWordText) {
                    $(this).attr('data-id', '');

                    $(this).attr('data-value', hodelKeyWordValue);
                    $(this).attr('data-name', hodelKeyWordText);
                    $(this).attr('data-mark', '');

                    $(this).val(hodelKeyWordText);

                    $(this).attr('data-pointType', '');
                    $('#HotelCodeHidden').val('');
                    //$(this).val(suggestMain.find('.sisa a.selected').children('b').text());
                }
                suggestMain.hide();
            }
        }).keydown(function (event) {
            popMain.hide();
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (keyCode == 38) {//上
                prevResult();
            } else if (keyCode == 40) {//下
                nextResult();
            }
        }).keypress(function (event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (13 == keyCode) {
                if (suggestMain.find('.sisa a.selected').length > 0) {
                    suggestSelected(suggestMain.find('.sisa a.selected'));
                    suggestMain.hide();
                }
            }
        }).keyup(function (event) {
            event = window.event || event;
            var keyCode = event.keyCode || event.which || event.charCode;
            if (keyCode != 13 && keyCode != 37 && keyCode != 39 && keyCode != 9 && keyCode != 38 && keyCode != 40) {
                queryKeyWord();
            }
        });

        function nextResult() {
            var t_index = suggestMain.find('.sisa a').index(suggestMain.find('.sisa a.selected')[0]);
            suggestMain.find('.sisa').children().removeClass('selected');
            t_index += 1;
            var t_end = suggestMain.find('.sisa a').index(suggestMain.find('.sisa a:visible').filter(':last')[0]);
            if (t_index > t_end) {
                t_index = suggestMain.find('.sisa a').index(suggestMain.find('.sisa a:visible').eq(0));
            }
            suggestMain.find('.sisa a').eq(t_index).addClass('selected');
        }

        function prevResult() {
            var t_index = suggestMain.find('.sisa a').index(suggestMain.find('.sisa a.selected')[0]);
            suggestMain.find('.sisa').children().removeClass('selected');
            t_index -= 1;
            var t_start = suggestMain.find('.sisa a').index(suggestMain.find('.sisa a:visible').filter(':first')[0]);
            if (t_index < t_start) {
                t_index = suggestMain.find('.sisa a').index(suggestMain.find('.sisa a:visible').filter(':last')[0]);
            }
            suggestMain.find('.sisa a').eq(t_index).addClass('selected');
        }

        function queryKeyWord() {
            popMain.hide();

            var self = this, $this = $(self);
            if ($this.data('QueryKeyWordTimeout')) {
                clearTimeout($this.data('QueryKeyWordTimeout'));
            }
            $this.data('QueryKeyWordTimeout', setTimeout(function () {
                var value = e.val().toLowerCase();
                var key = e.val();

                if (key && $.trim(key) != "") {
                    //
                    var baiduSearch = $.fn.GetInfoByBaidu({ q: key, region: globalCityText });

                    $.ajax({
                        type: "POST",
                        url: '/HotelList/SearchLocationByCityId',
                        data: {
                            cityId: globalCityId,
                            key: key,
                            timestamp: new Date().getTime()
                        },
                        success: function (data) {
                            var isHave = false;
                            var dataMap = {};
                            var mdList = [];//门店
                            var jcList = [];//机场车站
                            var otherList = [];//其它
                            var searchList = baiduSearch;//百度搜索返回的数据
                            for (var i = 0; i < data.length; i++) {
                                var _data = data[i];
                                isHave = true;
                                suggestKeyWordMap['s_keyword_' + _data.ID] = _data;
                                if (_data.PointType == 3) {//门店
                                    mdList.push(_data);
                                } else {
                                    if (_data.Mark == 1) {//1＝机场车站
                                        jcList.push(_data);
                                    } else {//其它
                                        if (_data.Mark == 2) {
                                            _data.Name = _data.Name + (typeof _data.BusName !== '' && _data.BusName && _data.BusName != '' ? '(' + _data.BusName + ')' : _data.Name);
                                        }
                                        otherList.push(_data);
                                    }
                                }
                            }

                            if (mdList && mdList.length > 0) {
                                dataMap.mdList = mdList;

                            }
                            if (jcList && jcList.length > 0) {
                                dataMap.jcList = jcList;

                            }
                            if (otherList && otherList.length > 0) {
                                dataMap.otherList = otherList;

                            }
                            if (searchList && searchList.length > 0) {
                                dataMap.searchList = searchList;

                            }

                            dataMap.id = id;

                            if (isHave) {
                                fundSuggestMain(dataMap);
                            } else {

                                var obj = new Object();
                                suggestMain.empty();
                                //如果solr没值  百度返回的就在这里处理

                                var data = $.fn.GetInfoByBaidu({ q: key, region: globalCityText });

                                if (data && data.length > 0) {

                                    obj.id = id;
                                    obj.searchList = new Array();
                                    obj.searchList = data;
                                    fundSuggestMain(obj);
                                }
                                else {
                                    suggestMain.append('<div class="c_address_title"><p>' + $.i18n.prop('label.hotel.list.nodata') + '</p>  <a class="close" href="javascript:;">×</a></div>');
                                }
                            }
                            suggestMain.show();
                        }
                    });
                }
            }, 200, self));

        }




        function init() {
            var PopListData = options.data;
            var hasData = false;
            //无数据显示
            if ((PopListData.locationList && PopListData.locationList.length > 0) || (PopListData.metroList && PopListData.metroList.length > 0) || (PopListData.slList && PopListData.slList.length > 0) || (PopListData.zoneList && PopListData.zoneList.length > 0)) {
                hasData = true;
            }

            if (hasData) {
                $("#KeyWordTemplate").tmpl(options.data).appendTo("body");
            } else {
                $("body").append('<div id="popKeyWord_' + PopListData.id + '" class="c_address_box" style="display: none"><a class="close" href="javascript:;">×</a> <dl class="key_word_list"> <dt>' + $.i18n.prop('label.hotel.list.nodata') + '</dt> </dd> </dl></div>');
            }
            $("body").append('<div id="suggestKeyWord_' + id + '" style="display:none" class="c_address_boxd"></div>');
        }

        function resetPosition() {
            popMain.css({ 'top': e.offset().top + e.outerHeight(), 'left': e.offset().left });
            suggestMain.css({ 'top': e.offset().top + e.outerHeight(), 'left': e.offset().left });
        }

        //选择弹出框
        function popSelected(o) {
            onSelect(o, false);
        }

        //选择Suggest框
        function suggestSelected(o) {
            onSelect(o, true);
        }

        //选择Keyword
        function onSelect(o, isSuggest) {
            var c;
            if (isSuggest) {
                c = suggestKeyWordMap[o.attr('id')];
            } else {
                c = popKeyWordMap[o.attr('id')];
            }
            e.val(c.Name);
            e.attr('data-value', c.ID);
            e.attr('data-name', c.Name);
            e.attr('data-mark', c.Mark);
            e.attr('data-pointType', c.PointType);



            var mark = c.Mark;
            var keywordId = '';
            var keywordText = $('#txtKeyword').val();

            if ($('#HotelCodeHidden')) {
                $('#HotelCodeHidden').val('');
            }
            e.attr('data-lineId', '');
            if (isSuggest && c.PointType == 3) {
                //keywordId = c.ID;
                keywordId = c.HotelCode;
                e.attr('data-value', c.HotelCode);
                if ($('#HotelCodeHidden')) {//如果是酒店列表，则设置HotelCodeHidde
                    $('#HotelCodeHidden').val(c.HotelCode);
                }
            } else if (isSuggest && mark == 2) {
                var pointXY = c.Pointxy;
                var p = pointXY.split(',');
                keywordId = $.trim(p[0]) + ',' + $.trim(p[1]);
                e.attr('data-value', keywordId);
                e.attr('data-lineId', c.BusID);
            } else if (mark == 9 || mark == 10) {
                keywordId = c.ID;
                e.attr('data-value', c.ID);
            } else {
                var pointXY = c.Pointxy;
                var p = pointXY.split(',');
                keywordId = $.trim(p[0]) + ',' + $.trim(p[1]);
                e.attr('data-value', keywordId);

                //全局变量，用于记录用户本次选择的搜索结果
                hodelKeyWordValue = keywordId;
                hodelKeyWordText = c.Name;
            }
            e.attr('data-mark', c.Mark);
            e.attr('data-pointType', c.PointType);
            var cityId = globalCityId;
            var cityText = globalCityText;
            var startDate = $('#txtCheckIn').val();
            var endDate = $('#txtCheckOut').val();

            if (window.location.href.indexOf('/HotelList') > 0) {
                //alert(window.location.href); ui-tab-active

                $('.ui-tab-list li').attr('class', '');
                //$('.ui-tab-list li[data-mark="' + c.Mark + '"]').attr('class', 'ui-tab-active');
                //$(".header-bottom_Loc .bottom-head_Loc div[data-mark='" + c.Mark + "']").addClass("ui-tab-active");

                $('.ui-tab-content').css('display', 'none');
                $('.ui-tab-content[data-mark="' + c.Mark + '"]').css('display', '');

                //$('.menu li').removeClass('ui-tab-active');
                $(".header-bottom_Loc .bottom-head_Loc").removeClass("ui-tab-active");
                //$('.menu li[data-mark="' + c.Mark + '"]').addClass('ui-tab-active');
                $(".header-bottom_Loc .bottom-head_Loc").each(function () {
                    if ($(this).attr("data-mark") == c.Mark) {
                        $(this).addClass("ui-tab-active"); return false;
                    }
                });
                
                if (c.Mark == 10) {//地铁不查询
                    $('.ui-tab-content input[name="locationId"]:checked').prop('checked', false);
                    var subwayTab = $('.ui-tab-content[data-mark="' + c.Mark + '"]');
                    subwayTab.find('ul li').attr('class', '');
                    subwayTab.find('ul li[data-id="' + keywordId + '"]').attr('class', 'ui-tab-active');
                    subwayTab.find('.ui-tab-bd div').css('display', 'none');
                    subwayTab.find('.ui-tab-bd div[data-id="' + keywordId + '"]').css('display', '');
                    loadingSubwayTab(cityId, keywordId, subwayTab.find('.ui-tab-bd div[data-id="' + keywordId + '"]'));
                } else {
                    $('.ui-tab-content input[name="locationId"]:checked').prop('checked', false);
                    $('.ui-tab-content input[name="locationId"][value="' + keywordId + '"]').prop('checked', true);
                    $('#PageIndex').val(0);//重置分页 add by tager
                    AjaxSearchHotel();
                }
            } else if (window.location.href.indexOf('/HotelMap') > 0) {
                $('#PageIndex').val(0);
                AjaxSearchHotelList();
            } else {
                //window.open(window.location.href.substring(0, window.location.href.lastIndexOf('/')) + '/HotelList?cityId=' + cityId + '&cityText=' + cityText + '&startDate=' + startDate + '&endDate=' + endDate + '&keywordId=' + keywordId + '&keywordText=' + keywordText);
                $.fn.forwardToHotelList(cityId, cityText, startDate, endDate, '', keywordId, keywordText, mark, c.PointType, typeof c.BusID === 'undefined' || !c.BusID ? '' : c.BusID);
            }

            selectTab();
        }

        //根据数据创建SuggestMain
        function fundSuggestMain(data) {
            suggestMain.remove();

            var html = $("#KeyWordSuggestTemplate").tmpl(data);

            $("#KeyWordSuggestTemplate").tmpl(data).appendTo("body");

            suggestMain = $("#suggestKeyWord_" + id);
            suggestMain.css({ 'top': e.offset().top + e.outerHeight(), 'left': e.offset().left });
            $('.sisa a:eq(0)', suggestMain).addClass('selected');
            //选择Suggest事件
            suggestMain.find('.sisa').on('click', 'a', function () {
                suggestSelected($(this));
                suggestMain.hide();

            }).on('mouseover', 'a', function () {
                t_suggest_focus = true;
            }).on('mouseout', 'a', function () {
                t_suggest_focus = false;
            });

            suggestMain.find('.c_address_title').on('mouseover', 'a', function () {
                t_suggest_focus = true;
            }).on('mouseout', 'a', function () {
                t_suggest_focus = false;
            }).on('click', 'a', function (event) {
                t_suggest_page_click = false;
                suggestMain.hide();
            });
        }
    }

    /**
     *
     */
    $.fn.JKeyWordSelector = function (options) {
        var defaults = {
            'data': {},
            'defaultText': ''
        };
        var options = $.extend(defaults, options);
        this.each(function () {
            new $.JKeyWordSelector(this, options);
        });
        return this;
    };
})(jQuery);

(function ($) {
    $.fn.bgIframe = $.fn.bgiframe = function (s) {
        //	if ( $.browser.msie && /6.0/.test(navigator.userAgent) ) {
        if (navigator.userAgent.match(/msie [6]/i)) {
            s = $.extend({
                top: 'auto', // auto == .currentStyle.borderTopWidth
                left: 'auto', // auto == .currentStyle.borderLeftWidth
                width: 'auto', // auto == offsetWidth
                height: 'auto', // auto == offsetHeight
                opacity: true,
                src: 'javascript:false;'
            }, s || {});
            var prop = function (n) {
                return n && n.constructor == Number ? n + 'px' : n;
            },
                html = '<iframe class="bgiframe"frameborder="0"tabindex="-1"src="' + s.src + '"' +
                    'style="display:block;position:absolute;z-index:-1;' +
                    (s.opacity !== false ? 'filter:Alpha(Opacity=\'0\');' : '') +
                    'top:' + (s.top == 'auto' ? 'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')' : prop(s.top)) + ';' +
                    'left:' + (s.left == 'auto' ? 'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')' : prop(s.left)) + ';' +
                    'width:' + (s.width == 'auto' ? 'expression(this.parentNode.offsetWidth+\'px\')' : prop(s.width)) + ';' +
                    'height:' + (s.height == 'auto' ? 'expression(this.parentNode.offsetHeight+\'px\')' : prop(s.height)) + ';' +
                    '"/>';
            return this.each(function () {
                if ($('> iframe.bgiframe', this).length == 0)
                    this.insertBefore(document.createElement(html), this.firstChild);
            });
        }
        return this;
    };
})(jQuery);



//通过百度API返回可能的关键字查询结果
//element  html 元素  options 传入的参数
(function ($) {
    $.fn.GetInfoByBaidu = function (options) {
        //百度返回的结果集
        var objArr = [];

        $.ajax({
            async: false,
            type: 'GET',
            url: '/home/GetSuggestByBaidu',
            data: {
                q: options.q,
                region: options.region,
                output: 'json',
                page_size: 5,

                timestamp: new Date().getTime()
            },
            success: function (json) {
                obj = json.results;
                for (var i = 0; i < obj.length; i++) {
                    //将从百度获取的数据拼接成solr需要的格式
                    var model = {
                        ID: "",
                        Name: "",
                        NameEn: "",
                        ParentID: "",
                        ShowOrder: "",
                        ShortName: "",
                        PointType: "",
                        Mark: "",
                        BusID: "",
                        BusName: "",
                        BusNameEn: "",
                        TblType: "",
                        IsHotCity: "",
                        SortHot: "",
                        CityAreaName: "",
                        CityAreaNameEn: "",
                        Cityid: "",
                        FullSpell: "",
                        IsMainCity: "",
                        Pointxy: "",
                        Pointxy_G: "",
                        ONum: "",
                        IsHot: "",
                        HotelCode: "",
                        Distance: ""
                    }
                    model.ID = i;
                    model.Name = obj[i].name;
                    if (obj[i].address) {
                        model.Name = obj[i].name + ' — ' + obj[i].address;
                    }
                    if (obj[i].location) {
                        model.Pointxy = obj[i].location.lat + ',' + obj[i].location.lng;
                        objArr.push(model);
                        suggestKeyWordMap['s_keyword_seach_' + i] = model;
                    }
                }

            },
            dataType: 'json',
        });
        //给弹出层的数据
        //var objInfo = new infoObj(id,objArr);
        //console.log(objArr);
        return objArr;
    };
})(jQuery);




(function ($) {
    var $scrollLoading;

    $.fn.scrollLoading = function (settings) {
        return this.each(function () {
            $scrollLoading.init($(this), settings);
        });
    };

    $scrollLoading = $.scrollLoading = {
        more: true,

        init: function (outer, settings) {
            // objects
            this.outer = outer;
            this.appendTo = settings.appendTo;
            this.judgeBy = settings.appendTo || this.appendTo;
            this.ratio = settings.ratio || 0.9;
            // ajax settings
            this.ajaxData = settings.ajaxData || {};

            // overwrite datType
            if ('undefined' === typeof this.ajaxData.dataType || !$.inArray(this.ajaxData.dataType, ('html', 'json'))) {
                this.ajaxData.dataType = 'html';
            }

            // overwrite success callback function
            this.tmp_success = this.ajaxData.success;
            this.ajaxData.success = function (ret) {
                sl = $scrollLoading;
                sl.resultHandler(ret);

                // 若需要有更新 data 的動作要寫在 success 裡
                if ('function' === typeof sl.tmp_success) {
                    sl.tmp_success(ret);
                }

                // 檢查版面高度夠不夠讓捲軸出現
                if (sl.more) {
                    sl.checkGetMore();
                }
            };

            this.outer.scroll(function () {
                var sl = $scrollLoading;
                var scrollBottom = sl.outer.scrollTop() + $(window).height();
                if ((true === sl.more) && (scrollBottom / sl.judgeBy.height() >= sl.ratio)) {
                    sl.more = false;
                    sl.sendRequest();
                }
            });

            // 檢查版面高度夠不夠讓捲軸出現
            this.checkGetMore();
        },

        sendRequest: function () {
            $.ajax(this.ajaxData);
        },

        checkGetMore: function () {
            if (this.judgeBy.height() < this.outer.height() * 2) {
                this.more = false;
                this.sendRequest();
            }
        },

        resultHandler: function (ret) {
            var content = '';
            if ('html' === this.ajaxData.dataType) {
                content = ret;
            } else if ('json' === this.ajaxData.dataType) {
                if (true === ret.error) {
                    content = '';
                } else if ('undefined' !== typeof ret.content) {
                    content = ret.content;
                }
            }

            if ('' !== content) {
                this.appendTo.append(content);
                this.more = true;
            }
        }
    };

})(jQuery);

(function ($) {
    var isShow = !1;
    $.fn.tab = function (options) {
        this.opts = $.extend({}, $.fn.tab.defaults, options), this._init(), this.disableArr = [];
    }, $.fn.tab.prototype = {
        "_init": function () {
            var _this = this;
            $(_this.opts.tabList).length > 0 && $(_this.opts.tabList).each(function (index) {
                $(this).bind(_this.opts.eventType, function () {
                    $.inArray(index, _this.disableArr) == -1 && !isShow && (!$(this).attr("class") || $(this).attr("class").indexOf(_this.opts.tabActiveClass) == -1) && (_this.opts.callBackStartEvent && _this.opts.callBackStartEvent(index), $(_this.opts.tabList).removeClass(_this.opts.tabActiveClass), $(this).addClass(_this.opts.tabActiveClass), _this._showContent(index));
                });
            });
        },
        "_showContent": function (index) {
            isShow = !0;
            var _this = this;
            switch (_this.opts.showType) {
                case "show":
                    $(_this.opts.contentList + ":visible").hide(), _this.opts.callBackHideEvent && _this.opts.callBackHideEvent(index), $(_this.opts.contentList).eq(index).show(), _this.opts.callBackShowEvent && _this.opts.callBackShowEvent(index), isShow = !1;
                    break;
                case "fade":
                    $(_this.opts.contentList + ":visible").fadeOut(_this.opts.showSpeed, function () {
                        _this.opts.callBackHideEvent && _this.opts.callBackHideEvent(index), $(_this.opts.contentList).eq(index).fadeIn(function () {
                            _this.opts.callBackShowEvent && _this.opts.callBackShowEvent(index), isShow = !1;
                        });
                    });
                    break;
                case "slide":
                    $(_this.opts.contentList + ":visible").slideUp(_this.opts.showSpeed, function () {
                        _this.opts.callBackHideEvent && _this.opts.callBackHideEvent(index), $(_this.opts.contentList).eq(index).slideDown(function () {
                            _this.opts.callBackShowEvent && _this.opts.callBackShowEvent(index), isShow = !1;
                        });
                    });
            }
        },
        "setDisable": function (index) {
            var _this = this;
            $.inArray(index, this.disableArr) == -1 && (this.disableArr.push(index), $(_this.opts.tabList).eq(index).addClass(_this.opts.tabDisableClass));
        },
        "setEnable": function (index) {
            var _this = this, i = $.inArray(index, this.disableArr);
            i > -1 && (this.disableArr.splice(i, 1), $(_this.opts.tabList).eq(index).removeClass(_this.opts.tabDisableClass));
        },
        "triggleTab": function (index) {
            $(this.opts.tabList).eq(index).trigger(this.opts.eventType);
        }
    }, $.fn.tab.defaults = {
        "tabList": ".ui-tab-container .ui-tab-list li",
        "contentList": ".ui-tab-container .ui-tab-content",
        "tabActiveClass": "ui-tab-active",
        "tabDisableClass": "ui-tab-disable",
        "eventType": "click",
        "showType": "show",
        "showSpeed": 200,
        "callBackStartEvent": null,
        "callBackHideEvent": null,
        "callBackShowEvent": null
    };
})(jQuery)

/*
 * jQuery XYTipsWindow Plus @requires jQuery v1.3.2
 * Dual licensed under the MIT and GPL licenses.
 *
 * Copyright (c) xinyour (http://www.xinyour.com/)
 *
 * Autor: Await
 * webSite: http://leotheme.cn/
 * Date: 星期四 2011年05月15日
 * Version: 2.8.0
 **********************************************************************
 * @example
 * $("#example").XYTipsWindow();
 **********************************************************************
 * XYTipsWindow o参数可配置项：
 *		    ___title : 窗口标题文字;
 *	  	    ___boxID : 弹出层ID(默认随机);
 *	 	  ___content : 内容(可选内容为){ text | id | img | swf | url | iframe};
 *	 	    ___width : 窗口宽度(默认宽度为300px);
 *	 	   ___height : 窗口离度(默认高度为200px);
 *	   ___titleClass : 窗口标题样式名称;
 *	 	  ___closeID : 关闭窗口ID;
 *	    ___triggerID : 相对于这个ID定位;[暂时取消此功能]
 *	   ___boxBdColor : 弹出层外层边框颜色(默认值:#E9F3FD);
 *   ___boxBdOpacity : 弹出层外层边框透明度(默认值:1,不透明);
 * ___boxWrapBdColor : 弹出层内部边框颜色(默认值:#A6C9E1);
 *  ___windowBgColor : 遮罩层背景颜色(默认值:#000000);
 *___windowBgOpacity : 遮罩层背景透明度(默认值:0.5);
 *		     ___time : 自动关闭等待时间;(单位毫秒);
 *		     ___drag : 拖动手柄ID[当指定___triggerID的时候禁止拖动];
 * ___dragBoxOpacity : 设置窗口拖动时窗口透明度(默认值:1,不透明);
 *	    ___showTitle : 是否显示标题(布尔值 默认为true);
 *	    ___showBoxbg : 是否显示弹出层背景(布尔值 默认为true);
 *		   ___showbg : 是否显示遮罩层(布尔值 默认为false);
 *	  	   ___button : 数组，要显示按钮的文字;
 *		 ___callback : 回调函数，默认返回所选按钮显示的文 ;
 *		  ___offsets : 设定弹出层位置,默认居中;内置固定位置浮动:left-top(左上角);right-top(右上角);left-bottom(左下角);right-bottom(右下角);middle-top(居中置顶);middle-bottom(居中置低);left-middle(靠左居中);right-middle(靠右居中);
 *		      ___fns : 弹出窗口后执行的函数;
 **********************************************************************/
; (function () {
    $.XYTipsWindow = function (o) {
        defaults = $.extend({
            ___title: "Hello World",
            ___boxID: boxID(10),
            ___content: "text:内容",
            ___width: "300",
            ___height: "200",
            ___titleClass: "boxTitle",
            ___closeID: "",
            ___triggerID: "",
            ___boxBdColor: "#E9F3FD",
            ___boxBdOpacity: "1",
            ___boxWrapBdColor: "#A6C9E1",
            ___windowBgColor: "#000000",
            ___windowBgOpacity: "0.5",
            ___time: "",
            ___drag: "",
            ___dragBoxOpacity: "1",
            ___showTitle: true,
            ___showBoxbg: true,
            ___showbg: false,
            ___offsets: "",
            ___button: "",
            ___callback: function () { },
            ___fns: function () { }
        }, o);
        $.XYTipsWindow.init(defaults);
    };
    var BOXID, isIE6 = !-[1, ] && !window.XMLHttpRequest;
    var $XYTipsWindowarr = new Array();
    var boxID = function (n) {
        var Str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (var i = 0, r = ""; i < n; i++) {
            r += Str.charAt(Math.floor(Math.random() * 62));
        };
        return r;
    };
    $.extend($.XYTipsWindow, {
        //初始化
        init: function (o) {
            BOXID = o;
            if ($("#" + o.___boxID).length > 0) {
                alert("对不起，创建弹出层失败！窗口“" + o.___boxID + "”已存在！");
                return false;
            };
            var $box = $("#" + o.___boxID);
            $.XYTipsWindow.showBox(o);
            if (o.___closeID != "") {
                $("#" + o.___closeID, $box).off().on("click", function () {
                    $.XYTipsWindow.removeBox();
                });
            };
            if (o.___time != "") {
                setTimeout($.XYTipsWindow.removeBox, o.___time);
            };
            if (o.___showbg != "" && o.___showbg == true) {
                var $boxBgDom = "<div id=\"XYTipsWindowBg\" style=\"position:absolute;background:" + o.___windowBgColor + ";filter:alpha(opacity=0);opacity:0;width:100%;left:0;top:0;z-index:870618\"><iframe src=\"about:blank\" style=\"width=100%;height:" + $(document).height() + "px;filter:alpha(opacity=0);opacity:0;scrolling=no;z-index:870610\"></iframe></div>";
                $($boxBgDom).appendTo("body").animate({ opacity: o.___windowBgOpacity }, 200);
            };
            if (o.___drag != "") {
                $.XYTipsWindow.dragBox(o);
            };
            if (o.___fns != "" && $.isFunction(o.___fns)) {
                o.___fns.call(this);
            };
            $.XYTipsWindow.contentBox(o);
            if (o.___button != "") {
                $.XYTipsWindow.ask(o);
            };
            $.XYTipsWindow.keyDown(o);
            $.XYTipsWindow.setBoxzIndex(o);
            if (o.___showbg != true) {
                $("#" + o.___boxID).addClass("shadow");
            };
            $("#" + o.___boxID).off().on("mouseenter", function () {
                BOXID = o;
            });
        },
        getID: function () {
            return thisID = BOXID.___boxID;
        },
        //构造弹出层
        showBox: function (o) {
            var $titleHeight = o.___showTitle != true ? 1 : 33,
				$borderHeight = o.___showTitle != true ? 0 : 10;
            $boxDialogHeight = o.___button != "" ? 45 : 0;
            $boxDialogBorder = $boxDialogHeight == "0" ? "0" : "1";
            var $width = parseInt(o.___width) > 1200 ? 1200 : parseInt(o.___width),
				$height = parseInt(o.___height) > 550 ? 550 : parseInt(o.___height);
            var $boxDom = "<div id=\"" + o.___boxID + "\" class=\"XYTipsWindow\">";
            $boxDom += "<div class=\"___boxWrap\">";
            $boxDom += "<div class=\"___boxTitle\"><h3></h3><span class=\"___closeBox\">关闭</span></div>";
            $boxDom += "<div class=\"___boxContent\"></div>";
            $boxDom += "<div class=\"___boxDialog\"></div>";
            $boxDom += "</div>";
            $boxDom += "<div class=\"___boxBd\"></div>";
            $boxDom += "<iframe src=\"about:blank\" style=\"position:absolute;left:0;top:0;filter:alpha(opacity=0);opacity:0;scrolling=no;z-index:10714\"></iframe>";
            $boxDom += "</div>";
            $($boxDom).appendTo("body");
            var $box = $("#" + o.___boxID);
            $box.css({
                position: "relative",
                width: $width + 12 + "px",
                height: $height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                zIndex: "891208"
            });
            var $iframe = $("iframe", $box);
            $iframe.css({
                width: $width + 12 + "px",
                height: $height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px"
            });
            var $boxWrap = $(".___boxWrap", $box);
            $boxWrap.css({
                position: "relative",
                top: "5px",
                margin: "0 auto",
                width: $width + 2 + "px",
                height: $height + $titleHeight + $boxDialogHeight + 1 + "px",
                overflow: "hidden",
                zIndex: "20590"
            });
            var $boxContent = $(".___boxContent", $box);
            $boxContent.css({
                position: "relative",
                width: $width + "px",
                height: $height + "px",
                padding: "0",
                borderWidth: "1px",
                borderStyle: "solid",
                borderColor: o.___boxWrapBdColor,
                overflow: "auto",
                backgroundColor: "#fff"
            });
            var $boxDialog = $(".___boxDialog", $box);
            $boxDialog.css({
                width: $width + "px",
                height: $boxDialogHeight + "px",
                borderWidth: $boxDialogBorder + "px",
                borderStyle: "solid",
                borderColor: o.___boxWrapBdColor,
                borderTop: "none",
                textAlign: "right"
            });
            var $boxBg = $(".___boxBd", $box);
            $boxBg.css({
                position: "absolute",
                width: $width + 12 + "px",
                height: $height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                left: "0",
                top: "0",
                opacity: o.___boxBdOpacity,
                background: o.___boxBdColor,
                zIndex: "10715"
            });
            var $title = $(".___boxTitle>h3", $box);
            $title.html(o.___title);
            $title.parent().css({
                position: "relative",
                width: $width + "px",
                borderColor: o.___boxWrapBdColor
            });
            if (o.___titleClass != "") {
                $title.parent().addClass(o.___titleClass);
                $title.parent().find("span").hover(function () {
                    $(this).addClass("hover");
                }, function () {
                    $(this).removeClass("hover");
                });
            };
            if (o.___showTitle != true) { $(".___boxTitle", $box).remove(); }
            if (o.___showBoxbg != true) {
                $(".___boxBd", $box).remove();
                $box.css({
                    width: $width + 2 + "px",
                    height: $height + $titleHeight + $boxDialogHeight + 1 + "px"
                });
                $boxWrap.css({ left: "0", top: "0" });

            };

            $(".___closeBox", $box).off().on("click", function () {
                $.XYTipsWindow.removeBox();
            }).css({ zIndex: "870618" });
            //定位弹出层
            var TOP = -1;
            $.XYTipsWindow.getDomPosition(o);
            var $location = o.___offsets;
            var $wrap = $("<div id=\"" + o.___boxID + "parent\"></div>");
            var est = isIE6 ? (o.___triggerID != "" ? 0 : document.documentElement.scrollTop) : "";
            if (o.___offsets == "" || o.___offsets.constructor == String) {
                switch ($location) {
                    case ("left-top")://左上角
                        $location = { left: "0px", top: "0px" + est };
                        TOP = 0;
                        break;
                    case ("left-bottom")://左下角
                        $location = { left: "0px", bottom: "0px" };
                        break;
                    case ("right-top")://右上角
                        $location = { right: "0px", top: "0px" + est };
                        TOP = 0;
                        break;
                    case ("right-bottom")://右下角
                        $location = { right: "0px", bottom: "0px" };
                        break;
                    case ("middle-top")://居中置顶
                        $location = { left: "50%", marginLeft: -parseInt($box.width() / 2) + "px", top: "0px" + est };
                        TOP = 0;
                        break;
                    case ("middle-bottom")://居中置低
                        $location = { left: "50%", marginLeft: -parseInt($box.width() / 2) + "px", bottom: "0px" };
                        break;
                    case ("left-middle")://左边居中
                        $location = { left: "0px", top: "50%" + est, marginTop: -parseInt($box.height() / 2) + "px" + est };
                        TOP = $getPageSize[1] / 2 - $box.height() / 2;
                        break;
                    case ("right-middle")://右边居中
                        $location = { right: "0px", top: "50%" + est, marginTop: -parseInt($box.height() / 2) + "px" + est };
                        TOP = $getPageSize[1] / 2 - $box.height() / 2;
                        break;
                    default://默认为居中
                        $location = { left: "50%", marginLeft: -parseInt($box.width() / 2) + "px", top: "50%" + est, marginTop: -parseInt($box.height() / 2) + "px" + est };
                        TOP = $getPageSize[1] / 2 - $box.height() / 2;
                        break;
                };
            } else {
                var str = $location.top;
                $location.top = $location.top + est;
                if (typeof (str) != 'undefined') {
                    str = str.replace("px", "");
                    TOP = str;
                };
            };
            if (o.___triggerID != "") {
                var $offset = $("#" + o.___triggerID).offset();
                var triggerID_W = $("#" + o.___triggerID).outerWidth(), triggerID_H = $("#" + o.___triggerID).outerHeight();
                var triggerID_Left = $offset.left, triggerID_Top = $offset.top;
                var vL = $location.left, vT = $location.top;
                if (typeof (vL) != 'undefined' || typeof (vT) != 'undefined') {
                    vL = parseInt(vL.replace("px", ""));
                    vT = parseInt(vT.replace("px", ""));
                };
                var ___left = vL >= 0 ? parseInt(vL) + triggerID_Left : parseInt(vL) + triggerID_Left - $getPageSize[2];
                var ___top = vT >= 0 ? parseInt(vT) + triggerID_Top : parseInt(vT) + triggerID_Top - $getPageSize[3];
                $location = { left: ___left + "px", top: ___top + "px" };
            };
            if (isIE6) {
                if (o.___triggerID == "") {
                    if (TOP >= 0) {
                        $.XYTipsWindow.addStyle(".ui_fixed_" + o.___boxID + "{width:100%;height:100%;position:absolute;left:expression(documentElement.scrollLeft+documentElement.clientWidth-this.offsetWidth);top:expression(documentElement.scrollTop+" + TOP + ")}");
                        $wrap = $("<div class=\"" + o.___boxID + "IE6FIXED\" id=\"" + o.___boxID + "parent\"></div>");
                        $box.appendTo($wrap);
                        $("body").append($wrap);
                        $("." + o.___boxID + "IE6FIXED").css($location).css({
                            position: "absolute",
                            width: $width + 12 + "px",
                            height: $height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                            zIndex: "891208"
                        }).addClass("ui_fixed_" + o.___boxID);
                    } else {
                        $.XYTipsWindow.addStyle(".ui_fixed2_" + o.___boxID + "{width:100%;height:100%;position:absolute;left:expression(documentElement.scrollLeft+documentElement.clientWidth-this.offsetWidth);top:expression(documentElement.scrollTop+documentElement.clientHeight-this.offsetHeight)}");
                        $wrap = $("<div class=\"" + o.___boxID + "IE6FIXED\"  id=\"" + o.___boxID + "parent\"></div>");
                        $box.appendTo($wrap);
                        $("body").append($wrap);
                        $("." + o.___boxID + "IE6FIXED").css($location).css({
                            position: "absolute",
                            width: $width + 12 + "px",
                            height: $height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                            zIndex: "891208"
                        }).addClass("ui_fixed2_" + o.___boxID);
                    };
                    $("body").css("background-attachment", "fixed").css("background-image", "url(n1othing.txt)");
                } else {
                    $wrap.css({
                        position: "absolute",
                        left: ___left + "px",
                        top: ___top + "px",
                        width: $width + 12 + "px",
                        height: $height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                        zIndex: "891208"
                    });
                };
            } else {
                $wrap.css($location).css({
                    position: "fixed",
                    width: $width + 12 + "px",
                    height: $height + $titleHeight + $borderHeight + $boxDialogHeight + 1 + "px",
                    zIndex: "891208"
                });
                if (o.___triggerID != "") { $wrap.css({ position: "absolute" }) };
                $("body").append($wrap);
                $box.appendTo($wrap);
            };
        },
        //装载弹出层内容
        contentBox: function (o) {
            var $box = $("#" + o.___boxID);
            var $width = parseInt(o.___width) > 1000 ? 1000 : parseInt(o.___width),
				$height = parseInt(o.___height) > 550 ? 550 : parseInt(o.___height);
            var $contentID = $(".___boxContent", $box);
            $contentType = o.___content.substring(0, o.___content.indexOf(":"));
            $content = o.___content.substring(o.___content.indexOf(":") + 1, o.___content.length);
            $.ajaxSetup({ global: false });
            switch ($contentType) {
                case "text":
                    $contentID.html($content);
                    break;
                case "id":
                    $("#" + $content).children().appendTo($contentID);
                    break;
                case "img":
                    $.ajax({
                        beforeSend: function () {
                            $contentID.html("<p class='boxLoading'>loading...</p>");
                        },
                        error: function () {
                            $contentID.html("<p class='boxError'>加载数据出错...</p>");
                        },
                        success: function (html) {
                            $contentID.html("<img src=" + $content + " alt='' />");
                        }
                    });
                    break;
                case "swf":
                    $.ajax({
                        beforeSend: function () {
                            $contentID.html("<p class='boxLoading'>loading...</p>");
                        },
                        error: function () {
                            $contentID.html("<p class='boxError'>加载数据出错...</p>");
                        },
                        success: function (html) {
                            $contentID.html("<div id='" + o.___boxID + "swf'><h1>Alternative content</h1><p><a href=\"http://www.adobe.com/go/getflashplayer\"><img src=\"http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif\" alt=\"Get Adobe Flash player\" /></a></p></div><script type=\"text/javascript\" src=\"swfobject.js\" ></script><script type=\"text/javascript\">swfobject.embedSWF('" + $content + "', '" + o.___boxID + "swf', '" + $width + "', '" + $height + "', '9.0.0', 'expressInstall.swf');</script>");
                            $("#" + o.___boxID + "swf").css({
                                position: "absolute",
                                left: "0",
                                top: "0",
                                textAlign: "center"
                            });
                        }
                    });
                    break;
                case "url":
                    var contentDate = $content.split("?");
                    $.ajax({
                        beforeSend: function () {
                            $contentID.html("<p class='boxLoading'>loading...</p>");
                        },
                        type: contentDate[0],
                        url: contentDate[1],
                        data: contentDate[2],
                        error: function () {
                            $contentID.html("<p class='boxError'><em></em><span>加载数据出错...</span></p>");
                        },
                        success: function (html) {
                            $contentID.html(html);
                        }
                    });
                    break;
                case "iframe":
                    $contentID.css({ overflowY: "hidden" });
                    $.ajax({
                        type: 'post',
                        beforeSend: function () {
                            $contentID.html("<p class='boxLoading'>loading...</p>");
                        },
                        error: function () {
                            $contentID.html("<p class='boxError'>加载数据出错...</p>");
                        },
                        success: function (html) {
                            $contentID.html("<iframe src=\"" + $content + "\" width=\"100%\" height=\"" + parseInt(o.___height) + "px\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
                        }
                    });
            };
        },
        //对话模式
        ask: function (o) {
            var $box = $("#" + o.___boxID);
            $boxDialog = $(".___boxDialog", $box);
            if (o.___button != "") {
                var map = {}, answerStrings = [];
                if (o.___button instanceof Array) {
                    for (var i = 0; i < o.___button.length; i++) {
                        map[o.___button[i]] = o.___button[i];
                        answerStrings.push(o.___button[i]);
                    };
                } else {
                    for (var k in o.___button) {
                        map[o.___button[k]] = k;
                        answerStrings.push(o.___button[k]);
                    };
                };
                $boxDialog.html($.map(answerStrings, function (v) {
                    return "<input class='dialogBtn' type='button'  value='" + v + "' />";
                }).join(' '));
                $(".dialogBtn", $boxDialog).hover(function () {
                    $(this).addClass("hover");
                }, function () {
                    $(this).removeClass("hover");
                }).click(function () {
                    var $this = this;
                    if (o.___callback != "" && $.isFunction(o.___callback)) {
                        //设置回调函数返回值很简单，就是回调函数名后加括号括住的返回值就可以了。
                        o.___callback(map[$this.value]);
                    };
                    $.XYTipsWindow.removeBox(o);
                });
            };
        },
        //获取要吸附的ID的left和top值并重新计算弹出层left和top值
        getDomPosition: function (o) {
            var $box = $("#" + o.___boxID);
            var cw = document.documentElement.clientWidth, ch = document.documentElement.clientHeight;
            var sw = $box.outerWidth(), sh = $box.outerHeight();
            var $soffset = $box.offset(), sl = $soffset.left, st = $soffset.top;
            $getPageSize = new Array();
            $getPageSize.push(cw, ch, sw, sh, sl, st);
        },
        //设置窗口的zIndex
        setBoxzIndex: function (o) {
            $XYTipsWindowarr.push(document.getElementById(o.___boxID + "parent"));//存储窗口到数组
            var ___event = "mousedown" || "click";
            var $box = $("#" + o.___boxID + "parent");
            $box.off().on("click", function () {
                for (var i = 0; i < $XYTipsWindowarr.length; i++) {
                    $XYTipsWindowarr[i].style.zIndex = 870618;
                };
                this.style.zIndex = 891208;
            });
        },
        //写入CSS样式
        addStyle: function (s) {
            var T = this.style;
            if (!T) {
                T = this.style = document.createElement('style');
                T.setAttribute('type', 'text/css');
                document.getElementsByTagName('head')[0].appendChild(T);
            };
            T.styleSheet && (T.styleSheet.cssText += s) || T.appendChild(document.createTextNode(s));
        },
        //绑定拖拽
        dragBox: function (o) {
            var $moveX = 0, $moveY = 0,
				drag = false;
            var $ID = $("#" + o.___boxID);
            $Handle = $("." + o.___drag, $ID);
            $Handle.mouseover(function () {
                if (o.___triggerID != "") {
                    $(this).css("cursor", "default");
                } else {
                    $(this).css("cursor", "move");
                };
            });
            $Handle.mousedown(function (e) {
                drag = o.___triggerID != "" ? false : true;
                if (o.___dragBoxOpacity) {
                    if (o.___boxBdOpacity != "1") {
                        $ID.children("div").css("opacity", o.___dragBoxOpacity);
                        $ID.children("div.___boxBd").css("opacity", o.___boxBdOpacity);
                    } else {
                        $ID.children("div").css("opacity", o.___dragBoxOpacity);
                    };
                };
                e = window.event ? window.event : e;
                var ___ID = document.getElementById(o.___boxID);
                $moveX = e.clientX - ___ID.offsetLeft;
                $moveY = e.clientY - ___ID.offsetTop;
                $(document).mousemove(function (e) {
                    if (drag) {
                        e = window.event ? window.event : e;
                        window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
                        var ___x = e.clientX - $moveX;
                        var ___y = e.clientY - $moveY;
                        $(___ID).css({
                            left: ___x,
                            top: ___y
                        });
                    };
                });
                $(document).mouseup(function () {
                    drag = false;
                    if (o.___dragBoxOpacity) {
                        if (o.___boxBdOpacity != "1") {
                            $ID.children("div").css("opacity", "1");
                            $ID.children("div.___boxBd").css("opacity", o.___boxBdOpacity);
                        } else {
                            $ID.children("div").css("opacity", "1");
                        };
                    };
                });
            });
        },
        //关闭弹出层
        removeBox: function () {
            var $box = $("#" + BOXID.___boxID);
            var $boxbg = $("#XYTipsWindowBg");
            if ($box != null || $boxbg != null) {
                var $contentID = $(".___boxContent", $box);
                $contentType = BOXID.___content.substring(0, BOXID.___content.indexOf(":"));
                $content = BOXID.___content.substring(BOXID.___content.indexOf(":") + 1, BOXID.___content.length);
                if ($contentType == "id") {
                    $contentID.children().appendTo($("#" + $content));
                    $box.parent().removeAttr("style").remove();
                    $boxbg.animate({ opacity: "0" }, 500, function () { $(this).remove(); });
                    $("body").css("background", "#fff");
                } else {
                    $box.parent().removeAttr("style").remove();
                    $boxbg.animate({ opacity: "0" }, 500, function () { $(this).remove(); });
                    $("body").css("background", "#fff");
                };
            };
        },
        //健盘事件，当按Esc的时候关闭弹出层
        keyDown: function (o) {
            document.onkeydown = function (e) {
                e = e || event;
                if (e.keyCode == 27) {
                    $.XYTipsWindow.removeBox();
                };
            };
        }
    });
})(jQuery);

// JavaScript Document
(function ($) {
    "use strict";
    // A nice closure for our definitions

    function getjQueryObject(string) {
        // Make string a vaild jQuery thing
        var jqObj = $("");
        try {
            jqObj = $(string).clone();
        } catch (e) {
            jqObj = $("<span />").html(string);
        }
        return jqObj;
    }

    function isNode(o) {
        /* http://stackoverflow.com/a/384380/937891 */
        return !!(typeof Node === "object" ? o instanceof Node : o && typeof o === "object" && typeof o.nodeType === "number" && typeof o.nodeName === "string");
    }


    $.print = $.fn.print = function () {
        // Print a given set of elements

        var options, $this, self = this;

        // console.log("Printing", this, arguments);

        if (self instanceof $) {
            // Get the node if it is a jQuery object
            self = self.get(0);
        }

        if (isNode(self)) {
            // If `this` is a HTML element, i.e. for
            // $(selector).print()
            $this = $(self);
            if (arguments.length > 0) {
                options = arguments[0];
            }
        } else {
            if (arguments.length > 0) {
                // $.print(selector,options)
                $this = $(arguments[0]);
                if (isNode($this[0])) {
                    if (arguments.length > 1) {
                        options = arguments[1];
                    }
                } else {
                    // $.print(options)
                    options = arguments[0];
                    $this = $("html");
                }
            } else {
                // $.print()
                $this = $("html");
            }
        }

        // Default options
        var defaults = {
            globalStyles: true,
            mediaPrint: false,
            stylesheet: null,
            noPrintSelector: ".no-print",
            iframe: true,
            append: null,
            prepend: null
        };
        // Merge with user-options
        options = $.extend({}, defaults, (options || {}));

        var $styles = $("");
        if (options.globalStyles) {
            // Apply the stlyes from the current sheet to the printed page
            $styles = $("style, link, meta, title");
        } else if (options.mediaPrint) {
            // Apply the media-print stylesheet
            $styles = $("link[media=print]");
        }
        if (options.stylesheet) {
            // Add a custom stylesheet if given
            $styles = $.merge($styles, $('<link rel="stylesheet" href="' + options.stylesheet + '">'));
        }

        // Create a copy of the element to print
        var copy = $this.clone();
        // Wrap it in a span to get the HTML markup string
        copy = $("<span/>").append(copy);
        // Remove unwanted elements
        copy.find(options.noPrintSelector).remove();
        // Add in the styles
        copy.append($styles.clone());
        // Appedned content
        copy.append(getjQueryObject(options.append));
        // Prepended content
        copy.prepend(getjQueryObject(options.prepend));
        // Get the HTML markup string
        var content = copy.html();
        // Destroy the copy
        copy.remove();

        var w, wdoc;
        if (options.iframe) {
            // Use an iframe for printing
            try {
                var $iframe = $(options.iframe + "");
                var iframeCount = $iframe.length;
                if (iframeCount === 0) {
                    // Create a new iFrame if none is given
                    $iframe = $('<iframe height="0" width="0" border="0" wmode="Opaque"/>').prependTo('body').css({
                        "position": "absolute",
                        "top": -999,
                        "left": -999
                    });
                }
                w = $iframe.get(0);
                w = w.contentWindow || w.contentDocument || w;
                wdoc = w.document || w.contentDocument || w;
                wdoc.open();
                wdoc.write(content);
                wdoc.close();
                setTimeout(function () {
                    // Fix for IE : Allow it to render the iframe
                    w.focus();
                    w.print();
                    setTimeout(function () {
                        // Fix for IE
                        if (iframeCount === 0) {
                            // Destroy the iframe if created here
                            $iframe.remove();
                        }
                    }, 100);
                }, 250);
            } catch (e) {
                // Use the pop-up method if iframe fails for some reason
                console.error("Failed to print from iframe", e.stack, e.message);
                w = window.open();
                w.document.write(content);
                w.document.close();
                w.focus();
                w.print();
                w.close();
            }
        } else {
            // Use a new window for printing
            w = window.open();
            w.document.write(content);
            w.document.close();
            w.focus();
            w.print();
            w.close();
        }
        return this;
    };

})(jQuery);

$(function () {
    $(".shuffScroll> a:gt(5)").hide();
    $(".shuffScroll> a:lt(6)").show();


    $(".nextBtn img").eq(0).click(function () {
        $(".shuffScroll> a:gt(5)").hide();
        $(".shuffScroll> a:lt(6)").show();

        //  if ($(this).attr("data-value") != '1') {
        //   $(this).attr("data-value", '1');
        $(this).attr("src", "/Content/themes/img/prev.jpg");
        $(".nextBtn img").eq(1).attr("src", "/Content/themes/img/next.jpg");
        // }
    });
    $(".nextBtn img").eq(1).click(function () {
        $(".shuffScroll> a:gt(5)").show();
        $(".shuffScroll> a:lt(6)").hide();
        // if ($(this).attr("data-value") != '0') {
        //    $(this).attr("data-value", '0');
        $(this).attr("src", "/Content/themes/img/prev.jpg");
        $(".nextBtn img").eq(0).attr("src", "/Content/themes/img/next.jpg");
        // }
    });

    //点击search里面的input关键字消失
    /*
    $(".search>div>div>input").focus(function () {
        var value = $(this).val();
        $(this).val("");
        $(this).blur(function () {
            $(this).val(value);
            $(this).next(".kuang").hide();
        });
        $(this).next(".kuang").show();
    });

    $(".search .date input").click(function () {
        $(this).val("");
    });
    */
    //map出现
    $(".map img").hover(function () {
        $(".hideMap").show();
    }, function () {
        $(".hideMap").hide();
    })

    /*//点击radio切换
	$("#main .address").eq(0).find("input").click(function(){
		alert($(this).index())
	})*/


    //$(".MygreenDiv").mouseover(function (e) {
    //    e.stopPropagation();
    //    $(".MygreenDiv").show();
    //    $(".Hovergreen").css({ "border-top": "#7fa5a3 1px solid", "border-left": "#7fa5a3 1px solid", "border-right": "#7fa5a3 1px solid", "width": "118px" })
    //}).mouseleave(function () {
    //    //e.stopPropagation();
    //    $(this).hide();
    //    $(".Hovergreen").css({ "border": "#fff 1px solid", "width": "118px" });
    //})

    //$("#nav li").mouseover(function (e) {
    //    e.stopPropagation();
    //    $(this).find("div").show(100);
    //});

    //$("#nav li div").mouseleave(function (e) {
    //    e.stopPropagation();
    //    $(this).hide(100);
    //});

});


//基本的AJAX
(function ($) {
    $.fn.baseAjax = function (settings, callback, errCallback, showLoading, closeLoading) {
        var returnData = null;
        if (settings.async == null || typeof settings.async === 'undefined') {
            settings.async = true;
        }
        if ($.isFunction(showLoading)) {
            showLoading();
        }
        $.ajax({
            url: settings.url,
            type: settings.type ? settings.type : "POST",
            dataType: "json",
            data: settings.data,
            contentType: settings.contentType,
            processData: settings.processData,
            async: settings.async,
            timeout: 1000000,
            cache: false,

            success: function (json) {
             
                if ($.isFunction(callback)) {
                    returnData= callback(json);
                    if ($.isFunction(closeLoading)) {
                        closeLoading();
                    }
                }
                
            },
            error: function (jqXHR, textStatus, errorThrown) {

                $.fn.Logger(jqXHR);
                $.fn.Logger(errorThrown);
                if ($.isFunction(errCallback)) {
                    returnData= errCallback();
                    
                    if ($.isFunction(closeLoading)) {
                        closeLoading();
                    }
                }
                
            }

        });
        return returnData;
    }
})(jQuery);


//postAjax
(function ($) {
    $.fn.postAjax = function (options, callback, errCallback, showLoading) {
        var settings = {
            type: "POST",
            timeout: 1000000
        };
        if (options) {
            $.extend(settings, options)
        }
        var returnData = $.fn.baseAjax(settings, callback, errCallback, showLoading);
        return returnData;
    };
})(jQuery);

//getAjax
(function ($) {
    $.fn.getAjax = function (options, callback, errCallback, showLoading) {
        var settings = {
            type: "GET",
            timeout: 1000000
        };
        if (options) {
            $.extend(settings, options)
        }
        var returnData = $.fn.baseAjax(settings, callback, errCallback, showLoading);
        return returnData;
    };
})(jQuery);


//Disabled 一个元素
(function ($) {
    $.fn.Disabled = function (elementId,bool) {
        var elementIdStr = '#' + elementId;
        var obj = $(elementIdStr);
        if (obj != 'undefined') {
            obj.attr("disabled", bool);
        }
    }
})(jQuery);

///*
// * jQuery placeholder, fix for IE6,7,8,9
// * @author JENA
// * @since 20131115.1504
// * @website ishere.cn
// */
//var JPlaceHolder = {
//	//检测
//	_check : function(){
//		return 'placeholder' in document.createElement('input');
//	},
//	//初始化
//	init : function(){
//		if(!this._check()){
//			this.fix();
//		}
//	},
//	//修复
//	fix : function(){
//		jQuery(':input[placeholder]').each(function(index, element) {
//            var self = $(this), txt = self.attr('placeholder');
//			self.wrap($('<div></div>').css({position:'relative', zoom:'1', border:'none', background:'none', padding:'none', margin:'none'}));
//			var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
//			var holder = $('<span></span>').text(txt).css({position:'absolute', left:pos.left, top:pos.top, height:h, lienHeight:h, paddingLeft:paddingleft, color:'#aaa'}).appendTo(self.parent());
//			self.focusin(function(e) {
//                holder.hide();
//            }).focusout(function(e) {
//                if(!self.val()){
//					holder.show();
//				}
//            });
//			holder.click(function(e) {
//                holder.hide();
//				self.focus();
//            });
//        });
//	}
//};
////执行
//jQuery(function(){
//	JPlaceHolder.init();	
//});
/*
        ShowMsg   
        notice通知型
        一般用于表单验证时的弹出信息
    */
var MSGN = function (_content) {
    if ($("body .msg-wrap1").length == 0) {
        var html = [];
        html.push('<div class="msg-mask"></div>');
        html.push('<div class="msg-wrap1">');
        html.push('<style>.msg-mask{position:fixed;top:0;left:0;z-index:1000000;width:100%;height:100%;background-color:rgba(0,0,0,.5)}.msg-wrap1,.msg-wrap2,.msg-wrap3{-webkit-user-select:none;position:fixed;left:50%;top:50%;z-index:1000001;border-radius:10px;padding:5px;margin-left:-130px;margin-top:-54px;font:Helvetica Neue,Helvetica,Arial,Lucida Grande,sans-serif;-webkit-animation-name:upIn;animation-name:upIn;-webkit-animation-duration:.3s;animation-duration:.3s;-webkit-animation-fill-mode:both;animation-fill-mode:both;opacity:0}.msg-wrap2,.msg-wrap3{margin-left:-145px!important}.msg-box{background:#fff;width:280px;margin:auto;position:relative;overflow:hidden;border-radius:10px;z-index:9999}.msg-padding{width:220px;line-height:24px;border-radius:5px;background:rgba(0,0,0,.7);padding:10px 15px;color:#fff;font-weight:700;word-break:break-all}.msg-error-tips,.msg-padding{text-align:center;font-size:14px}.msg-error-tips{color:#000;padding:25px}.msg-roller-btn{display:-webkit-box;display:-ms-flexbox;display:-webkit-flex;display:flex;width:100%;-webkit-box-flex:1;-webkit-flex:1;-ms-flex:1;flex:1;background:#fff;border-top:1px solid #f0f0f0;line-height:18px;text-align:center;box-sizing:border-box}.msg-roller-cancel{padding:14px 0}.msg-roller-cancel,.msg-roller-ok{color:#099fde;-webkit-box-flex:1;-webkit-flex:1;-ms-flex:1;flex:1;cursor:pointer}.msg-roller-ok{border-left:1px solid #f0f0f0;padding:13px 0}.msg-roller-cancel:active,.msg-roller-ok:active{opacity:.7}@-webkit-keyframes upIn{0%{opacity:0;-webkit-transform:translate3d(0,-100%,0);transform:translate3d(0,-100%,0)}to{opacity:1;-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}}@keyframes upIn{0%{opacity:0;-webkit-transform:translate3d(0,-100%,0);transform:translate3d(0,-100%,0)}to{opacity:1;-webkit-transform:translate3d(0,0,0);transform:translate3d(0,0,0)}}</style>')
        html.push('<div class="msg-padding">');
        html.push('<div class="msg-padding-content">');
        html.push(_content);
        html.push('</div></div></div>');
        $("body").append(html.join(""));
    }
    var setI = setTimeout(function () {
        $(".msg-mask,.msg-wrap1").remove();
    }, 2000);

    $(".msg-mask").unbind().bind("click", function () {
        $(".msg-mask,.msg-wrap1,#showmsgid").remove();
        clearTimeout(setI);
    });
}
function getCookie(cname)
{
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) 
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
}


//给选中的tab切换颜色   yjt  2016-10-19
function selectTab() {
    $(".header-bottom_Loc .bottom-head_Loc").each(function () {
        var tempSrc = $(this).find("img").attr("src").replace("X", "H");
        $(this).find("img").attr("src", tempSrc);
        $(this).find("h6").css("color", "#919090");
    });
    if ($(".header-bottom_Loc .bottom-head_Loc.ui-tab-active").length == 0)
        return;
    if ($(".header-bottom_Loc .bottom-head_Loc.ui-tab-active").find("img").attr("src").indexOf("X") > -1)
        return;


    
    var tempSrc = $(".header-bottom_Loc .bottom-head_Loc.ui-tab-active").find("img").attr("src").replace("H", "X");
    $(".header-bottom_Loc .bottom-head_Loc.ui-tab-active").find("img").attr("src", tempSrc);
    $(".header-bottom_Loc .bottom-head_Loc.ui-tab-active").find("h6").css("color", "#2A4F67");
}


function myBrowser() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
    var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
    if (isIE) {
        var IE5 = IE55 = IE6 = IE7 = IE8 = false;
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        IE55 = fIEVersion == 5.5;
        IE6 = fIEVersion == 6.0;
        IE7 = fIEVersion == 7.0;
        IE8 = fIEVersion == 8.0;
        if (IE55) {
            return "IE55";
        }
        if (IE6) {
            return "IE6";
        }
        if (IE7) {
            return "IE7";
        }
        if (IE8) {
            return "IE8";
        }
    }//isIE end
    if (isFF) {
        return "FF";
    }
    if (isOpera) {
        return "Opera";
    }
}//myBrowser() end