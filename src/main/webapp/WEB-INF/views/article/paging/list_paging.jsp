<%--
  Created by IntelliJ IDEA.
  User: jiwoong
  Date: 2020-12-03
  Time: 오후 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <h3 class="box-title">게시글 목록</h3>
                </div>
                <div class="box-body">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <th style="width: 30px">#</th>
                            <th>제목</th>
                            <th style="width: 100px">작성자</th>
                            <th style="width: 150px">작성일</th>
                            <th style="width: 60px">조회</th>
                        </tr>
                        <c:forEach items="${articles}" var="article">
                            <tr>
                                <td>${article.articleNo}</td>
                                <%--<td><a href="${path}/article/read?articleNo=${article.articleNo}">${article.title}</a></td>--%>
                                <td>
                                    <a href="${path}/article/paging/read_paging${pageMaker.makeQuery(pageMaker.criteria.page)}&articleNo=${article.articleNo}">
                                        ${article.title}
                                    </a>
                                </td>
                                <%-- Paging Upgrade Process by using javascript --%>


                                <td>${article.writer}</td>
                                <td><fmt:formatDate value="${article.regDate}" pattern="yyyy-MM-dd a HH:mm"/></td>
                                <td><span class="badge bg-red">${article.viewCnt}</span> </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer">
                    <div class="text-center">
                        <ul class="pagination">
                            <c:if test="${pageMaker.prev}">
                                <%-- Paging Upgrade --%>
                                <%--<li><a href="${path}/article/listPaging?page=${pageMaker.startPage - 1}">이전</a></li>--%>
                                <%--<li><a href="${path}/article/listPaging${pageMaker.makeQuery(pageMaker.startPage - 1)}}">이전</a></li>--%>
                                <li><a href="${pageMaker.startPage -1}">이전</a></li>
                            </c:if>
                            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                                <li <c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}"/>>
                                        <%-- Paging Upgrade --%>
                                    <%--<a href="${path}/article/listPaging?page=${idx}">${idx}</a>--%>
                                    <%--<a href="${path}/article/listPaging${pageMaker.makeQuery(idx)}>${idx}"></a>--%>
                                    <a href="${idx}">${idx}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                                <%-- Paging Upgrade --%>
                                <%--<li><a href="${path}/article/listPaging?page=${pageMaker.endPage + 1}">다음</a></li>--%>
                                <%--<li><a href="${path}/article/listPaging?${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>--%>
                                <li><a href="${pageMaker.endPage + 1}">다음</a></li>
                            </c:if>
                        </ul>
                        <form id="listPageForm">
                            <input type="hidden" name="page" value="${pageMaker.criteria.page}">
                            <input type="hidden" name="perPageNum" value="${pageMaker.criteria.perPageNum}">

                        </form>
                    </div>
                </div>
                <div class="box-footer">
                    <div class="pull-right">
                        <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                            <i class="fa fa-pencil"></i> 글쓰기
                        </button>
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
<script>
    let result = "${msg}";
    if (result == "regSuccess") {
        alert("게시글 등록이 완료되었습니다. ");
    } else if (result == "modSuccess") {
        alert("게시글 수정이 완료되었습니다.");
    } else if (result == "delSuccess") {
        alert("게시글 삭제가 완료되었습니다. ");
    }

    $(".pagination li a").on("click", function (event) {
        event.preventDefault();
        let targetPage = $(this).attr("href");
        let listPageForm = $("#listPageForm");
        listPageForm.find("[name='page']").val(targetPage);
        listPageForm.attr("action", "/article/paging/list_paging").attr("method", "get");
        listPageForm.submit();
    });

    /*실험 */
    /*$(".move").on("click", function (e) {
        e.preventDefault();
        let listPageForm = $("#listPageForm");
        let target = $(this).attr()
        listPageForm.append("<input type='hidden' name='articleNo' value='" +$(this).attr("href")+"'>");
        listPageForm.attr("action","/article/listPaging").attr("method", "get");
        listPageForm.submit();

    })*/

</script>

