(function (W, D) {
    W.$user = W.$user || {};

    $(function() {
        $user.ui.init();

    });

    $user.ui = {
        init:function() {

        }

        ,update: function(frm) {
            var params = $(frm).serializeObject();
            if (!params.nickname) {
                alert("닉네임을 입력하세요.");
                $(frm).findByName('nickname').focus();
                return false;
            }
            if (!params.password) {
                alert("현재 비밀번호를 입력하세요.");
                $(frm).findByName('password').focus();
                return false;
            }
            if (!params.password2) {
                alert("변경 비밀번호를 입력하세요.");
                $(frm).findByName('password2').focus();
                return false;
            }
            if (params.password == params.password2) {
                alert("변경 비밀번호가 현재 비밀번호와 동일합니다.");
                $(frm).findByName('password2').focus();
                return false;
            }
            if (!params.password_check || params.password2 != params.password_check) {
                alert("비밀번호 확인이 되지않았습니다.");
                $(frm).findByName('password_check').focus();
                return false;
            }

            if (confirm("개인정보를 수정하시겠습니까?")) {
                var url = "/user";
                $.ajax({
                    url: url
                    ,type: "POST"
                    ,data: JSON.stringify(params)
                    ,dataType: "json"
                    ,contentType: 'application/json'
                    ,success: function(res) {
                        if (res == 1) {
                            alert("정보가 수정되었습니다.");
                            location.href = url;
                        } else if (res == 2) {
                            alert("비밀번호가 틀렸습니다.");
                            $(frm).findByName('password').focus();
                        } else if (res == 3) {
                            alert("변경 비밀번호가 현재 비밀번호와 동일합니다.");
                            $(frm).findByName('password2').focus();
                        } else if (res == 4) {
                            alert("이미 존재하는 닉네임입니다.");
                            $(frm).findByName('nickname').focus();
                        } else {
                            alert("개인정보수정 실패");
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