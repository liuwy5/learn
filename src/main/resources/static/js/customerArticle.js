/**
 * Created by liuw on 17-5-5.
 */
$(document).ready(function () {
    $('#answer').click(function () {
        var type = $('#type').val();
        var level = $('#level').val();
        var period = $('#period').val();
        var num = $('#num').val();
        var first = $('input[name="first"]:checked').val();
        var second = $('input[name="second"]:checked').val();
        var third = $('input[name="third"]:checked').val();
        var fourth = $('input[name="fourth"]:checked').val();
        var fifth = $('input[name="fifth"]:checked').val();

        if (first == '' || second == '' || third == '' || fourth == '' || fifth == '' ||
            first == 'null' || second == 'null' || third == 'null' || fourth == 'null' || fifth == 'null' ||
            first == undefined || second == undefined || third == undefined || fourth == undefined || fifth == undefined) {
            alert("请完成所有题目");
            return false;
        }

        var params = {
            type : type,
            level : level,
            period : period,
            num : num,
            first : first,
            second : second,
            third : third,
            fourth : fourth,
            fifth : fifth
        };
        $.ajax({
            type: "POST",
            url: "/customer/history/insertOrUpdate",
            data: params,
            dataType: "json",
            traditional: true
        }).done(function (result) {
            if(result.code == 0){
                alert("提交成功");
                $("body").spinModal(false);
                setTimeout(function(){
                    location.reload(true);
                }, 1000);
            }else{
                $("body").spinModal(false);
                alert(result.desc);
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