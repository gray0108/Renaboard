<%--
  Created by IntelliJ IDEA.
  User: jiwoong
  Date: 2020-12-03
  Time: 오후 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/head.jsp"%>
<%@ include file="../include/main_header.jsp"%>
<%@ include file="../include/left_column.jsp"%>

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

       <h3><i class="fa fa-warning text-red"></i>${exception.getMessage()}</h3>
        <ul>
            <c:forEach items="${exception.getStackTrace()}" var="stack">
                <li>${stack.toString()}</li>
            </c:forEach>
        </ul>

    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@ include file="../include/main_footer.jsp"%>
<%@ include file="../include/plugin_js.jsp"%>

