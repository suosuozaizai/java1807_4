<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

    <title>我的商城 | 后台</title>
    <jsp:include page="../includes/header.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                <li class="active">用户管理</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">

            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>
                            <c:if test="${baseResult!=null}">

                                <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                                        &times;
                                    </button>
                                        ${baseResult.message}
                                </div>

                            </c:if>


                            <div class="row" style="padding-left: 15px;padding-top: 5px">
                                <a type="button" href="/user/form" class="btn btn-sm btn-default"><i
                                        class="fa fa-plus"></i> 新建</a>&nbsp;&nbsp;
                                <a type="button" onclick="App.deleteMuti('/user/deleteMuti')" class="btn btn-sm btn-default"><i
                                        class="fa fa-trash"></i> 删除</a>&nbsp;&nbsp;
                                <a type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;
                                <a type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导出</a>

                            </div>

                        </div>



                            <div class="box-body">
                                <div class="col-sm-4 form-group">
                                    <label for="username" class="col-sm-3 control-label">姓名</label>
                                    <div class="col-sm-8">
                                        <input id="username" placeholder="姓名"/>
                                    </div>

                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="phone" class="col-sm-3 control-label">电话</label>
                                    <div class="col-sm-8">
                                        <input id="phone" placeholder="电话"/>
                                    </div>
                                </div>
                                <div class="col-sm-4 form-group">
                                    <label for="email" class="col-sm-3 control-label">邮箱</label>
                                    <div class="col-sm-8">
                                        <input id="email" placeholder="邮箱"/>
                                    </div>
                                </div>
                                <div class="box-tools">
                                    <button type="button" onclick="mysearch()" class="btn  btn-primary btn-sm">搜索</button>
                                </div>
                            </div>




                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th>
                                        <input type="checkbox" class="minimal checkbox-master"/>
                                    </th>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>邮箱</th>
                                    <th>手机号码</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%-- <c:forEach items="${users1}" var="user">
                                     <tr>
                                         <td>
                                             <input id="${user.id}" type="checkbox" class="minimal" />
                                         </td>
                                         <td>${user.id}</td>
                                         <td>${user.username}</td>
                                         <td>${user.email}</td>
                                         <td>${user.phone}</td>
                                         <td><fmt:formatDate value="${user.updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                         <td>
                                             <a type="button" class="btn btn-sm btn-default"><i class="fa fa-search"></i>
                                                 查看</a>
                                             <a type="button" class="btn btn-sm btn-primary"><i class="fa fa-edit"></i>
                                                 编辑</a>
                                             <a type="button" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i>
                                                 删除</a>
                                         </td>

                                     </tr>

                                 </c:forEach>--%>
                                </tbody>

                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>


        </section>


    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>
<!-- end div class="wrapper"-->
<!-- modals -->
<div class="modal fade" id="modal-default">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">温馨提示</h4>
            </div>
            <div class="modal-body">
                <p></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" onclick="App.btnClick()" class="btn btn-primary">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


</body>
<jsp:include page="../includes/footer.jsp"/>

<script>

    var url = "/user/page";

    var columns =  [
        {
            "data": function (row, type, val, meta) {
                return '<input id=\"' + row.id + '\" type=\"checkbox\" class=\"minimal\" />'
            }
        },
        {"data": "id"},
        {"data": "username"},
        {"data": "email"},
        {"data": "phone"},
        {"data": "created"},
        {
            "data": function (row, type, val, meta) {


                return '<button type="button" onclick="App.showDetail(\'/user/detail?id='+row.id+'\')" class="btn btn-xs btn-default"><i class="fa fa-search"></i>查看</button>'+
                    '<a type="button" href="/user/form?id='+row.id+'" class="btn btn-xs btn-primary"><i class="fa fa-edit"></i>编辑</a>'+
                    '<a type="button" onclick="App.deletesingle('+row.id+')" class="btn btn-xs btn-danger"><i class="fa fa-trash"></i>删除</a>';
            }
        }
    ];

   var dt =  App.datatable(url,columns);


    function mysearch(){
        var username = $("#username").val();
        var email = $("#email").val();
        var phone = $("#phone").val();
        var param = {
            "username":username,
            "email":email,
            "phone":phone
        };
        App.search(dt,param);
    }






</script>
</html>
