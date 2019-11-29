<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>사이트 : 게시판</title>

    <script src="/js/common/include.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/board/board.js" ></script>
</head>
<body>

<%@ include file="/WEB-INF/view/include/header.jsp" %>

<div id="wrapper">
<%@ include file="/WEB-INF/view/include/nav.jsp" %>

<div id="content-wrapper">

    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active"><c:out value="${boardVO.boardName}" /></li>
        </ol>
        <div class="card mb-3">
            <div class="card-body">
                <div class="float-left">
                    <label>총 <span class="totalCount">0</span>건의 게시물</label>
                </div>
                <sec:authorize access="hasRole('USER')">
                    <div class="float-right">
                        <label>
                            <input type="button" class="btn btn-primary" id="rgstBtn" value="글쓰기" />
                        </label>
                    </div>
                </sec:authorize>
                <article id="list-article"></article>
            </div>
        </div>
    </div>
</div>


</div>


<script>
    $board.ui.boardNo = "${boardNo}";
    $board.ui.pageNo = "${pageNo}";
</script>
</body>
</html>