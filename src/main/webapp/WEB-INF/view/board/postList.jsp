<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table">
    <tr>
        <th></th>
        <th>작성자</th>
        <th>제목</th>
        <th>작성일</th>
    </tr>
    <c:choose>
        <c:when test="${not empty list}">
            <c:forEach items="${list}" var="vo">
                <tr>
                    <td>${vo.postNo}</td>
                    <td><c:out value="${vo.nickname}" />(<c:out value="${vo.userId}" />)</td>
                    <td><a href="javascript:void(0);" onclick="$board.ui.readPostMap('${vo.boardNo}', '${vo.postNo}');"><c:out value="${vo.title}" /></a></td>
                    <td>${vo.createDt}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr><td colspan="4">작성된 글이 없습니다.</td></tr>
        </c:otherwise>
    </c:choose>
</table>
<%@ include file="/WEB-INF/view/include/paging.jsp" %>