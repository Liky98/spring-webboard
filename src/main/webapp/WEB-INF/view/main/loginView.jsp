<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>사이트 : 로그인</title>

    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <script type="text/javascript" src="/js/common/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="/js/common/jquery-ui.min.js" ></script>
    <script type="text/javascript" src="/js/common/plugins.js" ></script>
    <script type="text/javascript" src="/js/main/login.js" ></script>
</head>
<body>

<div class="container-u">
    <form id="loginForm" class="form-u" method="post" action="/login">
        <h2 class="form-u-heading">로그인</h2>
        <p>
            <label for="username" class="sr-only">아이디</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="아이디" required="" autofocus="" onclick="$('#failMessage').text('')" >
        </p>
        <p>
            <label for="password" class="sr-only">비밀번호</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="비밀번호" required="">
        </p>

        <c:if test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message }">
            <p>
                <label id="failMessage" style="color:red;">
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }
                    <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" />
                </label>
            </p>
        </c:if>

        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>

        <br/>
        <div class="form-join">
            <a href="javascript:void(0);" id="findPassword">비밀번호 찾기</a>
<%--            &nbsp;&nbsp;&nbsp;--%>
<%--            <a href="/join">회원가입</a>--%>
        </div>
    </form>
</div>

<div id="pwModal" class="modal">
    <%@ include file="/WEB-INF/view/include/modal.jsp" %>
</div>

<div id="pwResultModal" class="modal">
    <%@ include file="/WEB-INF/view/include/modal.jsp" %>
</div>

</body>
</html>