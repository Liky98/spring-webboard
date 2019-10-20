<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>사이트 : 관리자페이지</title>

    <script src="/js/common/include.js" charset="utf-8"></script>
</head>
<body>

<%@ include file="/WEB-INF/view/include/header.jsp" %>

<div id="wrapper">
    <ul class="sidebar navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="/admin/board">
                <i class="fas fa-fw fa-edit"></i>
                <span>게시판관리</span></a>
        </li>
    </ul>

    <div id="content-wrapper">
        <div class="container-fluid">
            <ol class="breadcrumb">
                <li class="breadcrumb-item active">ADMIN PAGE</li>
            </ol>
        </div>
    </div>

</div>


</body>
</html>