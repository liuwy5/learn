/**
 * Created by liuw on 17-1-16.
 */
$(document).ready(function () {
    // 登录框显示
    $('#login').click(function () {
        $('#loginName').val('');
        $('#loginPwd').val('');
        $("#login-alert").html("");
    });

    // 点击登录按钮
    $('#loginButton').click(function () {
        var loginName = $('#loginName').val().trim();
        var loginPwd = $('#loginPwd').val().trim();
        var interestId = $('#interestId').val().trim();
        var url = '/chat/group/' + interestId;

        if ('' == loginName) {
            $('.login-alert').html('用户名不能为空');
            return false;
        } else if ('' == loginPwd) {
            $('.login-alert').html('密码不能为空');
            return false;
        }
        $.ajax({
            url:"/customer/login/auth",
            type:'post',
            data:$('#loginForm').serialize(),
            success:function (data) {
                if (data == 1) {
                    $('.login-alert').html("登录名不存在");
                } else if (data == 2) {
                    $('.login-alert').html("登录密码不正确");
                } else if (data == 0) {
                    location.href = url;
                }
            }
        })
    });

});