var signUpButton = document.getElementById('signUp');
var signInButton = document.getElementById('signIn');
var container = document.getElementById('dowebok');
var targetUrl =  "/";

signUpButton.addEventListener('click', function () {
    container.classList.add('right-panel-active')
});

signInButton.addEventListener('click', function () {
    container.classList.remove('right-panel-active')
});


$("#registerBtn").click(function () {
    if (!verify()) {
        showMsg("请填写有效数据");
        return;
    }
    register();
});

$("#loginBtn").click(function () {
    var user={
        'tel': $("#tel_login").val().trim(),
        'password': $("#password_login").val().trim(),
    }
    login(user)&&showMsg("登录成功");
});


/*登录*/
function login(user) {
    if (user.tel!=null && user.password!=null) {
            $.ajax({
                type:"post",
                url:"/login",
                dataType:"json",
                contentType: 'application/json; charset=UTF-8',
                data:JSON.stringify(user),
                success:function (data) {
                    console.log(data);
                    if (data.code==200){
                        localStorage.uid = data.data.uid;
                        localStorage.token = data.data.token;
                        localStorage.username = data.data.username;
                        window.location.href=targetUrl;
                        return true;
                    }else{
                        showMsg("登录失败: "+ data.message)
                    }
                },error:function (e) {
                    showMsg(e);
                }
            })
    }else{
        showMsg("请输入有效数据")
    }

}


/*注册*/
function register(){

    var registerData={
        'uname' : $("#uname").val(),
        'tel': $("#tel_register").val(),
        'password': $("#password-reg").val(),
        'code': $("#code").val()
    }
    $.ajax({
        type:"post",
        url:"/register",
        dataType:"json",
        contentType: 'application/json; charset=UTF-8',
        data:JSON.stringify(registerData),
        success:function (data) {
            if (data.code==200){
                showMsg("注册成功");
                var user = {
                    'tel': registerData.tel,
                    'password': registerData.password
                }
                console.log(login(user));
            }else {
                showMsg(data.message)
            }
        }
    })
}
/*数据校验*/
function verify(){
    if ($("#uname").val().trim() == ""||$("#password-reg").val().trim()==""||$("#code").val().trim()=="") {
       return false;
    }
    return true;
}

/*验证码*/
$(".codeBtn")[0].addEventListener('click',function () {
    var _self = this
    $.ajax({
        type:"get",
        url:"/pub/phoneCode",
        data:{
            tel:$("#tel_register").val()
        },success:function (data) {
            if (data.code == 200) {
                reSend(_self,60)
            }else{
                alert(data.message)
            }
        },error:function(e){
            alert("错误：" + e);
        },
    })

})


/*验证码倒计时*/
// 倒计时
function reSend(obj,time) {
    obj.setAttribute("disabled", true);
    obj.value = "重新发送(" + time + ")";
    time--;
    setTimeout(function () {
        if (time <= 0) {
            obj.value="免费获取验证码";
            obj.removeAttribute("disabled");
            return;
        }
        reSend(obj,time);
    }, 1000);
}
