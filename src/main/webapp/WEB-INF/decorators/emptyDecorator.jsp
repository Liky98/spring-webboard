<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <script src="/js/common/include.js" charset="utf-8"></script>
    <sitemesh:write property="head" />
</head>
<body>

<sitemesh:decorate decorator="/WEB-INF/view/include/header.jsp" />

<sitemesh:write property="body" />

</body>
</html>