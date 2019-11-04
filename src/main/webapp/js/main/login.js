(function (W, D) {
    W.$login = W.$login || {};

    $(function() {
        $login.ui.init();
    });

    $login.ui = {

        init: function() {
            this.pwModal();
        }

        ,pwModal: function() {
            $('#findPassword').click(function() {
                $.ajax({
                    url: "/pw"
                    ,type: "GET"
                    ,success: function(res) {
                        $('#pwModal').find('.modal-container').attr('style', 'width:450px');
                        $('#pwModal').find('.modal-content').html(res);
                        $('#pwModal').show();
                    }
                    ,error: function(res, a) {
                        //
                    }
                });
            });
            $('#pwModal').find('.close').click(function() {
                $('#pwModal').hide();
            });
        }

        ,findPassword: function(frm) {
            var params = $(frm).serializeObject();
            if (!params.userId) {
                alert("아이디를 입력하세요.");
                $(frm).findByName('userId').focus();
                return false;
            }
            if (!params.userName) {
                alert("이름을 입력하세요.");
                $(frm).findByName('userName').focus();
                return false;
            }

            $.ajax({
                url: "/pw"
                ,type: "POST"
                ,data: JSON.stringify(params)
                ,dataType: "json"
                ,contentType: 'application/json'
                ,success: function(res) {
                    if (res.result == 1) {
                        $login.ui.pwResultModal(res.password);
                    } else if (res.result == -1) {
                        alert("서버에서 처리중 에러가 발생하였습니다.");
                    } else {
                        alert("비밀번호를 찾을 수 없습니다.");
                        $(frm).findByName('userId').focus();
                    }
                }
                ,error: function(res, a) {
                    alert("서버에서 처리중 에러가 발생하였습니다.");
                }
            });
        }

        ,pwResultModal: function(password) {
            var str = "";
            str += "새로운 비밀번호가 발급되었습니다.<br/>";
            str += "비밀번호는 <b>" + password + "</b>입니다.<br/><br/>";
            str += "로그인후 사용자페이지에서 비밀번호를 변경해주시길 바랍니다.<br/><br/><br/>";
            str += "<button class=\"btn btn-lg btn-primary close\" type=\"button\">확인</button>";

            $('#pwResultModal').find('.modal-content').html(str);
            $('#pwResultModal').show();

            $('#pwResultModal').find('.close').click(function() {
                $('#pwResultModal').hide();
                $('#pwModal').hide();
            });
        }

        /**
         * login check - spring security로 대체
         * @param frm
         * @returns {boolean}
         */
        ,login: function(frm) {
            var params = $(frm).serializeObject();
            if (!params.userId) {
                alert("ID를 입력하세요.");
                return false;
            }
            if (!params.password) {
                alert("PW를 입력하세요.");
                return false;
            }

            $.ajax({
                url: "/login"
                ,type: "POST"
                ,data: JSON.stringify(params)
                ,dataType: "json"
                ,contentType: 'application/json'
                ,success: function(res) {
                    if (res == 1) {
                        location.href = "/";
                    } else if (res == -1) {
                        alert("로그인 정보를 찾을 수 없습니다.");
                    } else {
                        alert("로그인 정보를 다시 확인하십시오.");
                    }
                }
                ,error: function(res, a) {
                    alert("서버에서 처리중 에러가 발생하였습니다.");
                }
            });
        }
    }

}(window, document));