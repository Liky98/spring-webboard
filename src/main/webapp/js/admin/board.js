(function (W, D) {
    W.$board = W.$board || {};

    $board.ui = {
        rgstBtn: function() {
            var boardName = $('#createName').val();
            if (!boardName) {
                alert("게시판명을 입력하세요.");
                $('#createName').focus();
                return false;
            }
            if (getLength(boardName) > 255) {
                alert("게시판명의 허용된 글자수가 초과되었습니다.");
                $('#createName').focus();
                return false;
            }

            if (confirm("게시판을 생성하시겠습니까?")) {
                var url = "/admin/board/";

                $.ajax({
                    url: url
                    ,type: "POST"
                    ,data: JSON.stringify({boardName: boardName})
                    ,dataType: "json"
                    ,contentType: 'application/json'
                    ,success: function(res) {
                        if (res) {
                            $('#createName').val(null);

                            if ($('.tr-non').length == 1) {
                                $('.tr-non').remove();
                            }

                            var str = '';
                            str += '<tr class="tr">'
                            str += '    <td>' + res + '</td>';
                            str += '    <td>' + boardName + '</td>';
                            str += '    <td>';
                            // str += '        <button class="btn" >수정</button>';
                            str += '        <button class="btn btn-danger" onclick="$board.ui.delBtn(this, '+res+');">삭제</button>';
                            str += '    </td>';
                            str += '</tr>';
                            $('.list-board').append(str);
                        } else {
                            alert("게시판 생성 실패");
                        }
                    }
                    ,error: function(res, a) {
                        alert("서버에서 처리중 에러가 발생하였습니다.");
                    }
                });
            }
        }

        ,uptBtn: function(othis, boardNo) {
            // 생략
        }

        ,delBtn: function(othis, boardNo) {
            if (!boardNo) {
                return false;
            }
            if (confirm("게시판을 삭제하면 게시물도 삭제됩니다. 삭제하시겠습니까?")) {
                $.ajax({
                    url: "/admin/board/"+boardNo
                    ,type: "DELETE"
                    ,success: function(res) {
                        if (res == 1) {
                            $(othis).closest('tr').remove();
                            if ($('.tr').length == 0) {
                                $('.list-board').append('<tr class="tr-non"><td colspan="3">생성된 게시판이 없습니다.</td></tr>');
                            }
                        } else {
                            alert("게시판 삭제 실패");
                        }
                    }
                    ,error: function(res, a) {
                        alert("서버에서 처리중 에러가 발생하였습니다.");
                    }
                });
            }
        }

    }

}(window, document));