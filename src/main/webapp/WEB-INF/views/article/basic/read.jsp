<%--
  Created by IntelliJ IDEA.
  User: jiwoong
  Date: 2020-12-03
  Time: 오전 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function(){
        let formObj = $("form[role='form']");
        console.log(formObj);

        $(".modBtn").on("click", function(){
            formObj.attr("action", "/article/basic/modify");
            formObj.attr("method", "get");
            formObj.submit();
        });

        $(".delBtn").on("click", function(){
            formObj.attr("action", "/article/basic/remove");
            formObj.submit();
        });

        $(".listBtn").on("click", function(){
            self.location = "/article/basic/list"
        });
    })
</script>
<%@ include file="../../include/head.jsp"%>
<%@ include file="../../include/main_header.jsp"%>
<%@ include file="../../include/left_column.jsp"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Page Header
            <small>Optional description</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
            <li class="active">Here</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

        <div class="col-lg-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">글 제목 : ${article.title}</h3>
                </div>
                <div class="box-body" style="height: 700px">
                    ${article.content}
                </div>
                <div class="box-footer">
                    <div class="user-block">
                        <img class="img-circle img-bordered-sm" src="../../dist/img/user1-128x128.jpg" alt="user image">
                        <span class="username">
                            <a href="#">${article.writer}</a>
                        </span>
                        <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${article.regDate}"/></span>
                    </div>
                </div>
                <div class="box-footer">
                    <form role="form" method="post">
                        <input type="hidden" name="articleNo" value="${article.articleNo}">
                    </form>
                    <button type="submit" class="btn btn-primary listBtn"><i class="fa fa-list"></i>목록</button>
                    <div class="pull-right">
                        <button type="submit" class="btn btn-warning modBtn"><i class="fa fa-edit"></i>수정</button>
                        <button type="submit" class="btn btn-danger delBtn"><i class="fa fa-trash"></i>삭제</button>
                    </div>
                </div>
            </div>
        </div>

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="../../include/main_footer.jsp"%>
<%@ include file="../../include/plugin_js.jsp"%>
