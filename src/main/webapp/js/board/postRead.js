(function (W, D) {
    W.$postRead = W.$postRead || {};

    $(function() {
        $postRead.ui.init();
    });

    $postRead.ui = {
        boardNo: null
        ,postNo: null

        ,init:function() {
            if (this.boardNo && this.postNo) {
                var boardNo = this.boardNo;
                var postNo = this.postNo;

                this.uptBtn(boardNo, postNo);
                this.delBtn(boardNo, postNo);
                this.clsBtn(boardNo);
            }
        }

        ,uptBtn: function(boardNo, postNo) {
            $('#uptBtn').click(function() {
                location.href = "/board/" + boardNo + "/post/" + postNo;
            });
        }

        ,delBtn: function(boardNo, postNo) {
            $('#delBtn').click(function() {
                if (confirm("게시물을 정말로 삭제하시겠습니까?")) {
                    var url = "/board/" + boardNo + "/post/" + postNo;

                    $.ajax({
                        url: url
                        ,type: "DELETE"
                        ,success: function(res) {
                            if (res == 1) {
                                alert("게시물이 삭제되었습니다.");
                                location.href = "/board/" + boardNo;
                            }
                        }
                        ,error: function(res, a) {
                            alert("서버에서 처리중 에러가 발생하였습니다.");
                        }
                    });
                }
            });
        }

        ,clsBtn: function(boardNo) {
            $('#clsBtn').click(function() {
                // history.back(-1);
                location.href = "/board/" + boardNo;
            });
        }

    }

}(window, document));