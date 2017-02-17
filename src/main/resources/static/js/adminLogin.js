/**
 * Created by ubuntu on 16-12-17.
 */
$(document).ready(function () {
    $('#loginButton').click(function () {
        var loginName = $('#loginName').val();
        var loginPwd = $('#loginPwd').val();

        if ('' == loginName) {
            $('.login-alert').html('用户名不能为空');
            return false;
        } else if ('' == loginPwd) {
            $('.login-alert').html('密码不能为空');
            return false;
        }
        $.ajax({
            url:"/admin/login/auth",
            type:'post',
            data:$('#loginForm').serialize(),
            success:function (data) {
                if (data.code == 1) {
                    $('.login-alert').html("登录名不存在");
                } else if (data.code == 2) {
                    $('.login-alert').html("登录密码不正确");
                } else if (data.code == 0) {
                    location.href = data.url;
                }
            }
        })
    });
});