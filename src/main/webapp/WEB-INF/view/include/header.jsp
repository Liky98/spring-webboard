<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="/">MAIN</a>

    <div class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0 navbar-text">
        <sec:authorize access="isAuthenticated()">
            ${sessionScope.nickname}(${sessionScope.userId})님
        </sec:authorize>
    </div>

    <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="javascript:void(0);" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-user-circle fa-fw"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <sec:authorize access="hasRole('ADMIN')">
                    <a class="dropdown-item" href="/admin">관리자페이지</a>
                </sec:authorize>
                <sec:authorize access="hasRole('USER')">
                    <a class="dropdown-item" href="/user">사용자페이지</a>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <a class="dropdown-item" href="/login" >로그인</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/logout" >로그아웃</a>
                </sec:authorize>
            </div>
        </li>
    </ul>
</header>