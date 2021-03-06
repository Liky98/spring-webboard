<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>사이트 : 게시판</title>

    <script type="text/javascript" src="/js/board/postUpdate.js" ></script>
</head>
<body>

<div id="content-wrapper">

    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/board/${boardNo}"><c:out value="${boardVO.boardName}" /></a>
            </li>
            <li class="breadcrumb-item">
                <a href="/board/${boardNo}/${postNo}">글 상세</a>
            </li>
            <li class="breadcrumb-item active">글 수정</li>
        </ol>
        <div class="card mb-3">
            <div class="card-body">
                <form name="updatePostForm" id="updatePostForm" >
                    <table class="table">
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="title" class="form-control" value="<c:out value="${postVO.title}" />" /></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><textarea name="content" class="form-control" ><c:out value="${postVO.content}" /></textarea></td>
                        </tr>
                    </table>
                    <c:if test="${sessionScope.userId eq postVO.userId}" >
                        <input type="button" class="btn btn-primary" id="uptBtn" value="수정" />
                        <input type="reset" class="btn btn-secondary" value="초기화" />
                        <input type="button" class="btn btn-secondary" id="clsBtn" value="취소" />
                    </c:if>
                </form>
            </div>
        </div>
    </div>

</div>


<script>
    $postUpdate.ui.boardNo = "${boardNo}";
    $postUpdate.ui.postNo = "${postNo}";
</script>
</body>
</html>