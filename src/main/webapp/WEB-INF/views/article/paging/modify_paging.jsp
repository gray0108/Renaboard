<%--
  Created by IntelliJ IDEA.
  User: jiwoong
  Date: 2020-12-05
  Time: 오후 3:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function(){
        let formObj = $("form[role='form']");
        console.log(formObj);

        $(".modBtn").on("click", function(){
            formObj.submit();
        });

        $(".cancelBtn").on("click", function(){
            history.go(-1);
        });

        $(".listBtn").on("click", function(){
            /*self.location = "/article/list"*/
            self.location = "/article/listPaging?page=${criteria.page}&perPageNum=${criteria.perPageNum}";
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
            <form role="form" id="writeForm" method="post" action="${path}/article/paging/modify_paging"><%--여기도 수정--%>
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 수정</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <label for="title">제목</label>
                            <input class="form-control" id="title" name="title" placeholder="제목을 입력해 주세요" value="${article.title}">
                        </div>
                        <div class="form-group">
                            <label for="content">내용</label>
                            <textarea class="form-control" id="content" name="content" rows="30"
                                      placeholder="내용을 입력해주세요" style="resize: none;">${article.content}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="writer">작성자</label>
                            <input class="form-control" id="writer" name="writer" value="${article.writer}" readonly>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="button" class="btn btn-primary listBtn"><i class="fa fa-list"></i>목록</button>
                        <div class="pull-right">
                            <button type="button" class="btn btn-warning cancelBtn"><i class="fa fa-trash"></i>취소</button>
                            <button type="submit" class="btn btn-success modBtn"><i class="fa fa-save"></i>저장 </button>
                        </div>
                    </div>
                </div>
                <%-- hidden 생성 --%>
                <input type="hidden" name="articleNo" value="${article.articleNo}">
                <input type="hidden" name="page" value="${criteria.page}">
                <input type="hidden" name="perPageNum" value="${criteria.perPageNum}">
            </form>
        </div>

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@ include file="../../include/main_footer.jsp"%>
<%@ include file="../../include/plugin_js.jsp"%>