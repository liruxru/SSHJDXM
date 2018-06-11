/***
* 加载动画
*Create By JoneLu 
*2016-09-13
*/
//$(function () {
var wordTime, oldWord = $(".gtow_greentreeWord").text();
var filterPage = ['/HotelList/Index', '/HotelDetail/Index', '/HotelOrder/Index', '/HotelList', '/HotelDetail', '/HotelOrder/OrderSuccess'];
var setFilter = false;

var LoadJs = {
    IsShow:true,//是否显示ajax
    Init: function () {
        if ($("#gtow_greentreeLoad").length == 0) {
            var html = [];
            html.push("<div id='gtow_greentreeLoad'>");
            html.push("<div class='gtow_greentreeGif'>");
            html.push("</div>");
            html.push("<span class='gtow_greentreeWord'><span>")
            html.push("</div>");
            $("body").append(html.join(""));
        }
        $("#gtow_greentreeLoad").show();
    },
    InitWord: function (flag) {//文字初始化 flag:true->重置加载文字
        if (flag==true || $(".gtow_greentreeWord").text() == "" || $(".gtow_greentreeWord").text().indexOf("Loading，Please Wait") > -1 || $(".gtow_greentreeWord").text().indexOf("正在加载，请稍后") > -1)
        {
            if (getCookie("GTOWLang") != "CHS" && getCookie("GTOWLang") != null && typeof (getCookie("GTOWLang")) != "undefined" && getCookie("GTOWLang") != "") {
                $(".gtow_greentreeWord").text("Loading，Please Wait");
                oldWord = "Loading，Please Wait";
            }
            else {
                $(".gtow_greentreeWord").text("正在加载，请稍后");
                oldWord = "正在加载，请稍后";
            }
        }
        points = "";
        steps = 0;
    },
    set: function (text) {//设置load显示的值
        $(".gtow_greentreeWord").text(text);
        oldWord = text;
    },
    setVisiable:function(){
        this.IsShow == false;
        $("#gtow_greentreeLoad").hide();
    }
};


for (var i = 0; i < filterPage.length; i++) {
    if (window.location.href.indexOf(filterPage[i]) > -1) {
        setFilter = true;
        break;
    }
}


if (!setFilter) {
    $(document).ajaxStart(function () {
        //组件初始化
        LoadJs.Init();
        //文字初始化
        LoadJs.InitWord();
        if (LoadJs.IsShow == false) {
            $("#gtow_greentreeLoad").hide();
        }
        else {
            $("#gtow_greentreeLoad").show();
        }
        wordTime = setTimeout(AddPoint, 500);
    });

    $(document).ajaxStop(function () {
        $("#gtow_greentreeLoad").hide();
        window.clearTimeout(wordTime);
        //重新获取:初始化
        LoadJs.IsShow = true;
        LoadJs.InitWord(true);

    });

}
   




    var steps = 0;
    var points = "";
    //定时器，显示加载“点”
    function AddPoint() {
        if (steps < 3) {
            points += ".";
            $(".gtow_greentreeWord").text(oldWord + points);
            steps++;
        }
        else {
            $(".gtow_greentreeWord").text(oldWord);
            steps = 0;
            points = "";
        }
        wordTime = window.setTimeout(AddPoint, 500);
    }


    
    $(document).ajaxError(function () {
        $.fn.showAlert("遇到不可预知的错误!");
    });


/*获取Cookie*/
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}
//});




