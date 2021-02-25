/*
* header 头部导航栏动态展示
* */

;$(document).ready(function () {
    var userId = localStorage.uid;
    var token = localStorage.token;

    var loginNavItem = $("#cd-navigation #login");
    var mineNavItem = $("#cd-navigation #mine");

    if (userId != 0 && userId != undefined && token != "" && token != undefined) {
        /*以登录*/
        loginNavItem.addClass('hidden')
        mineNavItem.removeClass('hidden')
    } else {
        /*未登录*/
        loginNavItem.removeClass('hidden')
        mineNavItem.addClass('hidden')

    }
});
