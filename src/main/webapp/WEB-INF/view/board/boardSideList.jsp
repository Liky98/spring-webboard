<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<ul class="sidebar navbar-nav">
<c:choose>
    <c:when test="${not empty list}">
        <c:forEach items="${list}" var="vo">
            <li class="nav-item <c:if test='${vo.boardNo eq boardNo}'>active</c:if>">
                <a class="nav-link" href="/board/${vo.boardNo}">
                    <i class="fas fa-fw fa-list"></i>
                    <span><c:out value="${vo.name}" /></span></a>
            </li>
        </c:forEach>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>
</ul>