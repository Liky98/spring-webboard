<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" >
    var url = "/board/boardSideList";
    var boardNo = '${boardNo}';
    if (boardNo) {
        url = url + "?boardNo="+boardNo;
    }

    $.ajax({
        url : url
        ,type : "GET"
        ,success : function(res) {
            $('#sidebar').html(res);
        }
        ,error : function(res, a) {
            alert("서버에서 처리중 에러가 발생하였습니다.");
        }
    });
</script>

<nav id="sidebar">
    <ul class="sidebar navbar-nav">
    </ul>
    게시판 목록이 없습니다.
</nav>