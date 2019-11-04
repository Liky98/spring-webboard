<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>사이트 : 관리자페이지</title>

    <script src="/js/common/include.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/admin/board.js" ></script>
</head>
<body>

<%@ include file="/WEB-INF/view/include/header.jsp" %>

<div id="wrapper">
    <ul class="sidebar navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="/admin/board">
                <i class="fas fa-fw fa-edit"></i>
                <span>게시판관리</span></a>
        </li>
    </ul>

    <div id="content-wrapper">
        <div class="container-fluid">
            <ol class="breadcrumb">
                <li class="breadcrumb-item active">게시판관리</li>
            </ol>

            <div class="card mb-3">
                <div class="card-body">
                    <sec:authorize access="hasRole('ADMIN')">
                        <article id="list-article">
                            <table class="list-board table">
                                <tr>
                                    <th></th>
                                    <th>게시판</th>
                                    <th width="125px"></th>
                                </tr>
                                <c:choose>
                                    <c:when test="${not empty list}">
                                        <c:forEach items="${list}" var="vo">
                                            <tr class="tr">
                                                <td>${vo.boardNo}</td>
                                                <td><c:out value="${vo.boardName}" /></td>
                                                <td>
                                                    <button class="btn btn-danger" onclick="$board.ui.delBtn(this, ${vo.boardNo});">삭제</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="tr-non"><td colspan="3">생성된 게시판이 없습니다.</td></tr>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                            <table class="table">
                                <tr>
                                    <td><input type="text" class="form-control" id="createName" placeholder="게시판명을 입력해주세요."/></td>
                                    <td><button class="btn btn-primary" onclick="$board.ui.rgstBtn();">생성</button></td>
                                </tr>
                            </table>
                        </article>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>