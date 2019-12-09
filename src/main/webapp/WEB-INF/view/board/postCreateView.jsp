<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>사이트 : 게시판</title>

    <script type="text/javascript" src="/js/board/postCreate.js" ></script>
</head>
<body>
<div id="content-wrapper">

    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/board/${boardNo}"><c:out value="${boardVO.boardName}" /></a>
            </li>
            <li class="breadcrumb-item active">글쓰기</li>
        </ol>
        <div class="card mb-3">
            <div class="card-body">
                <form name="createPostForm" id="createPostForm" >
                    <table class="table">
                        <tr>
                            <th>제목</th>
                            <td><input type="text" class="form-control" name="title" placeholder="제목을 입력하세요." /></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><textarea name="content" class="form-control" placeholder="내용을 입력하세요."></textarea></td>
                        </tr>
                    </table>

                    <input type="button" class="btn btn-primary" id="rgstBtn" value="등록" />
                    <input type="reset" class="btn btn-secondary" value="초기화" />
                    <input type="button" class="btn btn-secondary" id="clsBtn" value="취소" />
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    $postCreate.ui.boardNo = "${boardNo}";
</script>
</body>
</html>