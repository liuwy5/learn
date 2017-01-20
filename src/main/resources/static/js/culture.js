/**
 * Created by liuw on 17-1-17.
 */
$(document).ready(function () {
    //查看详情
    $("#editCulture").click(function(){
        if($("tbody", $table).find("tr").hasClass("active")){
            var tid = $(this).attr("data-id");
            $("#cusDetail").find("table tbody").html("");
            $("#pLicenseInfo").html("");
            personalCus.getCusDetail(tid);
        }else{
            $.alert('请选择一行您要查看的数据！');
        }
    });

    // // 查看详情
    // function cultureDetail(id) {
    //     console.log('id: ' + id);
    //     $('#editInfo').modal('show');
    //     $.ajax({
    //         url: "/culture/delete",
    //         type: 'post',
    //         data: id,
    //         success: function (data) {
    //             $('#title').val(data.title);
    //             $('#content').val(data.comment);
    //         }
    //     });
    // }
});