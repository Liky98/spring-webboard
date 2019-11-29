<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>사이트 : 게시판</title>

    <script src="/js/common/include.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/board/postRead.js" ></script>
</head>
<body>

<%@ include file="/WEB-INF/view/include/header.jsp" %>

<div id="wrapper">
    <%@ include file="/WEB-INF/view/include/nav.jsp" %>

    <div id="content-wrapper">

        <div class="container-fluid">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="/board/${boardNo}"><c:out value="${boardVO.boardName}" /></a>
                </li>
                <li class="breadcrumb-item active">글 상세</li>
            </ol>
            <div class="card mb-3">
                <div class="card-body">
                    <table class="table">
                        <tr>
                            <th>작성자</th>
                            <td><c:out value="${postVO.nickname}" />(<c:out value="${postVO.userId}" />)</td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><c:out value="${postVO.title}" /></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td class="pre"><c:out value="${postVO.content}" /></td>
                        </tr>
                        <tr>
                            <th>작성일</th>
                            <td>${postVO.createDt }</td>
                        </tr>
                        <tr>
                            <th>수정일</th>
                            <td>${postVO.updateDt }</td>
                        </tr>
                    </table>

                    <c:choose>
                        <c:when test="${sessionScope.userId eq postVO.userId}" >
                            <input type="button" class="btn btn-primary" id="uptBtn" value="수정" />
                            <input type="button" class="btn btn-danger" id="delBtn" value="삭제" />
                        </c:when>
                        <c:otherwise>
                            <sec:authorize access="hasRole('ADMIN')">
                                <input type="button" class="btn btn-danger" id="delBtn" value="삭제" />
                            </sec:authorize>
                        </c:otherwise>
                    </c:choose>
                    <input type="button" class="btn btn-secondary" id="clsBtn" value="목록" />
                </div>
            </div>
        </div>
    </div>


</div>


<script>
    $postRead.ui.boardNo = "${boardNo}";
    $postRead.ui.postNo = "${postNo}";
</script>
</body>
</html>