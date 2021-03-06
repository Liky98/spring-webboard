<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>사이트 : 사용자페이지</title>

    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <script type="text/javascript" src="/js/main/user.js" ></script>
</head>
<body>

<section id="container" class="container-u">
    <form id="userForm" class="form-u" onsubmit="$user.ui.update(this);return false;">
        <h2 class="form-u-heading">개인정보</h2>
        <p>
            <label>이름</label>
            <input type="text" class="form-control" value="${userVO.userName}" readonly disabled>
        </p>
        <p>
            <label>아이디</label>
            <input type="text" class="form-control" value="${userVO.userId}" readonly disabled>
        </p>
        <p>
            <label>닉네임 <mark>*</mark></label>
            <input type="text" name="nickname" class="form-control" value="${userVO.nickname}" required="">
        </p>
        <p>
            <label>현재 비밀번호 <mark>*</mark></label>
            <input type="password" name="password" class="form-control" required="">
        </p>
        <p>
            <label>변경 비밀번호 <mark>*</mark></label>
            <input type="password" name="password2" class="form-control" required="">
        </p>
        <p>
            <label>변경 비밀번호 확인 <mark>*</mark></label>
            <input type="password" name="password_check" class="form-control" required="">
        </p>

        <button type="submit" class="btn btn-lg btn-primary btn-block" >개인정보수정</button>
    </form>
    </div>
</section>

</body>
</html>