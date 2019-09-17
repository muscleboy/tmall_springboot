// 获取URL参数，?cid=29  ->  返回29
function getUrlParam(param) {

    var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    // window.location以获取到url，所以不需要传入url
    var result = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (result != null)
        return unescape(result[2]);
    return null;
}

function checkEmpty(id, info) {

    var input = $("#id").val();
    if (input == ""){

        alert(info + "不能为空");
        return false;
    }
}