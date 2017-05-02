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
        $('#firstAExplainAdd').val('');
        $('#firstBAdd').val('');
        $('#firstBExplainAdd').val('');
        $('#firstCAdd').val('');
        $('#firstCExplainAdd').val('');
        $('#firstDAdd').val('');
        $('#firstDExplainAdd').val('');
        $('#firstAnswerAdd').val('a');
        $('#secondQuestionAdd').val('');
        $('#secondAAdd').val('');
        $('#secondAExplainAdd').val('');
        $('#secondBAdd').val('');
        $('#secondBExplainAdd').val('');
        $('#secondCAdd').val('');
        $('#secondCExplainAdd').val('');
        $('#secondDAdd').val('');
        $('#secondDExplainAdd').val('');
        $('#secondAnswerAdd').val('a');
        $('#thirdQuestionAdd').val('');
        $('#thirdAAdd').val('');
        $('#thirdAExplainAdd').val('');
        $('#thirdBAdd').val('');
        $('#thirdBExplainAdd').val('');
        $('#thirdCAdd').val('');
        $('#thirdCExplainAdd').val('');
        $('#thirdDAdd').val('');
        $('#thirdDExplainAdd').val('');
        $('#thirdAnswerAdd').val('a');
        $('#fourthQuestionAdd').val('');
        $('#fourthAAdd').val('');
        $('#fourthAExplainAdd').val('');
        $('#fourthBAdd').val('');
        $('#fourthBExplainAdd').val('');
        $('#fourthCAdd').val('');
        $('#fourthCExplainAdd').val('');
        $('#fourthDAdd').val('');
        $('#fourthDExplainAdd').val('');
        $('#fourthAnswerAdd').val('a');
        $('#fifthQuestionAdd').val('');
        $('#fifthAAdd').val('');
        $('#fifthAExplainAdd').val('');
        $('#fifthBAdd').val('');
        $('#fifthBExplainAdd').val('');
        $('#fifthCAdd').val('');
        $('#fifthCExplainAdd').val('');
        $('#fifthDAdd').val('');
        $('#fifthDExplainAdd').val('');
        $('#fifthAnswerAdd').val('a');
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
        var firstAExplain = $('#firstAExplainAdd').val();
        var firstB = $('#firstBAdd').val();
        var firstBExplain = $('#firstBExplainAdd').val();
        var firstC = $('#firstCAdd').val();
        var firstCExplain = $('#firstCExplainAdd').val();
        var firstD = $('#firstDAdd').val();
        var firstDExplain = $('#firstDExplainAdd').val();
        var firstAnswer = $('#firstAnswerAdd option:selected').val();
        var secondQuestion = $('#secondQuestionAdd').val();
        var secondA = $('#secondAAdd').val();
        var secondAExplain = $('#secondAExplainAdd').val();
        var secondB = $('#secondBAdd').val();
        var secondBExplain = $('#secondBExplainAdd').val();
        var secondC = $('#secondCAdd').val();
        var secondCExplain = $('#secondCExplainAdd').val();
        var secondD = $('#secondDAdd').val();
        var secondDExplain = $('#secondDExplainAdd').val();
        var secondAnswer = $('#secondAnswerAdd option:selected').val();
        var thirdQuestion = $('#thirdQuestionAdd').val();
        var thirdA = $('#thirdAAdd').val();
        var thirdAExplain = $('#thirdAExplainAdd').val();
        var thirdB = $('#thirdBAdd').val();
        var thirdBExplain = $('#thirdBExplainAdd').val();
        var thirdC = $('#thirdCAdd').val();
        var thirdCExplain = $('#thirdCExplainAdd').val();
        var thirdD = $('#thirdDAdd').val();
        var thirdDExplain = $('#thirdDExplainAdd').val();
        var thirdAnswer = $('#thirdAnswerAdd option:selected').val();
        var fourthQuestion = $('#fourthQuestionAdd').val();
        var fourthA = $('#fourthAAdd').val();
        var fourthAExplain = $('#fourthAExplainAdd').val();
        var fourthB = $('#fourthBAdd').val();
        var fourthBExplain = $('#fourthBExplainAdd').val();
        var fourthC = $('#fourthCAdd').val();
        var fourthCExplain = $('#fourthCExplainAdd').val();
        var fourthD = $('#fourthDAdd').val();
        var fourthDExplain = $('#fourthDExplainAdd').val();
        var fourthAnswer = $('#fourthAnswerAdd option:selected').val();
        var fifthQuestion = $('#fifthQuestionAdd').val();
        var fifthA = $('#fifthAAdd').val();
        var fifthAExplain = $('#fifthAExplainAdd').val();
        var fifthB = $('#fifthBAdd').val();
        var fifthBExplain = $('#fifthBExplainAdd').val();
        var fifthC = $('#fifthCAdd').val();
        var fifthCExplain = $('#fifthCExplainAdd').val();
        var fifthD = $('#fifthDAdd').val();
        var fifthDExplain = $('#fifthDExplainAdd').val();
        var fifthAnswer = $('#fifthAnswerAdd option:selected').val();

        if (title == '') {
            alert('标题不能为空');
            return;
        }
        if (content == '') {
            alert('内容不能为空');
            return;
        }
        if (firstQuestion == '' || firstA == '' || firstAExplain == '' || firstB == '' || firstBExplain == '' ||
            firstC == '' || firstCExplain == '' || firstD == '' || firstDExplain == '' || firstAnswer == '') {
            alert('请完善问题1信息');
            return;
        }

        if (secondQuestion == '' || secondA == '' || secondAExplain == '' || secondB == '' || secondBExplain == '' ||
            secondC == '' || secondCExplain == '' || secondD == '' || secondDExplain == '' || secondAnswer == '') {
            alert('请完善问题2信息');
            return;
        }

        if (thirdQuestion == '' || thirdA == '' || thirdAExplain == '' || thirdB == '' || thirdBExplain == '' ||
            thirdC == '' || thirdCExplain == '' || thirdD == '' || thirdDExplain == '' || thirdAnswer == '') {
            alert('请完善问题3信息');
            return;
        }

        if (fourthQuestion == '' || fourthA == '' || fourthAExplain == '' || fourthB == '' || fourthBExplain == '' ||
            fourthC == '' || fourthCExplain == '' || fourthD == '' || fourthDExplain == '' || fourthAnswer == '') {
            alert('请完善问题4信息');
            return;
        }

        if (fifthQuestion == '' || fifthA == '' || fifthAExplain == '' || fifthB == '' || fifthBExplain == '' ||
            fifthC == '' || fifthCExplain == '' || fifthD == '' || fifthDExplain == '' || fifthAnswer == '') {
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
            firstAExplain : firstAExplain,
            firstB : firstB,
            firstBExplain : firstBExplain,
            firstC : firstC,
            firstCExplain : firstCExplain,
            firstD : firstD,
            firstDExplain : firstDExplain,
            firstAnswer : firstAnswer,
            secondQuestion : secondQuestion,
            secondA : secondA,
            secondAExplain : secondAExplain,
            secondB : secondB,
            secondBExplain : secondBExplain,
            secondC : secondC,
            secondCExplain : secondCExplain,
            secondD : secondD,
            secondDExplain : secondDExplain,
            secondAnswer : secondAnswer,
            thirdQuestion : thirdQuestion,
            thirdA : thirdA,
            thirdAExplain : thirdAExplain,
            thirdB : thirdB,
            thirdBExplain : thirdBExplain,
            thirdC : thirdC,
            thirdCExplain : thirdCExplain,
            thirdD : thirdD,
            thirdDExplain : thirdDExplain,
            thirdAnswer : thirdAnswer,
            fourthQuestion : fourthQuestion,
            fourthA : fourthA,
            fourthAExplain : fourthAExplain,
            fourthB : fourthB,
            fourthBExplain : fourthBExplain,
            fourthC : fourthC,
            fourthCExplain : fourthCExplain,
            fourthD : fourthD,
            fourthDExplain : fourthDExplain,
            fourthAnswer : fourthAnswer,
            fifthQuestion : fifthQuestion,
            fifthA : fifthA,
            fifthAExplain : fifthAExplain,
            fifthB : fifthB,
            fifthBExplain : fifthBExplain,
            fifthC : fifthC,
            fifthCExplain : fifthCExplain,
            fifthD : fifthD,
            fifthDExplain : fifthDExplain,
            fifthAnswer : fifthAnswer
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
        var firstAExplain = $('#firstAExplainUpdate').val();
        var firstB = $('#firstBUpdate').val();
        var firstBExplain = $('#firstBExplainUpdate').val();
        var firstC = $('#firstCUpdate').val();
        var firstCExplain = $('#firstCExplainUpdate').val();
        var firstD = $('#firstDUpdate').val();
        var firstDExplain = $('#firstDExplainUpdate').val();
        var firstAnswer = $('#firstAnswerUpdate option:selected').val();
        var secondQuestion = $('#secondQuestionUpdate').val();
        var secondA = $('#secondAUpdate').val();
        var secondAExplain = $('#secondAExplainUpdate').val();
        var secondB = $('#secondBUpdate').val();
        var secondBExplain = $('#secondBExplainUpdate').val();
        var secondC = $('#secondCUpdate').val();
        var secondCExplain = $('#secondCExplainUpdate').val();
        var secondD = $('#secondDUpdate').val();
        var secondDExplain = $('#secondDExplainUpdate').val();
        var secondAnswer = $('#secondAnswerUpdate option:selected').val();
        var thirdQuestion = $('#thirdQuestionUpdate').val();
        var thirdA = $('#thirdAUpdate').val();
        var thirdAExplain = $('#thirdAExplainUpdate').val();
        var thirdB = $('#thirdBUpdate').val();
        var thirdBExplain = $('#thirdBExplainUpdate').val();
        var thirdC = $('#thirdCUpdate').val();
        var thirdCExplain = $('#thirdCExplainUpdate').val();
        var thirdD = $('#thirdDUpdate').val();
        var thirdDExplain = $('#thirdDExplainUpdate').val();
        var thirdAnswer = $('#thirdAnswerUpdate option:selected').val();
        var fourthQuestion = $('#fourthQuestionUpdate').val();
        var fourthA = $('#fourthAUpdate').val();
        var fourthAExplain = $('#fourthAExplainUpdate').val();
        var fourthB = $('#fourthBUpdate').val();
        var fourthBExplain = $('#fourthBExplainUpdate').val();
        var fourthC = $('#fourthCUpdate').val();
        var fourthCExplain = $('#fourthCExplainUpdate').val();
        var fourthD = $('#fourthDUpdate').val();
        var fourthDExplain = $('#fourthDExplainUpdate').val();
        var fourthAnswer = $('#fourthAnswerUpdate option:selected').val();
        var fifthQuestion = $('#fifthQuestionUpdate').val();
        var fifthA = $('#fifthAUpdate').val();
        var fifthAExplain = $('#fifthAExplainUpdate').val();
        var fifthB = $('#fifthBUpdate').val();
        var fifthBExplain = $('#fifthBExplainUpdate').val();
        var fifthC = $('#fifthCUpdate').val();
        var fifthCExplain = $('#fifthCExplainUpdate').val();
        var fifthD = $('#fifthDUpdate').val();
        var fifthDExplain = $('#fifthDExplainUpdate').val();
        var fifthAnswer = $('#fifthAnswerUpdate option:selected').val();

        if (title == '') {
            alert('标题不能为空');
            return;
        }
        if (content == '') {
            alert('内容不能为空');
            return;
        }
        if (firstQuestion == '' || firstA == '' || firstAExplain == '' || firstB == '' || firstBExplain == '' ||
            firstC == '' || firstCExplain == '' || firstD == '' || firstDExplain == '' || firstAnswer == '') {
            alert('请完善问题1信息');
            return;
        }

        if (secondQuestion == '' || secondA == '' || secondAExplain == '' || secondB == '' || secondBExplain == '' ||
            secondC == '' || secondCExplain == '' || secondD == '' || secondDExplain == '' || secondAnswer == '') {
            alert('请完善问题2信息');
            return;
        }

        if (thirdQuestion == '' || thirdA == '' || thirdAExplain == '' || thirdB == '' || thirdBExplain == '' ||
            thirdC == '' || thirdCExplain == '' || thirdD == '' || thirdDExplain == '' || thirdAnswer == '') {
            alert('请完善问题3信息');
            return;
        }

        if (fourthQuestion == '' || fourthA == '' || fourthAExplain == '' || fourthB == '' || fourthBExplain == '' ||
            fourthC == '' || fourthCExplain == '' || fourthD == '' || fourthDExplain == '' || fourthAnswer == '') {
            alert('请完善问题4信息');
            return;
        }

        if (fifthQuestion == '' || fifthA == '' || fifthAExplain == '' || fifthB == '' || fifthBExplain == '' ||
            fifthC == '' || fifthCExplain == '' || fifthD == '' || fifthDExplain == '' || fifthAnswer == '') {
            alert('请完善问题5信息');
            return;
        }

        var params = {
            id : id,
            title : title,
            content : content,
            firstQuestion : firstQuestion,
            firstA : firstA,
            firstAExplain : firstAExplain,
            firstB : firstB,
            firstBExplain : firstBExplain,
            firstC : firstC,
            firstCExplain : firstCExplain,
            firstD : firstD,
            firstDExplain : firstDExplain,
            firstAnswer : firstAnswer,
            secondQuestion : secondQuestion,
            secondA : secondA,
            secondAExplain : secondAExplain,
            secondB : secondB,
            secondBExplain : secondBExplain,
            secondC : secondC,
            secondCExplain : secondCExplain,
            secondD : secondD,
            secondDExplain : secondDExplain,
            secondAnswer : secondAnswer,
            thirdQuestion : thirdQuestion,
            thirdA : thirdA,
            thirdAExplain : thirdAExplain,
            thirdB : thirdB,
            thirdBExplain : thirdBExplain,
            thirdC : thirdC,
            thirdCExplain : thirdCExplain,
            thirdD : thirdD,
            thirdDExplain : thirdDExplain,
            thirdAnswer : thirdAnswer,
            fourthQuestion : fourthQuestion,
            fourthA : fourthA,
            fourthAExplain : fourthAExplain,
            fourthB : fourthB,
            fourthBExplain : fourthBExplain,
            fourthC : fourthC,
            fourthCExplain : fourthCExplain,
            fourthD : fourthD,
            fourthDExplain : fourthDExplain,
            fourthAnswer : fourthAnswer,
            fifthQuestion : fifthQuestion,
            fifthA : fifthA,
            fifthAExplain : fifthAExplain,
            fifthB : fifthB,
            fifthBExplain : fifthBExplain,
            fifthC : fifthC,
            fifthCExplain : fifthCExplain,
            fifthD : fifthD,
            fifthDExplain : fifthDExplain,
            fifthAnswer : fifthAnswer
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