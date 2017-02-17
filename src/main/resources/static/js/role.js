/**
 * Created by liuw on 17-2-13.
 */
$(document).ready(function () {
    // 点击新增按钮
    $('#addRoleBtn').click(function () {
        $('#warn-info').html('');
        $('#no').val('');
        $('#name').val('');
        var privilegeIdList = $("[name = privilegeId]:checkbox");
        for (var i in privilegeIdList) {
            privilegeIdList[i].checked = false;
        }
    });

    // 新增
    $('#addRoleInfo').click(function () {
        var no = $('#no').val();
        var name = $('#name').val();

        var count = 0;
        var privilegeIdList = $("[name = privilegeId]:checkbox");
        var idList = [];
        for (var i in privilegeIdList) {
            if(privilegeIdList[i].checked) {
                idList.push(privilegeIdList[i].value);
                count++;
            }
        }

        var alertIcon = "<i class='fa fa-exclamation-circle'></i>&nbsp;";
        if (no == '') {
            $('#warn-info').html(alertIcon + '角色号不能为空');
            return;
        }
        if (name == '') {
            $('#warn-info').html(alertIcon + '角色名不能为空');
            return;
        }
        if (count == 0) {
            $('#warn-info').html(alertIcon + '请选择权限菜单');
            return;
        }

        console.log(idList);

        var params = {
            no : no,
            name : name,
            privilegeIdList : idList
        };
        $.ajax({
            type: "POST",
            url: "/role/add",
            data: params,
            dataType: "json",
            traditional: true
        }).done(function (result) {
            if(result.code == 0){
                $("#addRoleInfoModal").modal("hide");
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
    $('#updateRoleInfo').click(function () {
        var id = $('#updateId').val();
        var no = $('#updateNo').val();
        var name = $('#updateName').val();
        var count = 0;
        var privilegeIdList = $("[name = privilegeUpdateId]:checkbox");
        var idList = [];
        for (var i in privilegeIdList) {
            if(privilegeIdList[i].checked) {
                idList.push(privilegeIdList[i].value);
                count++;
            }
        }

        var alertIcon = "<i class='fa fa-exclamation-circle'></i>&nbsp;";
        if (name == '') {
            $('#warn-info-update').html(alertIcon + '角色名不能为空');
            return;
        }
        if (count == 0) {
            $('#warn-info-update').html(alertIcon + '请选择权限菜单');
            return;
        }

        var params = {
            id : id,
            no : no,
            name : name,
            privilegeIdList : idList
        };
        $.ajax({
            type: "POST",
            url: "/role/update",
            data: params,
            dataType: "json",
            traditional: true
        }).done(function (result) {
            if(result.code == 0){
                $("#updateRoleInfoModal").modal("hide");
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
    });
    // 修改
    // $('#updateAdminInfo').click(function () {
    //     var id = $('#updateId').val();
    //     var loginName = $('#updateLoginName').val();
    //     var password = $('#updatePassword').val();
    //     var name = $('#updateName').val();
    //     var roleId = $('#updateRoleId').val();
    //
    //     $('#updateId').val(data.id);
    //     $('#updateNo').val(data.no);
    //     $('#updateName').val(data.name)
    //     var alertIcon = "<i class='fa fa-exclamation-circle'></i>&nbsp;";
    //     if (loginName == '') {
    //         $('#warn-info-update').html(alertIcon + '用户名不能为空');
    //         return;
    //     }
    //     if (password == '') {
    //         $('#warn-info-update').html(alertIcon + '密码不能为空');
    //         return;
    //     }
    //     if (name == '') {
    //         $('#warn-info-update').html(alertIcon + '姓名不能为空');
    //         return;
    //     }
    //     if (roleId == '') {
    //         $('#warn-info').html(alertIcon + '角色不能为空');
    //         return;
    //     }
    //     var params = {
    //         id: id,
    //         loginName : loginName,
    //         password : password,
    //         name : name,
    //         roleId : roleId
    //     };
    //     console.log(params);
    //     $.ajax({
    //         type: "POST",
    //         url: "/admin/update",
    //         data: params,
    //         dataType: "json"
    //     }).done(function (result) {
    //         if(result.code == 0){
    //             $("#updateAdminInfoModal").modal("hide");
    //             iosOverlay({
    //                 text: "更新成功",
    //                 duration: 2e3,
    //                 icon: "/assets/img/check.png"
    //             });
    //             $("body").spinModal(false);
    //             setTimeout(function(){
    //                 location.reload(true);
    //             }, 1000);
    //         }else{
    //             $("body").spinModal(false);
    //             $('#warn-info-update').html(alertIcon + result.message);
    //         }
    //     }).fail(function(){
    //         setTimeout(function(){
    //             $("body").spinModal(false);
    //             iosOverlay({
    //                 text: '服务异常',
    //                 duration: 2e3,
    //                 icon: "/assets/img/error.png"
    //             });
    //         },1500);
    //     });
    // })
});