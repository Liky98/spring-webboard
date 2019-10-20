(function (W, D) {
    W.$board = W.$board || {};

    $(function() {
        $board.ui.init();
    });

    $board.ui = {
        boardNo: null
        ,pageNo: null

        ,init:function() {
            if (this.boardNo) {
                var boardNo = this.boardNo;
                var pageNo = this.pageNo;
                this.getPostList(boardNo, pageNo);
                this.rgstBtn(boardNo);
            }
        }

        ,getPostList: function(boardNo, pageNo) {
            var url = "/board/list/" + boardNo;
            var params = {
                pageNo: pageNo
            }

            $.ajax({
                url: url
                ,type: "GET"
                ,data: params
                ,success: function(res) {
                    $('#list-article').html(res);
                }
                ,error: function(res, a) {
                    alert("서버에서 처리중 에러가 발생하였습니다.");
                }
            });
        }

        ,rgstBtn: function(boardNo) {
            $('#rgstBtn').click(function() {
                location.href = "/board/" + boardNo + "/post";
            });
        }

        ,readPostMap: function(boardNo, postNo) {
            if (boardNo && postNo) {
                location.href = "/board/" + boardNo + "/" + postNo;
            }
        }

    }

}(window, document));