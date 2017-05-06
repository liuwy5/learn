/**
 * Created by liuw on 17-4-30.
 */
$(document).ready(function () {
    // 点击新增按钮
    $('#addArticleBtn').click(function () {
        $('#warn-info-add').html('');　
        $('#titleAdd').val('');
        $('#contentAdd').val('');
        $('#firstQuestionAdd').val('');
        $('#firstAAdd').val('');
        $('#firstBAdd').val('');
        $('#firstCAdd').val('');
        $('#firstDAdd').val('');
        $('#firstAnswerAdd').val('a');
        $('#firstExplainAdd').val('aaaaa');
        $('#secondQuestionAdd').val('');
        $('#secondAAdd').val('');
        $('#secondBAdd').val('');
        $('#secondCAdd').val('');
        $('#secondDAdd').val('');
        $('#secondAnswerAdd').val('a');
        $('#secondExplainAdd').val('');
        $('#thirdQuestionAdd').val('');
        $('#thirdAAdd').val('');
        $('#thirdBAdd').val('');
        $('#thirdCAdd').val('');
        $('#thirdDAdd').val('');
        $('#thirdAnswerAdd').val('a');
        $('#thirdExplainAdd').val('');
        $('#fourthQuestionAdd').val('');
        $('#fourthAAdd').val('');
        $('#fourthBAdd').val('');
        $('#fourthCAdd').val('');
        $('#fourthDAdd').val('');
        $('#fourthAnswerAdd').val('a');
        $('#fourthExplainAdd').val('');
        $('#fifthQuestionAdd').val('');
        $('#fifthAAdd').val('');
        $('#fifthBAdd').val('');
        $('#fifthCAdd').val('');
        $('#fifthDAdd').val('');
        $('#fifthAnswerAdd').val('a');
        $('#fifthExplainAdd').val('');
    });

    // 新增
    $('#addArticleInfo').click(function () {
        var type = $('#learnTypeAdd').val();
        var level = $('#learnLevelAdd').val();
        var period = $('#periodAdd').val();
        var title = $('#titleAdd').val();
        var content = $('#contentAdd').val();
        var firstQuestion = $('#firstQuestionAdd').val();
        var firstA = $('#firstAAdd').val();
        var firstB = $('#firstBAdd').val();
        var firstC = $('#firstCAdd').val();
        var firstD = $('#firstDAdd').val();
        var firstAnswer = $('#firstAnswerAdd option:selected').val();
        var firstExplain = $('#firstExplainAdd').val();
        var secondQuestion = $('#secondQuestionAdd').val();
        var secondA = $('#secondAAdd').val();
        var secondB = $('#secondBAdd').val();
        var secondC = $('#secondCAdd').val();
        var secondD = $('#secondDAdd').val();
        var secondAnswer = $('#secondAnswerAdd option:selected').val();
        var secondExplain = $('#secondExplainAdd').val();
        var thirdQuestion = $('#thirdQuestionAdd').val();
        var thirdA = $('#thirdAAdd').val();
        var thirdB = $('#thirdBAdd').val();
        var thirdC = $('#thirdCAdd').val();
        var thirdD = $('#thirdDAdd').val();
        var thirdAnswer = $('#thirdAnswerAdd option:selected').val();
        var thirdExplain = $('#thirdExplainAdd').val();
        var fourthQuestion = $('#fourthQuestionAdd').val();
        var fourthA = $('#fourthAAdd').val();
        var fourthB = $('#fourthBAdd').val();
        var fourthC = $('#fourthCAdd').val();
        var fourthD = $('#fourthDAdd').val();
        var fourthAnswer = $('#fourthAnswerAdd option:selected').val();
        var fourthExplain = $('#fourthExplainAdd').val();
        var fifthQuestion = $('#fifthQuestionAdd').val();
        var fifthA = $('#fifthAAdd').val();
        var fifthB = $('#fifthBAdd').val();
        var fifthC = $('#fifthCAdd').val();
        var fifthD = $('#fifthDAdd').val();
        var fifthAnswer = $('#fifthAnswerAdd option:selected').val();
        var fifthExplain = $('#fifthExplainAdd').val();

        console.log('fir' + firstExplain);
        alert('firstExplain' + firstExplain);

        if (title == '') {
            alert('标题不能为空');
            return;
        }
        if (content == '') {
            alert('内容不能为空');
            return;
        }
        if (firstQuestion == '' || firstA == '' || firstB == '' ||
            firstC == '' || firstD == '' || firstAnswer == '' || firstExplain == '') {
            alert('请完善问题1信息');
            return;
        }

        if (secondQuestion == '' || secondA == '' || secondB == '' ||
            secondC == '' || secondD == '' || secondAnswer == '' || secondExplain == '') {
            alert('请完善问题2信息' + firstExplain);
            return;
        }

        if (thirdQuestion == '' || thirdA == '' || thirdB == '' ||
            thirdC == '' || thirdD == '' || thirdAnswer == '' || thirdExplain == '') {
            alert('请完善问题3信息');
            return;
        }

        if (fourthQuestion == '' || fourthA == '' || fourthB == '' ||
            fourthC == '' || fourthD == '' || fourthAnswer == '' || fourthExplain == '') {
            alert('请完善问题4信息');
            return;
        }

        if (fifthQuestion == '' || fifthA == '' || fifthB == '' ||
            fifthC == '' || fifthD == '' || fifthAnswer == '' || fifthExplain == '') {
            alert('请完善问题5信息');
            return;
        }

        var params = {
            type : type,
            level : level,
            period : period,
            title : title,
            content : content,
            firstQuestion : firstQuestion,
            firstA : firstA,
            firstB : firstB,
            firstC : firstC,
            firstD : firstD,
            firstAnswer : firstAnswer,
            firstExplain : firstExplain,
            secondQuestion : secondQuestion,
            secondA : secondA,
            secondB : secondB,
            secondC : secondC,
            secondD : secondD,
            secondAnswer : secondAnswer,
            secondExplain : secondExplain,
            thirdQuestion : thirdQuestion,
            thirdA : thirdA,
            thirdB : thirdB,
            thirdC : thirdC,
            thirdD : thirdD,
            thirdAnswer : thirdAnswer,
            thirdExplain : thirdExplain,
            fourthQuestion : fourthQuestion,
            fourthA : fourthA,
            fourthB : fourthB,
            fourthC : fourthC,
            fourthD : fourthD,
            fourthAnswer : fourthAnswer,
            fourthExplain : fourthExplain,
            fifthQuestion : fifthQuestion,
            fifthA : fifthA,
            fifthB : fifthB,
            fifthC : fifthC,
            fifthD : fifthD,
            fifthAnswer : fifthAnswer,
            fifthExplain : fifthExplain
        };
        $.ajax({
            type: "POST",
            url: "/article/add",
            data: params,
            dataType: "json",
            traditional: true
        }).done(function (result) {
            if(result.code == 0){
                $("#addArticleInfoModal").modal("hide");
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

    // 修改
    $("#saveArticleInfo").click(function(){
        var id = $('#id').val();
        var title = $('#titleUpdate').val();
        var content = $('#contentUpdate').val();
        var firstQuestion = $('#firstQuestionUpdate').val();
        var firstA = $('#firstAUpdate').val();
        var firstB = $('#firstBUpdate').val();
        var firstC = $('#firstCUpdate').val();
        var firstD = $('#firstDUpdate').val();
        var firstAnswer = $('#firstAnswerUpdate option:selected').val();
        var firstExplain = $('#firstExplainUpdate').val();
        var secondQuestion = $('#secondQuestionUpdate').val();
        var secondA = $('#secondAUpdate').val();
        var secondB = $('#secondBUpdate').val();
        var secondC = $('#secondCUpdate').val();
        var secondD = $('#secondDUpdate').val();
        var secondAnswer = $('#secondAnswerUpdate option:selected').val();
        var secondExplain = $('#secondExplainUpdate').val();
        var thirdQuestion = $('#thirdQuestionUpdate').val();
        var thirdA = $('#thirdAUpdate').val();
        var thirdB = $('#thirdBUpdate').val();
        var thirdC = $('#thirdCUpdate').val();
        var thirdD = $('#thirdDUpdate').val();
        var thirdAnswer = $('#thirdAnswerUpdate option:selected').val();
        var thirdExplain = $('#thirdExplainUpdate').val();
        var fourthQuestion = $('#fourthQuestionUpdate').val();
        var fourthA = $('#fourthAUpdate').val();
        var fourthB = $('#fourthBUpdate').val();
        var fourthC = $('#fourthCUpdate').val();
        var fourthD = $('#fourthDUpdate').val();
        var fourthAnswer = $('#fourthAnswerUpdate option:selected').val();
        var fourthExplain = $('#fourthExplainUpdate').val();
        var fifthQuestion = $('#fifthQuestionUpdate').val();
        var fifthA = $('#fifthAUpdate').val();
        var fifthB = $('#fifthBUpdate').val();
        var fifthC = $('#fifthCUpdate').val();
        var fifthD = $('#fifthDUpdate').val();
        var fifthAnswer = $('#fifthAnswerUpdate option:selected').val();
        var fifthExplain = $('#fifthExplainUpdate').val();

        if (title == '') {
            alert('标题不能为空');
            return;
        }
        if (content == '') {
            alert('内容不能为空');
            return;
        }
        if (firstQuestion == '' || firstA == '' || firstB == '' ||
            firstC == '' || firstD == '' || firstAnswer == '' || firstExplain == '') {
            alert('请完善问题1信息');
            return;
        }

        if (secondQuestion == '' || secondA == '' || secondB == '' ||
            secondC == '' || secondD == '' || secondAnswer == '' || secondExplain == '') {
            alert('请完善问题2信息');
            return;
        }

        if (thirdQuestion == '' || thirdA == '' || thirdB == '' ||
            thirdC == '' || thirdD == '' || thirdAnswer == '' || thirdExplain == '') {
            alert('请完善问题3信息');
            return;
        }

        if (fourthQuestion == '' || fourthA == '' || fourthB == '' ||
            fourthC == '' || fourthD == '' || fourthAnswer == '' || fourthExplain == '') {
            alert('请完善问题4信息');
            return;
        }

        if (fifthQuestion == '' || fifthA == '' || fifthB == '' ||
            fifthC == '' || fifthD == '' || fifthAnswer == '' || fifthExplain == '') {
            alert('请完善问题5信息');
            return;
        }

        var params = {
            id : id,
            title : title,
            content : content,
            firstQuestion : firstQuestion,
            firstA : firstA,

            firstB : firstB,
            firstC : firstC,
            firstD : firstD,
            firstAnswer : firstAnswer,
            firstExplain : firstExplain,
            secondQuestion : secondQuestion,
            secondA : secondA,
            secondB : secondB,
            secondC : secondC,
            secondD : secondD,
            secondAnswer : secondAnswer,
            secondExplain : secondExplain,
            thirdQuestion : thirdQuestion,
            thirdA : thirdA,
            thirdB : thirdB,
            thirdC : thirdC,
            thirdD : thirdD,
            thirdAnswer : thirdAnswer,
            thirdExplain : thirdExplain,
            fourthQuestion : fourthQuestion,
            fourthA : fourthA,
            fourthB : fourthB,
            fourthC : fourthC,
            fourthD : fourthD,
            fourthAnswer : fourthAnswer,
            fourthExplain : fourthExplain,
            fifthQuestion : fifthQuestion,
            fifthA : fifthA,
            fifthB : fifthB,
            fifthC : fifthC,
            fifthD : fifthD,
            fifthAnswer : fifthAnswer,
            fifthExplain : fifthExplain,
        };
        $.ajax({
            type: "POST",
            url: "/article/update",
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