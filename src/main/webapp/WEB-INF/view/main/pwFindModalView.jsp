<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="pwForm" class="form-u" onsubmit="$login.ui.findPassword(this); return false;">
    <h2 class="form-u-heading">비밀번호 찾기</h2>
    <p>
        <label for="userId" class="sr-only">아이디</label>
        <input type="text" id="userId" name="userId" class="form-control" placeholder="아이디" required="" autofocus="" >
    </p>
    <p>
        <label for="userName" class="sr-only">이름</label>
        <input type="text" id="userName" name="userName" class="form-control" placeholder="이름" required="" >
    </p>

    <button class="btn btn-lg btn-primary btn-block" type="submit">비밀번호 찾기</button>
</form>