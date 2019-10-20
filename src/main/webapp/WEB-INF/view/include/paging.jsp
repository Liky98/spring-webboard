<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $('.totalCount').text('${paginate.totalCount }');

    function loadPageList(pageNo) {
        var url = '${paginate.url }';
        if (!url) {
            return false;
        }
        location.href = url + "?pageNo=" + pageNo;
    }
</script>

<c:if test="${paginate.totalCount ne 0}">
    <div class="paginate">
        <ul class="pagination" style="margin:0px">
            <c:if test="${paginate.firstPageNo ne paginate.pageNo }">
                <li class="page-item">
                    <a class="page-link" href="javascript:loadPageList(${paginate.firstPageNo})" >&laquo;</a>
                </li>
            </c:if>
            <c:if test="${paginate.firstPageNo eq paginate.pageNo }">
                <li class="page-item disabled">
                    <a class="page-link" href="javascript:void(0);" >&laquo;</a>
                </li>
            </c:if>
            <c:if test="${paginate.prevPageNo ne paginate.startPageNo }">
                <li class="page-item">
                    <a class="page-link" href="javascript:loadPageList(${paginate.prevPageNo})" >&lsaquo;</a>
                </li>
            </c:if>
            <c:forEach var="i" begin="${paginate.startPageNo }" end="${paginate.endPageNo }" step="1">
                <c:if test="${i eq paginate.pageNo }">
                    <li class="page-item active">
                        <a class="page-link" href="javascript:void(0);">${i }</a>
                    </li>
                </c:if>
                <c:if test="${i ne paginate.pageNo }">
                    <li class="page-item">
                        <a class="page-link" href="javascript:loadPageList(${i })">${i }</a>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${paginate.nextPageNo ne paginate.finalPageNo }">
                <li class="page-item">
                    <a class="page-link" href="javascript:loadPageList(${paginate.nextPageNo })" >&rsaquo;</a>
                </li>
            </c:if>
            <c:if test="${paginate.finalPageNo eq paginate.pageNo }">
                <li class="page-item disabled">
                    <a class="page-link" href="javascript:void(0);" >&raquo;</a>
                </li>
            </c:if>
            <c:if test="${paginate.finalPageNo ne paginate.pageNo }">
                <li class="page-item">
                    <a class="page-link" href="javascript:loadPageList(${paginate.finalPageNo })" >&raquo;</a>
                </li>
            </c:if>
        </ul>
    </div>
</c:if>
