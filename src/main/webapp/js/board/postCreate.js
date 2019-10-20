(function (W, D) {
    W.$postCreate = W.$postCreate || {};

    $(function() {
        $postCreate.ui.init();
    });

    $postCreate.ui = {
        boardNo: null

        ,init:function() {
            var boardNo = this.boardNo;
            if (boardNo) {
                this.rgstBtn(boardNo);
            }
            this.clsBtn();
        }

        ,rgstBtn: function(boardNo) {
            $('#rgstBtn').click(function() {
                var frm = $('#createPostForm');
                var params = frm.serializeObject();
                if (!params.title) {
                    alert("제목을 입력하세요.");
                    frm.findByName('title').focus();
                    return false;
                }
                if (getLength(params.title) > 500) {
                    alert("제목의 허용된 글자수가 초과되었습니다.");
                    frm.findByName('title').focus();
                    return false;
                }
                if (!params.content) {
                    alert("내용을 입력하세요.");
                    frm.findByName('content').focus();
                    return false;
                }
                if (getLength(params.content) > 4000) {
                    alert("내용의 허용된 글자수가 초과되었습니다.");
                    frm.findByName('content').focus();
                    return false;
                }

                if (confirm("게시물을 등록하시겠습니까?")) {
                    var url = "/board/" + boardNo + "/post";
                    params.boardNo = boardNo;

                    $.ajax({
                        url: url
                        ,type: "POST"
                        ,data: JSON.stringify(params)
                        ,dataType: "json"
                        ,contentType: 'application/json'
                        ,success: function(res) {
                            if (res == 1) {
                                alert("게시물이 등록되었습니다.");
                                location.href = "/board/" + boardNo;
                            } else {
                                alert("게시물 등록이 실패했습니다.");
                            }
                        }
                        ,error: function(res, a) {
                            alert("서버에서 처리중 에러가 발생하였습니다.");
                        }
                    });
                }
            });
        }

        ,clsBtn: function() {
            $('#clsBtn').click(function() {
                if(confirm("이전화면으로 돌아가시겠습니까?")) {
                    history.back(-1);
                }
            });
        }

    }

}(window, document));