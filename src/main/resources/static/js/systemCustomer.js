/**
 * Created by liuw on 17-2-10.
 */
$(document).ready(function () {
    // 点击新增按钮
    $('#addAdminBtn').click(function () {
        $('#loginName').val('');
        $('#name').val('');
        $('#name').val('');
        $('#warn-info').html('');
    });

    // 新增
    $('#addAdminInfo').click(function () {
        var loginName = $('#loginName').val();
        var password = $('#password').val();
        var name = $('#name').val();
        var roleId = $('#roleId').val();
        var alertIcon = "<i class='fa fa-exclamation-circle'></i>&nbsp;";
        if (loginName == '') {
            $('#warn-info').html(alertIcon + '用户名不能为空');
            return;
        }
        if (password == '') {
            $('#warn-info').html(alertIcon + '密码不能为空');
            return;
        }
        if (name == '') {
            $('#warn-info').html(alertIcon + '姓名不能为空');
            return;
        }
        if (roleId == '') {
            $('#warn-info').html(alertIcon + '角色不能为空');
            return;
        }
        var params = {
            loginName : loginName,
            password : password,
            name : name,
            roleId : roleId
        };
        $.ajax({
            type: "POST",
            url: "/admin/add",
            data: params,
            dataType: "json"
        }).done(function (result) {
            if(result.code == 0){
                $("#addAdminInfoModal").modal("hide");
                iosOverlay({
                    text: "添加成功",
                    duration: 2e3,
                    icon: "/assets/img/check.png"
                });
                $("body").spinModal(false);
                setTimeout(function(){
                    location.reload(true);
                }, 1000);
            }else{
                $("body").spinModal(false);
                $('#warn-info').html(alertIcon + result.desc);
            }
        }).fail(function(){
            setTimeout(function(){
                $("body").spinModal(false);
                iosOverlay({
                    text: '服务异常',
                    duration: 2e3,
                    icon: "/assets/img/error.png"
                });
            },1500);
        });
    });

    // 修改
    $('#updateAdminInfo').click(function () {
        var id = $('#updateId').val();
        var loginName = $('#updateLoginName').val();
        var password = $('#updatePassword').val();
        var name = $('#updateName').val();
        var roleId = $('#updateRoleId').val();
        var alertIcon = "<i class='fa fa-exclamation-circle'></i>&nbsp;";
        if (loginName == '') {
            $('#warn-info-update').html(alertIcon + '用户名不能为空');
            return;
        }
        if (password == '') {
            $('#warn-info-update').html(alertIcon + '密码不能为空');
            return;
        }
        if (name == '') {
            $('#warn-info-update').html(alertIcon + '姓名不能为空');
            return;
        }
        if (roleId == '') {
            $('#warn-info-update').html(alertIcon + '角色不能为空');
            return;
        }
        var params = {
            id: id,
            loginName : loginName,
            password : password,
            name : name,
            roleId : roleId
        };
        console.log("update" + params);
        $.ajax({
            type: "POST",
            url: "/admin/update",
            data: params,
            dataType: "json"
        }).done(function (result) {
            console.log(result);
            if(result.code == 0){
                $("#updateAdminInfoModal").modal("hide");
                iosOverlay({
                    text: "更新成功",
                    duration: 2e3,
                    icon: "/assets/img/check.png"
                });
                $("body").spinModal(false);
                setTimeout(function(){
                    location.reload(true);
                }, 1000);
            }else{
                $("body").spinModal(false);
                $('#warn-info-update').html(alertIcon + result.desc);
            }
        }).fail(function(){
            setTimeout(function(){
                $("body").spinModal(false);
                iosOverlay({
                    text: '服务异常',
                    duration: 2e3,
                    icon: "/assets/img/error.png"
                });
            },1500);
        });
    })
});