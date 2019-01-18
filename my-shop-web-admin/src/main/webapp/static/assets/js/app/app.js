var App = function(){
    var handlerTest = function(){
        alert("hello js!");

    };

    var handlercheckAllInit =  function(){
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });

        _checkboxMaster = $(".checkbox-master");
        _checkbox = $("tbody").find("[type='checkbox']").not("[disabled]");
        _checkboxMaster.on("ifClicked", function (e) {
            // 当前状态已选中，点击后应取消选择
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            // 当前状态未选中，点击后应全选
            else {
                _checkbox.iCheck("check");
            }
        });
    };


    var handlerdeleteMuti = function(url) {
        var _idArray = new Array();
        _checkbox.each(function () {

            // 判断是否选中
            var delFlag = $(this).is(":checked");
            if (delFlag) {
                _idArray.push($(this).attr("id"));
            }

        });
        if (_idArray.length === 0) {
            $(".modal-body").html("未选中任何要删除的项");
            $("#modal-default").modal("show");
        } else {
            $.ajax({
                // url: "/user/deleteMuti",
                "url":url,
                "type": "post",
                "data": {"ids": _idArray.toString()},
                "dataType": "json",
                "success": function (data) {
                    if (data.status == 200) {

                        window.location.reload();
                        alert(data.message);

                    } else {
                        alert(data.message);
                    }
                }
            });
        }

    };


    var handlerbtnClick =  function() {
        $("#modal-default").modal("hide");
    };

    var handlerdeletesingle = function(id){
        var _idArray = new Array();
        _idArray.push(id);
        $.ajax({
            url:"/user/deleteMuti",
            type:"post",
            data:{"ids":_idArray.toString()},
            dataType:"json",
            success:function(data){
                alert(data.message);
                window.location.reload();
            }

        });
    };

     var handlershowDetail =  function(url){
        $.ajax({
            //"url":"/user/detail?id="+id,
            "url":url,
            "type":"get",
            "dataType":"html",
            "success":function(data){
                $("#modal-detail").html(data);
                $("#modal-user").modal("show");
            }

        });
    };

    var handlersearch = function(dt,param){
        dt.settings()[0].ajax.data = param;
        dt.ajax.reload();
    };


    var handlerdatatable = function(url,columns){

        var _dataTable =  $('#dataTable').DataTable({
            // 是否开启本地分页
            "paging": true,
            // 是否开启本地排序
            "ordering": false,
            // 是否显示左下角信息
            "info": true,
            // 是否允许用户改变表格每页显示的记录数
            "lengthChange": false,
            // 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)
            "processing": true,
            // 是否允许 DataTables 开启本地搜索
            "searching": false,
            // 是否开启服务器模式
            "serverSide": true,
            // 控制 DataTables 的延迟渲染，可以提高初始化的速度
            "deferRender": true,
            "ajax": {
                // "url": "/user/page"
                "url":url
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function (settings) {
                App.checkAllInit();
            }
        });
        return _dataTable;
    };

    var handlerdeletecategory = function(id){
        $.ajax({
            "url":"/content/category/delete",
            "type":"POST",
            "data":{"id":id},
            "dataType":"JSON",
            "success":function(data){
                if(data.status==200){
                    alert(data.message);
                    window.location.reload();
                }else{
                    alert(data.message);
                }



            }
        });
    }



    return {
        test:function(){
            handlerTest();
        },
        checkAllInit:function(){
            handlercheckAllInit();
        },
        deleteMuti:function(url){
            handlerdeleteMuti(url);
        },
        btnClick:function(){
            handlerbtnClick();
        },
        deletesingle:function(id){
            handlerdeletesingle(id);
        },
        showDetail:function(url){
            handlershowDetail(url);
        },
        search:function(dt,param){
            handlersearch(dt,param);
        },
        datatable:function(url,columns){
            return handlerdatatable(url,columns);
        },
        deletecategory:function(id){
            handlerdeletecategory(id);
        }

    }
}();