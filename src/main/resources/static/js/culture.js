/**
 * Created by liuw on 17-1-17.
 */
$(document).ready(function () {
    // 点击新增按钮
    $('#addCultureBtn').click(function () {
        $('#titleAdd').val('');
        $('#contentAdd').val('');
    });

    // 新增
    $('#addCultureInfo').click(function () {
        var title = $('#titleAdd').val();
        var content = $('#contentAdd').val();
        var alertIcon = "<i class='fa fa-exclamation-circle'></i>&nbsp;";
        if (title == '') {
            $('#warn-info').html(alertIcon + '标题不能为空');
            return;
        }
        if (content == '') {
            $('#warn-info').html(alertIcon + '内容不能为空');
            return;
        }
        var params = {
            title: title,
            content: content
        };
        $.ajax({
            type: "POST",
            url: "/culture/add",
            data: params,
            dataType: "json"
        }).done(function (result) {
            if(result.code == 0){
                $("#addCultureInfoModal").modal("hide");
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
                $('#warn-info').html(alertIcon + result.message);
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
    $("#saveCultureInfo").click(function(){
        var id = $('#id').val();
        var title = $('#title').val();
        var content = $('#content').val();
        var alertIcon = "<i class='fa fa-exclamation-circle'></i>&nbsp;";
        if (title == '') {
            $('#warn-info').html(alertIcon + '标题不能为空');
            return;
        }
        if (content == '') {
            $('#warn-info').html(alertIcon + '内容不能为空');
            return;
        }
        var params = {
            id: id,
            title: title,
            content: content
        };
        $.ajax({
            type: "POST",
            url: "/culture/update",
            data: params,
            dataType: "json"
        }).done(function (result) {
            if(result.code == 0){
                $("#editCultureInfo").modal("hide");
                iosOverlay({
                    text: "修改成功",
                    duration: 2e3,
                    icon: "/assets/img/check.png"
                });
                $("body").spinModal(false);
                setTimeout(function(){
                    location.reload(true);
                }, 1000);
            }else{
                $("body").spinModal(false);
                $('#warn-info').html(alertIcon + result.message);
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


});